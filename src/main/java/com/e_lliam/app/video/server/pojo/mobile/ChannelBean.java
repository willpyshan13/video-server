package com.e_lliam.app.video.server.pojo.mobile;

public class ChannelBean {
	private Long videoId;
	private String videoTitle;
	private String videoPreviewPicUrl;
	private String videoBrief;
	private Integer priseNumber;
	private Integer collectionNumber;
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}
	public String getVideoPreviewPicUrl() {
		return videoPreviewPicUrl;
	}
	public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
		this.videoPreviewPicUrl = videoPreviewPicUrl;
	}
	public String getVideoBrief() {
		return videoBrief;
	}
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}
	public Integer getPriseNumber() {
		return priseNumber;
	}
	public void setPriseNumber(Integer priseNumber) {
		this.priseNumber = priseNumber;
	}
	public Integer getCollectionNumber() {
		return collectionNumber;
	}
	public void setCollectionNumber(Integer collectionNumber) {
		this.collectionNumber = collectionNumber;
	}
	
}
