package com.e_lliam.app.video.server.pojo.mobile;

import java.util.Date;

public class VideoRecordBean {
	private Long videoId;
	private String videoTitle;
	private String videoPreviewPicUrl;
	private Date createTime;
	private String videoRecord;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVideoRecord() {
		return videoRecord;
	}
	public void setVideoRecord(String videoRecord) {
		this.videoRecord = videoRecord;
	}
	
}
