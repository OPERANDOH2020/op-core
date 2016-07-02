#!/usr/bin/env python
import urllib2
import json
from flask import jsonify
from flask import Flask, Response, request
from xml.etree import ElementTree as ET
import ssl
import requests
import json
import ConfigParser

config = ConfigParser.RawConfigParser()

__hdr = {"Content-Type": "application/json", "Accept": "*/*", }
__URL = "http://snf-706921.vm.okeanos.grnet.gr:8080/dan/operando/pdr/access_node/getResults"
__URL_TGT = "http://snf-706921.vm.okeanos.grnet.gr:8080/authentication/aapi/tickets/"
URL_TGT__2GET = 'https://snf-706921.vm.okeanos.grnet.gr:8443/dan'
__URL_PC='http://server02tecnalia.westeurope.cloudapp.azure.com:8095/operando/core/pc/osp_policy_evaluator?user_id=pjgrace&osp_id=Hospital+OSP'
__URL_LOGDB='http://server02tecnalia.westeurope.cloudapp.azure.com:8090/operando/core/ldb/log'
app = Flask(__name__)
TGT=""



# SQL_PatientsOfDoctor=SELECT p.FirstName, p.LastName, p.Weight FROM Patient p WHERE p.IDPrimaryCaregiver = ? 
# SQL_PatientWithID=SELECT p.Id, p.FirstName, p.LastName, p.Weight FROM Patient p WHERE p.ID = ?
@app.route('/Results', methods=['POST'])
def results_get():
    # read the request parameters
    data = request.get_json(force=True)
    try:
        tckt = data['ticket']
        sID = data['ServiceID']
        params = data['ParamValues']
        roleID= data['RoleID']
    except (KeyError, TypeError, ValueError):
        raise JsonError(description='Invalid value.')
    print 10*"-"+tckt+"||"+sID+"||"+params

    logdata={}    
    logdata["requesterType"] = "MODULE"
    #TODO: Fix the requesterID
    logdata["requesterId"] =sID
    logdata["logPriority"] ="LOW"
    logdata[ "logDataType"] ="INFO"
    logdata["title"] ="Request to RM"
    logdata[ "description"]= "access to query with params: %s" % params
    logdata[ "keywords"]=  ["query"] 
    logdata=json.dumps(logdata)

    log_resp=requests.post(__URL_LOGDB, headers=__hdr, data=logdata)
    #test the response
    logmsg=log_resp.text
    try:
        msg=json.loads(logmsg)
        if msg["type"].lower()!="ok":
            print "error logging"
    except:
        print "error logging"


    # validate the received ticket
    URL_validate = "https://snf-706921.vm.okeanos.grnet.gr:8443/casv415/serviceValidate?ticket=%s&service=https://snf-706921.vm.okeanos.grnet.gr:8443/dan" % tckt

    # since this is a self signed ticket we have to disable certificate
    ctx = ssl.create_default_context()
    ctx.check_hostname = False
    ctx.verify_mode = ssl.CERT_NONE
    # verification
    val = urllib2.urlopen(URL_validate, context=ctx).read()
    try:
        # validate the XML
        root = ET.fromstring(val)
        namespaces = {'cas': 'http://www.yale.edu/tp/cas'}
        elmt = root.find('*/*')
        # check whether the request has a valid ticket
        if elmt is not None:
            if elmt.tag.split("}")[1] == "user" and elmt.text == sID:

                #determine if the query has to be executed
                dt={}

                dt["subject"]=roleID
                #for both queries these properties are the same
                dt["requester_id"]="Hospital OSP"
                dt["requested_url"]="Weight"
                dt["action"]="Access"
                dt["attributes"]= []
                dt=[dt]
                data=json.dumps(dt)
                req = requests.post(__URL_PC, headers=__hdr, data=data)
                resp= req.text
                print resp
                resp=json.loads(resp)
                if resp["status"].lower()=="true":
                    # get a ticket for DAN
                    print "in 1*****"
                    tckt4DAN = requests.post(__URL_TGT+TGT, data=URL_TGT__2GET).text
                    params=json.loads(params)
                    params["st"]=tckt4DAN
                    params=json.dumps(params)
                    print "in 2*****"
                    # execute the query to DAN                
                    req = urllib2.Request(__URL, headers=__hdr, data=params)
                    print "3*****"
                    return Response(urllib2.urlopen(req).read(), status=200, mimetype='application/json')
                else:
                    resp = jsonify({'status': 401, 'message': resp["compliance"], })
                    resp.status_code = 404
                    return resp
            else:
                resp = jsonify({'status': 401, 'message': 'Invalid ticket', })
                resp.status_code = 404
                return resp
        else:
            resp = jsonify({'status': 401, 'message': 'Invalid ticket', })
            resp.status_code = 404
            return resp
    except:
        resp = jsonify({'status': 400, 'message': 'Malformed XML', })
        resp.status_code = 404

        return resp

if __name__ == '__main__':
    #log on to server
    config.read('config.cfg')
    user = config.get('Credentials', 'user')
    passwd = config.get('Credentials', 'password')
    data={"password": passwd,"username": user}
    data = json.dumps(data)
    req = urllib2.Request('http://snf-706921.vm.okeanos.grnet.gr:8080/authentication/aapi/tickets', headers=__hdr, data=data)
    TGT = urllib2.urlopen(req).read()  
    #start the service
    app.run(host='0.0.0.0',port=8080, threaded=True, debug=True)
