package kr.pe.jane.notification.web.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@JsonInclude( JsonInclude.Include.NON_EMPTY )
@Data
public class PushTransmitParam {
private String appId;
	
	private String accountId;
	
	private Notification notification;

	private Map< String, Object > data;
	
	private Webpush webpush;
	
	private Map< String, Object > android;
	
	private Map< String, Object > apns;
	
	// private Map< String, Object > fcm_options;
	
	@JsonInclude( JsonInclude.Include.NON_EMPTY )
	@Data
	public static class Notification {
		
		private String title;
		
		private String body;
		
		private String image;
	}
	
	@JsonInclude( JsonInclude.Include.NON_EMPTY )
	@Data
	public static class Webpush {
		
		private Map<String, String> headers;
		
		private Map<String, String> fcm_options;
	}
}
