# Policy DataBase (PDB)

## Overview
The Policy Database (PDB) java implementation is composed of:
- pdb server
- pdb client
- pdb common-model (a common models shared between server and client)
- pdb server tests (unittest python scripts for the server api)
- aapi-client (aapi client)
- ldb-client (log db client)


## Build PDB Server
The PDB server is using the [JAX-RS](https://jax-rs-spec.java.net/) framework.

To run the server please execute the following:

```
mvn clean package jetty:run
```

The PDB server is packaged as a war file which can be deployed in any java
servlet container.

### PDB Service Dependencies
PDB server operation, requires valid connections to the following services:
- mongoDB server
- aapi server
- logdb server

### Configuration
resources/config/service.properties contains the main configuration properties
for the PDB service, i.e. mongoDB connection parameters, logdb and aapi
endpoints, as well as service registration id and credentials.

```
mongo.server.host=mongo.integration.operando.dmz.lab.esilab.org
mongo.server.port=27017
aapi.basepath=http://integration.operando.esilab.org:8135/operando/interfaces/aapi
#
logdb.basepath=http://integration.operando.esilab.org:8090/operando/core/ldb
logdb.service.id=/operando/core/ldb
#
pdb.osp.service.id=pdb/OSP/.*
pdb.osp.service.login=xxxx
pdb.osp.service.password=xxxx
# reg
pdb.reg.service.id=pdb/regulations/.*
pdb.reg.service.login=xxxx
pdb.reg.service.password=xxxx
# upp
pdb.upp.service.id=pdb/user_privacy_policy/.*
pdb.upp.service.login=xxxx
pdb.upp.service.password=xxxx
```

## PDB API

The Policy Database that stores three types of documents in dedicated
collections.

1. User Privacy Policy. Each OPERANDO user has one UPP document describing their
privacy policies for each of the OSP services they are subscribed to.  The UPP
contains the current B2C privacy settings for a subscribed to OSP.  The UPP
contains the users privacy preferences. The UPP contains the G2C access
policies for the OSP with justification for access.

2. The legislation policies. The regulations entered into OPERANDO using the
OPERANDO regulation API.

3. The OSP descriptions and data requests. For each OSP its privacy policy, its
access control rules, and the data it requests (as a workflow). For B2C, the
set of privacy settings is stored.


## UPP

Storage and management of UPP documents.

### GET /user_privacy_policy/
Perform a search query across the collection of UPPs.

The query contains a json object (names, values) and the request returns the
list of documents (UPPs) where the filter matches i.e. each document contains
fields with those values.

Parameters

- *filter* * string (query) The query filter to be matched - ?filter={json description}

Responses

- 200 The list of UPP documents that match the query are returned in full.
- 405 Error in request. There is an invalid input in the query field.

curl example: with filter policyText:*

```
curl -v
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/user_privacy_policy/?filter=%7B%27policyText%27:%27%27%7D
```

### POST /user_privacy_policy/
Create a new UPP entry in the database for the user.

Called when a new user is registered with operando. Their new privacy
preferences are passed in the UPP document and stored in the policy DB.

Parameters

- *upp* * (body) The first instance of this user’s UPP:
```
{
user_id: "",
user_references: [],
subscribed_osp_policies: [],
subscribed_osp_settings:[]
}
```

Responses

- 201
The document (UPP) was successfully created in the database. The Operando
user-id is returned as a string parameter

- 405
Error. The document (UPP) at this id has previously been created in the
database.

curl example:
```
curl -v -d @upp.json -H "Content-Type: application/json" http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/user_privacy_policy/ 
```

## GET /user_privacy_policy/{user-id}/
Read the user privacy policy for the given user id.

Get a specific UPP document via the user’s id. This will return the full user
privacy policy document in json format.

Parameters

- *user-id* * string (path) The user identifier number

Responses

- 200
The UPP document requested to be read is returned in full

- 404 Error the user does not exist

curl example:
```
curl -v  http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/user_privacy_policy/{user-id}
```

### PUT /user_privacy_policy/{user-id}/
Update UPP entry in the database for the user.

Called when a user makes a change to the UPP registered with operando.
Their new privacy preferences are passed in the UPP document and stored
in the policy DB.

Parameters

- *user-id* * string (path), the user identifier number

- *upp* * (body) the changed instance of this user’s UPP:
```
{
user_id: "",
user_references: [],
subscribed_osp_policies: [],
subscribed_osp_settings:[]
}
```

Responses:

- 204
The document (UPP) was successfully updated in the database.

- 404
Error. No document exists to be updated.

curl example:
```
curl -v -X PUT -d @post_access_reason.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/user_privacy_policy/{user-id}
```


### DELETE /user_privacy_policy/{user-id}/
Remove the UPP entry in the database for the user.

Called when a user leaves operando. Their privacy preferences and
policies are deleted from the database.

Parameters

- *user-id* * string (path) The user identifier number

Responses:

- 204
The document (UPP) was successfully deleted from the database.

- 404
Error. No document exists to be deleted.

curl example:
```
curl -v -X DELETE
http://integration.operando.esilab.org:8096/operando/core/pdb/user_privacy_policy/{user_id}
```


## OSP

Storage and management of OSP documents.

### GET /OSP/
Perform a search query across the collection of OSP behaviour.

The query contains a json object (names, values) and the request returns the
list of documents (regulations) where the filter matches i.e. the document
contains fields with those values.

Parameters
- *filter* * string (query) The query filter to be matched - ?filter={json description}

Responses

- 200
The list of OSP documents that match the query are returned in full.

- 405
Error in request. There is an invalid input in the query field.

curl example:

```
curl -v
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/?filter=%7B%27policyText%27:%27%27%7D
```

### POST /OSP/
Create a new OSP entry in the database.

Called by the policy computation component when a new regulation is added
to OPERANDO.

Parameters

- *osp-policy* * (body) The first instance of this OSP document:
```
{
policy_text: "",
policy_url: "",
workflow: [],
policies:[]
}
```

Responses

- 201
The document (OSPBehaviour) was successfully created in the database. The
Operando user-id is returned as a string parameter

- 405
Error. The document (OSPBehaviour) at this id has previously been created in
the database.

curl example
```
curl -v -d @osp.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/
```

### GET /OSP/{osp-id}/privacy-policy/
Get the current set of privacy policy statements about the usage of data for
stated reasons.

This is a machine readable version of a G2C privacy policy statement entered
using the OSP Admin dashboard; and retrieved by both the OSP & PSP analyst
dashboard for display purposes and also by the OSE component for checking
regulation compliance.

Parameters

- *osp-id* * string (path) The identifier number of an OSP

Responses

- 200
The list of OSP privacy policy statements are returned as a JSON object:
```
{
osp_policy_id: "",
policies: []
}
```

- 404
Error - the OSP does not have a policy stored in the db.

curl example:
```
curl -v  http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy
```


### PUT /OSP/{osp-id}/privacy-policy/
Update OSP text policy entry in the database.

Called when by the watchdog detects a change in the textual policy and wants
to update the current policy.

Parameters

- *osp-id* * string The identifier number of an OSP
- *osp-policy* * (body) the changed instance of this OSPRequest:
```
{
policies: []
}
```

Responses

- 204 The document (policy text) was successfully updated in the database
- 404 Error. No document exists to be updated

curl example
```
curl -v -X PUT -d @osp_hospital1.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy/
```

### GET /OSP/{osp-id}/privacy-policy/access-reasons
Get the list of access reason policy statements.

List of policy statements.

Parameters

- *osp-id* * string (path) The identifier number of an OSP

Responses

- 200
The list of OSP privacy policy statements are returned as a JSON object.

- 404
Error - the OSP does not have a policy stored in the db.

curl example:
```
curl -v
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy/access-reasons
```

### POST /OSP/{osp-id}/privacy-policy/access-reasons
Create a new access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements

Parameters

- *osp-id* * string (path) The identifier number of an OSP
- *osp-policy* * (body) The first instance of this new statement:
```
{
reasonid: "",
datauser: "",
datasubjecttype: "",
datatype: ""
reason: ""
}
```

Responses

- 201
The statements (AccessReason) was successfully created in the list.
- 405
Error. The document (AccessReason) at this id has previously been created in
the list.

curl example
```
curl -v -X PUT -d @put_reason_policy.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy/access-reasons
```

### PUT /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}
Update an access reason statement in the privacy policy.

Called by the UI when OSP updating the policy statements

Parameters

- *osp-id* * string (path) The identifier number of an OSP
- *reason-id* * string (path) The identifier of a statement in a policy, is only unique to the policy.
- *osp-policy* * (body) The updated instance of this OSP policy statement:
```
{
reasonid: "",
datauser: "",
datasubjecttype: "",
datatype: "",
reason: ""
}
```

Responses

- 204
The statements (OSPBehaviour) was successfully updated in the list.

- 404
Error. No statement in list to update, use post.

curl example:
```
curl -v  -X PUT -d @post_access_reason1.json -H "Content-Type:
application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}
```

### DELETE /OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}
Remove the AccessReason entry in the list.

Remove an identified value.

Parameters

- *osp-id* * string (path) The identifier number of an OSP
- *reason-id* * string (path) The identifier of a statement in a policy, is only unique to the policy.

Responses

- 204
The document (OSPBehaviour) was successfully deleted from the database.

- 404
Error. No document exists to be deleted.

curl example
```
curl -v -X DELETE
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}
```

### GET /OSP/{osp-id}/
Read a given OSP behaviour policy.

Get a specific OSP document via the id. This will return the full OSP document
in json format.

Parameters

- *osp-id* * string (path) The identifier number of an OSP

Responses

- 200
The OSP document requested to be read is returned in full:
```
{
policy_text: "",
policy_url: "",
workflow: [],
policies:[]
}
```

- 404 
Error - the osp does not have a policy in the db.

curl example:
```
curl -v http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/
```

### PUT /OSP/{osp-id}/
Update OSPBehaviour entry in the database.

Called when by the policy computation component when the regulator api
updates a regulation. Their new OSPRequest document is stored in the
policy DB.

Parameters

- *osp-id* * string (path) The identifier number of an OSP
- *osp-policy* * (body) The changed instance of this OSPRequest:
```
{
policy_text: "",
policy_url: "",
workflow: [],
policies:[]
}
```

Responses

- 204
The document (OSPBehaviour) was successfully updated in the database.
- 404
Error. No document exists to be updated.

curl example
```
curl -v -X PUT -d @osp_hospital1.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/
```

### DELETE /OSP/{osp-id}/
Remove the OSPRequest entry in the database.

Called when by the policy computation component when the regulator api
requests that the regulation be deleted.

Parameters

- *osp-id* * string (path) The identifier number of an OSP

Responses

- 204
The document (OSPBehaviour) was successfully deleted from the database.
- 404
Error. No document exists to be deleted.

curl example
```
curl -v -X DELETE http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/OSP/{osp-id}/
```

## Legislation

Storage and management of privacy regulations.

### GET /regulations/
Perform a search query across the collection of regulation.

The query contains a json object (names, values) and the request returns
the list of documents (regulations) where the filter matches i.e. the
document contains fields with those values.

Parameters

- *filter* * string (query) The query filter to be matched - ?filter={json description}

Responses

- 200
The list of regulations documents that match the query are returned in full.
- 405
Error in request. There is an invalid input in the query field.

curl example
```
curl -v
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/regulations/?filter=%7B%27action%27:%27delete%20my%20pictures%27%7D
```

### POST /regulations/
Create a new legislation entry in the database.

Called by the policy computation component when a new regulation is added
to OPERANDO.

Parameters

- *regulation* * (body) The first instance of this regulation document:
```
{
legislation_sector: "",
private_information_type: "",
action: "",
required_consent: ""
}
```

Responses

- 201
The document (PrivacyRegulation) was successfully created in the database and
the location field in the HTTP header contains the full URL of the resource.
The unique ID reg_id is stored in the body entity.

curl example
```
curl -v  -d @reg_OSE_POST.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/regulations/
```

### GET /regulations/{reg-id}/
Read a given legislation with its ID.

Get a specific legislation document via the id. This will return the
full legislation document in json format.

Parameters

- *reg-id* * string (path) The identifier number of a regulation

Responses

- 200
The regulation document requested to be read is returned in full:
```
{
reg_id: "",
legislation_sector: "",
private_information_type: "",
action: "",
required_consent: ""
}
```

- 404
Error - the regulation does not exist.

curl example:
```
curl -v http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/regulations/{reg_id}/
```


### PUT /regulations/{reg-id}/
Update PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api
updates a regulation. Their new PrivacyRegulation document is stored in
the policy DB.

Parameters

- *reg-id* * string (path) The identifier number of a regulation
- *regulation* * (body) The changed instance of this PrivacyRegulation:
```
{
legislation_sector: "",
private_information_type: "",
action: "",
required_consent: ""
}
```

Responses:

- 204
The document (PrivacyRegulation) was successfully updated in the database.
- 404
Error. No document exists to be updated.

curl example
```
curl -v -X PUT -d @reg_PUT.json -H "Content-Type: application/json"
http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/regulations/{reg-id}/
```

### DELETE /regulations/{reg-id}/
Remove the PrivacyRegulation entry in the database.

Called when by the policy computation component when the regulator api
requests that the regulation be deleted.

Parameters

- *reg-id* * string (path) The identifier number of a regulation

Responses

- 204
The document (PrivacyRegulation) was successfully deleted from the database.

- 404
Error. No document exists to be deleted.

curl example
```
curl -v -X DELETE  http://integration.operando.esilab.org:8096/operando/core/pdb/policy_database/regulations/{reg-id}/
```
