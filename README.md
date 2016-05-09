# op-core-anonymizationengine
Repository for anonymization engine.
Server and client stubs are included.

Requirements to execute the test case:
- the testing MySql Database has to be created (the scripts can be found under testingDatabase folder
- connection data has to be checked on the Test.java class
- this server project has to be deployed on a web server. For testing purposes it has
  been used the jetty web server.
- the test project contains a JUNit test case that can be run directly from the eclipse environment.
  Apart from containing an example on how to call a exposed web service from a module, it contains also a simple example on how
  can be used the ARX Api to anonymize some data comming from a testing database.
