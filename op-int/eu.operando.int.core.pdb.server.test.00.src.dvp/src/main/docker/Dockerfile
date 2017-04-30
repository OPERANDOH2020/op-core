# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM ubuntu

RUN apt-get update && apt-get install curl -y && rm -rf /var/lib/apt/lists/*

ENV PDB_ENDPOINT none

ADD sources	sources
ADD mongo mongo
ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /:$PATH

CMD ["run.sh"]