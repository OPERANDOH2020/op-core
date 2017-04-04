# I take a official mono image https://hub.docker.com/_/java/
# we choose normal 
FROM java:8

ADD generated/. /

ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /:$PATH

CMD ["run.sh"]