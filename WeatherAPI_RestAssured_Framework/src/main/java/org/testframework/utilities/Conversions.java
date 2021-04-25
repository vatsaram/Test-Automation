package org.testframework.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Conversions {

	/**
	 * @param <T>
	 * @param pojoClass
	 * @param jsonBody
	 * @return
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * 
	 *                                 Method to deserialise the response JSON. The
	 *                                 calling method will need to handle the
	 *                                 exceptions.
	 */
	public static <T> T deseraliseJSON(Class<T> pojoClass, String jsonBody)
			throws JsonMappingException, JsonProcessingException {
		return new ObjectMapper().readValue(jsonBody, pojoClass);
	}

	/**
	 * @param value
	 * @return
	 * 
	 *         Get temperature in Celsius since the response variable is in Kelvin
	 */
	public static Double getTempInCelsius(double value) {
		return (value - 273.15);
	}
}
