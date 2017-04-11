# I take a official mono image https://hub.docker.com/_/andyshinn/dnsmasq:2.75
# we choose normal 
FROM andyshinn/dnsmasq:2.75

ENV LOCAL_IP=10.0.0.4

ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /usr/local/tomcat:$PATH

ENTRYPOINT ["run.sh"]