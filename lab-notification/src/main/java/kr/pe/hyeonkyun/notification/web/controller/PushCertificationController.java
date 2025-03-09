package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.JsonProcessingException;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.service.IPushCertificationService;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushCertificationParam;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/push" )
public class PushCertificationController {

	private final IPushCertificationService pushCertificationService;

	@Autowired
	PushCertificationController (IPushCertificationService pushCertificationService) {
		this.pushCertificationService = pushCertificationService;
	}
	
	/** | 1 | POST    | `/push/certification` | Firebase 서비스 계정 비공개 키 등록 | */
	@PostMapping( value = "/certification", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushCertificationRegister( @RequestBody PushCertificationParam pushCertificationParam ) throws PushException {
		log.debug( pushCertificationParam.toString() );
		
		if ( !pushCertificationParam.isVaild() ) {
			throw new PushException(PushError.PARAMETER_MISSING);
		}
		
		pushCertificationService.addPushCertification( pushCertificationParam );
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK) );
	}
	
	/** | 2 | GET     | `/push/certifications?pageNum={pageNum}&pageSize={pageSize}` | Firebase 서비스 계정 비공개 키 조회(리스트) | */
	@GetMapping( value = "/certifications", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushCertificationSearchList(@RequestParam("pageNum") int _page, @RequestParam ("pageSize") int _size ) throws PushException {
		Map<String, Object> responseBody = new HashMap<>();
		Page page = new Page( _page, _size );
		
		List<PushCertificationInfo> pushCertificationInfoList = pushCertificationService.getPushCertificationList( page );
		
		pushCertificationInfoList.forEach(action -> {
			try {
				action.toCertificationMap();
			} catch (PushException | JsonProcessingException e) {
				throw new PushException(PushError.INTERNAL_ERROR, PushError.toMessage(PushError.INTERNAL_ERROR), e);
			}
		});
		
		if (!pushCertificationInfoList.isEmpty()) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushCertificationInfoList", pushCertificationInfoList );
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}
	
	/** | 3 | GET     | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 조회(단건) | */
	@GetMapping( value = "/certification/{appId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushCertificationSearch( @PathVariable ("appId") String _appId ) throws PushException, JsonProcessingException {
		Map<String, Object> responseBody = new HashMap<>();
		
		PushCertificationInfo pushCertificationInfo = pushCertificationService.getPushCertification( _appId );
		
		if( pushCertificationInfo != null ) {
			pushCertificationInfo.toCertificationMap();
			responseBody.put("pushCertificationInfo", pushCertificationInfo);
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}
	
	/** | 4 | PUT     | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 수정 | */
	@PutMapping( value = "/certification/{appId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushCertificationModify( @PathVariable ("appId") String _appId, @RequestBody PushCertificationParam pushCertificationParam ) throws PushException {
		log.debug( pushCertificationParam.toString() );
		
		pushCertificationParam.setAppId( _appId );
		
		if ( !pushCertificationParam.isVaild() ) {
			throw new PushException(PushError.PARAMETER_MISSING);
		}
		
		pushCertificationService.modifyPushCertification(pushCertificationParam);
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK) );
	}
	
	/** | 5 | DELETE  | `/push/certification/{appId}` | Firebase 서비스 계정 비공개 키 삭제 | */
	@DeleteMapping( value = "/certification/{appId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushCertificationRemove( @PathVariable ("appId") String _appId ) throws PushException {
		pushCertificationService.removePushCertification( _appId );
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK) );
	}	
}