#!/bin/bash
java -jar eu.operando.int.core.ldb.server.test01.src.dvp-ALPHA-jar-with-dependencies.jar
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
