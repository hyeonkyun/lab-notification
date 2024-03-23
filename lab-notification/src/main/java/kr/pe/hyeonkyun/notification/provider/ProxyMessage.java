package kr.pe.hyeonkyun.notification.provider;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class ProxyMessage {

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
}
