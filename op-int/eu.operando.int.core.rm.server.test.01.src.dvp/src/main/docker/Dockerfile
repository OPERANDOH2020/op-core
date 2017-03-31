# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

ENV OLD_RM_URLPATH operando/rm
ENV RM_URLPATH operando/core/rm
ENV OLD_DAN_ENDPOINT http://integration.operando.esilab.org:8111/operando/pdr/dan
ENV DAN_ENDPOINT http://pdr.dan.operando.lan.esilab.org:8111/operando/pdr/dan
ENV OLD_AAPI_ENDPOINT http://integration.operando.esilab.org:8135/operando/interfaces/aapi
ENV AAPI_ENDPOINT http://aapi.integration.operando.lan.esilab.org:8135/operando/interfaces/aapi
ENV OLD_LDB_ENDPOINT http://ldb.integration.operando.esilab.org:8090/operando/core/ldb
ENV LDB_ENDPOINT http://ldb.integration.operando.lan.esilab.org:8090/operando/core/ldb
ENV OLD_PC_ENDPOINT http://integration.operando.esilab.org:8095/operando/core/pc
ENV PC_ENDPOINT http://integration.operando.lan.esilab.org:8095/operando/core/pc
ENV OLD_RM_ENDPOINT http://127.0.0.1:8102
ENV RM_ENDPOINT http://integration.operando.lan.esilab.org:8102
ENV OLD_AAPI_USER rmuser
ENV AAPI_USER rmuser
ENV OLD_AAPI_PASSWORD r1ghts
ENV AAPI_PASSWORD r1ghts

ADD sources sources
ADD requirements.txt requirements.txt

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8080

WORKDIR /sources

ADD run.sh /run.sh
RUN chmod 750 /run.sh
ENV PATH /:$PATH

CMD ["/run.sh"]



