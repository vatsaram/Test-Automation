package org.testframework.pojo;

import org.testframework.utilities.FileReaderManager;

/**
 * @author sriva
 * 
 *         This object has the list of query parameters. I have not used Project
 *         Lombok to avoid installation, else the getter and setter methods can
 *         be removed. The values which the variables will hold have been
 *         parameterised and need to be updated through the project.properties
 *         file.
 *
 */
public class QueryParameters {

	private String q;
	private String appID;

	public QueryParameters() {
		this.q = FileReaderManager.getInstance().getCityName() + "," + FileReaderManager.getInstance().getCountryCode();
		this.appID = FileReaderManager.getInstance().getAccessToken();

	}

	public String getAppID() {
		return appID;
	}

	public String getQ() {
		return q;
	}
}
