# op-core-logdb
Repository for logdb.
Server and client stubs are included.

Requirements to execute the test case:
- the operando_logdb MySql Database containing the LOGS table has to be created.
  The creation script can be found under the databse/scripts folder on the
  server project.
- check that log4j.appender.DB.URL entry on lo4jMySql.properties under the
  config on the server project folder points correctly to the database.
- this server project has to be deployed on a web server. For testing purposes it has
  been used the jetty web server.
- the test projects contains JUNit test cases that can be run directly from the command line (executable jar files).
