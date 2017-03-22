#!/bin/bash

echo DAN Endpoint: $DAN_ENDPOINT
echo AAPI Endpoint: $AAPI_ENDPOINT
echo LDB Endpoint: $LDB_ENDPOINT
echo RM Url Path: $RM_URLPATH

# http://askubuntu.com/questions/76808/how-do-i-use-variables-in-a-sed-command
# I replace the endpoint path
sed -i "s|path\[:7\] != \"Results\"|\path != \"$RM_URLPATH\/Results\"|g"  /sources/app.py
RM_URLPATH_LENGHT=${#RM_URLPATH}+9
echo RM Url Path lenght: $RM_URLPATH_LENGHT
sed -i "s|path\[8:\]|\path\[$RM_URLPATH_LENGHT:\]|g"  /sources/app.py

#__DAN_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/op-pdr-dan/%s'
OLD_DAN_ENDPOINT=https://snf-706921.vm.okeanos.grnet.gr:8443/op-pdr-dan
sed -i -e "s|$OLD_DAN_ENDPOINT|$DAN_ENDPOINT|" \
/sources/app.py

#__aapi_st_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets/%s'
#__aapi_tgt_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets'
#__aapi_tckt_val_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets/%s/validate?serviceId=%s'
OLD_AAPI_ENDPOINT=https://snf-706921.vm.okeanos.grnet.gr:8443/authentication
sed -i -e "s|$OLD_AAPI_ENDPOINT|$AAPI_ENDPOINT|" \
/sources/app.py

#__URL_LOGDB = 'http://server02tecnalia.westeurope.cloudapp.azure.com:8090/operando/core/ldb/log'
OLD_LDB_ENDPOINT=http://server02tecnalia.westeurope.cloudapp.azure.com:8090/operando/core/ldb
sed -i -e "s|$OLD_LDB_ENDPOINT|$LDB_ENDPOINT|" \
/sources/app.py

OLD_AAPI_USER=username
sed -i -e "s|$OLD_AAPI_USER$|$AAPI_USER|" \
/sources/config.cfg
OLD_AAPI_PASSWORD=password
sed -i -e "s|$OLD_AAPI_PASSWORD$|$AAPI_PASSWORD|" \
/sources/config.cfg

cd sources
python app.py



