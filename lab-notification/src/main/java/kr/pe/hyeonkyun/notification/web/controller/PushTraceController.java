package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
	public PushResponse pushTransmitLogTraceByTransReqId( @PathVariable ("transReqId") String _transmitReqId ) throws PushException {
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("pushTransmitReqInfo", pushTraceService.getPushTransmitReqByTransmitReqId( _transmitReqId ) );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ), responseBody );
	}
	
	/** | 2 | GET     | `/push/trace/{reqDt}?pageNum={pageNum}&pageSize={pageSize}` | PUSH 발송 요청 로그 조회(리스트 by 요청일자) | */
	@GetMapping( value = "/{reqDt}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTransmitLogTrace(@PathVariable ("reqDt") String _reqDt, @RequestParam("pageNum") int _page, @RequestParam ("pageSize") int _size ) throws PushException {
		Map<String, Object> responseBody = new HashMap<>();
		
		Page page = new Page( _page, _size );
		String fromDate = String.format( "%s 00:00:00", _reqDt ); 
		String toDate = String.format( "%s 23:59:59", _reqDt );
		
		log.info( "fromDate : " + fromDate + ", toDate : " + toDate + ", page : " + page );
		List<PushTransmitReq> pushTransmitReqList = pushTraceService.getPushTransmitReqList( fromDate, toDate, page );
		
		if ( pushTransmitReqList != null && !pushTransmitReqList.isEmpty()) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTransmitReqList", pushTransmitReqList );
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ), responseBody );
	}
}
