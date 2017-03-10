#!/bin/bash
mysql -u $MYSQL_DB_USER -p$MYSQL_DB_PASSWORD -h $MYSQL_DB_HOST -D $MYSQL_DB_NAME -e sql/test.sql
echo "el perro the san roque no tiene rabo"
echo "se de buen tinta que ramon rodriguez se lo ha cortado"
exit 3