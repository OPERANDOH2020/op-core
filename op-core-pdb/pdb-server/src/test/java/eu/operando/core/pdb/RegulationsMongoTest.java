///////////////////////////////////////////////////////////////////////////
////
//// © University of Southampton IT Innovation Centre, 2016
////
//// Copyright in this software belongs to University of Southampton
//// IT Innovation Centre of Gamma House, Enterprise Road,
//// Chilworth Science Park, Southampton, SO16 7NS, UK.
////
//// This software may not be used, sold, licensed, transferred, copied
//// or reproduced in whole or in part in any manner or form or in or
//// on any media by any person other than in accordance with the terms
//// of the Licence Agreement supplied with the software, or otherwise
//// without the prior written consent of the copyright owners.
////
//// This software is distributed WITHOUT ANY WARRANTY, without even the
//// implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
//// PURPOSE, except where stated in the Licence Agreement supplied with
//// the software.
////
////      Created By :            Panos Melas
////      Created Date :          2016-04-28
////      Created for Project :   OPERANDO
////
///////////////////////////////////////////////////////////////////////////
//
//
//package eu.operando.core.pdb;
//
//import io.swagger.model.PrivacyRegulation;
//import io.swagger.model.PrivacyRegulationInput;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Ignore;
//
///**
// *
// * @author sysman
// */
//public class RegulationsMongoTest {
//    PrivacyRegulationInput myTestPR;
//    String regId;
//
//    public RegulationsMongoTest() {
//        myTestPR = new PrivacyRegulationInput();
//        myTestPR.setAction("store my pictures");
//        myTestPR.setLegislationSector("UK protection act");
//        myTestPR.setPrivateInformationSource("pm new photos");
//        myTestPR.setPrivateInformationType(PrivacyRegulationInput.PrivateInformationTypeEnum.IDENTIFICATION);
//        myTestPR.setRequiredConsent(PrivacyRegulationInput.RequiredConsentEnum.IN);
//
//        regId = null;
//    }
//
//    @BeforeClass
//    public static void setUpClass() {
//    }
//
//    @AfterClass
//    public static void tearDownClass() {
//    }
//
//    @Before
//    public void setUp() {
//    }
//
//    @After
//    public void tearDown() {
//    }
//
//    /**
//     * Test of storeRegulation method, of class RegulationsMongo.
//     */
//    @Ignore @Test
//    public void testStoreRegulation() {
//        System.out.println("storeRegulation");
//        PrivacyRegulationInput reg = myTestPR;
//        RegulationsMongo instance = new RegulationsMongo();
//        String expResult = "";
//        String result = instance.storeRegulation(reg);
//        //assertEquals(expResult, result);
//        assertNotNull(result);
//    }
//
//    /**
//     * Test of deleteRegulationById method, of class RegulationsMongo.
//     */
//    @Ignore @Test
//    public void testDeleteRegulationById() {
//        System.out.println("deleteRegulationById");
//        String regId = "";
//        RegulationsMongo instance = new RegulationsMongo();
//        boolean expResult = false;
//        boolean result = instance.deleteRegulationById(regId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRegulationByFilter method, of class RegulationsMongo.
//     */
//    @Ignore @Test
//    public void testGetRegulationByFilter() {
//        System.out.println("getRegulationByFilter");
//        String filter = "";
//        RegulationsMongo instance = new RegulationsMongo();
//        String expResult = "";
//        String result = instance.getRegulationByFilter(filter);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getRegulationById method, of class RegulationsMongo.
//     */
//    @Ignore @Test
//    public void testGetRegulationById() {
//        System.out.println("getRegulationById");
//        String regId = "";
//        RegulationsMongo instance = new RegulationsMongo();
//        String expResult = "";
//        String result = instance.getRegulationById(regId);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateRegulation method, of class RegulationsMongo.
//     */
//    @Ignore @Test
//    public void testUpdateRegulation() {
//        System.out.println("updateRegulation");
//        String regId = "";
//        PrivacyRegulationInput reg = null;
//        RegulationsMongo instance = new RegulationsMongo();
//        boolean expResult = false;
//        boolean result = instance.updateRegulation(regId, reg);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//
//
//}
