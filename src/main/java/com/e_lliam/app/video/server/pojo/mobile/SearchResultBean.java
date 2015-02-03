package com.e_lliam.app.video.server.pojo.mobile;

public class SearchResultBean {
	private Long videoId;
	private String videoTitle;
	private String videoPreviewPicUrl;
	private String videoDirector;
	private String videoActor;
	private String videoRegion;
	private String videoYear;
	private Integer praise;
	private String videoBrief;
	private Integer commentNumber;
	
	public String getVideoBrief() {
		return videoBrief;
	}
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}
	public Integer getCommentNumber() {
		return commentNumber;
	}
	public void setCommentNumber(Integer commentNumber) {
		this.commentNumber = commentNumber;
	}
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
	public Integer getPraise() {
		return praise;
	}
	public void setPraise(Integer praise) {
		this.praise = praise;
	}
	
}
