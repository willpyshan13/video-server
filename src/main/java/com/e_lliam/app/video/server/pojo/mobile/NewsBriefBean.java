package com.e_lliam.app.video.server.pojo.mobile;
/**
 * 
 * @author yushan.peng
 *
 * @Description: 新闻轮播
 * @since 2013年9月24日
 * @version V1.0
 */
public class NewsBriefBean {
	private Long newsId;
	private String newsTitle;
	private String newsWheelPicUrl;
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
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
}
