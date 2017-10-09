# Online Service Provider Enforcement (OSE Component)

## Overview
The Online Service Provider Enforcement component (OSE) ensures that Online
Service Providers (OSPs) behaviour in terms of their usage of a user's personal 
data complies with the privacy policy statements that they make and register
with Operando about usage of private data; and also creates reports based upon
the OSP's stated privacy policy and functional usage that can be used by
privacy experts to evaluate the extent to which they comply with current
privacy regulations. 

More specifically, the OSE module has the following key functional purposes: 
* The OSE component manages when a G2C OSP changes its behaviour—ensuring that user policies 
are updated to comply with GDPR regulations. That is, users are notified of all changes to the
privacy policy; and their consents are set to false for the changes. They must then
choose to opt-in or not.

* The OSE component audits the behaviour of an OSP in regards to its stated
privacy policy. An audit report can be produced on request that evaluates data
access requests and reports policy conflicts.

* The OSE produces consent reports that can be used to analyse how users make
choices about their personal data.

## Installation

The component is implemented as a Maven project. Therefore, to install and
deploy the OSE component, first ensure that Maven is installed and then execute
the "jetty:run" target goal in Maven. This can be performed as followed via
the command line:

```
mvn clean package jetty:run
```

The software component is then accessible via the HTTP endpoint:

```
http://domainhost:port
```

There are then four REST interface endpoints that can be interacted with.

The OSP API, which is a set of methods for managing OSP policy changes and auditing behaviour:
```
http://domainhost:port/osps
```

The Consent API, which is a set of methods for producing consent reports:
```
http://domainhost:port/consents
```

The Monitor API, which is a set of methods for monitoring the OSE component:
```
http://domainhost:port/monitor
```

The Regulation API, which is a set of methods for adding new regulations:
```
http://domainhost:port/regulations
```

The full REST API documentation for each of these four interfaces is included in
the following section.

## Programming Guide

The following programming guide describes each and every method available from
the OSE component, with working examples to illustrate how to execute each
REST call.

### Consent API ###

#### GET Consent Report ####
----
This method produces and returns a consent report about all users, which is a JSON document 
describing whether consent has been given or not to a particular action of the
OSP as described in its privacy policy. For example, an action policy could be
"Doctor reads Patient's BMI data".

The method can produce four different types of reports based upon the parameters
provided to the call.

1. An anonymised list of all access consents by all users subscribed to the OSP.
2. The list of all access consents by all users subscribed to the OSP for a specific field.
3. The list of all access consents by all users subscribed to the OSP by a specific role.
3. The list of all access consents by all users subscribed to the OSP by a specific role for a specific field.

* **URL**

  /consents/{osp-id}

* **Method:**

  `GET` 
  
*  **URL Params**

   **Required:**
 
   `osp-id=[alphanumeric]`

    This is a path parameter entered in the URL. It is the unique Operando identifier
    given to an OSP.

   **Optional:**
 
   `field=[alphanumeric]`

    This is the field name of a data element in the OSP's policy. That is a field that
    the OSP performs actions on.

    `role=[alphanumeric]`

    This is the name of a user role in the OSP's policy. That is a user type who
    performs actions on the data.

* **Success Response:**
  
  <_What should the status code be on success and is there any returned data? This is useful when people need to to know what their callbacks should expect!_>

  * **Code:** 200 <br />
    **Content:** `
[{"subject":"Doctor","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]},{"subject":"Receptionist","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]},{"subject":"WebsiteAdmins","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]}]`
 
* **Sample Calls:**

1.  Get an anonymised list of all access consents by all users subscribed to the OSP "YellowPages".

    `curl -H "Content-type: application/json" -X GET "http://hostname:port/consent/YellowPages"`

2. The list of all access consents by all users subscribed to the OSP (YellowPages) for a specific field (personalInfo.patient.name).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages?field=personalInfo.patient.name"`

3. The list of all access consents by all users subscribed to the OSP (YellowPages) by a specific role (Doctor).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages?role=Doctor"`

4. The list of all access consents by all users subscribed to the OSP (YellowPages) by a specific role (Doctor) for a specific field (personalInfo.patient.name).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages?field=personalInfo.patient.name&role=Doctor"`

#### GET User Consent Report ####
----
This method produces and returns a consent report about an individual user, which is a JSON document 
describing whether consent has been given or not to a particular action of the
OSP as described in its privacy policy. For example, an action policy could be
"Doctor reads Patient's BMI data".

The method can produce four different types of reports based upon the parameters
provided to the call.

1. An anonymised list of all access consents by the user subscribed to the OSP.
2. The list of all access consents by the user subscribed to the OSP for a specific field.
3. The list of all access consents by the user subscribed to the OSP by a specific role.
3. The list of all access consents by the user subscribed to the OSP by a specific role for a specific field.

* **URL**

  /consents/{osp-id}/{user-id}

* **Method:**

  `GET` 
  
*  **URL Params**

   **Required:**
 
   `osp-id=[alphanumeric]`

    This is a path parameter entered in the URL. It is the unique Operando identifier
    given to an OSP.

    `user-id=[alphanumeric]`

    This is a path parameter entered in the URL. It is the unique Operando identifier
    of the user requiring a consent report for.

   **Optional:**
 
   `field=[alphanumeric]`

    This is the field name of a data element in the OSP's policy. That is a field that
    the OSP performs actions on.

    `role=[alphanumeric]`

    This is the name of a user role in the OSP's policy. That is a user type who
    performs actions on the data.

* **Success Response:**
  
  <_What should the status code be on success and is there any returned data? This is useful when people need to to know what their callbacks should expect!_>

  * **Code:** 200 <br />
    **Content:** `
[{"subject":"Doctor","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]},{"subject":"Receptionist","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]},{"subject":"WebsiteAdmins","permission":true,"action":"Access","resource":"personalInfo.patient.surname","attributes":[{"attribute_name":"category","attribute_value":"personal"},{"attribute_name":"friendly_name","attribute_value":"Surname"},{"attribute_name":"tooltip","attribute_value":"surname"}]}]`
 
* **Sample Calls:**

1.  Get an anonymised list of all access consents by all users subscribed to the OSP "YellowPages".

    `curl -H "Content-type: application/json" -X GET "http://hostname:port/consent/YellowPages/user1"`

2. The list of all access consents by all users subscribed to the OSP (YellowPages) for a specific field (personalInfo.patient.name).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages/user1?field=personalInfo.patient.name"`

3. The list of all access consents by all users subscribed to the OSP (YellowPages) by a specific role (Doctor).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages/user1?role=Doctor"`

4. The list of all access consents by all users subscribed to the OSP (YellowPages) by a specific role (Doctor) for a specific field (personalInfo.patient.name).
    `curl -H "Content-type: application/json" -X GET  "http://hostname:port/consent/YellowPages/user1?field=personalInfo.patient.name&role=Doctor"`