# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

ADD sources sources
ADD requirements.txt requirements.txt

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8080

WORDIR /sources

CMD [ "python", "./app.py" ]




