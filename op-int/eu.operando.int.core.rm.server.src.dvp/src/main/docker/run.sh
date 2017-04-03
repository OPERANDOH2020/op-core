#!/bin/bash

echo DAN Endpoint: $DAN_ENDPOINT
echo AAPI Endpoint: $AAPI_ENDPOINT
echo LDB Endpoint: $LDB_ENDPOINT
echo RM Url Path: $RM_URLPATH
RM_URLPATH_LENGHT=${#RM_URLPATH}
echo RM Url Path lenght: $RM_URLPATH_LENGHT

# http://askubuntu.com/questions/76808/how-do-i-use-variables-in-a-sed-command

sed -i "s|addr == \"monitor\"|addr == \"$RM_URLPATH\/monitor\"|g"  \
/sources/app.py

sed -i -e "s|$OLD_DAN_ENDPOINT|$DAN_ENDPOINT|" \
/sources/app.py
sed -i -e "s|$OLD_DAN_ENDPOINT|$DAN_ENDPOINT|" \
/sources/config.cfg

sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|" \
/sources/app.py
sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|" \
/sources/config.cfg

sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|" \
/sources/app.py
sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|" \
/sources/config.cfg

sed -i -e "s|$OLD_PC_ENDPOINT|$PC_ENDPOINT|" \
/sources/app.py
sed -i -e "s|$OLD_PC_ENDPOINT|$PC_ENDPOINT|" \
/sources/config.cfg

#OLD_AAPI_USER=username
sed -i -e "s|$OLD_AAPI_USER$|$AAPI_USER|" \
/sources/config.cfg
#OLD_AAPI_PASSWORD=password
sed -i -e "s|$OLD_AAPI_PASSWORD$|$AAPI_PASSWORD|" \
/sources/config.cfg

cd /sources

python app.py



