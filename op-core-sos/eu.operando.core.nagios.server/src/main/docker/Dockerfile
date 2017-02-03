# I take a nagios image https://hub.docker.com/r/jasonrivers/nagios/
# docker run --name nagios4  \
#   -v /path-to-nagios/etc/:/opt/nagios/etc/ \
#   -v /path-to-nagios/var:/opt/nagios/var/ \
#   -v /path-to-custom-plugins:/opt/Custom-Nagios-Plugins \
#   -p 0.0.0.0:8080:80 jasonrivers/nagios:latest
# The default credentials for the web interface is nagiosadmin / nagios
FROM jasonrivers/nagios:latest
ADD etc /opt/nagios/etc
