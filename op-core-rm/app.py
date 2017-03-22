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

# load credentials
config = ConfigParser.RawConfigParser()
config.read('config.cfg')
user = config.get('Credentials', 'user')
passwd = config.get('Credentials', 'password')
__hdr = {"Content-Type": "application/json", "Accept": "*/*", }

# URLs
__DAN_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/op-pdr-dan/%s'
__aapi_st_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets/%s'
__aapi_tgt_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets'
__aapi_tckt_val_url = 'https://snf-706921.vm.okeanos.grnet.gr:8443/authentication/aapi/tickets/%s/validate?serviceId=%s'
__URL_LOGDB = 'http://server02tecnalia.westeurope.cloudapp.azure.com:8090/operando/core/ldb/log'

app = Flask(__name__)
# TODO
# contact PC and apply prefs


def Log2CAS():
    data = '{"password": "%s","username": "%s"}' % (user, passwd)
    response = requests.post(__aapi_tgt_url, data=data,
                             verify=False, headers=__hdr)
    return response.text


def joinSTR(lst):
    return ','.join(str(e) for e in lst)


def GetST():
    # get service ticket for dan
    response = requests.post(__aapi_st_url %
                             TGT, data='op-pdr/dan', verify=False)
    return response.text


def ValidateReceivedTicket(tckt, sID):
    r = requests.get(__aapi_tckt_val_url %
                     (tckt, '/operando/rm/'), verify=False)
    return r.status_code == 200
    # the rest of it not valid till we get the user mappings
    try:
        # validate the XML
        root = ET.fromstring(r.text)
        namespaces = {'cas': 'http://www.yale.edu/tp/cas'}
        elmt = root.find('*/*')
        # check whether the request has a valid ticket
        if elmt is not None:
            if elmt.tag.split("}")[1] == "user":  # and elmt.text == sID:
                return True
    except:
        return False
    return False


def logdata(requesterId, action, actiontype):
    logdata = {}
    logdata["requesterType"] = "MODULE"
    # TODO: Fix the requesterID
    logdata["requesterId"] = requesterId
    logdata["logPriority"] = "LOW"
    logdata["logDataType"] = "INFO"
    logdata["title"] = actiontype
    logdata["description"] = action
    logdata["keywords"] = ["query"]
    logdata = json.dumps(logdata)

    log_resp = requests.post(__URL_LOGDB, headers=__hdr, data=logdata)
    # test the response
    logmsg = log_resp.text
    try:
        msg = json.loads(logmsg)
        if msg["type"].lower() != "ok":
            print "error logging"
    except:
        print "error logging"


@app.route('/', defaults={'path': ''}, methods=['POST', 'GET', 'PUT'])
@app.route('/<path:path>', methods=['POST', 'GET', 'PUT'])
def catch_all(path):
# GBE added for monitoring purposes
    if path == "monitor":
        resp = jsonify({'status': 200, 'message': 'RM listening', })
        resp.status_code = 200
        return resp
# GBE End
    if path[:7] != "Results":
        resp = jsonify({'status': 401, 'message': 'Invalid address', })
        resp.status_code = 404
        return resp
    else:
        addr = path[8:]
    if request.method == 'POST':
        # this is an insert
                # validate the received ticket
        receivedST = request.headers["service-ticket"]
        if not ValidateReceivedTicket(receivedST, 'operando/rm/'):
            return Response("Service ticket (ST) is invalid", status=400, mimetype='application/json')
        ST = GetST()
        headers = {'service-ticket': ST, 'Content-Type': 'application/json',
                   'Accept': '*/*', 'osp-identifier': 'built-in', 'psp-user-identifier': 'unknown'}
        # have to check with PC whether this guy can insert.
        # ITI API pending
        # suppose that the response is true
        r = requests.post(__DAN_url % addr, headers=headers,
                          data=request.data, verify=False)
        if r.status_code == 200:
            ks = json.loads(request.data)
            logdata("requesterId", "insert into table %s" % addr,
                    "fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
            return Response(r.text, status=200, mimetype='application/json')
        else:
            returnResponse(r.text,
                           status=r.status_code, mimetype='application/json')
    elif request.method == 'GET':
        # this is a select
        # validate the received ticket
        receivedST = request.headers["service-ticket"]
        if not ValidateReceivedTicket(receivedST, 'operando/rm/'):
            return Response("Service ticket (ST) is invalid", status=400, mimetype='application/json')
        ST = GetST()
        headers = {'service-ticket': ST, 'Accept': '*/*',
                   'osp-identifier': 'built-in', 'psp-user-identifier': 'unknown', }
        redirect = "?" + request.query_string if request.query_string else ""
        r = requests.get(__DAN_url % (addr + redirect),
                         headers=headers, verify=False)
        # check whether the response is OK
        if r.status_code == 200:
            jsonResponse = json.loads(r.text)
            if "results" in jsonResponse['d'].keys():  # many ids
                data2respond = jsonResponse['d']['results']
                for d in data2respond:
                    ks = d.keys()
                    ks.remove("__metadata")
                    ks.remove("MetadatavalueDetails")
                    ks[ks.index("Iduser")] = d['Iduser']
                    # request ks from PC
                    # pending changes from ITI
                    # suppose that the response is true

                logdata("requesterId", "select from table %s" %
                        path, "fieds:%s||status:%s" % (joinSTR(ks), "Granted"))
                return Response(r.text, status=200, mimetype='application/json')
            else:
                data2respond = jsonResponse['d']
                ks = data2respond.keys()
                ks.remove("__metadata")
                ks.remove("MetadatavalueDetails")
                ks[ks.index("Iduser")] = data2respond['Iduser']
                # request ks from PC
                # pending changes from ITI
                # suppose that the response is true
                logdata("requesterId", "select from table %s" %
                        path, "fieds:%s||status:%s" % (joinSTR(ks), "Granted"))
                return Response(r.text, status=200, mimetype='application/json')

        else:
            returnResponse(r.text,
                           status=r.status_code, mimetype='application/json')
    elif request.method == 'PUT':
        # this is an update
        # validate the received ticket
        receivedST = request.headers["service-ticket"]
        if not ValidateReceivedTicket(receivedST, 'operando/rm/'):
            return Response("Service ticket (ST) is invalid", status=400, mimetype='application/json')
        ST = GetST()
        headers = {'service-ticket': ST, 'Content-Type': 'application/json',
                   'Accept': '*/*', 'osp-identifier': 'built-in', 'psp-user-identifier': 'unknown', }
        # have to check with PC whether this guy can insert.
        # ITI API pending
        # suppose that the response is true
        r = requests.put(__DAN_url % addr, headers=headers,
                         data=request.data, verify=False)
        if r.status_code == 200:
            ks = json.loads(request.data)
            logdata("requesterId", "update table %s" % addr,
                    "fieds:%s||status:%s" % (joinSTR(ks.keys()), "Granted"))
            return Response("updated", status=200, mimetype='application/json')
        else:
            returnResponse(r.text,
                           status=r.status_code, mimetype='application/json')
        return Response("updated", status=200, mimetype='application/json')
    else:
        return "Method not supported"


if __name__ == '__main__':
    TGT = Log2CAS()
    ST = GetST()
    # start the service
    app.run(host='0.0.0.0',port=8080, threaded=True, debug=False)
    print ST
