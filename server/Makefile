remove-containers:
	docker stop $(docker ps -aq)
	docker rm $(docker ps -aq)
	docker container prune -f

build:
	docker-compose build
	docker-compose rm -f

start:
	docker-compose up --remove-orphans

cleanup:
	docker-compose down -v

dl-files:
	docker-compose exec web python3 preload.py