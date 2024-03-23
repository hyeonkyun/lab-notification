package kr.pe.hyeonkyun.notification.provider.service;

import kr.pe.hyeonkyun.notification.provider.web.dto.PushTransmitParam;

public interface IPushTransmitService {

	public void transmit( PushTransmitParam pushTransmitParam ) ;
}
