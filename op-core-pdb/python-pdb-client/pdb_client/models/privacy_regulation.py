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


class PrivacyRegulation(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self, reg_id=None, legislation_sector=None, reason=None, private_information_type=None, action=None, required_consent=None):
        """
        PrivacyRegulation - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.pdb_types = {
            'reg_id': 'str',
            'legislation_sector': 'str',
            'reason': 'str',
            'private_information_type': 'str',
            'action': 'str',
            'required_consent': 'str'
        }

        self.attribute_map = {
            'reg_id': 'reg_id',
            'legislation_sector': 'legislation_sector',
            'reason': 'reason',
            'private_information_type': 'private_information_type',
            'action': 'action',
            'required_consent': 'required_consent'
        }

        self._reg_id = reg_id
        self._legislation_sector = legislation_sector
        self._reason = reason
        self._private_information_type = private_information_type
        self._action = action
        self._required_consent = required_consent

    @property
    def reg_id(self):
        """
        Gets the reg_id of this PrivacyRegulation.

        :return: The reg_id of this PrivacyRegulation.
        :rtype: str
        """
        return self._reg_id

    @reg_id.setter
    def reg_id(self, reg_id):
        """
        Sets the reg_id of this PrivacyRegulation.

        :param reg_id: The reg_id of this PrivacyRegulation.
        :type: str
        """

        self._reg_id = reg_id

    @property
    def legislation_sector(self):
        """
        Gets the legislation_sector of this PrivacyRegulation.
        The identifier of the set of legislation rules this rule belongs to e.g. the UK data protection act. 

        :return: The legislation_sector of this PrivacyRegulation.
        :rtype: str
        """
        return self._legislation_sector

    @legislation_sector.setter
    def legislation_sector(self, legislation_sector):
        """
        Sets the legislation_sector of this PrivacyRegulation.
        The identifier of the set of legislation rules this rule belongs to e.g. the UK data protection act. 

        :param legislation_sector: The legislation_sector of this PrivacyRegulation.
        :type: str
        """
        if legislation_sector is None:
            raise ValueError("Invalid value for `legislation_sector`, must not be `None`")

        self._legislation_sector = legislation_sector

    @property
    def reason(self):
        """
        Gets the reason of this PrivacyRegulation.
        The purpose for performing an action on data e.g. medical care, marketing, admin

        :return: The reason of this PrivacyRegulation.
        :rtype: str
        """
        return self._reason

    @reason.setter
    def reason(self, reason):
        """
        Sets the reason of this PrivacyRegulation.
        The purpose for performing an action on data e.g. medical care, marketing, admin

        :param reason: The reason of this PrivacyRegulation.
        :type: str
        """

        self._reason = reason

    @property
    def private_information_type(self):
        """
        Gets the private_information_type of this PrivacyRegulation.
        The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities? 

        :return: The private_information_type of this PrivacyRegulation.
        :rtype: str
        """
        return self._private_information_type

    @private_information_type.setter
    def private_information_type(self, private_information_type):
        """
        Sets the private_information_type of this PrivacyRegulation.
        The type of private information; e.g. is it information that identifies the user (e.g. id number)? is it location information about the user? Is it about their activities? 

        :param private_information_type: The private_information_type of this PrivacyRegulation.
        :type: str
        """

        self._private_information_type = private_information_type

    @property
    def action(self):
        """
        Gets the action of this PrivacyRegulation.
        The action being carried out on the data

        :return: The action of this PrivacyRegulation.
        :rtype: str
        """
        return self._action

    @action.setter
    def action(self, action):
        """
        Sets the action of this PrivacyRegulation.
        The action being carried out on the data

        :param action: The action of this PrivacyRegulation.
        :type: str
        """

        self._action = action

    @property
    def required_consent(self):
        """
        Gets the required_consent of this PrivacyRegulation.
        The type of consent that must be followed

        :return: The required_consent of this PrivacyRegulation.
        :rtype: str
        """
        return self._required_consent

    @required_consent.setter
    def required_consent(self, required_consent):
        """
        Sets the required_consent of this PrivacyRegulation.
        The type of consent that must be followed

        :param required_consent: The required_consent of this PrivacyRegulation.
        :type: str
        """

        self._required_consent = required_consent

    def to_dict(self):
        """
        Returns the model properties as a dict
        """
        result = {}

        for attr, _ in iteritems(self.pdb_types):
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
