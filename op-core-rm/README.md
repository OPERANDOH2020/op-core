
#### Rights Management 
The Rights Management module ensures that OSPs have the required privileges to access the requested user data. RM implements the logic of granting or denying requests to access private user data. Its functionality includes granular evaluation of privileges, down to specific user data fields, against the UPP and explicit user consent. The results of the evaluation shall include an access token and a machine-readable response that describes the data items approved.
The Rights Management module is also responsible for managing user consent to grant access to her private data. It implements the workflows of triggering requests for user consent using client applications or email, acting on user input and documenting user consent.

The Rights Management module can be considered a wrapper for access to DAN. In this regard, it receives a request from the Gatekeeper in the form of an oData query and tries to apply the according privacy policies. Therefore, Rights management provides a RESTful API under the root address which supports the POST, GET and PUT HTTP methods.
Conforming to the oData standard, Rights Management receives the query to be executed using the corresponding method and extracts the corresponding IDs and fields when this is possible. Based on this information, it contacts the PC module to receive the user preferences (based on the IDs) for each field. If the method would change the data, the case of Insert/Update, this check is performed prior to the action, since the IDs are already known. However, if the action is to select data, then Rights Management contacts PC afterwards, when it will have access to all IDs. 
In the header of the request, there is a service ticket which is used for authentication and should it be validated, all actions will be performed. Based on the service ticket, the Rights Management determines which is the user requesting access to the data as well as its role to query PC accordingly.



#### Installation 
RM depends on the following Python modules:
•	urllib2
•	json
•	flask
•	xml
•	ssl
•	requests
•	json
•	ConfigParser
•	re
Depending on the Python installation, most of the aforemetioned modules are preinstalled, apart from flask, however all of them can be installed via the standard pip install.

#### User guide 
To interact with the module one needs to go invoke an HTTP method(GET, PUT, POST) to port 8102 of the root directory where RM is listening, providing a valid oDATA query e.g. 
http://127.0.0.1:8102/Users(301)/MetadatavalueDetails?$format=json&$expand=MetadatafieldregistryDetails
Moreover, since the user is expected to be the Gatekeeper, he needs to provide some additional headers. These are the following:
•	service-ticket: a valid service ticket from CAS to use RM
•	osp-identifier: as the name implies, the name of the OSP to be queried
•	psp-user-identifier: the name of the user from OSP who issued the query


