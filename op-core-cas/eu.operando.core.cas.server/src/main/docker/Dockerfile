# I take a official apereo/cas
# we choose normal 
FROM apereo/cas:v4.1.5

ADD cas.properties /etc/cas/cas.properties
ADD deployerConfigContext.xml /cas-overlay/src/main/webapp/WEB-INF/deployerConfigContext.xml
ADD pom.xml /cas-overlay/pom.xml

RUN wget https://raw.githubusercontent.com/osixia/docker-light-baseimage/stable/image/service-available/%3Assl-tools/assets/default-ca/default-ca.pem
RUN keytool -v -alias openldapsrv -import -file default-ca.pem -keystore /etc/cas/jetty/thekeystore -storepass changeit

# Exposed ports by parent docker
# EXPOSE 8080 8443

