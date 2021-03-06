#!/usr/bin/env python
import urllib2
import json
from flask import jsonify
from flask import Flask, Response, abort, request
from xml.etree import ElementTree as ET
import ssl
import requests
import json
import ConfigParser
import re
import sys
import requests.auth

__hdr = {"Content-Type": "application/json", "Accept": "*/*", }

config = ConfigParser.RawConfigParser()
config.read('config.cfg')

user = config.get('Credentials', 'user')
passwd = config.get('Credentials', 'password')

__aapi_user = config.get('URLs', 'aapi_user')
__aapi_tgt_url = config.get('URLs', 'aapi_tgt_url')
__aapi_st_url = config.get('URLs', 'aapi_st_url')
__URL_LOGDB = config.get('URLs', 'URL_LOGDB')
__DAN_url = config.get('URLs', 'DAN_url')
__URL_PC = config.get('URLs', 'URL_PC')
__aapi_tckt_val_url = config.get('URLs', 'aapi_tckt_val_url')
__url_pc = config.get('URLs', 'url_pc')

app = Flask(__name__)


def is_json(myjson):
    try:
        json_object = json.loads(myjson)
    except ValueError, e:
        print "Error!:", myjson
        return False
    return True


def getUserRole(user):
    response = requests.get(__aapi_user + user)
    JSONResponse = response.text
    try:
        data = json.loads(JSONResponse)
        attr = data["requiredAttrs"]
        for a in attr:
            if a["attrName"] == 'role':
                return a["attrValue"]
    except:
        return ""


# contact PC and to get prefs


def getPCresponse(action, osp, userid, requester_id, urls, role):
    headers = {'Content-Type': 'application/json'}
    params = (('user_id', "pat_sql"), ('osp_id', osp))
    params = (('user_id', userid), ('osp_id', osp))
    print 20 * "*"
    print "QUERY TO PC"
    print "params", params
    print "headers", headers

    lst = ""
    for u in urls:
        curParam = '{"requester_id": "%s","subject": "%s","requested_url": "%s","action": "%s","attributes": []},' % (
            requester_id, role, u, action)
        lst += curParam
    PCRequestParams = "[" + lst[:-1] + "]"
    print PCRequestParams
    resp = requests.post("http://integration.operando.esilab.org:8095/operando/core/pc/osp_policy_evaluator",
                         headers=headers, params=params, data=PCRequestParams)
    print "PC RESPONSE\n", resp.text
    try:
        return json.loads(resp.text)
    except:
        return {"error": "problem request"}


def Log2CAS():
    data = '{"password": "%s","username": "%s"}' % (passwd, user)
    response = requests.post(__aapi_tgt_url, data=data,
                             verify=False, headers=__hdr)
    return response.text


def joinSTR(lst):
    return ','.join(str(e) for e in lst)


# get service ticket for dan


def GetST():
    response = requests.post(__aapi_st_url %
                             TGT, data='op-pdr/dan', verify=False)
    print response.text
    return response.text


def ValidateReceivedTicket(tckt, sID):
    r = requests.get(__aapi_tckt_val_url %
                     (tckt, '/operando/rm/'), verify=False)
    try:
        # validate the XML
        root = ET.fromstring(r.text)
        namespaces = {'cas': 'http://www.yale.edu/tp/cas'}
        elmt = root.find('*/*')
        # check whether the request has a valid ticket
        print elmt.text
        return elmt.text == "gatekeeper"
    except:
        return False
    return False


def logdata(requesterId, requestedFields, grantedFields, affectedUserID=""):
    dataPayload = {}
    dataPayload["userId"] = requesterId
    dataPayload["title"] = 'Access requested'
    dataPayload["description"] = 'Access requested by user ' + requesterId

    dataPayload["requesterId"] = requesterId
    dataPayload["requesterType"] = "MODULE"
    dataPayload["affectedUserId"] = affectedUserID
    dataPayload["requestedFields"] = requestedFields
    dataPayload["grantedFields"] = grantedFields

    dataPayload["logPriority"] = "NORMAL"
    dataPayload["logType"] = "DATA_ACCESS"
    dataPayload["logLevel"] = "INFO"

    dataPayload["keywords"] = ["query"]

    dataPayload = json.dumps(dataPayload)
    try:
        response = requests.post(__URL_LOGDB, headers=__hdr, data=dataPayload)
        responseBody = json.loads(response.text)
        if responseBody["type"].lower() != "ok":
            print "error logging", responseBody
    except Exception, e:
        print "error logging", e


