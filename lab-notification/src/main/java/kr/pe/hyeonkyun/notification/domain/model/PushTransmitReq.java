package kr.pe.hyeonkyun.notification.domain.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PushTransmitReq {
	
	public PushTransmitReq( String transmitReqId, String errMsg ) {
		this.transmitReqId = transmitReqId;
		this.errMsg = errMsg;
	}

	private String transmitReqId;
	
	private String appId;
	
	private String transmitReqParam;
	
	private String reqIp;
	
	private Date reqDt;
	
	private String errMsg;
	
	private List<PushTransmit> pushTransmits;
		
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class PushTransmit {
		
		public PushTransmit(String _transmitReqId, String _target, String _targetType, String _accountId, String _reqData) {
			this.transmitReqId = _transmitReqId;
			this.target = _target;
			this.targetType = _targetType;
			this.accountId = _accountId;
			this.reqData = _reqData;
			this.reqDt = new Date();
		}
		
		private String transmitReqId;
		
		private String target;
		
		private String targetType;
		
		private String accountId;
		
		private String reqData;
		
		private int resStatusCd;
		
		private String resData;
		
		private Date reqDt;
		
		private Date endDt;
	}
}

