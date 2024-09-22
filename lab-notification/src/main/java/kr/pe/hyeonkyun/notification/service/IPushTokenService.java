package kr.pe.hyeonkyun.notification.service;

import java.util.List;

import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushTokenParam;

public interface IPushTokenService {
	
	public int addPushToken( PushTokenParam pushTokenParam ) throws PushException;
	
	public List<PushTokenInfo> getPushTokenList( String appId, Page page ) throws PushException;
	
	public List<PushTokenInfo> getPushTokenList( String appId, String accountId, Page page ) throws PushException;

	public int getPushTokenTotalCnt( String appId, String accountId ) throws PushException;
	
	public int modifyPushToken( PushTokenParam pushTokenParam ) throws PushException;
	
	public int removePushToken( String appId, String accountId ) throws PushException;
	
	public int removePushToken( String appId, String accountId, String token ) throws PushException;

}
