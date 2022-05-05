package kr.pe.jane.notification.common.exception;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

public class PushException extends RuntimeException {

	private static final long serialVersionUID = 433512642694507943L;

	@Getter
	private String resultCode ;
	
	@Getter @Setter
	private String resultMessage ;
	
	public PushException() {
		super( PushError.toMessage(PushError.toMessage(PushError.UNKNOWN)) ) ;
		
		this.resultCode = PushError.UNKNOWN ;
		this.resultMessage = PushError.toMessage(resultCode) ;
	}

	public PushException( String resultCode ) {
		super( PushError.toMessage(resultCode) ) ;
		
		this.resultCode = resultCode ;
		this.resultMessage = PushError.toMessage(resultCode) ;
	}

	public PushException( String resultCode, String resultMessage ) {
		super(resultMessage) ;
		
		this.resultCode = resultCode ;
		this.resultMessage = resultMessage ;
	}
	
	public PushException( String resultCode, Exception e) {		
		super(e.getMessage(), e) ;
		
		this.resultCode = resultCode ;
		this.resultMessage = PushError.toMessage(resultCode) ;
	}
	
	public PushException( String resultCode, String resultMessage, Exception e) {		
		super(e.getMessage(), e) ;
		
		this.resultCode = resultCode ;
		this.resultMessage = resultMessage ;
	}
	
	public Map< String, String > toMap() {
		HashMap< String, String > map = new HashMap<>() ;
		
		map.put( "resultCode", this.resultCode) ;
		map.put( "resultMessage", this.resultMessage) ;
		
		return map ;
	}
}
