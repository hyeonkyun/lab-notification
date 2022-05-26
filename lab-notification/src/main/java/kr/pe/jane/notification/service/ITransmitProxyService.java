package kr.pe.jane.notification.service;

import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.web.dto.PushTransmitParam;

public interface ITransmitProxyService {
	
	public void transmit( PushTransmitParam pushTransmitParam ) throws PushException;
}
