package kr.pe.jane.notification.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.jane.notification.common.Page;
import kr.pe.jane.notification.domain.model.PushCertificationInfo;
import kr.pe.jane.notification.web.dto.PushCertificationParam;

@Mapper
@Transactional(readOnly=true)
public interface IPushRepository {

		// certification management
		@Transactional(readOnly = false)
		public int insertPushCertification( PushCertificationParam pushCertificationParam );
		
		@Select( "SELECT * FROM TB_PUSH_CERTIFICATION WHERE APP_ID = #{appId}" )
		public PushCertificationInfo selectPushCertificationInfo( @Param( "appId" ) String _appId );
		
		@Select( "SELECT * FROM TB_PUSH_CERTIFICATION LIMIT #{page.offset}, #{page.size}" )
		public List<PushCertificationInfo> selectPushCertificationInfoList( @Param( "page" ) Page _page );
		
		@Select( "SELECT COUNT(*) FROM TB_PUSH_CERTIFICATION" )
		public int selectPushCertificationInfoTotalCnt();
		
		@Transactional(readOnly = false)
		public int updatePushCertification( PushCertificationParam pushCertificationParam );
		
		@Transactional(readOnly = false)
		@Delete( "DELETE FROM TB_PUSH_CERTIFICATION WHERE APP_ID = #{appId}" )
		public int deletePushCertification( @Param( "appId" ) String _appId );
}

