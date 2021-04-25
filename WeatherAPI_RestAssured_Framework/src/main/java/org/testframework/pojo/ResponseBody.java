package org.testframework.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sriva
 * 
 *         The class which has the root contents for the JSON payload. Using
 *         Ignore Unknown for the JSON objects/ nodes which are not of interest.
 *         Using public for the variable to avoid getter methods for demo
 *         purposes.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBody {

	@JsonProperty("country")
	public String country;

	@JsonProperty("list")
	public List<ResponseList> responseList;

}
