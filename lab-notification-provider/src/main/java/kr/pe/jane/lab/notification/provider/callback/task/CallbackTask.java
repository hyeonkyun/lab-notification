package kr.pe.jane.lab.notification.provider.callback.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.pe.jane.lab.notification.provider.callback.CallbackMessage;
import kr.pe.jane.lab.notification.provider.callback.CallbackSender;
import kr.pe.jane.lab.notification.provider.web.dto.PushTransmitParam;

@Component
@Scope( "prototype" )
public class CallbackTask implements Runnable {

	PushTransmitParam pushTransmitParam;
	
	CallbackMessage callbackMessage;
	
	CallbackSender callbackSender;
	
	public CallbackTask( PushTransmitParam _pushTransmitParam, CallbackMessage _callbackMessage, CallbackSender _callbackSender) {
		this.pushTransmitParam = _pushTransmitParam ;
		this.callbackMessage = _callbackMessage ;
		this.callbackSender = _callbackSender ;
	}
	
	@Override
	public void run() {
		callbackSender.sendCallback( pushTransmitParam, callbackMessage );
	}
}
