# coding: utf-8

"""
    Policy DB

    The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 

    OpenAPI spec version: 1.0.0
    Contact: support@operando.eu
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from __future__ import absolute_import

import sys
import os
import re

# python 2 and python 3 compatibility library
from six import iteritems

from ..configuration import Configuration
from ..api_client import ApiClient


class DELETEApi(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    Ref: https://github.com/swagger-api/swagger-codegen
    """

    def __init__(self, api_client=None):
        config = Configuration()
        if api_client:
            self.api_client = api_client
        else:
            if not config.api_client:
                config.api_client = ApiClient()
            self.api_client = config.api_client

    def o_sp_osp_id_delete(self, osp_id, **kwargs):
        """
        Remove the OSPRequest entry in the database.
        Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.o_sp_osp_id_delete(osp_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str osp_id: The identifier number of an OSP (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.o_sp_osp_id_delete_with_http_info(osp_id, **kwargs)
        else:
            (data) = self.o_sp_osp_id_delete_with_http_info(osp_id, **kwargs)
            return data

    def o_sp_osp_id_delete_with_http_info(self, osp_id, **kwargs):
        """
        Remove the OSPRequest entry in the database.
        Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.o_sp_osp_id_delete_with_http_info(osp_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str osp_id: The identifier number of an OSP (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['osp_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method o_sp_osp_id_delete" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'osp_id' is set
        if ('osp_id' not in params) or (params['osp_id'] is None):
            raise ValueError("Missing the required parameter `osp_id` when calling `o_sp_osp_id_delete`")


        collection_formats = {}

        resource_path = '/OSP/{osp-id}/'.replace('{format}', 'json')
        path_params = {}
        if 'osp_id' in params:
            path_params['osp-id'] = params['osp_id']

        query_params = {}

        header_params = {}

        form_params = []
        local_var_files = {}

        body_params = None
        # Authentication setting
        auth_settings = []

        return self.api_client.call_api(resource_path, 'DELETE',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type=None,
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)

    def o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete(self, osp_id, reason_id, **kwargs):
        """
        Remove the AccessReason entry in the list.
        Remove an identified value. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete(osp_id, reason_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str osp_id: The identifier number of an OSP (required)
        :param str reason_id: The identifier of a statement in a policy, is only unique to the policy. (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete_with_http_info(osp_id, reason_id, **kwargs)
        else:
            (data) = self.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete_with_http_info(osp_id, reason_id, **kwargs)
            return data

    def o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete_with_http_info(self, osp_id, reason_id, **kwargs):
        """
        Remove the AccessReason entry in the list.
        Remove an identified value. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete_with_http_info(osp_id, reason_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str osp_id: The identifier number of an OSP (required)
        :param str reason_id: The identifier of a statement in a policy, is only unique to the policy. (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['osp_id', 'reason_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'osp_id' is set
        if ('osp_id' not in params) or (params['osp_id'] is None):
            raise ValueError("Missing the required parameter `osp_id` when calling `o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete`")
        # verify the required parameter 'reason_id' is set
        if ('reason_id' not in params) or (params['reason_id'] is None):
            raise ValueError("Missing the required parameter `reason_id` when calling `o_sp_osp_id_privacy_policy_access_reasons_reason_id_delete`")


        collection_formats = {}

        resource_path = '/OSP/{osp-id}/privacy-policy/access-reasons/{reason-id}'.replace('{format}', 'json')
        path_params = {}
        if 'osp_id' in params:
            path_params['osp-id'] = params['osp_id']
        if 'reason_id' in params:
            path_params['reason-id'] = params['reason_id']

        query_params = {}

        header_params = {}

        form_params = []
        local_var_files = {}

        body_params = None
        # Authentication setting
        auth_settings = []

        return self.api_client.call_api(resource_path, 'DELETE',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type=None,
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)

    def regulations_reg_id_delete(self, reg_id, **kwargs):
        """
        Remove the PrivacyRegulation entry in the database.
        Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.regulations_reg_id_delete(reg_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str reg_id: The identifier number of a regulation (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.regulations_reg_id_delete_with_http_info(reg_id, **kwargs)
        else:
            (data) = self.regulations_reg_id_delete_with_http_info(reg_id, **kwargs)
            return data

    def regulations_reg_id_delete_with_http_info(self, reg_id, **kwargs):
        """
        Remove the PrivacyRegulation entry in the database.
        Called when by the policy computation component when the regulator api requests that the regulation be deleted. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.regulations_reg_id_delete_with_http_info(reg_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str reg_id: The identifier number of a regulation (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['reg_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method regulations_reg_id_delete" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'reg_id' is set
        if ('reg_id' not in params) or (params['reg_id'] is None):
            raise ValueError("Missing the required parameter `reg_id` when calling `regulations_reg_id_delete`")


        collection_formats = {}

        resource_path = '/regulations/{reg-id}/'.replace('{format}', 'json')
        path_params = {}
        if 'reg_id' in params:
            path_params['reg-id'] = params['reg_id']

        query_params = {}

        header_params = {}

        form_params = []
        local_var_files = {}

        body_params = None
        # Authentication setting
        auth_settings = []

        return self.api_client.call_api(resource_path, 'DELETE',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type=None,
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)

    def user_privacy_policy_user_id_delete(self, user_id, **kwargs):
        """
        Remove the UPP entry in the database for the user.
        Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.user_privacy_policy_user_id_delete(user_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str user_id: The user identifier number (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """
        kwargs['_return_http_data_only'] = True
        if kwargs.get('callback'):
            return self.user_privacy_policy_user_id_delete_with_http_info(user_id, **kwargs)
        else:
            (data) = self.user_privacy_policy_user_id_delete_with_http_info(user_id, **kwargs)
            return data

    def user_privacy_policy_user_id_delete_with_http_info(self, user_id, **kwargs):
        """
        Remove the UPP entry in the database for the user.
        Called when a user leaves operando. Their privacy preferences and policies are deleted from the database. 
        This method makes a synchronous HTTP request by default. To make an
        asynchronous HTTP request, please define a `callback` function
        to be invoked when receiving the response.
        >>> def callback_function(response):
        >>>     pprint(response)
        >>>
        >>> thread = api.user_privacy_policy_user_id_delete_with_http_info(user_id, callback=callback_function)

        :param callback function: The callback function
            for asynchronous request. (optional)
        :param str user_id: The user identifier number (required)
        :return: None
                 If the method is called asynchronously,
                 returns the request thread.
        """

        all_params = ['user_id']
        all_params.append('callback')
        all_params.append('_return_http_data_only')
        all_params.append('_preload_content')
        all_params.append('_request_timeout')

        params = locals()
        for key, val in iteritems(params['kwargs']):
            if key not in all_params:
                raise TypeError(
                    "Got an unexpected keyword argument '%s'"
                    " to method user_privacy_policy_user_id_delete" % key
                )
            params[key] = val
        del params['kwargs']
        # verify the required parameter 'user_id' is set
        if ('user_id' not in params) or (params['user_id'] is None):
            raise ValueError("Missing the required parameter `user_id` when calling `user_privacy_policy_user_id_delete`")


        collection_formats = {}

        resource_path = '/user_privacy_policy/{user-id}/'.replace('{format}', 'json')
        path_params = {}
        if 'user_id' in params:
            path_params['user-id'] = params['user_id']

        query_params = {}

        header_params = {}

        form_params = []
        local_var_files = {}

        body_params = None
        # Authentication setting
        auth_settings = []

        return self.api_client.call_api(resource_path, 'DELETE',
                                        path_params,
                                        query_params,
                                        header_params,
                                        body=body_params,
                                        post_params=form_params,
                                        files=local_var_files,
                                        response_type=None,
                                        auth_settings=auth_settings,
                                        callback=params.get('callback'),
                                        _return_http_data_only=params.get('_return_http_data_only'),
                                        _preload_content=params.get('_preload_content', True),
                                        _request_timeout=params.get('_request_timeout'),
                                        collection_formats=collection_formats)