def diff(first, second):
    second = set(second)
    return [item for item in first if item not in second]


# handle a select query
def handleSelect(request, addr):
    if addr == "monitor":
        return Response("{'status': 200, 'message': 'RM listening', }", status=200, mimetype='application/json')
    try:
        req_db = request.headers["osp-identifier"]
        psp_user_identifier = request.headers["psp-user-identifier"]
        requester = psp_user_identifier
        # validate the received ticket
        receivedST = request.headers["service-ticket"]
        # if not
        if not ValidateReceivedTicket(receivedST, '/operando/rm/'):
            return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400,
                            mimetype='application/json')
        ST = GetST()
    except:
        return Response("{'status': 400, 'message': 'Check your headers', }", status=400, mimetype='application/json')
    headers = {'Content-Type': 'application/json', 'service-ticket': ST, 'osp-identifier': req_db,
               'psp-user-identifier': psp_user_identifier, 'Cache-Control': 'no-cache', }
    requester_Role = getUserRole(psp_user_identifier)
    params = "?" + request.query_string if request.query_string else ""
    pams = (('$format', 'json'),)

    r = requests.get(__DAN_url % addr + params,
                     headers=headers, verify=False, params=pams)

    # check whether the response is OK
    print "*" * 10
    print "DAN Response:\n%s" % r.text.encode('utf-8')
    if not is_json(r.text.encode('utf-8')):
        return Response(r.text.encode('utf-8'), status=r.status_code, mimetype='application/json')
    else:
        jsonResponse = json.loads(r.text.encode('utf-8'))
    if r.status_code == 200:
        # check if we have an error
        if "error" in jsonResponse.keys():
            return Response(r.text.encode('utf-8'), status=500, mimetype='application/json')
        # check whether we have the IDs in the oData query
        # let's check whether the query has the user id
        uID_split = re.findall('\((.*?)\)', addr)
        if req_db != "YellowPages" and req_db != "built-in":
            # if value does not exist this means that the response is one entity and not a set of ones
            if 'value' not in jsonResponse:
                usersValue = jsonResponse
                counter = 0;
                fields2query = []
                userid = "-1"
                placeduserid = "-1"
                for element in usersValue.keys():
                    if element.lower() == "id": userid = usersValue[element]
                    if element.lower() == "userid":
                        placeduserid = usersValue[element]
                    # if element.lower() == "id": userid = "301"
                    fields2query.append(element)
                if placeduserid != "-1": userid = placeduserid
                print "--" * 10
                print userid
                print "#" * 20
                print fields2query
                print "#" * 20
                policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                         requester_id=psp_user_identifier, role=requester_Role, urls=fields2query)
                print ("pc polices for userid %s are %s" % (userid, policies))

                if policies["compliance"] == "NO_POLICY":
                    # there is no policy defined so return the result
                    # return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                    return Response(json.dumps({"d": {"error": "Policies restrictions"}}), status=200,
                                    mimetype='application/json')
                elif policies["compliance"] == "VALID":
                    # there is a VALID reponce, no policies exist, all data are shown
                    restrictedFields = []
                    logdata(requester, fields2query, fields2query, userid)
                    return Response(json.dumps(usersValue), status=200, mimetype='application/json')
                else:

                    restrictedFields = []
                    for ev in policies["evaluations"]:
                        print ev
                        if ev["result"] == "false":

                            # find the proper field to strip the data
                            for element in usersValue:
                                if element == ev["datafield"]:
                                    restrictedFields.append(ev["datafield"])
                                    usersValue[element] = "***PERMISSION DENIED***"

                            for k, v in usersValue.items():
                                if v == '***PERMISSION DENIED***':
                                    del usersValue[k]

                    logdata(requester, fields2query,
                            diff(fields2query, restrictedFields), userid)
                    return Response(json.dumps(usersValue), status=200, mimetype='application/json')
            else:
                usersValue = jsonResponse['value']
                counter = 0;
                # item is eg the User object
                for item in usersValue:
                    # print "I am in item %s\n"%item
                    fields2query = []
                    userid = "-1"
                    placeduserid = "-1"
                    for element in item:
                        placeduseridExists = False;
                        if element.lower() == "id": userid = item[element]
                        if element.lower() == "userid":
                            placeduserid = item[element]
                        # if element.lower() == "id": userid = "301"
                        fields2query.append(element)
                    if placeduserid != "-1": userid = placeduserid

                    print "--" * 10
                    print userid

                    policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                             requester_id=psp_user_identifier, role=requester_Role, urls=fields2query)

                    if policies["compliance"] == "NO_POLICY":
                        # there is no policy defined so return the result
                        # return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                        logdata(requester, fields2query, [], userid)
                        return Response(json.dumps({"d": {"error": "Policies restrictions"}}), status=200,
                                        mimetype='application/json')
                    elif policies["compliance"] == "VALID":
                        # there is a VALID reponce, no policies exist, all data are shown
                        # do nothing in the user object, everything should be visible
                        # just print a log
                        logdata(requester, fields2query, fields2query, userid)
                    else:
                        restrictedFields = []
                        for ev in policies["evaluations"]:
                            print ev
                            if ev["result"] == "false":

                                # find the proper field to strip the data
                                for element in item:
                                    if element == ev["datafield"]:
                                        restrictedFields.append(ev["datafield"])
                                        item[element] = "***PERMISSION DENIED***"

                                for k, v in item.items():
                                    if v == '***PERMISSION DENIED***':
                                        del item[k]

                        usersValue[counter] = item
                        counter = counter + 1
                        logdata(requester, fields2query,
                                diff(fields2query, restrictedFields), userid)

            return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
        else:  # built-in structure case (and YellowPages)
            if len(uID_split) == 1:
                userid = uID_split[0]
                fields2query = []
                # now we check whether the query returns metadata or not
                if "Metadata" in addr:
                    fields = jsonResponse["d"]["results"]
                    # get the returned field names to query PC
                    for f in fields:
                        fields2query.append(
                            f["MetadatafieldregistryDetails"]["Element"])

                    policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                             requester_id=psp_user_identifier, role=requester_Role, urls=fields2query)
                    if policies["compliance"] == "NO_POLICY":
                        # there is no policy defined so return the result
                        # return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                        logdata(requester, fields2query, [], userid)
                        return Response(json.dumps({"d": {"error": "Policies restrictions"}}), status=200,
                                        mimetype='application/json')
                    elif policies["compliance"] == "VALID":
                        # just do nothing, only show a log and print the user as it is
                        logdata(requester, fields2query, fields2query, userid)
                        return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')

                    elif policies["compliance"] == "PREFS_CONFLICT":
                        # there is a conflict in the policies
                        restrictedFields = []
                        for ev in policies["evaluations"]:
                            if ev["result"] == "false":
                                # find the proper field to strip the data
                                for f in jsonResponse["d"]["results"]:
                                    if f["MetadatafieldregistryDetails"]["Element"] == ev["datafield"]:
                                        restrictedFields.append(ev["datafield"])
                                        f["TextValue"] = "***PERMISSION DENIED***"
                                        del f["TextValue"]

                        logdata(requester, fields2query,
                                diff(fields2query, restrictedFields), userid)
                        return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                    else:
                        logdata(requester, fields2query, [], userid)
                        return Response(json.dumps({"d": {"error": "unknown"}}), status=400,
                                        mimetype='application/json')

                else:
                    fields2query = jsonResponse['d'].keys()
                    restrictedFields = []
                    # no metadata
                    policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                             requester_id=psp_user_identifier, role=requester_Role,
                                             urls=jsonResponse['d'].keys())
                    if policies["compliance"] == "NO_POLICY":
                        # there is no policy defined so return the result
                        logdata(requester, fields2query, [], userid)
                        return Response(json.dumps({"error": "Policies restrictions"}), status=200,
                                        mimetype='application/json')
                    elif policies["compliance"] == "VALID":
                        # do nothing, just return the json object as it is.
                        restrictedFields = []
                        logdata(requester, fields2query, fields2query, userid)
                        return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                    elif policies["compliance"] == "PREFS_CONFLICT":
                        # there is a conflict in the policies

                        for ev in policies["evaluations"]:
                            if ev["result"] == "false":
                                restrictedFields.append(ev["datafield"])
                                jsonResponse[ev["datafield"]] = "***PERMISSION DENIED***"
                                del jsonResponse[ev["datafield"]]

                        logdata(requester, fields2query,
                                diff(fields2query, restrictedFields), userid)
                        return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                    else:
                        logdata(requester, fields2query, [], userid)
                        return Response(json.dumps({"d": {"error": "unknown"}}), status=400,
                                        mimetype='application/json')
            # case we don't know the user id from the oData query, so we need to go
            # through the results
            else:
                # now we check whether the query returns metadata or not
                if "Metadata" in addr:
                    for i in range(len(jsonResponse["d"]["results"])):
                        userid = jsonResponse["d"]["results"][i]["Iduser"]
                        # get the returned field names to query PC
                        fields = jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"]
                        fields2query = []
                        for f in fields:
                            fields2query.append(
                                f["MetadatafieldregistryDetails"]["Element"])

                        policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                                 requester_id=psp_user_identifier, role=requester_Role,
                                                 urls=fields2query)
                        if policies["compliance"] == "NO_POLICY":
                            # there is no policy defined so return the result
                            logdata(requester, fields2query, [], userid)
                            jsonResponse["d"]["results"][i] = {"error": "Policies restrictions"}
                        elif policies["compliance"] == "VALID":
                            # do nothing, show all data
                            logdata(requester, fields2query, fields2query, userid)
                        elif policies["compliance"] == "PREFS_CONFLICT":
                            # there is a conflict in the policies
                            restrictedFields = []
                            for ev in policies["evaluations"]:
                                if ev["result"] == "false":
                                    # find the proper field to strip the data
                                    for j in range(
                                            len(jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"])):
                                        if jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"][j][
                                            "MetadatafieldregistryDetails"]["Element"] == ev["datafield"]:
                                            restrictedFields.append(ev["datafield"])
                                            jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"][j][
                                                "TextValue"] = "***PERMISSION DENIED***"

                            logdata(requester, fields2query,
                                    diff(fields2query, restrictedFields), userid)
                        else:
                            logdata(requester, fields2query, [], userid)
                            jsonResponse["d"]["results"][i] = {"error": "uknown"}

                    return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')

                else:
                    rows = jsonResponse["d"]["results"]
                    for i in range(len(rows)):
                        fields2query = jsonResponse["d"]["results"][i].keys()

                        userid = jsonResponse["d"]["results"][i]["Iduser"]
                        policies = getPCresponse(action="Select", osp=req_db,
                                                 userid=jsonResponse["d"]["results"][i]["Iduser"],
                                                 requester_id=psp_user_identifier, role=requester_Role,
                                                 urls=jsonResponse["d"]["results"][i].keys())
                        if policies["compliance"] == "NO_POLICY":
                            # there is no policy defined so return the result
                            logdata(requester, fields2query, [], userid)
                            jsonResponse["d"]["results"][i] = {"error": "Policies restrictions"}
                        elif policies["compliance"] == "VALID":
                            # do nothing, show all data
                            restrictedFields = []
                            logdata(requester, fields2query, fields2query, userid)
                        elif policies["compliance"] == "PREFS_CONFLICT":
                            # there is a conflict in the policies
                            restrictedFields = []
                            for ev in policies["evaluations"]:
                                if ev["result"] == "false":
                                    restrictedFields.append(ev["datafield"])
                                    jsonResponse["d"]["results"][i]["datafield"] = "***PERMISSION DENIED***"
                                    del jsonResponse["d"]["results"][i]["datafield"]
                            logdata(requester, fields2query,
                                    diff(fields2query, restrictedFields), userid)
                        else:
                            logdata(requester, fields2query, [], userid)
                            jsonResponse["d"]["results"][i] = {"error": "unknown"}

                    return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
    else:
        # return json/xml
        try:
            testResponse = json.loads(r.text)
            return Response("not an expected odata response:" + r.text, status=r.status_code,
                            mimetype='application/text')
        except:
            return Response("not an expected odata response:" + r.text, status=r.status_code,
                            mimetype='application/text')


