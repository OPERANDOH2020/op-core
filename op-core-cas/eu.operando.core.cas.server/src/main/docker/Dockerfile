# I take a official apereo/cas
# we choose normal 
FROM apereo/cas:v4.1.5

ADD cas.properties /etc/cas/cas.properties
ADD deployerConfigContext.xml /cas-overlay/src/main/webapp/WEB-INF/deployerConfigContext.xml
ADD pom.xml /cas-overlay/pom.xml

# Exposed ports by parent docker
# EXPOSE 8080 8443

