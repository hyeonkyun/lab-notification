package kr.pe.jane.notification.domain.model;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.common.utils.ConvertUtils;
import lombok.Data;

@Data
public class PushCertificationInfo {

private String appId;
	
	private String pmsCd;
	
	private String projectId;
	
	@JsonIgnore 
	private String certification;
	
	@JsonProperty( value = "certification" )
	private Map<String, Object> certificationMap;
	
	private String appType;
	
	private String insUser;
	
	private Date insDt;
	
	private String updUser;
	
	private Date updDt;
	
	public void toCertificationMap() throws PushException, JsonMappingException, JsonProcessingException {
		this.certificationMap = ConvertUtils.JsonStringtoMap( certification );	
	}
}
