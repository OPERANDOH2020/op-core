# coding: utf-8

"""
    Policy DB

    The Policy Database that stores three types of documents in dedicated collections.   1) User Privacy Policy. Each OPERANDO user has one UPP document describing their privacy policies for each of the OSP services they are subscribed to. The UPP contains the current B2C privacy settings for a subscribed to OSP. The UPP contains the users privacy preferences. The UPP contains the G2C access policies for the OSP with justification for access.   2) The legislation policies. The regulations entered into OPERANDO using the OPERANDO regulation API.   3) The OSP descriptions and data requests. For each OSP its privacy policy, its access control rules, and the data it requests (as a workflow). For B2C, the set of privacy settings is stored. 

    OpenAPI spec version: 1.0.0
    Contact: support@operando.eu
    Generated by: https://github.com/swagger-api/swagger-codegen.git
"""


from pprint import pformat
from six import iteritems
import re


class UserPrivacyPolicy(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self, user_id=None, user_preferences=None, subscribed_osp_policies=None, subscribed_osp_settings=None):
        """
        UserPrivacyPolicy - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'user_id': 'str',
            'user_preferences': 'list[UserPreference]',
            'subscribed_osp_policies': 'list[OSPConsents]',
            'subscribed_osp_settings': 'list[OSPSettings]'
        }

        self.attribute_map = {
            'user_id': 'user_id',
            'user_preferences': 'user_preferences',
            'subscribed_osp_policies': 'subscribed_osp_policies',
            'subscribed_osp_settings': 'subscribed_osp_settings'
        }

        self._user_id = user_id
        self._user_preferences = user_preferences
        self._subscribed_osp_policies = subscribed_osp_policies
        self._subscribed_osp_settings = subscribed_osp_settings

    @property
    def user_id(self):
        """
        Gets the user_id of this UserPrivacyPolicy.
        The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.

        :return: The user_id of this UserPrivacyPolicy.
        :rtype: str
        """
        return self._user_id

    @user_id.setter
    def user_id(self, user_id):
        """
        Sets the user_id of this UserPrivacyPolicy.
        The unique operando id of the user this policy is about. Each OPERANDO user has one and only one UPP.

        :param user_id: The user_id of this UserPrivacyPolicy.
        :type: str
        """

        self._user_id = user_id

    @property
    def user_preferences(self):
        """
        Gets the user_preferences of this UserPrivacyPolicy.
        User stated preferences (questionnaire answers)

        :return: The user_preferences of this UserPrivacyPolicy.
        :rtype: list[UserPreference]
        """
        return self._user_preferences

    @user_preferences.setter
    def user_preferences(self, user_preferences):
        """
        Sets the user_preferences of this UserPrivacyPolicy.
        User stated preferences (questionnaire answers)

        :param user_preferences: The user_preferences of this UserPrivacyPolicy.
        :type: list[UserPreference]
        """

        self._user_preferences = user_preferences

    @property
    def subscribed_osp_policies(self):
        """
        Gets the subscribed_osp_policies of this UserPrivacyPolicy.
        The user policies for each OSP they subscribe to.

        :return: The subscribed_osp_policies of this UserPrivacyPolicy.
        :rtype: list[OSPConsents]
        """
        return self._subscribed_osp_policies

    @subscribed_osp_policies.setter
    def subscribed_osp_policies(self, subscribed_osp_policies):
        """
        Sets the subscribed_osp_policies of this UserPrivacyPolicy.
        The user policies for each OSP they subscribe to.

        :param subscribed_osp_policies: The subscribed_osp_policies of this UserPrivacyPolicy.
        :type: list[OSPConsents]
        """

        self._subscribed_osp_policies = subscribed_osp_policies

    @property
    def subscribed_osp_settings(self):
        """
        Gets the subscribed_osp_settings of this UserPrivacyPolicy.
        The user settings for each of their services

        :return: The subscribed_osp_settings of this UserPrivacyPolicy.
        :rtype: list[OSPSettings]
        """
        return self._subscribed_osp_settings

    @subscribed_osp_settings.setter
    def subscribed_osp_settings(self, subscribed_osp_settings):
        """
        Sets the subscribed_osp_settings of this UserPrivacyPolicy.
        The user settings for each of their services

        :param subscribed_osp_settings: The subscribed_osp_settings of this UserPrivacyPolicy.
        :type: list[OSPSettings]
        """

        self._subscribed_osp_settings = subscribed_osp_settings

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.swagger_types):
            value = getattr(self, attr)
            if isinstance(value, list):
                result[attr] = list(map(
                    lambda x: x.to_dict() if hasattr(x, "to_dict") else x,
                    value
                ))
            elif hasattr(value, "to_dict"):
                result[attr] = value.to_dict()
            elif isinstance(value, dict):
                result[attr] = dict(map(
                    lambda item: (item[0], item[1].to_dict())
                    if hasattr(item[1], "to_dict") else item,
                    value.items()
                ))
            else:
                result[attr] = value

        return result

    def to_str(self):
        """
        Returns the string representation of the model
        """
        return pformat(self.to_dict())

    def __repr__(self):
        """
        For `print` and `pprint`
        """
        return self.to_str()

    def __eq__(self, other):
        """
        Returns true if both objects are equal
        """
        return self.__dict__ == other.__dict__

    def __ne__(self, other):
        """
        Returns true if both objects are not equal
        """
        return not self == other
