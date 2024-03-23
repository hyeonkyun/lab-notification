package kr.pe.hyeonkyun.notification.service;

import java.util.List;

import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushCertificationParam;

public interface IPushCertificationService {

	public int addPushCertification( PushCertificationParam pushCertificationParam ) throws PushException;
	
	public PushCertificationInfo getPushCertification( String appId ) throws PushException;
	
	public List<PushCertificationInfo> getPushCertificationList( Page page ) throws PushException; 
	
	public int modifyPushCertification( PushCertificationParam pushCertificationParam )  throws PushException;
	
	public int removePushCertification( String appId )  throws PushException;
}
