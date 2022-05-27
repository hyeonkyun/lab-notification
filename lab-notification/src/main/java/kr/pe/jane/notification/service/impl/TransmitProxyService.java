package kr.pe.jane.notification.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.pe.jane.notification.common.PushTransmitReqDataHolder;
import kr.pe.jane.notification.common.exception.PushError;
import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushCertificationInfo;
import kr.pe.jane.notification.domain.model.PushTokenInfo;
import kr.pe.jane.notification.domain.repository.IPushRepository;
import kr.pe.jane.notification.domain.repository.IPushTransmitRepository;
import kr.pe.jane.notification.provider.ProxySender;
import kr.pe.jane.notification.provider.task.ProxyTask;
import kr.pe.jane.notification.provider.task.ProxyTaskManager;
import kr.pe.jane.notification.service.ITransmitProxyService;
import kr.pe.jane.notification.web.dto.PushTransmitParam;

@Service
public class TransmitProxyService implements ITransmitProxyService {

	@Inject
	private IPushRepository pushRepository;
	
	@Inject
	private IPushTransmitRepository pushTransmitRepository;
	
	@Inject
	private ProxyTaskManager proxyTaskManager;
	
	@Inject
	ProxySender proxySender;
	
	@Override
	public void transmit(PushTransmitParam pushTransmitParam) throws PushException {
		
		PushCertificationInfo pushCertificationInfo = pushRepository.selectPushCertificationInfo( pushTransmitParam.getAppId() );
		pushTransmitRepository.insertPushTransmitReq( PushTransmitReqDataHolder.getRequestData() );
		
		if ( pushCertificationInfo == null ) {
			PushTransmitReqDataHolder.getRequestData().setErrMsg( PushError.toMessage(PushError.CERTIFICATION_NOT_FOUND) );
			pushTransmitRepository.updatePushTransmitReqErrMsg( PushTransmitReqDataHolder.getRequestData() );
			throw new PushException( PushError.CERTIFICATION_NOT_FOUND, PushError.toMessage(PushError.CERTIFICATION_NOT_FOUND) );
		}
		
		List<PushTokenInfo> pushTokenInfoList = pushRepository.selectPushTokenInfoListForTransmit( pushTransmitParam.getAppId(), pushTransmitParam.getAccountId() );
		
		if( pushTokenInfoList == null || pushTokenInfoList.size() < 1 ) {
			PushTransmitReqDataHolder.getRequestData().setErrMsg( PushError.toMessage(PushError.TOKEN_NOT_FOUND) );
			pushTransmitRepository.updatePushTransmitReqErrMsg( PushTransmitReqDataHolder.getRequestData() );
			throw new PushException( PushError.TOKEN_NOT_FOUND, PushError.toMessage(PushError.TOKEN_NOT_FOUND) );
		}	 
		
		for( PushTokenInfo pushTokenInfo : pushTokenInfoList ) {
			proxyTaskManager.execute( new ProxyTask( PushTransmitReqDataHolder.getRequestData().getTransmitReqId(), 
					pushTransmitParam, pushCertificationInfo, pushTokenInfo, proxySender ) );
		}
	}
}
