package kr.pe.jane.lab.notification.provider.service;

import kr.pe.jane.lab.notification.provider.web.dto.PushTransmitParam;

public interface IPushTransmitService {

	public void transmit( PushTransmitParam pushTransmitParam ) ;
}
