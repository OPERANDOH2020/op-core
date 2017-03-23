# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM tomcat:7

ENV OLD_MONGO_HOST mongo.integration.operando.lan.esilab.org
ENV MONGO_HOST mongo.integration.operando.lan.esilab.org
ENV OLD_MONGO_PORT 27017
ENV MONGO_PORT 27017
ENV OLD_AAPI_ENDPOINT http://aapi.integration.operando.lan.esilab.org:8135/operando/interfaces/aapi
ENV AAPI_ENDPOINT http://aapi.integration.operando.lan.esilab.org:8135/operando/interfaces/aapi
ENV OLD_LDB_ENDPOINT http://ldb.integration.operando.lan.esilab.org:8090/operando/core/ldb
ENV LDB_ENDPOINT http://ldb.integration.operando.lan.esilab.org:8090/operando/core/ldb

ADD generated/. /usr/local/tomcat/webapps

ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /usr/local/tomcat:$PATH

CMD ["run.sh"]