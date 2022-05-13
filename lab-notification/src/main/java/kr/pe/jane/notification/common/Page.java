package kr.pe.jane.notification.common;

import lombok.Data;

@Data
public class Page {

	int offset;
	
	int size;
	
	int pageNo;
	
	public Page() {
		this.pageNo = 1;
		this.size = 10;
		this.offset = 0;
	}
	
	public Page( int _pageNo, int _size ) {
		this.pageNo = _pageNo;
		this.size = _size;
		this.offset = ( _pageNo - 1 ) * _size;
	}
}
