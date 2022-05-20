package kr.pe.jane.notification.service;

import java.util.List;

import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushTokenInfo;
import kr.pe.jane.notification.web.dto.Page;
import kr.pe.jane.notification.web.dto.PushTokenParam;

public interface IPushTokenService {
	
	public int addPushToken( PushTokenParam pushTokenParam ) throws PushException;
	
	public List<PushTokenInfo> getPushTokenList( String appId, Page page ) throws PushException;
	
	public List<PushTokenInfo> getPushTokenList( String appId, String accountId, Page page ) throws PushException;
	
	//public int getPushTokenTotalCnt( String appId ) throws PushException;
	
	public int getPushTokenTotalCnt( String appId, String accountId ) throws PushException;
	
	public int modifyPushToken( PushTokenParam pushTokenParam ) throws PushException;
	
	public int removePushToken( String appId, String accountId ) throws PushException;
	
	public int removePushToken( String appId, String accountId, String token ) throws PushException;

}
