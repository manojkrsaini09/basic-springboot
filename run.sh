#!/bin/bash
mvn clean
mvn spring-boot:run
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Deployment Successful"
else
echo "Deployment Failed"
fi