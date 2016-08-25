# op-core-logdb
Repository for logdb.
Server and client stubs are included.

Requirements to execute the test cases (one for logdb and other for logdbsearch):
- the operando_logdb MySql Database containing the LOGS table has to be created.
  The creation script can be found under the databse/scripts folder on the
  server project.
- check that log4j.appender.DB.URL entry on lo4jMySql.properties under the
  src/main/resources/config on the server project folder points correctly to the database.
- both server projects has to be deployed on a web server. For testing purposes it has
  been used the tomcar web server.
- the test projects containing JUNit test cases can be run directly from the command line (executable jar files).
  The directly executable jar files can be found under the release/test folder of each of the server projects.
  Example of execution command: java -jar eu.operando.core.ldbsearch.test.jar
