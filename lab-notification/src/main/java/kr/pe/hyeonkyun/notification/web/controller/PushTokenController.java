package kr.pe.hyeonkyun.notification.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	@Inject
	IPushTokenService pushTokenService; 

	/** | 1 | POST    | `/push/token` | App 토큰 등록 | */
	@PostMapping( value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE )
	@CrossOrigin(origins = "*", methods = RequestMethod.POST)
	public PushResponse pushTokenRegister( @RequestBody PushTokenParam pushTokenParam, HttpServletRequest request, HttpServletResponse response ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		if ( !pushTokenParam.isVaild() ) {
			throw new PushException(PushError.PARAMETER_MISSING);
		}
		
		pushTokenService.addPushToken( pushTokenParam );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
	
	/** | 2 | GET     | `/push/tokens/{appId}/{page}/{size}` | Token 조회(리스트 by appId) | */
	@GetMapping( value = "/tokens/{appId}/{page}/{size}", produces = MediaType.APPLICATION_JSON_VALUE )
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

	/** | 3 | GET     | `/push/tokens/{appId}/{accountId}/{page}/{size}` | Token 조회(리스트 by appId, accountId, page, size) | */
	@RequestMapping(value = "/tokens/{appId}/{accountId}/{page}/{size}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenInfoSearchListByAppIdAndAccountId( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, 
			@PathVariable("page") int _page, @PathVariable("size") int _size, HttpServletRequest request) throws PushException {
		Map<String, Object> responseBody = new HashMap<String, Object>();
		Page page = new Page( _page, _size );
		
		List<PushTokenInfo> pushTokenInfoList = pushTokenService.getPushTokenList( _appId, _accountId, page ) ;

		if( pushTokenInfoList != null && pushTokenInfoList.size() > 0 ) {
			responseBody.put( "pagination", page );
			responseBody.put( "pushTokenInfoList", pushTokenInfoList ); 
		}
		
		return new PushResponse( PushError.OK, PushError.toMessage(PushError.OK), responseBody );
	} 

	/** | 4 | PUT     | `/push/token/{appId}/{accountId}` | Token 사용 여부를 사용 or 미사용 으로 변경(by appId, accountId) | */
	@RequestMapping(value = "/token/{appId}/{accountId}", method = { RequestMethod.PUT, RequestMethod.PATCH }, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenModify( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @RequestBody PushTokenParam pushTokenParam, HttpServletRequest request ) throws PushException {
		log.debug( pushTokenParam.toString() );
		
		pushTokenParam.setAppId( _appId );
		pushTokenParam.setAccountId( _accountId );
		
		pushTokenService.modifyPushToken(pushTokenParam);
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}

	/** | 5 | DELETE  | `/push/tokens/{appId}/{accountId}` | Token 삭제(by appId, accountId) | */
	@RequestMapping(value = "/tokens/{appId}/{accountId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, HttpServletRequest request ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}

	/** | 6 | DELETE  | `/token/{appId}/{accountId}/{token}` | Token 삭제(by appId, accountId, token) | */
	@RequestMapping(value = "/token/{appId}/{accountId}/{token}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PushResponse pushTokenRemove( @PathVariable("appId") String _appId, @PathVariable("accountId") String _accountId, @PathVariable("token") String _token, HttpServletRequest request ) throws PushException {
		pushTokenService.removePushToken( _appId, _accountId, _token );
		
		return new PushResponse( PushError.OK, PushError.toMessage( PushError.OK ) );
	}
}