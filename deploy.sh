#!/bin/sh

docker-compose down
docker-compose up -d --build
docker rmi $(docker images -f "dangling=true" -q)
