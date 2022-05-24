package kr.pe.jane.lab.notification.provider.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.pe.jane.lab.notification.provider.service.IPushTransmitService;
import kr.pe.jane.lab.notification.provider.transmitter.PushTransmitter;
import kr.pe.jane.lab.notification.provider.web.dto.PushTransmitParam;

@Service
public class PushTransmitServiceImpl implements IPushTransmitService {

	@Inject
	PushTransmitter pushTransmitter;
	
	@Inject
	PushTransmitTaskManager pushTransmitTaskManager;
	
	@Override
	public void transmit( PushTransmitParam pushTransmitParam ) {
		pushTransmitTaskManager.execute( new PushTransmitTask( pushTransmitParam, pushTransmitter ) );
	}
}
