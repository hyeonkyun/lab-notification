package kr.pe.jane.notification.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.pe.jane.notification.common.exception.PushError;
import kr.pe.jane.notification.common.exception.PushException;
import kr.pe.jane.notification.domain.model.PushTransmitReq;
import kr.pe.jane.notification.domain.repository.IPushTransmitRepository;
import kr.pe.jane.notification.service.IPushTraceService;
import kr.pe.jane.notification.web.dto.Page;

@Service
public class PushTraceServiceImpl implements IPushTraceService {
	
	@Inject
	private IPushTransmitRepository pushTransmitRepository;

	@Override
	public PushTransmitReq getPushTransmitReqByTransmitReqId( String transmitReqId ) throws PushException {
		try {
			return pushTransmitRepository.selectPushTransmitReqByTransmitReqId( transmitReqId );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public List<PushTransmitReq> getPushTransmitReqList(String fromDate, String toDate, Page page) throws PushException {
		try {
			return pushTransmitRepository.selectPushTransmitReq( fromDate, toDate, page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}
}
	