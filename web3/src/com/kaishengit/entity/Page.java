package com.kaishengit.entity;

import java.util.List;

public class Page<T> {
	
	//��ҳ��
	private int totalPage;
	//��ǰҳ��
	private int pageNo;
	//��ǰҳ����
	private List<T> items;
	//������
	private int totals;
	//ÿҳ��ʾ������
	private int pageSize=5;
	//��ǰҳ����ʼ�к�
	private int start;
	
	
	public Page(int totals,int pageNo){
		if(pageNo<1){
			pageNo=1;
		}
		this.totals=totals;
		
		//��ȡ��ҳ��
		totalPage=totals/pageSize;
		if(totals%pageSize!=0){
			totalPage++;
		}
		
		if(pageNo>totalPage){
			pageNo=totalPage;
		}
		this.pageNo = pageNo;
		
		//���㵱ǰҳ����ʼ����
		start = (pageNo-1)*pageSize;
	}
	
	
	
	
	
	
	
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
	public int getTotals() {
		return totals;
	}
	public void setTotals(int totals) {
		this.totals = totals;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
}
