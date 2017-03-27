# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM ubuntu

RUN apt-get update && apt-get install mysql-client -y && rm -rf /var/lib/apt/lists/*

ENV MYSQL_DB_USER root
ENV MYSQL_DB_PASSWORD root
ENV MYSQL_DB_NAME_TEST1 operando_data
ENV MYSQL_DB_NAME_TEST2 operando_report
ENV MYSQL_DB_HOST none

ADD sql sql
ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /:$PATH

CMD ["run.sh"]