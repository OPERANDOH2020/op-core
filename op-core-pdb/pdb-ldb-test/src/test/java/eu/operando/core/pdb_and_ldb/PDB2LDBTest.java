/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.operando.core.pdb_and_ldb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sysman
 */
public class PDB2LDBTest {
    
    public PDB2LDBTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getServiceProviders method, of class PDB2LDB.
     */
//    @Test(expected = eu.operando.core.pdb.client.ApiException.class)
//    public void testGetServiceProviders() throws eu.operando.core.pdb.client.ApiException {
//        System.out.println("getServiceProviders");
//        PDB2LDB instance = new PDB2LDB();
//        instance.getServiceProviders();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
    /**
     * Test of getLogs method, of class PDB2LDB.
     */
    @Test
    public void testGetLogs() {
        System.out.println("getLogs");
        PDB2LDB instance = new PDB2LDB();
        instance.getLogs();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
