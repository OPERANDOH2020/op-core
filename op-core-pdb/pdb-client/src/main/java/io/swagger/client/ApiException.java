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
//      Created By :            Panos Melas
//      Created Date :          2016-04-28
//      Created for Project :   OPERANDO
//
/////////////////////////////////////////////////////////////////////////

package io.swagger.client;

import java.util.Map;
import java.util.List;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-04-19T15:50:46.998Z")
public class ApiException extends Exception {
  private int code = 0;
  private Map<String, List<String>> responseHeaders = null;
  private String responseBody = null;

  public ApiException() {}

  public ApiException(Throwable throwable) {
    super(throwable);
  }

  public ApiException(String message) {
    super(message);
  }

  public ApiException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders, String responseBody) {
    super(message, throwable);
    this.code = code;
    this.responseHeaders = responseHeaders;
    this.responseBody = responseBody;
  }

  public ApiException(String message, int code, Map<String, List<String>> responseHeaders, String responseBody) {
    this(message, (Throwable) null, code, responseHeaders, responseBody);
  }

  public ApiException(String message, Throwable throwable, int code, Map<String, List<String>> responseHeaders) {
    this(message, throwable, code, responseHeaders, null);
  }

  public ApiException(int code, Map<String, List<String>> responseHeaders, String responseBody) {
    this((String) null, (Throwable) null, code, responseHeaders, responseBody);
  }

  public ApiException(int code, String message) {
    super(message);
    this.code = code;
  }

  public ApiException(int code, String message, Map<String, List<String>> responseHeaders, String responseBody) {
    this(code, message);
    this.responseHeaders = responseHeaders;
    this.responseBody = responseBody;
  }

  public int getCode() {
    return code;
  }

  /**
   * Get the HTTP response headers.
   */
  public Map<String, List<String>> getResponseHeaders() {
    return responseHeaders;
  }

  /**
   * Get the HTTP response body.
   */
  public String getResponseBody() {
    return responseBody;
  }
}
