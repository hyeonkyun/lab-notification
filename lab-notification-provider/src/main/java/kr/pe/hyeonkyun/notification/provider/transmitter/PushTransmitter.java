package kr.pe.hyeonkyun.notification.provider.transmitter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import kr.pe.hyeonkyun.notification.provider.callback.CallbackMessage;
import kr.pe.hyeonkyun.notification.provider.callback.CallbackSender;
import kr.pe.hyeonkyun.notification.provider.callback.task.CallbackTask;
import kr.pe.hyeonkyun.notification.provider.callback.task.CallbackTaskManager;
import kr.pe.hyeonkyun.notification.provider.common.utils.ConvertUtils;
import kr.pe.hyeonkyun.notification.provider.web.dto.PushTransmitParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class PushTransmitter implements InitializingBean {
	
	private RequestConfig requestConfig ; 
	
	private static final int socketTimeout = 10000 ;
	
	private static final int connectTimeout = 10000 ;
	
	private static final int connectionRequestTimeout = 10000 ;
	
	private static final String MESSAGE_KEY = "message";
	private static final String BASE_URL = "https://fcm.googleapis.com";
	private static final String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
	
	private static final String[] SCOPES = { MESSAGING_SCOPE };
	
	@Inject
	CallbackSender callbackSender;
	
	@Inject
	CallbackTaskManager callbackTaskManager;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.build();
	}

	public void sendMessage( PushTransmitParam pushTransmitParam ) {
		String payload = "";
		
		Map<String, String> logMap = new HashMap<String, String>();
		
		try {
			payload = buildMessagePayload( pushTransmitParam );
			logMap.put("payload", ConvertUtils.jsonStringToPrettyString( payload ));
		} catch (JsonProcessingException e) {		
			log.error( "PushTransmitter.sendMessage() >> fcm request body json parsing JsonProcessingException error." );
			e.printStackTrace();
			return;
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null;
		
		try {
			response = httpclient.execute( buildHttpPostFcmRequest( pushTransmitParam, payload ) );
		    
			HttpEntity entity = response.getEntity();
			String resBody = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			
			logMap.put("status", response.getStatusLine().toString() );
			logMap.put("body", resBody);
			
			if( StringUtils.hasText(pushTransmitParam.getCallbackUrl()) ) {
				CallbackMessage callbackMessage = new CallbackMessage();
				callbackMessage.setTransmitReqId( pushTransmitParam.getTransmitReqId() );
				callbackMessage.setToken( pushTransmitParam.getToken() );
				callbackMessage.setResponseStatusCd( response.getStatusLine().getStatusCode() );
				callbackMessage.setResponseData( resBody );
				
				callbackTaskManager.execute( new CallbackTask(pushTransmitParam, callbackMessage, callbackSender) );
			}
		} catch (ClientProtocolException cpe) {
			logMap.put("error", "PushTransmitter.sendMessage() ClientProtocolException error.");
			cpe.printStackTrace();
		} catch (IOException ioe) {
			logMap.put("error", "PushTransmitter.sendMessage() IOException error.");
			ioe.printStackTrace();
		} catch (Exception e) {
			logMap.put("error", "PushTransmitter.sendMessage() Exception error.");
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
			} catch(Exception ex) {
				logMap.put("error", "PushTransmitter.sendMessage() >> fcm response close Exception error.");
				ex.printStackTrace();
			}
			
			writeLog( logMap );
		}
	}
	
	private HttpPost buildHttpPostFcmRequest( PushTransmitParam pushTransmitParam, String payload ) throws IOException {
		
		HttpPost httpPost = new HttpPost(BASE_URL + "/v1/projects/" + pushTransmitParam.getProjectId() + "/messages:send");
		
		httpPost.setConfig(requestConfig);
		
		httpPost.setHeader("Authorization", "Bearer " + getAccessToken( pushTransmitParam.getCertification() ) );			
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");			
		
		httpPost.setEntity( new StringEntity(payload, "UTF-8") );
		
		return httpPost;
	}
	
	private String buildMessagePayload( PushTransmitParam pushTransmitParam ) throws JsonProcessingException {		
		
		Map<String, Object> payload = new HashMap<String, Object>();
		Map<String, Object> message = new HashMap<String, Object>();
		
		message.put( "notification" , pushTransmitParam.getNotification() );
		
		if( pushTransmitParam.getData() != null ) {
			message.put( "data" , pushTransmitParam.getData() );
		}
		
		if( pushTransmitParam.getWebpush() != null ) {
			message.put( "webpush" , pushTransmitParam.getWebpush() );
		}
				
		message.put( "token", pushTransmitParam.getToken() );
		
		payload.put( MESSAGE_KEY, message );
		
		return ConvertUtils.mapToJsonString( payload );
	}	
	
	private String getAccessToken( String serverKey ) throws IOException {
		GoogleCredential googleCredential = GoogleCredential.fromStream( new ByteArrayInputStream( serverKey.getBytes() ) ).createScoped( Arrays.asList(SCOPES) );
		googleCredential.refreshToken();
		String result = googleCredential.getAccessToken();
		
		return result;
	}
	
	private void writeLog( Map<String, String> logMap ) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append("\n");
		sb.append("+ pms target : fcm ").append("\n");
		sb.append("+ pms payload : ").append(logMap.get("payload")).append("\n");
		sb.append("+ pms response status : ").append(logMap.get("status")).append("\n");
		sb.append("+ pms response body : ").append(logMap.get("body")).append("\n");
		sb.append("+ pms error : ").append(logMap.get("error")).append("\n");
		sb.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++").append("\n");
		
		log.info(sb.toString());
	}
}
