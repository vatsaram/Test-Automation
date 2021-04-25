package org.testframework.utilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author sriva
 *
 */
public class FileReaderManager {

	private static Logger log = Logger.getLogger(FileReaderManager.class);

	public static Properties commonProp;
	private static String propertyFilePath = "project.properties";

	private FileReaderManager() {
	}

	private static class FileManager {
		private static FileReaderManager fileReader = new FileReaderManager();

	}

	public static FileReaderManager getInstance() {

		return FileManager.fileReader;
	}

	/**
	 * Reads and loads the property file at the start of the program. For subsequent
	 * checks, skips reading the property by checking for the boolean flag
	 * 
	 */
	public void readProperty() {

		try {
			commonProp = new Properties();
			java.io.FileReader reader = new java.io.FileReader(propertyFilePath);
			commonProp.load(reader);
			reader.close();
			log.info("Property file set");
		} catch (FileNotFoundException errorInReadingProperties) {
			log.error("Error in reading property file");
			log.error(errorInReadingProperties.getMessage());;
		} catch (IOException ioError) {
			log.error("Error in reading property file");
			log.error(ioError.getMessage());
		}
	}

	/**
	 * @return
	 * 
	 * The access token for accessing the public API
	 * 
	 */
	public String getAccessToken() {
		return commonProp.getProperty("accessToken").trim();

	}
	
	/**
	 * @return
	 * 
	 * Returns the value of the city Name
	 * 
	 */
	public String getCityName() {
		return commonProp.getProperty("cityName").trim();
	}
	
	/**
	 * @return
	 * 
	 * Returns the country code
	 * 
	 */
	public String getCountryCode() {
		return commonProp.getProperty("countryCode").toUpperCase().trim();
	}

}
