#!/bin/bash

rm -rf data_server/

java -jar swagger-codegen-cli-2.2.2.jar generate -i swagger.yaml -l msf4j -o data_server --api-package cz.cuni.mff.d3s.spl.restapi

