#!/bin/bash
java -jar eu.operando.int.core.ae.server.test.01.src.dvp-ALPHA-jar-with-dependencies.jar
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
