#!/bin/bash
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME -e sql/creation.sql
catalina.sh run
