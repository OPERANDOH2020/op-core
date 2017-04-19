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

# load credentials
config = ConfigParser.RawConfigParser()
config.read('config.cfg')
user = config.get('Credentials', 'user')
passwd = config.get('Credentials', 'password')
__hdr = {"Content-Type": "application/json", "Accept": "*/*", }

# URLs
# config.get('URLs', 'aapi_tgt_url')
__aapi_tgt_url = config.get('URLs', 'aapi_tgt_url')
__aapi_st_url = config.get('URLs', 'aapi_st_url')
__URL_LOGDB = config.get('URLs', 'URL_LOGDB')
__DAN_url = config.get('URLs', 'DAN_url')
__URL_PC = config.get('URLs', 'URL_PC')
__aapi_tckt_val_url = config.get('URLs', 'aapi_tckt_val_url')
__url_pc = config.get('URLs', 'url_pc')

# OLD

__aapi_tgt_url = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets"
__aapi_st_url = 'http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets/%s'
# config.get('URLs', 'URL_LOGDB')
__URL_LOGDB = "http://ldb.integration.operando.esilab.org:8090/operando/core/ldb/log/"
# config.get('URLs', 'DAN_url')
__DAN_url = "http://integration.operando.esilab.org:8111/operando/pdr/dan/%s"
__URL_PC = "http://integration.operando.esilab.org:8095/operando/core/pc/osp_policy_evaluator"
__aapi_tckt_val_url = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets/%s/validate?serviceId=%s"
__aapi_user = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/user/"
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
    print 20*"*"
    print "QUERY TO PC"
    print "params",params
    print "headers",headers

    lst = ""
    for u in urls:
        curParam = '{"requester_id": "%s","subject": "%s","requested_url": "%s","action": "%s","attributes": []},' % (
            requester_id, role, u, action)
        lst += curParam
    PCRequestParams = "[" + lst[:-1] + "]"
    print PCRequestParams
    resp = requests.post("http://integration.operando.esilab.org:8095/operando/core/pc/osp_policy_evaluator",
                         headers=headers, params=params, data=PCRequestParams)
    print "PC RESPONSE\n",resp.text
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
        return elmt.text == "gatekeeper"
    except:
        return False
    return False


def logdata(requesterId, action, actiontype,affectedUserID=""):
    logdata = {}
    logdata["requesterType"] = "MODULE"
    logdata["userId"] = "001"    
	logdata["affectedUserId"] = affectedUserID
    logdata["requesterId"] = requesterId
    logdata["logPriority"] = "LOW"
    logdata["logType"] = "DATA_ACCESS"
    logdata["logLevel"] = "INFO"
    logdata["title"] = actiontype
    logdata["description"] = action
    logdata["keywords"] = ["query"]
    logdata = json.dumps(logdata)
    try:
        log_resp = requests.post(__URL_LOGDB, headers=__hdr, data=logdata)
        logmsg = log_resp.text
        msg = json.loads(logmsg)
        if msg["type"].lower() != "ok":
            print "error logging", msg
    except Exception, e:
        print "error logging", e


