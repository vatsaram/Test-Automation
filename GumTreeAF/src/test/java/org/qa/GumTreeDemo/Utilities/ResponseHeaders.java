package org.qa.GumTreeDemo.Utilities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseHeaders {
	
	@JsonProperty("Server")
	private String server;
	
	@JsonProperty("Content-Type")
	private String contenttype;
	
	@JsonProperty("Transfer-Encoding")
	private String transferEncoding;
	
	@Override public boolean equals(Object obj)
	{

		if (this == obj)
			return true;

		if (obj == null
			|| this.getClass() != obj.getClass())
			return false;

		ResponseHeaders p1 = (ResponseHeaders)obj; 
		// checking if the two
		// objects share all the same values
		return this.server.equals(p1.getServer())
			&& this.contenttype.equals(p1.getContenttype())
			&& this.transferEncoding.equals(p1.getTransferEncoding());
	}

}
