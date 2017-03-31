/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this software belongs to University of Southampton
// IT Innovation Centre of Gamma House, Enterprise Road,
// Chilworth Science Park, Southampton, SO16 7NS, UK.
//
// This software may not be used, sold, licensed, transferred, copied
// or reproduced in whole or in part in any manner or form or in or
// on any media by any person other than in accordance with the terms
// of the Licence Agreement supplied with the software, or otherwise
// without the prior written consent of the copyright owners.
//
// This software is distributed WITHOUT ANY WARRANTY, without even the
// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
// PURPOSE, except where stated in the Licence Agreement supplied with
// the software.
//
//      Created By :            Robbie Anderson
//      Created Date :          2016-09-02
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

/*
 * @author ra16 <ra16@it-innovation.soton.ac.uk
 */

package eu.operando.core.cpcu.servlet.springwrappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

/**
 * The Class OSPPrivacyPolicy.
 */
@Generated("org.jsonschema2pojo")
public class OSPPrivacyPolicy {

    /** The osp policy id. */
    private String ospPolicyId;
    
    /** The policy text. */
    private String policyText;
    
    /** The policy url. */
    private String policyUrl;
    
    /** The workflow. */
    private List<Workflow> workflow = new ArrayList<Workflow>();
    
    /** The policies. */
    private List<Policy> policies = new ArrayList<Policy>();
    
    /** The additional properties. */
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * Gets the osp policy id.
     *
     * @return     The ospPolicyId
     */
    public String getOspPolicyId() {
        return ospPolicyId;
    }

    /**
     * Sets the osp policy id.
     *
     * @param ospPolicyId     The osp_policy_id
     */
    public void setOspPolicyId(String ospPolicyId) {
        this.ospPolicyId = ospPolicyId;
    }

    /**
     * Gets the policy text.
     *
     * @return     The policyText
     */
    public String getPolicyText() {
        return policyText;
    }

    /**
     * Sets the policy text.
     *
     * @param policyText     The policy_text
     */
    public void setPolicyText(String policyText) {
        this.policyText = policyText;
    }

    /**
     * Gets the policy url.
     *
     * @return     The policyUrl
     */
    public String getPolicyUrl() {
        return policyUrl;
    }

    /**
     * Sets the policy url.
     *
     * @param policyUrl     The policy_url
     */
    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl;
    }

    /**
     * Gets the workflow.
     *
     * @return     The workflow
     */
    public List<Workflow> getWorkflow() {
        return workflow;
    }

    /**
     * Sets the workflow.
     *
     * @param workflow     The workflow
     */
    public void setWorkflow(List<Workflow> workflow) {
        this.workflow = workflow;
    }

    /**
     * Gets the policies.
     *
     * @return     The policies
     */
    public List<Policy> getPolicies() {
        return policies;
    }

    /**
     * Sets the policies.
     *
     * @param policies     The policies
     */
    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }

    /**
     * Gets the additional properties.
     *
     * @return the additional properties
     */
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Sets the additional property.
     *
     * @param name the name
     * @param value the value
     */
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
