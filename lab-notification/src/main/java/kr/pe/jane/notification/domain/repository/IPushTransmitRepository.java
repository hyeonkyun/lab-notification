package kr.pe.jane.notification.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.jane.notification.domain.model.PushTransmitReq;
import kr.pe.jane.notification.domain.model.PushTransmitReq.PushTransmit;
import kr.pe.jane.notification.web.dto.Page;

@Mapper
@Transactional(readOnly=true)
public interface IPushTransmitRepository {

	// add transmit log 
	@Transactional(readOnly = false)
	public int insertPushTransmitReq( PushTransmitReq pushTransmitReq );
	
	@Transactional(readOnly = false)
	public int updatePushTransmitReqErrMsg( PushTransmitReq pushTransmitReq );
	
	@Transactional(readOnly = false)
	public int insertPushTransmit( PushTransmit pushTransmit );
	
	@Transactional(readOnly = false)
	public int updatePushTransmit( PushTransmit pushTransmit );
	
	// trace transmit logs	
	@ResultMap( "PushTransmitReqMap" )
    @Select( "SELECT * FROM TB_PUSH_TRANSMIT_REQ WHERE TRANSMIT_REQ_ID = #{transmitReqId}" )
	public PushTransmitReq selectPushTransmitReqByTransmitReqId( String transmitReqId );
	
	public List<PushTransmitReq> selectPushTransmitReq ( String fromDate, String toDate, Page page );
}
