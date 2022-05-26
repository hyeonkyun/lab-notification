package kr.pe.jane.notification.web.dto;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class PushTraceParam {

	private String appId;
	
	private String accountId;
	
	private String fromDate;
	
	private String toDate;
	
	private int pageNo;
	
	private int size;
	
	private Page page;
	
	public String getFromDate() {
		if( this.fromDate != null ) return String.format( "%s 00:00:00", this.fromDate ); 
		return null;
	}
	
	public String getToDate() {
		if( this.toDate != null ) return String.format( "%s 23:59:59", this.toDate );
		return null;
	}
	
	public void toPage() {
		if ( this.pageNo == 0 ) this.pageNo = 1;
		if ( this.size == 0 ) this.size = 10;
		this.page = new Page( this.pageNo, this.size );
	}
	
	public boolean isVaild() {
		if ( !StringUtils.hasText( appId ) ) return false; 
		if ( page == null ) return false;
		return true;
	}
}
