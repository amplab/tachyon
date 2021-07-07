/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.fuse;

import alluxio.jnifuse.FuseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.Signal;
import sun.misc.SignalHandler;


public class FuseSignalHandler implements SignalHandler {

    private static final Logger LOG = LoggerFactory.getLogger(FuseSignalHandler.class);

    /**
     * Use to umount Fuse application during stop.
     */
    private final FuseUmountable mFuseUmountable;

    public FuseSignalHandler(FuseUmountable mFuseUmountable) {
        this.mFuseUmountable = mFuseUmountable;
    }

    @Override
    public void handle(Signal signal) {
        int number = signal.getNumber();
        if (number == 15) {
            try {
                mFuseUmountable.umount(false);
            } catch (FuseException e) {
                LOG.error("unable to umount fuse.", e);
                return;
            }
        }
        System.exit(0);
    }
}
