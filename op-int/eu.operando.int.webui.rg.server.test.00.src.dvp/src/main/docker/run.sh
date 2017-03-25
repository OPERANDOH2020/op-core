#!/bin/bash
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME_TEST1 < sql/test1.sql
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME_TEST2 < sql/test2.sql
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
