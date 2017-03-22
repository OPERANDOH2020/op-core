# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

ADD sources sources
WORKDIR  /sources

RUN pip install --no-cache-dir -r requirements.txt
RUN pip install --no-cache-dir -r test-requirements.txt

ADD run.sh /run.sh
RUN chmod 750 /run.sh
ENV PATH /:$PATH

CMD ["run.sh"]



