package com.comfunmanager.utils;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
 
/**
 * use:jackson
 */
public class UtilsJSONChange {

	private static ObjectMapper objectMapper = new ObjectMapper();

	  public static String objectToString(Object object) throws JsonProcessingException {
	    return objectMapper.writeValueAsString(object);
	  }

	  public static <T> T stringToObject(String json,Class<T> object) throws IOException {
	    return objectMapper.readValue(json,object);
	  }
	
}
