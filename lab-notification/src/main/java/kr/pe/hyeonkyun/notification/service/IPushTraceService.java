package kr.pe.hyeonkyun.notification.service;

import java.util.List;

import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq;
import kr.pe.hyeonkyun.notification.web.dto.Page;

public interface IPushTraceService {

	public PushTransmitReq getPushTransmitReqByTransmitReqId( String transmitReqId ) throws PushException;
	
	public List<PushTransmitReq> getPushTransmitReqList( String fromDate, String toDate, Page page ) throws PushException;
}
