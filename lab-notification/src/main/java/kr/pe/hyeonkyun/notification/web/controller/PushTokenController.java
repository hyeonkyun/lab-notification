package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.service.IPushTokenService;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushResponse;
import kr.pe.hyeonkyun.notification.web.dto.PushTokenParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/push" )
public class PushTokenController {
	
	private final IPushTokenService pushTokenService;

	@Autowired
	PushTokenController( IPushTokenService pushTokenService ) {
		this.pushTokenService = pushTokenService;
	}

	/** | 1 | POST    | `/push/token` | App 토큰 등록 | */
	@PostMapping( value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*", methods = RequestMethod.POST)
	public PushResponse pushTokenRegister( @RequestBody PushTokenParam pushTokenParam ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		if ( !pushTokenParam.isVaild() ) {
			throw new PushException(PushError.PARAMETER_MISSING);
		}
		
		pushTokenService.addPushToken( pushTokenParam );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
	
	/** | 2 | GET     | `/push/tokens/{appId}?pageNum={pageNum}&pageSize={pageSize}` | Token 조회(리스트 by appId) | */
	@GetMapping( value = "/tokens/{appId}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTokenInfoSearchListByAppId(@PathVariable ("appId") String _appId, @RequestParam("pageNum") int _page, @RequestParam ("pageSize") int _size ) throws PushException {
		Map<String, Object> responseBody = new HashMap<>();
		Page page = new Page( _page, _size );
		
		List<PushTokenInfo> pushTokenInfoList = pushTokenService.getPushTokenList( _appId, page );
		
		if( pushTokenInfoList != null && !pushTokenInfoList.isEmpty()) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTokenInfoList", pushTokenInfoList ); 
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}

	/** | 3 | GET     | `/push/tokens/{appId}/{accountId}?pageNum={pageNum}&pageSize={pageSize}` | Token 조회(리스트 by appId, accountId, page, size) | */
	@RequestMapping(value = "/tokens/{appId}/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenInfoSearchListByAppIdAndAccountId( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId,
																	@RequestParam("pageNum") int _page, @RequestParam ("pageSize") int _size ) throws PushException {
		Map<String, Object> responseBody = new HashMap<>();
		Page page = new Page( _page, _size );
		
		List<PushTokenInfo> pushTokenInfoList = pushTokenService.getPushTokenList( _appId, _accountId, page ) ;

		if( pushTokenInfoList != null && !pushTokenInfoList.isEmpty()) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTokenInfoList", pushTokenInfoList ); 
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	} 

	/** | 4 | PUT     | `/push/token/{appId}/{accountId}` | Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId) | */
	@RequestMapping(value = "/token/{appId}/{accountId}", method = { RequestMethod.PUT, RequestMethod.PATCH }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenModify( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @RequestBody PushTokenParam pushTokenParam ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		pushTokenParam.setAppId( _appId );
		pushTokenParam.setAccountId( _accountId );
		
		pushTokenService.modifyPushToken(pushTokenParam);
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}

	/** | 5 | DELETE  | `/push/tokens/{appId}/{accountId}` | Token 삭제(by appId, accountId) | */
	@RequestMapping(value = "/tokens/{appId}/{accountId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}

	/** | 6 | DELETE  | `/token/{appId}/{accountId}/{token}` | Token 삭제(by appId, accountId, token) | */
	@RequestMapping(value = "/token/{appId}/{accountId}/{token}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @PathVariable("token") String _token ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId, _token );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
}