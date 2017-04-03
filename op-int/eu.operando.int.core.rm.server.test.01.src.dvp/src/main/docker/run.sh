#!/bin/bash

echo DAN Endpoint: $DAN_ENDPOINT
echo AAPI Endpoint: $AAPI_ENDPOINT
echo LDB Endpoint: $LDB_ENDPOINT
echo RM Url Path: $RM_URLPATH
RM_URLPATH_LENGHT=${#RM_URLPATH}
echo RM Url Path lenght: $RM_URLPATH_LENGHT

# http://askubuntu.com/questions/76808/how-do-i-use-variables-in-a-sed-command
# I replace the endpoint path

sed -i -e "s|$OLD_DAN_ENDPOINT|$DAN_ENDPOINT|g" \
/sources/tester.py
sed -i -e "s|$OLD_DAN_ENDPOINT|$DAN_ENDPOINT|g" \
/sources/config.cfg

sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|g" \
/sources/tester.py
sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|g" \
/sources/config.cfg

sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|g" \
/sources/tester.py
sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|g" \
/sources/config.cfg

sed -i -e "s|$OLD_PC_ENDPOINT|$PC_ENDPOINT|g" \
/sources/tester.py
sed -i -e "s|$OLD_PC_ENDPOINT|$PC_ENDPOINT|g" \
/sources/config.cfg

sed -i -e "s|$OLD_RM_ENDPOINT|$RM_ENDPOINT|g" \
/sources/tester.py
sed -i -e "s|$OLD_RM_ENDPOINT|$RM_ENDPOINT|g" \
/sources/config.cfg

#OLD_AAPI_USER=username
sed -i -e "s|$OLD_AAPI_USER$|$AAPI_USER|g" \
/sources/config.cfg
#OLD_AAPI_PASSWORD=password
sed -i -e "s|$OLD_AAPI_PASSWORD$|$AAPI_PASSWORD|g" \
/sources/config.cfg

cd /sources

python tester.py

if [ $? -eq 0 ]
then
  echo "Final Result:"
  echo "Errorcode: $?"
  echo "ALL SUCCESSFUL"
else
  echo "Final Result:"
  echo "Errorcode: $?"
  echo "ERROR"
fi

echo "Leaving..."

