/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.operando;

/**
 *
 * @author pjg
 */
public class RegulationEvaluate {

    private static final String PC_URL = "http://integration.operando.esilab.org:8095/operando/core/pc";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestHelperMethods tMethods = new TestHelperMethods();
        System.out.println("Output from PC: " + tMethods.postPCRegulation("58a6df0fdc0e82000140d2a3"));
    }
}
