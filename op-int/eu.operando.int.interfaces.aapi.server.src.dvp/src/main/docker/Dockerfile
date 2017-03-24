# I take a official mono image https://hub.docker.com/_/tomcat/
# we choose normal 
FROM tomcat:7-jre8

ADD generated/. /usr/local/tomcat/webapps
RUN wget https://raw.githubusercontent.com/osixia/docker-light-baseimage/stable/image/service-available/%3Assl-tools/assets/default-ca/default-ca.pem
RUN keytool -alias openldapsrv -import -file default-ca.pem -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt

ADD run.sh .
RUN chmod 750 run.sh
ENV PATH /usr/local/tomcat:$PATH

CMD ["run.sh"]