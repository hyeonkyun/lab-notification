package kr.pe.jane.notification.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Page {

	@JsonIgnore
	private int offset;
	
	private int page;
	
	private int size;
	
	private int totalCount;
	
	private int totalPageCount;
	
	public Page() {
		this.page = 1;
		this.size = 10;
		this.offset = 0;
	}
	
	public Page( int _page, int _size ) {
		this.page = _page;
		this.size = _size;
		this.offset = ( _page - 1 ) * _size;
	}
	
	public void setTotalCount(int _totalCount) {
		this.totalCount = _totalCount;
		this.totalPageCount = (int) Math.ceil((double)_totalCount/size);
	}
}
