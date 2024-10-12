package kr.pe.hyeonkyun.notification.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq.PushTransmit;
import kr.pe.hyeonkyun.notification.web.dto.Page;

@Mapper
@Transactional(readOnly=true)
public interface IPushTransmitRepository {

	// add transmit log
	@Transactional(readOnly = false)
	int insertPushTransmitReq( PushTransmitReq pushTransmitReq );

	@Transactional(readOnly = false)
	int updatePushTransmitReqErrMsg( PushTransmitReq pushTransmitReq );

	@Transactional(readOnly = false)
	int insertPushTransmit( PushTransmit pushTransmit );

	@Transactional(readOnly = false)
	int updatePushTransmit( PushTransmit pushTransmit );

	// trace transmit logs
	@ResultMap( "PushTransmitReqMap" )
    @Select( "SELECT * FROM TB_PUSH_TRANSMIT_REQ WHERE TRANSMIT_REQ_ID = #{transmitReqId}" )
	PushTransmitReq selectPushTransmitReqByTransmitReqId( String transmitReqId );

	int selectPushTransmitReqTotalCnt(String fromDate, String toDate);

	List<PushTransmitReq> selectPushTransmitReq ( String fromDate, String toDate, Page page );
}
