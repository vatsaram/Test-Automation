package org.testframework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sriva
 * 
 *         The temperature details from the response JSON. Using public for the
 *         variable to avoid getter methods for demo purposes.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureDetails {

	@JsonProperty("temp")
	public double temp;

	@JsonProperty("feels_like")
	public double feelsLike;

}
