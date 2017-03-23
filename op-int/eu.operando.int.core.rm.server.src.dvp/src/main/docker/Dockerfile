# I take a official python image https://hub.docker.com/_/python/
# we choose normal 
FROM python:2.7.12

ENV RM_URLPATH none
ENV OLD_DAN_ENDPOINT https://snf-706921.vm.okeanos.grnet.gr:8443/op-pdr-dan
ENV DAN_ENDPOINT http://pdr.dan.operando.lan.esilab.org:8111/operando/pdr/dan
ENV OLD_AAPI_ENDPOINT https://snf-706921.vm.okeanos.grnet.gr:8443/authentication
ENV AAPI_ENDPOINT http://aapi.integration.operando.lan.esilab.org:8135/operando/interfaces/aapi
ENV OLD_LDB_ENDPOINT http://server02tecnalia.westeurope.cloudapp.azure.com:8090/operando/core/ldb
ENV LDB_ENDPOINT http://ldb.integration.operando.lan.esilab.org:8090/operando/core/ldb
ENV OLD_AAPI_USER username
ENV AAPI_USER operando
ENV OLD_AAPI_PASSWORD password
ENV AAPI_PASSWORD 1234

ADD sources sources
ADD requirements.txt requirements.txt

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8080

WORKDIR /sources

ADD run.sh /run.sh
RUN chmod 750 /run.sh
ENV PATH /:$PATH

CMD ["/run.sh"]



