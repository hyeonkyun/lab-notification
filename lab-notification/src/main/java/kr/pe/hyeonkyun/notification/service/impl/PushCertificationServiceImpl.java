package kr.pe.hyeonkyun.notification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.domain.repository.IPushRepository;
import kr.pe.hyeonkyun.notification.service.IPushCertificationService;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushCertificationParam;

@Service
public class PushCertificationServiceImpl implements IPushCertificationService {

	private final IPushRepository pushRepository;

	@Autowired
	PushCertificationServiceImpl(IPushRepository pushRepository) {
		this.pushRepository = pushRepository;
	}

	@Override
	public int addPushCertification( PushCertificationParam pushCertificationParam ) throws PushException {		
		PushCertificationInfo pushCertificationInfo = pushRepository.selectPushCertificationInfo( pushCertificationParam.getAppId() );
		
		if( pushCertificationInfo != null ) {
			throw new PushException(PushError.CERTIFICATION_DUPLICATED);
		}
		
		try {
			return pushRepository.insertPushCertification( pushCertificationParam );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public PushCertificationInfo getPushCertification( String appId ) throws PushException {
		try {
			return pushRepository.selectPushCertificationInfo( appId );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public List<PushCertificationInfo> getPushCertificationList( Page page ) throws PushException {		
		try {	  
			page.setTotalCount( pushRepository.selectPushCertificationInfoTotalCnt() );
			
			return pushRepository.selectPushCertificationInfoList( page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}
	
	@Override
	public int modifyPushCertification( PushCertificationParam pushCertificationParam ) throws PushException {
		try {
			return pushRepository.updatePushCertification( pushCertificationParam );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public int removePushCertification( String appId ) throws PushException {
		try {
			return pushRepository.deletePushCertification( appId );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

}
