package com.e_lliam.app.video.server.pojo.mobile;

import java.util.List;

public class VideoDetailBean {
	private Long videoId;
	private String videoTitle;
	private String videoPreviewPicUrl;
	private String videoDirector;
	private String videoActor;
	private String videoRegion;
	private String videoYear;
	private String videoDesc;
	private String videoPlayUrl;
	private boolean isCollect;
	private boolean isPraised;
	private Integer praise;
	private List<VideoUrlBean> list;
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
	public String getVideoDirector() {
		return videoDirector;
	}
	public void setVideoDirector(String videoDirector) {
		this.videoDirector = videoDirector;
	}
	public String getVideoActor() {
		return videoActor;
	}
	public void setVideoActor(String videoActor) {
		this.videoActor = videoActor;
	}
	public String getVideoRegion() {
		return videoRegion;
	}
	public void setVideoRegion(String videoRegion) {
		this.videoRegion = videoRegion;
	}
	public String getVideoYear() {
		return videoYear;
	}
	public void setVideoYear(String videoYear) {
		this.videoYear = videoYear;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	public String getVideoPlayUrl() {
		return videoPlayUrl;
	}
	public void setVideoPlayUrl(String videoPlayUrl) {
		this.videoPlayUrl = videoPlayUrl;
	}
	public boolean isCollect() {
		return isCollect;
	}
	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
	public boolean isPraised() {
		return isPraised;
	}
	public void setPraised(boolean isPraised) {
		this.isPraised = isPraised;
	}
	public List<VideoUrlBean> getList() {
		return list;
	}
	public void setList(List<VideoUrlBean> list) {
		this.list = list;
	}
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}

}
