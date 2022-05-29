package org.qa.GumTreeDemo.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;


public class FileOperations {
	private static FileOperations instance = null;
	private static Logger log = Logger.getLogger(FileOperations.class);

	public static FileOperations getInstance() {

		if (instance == null)
			instance = new FileOperations();
		return instance;
	}

	/**
	 * @param fileName
	 * @return
	 */
	public JSONObject getFileAsJSON(String fileName) {
		String jsonString = null;
		try {
			jsonString = FileUtils.readFileToString(new File(getFilePath(fileName)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new JSONObject(jsonString);
	}

	/**
	 * @param fileName
	 * @return
	 */
	public String getFilePath(String fileName) {
		String path = "/TestData/" + fileName;
		String filePath = FileOperations.class.getResource(path).getPath();
		return filePath;
	}

	/**
	 * @param <T>
	 * @param classType
	 * @param filePath
	 * @return
	 * 
	 *         The method maps the file available at the file location to the class
	 *         type requested. Can be used if input file is of type JSON
	 */
	public <T> T mapFileToClass(Class<T> classType, String filePath) {
		try {
			return new ObjectMapper().readValue(new File(filePath), classType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	/**
	 * @param <T>
	 * @param classType
	 * @param jsonString
	 * @return
	 * 	       The method maps the JSON String to the class type requested
	 *         
	 */
	public <T> T mapToClass(Class<T> classType, String jsonString) {
		try {
			return new ObjectMapper().readValue(jsonString, classType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}


}
