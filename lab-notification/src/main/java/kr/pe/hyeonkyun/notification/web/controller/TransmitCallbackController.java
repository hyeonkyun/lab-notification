package kr.pe.hyeonkyun.notification.web.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.service.ITransmitCallbackService;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/callback" )
public class TransmitCallbackController {

	private final ITransmitCallbackService transmitCallbackService ;

	@Autowired
	public TransmitCallbackController( ITransmitCallbackService transmitCallbackService ) {
		this.transmitCallbackService = transmitCallbackService ;
	}

	@RequestMapping( value = "/provider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse transmitCallback( @RequestBody Map<String, Object> requestBody, HttpServletRequest request ) throws Exception {

		log.info( requestBody.toString() );		
		transmitCallbackService.callback( requestBody );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
}
