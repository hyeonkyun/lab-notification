package kr.pe.hyeonkyun.notification.service;

import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.web.dto.PushTransmitParam;

public interface ITransmitProxyService {
	
	public void transmit( PushTransmitParam pushTransmitParam ) throws PushException;
}
