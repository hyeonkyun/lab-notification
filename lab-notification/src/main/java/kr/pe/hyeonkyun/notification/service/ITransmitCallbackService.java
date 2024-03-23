package kr.pe.hyeonkyun.notification.service;

import java.util.Map;

import kr.pe.hyeonkyun.notification.common.exception.PushException;

public interface ITransmitCallbackService {

	public void callback( Map<String, Object> requestBody ) throws PushException;
}
