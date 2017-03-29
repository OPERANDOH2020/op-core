#!/bin/bash

echo RG_BIRT_ENDPOINT: $RG_BIRT_ENDPOINT
grep -rl -e "http:\/\/www.birt.sassuolo.info\/birt-viewer_3_7\/" **/*.sql | xargs sed -i -e "s/http:\/\/www.birt.sassuolo.info\/birt-viewer_3_7\//$RG_BIRT_ENDPOINT/g" 

mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME_TEST1 < sql/test1.sql
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME_TEST2 < sql/test2.sql
if [ $? -eq 0 ]
then
  echo "ALL SUCCESSFUL"
else
  echo "ERROR"
fi
