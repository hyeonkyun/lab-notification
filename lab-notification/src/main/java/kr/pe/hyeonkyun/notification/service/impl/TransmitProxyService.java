package kr.pe.hyeonkyun.notification.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hyeonkyun.notification.common.PushTransmitReqDataHolder;
import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.domain.repository.IPushRepository;
import kr.pe.hyeonkyun.notification.domain.repository.IPushTransmitRepository;
import kr.pe.hyeonkyun.notification.provider.ProxySender;
import kr.pe.hyeonkyun.notification.provider.task.ProxyTask;
import kr.pe.hyeonkyun.notification.provider.task.ProxyTaskManager;
import kr.pe.hyeonkyun.notification.service.ITransmitProxyService;
import kr.pe.hyeonkyun.notification.web.dto.PushTransmitParam;

@Service
public class TransmitProxyService implements ITransmitProxyService {

	private final IPushRepository pushRepository;
	
	private final IPushTransmitRepository pushTransmitRepository;
	
	private final ProxyTaskManager proxyTaskManager;
	
	private final ProxySender proxySender;

	@Autowired
	TransmitProxyService(IPushRepository pushRepository, IPushTransmitRepository pushTransmitRepository, ProxyTaskManager proxyTaskManager, ProxySender proxySender) {
		this.pushRepository = pushRepository;
		this.pushTransmitRepository = pushTransmitRepository;
		this.proxyTaskManager = proxyTaskManager;
		this.proxySender = proxySender;
	}
	
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
		
		if( pushTokenInfoList == null || pushTokenInfoList.isEmpty()) {
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
