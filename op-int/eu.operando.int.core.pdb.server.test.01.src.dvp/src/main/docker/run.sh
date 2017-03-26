#!/bin/bash

all_Successful=0

cd /sources
python3 -m unittest

sleep 10

if [ $? -eq 0 ]
then
  echo "Final Result:"
  echo "Errorcode: $?"
  echo "ALL_SUCCESSFUL"
else
  echo "Final Result:"
  echo "Errorcode: $?"
  echo "ERROR"
fi

echo "Leaving..."