# handle an insert query
def handleInsert(request, addr):
    if not request.json:
        abort(400)

    try:
        # validate the received ticket
        receivedST = request.headers["service-ticket"]
        req_db = request.headers["osp-identifier"]
        psp_user_identifier = request.headers["psp-user-identifier"]
        # if not
        if not ValidateReceivedTicket(receivedST, '/operando/rm/'):
            return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400,
                            mimetype='application/json')
    except:
        return Response("{'status': 400, 'message': 'Check your headers', }", status=400, mimetype='application/json')
    ST = GetST()
    headers = {'service-ticket': ST, 'Content-Type': 'application/json',
               'Accept': '*/*', 'osp-identifier': req_db, 'psp-user-identifier': psp_user_identifier}

    # get the userId from URL
    uID_split = re.findall('\((.*?)\)', addr)
    if len(uID_split) == 1:
        userid = uID_split[0]

    # get the model record
    addrParts = addr.split("/")
    model = addrParts[len(addrParts) - 1]

    # have to check with PC whether this guy can insert.
    policies = getPCresponse(action="Insert", osp=req_db, userid=userid,
                             requester_id=psp_user_identifier, urls=[model + ".record"],
                             role=getUserRole(psp_user_identifier))

    if policies["status"] == "true":
        # suppose that the response is true
        r = requests.post(__DAN_url % addr, headers=headers,
                          data=json.dumps(request.json), verify=False)
        # return Response(r.text, status=200, mimetype='application/json')
        if r.status_code == 200:
            ks = json.loads(request.data)
            # logdata("RM", "insert into table %s" % addr,"fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
            return Response(r.text, status=200, mimetype='application/json')
        else:
            return Response(r.text, status=r.status_code, mimetype='application/json')

    return Response("Permission Denied", status=401, mimetype='application/json')


