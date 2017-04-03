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
import requests.exceptions

# load credentials
config = ConfigParser.RawConfigParser()
config.read('config.cfg')
user = config.get('Credentials', 'user')
passwd = config.get('Credentials', 'password')
__hdr = {"Content-Type": "application/json", "Accept": "*/*", }

# URLs
# config.get('URLs', 'aapi_tgt_url')
__aapi_tgt_url = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets"
__aapi_st_url = 'http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets/%s'
# config.get('URLs', 'URL_LOGDB')
__URL_LOGDB = "http://ldb.integration.operando.esilab.org:8090/operando/core/ldb/"
# config.get('URLs', 'DAN_url')
__DAN_url = "http://integration.operando.esilab.org:8111/operando/pdr/dan/%s"
__URL_PC = "http://integration.operando.esilab.org:8095/operando/core/pc"
__aapi_tckt_val_url =    "http://integration.operando.esilab.org:8135/operando/interfaces/aapi/aapi/tickets/%s/validate?serviceId=%s"
# config.get('URLs', 'aapi_tckt_val_url')


def Log2CAS(user, passwd):
    data = '{"password": "%s","username": "%s"}' % (passwd, user)
    response = requests.post(__aapi_tgt_url, data=data,
                             verify=False, headers=__hdr)
    return response.text


def joinSTR(lst):
    return ','.join(str(e) for e in lst)


def GetST(tgt):
    response = requests.post(__aapi_st_url %
                             TGT, data='/operando/rm/', verify=False)
    return response.text

TGT = Log2CAS("gatekeeper", "gatekeeper")
print "TGT returned by Log2CAS: %s" %TGT
ST = GetST(TGT)
print "ST returned by GetST: %s" %ST

headers = {'service-ticket': ST, "osp-identifier": "YellowPages",
           "psp-user-identifier": "Wendy"}
print "header to be used: %s" %headers

# done
r = requests.get('http://127.0.0.1:8102/Users(4)?$format=json', headers=headers)
# r = requests.get('http://127.0.0.1:8102/Users(2)/MetadatavalueDetails?$format=json&$expand=MetadatafieldregistryDetails', headers=headers)

# r = requests.get('http://127.0.0.1:8102/Users?$format=json', headers=headers)
# r = requests.get('http://127.0.0.1:8102/Users?$format=json', headers=headers)
print r.text

try:
    r.raise_for_status()  # Raises a HTTPError if the status is 4xx, 5xxx
except (requests.exceptions.ConnectionError, requests.exceptions.Timeout):
    print "Down"
except requests.exceptions.HTTPError:
    print "4xx, 5xx"
else:
    print "All good!"  # Proceed to do stuff with `r` 
