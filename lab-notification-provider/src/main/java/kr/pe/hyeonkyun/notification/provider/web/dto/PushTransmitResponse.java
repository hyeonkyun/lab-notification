package kr.pe.hyeonkyun.notification.provider.web.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class PushTransmitResponse {
	
	private String responseCode;
	
	private String responseMessage;
	
	private Map<String, Object> responseBody;
	
	public PushTransmitResponse( String _resultCd ) {
		this.responseCode = _resultCd ;
	}

	public PushTransmitResponse( String _resultCd, String _resultMsg ) {
		this.responseCode  = _resultCd ;
		this.responseMessage = _resultMsg ;
	}
	
	public PushTransmitResponse( String _resultCd, String _resultMsg, Map<String, Object> _responseBody ) {
		this.responseCode  = _resultCd ;
		this.responseMessage = _resultMsg ;
		this.responseBody =_responseBody ;
	}

}
