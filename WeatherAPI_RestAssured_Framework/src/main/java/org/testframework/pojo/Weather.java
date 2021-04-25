package org.testframework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sriva
 * 
 *         The weather details from the response JSON. Using public for the
 *         variable to avoid getter methods for demo purposes.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	@JsonProperty("main")
	public String main;

	@JsonProperty("description")
	public String description;

	@JsonProperty("icon")
	public String icon;

	public boolean isSunnyDay() {
		return main.toLowerCase().trim().equals("clear");
	}
}
