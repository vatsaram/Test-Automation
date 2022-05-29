package org.qa.GumTreeDemo.BaseAPI;

import java.util.Map;

import org.apache.log4j.Logger;
import org.qa.GumTreeDemo.Utilities.Constants;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

/**
 * @author sriva
 * 
 *         This class comprises of all the methods needed for accessing the
 *         public API. 
 *         
 *          
 */
public class BaseAPI {
	private static Logger log = Logger.getLogger(BaseAPI.class);

	private RequestSpecBuilder requestBuilder = new RequestSpecBuilder();
	private String method;
	private String url;

	public BaseAPI(String uri, String method) {
		this.url = uri;
		this.method = method;
	}

	/**
	 * @return
	 * 
	 *         This method is used for constructing payload and currently 
	 *         no content type is set.
	 * 
	 */
	public ResponseOptions<Response> getResponseFromAPI() {
		RequestSpecification request = RestAssured.given().spec(requestBuilder.build());

//		request.contentType(ContentType.ANY);

		request.log().all();
		log.info("Request specification built succesfully");
		if (this.method.equalsIgnoreCase(Constants.APIHTTPMethods.POSTMETHOD))
			return request.post(this.url);
		else if (this.method.equalsIgnoreCase(Constants.APIHTTPMethods.DELETEMETHOD))
			return request.delete(this.url);
		else if (this.method.equalsIgnoreCase(Constants.APIHTTPMethods.GETMETHOD))
			return request.get(this.url);
		return null;
	}

	/**
	 * @param queryParameters
	 * @return
	 * 
	 *         Add query parameters while sending the request
	 * 
	 */
	public ResponseOptions<Response> addQueryParameters(Map<String, Object> queryParameters) {
		requestBuilder.addQueryParams(queryParameters);
		return getResponseFromAPI();
	}

	/**
	 * @param pathParameters
	 * @return
	 * 
	 *         Add Path parameters while sending the request
	 */
	public ResponseOptions<Response> addPathParameters(Map<String, Object> pathParameters) {
		requestBuilder.addPathParams(pathParameters);
		return getResponseFromAPI();
	}

	/**
	 * @param pathParams
	 * @param body
	 * @return
	 * 
	 *         Add Path parameters along with the body while sending the request
	 */
	public ResponseOptions<Response> addPathParametersWithBody(Map<String, Object> pathParams,
			Map<String, Object> body) {
		requestBuilder.setBody(body).addPathParams(pathParams);
		return getResponseFromAPI();
	}

	/**
	 * @param body
	 * @return
	 * 
	 *         To add Body while sending the request
	 */
	public ResponseOptions<Response> addBody(Map<String, Object> body) {
		requestBuilder.setBody(body);
		return getResponseFromAPI();
	}

}
