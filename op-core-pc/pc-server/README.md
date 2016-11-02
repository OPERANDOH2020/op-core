# OPERANDO Policy Computation Component

## Overview
The role of the policy computation component is to manage both user's privacy
preferences and legal privacy regulations and how they relate to the usage
of private data by online service providers. For this purpose, the component
exposes a set of Restful endpoints.

## Deployment

To run the server, please execute the following:

```
mvn clean package jetty:run
```

You can then view the swagger listing here:

```
http://localhost:8081/swagger.json
```

Note that if you have configured the `host` to be something other than localhost, the calls through
swagger-ui will be directed to that host and not localhost!

## Policy Evaluation

The Policy Evalation API is available at:

```
http://localhost:8081/osp_policy_evaluator
```
There is one POST method available

**osp_policy_evaluator**
----
  Returns a json report describing if the data subject (defined by the unique OPERANDO ID) allows
or denies a set of requests .

* **URL**

  /osp_policy_evaluator/

* **Method:**

  `POST`
  
*  **QUERY PARAMS**

   **Required:**
    The OPERANDO generated unique identifier of an OSP that is requesting access to data.
   `osp_id=[string]`

    The OPERANDO generated unique identifier of a data subject (user).
   `user_id=[string]`

* **Data Params**

    **Required:**
    Json data describing the list of access requests. These are a list of oData fields being requested by
    an OSP subject (ie. data user). For example, a doctor wants to access a patient's weight field.
    [{
        requester_id: [string], 
        subject: [string], 
        requested_url: [string], 
        "action": [string], 
        "attributes": [json array]
     },{
        requester_id: [string], 
        subject: [string], 
        requested_url: [string], 
        "action": [string], 
        "attributes": [json array]
     },
    ]
  
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    Json data describing the evaluation report:

    `{ status : [string],   // All requests allowed or not? "true" or "false"
       compliance : string, // If true: "VALID" otherwise Reason for false - "NO_POLICY", "PREFS_CONFLICT"
       [{
            datauser: [string] // e.g. doctor
            datafield: [url]   // oData field being accessed
            action: [string]    // Action being carried out by the datauser
            result: [string]    // "true","false" if this request is allowed
        },{
            datauser: 
            datafield:
            action: 
            result
        }] 
    }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User policy doesn't exist" }`


* **Sample Call:**

  ```CURL
   curl -H "Content-Type: application/json" -X POST -d "[{"requester_id": "osp1","subject": "doctor","requested_url": "http://services.odata.org/TripPinRESTierService/patient('ospuser1')/personal_information/gender","action": "Access","attributes": []}]" "http://localhost:8081/osp_policy_evaluator?user_id=_demo_user1&osp_id=osp1"

  ```