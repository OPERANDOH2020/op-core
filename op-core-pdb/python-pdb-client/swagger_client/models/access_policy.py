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


class AccessPolicy(object):
    """
    NOTE: This class is auto generated by the swagger code generator program.
    Do not edit the class manually.
    """
    def __init__(self, subject=None, permission=None, action=None, resource=None, attributes=None):
        """
        AccessPolicy - a model defined in Swagger

        :param dict swaggerTypes: The key is attribute name
                                  and the value is attribute type.
        :param dict attributeMap: The key is attribute name
                                  and the value is json key in definition.
        """
        self.swagger_types = {
            'subject': 'str',
            'permission': 'bool',
            'action': 'str',
            'resource': 'str',
            'attributes': 'list[PolicyAttribute]'
        }

        self.attribute_map = {
            'subject': 'subject',
            'permission': 'permission',
            'action': 'action',
            'resource': 'resource',
            'attributes': 'attributes'
        }

        self._subject = subject
        self._permission = permission
        self._action = action
        self._resource = resource
        self._attributes = attributes

    @property
    def subject(self):
        """
        Gets the subject of this AccessPolicy.
        A description of the subject who the policies grants/doesn't grant to carry out. 

        :return: The subject of this AccessPolicy.
        :rtype: str
        """
        return self._subject

    @subject.setter
    def subject(self, subject):
        """
        Sets the subject of this AccessPolicy.
        A description of the subject who the policies grants/doesn't grant to carry out. 

        :param subject: The subject of this AccessPolicy.
        :type: str
        """

        self._subject = subject

    @property
    def permission(self):
        """
        Gets the permission of this AccessPolicy.
        Grant or deny the subject access to the resource via the operation defined in this policy 

        :return: The permission of this AccessPolicy.
        :rtype: bool
        """
        return self._permission

    @permission.setter
    def permission(self, permission):
        """
        Sets the permission of this AccessPolicy.
        Grant or deny the subject access to the resource via the operation defined in this policy 

        :param permission: The permission of this AccessPolicy.
        :type: bool
        """

        self._permission = permission

    @property
    def action(self):
        """
        Gets the action of this AccessPolicy.
        The action being carried out on the private date e.g. accessing, disclosing to a third party.  

        :return: The action of this AccessPolicy.
        :rtype: str
        """
        return self._action

    @action.setter
    def action(self, action):
        """
        Sets the action of this AccessPolicy.
        The action being carried out on the private date e.g. accessing, disclosing to a third party.  

        :param action: The action of this AccessPolicy.
        :type: str
        """
        allowed_values = ["Collect", "Access", "Use", "Disclose", "Archive"]
        if action not in allowed_values:
            raise ValueError(
                "Invalid value for `action` ({0}), must be one of {1}"
                .format(action, allowed_values)
            )

        self._action = action

    @property
    def resource(self):
        """
        Gets the resource of this AccessPolicy.
        The identifier of the resource that the policy concerns (e.g. URL) 

        :return: The resource of this AccessPolicy.
        :rtype: str
        """
        return self._resource

    @resource.setter
    def resource(self, resource):
        """
        Sets the resource of this AccessPolicy.
        The identifier of the resource that the policy concerns (e.g. URL) 

        :param resource: The resource of this AccessPolicy.
        :type: str
        """

        self._resource = resource

    @property
    def attributes(self):
        """
        Gets the attributes of this AccessPolicy.
        The set of context attributes attached to the policy (e.g. subject role, subject purpose) 

        :return: The attributes of this AccessPolicy.
        :rtype: list[PolicyAttribute]
        """
        return self._attributes

    @attributes.setter
    def attributes(self, attributes):
        """
        Sets the attributes of this AccessPolicy.
        The set of context attributes attached to the policy (e.g. subject role, subject purpose) 

        :param attributes: The attributes of this AccessPolicy.
        :type: list[PolicyAttribute]
        """

        self._attributes = attributes

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
