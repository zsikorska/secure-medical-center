all:
	docker-compose up -d

buildrun:
	docker-compose up -d --build

access:
	docker exec -it mysql_db_sec /bin/bash

database:
	docker-compose up -d mysql_db_sec

down:
	docker-compose down --remove-orphans

clean:
	docker rmi -f $(shell docker images -aq)
	docker rm -f $(shell docker ps -aq)
	docker ps -a
	docker images -a
