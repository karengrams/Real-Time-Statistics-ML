#!/bin/bash

OUTPUT=$(ls -1)
echo "${OUTPUT}"

MULTILINE=$(ls \
   -1)
echo "${MULTILINE}"

mvn package

docker build -t melichallenge .

clear

docker run -p 5001:8080 melichallenge