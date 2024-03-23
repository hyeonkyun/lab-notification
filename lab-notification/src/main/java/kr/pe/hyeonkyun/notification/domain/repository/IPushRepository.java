package kr.pe.hyeonkyun.notification.domain.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushCertificationParam;
import kr.pe.hyeonkyun.notification.web.dto.PushTokenParam;

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
		
		// token management
		@Transactional(readOnly = false)
		public int insertPushToken( PushTokenParam pushTokenParam );
		
		@Select( "SELECT * FROM TB_PUSH_TOKEN WHERE APP_ID = #{appId} AND ACCOUNT_ID = #{accountId} AND TOKEN = #{token}" )
		public PushTokenInfo selectPushTokenInfo( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId, @Param( "token" ) String _token );
		
		@Select( "SELECT * FROM TB_PUSH_TOKEN WHERE APP_ID = #{appId} LIMIT #{page.offset}, #{page.size}" )
		public List<PushTokenInfo> selectPushTokenInfoList( @Param( "appId" ) String _appId, @Param( "page" ) Page _page );
		
		@Select( "SELECT * FROM TB_PUSH_TOKEN WHERE APP_ID  = #{appId} AND ACCOUNT_ID = #{accountId} LIMIT #{page.offset}, #{page.size}" )
		public List<PushTokenInfo> selectPushTokenInfoListWithAccountId( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId, @Param( "page" ) Page _page );
		
		@Select( "SELECT COUNT(*) FROM TB_PUSH_TOKEN WHERE APP_ID = #{appId}" )
		public int selectPushTokenInfoListTotalCnt( @Param( "appId" ) String _appId );
		
		@Select( "SELECT COUNT(*) FROM TB_PUSH_TOKEN WHERE APP_ID  = #{appId} AND ACCOUNT_ID = #{accountId}" )
		public int selectPushTokenInfoListTotalCntWithAccountId( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId );
		
		@Transactional(readOnly = false)
		@Update( "UPDATE TB_PUSH_TOKEN SET USE_YN = #{useYn} WHERE APP_ID  = #{appId} AND ACCOUNT_ID = #{accountId}" )
		public int updatePushTokenUseYn( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId, @Param( "useYn" ) String _useYn );
		
		@Transactional(readOnly = false)
		@Update( "UPDATE TB_PUSH_TOKEN SET USE_YN = #{useYn} WHERE APP_ID  = #{appId} AND ACCOUNT_ID = #{accountId} AND TOKEN = #{token}" )
		public int updatePushTokenUseYnWithToken( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId, @Param( "token" ) String _token, @Param( "useYn" ) String _useYn );
		
		@Transactional(readOnly = false)
		@Delete( "DELETE FROM TB_PUSH_TOKEN WHERE APP_ID = #{appId} AND ACCOUNT_ID = #{accountId}" )
		public int deletePushToken( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId );
		
		@Transactional(readOnly = false)
		@Delete( "DELETE FROM TB_PUSH_TOKEN WHERE APP_ID = #{appId} AND ACCOUNT_ID = #{accountId} AND TOKEN = #{token}" )
		public int deletePushTokenWithToken( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId, @Param( "token" ) String _token );
		
		@Select( "SELECT * FROM TB_PUSH_TOKEN WHERE APP_ID  = #{appId} AND ACCOUNT_ID = #{accountId} AND USE_YN = 'Y'" )
		public List<PushTokenInfo> selectPushTokenInfoListForTransmit( @Param( "appId" ) String _appId, @Param( "accountId" ) String _accountId );	
}

