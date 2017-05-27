package com.hp.webedu.util;

import java.util.ArrayList;
import java.util.List;

public class PageResult {
	//总记录数
	private long totalCount;
	//当前页号
	private int pageNo;
	//总页数
	private int totalPageCount;
	
	//页大小
	private int pageSize;
	//列表记录
	private List items;
	//计算总页数
	public PageResult(long totalCount, int pageNo, int pageSize, List items) {
		//防止出现空指针异常
		this.items = items==null?new ArrayList():items;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		if(totalCount!=0)//计算总页数
		{
			int tem=(int)totalCount/pageSize;
			this.totalPageCount =totalCount%pageSize==0?tem:tem+1;
			this.pageNo=pageNo;
		}
		else
		{
			this.pageNo =0;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getPageSize() {
		if(pageSize<1) pageSize=1;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getItems() {
		return items;
	}
	public void setItems(List items) {
		this.items = items;
	}
 
}