# handle a select query
def handleSelect(request, addr):
    if addr == "monitor":
        return Response("{'status': 200, 'message': 'RM listening', }", status=200, mimetype='application/json')
    try:
        req_db = request.headers["osp-identifier"]
        psp_user_identifier = request.headers["psp-user-identifier"]
        # validate the received ticket
        receivedST = request.headers["service-ticket"]
        # if not
        if not ValidateReceivedTicket(receivedST, '/operando/rm/'):

            return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400, mimetype='application/json')
        ST = GetST()
    except:
        return Response("{'status': 400, 'message': 'Check your headers', }", status=400, mimetype='application/json')
    headers = {'Content-Type': 'application/json', 'service-ticket': ST,  'osp-identifier': req_db,
               'psp-user-identifier': psp_user_identifier,            'Cache-Control': 'no-cache', }
    requester_Role = getUserRole(psp_user_identifier)
    params = "?" + request.query_string if request.query_string else ""
    pams = (('$format', 'json'),)
    r = requests.get(__DAN_url % addr + params,
                     headers=headers, verify=False, params=pams)
    # check whether the response is OK
    print "*"*10
    print "DAN Response:\n%s"%r.text
    if not is_json(r.text):
        return Response(r.text, status=r.status_code, mimetype='application/json')
    else:
        jsonResponse = json.loads(r.text)
    if r.status_code == 200:
        # check if we have an error
        if "error" in jsonResponse.keys():
            return Response(r.text, status=500, mimetype='application/json')
        # check whether we have the IDs in the oData query
        # let's check whether the query has the user id
        uID_split = re.findall('\((.*?)\)', addr)
        if len(uID_split) == 1:
            userid = uID_split[0]
            # now we check whether the query returns metadata or not
            if "Metadata" in addr:
                fields = jsonResponse["d"]["results"]
                # get the returned field names to query PC
                fields2query = []
                for f in fields:
                    fields2query.append(
                        f["MetadatafieldregistryDetails"]["Element"])

                policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                         requester_id=req_db, role=requester_Role, urls=fields2query)
                if policies["compliance"] == "NO_POLICY":
                    # there is no policy defined so return the result
                    # return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                    return Response(json.dumps({"d": {"error": "Policies restrictions"}}), status=200, mimetype='application/json')

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
			
		    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(restrictedFields), "Denied"), "Select", userid)
		    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(list(set(restrictedFields) - set(fields2query))), "Allowed"), "Select", userid)
		    
                    return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                else:
                    return Response(json.dumps({"d": {"error": "unknown"}}), status=400, mimetype='application/json')

            else:
                # no metadata
                policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                         requester_id=req_db, role=requester_Role, urls=jsonResponse['d'].keys())
                if policies["compliance"] == "NO_POLICY":
                    # there is no policy defined so return the result
                    return Response(json.dumps({"error": "Policies restrictions"}), status=200, mimetype='application/json')
                elif policies["compliance"] == "PREFS_CONFLICT":
                    # there is a conflict in the policies
		    restrictedFields = []
                    for ev in policies["evaluations"]:
                        if ev["result"] == "false":
                            restrictedFields.append(ev["datafield"])
                            jsonResponse[ev["datafield"]] = "***PERMISSION DENIED***"
                        
	    	    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(restrictedFields), "Denied"), "Select", userid)
                    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(list(set(restrictedFields) - set(fields2query))), "Allowed"), "Select", userid)
	   	    return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
                else:
                    return Response(json.dumps({"d": {"error": "unknown"}}), status=400, mimetype='application/json')
        # case we don't know the user id from the oData query, so we need to go
        # through the results
        else:
            # now we check whether the query returns metadata or not
            if "Metadata" in addr:
                for i in range(len(jsonResponse["d"]["results"])):
                    userid=jsonResponse["d"]["results"][i]["Iduser"]
                    # get the returned field names to query PC
                    fields = jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"]
                    fields2query = []
                    for f in fields:
                        fields2query.append(
                            f["MetadatafieldregistryDetails"]["Element"])

                    policies = getPCresponse(action="Select", osp=req_db, userid=userid,
                                             requester_id=req_db, role=requester_Role, urls=fields2query)
                    if policies["compliance"] == "NO_POLICY":
                        # there is no policy defined so return the result
                        jsonResponse["d"]["results"][i]={"error":"Policies restrictions"}
                    elif policies["compliance"] == "PREFS_CONFLICT":
                        # there is a conflict in the policies
			restrictedFields = []
                        for ev in policies["evaluations"]:
                            if ev["result"] == "false":
                                # find the proper field to strip the data
                                for j in range(len(jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"])):
                                    if jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"][j]["MetadatafieldregistryDetails"]["Element"] == ev["datafield"]:
			  	        restrictedFields.append(ev["datafield"])
                                        jsonResponse["d"]["results"][i]["MetadatavalueDetails"]["results"][j]["TextValue"] = "***PERMISSION DENIED***"

                    else:
                        jsonResponse["d"]["results"][i]={"error":"uknown"}
                    
	            logdata(req_db, "fieds:%s||status:%s" % (joinSTR(restrictedFields), "Denied"), "Select", userid)
                    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(list(set(restrictedFields) - set(fields2query))), "Allowed"), "Select", userid)                  
                
		return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')

            else:
                rows=jsonResponse["d"]["results"]
                for i in range(len(rows)):

                    policies = getPCresponse(action="Select", osp=req_db, userid=jsonResponse["d"]["results"][i]["Iduser"],
                                             requester_id=req_db, role=requester_Role, urls=jsonResponse["d"]["results"][i].keys())
                    if policies["compliance"] == "NO_POLICY":
                        # there is no policy defined so return the result
                        jsonResponse["d"]["results"][i]={"error":"Policies restrictions"}
                    elif policies["compliance"] == "PREFS_CONFLICT":
                        # there is a conflict in the policies
			restrictedFields = []
                        for ev in policies["evaluations"]:
                            if ev["result"] == "false":
                                restrictedFields.append(jsonResponse["d"]["results"][i][datafield])							
                                jsonResponse["d"]["results"][i]["datafield"]="***PERMISSION DENIED***"
                    else:
                        jsonResponse["d"]["results"][i]={"error":"unknown"}				
                    
	    	    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(restrictedFields), "Denied"), "Select", jsonResponse["d"]["results"][i]["Iduser"])
                    logdata(req_db, "fieds:%s||status:%s" % (joinSTR(list(set(restrictedFields) - set(fields2query))), "Allowed"), "Select", jsonResponse["d"]["results"][i]["Iduser"])
					
                return Response(json.dumps(jsonResponse), status=200, mimetype='application/json')
    else:
        # return json/xml
        try:
            testResponse = json.loads(r.text)
            return Response(r.text, status=r.status_code, mimetype='application/json')
        except:
            return Response(r.text, status=r.status_code, mimetype='application/text')


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
            return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400, mimetype='application/json')
    except:
        return Response("{'status': 400, 'message': 'Check your headers', }", status=400, mimetype='application/json')
    ST = GetST()
    headers = {'service-ticket': ST, 'Content-Type': 'application/json',
               'Accept': '*/*', 'osp-identifier': req_db, 'psp-user-identifier': psp_user_identifier}
    # have to check with PC whether this guy can insert.
    res = getPCresponse(action="Insert", osp=req_db, userid="",
                        requester_id=req_db, subject="", urls=[])
    # suppose that the response is true
    r = requests.post(__DAN_url % addr, headers=headers,
                      data=json.dumps(request.json), verify=False)
    # return Response(r.text, status=200, mimetype='application/json')
    if r.status_code == 200:
        ks = json.loads(request.data)
        logdata("RM", "insert into table %s" % addr,"fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
        return Response(r.text, status=200, mimetype='application/json')
    else:
		return Response(r.text,status=r.status_code, mimetype='application/json')


# this is an update
def handleUpdate(request, addr):
    if not request.json:
        abort(400)
    # validate the received ticket
    receivedST = request.headers["service-ticket"]
    # if not
    if not ValidateReceivedTicket(receivedST, '/operando/rm/'):
        return Response("{'status': 400, 'message': 'Service ticket (ST) is invalid', }", status=400, mimetype='application/json')

    ST = GetST()
    req_db = request.headers["osp-identifier"]
    psp_user_identifier = request.headers["psp-user-identifier"]
    headers = {'service-ticket': ST, 'Content-Type': 'application/json',
               'Accept': '*/*', 'osp-identifier': req_db, 'psp-user-identifier': 'unknown', }
    # have to check with PC whether this guy can insert.
    res = getPCresponse(action="Update", osp=req_db, userid="",
                        requester_id=req_db, subject="", urls=[])
    r = requests.put(__DAN_url % addr, headers=headers,
                     data=request.json, verify=False)
    if r.status_code == 200:
        # ks = json.loads(request.data)
        # logdata("requesterId", "update table %s" % addr,
        #"fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
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
        return Response("{'status': 400, 'message': 'Method not supported. Check the manual', }", status=400, mimetype='application/json')

if __name__ == '__main__':
    TGT = Log2CAS()
    ST = GetST()
    logdata("test", "test", "test")
    # start the service
    app.run(host='0.0.0.0', port=8102, threaded=True, debug=True)
    #app.run(port=8102, threaded=True, debug=True)
