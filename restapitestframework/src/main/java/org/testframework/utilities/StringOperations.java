package org.testframework.utilities;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.json.JSONObject;

public class StringOperations {
	private static Logger log = Logger.getLogger(StringOperations.class);


	/**
	 * @param filelocation
	 * @param variablesToReplace
	 * @return
	 * 
	 *         Replaces the variables in the test JSON with the actual test values.
	 *         The method works on the assumption that parameter which needs to be
	 *         replaced exists as the key in HashMap which is being passed. If the
	 *         key is not found, the variable in the test JSON is not replaced.
	 *         
	 */
	public static Map<String, Object> replaceVariablesWithTestPayload(String filelocation,
			HashMap<String, String> variablesToReplace) {
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.init();

		try {
			Template fileTemplate = velocityEngine.getTemplate(filelocation);

			VelocityContext context = new VelocityContext();
			variablesToReplace.forEach((key, value) -> {
				context.put(key, value);
			});

			StringWriter writer = new StringWriter();
			fileTemplate.merge(context, writer);
			return convertFileToMap(writer.toString());
		} catch (ResourceNotFoundException resourceNotFound) {
			resourceNotFound.printStackTrace();
			log.error(resourceNotFound.getMessage());
			
		} catch (ParseErrorException parseException) {
			parseException.printStackTrace();
			log.error(parseException.getMessage());
			
		} catch (MethodInvocationException invocationException) {
			invocationException.printStackTrace();
			log.error(invocationException.getMessage());
			
		}
		return null;
	}

	/**
	 * @param fileContent
	 * @return
	 * 
	 *         Returns the string as Map to serve as the body for API
	 * 
	 */
	private static Map<String, Object> convertFileToMap(String fileContent) {
		JSONObject testData = new JSONObject(fileContent);
		if (testData.has("email"))
			testData.remove("email");

		testData.put("email", getRandomText() + "@testing.api");
		log.info("Payload prepared and variables succesfully replaced");
		return testData.toMap();
	}

	/**
	 * @return
	 * 
	 *         Method created as API uses Email to be an unique key and will fail if
	 *         attempted for subsequent attempts
	 * 
	 */
	private static String getRandomText() {
		String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		int stringLength = 12;
		while (stringLength-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}
}
