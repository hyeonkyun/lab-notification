package kr.pe.hyeonkyun.notification.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq.PushTransmit;
import kr.pe.hyeonkyun.notification.domain.repository.IPushTransmitRepository;
import kr.pe.hyeonkyun.notification.service.ITransmitCallbackService;

@Service
public class TransmitCallbackServiceImpl implements ITransmitCallbackService {
	
	private final IPushTransmitRepository pushTransmitRepository;

	@Autowired
	TransmitCallbackServiceImpl(IPushTransmitRepository pushTransmitRepository) {
		this.pushTransmitRepository = pushTransmitRepository;
	}

	@Override
	public void callback(Map<String, Object> requestBody) throws PushException {
		
		PushTransmit pushTransmit = new PushTransmit();
		
		pushTransmit.setTransmitReqId( requestBody.get("transmitReqId").toString() );
		pushTransmit.setTarget( requestBody.get("token").toString() );
		pushTransmit.setResStatusCd( (int)requestBody.get("responseStatusCd") );
		pushTransmit.setResData( requestBody.get("responseData").toString() );
		pushTransmit.setEndDt( new Date() );
		
		pushTransmitRepository.updatePushTransmit(pushTransmit);
	}
}
