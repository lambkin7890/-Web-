package com.rental.page;

import org.springframework.stereotype.Component;

/**
 * 分页基本信息
 *
 */
@Component
public class Page {
	private int currentPage = 1;//当前页码
	
	private int pageSize;//每页显示数量
	
	private int offset;//对应数据库中的偏移量

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getOffset() {
		this.offset = (currentPage - 1) * pageSize;
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	
}
