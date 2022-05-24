package kr.pe.jane.lab.notification.provider.web.dto;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class PushTransmitParam {

	private static final ObjectMapper mapper = new ObjectMapper();
	
	private String transmitReqId;
	
	private String projectId;
	
	private String token;
	
	// TODO private List<String> tokens;
	
	// TODO private String topic;
	
	private Map< String, Object > certification;
	
	private Map< String, Object > notification;

	private Map< String, Object > data;
	
	private Map< String, Object > webpush;
	
	// TODO private Map< String, Object > android;
	
	// TODO private Map< String, Object > apns;
	
	private String callbackUrl;
	
	public String getCertification()  {
		
		if( certification != null ) {
			try {
				return mapper.writeValueAsString( certification );
			} catch (JsonProcessingException e) {				
				e.printStackTrace();
			} 
		}
		
		return null;
	}
}
