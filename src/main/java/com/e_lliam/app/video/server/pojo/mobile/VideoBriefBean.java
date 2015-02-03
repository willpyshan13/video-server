package com.e_lliam.app.video.server.pojo.mobile;
/**
 * 顶部轮播视频
 * @author Landy
 *
 */
public class VideoBriefBean {
	private Long videoId;
	private String picUrl;
	private String videoBrief;
	private String videoTitle;
	private Integer praise;
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	public Integer getComment() {
		return comment;
	}
	public void setComment(Integer comment) {
		this.comment = comment;
	}
	private Integer comment;
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getVideoBrief() {
		return videoBrief;
	}
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}
}
