#!/bin/bash

OLD_MONGO_HOST=mongo.integration.operando.lan.esilab.org
MONGO_HOST=changed
OLD_MONGO_PORT=27017
MONGO_PORT=changed
OLD_AAPI_ENDPOINT=http://aapi.integration.operando.lan.esilab.org:8135/operando/interfaces/aapi
AAPI_ENDPOINT=changed
OLD_LDB_ENDPOINT=http://ldb.integration.operando.lan.esilab.org:8090/operando/core/ldb
LDB_ENDPOINT=changed

echo MONGO Host OLD: $OLD_MONGO_HOST
echo MONGO Host: $MONGO_HOST
echo MONGO Port OLD: $OLD_MONGO_PORT
echo MONGO Port: $MONGO_PORT
echo AAPI Endpoint OLD: $OLD_AAPI_ENDPOINT
echo AAPI Endpoint: $AAPI_ENDPOINT
echo LDB Endpoint OLD: $OLD_LDB_ENDPOINT
echo LDB Endpoint: $LDB_ENDPOINT

WAR_FILENAME=operando#core#pdb
WAR_FILE=$WAR_FILENAME.war
WAR_DIR=/usr/local/tomcat/webapp
TMP_DIR=/tmp
echo War Filename: $WAR_FILENAME
echo War File: $WAR_FILE
echo War Directory: $WAR_DIR
echo Tmp Dir: $TMP_DIR

echo Decompress file to tmp
mkdir -p $TMP_DIR/$WAR_FILENAME
cd $TMP_DIR/$WAR_FILENAME
jar xvf $WAR_DIR/$WAR_FILE 

echo Replace
REPLACE_FILE_PATH=$TMP_DIR/$WAR_FILENAME/WEB-INF/classes/db.properties
sed -i -e "s|$OLD_MONGO_HOST|$MONGO_HOST|" $REPLACE_FILE_PATH
sed -i -e "s|$OLD_MONGO_PORT|$MONGO_PORT|" $REPLACE_FILE_PATH


REPLACE_FILE_PATH=$TMP_DIR/$WAR_FILENAME/WEB-INF/classes/service.properties
sed -i -e "s|$OLD_MONGO_HOST|$MONGO_HOST|" $REPLACE_FILE_PATH
sed -i -e "s|$OLD_MONGO_PORT|$MONGO_PORT|" $REPLACE_FILE_PATH
sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|" $REPLACE_FILE_PATH
sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|" $REPLACE_FILE_PATH

echo Recompress file
jar cvf $WAR_FILE .

echo move file
mv $TMP_DIR/$WAR_FILENAME/$WAR_FILE $WAR_DIR/$WAR_FILE

catalina.sh run
