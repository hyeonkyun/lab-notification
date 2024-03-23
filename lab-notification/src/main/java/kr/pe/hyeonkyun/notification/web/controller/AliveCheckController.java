package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;

@RestController
public class AliveCheckController {

	@Value("${service.mode}")
    private String serviceMode; 

	@RequestMapping( value = "/alive", method = RequestMethod.GET )
	public PushResponse aliveCheck( HttpServletRequest request ) throws Exception {
		
		Map<String, Object> responseBody = new HashMap<String, Object>();	
		responseBody.put("serverMode", serviceMode);
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}
}
