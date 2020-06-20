package org.testframework.stepDefn;

import static org.junit.Assert.assertTrue;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testframework.api.BaseAPI;
import org.testframework.utilities.Constants;
import org.testframework.utilities.ExcelReader;
import org.testframework.utilities.FileReaderManager;
import org.testframework.utilities.StringOperations;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class StepDefinition {

	private BaseAPI apiRunner;
	private ResponseOptions<Response> response;

	@Before
	public void setTestData() {
		FileReaderManager.getInstance().readProperty();
	}

	@Given("connection request is prepared for API under test")
	public void prepareGETRequest() {
		apiRunner = new BaseAPI("", Constants.APIHTTPMethods.GETMETHOD);
	}

	@Then("validate that API responds with success and provides the list of users")
	public void getResponseFromAPI() {
		response = apiRunner.getResponseFromAPI();
		validateAssertions(Constants.APIHTTPMethods.GETMETHOD);
	}


	@Then("validate that API responds with success and provides the list of users based on query parameters")
	public void getResponseFromAPIWithQueryParam() {
		validateAssertions(Constants.APIHTTPMethods.GETMETHOD);
	}

	@Given("connection request is prepared for API under test with query paramaters")
	public void prepareGETRequestWithQuery(Map<String, String> queryParamTestData) {
		apiRunner = new BaseAPI("", Constants.APIHTTPMethods.GETMETHOD);
		response = apiRunner.addQueryParameters(queryParamTestData);

	}

	@Given("connection request is prepared for API by querying with UserID")
	public void prepareGETRequestWithResource(Map<String, String> resourceIDs) {
		for (String uris : resourceIDs.keySet()) {
			String uri = "/" + uris.trim();
			apiRunner = new BaseAPI(uri, Constants.APIHTTPMethods.GETMETHOD);
		}
	}

	@Given("Payload is prepared using jsonfile {string} sheet {string} and scenarios as below")
	public void preparePOSTRequest(String jsonFileName, String sheetName, Map<String, String> scenarioNames) {
		for (String scenarioName : scenarioNames.keySet()) {
			Map<String, Object> payLoad = getTestJSON(jsonFileName.trim(), sheetName.trim(), scenarioName.trim());
			apiRunner = new BaseAPI("", Constants.APIHTTPMethods.POSTMETHOD);
			response = apiRunner.addBody(payLoad);
		}
	}

	@Then("validate that API responds with success for a post request")
	public void validatePostRequest() {
		assertTrue(getMessageResponse(response).contains(Constants.APIValidation.SUCESS_RESPONSE_POST));
		assertTrue(getStatusResponse(response).contains(Constants.APIValidation.SUCESS_POST_CODE));
	}
	
	@Then("validate that API responds with failure when resource does not exist") 
	public void validateFailGetResponse(){
		assertTrue(getMessageResponse(response).contains(Constants.APIValidation.FAILURE_RESPONSE_GET));
		assertTrue(getStatusResponse(response).contains(Constants.APIValidation.FAILURE_GET_CODE));
	}

	private String getMessageResponse(ResponseOptions<Response> response) {
		response.getBody().print();
		return response.getBody().jsonPath().get("_meta.message").toString();
	}
	
	private String getStatusResponse(ResponseOptions<Response> response) {
		return response.getBody().jsonPath().get("_meta.code").toString();
	}

	private static Map<String, Object> getTestJSON(String jsonFileName, String sheetName, String scenarioName) {
		String jsonFileForTest = FileReaderManager.getInstance().getTestDataPath() + Constants.TestData.JSON_FILE;
		ExcelReader reader = new ExcelReader();
		LinkedHashMap<String, String> testData = reader.readExcelData(sheetName, scenarioName);
		return StringOperations.replaceVariablesWithTestPayload(jsonFileForTest, testData);
	}
	
	/**
	 * @param httpMethod
	 * 
	 * validate assertions
	 */
	private void validateAssertions(String httpMethod) {
		if(httpMethod.equalsIgnoreCase(Constants.APIHTTPMethods.GETMETHOD)) {
			assertTrue(getMessageResponse(response).equalsIgnoreCase(Constants.APIValidation.SUCESS_RESPONSE_GET));
			assertTrue(getStatusResponse(response).equalsIgnoreCase(Constants.APIValidation.SUCESS_GET_CODE));
		}
		
		else if(httpMethod.equalsIgnoreCase(Constants.APIHTTPMethods.POSTMETHOD)) {
			assertTrue(getMessageResponse(response).equalsIgnoreCase(Constants.APIValidation.SUCESS_RESPONSE_POST));
			assertTrue(getStatusResponse(response).equalsIgnoreCase(Constants.APIValidation.SUCESS_POST_CODE));
		}

	}

}
