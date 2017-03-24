# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM tomcat:7

ADD generated/. /usr/local/tomcat/webapps

RUN apt-get update && apt-get install mysql-client -y && rm -rf /var/lib/apt/lists/*

ENV MYSQL_DB_USER root
ENV MYSQL_DB_PASSWORD root
ENV MYSQL_DB_NAME operando_personaldatadb
ENV MYSQL_DB_HOST none

ADD sql sql
ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /usr/local/tomcat:$PATH

CMD ["run.sh"]