# this is an update
def handleUpdate(request, addr):
    if not request.json:
        abort(400)
    # validate the received ticket
    receivedST = request.headers["service-ticket"]
    # if not
    if not ValidateReceivedTicket(receivedST, '/operando/rm/'):
        return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400,
                        mimetype='application/json')

    ST = GetST()
    req_db = request.headers["osp-identifier"]
    psp_user_identifier = request.headers["psp-user-identifier"]
    headers = {'service-ticket': ST, 'Content-Type': 'application/json',
               'Accept': '*/*', 'osp-identifier': req_db, 'psp-user-identifier': 'unknown', }
    # have to check with PC whether this guy can insert.
    res = getPCresponse(action="Update", osp=req_db, userid="",
                        requester_id=psp_user_identifier, subject="", urls=[])
    r = requests.put(__DAN_url % addr, headers=headers,
                     data=request.json, verify=False)
    if r.status_code == 200:
        # ks = json.loads(request.data)
        # logdata("requesterId", "update table %s" % addr,
        # "fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
        return Response("{'status': 200, 'message': 'Updated', }", status=400, mimetype='application/json')
    else:
        return Response(r.text,
                        status=r.status_code, mimetype='application/json')
    return Response("{'status': 200, 'message': 'Updated', }", status=200, mimetype='application/json')


@app.route('/', defaults={'path': ''}, methods=['POST', 'GET', 'PUT'])
@app.route('/<path:path>', methods=["GET", "POST", "PUT"])
def catch_all(path):
    # Depending on the OData call act accordingly...
    if request.method == "POST":
        # handle an insert
        return handleInsert(request, path)
    elif request.method == 'GET':
        return handleSelect(request, path)
    elif request.method == 'PUT':
        return handleUpdate(request, path)
    else:
        return Response("{'status': 400, 'message': 'Method not supported. Check the manual', }", status=400,
                        mimetype='application/json')


if __name__ == '__main__':
    TGT = Log2CAS()
    ST = GetST()
    # start the service
    app.run(host='0.0.0.0', port=8102, threaded=True, debug=True)
    # app.run(port=8102, threaded=True, debug=True)
