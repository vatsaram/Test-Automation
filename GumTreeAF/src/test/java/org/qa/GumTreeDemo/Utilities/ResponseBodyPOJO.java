package org.qa.GumTreeDemo.Utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sriva
 * 
 *         The class which has the root contents for the JSON payload. Using
 *         Ignore Unknown for the JSON objects/ nodes which are not of interest.
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseBodyPOJO {

	@JsonProperty("status")
	private String status;

	@JsonProperty("error")
	private String error;

	@JsonProperty("path")
	private String path;
	
	@Override public boolean equals(Object obj)
	{

		if (this == obj)
			return true;

		if (obj == null
			|| this.getClass() != obj.getClass())
			return false;

		ResponseBodyPOJO p1 = (ResponseBodyPOJO)obj; 

		return this.status.equals(p1.getStatus())
			&& this.error.equals(p1.getError())
			&& this.path.equals(p1.getPath());
	}

}
