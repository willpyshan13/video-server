package com.e_lliam.app.video.server.pojo.mobile;

public class CollectionBean {
	private Long videoId;
	private String videoTitle;
	private String videoPreviewPicUrl;
	private String videoLength;
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
	public String getVideoLength() {
		return videoLength;
	}
	public void setVideoLength(String videoLength) {
		this.videoLength = videoLength;
	}
	
}
