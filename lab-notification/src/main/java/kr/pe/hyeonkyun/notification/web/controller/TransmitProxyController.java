package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.notification.common.PushTransmitReqDataHolder;
import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.utils.IdGeneratorUtils;
import kr.pe.hyeonkyun.notification.service.ITransmitProxyService;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import kr.pe.hyeonkyun.notification.web.dto.PushTransmitParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/push/transmit" )
public class TransmitProxyController {

	@Inject
	private ITransmitProxyService pushTransmitProxyService ;

	/** | 1 | POST    | `/push/transmit/{appId}/{accountId}` | 사용자 별 Push 발송 요청 | */
	@PostMapping( value = "/{appId}/{accountId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTransmitRequestByAppIdAndAccountId( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @RequestBody PushTransmitParam pushTransmitParam, HttpServletRequest request ) throws Exception {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		pushTransmitParam.setAppId( _appId );
		pushTransmitParam.setAccountId( _accountId );
		log.info( pushTransmitParam.toString() );
		
		PushTransmitReqDataHolder.setRequestData( IdGeneratorUtils.makeTransmitReqId() , pushTransmitParam, request );
		
		pushTransmitProxyService.transmit( pushTransmitParam );
		
		responseBody.put( "transmitReqId", PushTransmitReqDataHolder.getRequestData().getTransmitReqId() );
		
		PushTransmitReqDataHolder.resetRequestData();
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ), responseBody );
	}
	
/* 	TODO
	| 2 | POST    | `/push/transmit/{appId}` | 앱 별 Push 발송 요청 |
	| 3 | POST    | `/push/transmit/{appId}/{topic}` | 주제 별 Push 발송 요청 |
*/	

}
