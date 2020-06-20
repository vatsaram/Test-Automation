package org.testframework.utilities;

/**
 * @author sriva
 * 
 *         Test Data and any necessary data used by the framework.
 *
 */
public class Constants {

	public class APIHTTPMethods {
		public static final String PUBLIC_API = "https://gorest.co.in/public-api/users";
		public static final String GETMETHOD = "GET";
		public static final String POSTMETHOD = "POST";
		public static final String DELETEMETHOD = "DELETE";
	}
	
	public class APIValidation{
		public static final String SUCESS_RESPONSE_GET = "OK. Everything worked as expected.";
		public static final String FAILURE_RESPONSE_GET = "The requested resource does not exist";
		public static final String SUCESS_RESPONSE_POST = "A resource was successfully created in response to a POST request";
		public static final String SUCESS_GET_CODE = "200";
		public static final String FAILURE_GET_CODE = "404";
		public static final String SUCESS_POST_CODE = "201";

	}
	

	public class TestData {
		public static final String EXCEL_FILE_NAME = "TestInformation.xls";
		public static final String JSON_FILE = "postData.json";
	}
}
