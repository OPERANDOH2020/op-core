#!/bin/bash
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME < sql/creation.sql
catalina.sh run
