package kr.pe.jane.lab.notification.provider.callback;

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
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import kr.pe.jane.lab.notification.provider.web.dto.PushTransmitParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CallbackSender implements InitializingBean {
	
	private RequestConfig requestConfig ; 
	
	private static final int socketTimeout = 10000 ;
	
	private static final int connectTimeout = 10000 ;
	
	private static final int connectionRequestTimeout = 10000 ;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout)
				.setConnectionRequestTimeout(connectionRequestTimeout)
				.build();
	}
	
	public void sendCallback( PushTransmitParam pushTransmitParam, CallbackMessage callbackMessage ) {
		CloseableHttpClient httpclient = HttpClients.createDefault(); 
		CloseableHttpResponse response = null;
		
		Gson gson = new Gson();
		String jstrCallbackMessage = gson.toJson(callbackMessage);
		
		Map<String, String> logMap = new HashMap<String, String>();
		
		try {
			HttpPost httpPost = new HttpPost( pushTransmitParam.getCallbackUrl() );
			
			httpPost.setConfig(requestConfig);
					
			httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");		
			httpPost.setEntity( new StringEntity( jstrCallbackMessage, "UTF-8") );
			
			response = httpclient.execute( httpPost );
		    
			HttpEntity entity = response.getEntity();
			String resBody = EntityUtils.toString(entity);
			EntityUtils.consume(entity);
			
			logMap.put("target", pushTransmitParam.getCallbackUrl());
			logMap.put("message", jstrCallbackMessage);
			logMap.put("status", response.getStatusLine().toString() );
			logMap.put("body", resBody);
			
		} catch (ClientProtocolException cpe) {
			logMap.put("error", "CallbackSender.sendCallback() ClientProtocolException error.");
			cpe.printStackTrace();
		} catch (IOException ioe) {
			logMap.put("error", "CallbackSender.sendCallback() IOException error.");
			ioe.printStackTrace();
		} catch (Exception e) {
			logMap.put("error", "CallbackSender.sendCallback() Exception error.");
			e.printStackTrace();
		} finally {
			try {
				if(response != null) response.close();
			} catch(Exception ex) {
				logMap.put("error", "CallbackSender.sendCallback() >> fcm response close Exception error.");
				ex.printStackTrace();
			}
			
			writeLog( logMap );
		}
	}
	
	private void writeLog( Map<String, String> logMap ) {
		StringBuilder sb = new StringBuilder();
		sb.append("\n");
		sb.append("-------------------------------------------------------------------").append("\n");
		sb.append("- callback target : ").append(logMap.get("target")).append("\n");
		sb.append("- callback message : ").append(logMap.get("message")).append("\n");
		sb.append("- callback response status : ").append(logMap.get("status")).append("\n");
		sb.append("- callback response body : ").append(logMap.get("body")).append("\n");
		sb.append("- callback error : ").append(logMap.get("error")).append("\n");
		sb.append("-------------------------------------------------------------------").append("\n");
		
		log.info(sb.toString());
	}
}
