package com.e_lliam.app.video.server.pojo.extjs;


public class ExtGridData extends ExtData {
	private long total;
	
	public ExtGridData(boolean success, long total, Object datas) {
		super();
		this.setSuccess(success);
		this.total = total;
		this.data = datas;
	}
	public ExtGridData(boolean success,long total){
		this(success,total,null);
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
}
