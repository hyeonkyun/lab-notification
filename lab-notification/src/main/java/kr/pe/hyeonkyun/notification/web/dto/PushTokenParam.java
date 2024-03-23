package kr.pe.hyeonkyun.notification.web.dto;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class PushTokenParam {

	private String appId;
	
	private String accountId;
	
	private String token;
	
	private String useYn = "Y";
	
	public boolean isVaild() {
		if ( !StringUtils.hasText( appId ) ) return false; 
		if ( !StringUtils.hasText( accountId ) ) return false;		
		if ( !StringUtils.hasText( token ) ) return false;
		return true;
	}
}
