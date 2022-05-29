package org.qa.GumTreeDemo.StepDefn;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.qa.GumTreeDemo.BaseAPI.BaseAPI;
import org.qa.GumTreeDemo.Utilities.Constants;
import org.qa.GumTreeDemo.Utilities.FileOperations;
import org.qa.GumTreeDemo.Utilities.ResponseBodyPOJO;
import org.qa.GumTreeDemo.Utilities.ResponseHeaders;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

/**
 * @author sriva
 *
 */
public class APIStepDefn {
	private static Logger log = Logger.getLogger(APIStepDefn.class);

	private String endPoint;
	private ResponseOptions<Response> response;

	@DataTableType
	public ResponseBodyPOJO transformResponseDataTable(Map<String, String> row) {
		
		return new ResponseBodyPOJO(row.get("Status"), row.get("Error"), row.get("Path"));
	}

	@DataTableType
	public ResponseHeaders transformHeaderInfo(Map<String, String> row) {

		return new ResponseHeaders(row.get("Server"), row.get("Content-Type"), row.get("Transfer-Encoding"));

	}

	/**
	 * @param searchString
	 * @param fileName
	 * 
	 * Get the end point URI from the json file. Can be used if no query parameters are needed.
	 */
	@Given("I construct a payload with end point {string} from file {string}")
	public void getEndPoint(String searchString, String fileName) {
		endPoint = FileOperations.getInstance().getFileAsJSON(fileName).getString(searchString);
		log.info("End point URI to be accessed " + endPoint);
	}

	/**
	 * @param queryFileName
	 * 
	 * Add query parameters based on request file and get the response from rest end point
	 */
	@Given("query parameters defined in file {string} and hit the end point")
	public void query_parameters_defined_as_per_scenario_from_file_with_query_defined_in_file(String queryFileName) {
		JSONObject queryParameters = FileOperations.getInstance().getFileAsJSON(queryFileName);
		log.info("Query parameters for construction : " + queryParameters.toString());
		BaseAPI api = new BaseAPI(endPoint, Constants.APIHTTPMethods.GETMETHOD);
//		Construct the query parameters and call the rest end point
		response = api.addQueryParameters(queryParameters.toMap());
		log.info("Generated pay load successfully and performed the GET operation");
	}

	@Then("validate the response type as {string}")
	public void validateResponseType(String contentType) {
		log.info("Response type recieved : " + response.contentType());
		Assert.assertTrue(response.contentType().equals(contentType));
		log.info("Response type matches with expected : " + response.contentType());
	}

	@Then("response code as {string}")
	public void validateResponseCode(String responseCode) {
		log.info("Response code recieved : " + response.getStatusCode());
		Integer codeValue = Integer.parseInt(responseCode);
		Assert.assertTrue(codeValue.equals(response.getStatusCode()));
		log.info("Response code matches with expected : " + response.getStatusCode());
	}

	/**
	 * @param dataTable
	 * 
	 *                  Validating the response through maps and pre java 8 iteration
	 * 
	 */
	@Then("validate the headers as per datatable")
	public void validateHeaders(DataTable dataTable) {
		List<Map<String, String>> expectedValues = dataTable.asMaps();
		Headers headerDetails = response.getHeaders();
		log.info("Response headers recieved : \n" + headerDetails.toString());
		log.info("Expected Response headers : \n" + expectedValues.toString());
		boolean comparator = true;
		for (Map<String, String> map : expectedValues) {
			for (String key : map.keySet()) {
				if (headerDetails.hasHeaderWithName(key.trim())) {
					if (!headerDetails.getValue(key).equalsIgnoreCase(map.get(key).trim())) {
						log.error("Expected value does not match with actual. Expected key : " + key
								+ " Expected value : " + map.get(key) + "\nObserved values : "
								+ headerDetails.getValue(key));
						comparator = false;
						break;
					} else {
						log.info("Expected value matches with actual. Expected key : " + key + " Expected value : "
								+ map.get(key));
					}
				}
			}
		}

		Assert.assertTrue(comparator);
	}

	/**
	 * @param expectedValues
	 * 
	 *                       Transforming the datatable to POJO class and validating
	 *                       the response
	 */
	@Then("verify content as per below table")
	public void validateResponseBody(List<ResponseBodyPOJO> expectedValues) {
//		This currently works only for data table with single row and with JSON as response body. 
//		If this needs to be extended, then this would need Scenario Outline instead of scenario.
		for (ResponseBodyPOJO eachValue : expectedValues) {
			log.info("Expected Response body  : \n" + expectedValues.toString());
			ResponseBodyPOJO responseBodyRecieved = FileOperations.getInstance().mapToClass(ResponseBodyPOJO.class,
					response.getBody().asString());
			log.info("Response body recieved : \n" + responseBodyRecieved.toString());
			Assert.assertTrue(responseBodyRecieved.equals(eachValue));
			log.info("Expected Response body matches with response");
		}
	}

};