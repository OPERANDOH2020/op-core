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
 
   `osp_id=[string]`
   `user_id=[string]`

* **Data Params**

    **Required:**
    [{requester_id: [string], subject: [string], requested_url: [string], "action": [string], "attributes": [json array]}]
  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 
    `{ status : "false", compliance : "PREFS_CONFLICT", [{datauser: doctor
        datafield: http://services.odata.org/osp/patient('ospuser3')/personal_information/anthropometric_data/body_circumference
        action: ACCESS
        result: true},{{datauser: nurse
        datafield: http://services.odata.org/osp/patient('ospuser3')/personal_information/anthropometric_data/weight
        action: COLLECT}] }`
 
* **Error Response:**

  * **Code:** 404 NOT FOUND <br />
    **Content:** `{ error : "User policy doesn't exist" }`


* **Sample Call:**

  ```CURL
   curl -H "Content-Type: application/json" -X POST -d "[{"requester_id": "osp1","subject": "doctor","requested_url": "http://services.odata.org/TripPinRESTierService/patient('ospuser1')/personal_information/gender","action": "Access","attributes": []}]" "http://localhost:8081/osp_policy_evaluator?user_id=_demo_user1&osp_id=osp1"

  ```