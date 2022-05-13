package kr.pe.jane.notification.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.pe.jane.notification.common.Page;
import kr.pe.jane.notification.common.exception.PushError;
import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushCertificationInfo;
import kr.pe.jane.notification.domain.repository.IPushRepository;
import kr.pe.jane.notification.service.IPushCertificationService;
import kr.pe.jane.notification.web.dto.PushCertificationParam;

@Service
public class PushCertificationServiceImpl implements IPushCertificationService {

	@Inject
	private IPushRepository pushRepository;

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
			return pushRepository.selectPushCertificationInfoList( page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}
	
	@Override
	public int getPushCertificationTotalCnt() throws PushException {
		try {	  
			return pushRepository.selectPushCertificationInfoTotalCnt();
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
