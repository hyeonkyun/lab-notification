package kr.pe.jane.notification.service;

import java.util.List;

import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushTransmitReq;
import kr.pe.jane.notification.web.dto.Page;

public interface IPushTraceService {

	public PushTransmitReq getPushTransmitReqByTransmitReqId( String transmitReqId ) throws PushException;
	
	public List<PushTransmitReq> getPushTransmitReqList( String fromDate, String toDate, Page page ) throws PushException;
}
