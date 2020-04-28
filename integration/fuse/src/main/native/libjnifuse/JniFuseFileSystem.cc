#include <fuse.h>
#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <jni.h>

#include "JniFuseFileSystem.h"
#include "debug.h"
#include "fuse.h"

using namespace jnifuse;

JniFuseFileSystem::JniFuseFileSystem()
{
}

JniFuseFileSystem::~JniFuseFileSystem()
{
}

void JniFuseFileSystem::init(JNIEnv* env, jobject obj)
{
    env->GetJavaVM(&this->jvm);
    this->fs = env->NewGlobalRef(obj);

    this->getattrOper = new GetattrOperation(this);
    this->openOper = new OpenOperation(this);
    this->readOper = new ReadOperation(this);
    this->readdirOper = new ReaddirOperation(this);
}

JniFuseFileSystem* JniFuseFileSystem::getInstance()
{
    static JniFuseFileSystem* instance = nullptr;
    if (instance == nullptr) {
        instance = new JniFuseFileSystem();
    }
    return instance;
}

JNIEnv* JniFuseFileSystem::getEnv()
{
    JNIEnv* env;
    this->jvm->AttachCurrentThreadAsDaemon((void **)&env, NULL);
    return env;
}

jobject JniFuseFileSystem::getFSObj()
{
    return this->fs;
}

Operation::Operation()
{
}

Operation::~Operation()
{
}

GetattrOperation::GetattrOperation(JniFuseFileSystem* fs) 
{
    this->fs = fs;
    JNIEnv* env = this->fs->getEnv();
    this->obj = this->fs->getFSObj();
    this->clazz = env->GetObjectClass(this->fs->getFSObj());
    this->signature = "(Ljava/lang/String;Ljava/nio/ByteBuffer;)I";
    this->methodID = env->GetMethodID(this->clazz, "getattrCallback", signature);
}

int GetattrOperation::call(const char *path, struct stat* stbuf)
{
    JNIEnv* env = this->fs->getEnv();
    jstring jspath = env->NewStringUTF(path);
    jobject buffer = env->NewDirectByteBuffer((void *)stbuf, sizeof(struct stat));

    int ret = env->CallIntMethod(this->obj, this->methodID, jspath, buffer);
    
    env->DeleteLocalRef(jspath);
    env->DeleteLocalRef(buffer);

    return ret;
}

OpenOperation::OpenOperation(JniFuseFileSystem* fs)
{
    this->fs = fs;
    JNIEnv* env = this->fs->getEnv();
    this->obj = this->fs->getFSObj();
    this->clazz = env->GetObjectClass(this->fs->getFSObj());
    this->signature = "(Ljava/lang/String;Ljava/nio/ByteBuffer;)I";
    this->methodID = env->GetMethodID(this->clazz, "openCallback", signature);
}

int OpenOperation::call(const char *path, struct fuse_file_info *fi)
{
    JNIEnv* env = this->fs->getEnv();
    jstring jspath = env->NewStringUTF(path);
    jobject fibuf = env->NewDirectByteBuffer((void *)fi, sizeof(struct fuse_file_info));

    int ret = env->CallIntMethod(this->obj, this->methodID, jspath, fibuf);

    env->DeleteLocalRef(jspath);
    env->DeleteLocalRef(fibuf);

    return ret;
}

ReadOperation::ReadOperation(JniFuseFileSystem* fs)
{
    this->fs = fs;
    JNIEnv* env = this->fs->getEnv();
    this->obj = this->fs->getFSObj();
    this->clazz = env->GetObjectClass(this->fs->getFSObj());
    this->signature = "(Ljava/lang/String;Ljava/nio/ByteBuffer;JJLjava/nio/ByteBuffer;)I";
    this->methodID = env->GetMethodID(this->clazz, "readCallback", signature);
}

int ReadOperation::call(const char *path, char *buf, size_t size, off_t offset, struct fuse_file_info *fi)
{
    JNIEnv* env = this->fs->getEnv();
    jstring jspath = env->NewStringUTF(path);
    jobject buffer = env->NewDirectByteBuffer((void *)buf, size);
    jobject fibuf = env->NewDirectByteBuffer((void *)fi, sizeof(struct fuse_file_info));
    
    int ret = env->CallIntMethod(this->obj, this->methodID, jspath, buffer, size, offset, fibuf);

    env->DeleteLocalRef(jspath);
    env->DeleteLocalRef(buffer);
    env->DeleteLocalRef(fibuf);

    return ret;
}

ReaddirOperation::ReaddirOperation(JniFuseFileSystem* fs)
{
    this->fs = fs;
    JNIEnv* env = this->fs->getEnv();
    this->obj = this->fs->getFSObj();
    this->clazz = env->GetObjectClass(this->fs->getFSObj());
    this->signature = "(Ljava/lang/String;JLalluxio/jnifuse/FuseFillDir;JLjava/nio/ByteBuffer;)I";
    this->methodID = env->GetMethodID(this->clazz, "readdirCallback", signature);

    this->fillerclazz = env->FindClass("alluxio/jnifuse/FuseFillDir");
    this->fillerconstructor = env->GetMethodID(fillerclazz, "<init>", "(J)V");
}

int ReaddirOperation::call(const char* path, void* buf, fuse_fill_dir_t filler,
        off_t offset, struct fuse_file_info* fi) {
    
    JNIEnv* env = this->fs->getEnv();

    jobject fillerobj = env->NewObject(this->fillerclazz, this->fillerconstructor, (void *)filler);
    
    jstring jspath = env->NewStringUTF(path);
    jobject fibuf = env->NewDirectByteBuffer((void *)fi, sizeof(struct fuse_file_info));

    int ret = env->CallIntMethod(this->obj, this->methodID, jspath, buf, fillerobj, offset, fibuf);

    env->DeleteLocalRef(fillerobj);
    env->DeleteLocalRef(jspath);
    env->DeleteLocalRef(fibuf);

    return ret;
}