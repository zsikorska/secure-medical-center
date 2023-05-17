all:
	docker-compose up -d
	watch -n0.5 docker ps

access:
	docker exec -it mysql_db /bin/bash

database:
	docker-compose up -d mysql_db

down:
	docker-compose down --remove-orphans

clean:
	docker rmi -f $(shell docker images -aq)
	docker rm -f $(shell docker ps -aq)
	docker ps -a
	docker images -a
