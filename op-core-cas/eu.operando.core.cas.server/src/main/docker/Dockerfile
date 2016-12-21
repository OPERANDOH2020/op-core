# I take a official apereo/cas
# we choose normal 
FROM apereo/cas:v4.1.5

ADD cas.properties /etc/cas/cas.properties
RUN mkdir -p /etc/cas/WEB-INF
ADD deployerConfigContext.xml /etc/cas/WEB-INF/deployerConfigContext.xml
WORKDIR /etc/cas
RUN jar -uvf /cas-overlay/target/cas.war WEB-INF
WORKDIR /cas-overlay

# Exposed ports by parent docker
# EXPOSE 8080 8443
