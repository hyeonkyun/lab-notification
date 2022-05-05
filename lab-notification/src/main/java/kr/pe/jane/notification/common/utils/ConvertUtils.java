package kr.pe.jane.notification.common.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConvertUtils {

static final ObjectMapper mapper = new ObjectMapper();
	
	public static String jsonObjectToPrettyString( JsonObject jsonObject ) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(jsonObject);
	}
	
	public static String jsonStringToPrettyString( String jsonString ) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(jsonString);
		return gson.toJson(je);
	}
	
	@SuppressWarnings("rawtypes")
	public static String mapToJsonString( Map map ) throws JsonProcessingException {
		return mapper.writeValueAsString(map);
	}	
	
	public static String objectToJsonString( Object obj ) throws JsonProcessingException {
		return mapper.writeValueAsString(obj);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, Object> JsonStringtoMap( String jsonString ) throws JsonMappingException, JsonProcessingException {		
		return mapper.readValue(jsonString, Map.class);
	}
}
