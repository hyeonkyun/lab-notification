package kr.pe.jane.notification.web.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_EMPTY )
public class PushResponse {
	
	private String responseCode;
	
	private String responseMessage;
	
	private Map<String, Object> responseBody;
	
	public PushResponse( String _resultCd ) {
		this.responseCode = _resultCd ;
	}

	public PushResponse( String _resultCd, String _resultMsg ) {
		this.responseCode  = _resultCd ;
		this.responseMessage = _resultMsg ;
	}
	
	public PushResponse( String _resultCd, String _resultMsg, Map<String, Object> _responseBody ) {
		this.responseCode  = _resultCd ;
		this.responseMessage = _resultMsg ;
		this.responseBody =_responseBody ;
	}
}
