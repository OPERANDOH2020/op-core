# I take a official apereo/cas
# we choose normal 
FROM apereo/cas:v4.1.5

ADD cas.properties /etc/cas/cas.properties
ADD cas-overlay /cas-overlay
ADD pom.xml /cas-overlay/pom.xml
ADD certs /cas-overlay/certs

RUN wget https://raw.githubusercontent.com/osixia/docker-light-baseimage/stable/image/service-available/%3Assl-tools/assets/default-ca/default-ca.pem
RUN keytool -alias openldapsrv -import -file default-ca.pem -keystore /etc/cas/jetty/thekeystore -storepass changeit -noprompt
RUN keytool -alias openldapsrv -import -file default-ca.pem -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -noprompt
RUN keytool -alias jetty -import -file /cas-overlay/certs/jetty.pem -keystore /etc/cas/jetty/thekeystore -storepass changeit -noprompt
RUN keytool -alias jetty -import -file /cas-overlay/certs/jetty.pem -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit -noprompt

# Exposed ports by parent docker
# EXPOSE 8080 8443

