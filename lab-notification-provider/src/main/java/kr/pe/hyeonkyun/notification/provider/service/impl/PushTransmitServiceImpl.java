package kr.pe.hyeonkyun.notification.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hyeonkyun.notification.provider.service.IPushTransmitService;
import kr.pe.hyeonkyun.notification.provider.transmitter.PushTransmitter;
import kr.pe.hyeonkyun.notification.provider.transmitter.task.PushTransmitTask;
import kr.pe.hyeonkyun.notification.provider.transmitter.task.PushTransmitTaskManager;
import kr.pe.hyeonkyun.notification.provider.web.dto.PushTransmitParam;

@Service
public class PushTransmitServiceImpl implements IPushTransmitService {

	private final PushTransmitter pushTransmitter;

	private final PushTransmitTaskManager pushTransmitTaskManager;

	@Autowired
    public PushTransmitServiceImpl(PushTransmitter pushTransmitter, PushTransmitTaskManager pushTransmitTaskManager) {
        this.pushTransmitter = pushTransmitter;
        this.pushTransmitTaskManager = pushTransmitTaskManager;
    }

    @Override
	public void transmit( PushTransmitParam pushTransmitParam ) {
		pushTransmitTaskManager.execute( new PushTransmitTask( pushTransmitParam, pushTransmitter ) );
	}
}
