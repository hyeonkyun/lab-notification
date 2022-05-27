package kr.pe.jane.notification.service;

import java.util.Map;

import kr.pe.jane.notification.common.exception.PushException;

public interface ITransmitCallbackService {

	public void callback( Map<String, Object> requestBody ) throws PushException;
}
