# coding: utf-8

"""
    Policy DB

    The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 

    OpenAPI spec version: 1.0.0
    Contact: support@operando.eu
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from __future__ import absolute_import

import os
import sys
import unittest

import pdb_client
from pdb_client.rest import ApiException
from pdb_client.apis.legislation_api import LegislationApi

import aapi_client
from aapi_client.rest import ApiException as AApiException
from aapi_client.models import UserCredential

class TestLegislationApi(unittest.TestCase):
    """ LegislationApi unit test stubs """
    BASE_PATH = "http://10.136.24.87:8080"
    BASE_PATH = "http://integration.operando.esilab.org:8096/operando/core"

    AAPI_PATH = "http://integration.operando.esilab.org:8135/operando/interfaces/aapi"
    _osp_id = "pdb/regulations/.*"
    _osp_policy = None

    def setUp(self):
        # try to get sertice ticket
        aapi_cli = aapi_client.ApiClient(host=self.AAPI_PATH)
        aapi = aapi_client.apis.DefaultApi(aapi_cli)
        user_credential =  UserCredential("panos2", "operando")
        self.tgt = ""
        try:
            self.tgt = aapi.aapi_tickets_post(user_credential)
            print("setUp got ticket: ", self.tgt)
        except AApiException as e:
            print("Exception when trying to get a ticket %s\n" % e)

        self.st = ""
        if self.tgt:
            try:
                self.st = aapi.aapi_tickets_tgt_post(self.tgt, self._osp_id)
                print("setUp got service ticket:", self.st)
            except AApiException as e:
                print("Exception when tryint to get OSP service ticket %s\n", e)

        self.api = pdb_client.apis.legislation_api.LegislationApi()
        base_path = "".join([self.BASE_PATH, "/pdb"])
        api_client = pdb_client.ApiClient(host=base_path)
        if self.st:
            api_client.set_default_header("Service-Ticket", self.st)
        self.api = pdb_client.apis.legislation_api.LegislationApi(api_client)

    def tearDown(self):
        pass

    # @unittest.skip("demo skipping")
    def test_regulations_all(self):
        print("test regulation")
        my_reg = pdb_client.models.PrivacyRegulation()
        my_reg.reg_id = "my reg id"
        my_reg.legislation_sector = "my new photos"
        my_reg.reason = "no reason"
        my_reg.private_information_type = "Geographic"
        my_reg.action = "delete my pictures"
        my_reg.required_consent = "opt-in"

        api_response = self.api.regulations_post(my_reg)
        my_reg_id = api_response.reg_id
        self.assertNotEqual(api_response.reg_id, None)
        self.assertEqual(api_response.legislation_sector, my_reg.legislation_sector)
 
        print("Test regulations get with filter")
        reg_all_filter = "{\"legislation_sector\":\"\"}"
        api_response = self.api.regulations_get(reg_all_filter)
        # print("get regulations:", api_response)
        reg_list_length = len(api_response)
        self.assertNotEqual(api_response, None)
        try:
           assert len(api_response) > 0
           regulation = api_response[0]
        except Exception as e:
           print("Test legislation failed to get any stored regulation")
           raise e

        print("test regulations post")
        ls = "TEST TEST TEST"
        regulation.legislation_sector = ls
        api_response = self.api.regulations_post(regulation)
        # print("POST regulation response:", api_response.reg_id)
        self.assertNotEqual(api_response.reg_id, None)
        self.assertEqual(api_response.legislation_sector, ls)
        reg_id = api_response.reg_id

        print("GET regulation test")
        api_response = self.api.regulations_reg_id_get(reg_id)
        # print("GET regulation:", api_response)
        self.assertEqual(reg_id, api_response.reg_id)

        print("PUT regulation test")
        ls = "one two three"
        regulation.legislation_sector = ls
        api_response = self.api.regulations_reg_id_put(reg_id, regulation)
        print("PUT response:", api_response)
        api_response = self.api.regulations_reg_id_get(reg_id)
        self.assertEqual(api_response.legislation_sector, ls)

        print("DELETE regulation test")
        api_response = self.api.regulations_reg_id_delete(reg_id)

        api_response = self.api.regulations_get(reg_all_filter)
        self.assertEqual(reg_list_length, len(api_response))
       
        api_response = self.api.regulations_reg_id_delete(my_reg_id)

        pass

    @unittest.skip("demo skipping")
    def test_regulations_get(self):
        """
        Test case for regulations_get

        Perform a search query across the collection of regulation.
        """
        pass

    @unittest.skip("demo skipping")
    def test_regulations_post(self):
        """
        Test case for regulations_post

        Create a new legislation entry in the database.
        """
        pass

    @unittest.skip("demo skipping")
    def test_regulations_reg_id_delete(self):
        """
        Test case for regulations_reg_id_delete

        Remove the PrivacyRegulation entry in the database.
        """
        pass

    @unittest.skip("demo skipping")
    def test_regulations_reg_id_get(self):
        """
        Test case for regulations_reg_id_get

        Read a given legislation with its ID.
        """
        pass

    @unittest.skip("demo skipping")
    def test_regulations_reg_id_put(self):
        """
        Test case for regulations_reg_id_put

        Update PrivacyRegulation entry in the database.
        """
        pass


if __name__ == '__main__':
    unittest.main()
