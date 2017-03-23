# I take a official mono image https://hub.docker.com/_/osixia/openldap
# we choose normal 
FROM osixia/openldap:1.1.7

# ADD bootstrap /container/service/slapd/assets/config/bootstrap

ADD aapi-ldap.ldif .
ADD acl.ldif .
ADD startup.sh /container/service/slapd/startup.sh
RUN chown openldap:openldap /container/service/slapd/startup.sh
RUN chmod 775 /container/service/slapd/startup.sh

# Expose default ldap and ldaps ports
EXPOSE 389 636

ENTRYPOINT ["/container/tool/run"]
#ENTRYPOINT ["/import.sh"]

