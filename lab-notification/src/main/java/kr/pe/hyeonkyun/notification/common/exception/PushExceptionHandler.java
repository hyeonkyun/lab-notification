package kr.pe.hyeonkyun.notification.common.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class PushExceptionHandler {
	
	@ExceptionHandler(value = PushException.class)
	public PushResponse handlePushException(PushException exception, HttpServletRequest request) {
		log.error( String.format("PushException.class - %s", exception.toString()) );
		exception.printStackTrace();		
		return new PushResponse( exception.getResultCode(), exception.getResultMessage() ) ;
	}
	
	@ExceptionHandler(value = JsonMappingException.class)
	public PushResponse handleException(JsonMappingException exception, HttpServletRequest request) {
		log.error( String.format("Exception.class - %s", exception.toString()) );
		exception.printStackTrace();
		return new PushResponse( PushError.INTERNAL_ERROR, PushError.toMessage(PushError.INTERNAL_ERROR) ) ;
	}
	
	@ExceptionHandler(value = JsonProcessingException.class)
	public PushResponse handleException(JsonProcessingException exception, HttpServletRequest request) {
		log.error( String.format("Exception.class - %s", exception.toString()) );
		exception.printStackTrace();
		return new PushResponse( PushError.INTERNAL_ERROR, PushError.toMessage(PushError.INTERNAL_ERROR) ) ;
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PushResponse handleException(HttpMessageNotReadableException exception, HttpServletRequest request) {
		log.error( String.format("Exception.class - %s", exception.toString()) );
		exception.printStackTrace();
		return new PushResponse( PushError.INVALID_PARAMETER, PushError.toMessage(PushError.INVALID_PARAMETER) ) ;
	}
	
	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public PushResponse handleException(Exception exception, HttpServletRequest request) {
		log.error( String.format("Exception.class - %s", exception.toString()) );
		exception.printStackTrace();
		return new PushResponse( PushError.UNKNOWN, PushError.toMessage(PushError.UNKNOWN) ) ;
	}
}
