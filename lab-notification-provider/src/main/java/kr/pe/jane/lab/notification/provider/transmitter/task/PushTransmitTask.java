package kr.pe.jane.lab.notification.provider.transmitter.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.pe.jane.lab.notification.provider.transmitter.PushTransmitter;
import kr.pe.jane.lab.notification.provider.web.dto.PushTransmitParam;

@Component
@Scope( "prototype" )
public class PushTransmitTask implements Runnable {
	
	PushTransmitParam pushTransmitParam ;
	
	PushTransmitter pushTransmitter ; 
	
	public PushTransmitTask( PushTransmitParam _pushTransmitParam, PushTransmitter _pushTransmitter ) {
		this.pushTransmitParam = _pushTransmitParam ;
		this.pushTransmitter = _pushTransmitter;
	}

	@Override
	public void run() {
		pushTransmitter.sendMessage( pushTransmitParam );
	}

}
