package org.testframework.runner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testframework.api.BaseAPI;
import org.testframework.pojo.QueryParameters;
import org.testframework.pojo.ResponseBody;
import org.testframework.pojo.ResponseList;
import org.testframework.utilities.Constants;
import org.testframework.utilities.Conversions;
import org.testframework.utilities.FileReaderManager;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class RunProgram {
	private static Logger log = Logger.getLogger(RunProgram.class);

	private BaseAPI constructApi;
	private ResponseBody responseBody;

	@BeforeTest
	public void setUp() {
		FileReaderManager.getInstance().readProperty();
		constructApi = new BaseAPI(Constants.APIHTTPMethods.GETMETHOD);
	}

	/**
	 * The method is used to construct the API payload and parse the response to
	 * POJO for further usage
	 */
	@Test(priority = 1)
	public void veriyfySuccessfulResponse() {

		@SuppressWarnings("unchecked")
		Map<String, Object> queryParameters = (Map<String, Object>) new ObjectMapper()
				.convertValue(new QueryParameters(), Map.class);
		ResponseOptions<Response> responseData = constructApi.addQueryParameters(queryParameters);
		Assert.assertEquals(200, responseData.getStatusCode());
		log.info("<-- Connected with API and recieved a success response --> ");
		System.out.println(responseData.getBody().asString());
		try {
			responseBody = Conversions.deseraliseJSON(ResponseBody.class, responseData.getBody().asString());
		} catch (JsonMappingException mappingException) {
			log.error("Mapping exception encountered : " + mappingException.getMessage());
			mappingException.printStackTrace();
		} catch (JsonProcessingException processingException) {
			log.error("Unable to process the JSON : " + processingException.getMessage());
			processingException.printStackTrace();
		}
	}

	/**
	 * This test method will check the number of days where the temperature is
	 * greater than 20C
	 * 
	 */
	@Test(priority = 2)
	public void verifyDaysWithTemp() {
		long numberOfDays = responseBody.responseList.stream()
				.filter(response -> Conversions.getTempInCelsius(response.tempDetails.temp) > 20.0)
				.map(x -> x.getDate()).distinct().count();
		log.info("<-- Number of day(s) with temperature > 20 Degree celsius is " + numberOfDays + " -->");
	}

	/**
	 * This method will check the number of days where the weather is mentioned as
	 * clear. Note as part of this method, it counts the unique days where the
	 * weather has clear mentioned in report.
	 * 
	 */
	@Test(priority = 3)
	public void verifySunnyDays() {
		List<ResponseList> sunnyDays = new ArrayList<>();
		for (ResponseList eachResponse : responseBody.responseList) {
			if (eachResponse.weather.stream().filter(x -> x.isSunnyDay()).count() > 0)
				sunnyDays.add(eachResponse);
		}
		long numberOfDays = sunnyDays.stream().map(x -> x.getDate()).distinct().count();
		log.info("<-- Number of days with Sunny weather : " + numberOfDays + " --> ");
	}

}
