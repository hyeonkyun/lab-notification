package kr.pe.jane.notification.provider.task;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import kr.pe.jane.notification.domain.model.PushCertificationInfo;
import kr.pe.jane.notification.domain.model.PushTokenInfo;
import kr.pe.jane.notification.provider.ProxySender;
import kr.pe.jane.notification.web.dto.PushTransmitParam;

@Component
@Scope( "prototype" )
public class ProxyTask implements Runnable {
	
	String transReqId;
	
	PushTransmitParam pushTransmitParam;
	
	PushCertificationInfo pushCertificationInfo;
	
	PushTokenInfo pushTokenInfo;
	
	ProxySender proxySender;
	
	public ProxyTask( String _transReqId, PushTransmitParam _pushTransmitParam, PushCertificationInfo _pushCertificationInfo, PushTokenInfo _pushTokenInfo, ProxySender _proxySender ) {
		this.transReqId = _transReqId;
		this.pushTransmitParam = _pushTransmitParam;
		this.pushCertificationInfo = _pushCertificationInfo;
		this.pushTokenInfo = _pushTokenInfo;
		this.proxySender = _proxySender;
	}

	@Override
	public void run() {
		proxySender.sendProvider( transReqId, pushTransmitParam, pushCertificationInfo, pushTokenInfo );
	}
}
