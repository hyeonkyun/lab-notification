package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq;
import kr.pe.hyeonkyun.notification.service.IPushTraceService;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/push/trace" )
public class PushTraceController {
	
	private final IPushTraceService pushTraceService;

	@Autowired
	public PushTraceController( IPushTraceService pushTraceService ) {
		this.pushTraceService = pushTraceService;
	}
	
	/** | 1 | GET     | `/push/trace/{transReqId}` | PUSH 발송 요청 로그 조회(단건) | */
	@GetMapping( value = "/{transReqId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTransmitLogTraceByTransReqId( @PathVariable ("transReqId") String _transmitReqId, HttpServletRequest request ) throws PushException {
		Map<String, Object> responseBody = new HashMap<String, Object>();		
		responseBody.put("pushTransmitReqInfo", pushTraceService.getPushTransmitReqByTransmitReqId( _transmitReqId ) );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ), responseBody );
	}
	
	/** | 2 | GET     | `/push/trace/{reqDt}/{page}/{size}` | PUSH 발송 요청 로그 조회(리스트 by 요청일자) | */
	@GetMapping( value = "/{reqDt}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTransmitLogTrace( @PathVariable ("reqDt") String _reqDt, @PathVariable ("page") int _page, @PathVariable ("size") int _size, HttpServletRequest request ) throws PushException {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		Page page = new Page( _page, _size );
		String fromDate = String.format( "%s 00:00:00", _reqDt ); 
		String toDate = String.format( "%s 23:59:59", _reqDt );
		
		log.info( "fromDate : " + fromDate + ", toDate : " + toDate + ", page : " + page.toString() );
		List<PushTransmitReq> pushTransmitReqList = pushTraceService.getPushTransmitReqList( fromDate, toDate, page );
		
		if ( pushTransmitReqList != null && !pushTransmitReqList.isEmpty()) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTransmitReqList", pushTransmitReqList );
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ), responseBody );
	}
}
