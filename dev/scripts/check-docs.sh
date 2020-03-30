#!/bin/bash

SCRIPT_DIR=$(cd "$( dirname "$( readlink "$0" || echo "$0" )" )"; pwd)

GOPATH="${SCRIPT_DIR}" go run "${SCRIPT_DIR}/src/alluxio.org/check-docs/main.go"
