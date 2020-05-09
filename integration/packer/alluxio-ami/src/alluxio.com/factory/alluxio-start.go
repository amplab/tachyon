package factory

import (
	"fmt"
	"time"
)

func StartAlluxio(config *UserDataConfigure) error {
	var cmds []string
	if config.AlluxioRole == MasterRole {
		if config.AlluxioRestoreUri != nil && config.AlluxioRestoreUri != "" {
			cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a -i %v master", alluxioHome, config.AlluxioRestoreUri))
			cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a -i %v job_master", alluxioHome, config.AlluxioRestoreUri))
		} else {
			cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a master", alluxioHome))
			cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a job_master", alluxioHome))
		}
	} else {
		if _, err := Exec(fmt.Sprintf("%v/bin/alluxio-mount.sh", alluxioHome), []string{"SudoMount", "local"}); err != nil {
			return err
		}
		if err := waitForLeaderMaster(); err != nil {
			return err
		}

		cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a worker", alluxioHome))
		cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a job_worker", alluxioHome))
	}
	cmds = append(cmds, fmt.Sprintf("%v/bin/alluxio-start.sh -a proxy", alluxioHome))
	if err := runAsAlluxioUser(cmds); err != nil {
		return err
	}
	return nil
}

func waitForLeaderMaster() error {
	if err := RetryWithWait(5*time.Second, 25, func() (bool, error) {
		_, err := Exec(fmt.Sprintf("%v/bin/alluxio", alluxioHome), []string{"fsadmin", "report"})
		return err == nil, nil
	}); err != nil {
		return err
	}
	return nil
}

func runAsAlluxioUser(cmds []string) error {
	for _, cmd := range cmds {
		args := []string{"runuser", "-l", "alluxio", "-c"}
		args = append(args, cmd)
		output, err := Exec("sudo", args)
		fmt.Println(fmt.Sprintf("Running command %v, output is \n %v", cmd, output))
		if err != nil {
			return err
		}
	}
	return nil
}
