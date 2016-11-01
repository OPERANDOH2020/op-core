/////////////////////////////////////////////////////////////////////////
//
// © University of Southampton IT Innovation Centre, 2016
//
// Copyright in this library belongs to the University of Southampton
// University Road, Highfield, Southampton, UK, SO17 1BJ
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
// Created By : Paul Grace
// Created for Project : OPERANDO (http://www.operando.eu)
//
/////////////////////////////////////////////////////////////////////////
//
//  License : GNU Lesser General Public License, version 3
//
/////////////////////////////////////////////////////////////////////////

package eu.operando;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author pjg
 */
public class XSDParser {

    public static String getElementDataType(String resourcePath) throws InvalidPreferenceException{
        try {
            String Category="Medical Information";
            URL url = new URL(resourcePath);
            String path = url.getPath();
            int loc = path.indexOf(")/");
            String ca = path.substring(loc+2);
            int loc2 = ca.indexOf('/');
            Category = ca.substring(0, loc2);
            return Category;
        } catch (MalformedURLException ex) {
            throw new InvalidPreferenceException(ex.getMessage());
        }
    }



}
