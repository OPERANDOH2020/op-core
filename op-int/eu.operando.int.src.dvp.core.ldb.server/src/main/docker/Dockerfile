# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM tomcat:7

RUN apt-get update && apt-get install mysql-client -y && rm -rf /var/lib/apt/lists/*

ENV MYSQL_DB_USER root
ENV MYSQL_DB_PASSWORD root
ENV MYSQL_DB_NAME operando_logdb
ENV MYSQL_DB_HOST none

ADD sql sql
ADD run.sh .

CMD ["run.sh"]