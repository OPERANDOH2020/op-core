#!/bin/bash
java -jar eu.operando.core.anonymization.test.jar
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
