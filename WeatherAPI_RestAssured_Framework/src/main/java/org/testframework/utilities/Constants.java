package org.testframework.utilities;

/**
 * @author sriva
 * 
 *         Test Data and any necessary data used by the framework.
 *
 */
public class Constants {

	public class APIHTTPMethods {
		public static final String PUBLIC_API = "https://api.openweathermap.org/data/2.5/forecast";
		public static final String GETMETHOD = "GET";
		public static final String POSTMETHOD = "POST";
		public static final String DELETEMETHOD = "DELETE";
	}
	
	public class APIValidation{
		public static final String SUCCESS_GET_CODE = "200";
		public static final String FAILURE_GET_CODE = "401";
	}
	
}
