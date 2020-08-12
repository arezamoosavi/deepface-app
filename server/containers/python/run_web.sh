#!/bin/sh


set -o errexit
set -o nounset

while python3 check_database.py; do echo 'connecting to database...'; sleep 2; done;


echo ". . . . . Web Boot Up Is Done! . . . . ."

uvicorn run_app:app --host ${HOST} --port ${PORT} --reload --ws 'auto' \
--loop 'auto' --workers 8

exec "$@"