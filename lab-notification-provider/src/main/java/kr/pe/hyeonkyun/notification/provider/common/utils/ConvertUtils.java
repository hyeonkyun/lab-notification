package kr.pe.hyeonkyun.notification.provider.common.utils;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ConvertUtils {

private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String jsonStringToPrettyString( String jsonString ) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonElement je = JsonParser.parseString(jsonString);
		return gson.toJson(je);
	}
	
	public static String mapToJsonString( Map<String, Object> map ) throws JsonProcessingException {
		return mapper.writeValueAsString(map);
	}
}
