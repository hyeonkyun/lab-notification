package kr.pe.jane.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.pe.jane.notification.common.exception.PushError;
import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushTokenInfo;
import kr.pe.jane.notification.service.IPushTokenService;
import kr.pe.jane.notification.web.dto.Page;
import kr.pe.jane.notification.web.dto.PushResponse;
import kr.pe.jane.notification.web.dto.PushTokenParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping( value = "/push" )
public class PushTokenController {
	
	@Inject
	IPushTokenService pushTokenService; 
/*
| 4 | GET     | `/push/token/{appId}/{accountId}/{page}/{size}` | Token 조회(리스트 by appId, accountId, page, size) |
| 5 | PUT     | `/push/token/{appId}/{accountId}` | Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId) |
| 6 | DELETE  | `/push/token/{appId}/{accountId}` | Token 삭제(by appId, accountId) |
| 7 | DELETE  | `Token 삭제(by appId, accountId, token)` | Token 삭제(by appId, accountId, token) |
*/	
	/** | 1 | POST    | `/push/token` | App 토큰 등록 | */
	@PostMapping( value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTokenRegister( @RequestBody PushTokenParam pushTokenParam, HttpServletRequest request ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		if ( !pushTokenParam.isVaild() ) {
			throw new PushException(PushError.PARAMETER_MISSING);
		}
		
		pushTokenService.addPushToken( pushTokenParam );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
	
	/** | 2 | GET     | `/push/tokens/{appId}/{page}/{size}` | Token 조회(리스트 by appId) | */
	@GetMapping( value = "/{appId}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTokenInfoSearchListByAppId( @PathVariable ("appId") String _appId, @PathVariable ("page") int _page, @PathVariable ("size") int _size, HttpServletRequest request ) throws PushException {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		Page page = new Page( _page, _size );
		
		List<PushTokenInfo> pushTokenInfoList = pushTokenService.getPushTokenList( _appId, page );
		
		if( pushTokenInfoList != null && pushTokenInfoList.size() > 0 ) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTokenInfoList", pushTokenInfoList ); 
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}
	/** | 3 | GET     | `/push/token/{appId}/{accountId}` | Token 조회(리스트 by appId, accountId) | */
	@RequestMapping( value = "/{appId}/{accountId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
	public PushResponse pushTokenInfoSearchListByAppIdAndAccountId( @PathVariable ("appId") String _appId, @PathVariable ("accountId") String _accountId, HttpServletRequest request ) throws PushException {
		return pushTokenInfoSearchListByAppIdAndAccountId(_appId, _accountId, 1, 10, request );
	}
	
	@RequestMapping(value = "/{appId}/{accountId}/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenInfoSearchListByAppIdAndAccountId( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, 
			@PathVariable("page") int _page, @PathVariable("size") int _size, HttpServletRequest request) throws PushException {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		
		List<PushTokenInfo> pushTokenInfoList = pushTokenService.getPushTokenList( _appId, _accountId, new Page( _page, _size ) );
		
		responseBody.put("page", _page);
		responseBody.put("size", _size);
		responseBody.put("total", pushTokenService.getPushTokenTotalCnt( _appId, _accountId ) );
		
		if( pushTokenInfoList != null && pushTokenInfoList.size() > 0 ) {
			responseBody.put( "pushTokenInfoList", pushTokenInfoList ); 
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/{appId}/{accountId}", method = { RequestMethod.PUT, RequestMethod.PATCH }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenModify( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @RequestBody PushTokenParam pushTokenParam, HttpServletRequest request ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		pushTokenParam.setAppId( _appId );
		pushTokenParam.setAccountId( _accountId );
		
		pushTokenService.modifyPushToken(pushTokenParam);
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
	
	@RequestMapping(value = "/{appId}/{accountId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, HttpServletRequest request ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
	
	@RequestMapping(value = "/{appId}/{accountId}/{token}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @PathVariable("token") String _token, HttpServletRequest request ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId, _token );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}

}
