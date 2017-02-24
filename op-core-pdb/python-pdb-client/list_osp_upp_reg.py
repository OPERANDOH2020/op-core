from __future__ import print_function
import time
import swagger_client
from swagger_client.rest import ApiException
from pprint import pprint

base_path = "http://integration.operando.esilab.org:8096/operando/core/pdb"
# base_path = "http://10.136.24.87:8080/pdb"

api_client = swagger_client.ApiClient(host=base_path)
api_client.set_default_header("Service-Ticket",
        "ST-4-jRVgo6V31QumyQ0ro0AH-casdotoperandodoteu")

def pdb_osp_get_filter():
    # create and instance of the API class
    api_instance = swagger_client.GETApi(api_client)
    osp_all_filter = "{\"policy_text\":\"\"}"

    try:
        api_response = api_instance.o_sp_get(osp_all_filter)
        # print list of OSP providers
        pprint(len(api_response))
        return api_response
    except ApiException as e:
        print("Exception when calling GetApi->o_sp_get: %s\n" % e)


def pdb_reg_get_filter():
    # create and instance of the API class
    api_instance = swagger_client.GETApi(api_client)
    reg_all_filter = "{\"legislation_sector\":\"\"}"

    try:
        api_response = api_instance.regulations_get(reg_all_filter)
        pprint(len(api_response))
        return api_response
    except ApiException as e:
        print("Exception when calling GetApi->regulations_get: %s\n" % e)



def pdb_upp_get_filter():
    # create and instance of the API class
    api_instance = swagger_client.GETApi(api_client)
    upp_all_filter = "{\"user_id\":\"\"}"

    try:
        api_response = api_instance.user_privacy_policy_get(upp_all_filter)
        pprint(len(api_response))
        return api_response
    except ApiException as e:
        print("Exception when calling GetApi->user_privacy_policy_get: %s\n" % e)


osp_list = pdb_osp_get_filter()
for osp_item in osp_list:
    print("OSP policy id:", osp_item.osp_policy_id, osp_item.policy_text)

reg_list = pdb_reg_get_filter()
for reg_item in reg_list:
    print("Regulation  id:", reg_item.reg_id, reg_item.legislation_sector)

upp_list = pdb_upp_get_filter()
for upp_item in upp_list:
    print("UPP id:", upp_item.user_id)
