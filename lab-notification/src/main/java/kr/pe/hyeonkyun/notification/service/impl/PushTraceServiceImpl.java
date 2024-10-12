package kr.pe.hyeonkyun.notification.service.impl;

import java.util.List;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq;
import kr.pe.hyeonkyun.notification.domain.repository.IPushTransmitRepository;
import kr.pe.hyeonkyun.notification.service.IPushTraceService;
import kr.pe.hyeonkyun.notification.web.dto.Page;

@Service
public class PushTraceServiceImpl implements IPushTraceService {
	
	private final IPushTransmitRepository pushTransmitRepository;

	@Autowired
	PushTraceServiceImpl(IPushTransmitRepository pushTransmitRepository) {
		this.pushTransmitRepository = pushTransmitRepository;
	}

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
			page.setTotalCount( pushTransmitRepository.selectPushTransmitReqTotalCnt( fromDate, toDate ) );
			return pushTransmitRepository.selectPushTransmitReq( fromDate, toDate, page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}
}
	