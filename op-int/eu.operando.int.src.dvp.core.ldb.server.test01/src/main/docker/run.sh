#!/bin/bash
java -jar eu.operando.int.src.dvp.core.ldb.server.test01-ALPHA-jar-with-dependencies.jar
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
