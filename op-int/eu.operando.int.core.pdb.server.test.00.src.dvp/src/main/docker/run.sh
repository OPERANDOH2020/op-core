#!/bin/bash

echo PDB Endpoint: $PDB_ENDPOINT

all_Successful=0

curl -d @sources/aslbergamo_gat.json -H "Content-Type:application/json" $PDB_ENDPOINT/OSP
if [ $? -eq 0 ]
then
  echo "ASL BERGAMO SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

curl -d @sources/ami.json -H "Content-Type:application/json" $PDB_ENDPOINT/OSP
if [ $? -eq 0 ]
then
  echo "AMI SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

curl -d @sources/yellowpages.json -H "Content-Type:application/json" $PDB_ENDPOINT/OSP
if [ $? -eq 0 ]
then
  echo "YELLOWPAGES SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

curl -d @mongo/osp_POST.json -H "Content-Type:application/json" $PDB_ENDPOINT/OSP
if [ $? -eq 0 ]
then
  echo "OSP POST SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

curl -d @mongo/osp_foodcoach.json -H "Content-Type:application/json" $PDB_ENDPOINT/OSP
if [ $? -eq 0 ]
then
  echo "OSP FOODCOACH SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

curl -H "Content-Type: application/json" -X POST -d "{\"user_id\": \"pjgrace\",\"user_preferences\": [],\"subscribed_osp_policies\": [{\"osp_id\": \"Hospital OSP\",\"access_policies\": [{\"subject\": \"Doctor\",\"permission\": true,\"action\": \"Access\",\"resource\": \"Medical Information\",\"attributes\": []}]},{\"osp_id\": \"Food Coach demo\",\"access_policies\": [{\"subject\": \"Dietician\",\"permission\": true,\"action\": \"Access\", \"resource\": \"Medical Information\", \"attributes\": []}]}], \"subscribed_osp_settings\": []}" $PDB_ENDPOINT/user_privacy_policy 
if [ $? -eq 0 ]
then
  echo "PJGRACE SUCCESSFULLY LOADED"
else
  echo "ERROR"
  all_Successful=1
fi

if [ $all_Successful -eq 0 ]
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