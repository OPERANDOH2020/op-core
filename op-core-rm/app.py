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
app = Flask(__name__)
TGT=""

@app.route('/Results', methods=['GET'])
def results_get(ticket=None, serviceID=None, paramValues=None):
    # read the request parameters
    tckt = request.args.get('ticket')
    sID = request.args.get('ServiceID')
    params = request.args.get('ParamValues')
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
                # get a ticket for DAN
                tckt4DAN = requests.post(__URL_TGT+TGT, data=URL_TGT__2GET).text
                params=json.loads(params)
                params["st"]=tckt4DAN
                params=json.dumps(params)
                # execute the query to DAN                
                req = urllib2.Request(__URL, headers=__hdr, data=params)
                return Response(urllib2.urlopen(req).read(), status=200, mimetype='application/json')
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
    print TGT  
    app.run(host='0.0.0.0',port=8080, threaded=True, debug=False)
