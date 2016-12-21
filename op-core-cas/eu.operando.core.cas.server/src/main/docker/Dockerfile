# I take a official apereo/cas
# we choose normal 
FROM apereo/cas:v4.1.5

ADD cas.properties /etc/cas/cas.properties
RUN mkdir -p /etc/cas/WEB-INF
ADD deployerConfigContext.xml /etc/cas/WEB-INF/deployerConfigContext.xml
ADD updatewar.sh /updatewar.sh
RUN chmod 775 /updatewar.sh
RUN /updatewar.sh
ADD start.sh /start.sh
RUN chmod 775 /start.sh
RUN /start.sh

# Exposed ports by parent docker
# EXPOSE 8080 8443

CMD ["/start.sh"]