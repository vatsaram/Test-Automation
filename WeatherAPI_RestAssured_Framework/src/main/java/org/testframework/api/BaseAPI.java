package org.testframework.api;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testframework.utilities.Constants;
import org.testframework.utilities.FileReaderManager;

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
 *         public API. This is currently written for Bearer authentication
 *         method. However this can be used for Basic Auth as well if the API
 *         utilises Basic Auth by passing the authentication as header
 *         parameters.
 *
 */
public class BaseAPI {
	private static Logger log = Logger.getLogger(BaseAPI.class);

	private RequestSpecBuilder requestBuilder;
	private String method;
	private String url;

	public BaseAPI(String method) {
		requestBuilder = new RequestSpecBuilder();
		this.url = Constants.APIHTTPMethods.PUBLIC_API;
		this.method = method;
		requestBuilder.addHeader("Authorization", "Bearer " + FileReaderManager.getInstance().getAccessToken());
	}

	/**
	 * @return
	 * 
	 *         The default method which can be used to run HTTP GET method. The
	 *         request type is currently hardcoded for JSON but can be extended by
	 *         using switch or if statement to extend to XML or Text based on API
	 *         messaging needs
	 * 
	 */
	public ResponseOptions<Response> getResponseFromAPI() {
		RequestSpecification request = RestAssured.given().spec(requestBuilder.build());

		// Setting the content type to JSON. This can be parameterised if need be
		request.contentType(ContentType.JSON);

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
	public ResponseOptions<Response> addQueryParameters(Map<String, ?> queryParameters) {
		requestBuilder.addQueryParams(queryParameters);
		return getResponseFromAPI();
	}

	/**
	 * @param pathParameters
	 * @return
	 * 
	 *         Add Path parameters while sending the request
	 */
	public ResponseOptions<Response> addPathParameters(Map<String, ?> pathParameters) {
		requestBuilder.addPathParams(pathParameters);
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
