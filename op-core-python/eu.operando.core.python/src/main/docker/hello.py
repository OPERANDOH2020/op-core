import urllib2
import json
from flask import jsonify
from flask import Flask, Response, request
from xml.etree import ElementTree as ET
import ssl
import requests
import json
import ConfigParser

app = Flask(__name__)

@app.route("/")
def hello():
    return "Hello World!"

if __name__ == "__main__":
#    app.run()
#	from costas app.py
    app.run(host='0.0.0.0',port=8080, threaded=True, debug=True)