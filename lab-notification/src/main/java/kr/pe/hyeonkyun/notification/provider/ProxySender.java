package kr.pe.hyeonkyun.notification.provider;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import kr.pe.hyeonkyun.notification.common.constants.TargetType;
import kr.pe.hyeonkyun.notification.domain.model.PushCertificationInfo;
import kr.pe.hyeonkyun.notification.domain.model.PushTokenInfo;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq;
import kr.pe.hyeonkyun.notification.domain.model.PushTransmitReq.PushTransmit;
import kr.pe.hyeonkyun.notification.domain.repository.IPushTransmitRepository;
import kr.pe.hyeonkyun.notification.web.dto.PushTransmitParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Component
public class ProxySender implements InitializingBean {
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private RequestConfig requestConfig ; 
	
	private static final int socketTimeout = 10000 ;
	
	private static final int connectTimeout = 10000 ;
	
	private static final int connectionRequestTimeout = 10000 ;
	
	@Value("${callback.url}")
    private String callbackUrl; 
	
	@Value("${provider.url}")
    private String providerUrl; 

	private final IPushTransmitRepository pushTransmitRepository;

	@Autowired
	ProxySender(IPushTransmitRepository pushTransmitRepository) {
		this.pushTransmitRepository = pushTransmitRepository;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.build();
	}
	
	public void sendProvider( String transmitReqId, PushTransmitParam pushTransmitParam, PushCertificationInfo pushCertificationInfo, PushTokenInfo pushTokenInfo ) {
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null;
		
		Gson gson = new Gson();
		
		Map<String, String> logMap = new HashMap<String, String>();
		PushTransmit pushTransmit = new PushTransmit( transmitReqId, pushTokenInfo.getToken(), TargetType.toMessage( TargetType.TOKEN ), pushTokenInfo.getAccountId(), "" );
		
		try {
			pushCertificationInfo.toCertificationMap();
			
			ProxyMessage proxyMessage = new ProxyMessage();
			proxyMessage.setTransmitReqId( transmitReqId );
			proxyMessage.setProjectId( pushCertificationInfo.getProjectId() );
			proxyMessage.setToken( pushTokenInfo.getToken() );
			proxyMessage.setCertification( pushCertificationInfo.getCertificationMap() );
			proxyMessage.setNotification( objectMapper.convertValue(pushTransmitParam.getNotification(), Map.class) );
			proxyMessage.setData( pushTransmitParam.getData() );
			proxyMessage.setWebpush( objectMapper.convertValue(pushTransmitParam.getWebpush(), Map.class) );
			proxyMessage.setCallbackUrl( callbackUrl );
			
			String jstrProxyMessage = gson.toJson(proxyMessage);
			
			pushTransmit.setReqData(jstrProxyMessage);
			pushTransmitRepository.insertPushTransmit( pushTransmit );
			
			HttpPost httpPost = new HttpPost( providerUrl );
			httpPost.setConfig(requestConfig);
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");		
			httpPost.setEntity( new StringEntity( jstrProxyMessage, "UTF-8") );
			
			response = httpclient.execute( httpPost );
		    
			HttpEntity entity = response.getEntity();
			String resBody = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			
			logMap.put("target", providerUrl);
			logMap.put("message", jstrProxyMessage);
			logMap.put("status", response.getStatusLine().toString() );
			logMap.put("body", resBody);
			
		} catch (ClientProtocolException cpe) {
			logMap.put("error", "ProxySender.sendProvider() ClientProtocolException error.");
			pushTransmitRepository.updatePushTransmitReqErrMsg( new PushTransmitReq(transmitReqId, "ProxySender.sendProvider() ClientProtocolException error.") );
			cpe.printStackTrace();
		} catch (IOException ioe) {
			logMap.put("error", "ProxySender.sendProvider() IOException error.");
			pushTransmitRepository.updatePushTransmitReqErrMsg( new PushTransmitReq(transmitReqId, "ProxySender.sendProvider() IOException error.") );
			ioe.printStackTrace();
		} catch (Exception e) {
			logMap.put("error", "ProxySender.sendProvider() Exception error.");
			pushTransmitRepository.updatePushTransmitReqErrMsg( new PushTransmitReq(transmitReqId, "ProxySender.sendProvider() Exception error.") );
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
			} catch(Exception ex) {
				logMap.put("error", "ProxySender.sendProvider() >> fcm response close Exception error.");
				pushTransmitRepository.updatePushTransmitReqErrMsg( new PushTransmitReq(transmitReqId, "ProxySender.sendProvider() >> fcm response close Exception error.") );
				ex.printStackTrace();
			}
			
			writeLog( logMap );
		}
	}
	
	private void writeLog( Map<String, String> logMap ) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append("\n");
		sb.append("+ provider target : ").append(logMap.get("target")).append("\n");
		sb.append("+ provider message : ").append(logMap.get("message")).append("\n");
		sb.append("+ provider response status : ").append(logMap.get("status")).append("\n");
		sb.append("+ provider response body : ").append(logMap.get("body")).append("\n");
		sb.append("+ provider error : ").append(logMap.get("error")).append("\n");
		sb.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append("\n");
		
		log.info(sb.toString());
	}

}
