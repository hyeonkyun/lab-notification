package kr.pe.jane.notification.domain.model;

import java.util.Date;

import lombok.Data;

@Data
public class PushTokenInfo {
	
	private String appId;
	
	private String accountId;
	
	private String token;
	
	private String useYn;
	
	private Date regDt;
	
	private Date updDt;
}
