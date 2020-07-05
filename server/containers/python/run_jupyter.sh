#!/bin/sh

set -o errexit
set -o nounset

sleep 10
jupyter lab --port=8888 --no-browser --ip=0.0.0.0 --allow-root
