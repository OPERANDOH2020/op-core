/*
 * Copyright (c) 2016 {TECNALIA}.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the The MIT License (MIT).
 * which accompanies this distribution, and is available at
 * http://opensource.org/licenses/MIT
 *
 * Contributors:
 *    {Gorka Mikel Echevarría} {TECNALIA}
 * Initially developed in the context of OPERANDO EU project www.operando.eu
 */

package eu.operando.server.logdb.test;

import eu.operando.server.logdb.OperandoLog;

public class Test {

	/** Test method used to call OperandoLog.logToDB
	 * 
	 */
	@org.junit.Test
	public void test() {
		OperandoLog.logToDB("1", "1", "193.145.252.132", "00-16-41-34-2C-A6", "www.google.com", "requesterId",
				"https://eu.operando/getOffers", "{\"username\":\"xyz\",\"password\":\"xyz\"}", true,
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/48.0.2564.97 Safari/537.36",
				true, "android", "F2ADD4D612E23C9B18B0166BBDE1DB839BFB8A376ED01E32FADB03A0D1B720C7",
				"F2ADD4D612E23C9B18B0166BBDE1DB839BFB8A376ED01E32FADB03A0D1B720C7");
	}

}
