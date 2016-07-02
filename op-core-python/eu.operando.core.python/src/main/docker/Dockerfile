# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY rm/app.py /usr/src/app/
COPY requirements.txt /usr/src/app/

# ONBUILD RUN pip install --no-cache-dir -r requirements.txt





