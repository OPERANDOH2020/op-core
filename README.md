# server
The server repository contains the following projects:

--LogDB--
Module that inserts data comming from the requests to the Log database.
[Detailed Module Description](LogDB/README.md)

In this first approach it is being used LOG4J to perform the insertions in a declarative way.
Just for testing purposes, there have been created 2 log4j configuration files: one that uses a MySQL database and other one using
derby database. It is possible to choose any of these two databases for testing purposes.
These files can be found on config folder.

Requirements for executing this first testing version:
- create a new derby or mysql database.
- execute the script under script folder in the database.
- lo4jDerby.properties or lo4jMySql.properties has to be modified according to the previously created database .
- specify which database is going to be used (derby or mysql). This can be established through OperandoLog.logToDB() method (first instruction).
- execute the Test class by running it as a 2 Junit Test.
- after this, a new record should have been created in Logs table.
