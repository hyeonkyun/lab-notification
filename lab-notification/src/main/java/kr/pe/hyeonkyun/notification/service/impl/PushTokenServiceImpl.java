package kr.pe.hyeonkyun.notification.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.pe.hyeonkyun.notification.common.exception.PushError;
import kr.pe.hyeonkyun.notification.common.exception.PushException;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.domain.repository.IPushRepository;
import kr.pe.hyeonkyun.notification.service.IPushTokenService;
import kr.pe.hyeonkyun.notification.web.dto.Page;
import kr.pe.hyeonkyun.notification.web.dto.PushTokenParam;

@Service
public class PushTokenServiceImpl implements IPushTokenService {

	@Inject
	private IPushRepository pushRepository;

	@Override
	public int addPushToken( PushTokenParam pushTokenParam ) throws PushException {
		try {
			return pushRepository.insertPushToken( pushTokenParam );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public List<PushTokenInfo> getPushTokenList( String appId, Page page ) throws PushException {
		try {
			page.setTotalCount( pushRepository.selectPushTokenInfoListTotalCnt( appId ) );
			return pushRepository.selectPushTokenInfoList( appId , page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public List<PushTokenInfo> getPushTokenList( String appId, String accountId, Page page ) throws PushException {
		try {
			page.setTotalCount( pushRepository.selectPushTokenInfoListTotalCntWithAccountId( appId, accountId ) );
			return pushRepository.selectPushTokenInfoListWithAccountId( appId, accountId, page );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public int getPushTokenTotalCnt( String appId, String accountId ) throws PushException {
		try {
			return pushRepository.selectPushTokenInfoListTotalCntWithAccountId( appId, accountId );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public int modifyPushToken( PushTokenParam pushTokenParam ) throws PushException {
		try {			
			if( StringUtils.hasText( pushTokenParam.getToken() ) ) {
				return pushRepository.updatePushTokenUseYnWithToken( pushTokenParam.getAppId(), pushTokenParam.getAccountId(), pushTokenParam.getToken(), pushTokenParam.getUseYn() );
			} else {
				return pushRepository.updatePushTokenUseYn( pushTokenParam.getAppId(), pushTokenParam.getAccountId(), pushTokenParam.getUseYn() );
			}
			
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public int removePushToken(String appId, String accountId) throws PushException {
		try {
			return pushRepository.deletePushToken( appId, accountId );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}

	@Override
	public int removePushToken(String appId, String accountId, String token) throws PushException {
		try {
			return pushRepository.deletePushTokenWithToken( appId, accountId, token );
		} catch (Exception e) {
			throw new PushException(PushError.INTERNAL_ERROR, e);
		}
	}
}
