#!/bin/sh


set -o errexit
set -o nounset


RUN python3 preload.py

uvicorn run_app:app --host "0.0.0.0" --port $PORT --reload --ws 'auto' \
--loop 'auto' --workers 2