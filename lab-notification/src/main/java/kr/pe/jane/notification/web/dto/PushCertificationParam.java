package kr.pe.jane.notification.web.dto;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Data
public class PushCertificationParam {

	private static final ObjectMapper mapper = new ObjectMapper();
    
	private String appId;
	
	private String pmsCd;
	
	private String projectId;
		
	private Map<String, String> certification;
	
	private String appType;
	
	private String reqUser;
	
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
	
	public boolean isVaild() {
		if ( !StringUtils.hasText( appId ) ) return false; 
		if ( !StringUtils.hasText( pmsCd ) ) return false;
		if ( !StringUtils.hasText( projectId ) ) return false;
		if ( certification == null ) return false;
		if ( !StringUtils.hasText( appType ) ) return false;
		if ( !StringUtils.hasText( reqUser ) ) return false;
			
		return true;
	}
}
