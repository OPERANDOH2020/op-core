#  8000: admin
#  8080: websockets
docker stop OPERANDO
docker rm OPERANDO
docker run -p 9001:8080  --name="OPERANDO" operando

