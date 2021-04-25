package org.testframework.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sriva
 * 
 *         This object stores the important parameters for the API under which
 *         is of interest for the application. Using public for the variable to
 *         avoid getter methods for demo purposes.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseList {

	@JsonProperty("main")
	public TemperatureDetails tempDetails;

	@JsonProperty("weather")
	public List<Weather> weather;

	@JsonProperty("dt_txt")
	public String dateText;

	public String getDate() {
		return dateText.split(" ")[0];
	}
}
