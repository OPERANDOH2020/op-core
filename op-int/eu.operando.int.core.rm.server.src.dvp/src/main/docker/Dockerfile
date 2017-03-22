# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

ENV DAN_ENDPOINT none
ENV AAPI_ENDPOINT none
ENV LDB_ENDPOINT none
ENV RM_URLPATH none

ADD sources sources
ADD requirements.txt requirements.txt

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8080

WORKDIR /sources

ADD run.sh /run.sh
RUN chmod 750 /run.sh
ENV PATH /:$PATH

CMD ["/run.sh"]



