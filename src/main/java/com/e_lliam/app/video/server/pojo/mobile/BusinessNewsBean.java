package com.e_lliam.app.video.server.pojo.mobile;

import java.util.Date;

public class BusinessNewsBean {
	private String newsTitle;
	private String newsWheelPicUrl;
	private String newsContent;
	private Date createTime;
	private String newsFrom;
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsWheelPicUrl() {
		return newsWheelPicUrl;
	}
	public void setNewsWheelPicUrl(String newsWheelPicUrl) {
		this.newsWheelPicUrl = newsWheelPicUrl;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getNewsFrom() {
		return newsFrom;
	}
	public void setNewsFrom(String newsFrom) {
		this.newsFrom = newsFrom;
	}
	
}
