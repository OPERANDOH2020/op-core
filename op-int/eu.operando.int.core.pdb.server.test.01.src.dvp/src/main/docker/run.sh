#!/bin/bash

all_Successful=0

cd /sources
python3 -m unittest
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
