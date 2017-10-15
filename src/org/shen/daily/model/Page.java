package org.shen.daily.model;

import java.util.List;

/**
 * @author ZhiqiangShen
 * 分页实体类
 * @param <T>
 */
public class Page<T>{
	
	/**
	 * 当前页码
	 */
	private int currentPage;
	
	/**
	 * 一页多少条记录
	 */
	private int pageSize;
	
	/**
	 * 总共多少页
	 */
	private int pageNum;
	
	/**
	 * 总共多少条记录 
	 */
	private int totalNum;
	
	/**
	 * 当前页对应的数据列表
	 */
	private List<T> list;


	

	public Page(int currentPage, int pageSize, int totalNum, List<T> list) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalNum = totalNum;
		this.pageNum=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		this.list = list;
	}

	public Page() {
		super();
	}
	
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

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [currentPage=" + currentPage + ", pageSize=" + pageSize + ", pageNum=" + pageNum + ", totalNum="
				+ totalNum + ", list=" + list + "]";
	}
	
	
	
	
}
