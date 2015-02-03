package com.e_lliam.app.video.server.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.google.common.collect.Lists;

/**
 * 视频
 * @author Landy
 *
 */
@SqlResultSetMapping(name="VideoWithTopline",entities={
		@EntityResult(entityClass=VideoEntity.class)
},columns={
		@ColumnResult(name="hotId"),
		@ColumnResult(name="wheelId")
})
@Entity
public class VideoEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long videoId;
	/**
	 * 视频标题
	 */
    @Column(length=50)
	private String videoTitle;
	/**
	 * 轮播图片
	 */
	private String videoWheelPicUrl;
	/**
	 * 预览图片
	 */
	private String videoPreviewPicUrl;
	/**
	 * 简述
	 */
    @Column(length = 60)
	private String videoBrief;
	/**
	 * 影片介绍
	 */
    @Column(columnDefinition = "text")
	private String videoDesc;
	/**
	 * 导演
	 */
    @Column(length = 40)
	private String videoDirector;
	/**
	 * 编剧
	 */
    @Column(length = 40)
	private String videoScriptwriter;
	/**
	 * 演员
	 */
	private String videoActor;
	/**
	 * 区域
	 */
	private Long videoRegion;
	/**
	 * 年份
	 */
	private Long videoYear;
	/**
	 * 状态
	 */
    @Column(length = 2)
	private Integer status;
	/**
	 * 创建时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;
	@Transient
	private boolean hot=false;
	@Transient
	private boolean wheel=false;
	@Transient
	private List<Long> labels;
	
	public String getVideoDirector() {
		return videoDirector;
	}
	public void setVideoDirector(String videoDirector) {
		this.videoDirector = videoDirector;
	}
	public String getVideoScriptwriter() {
		return videoScriptwriter;
	}
	public void setVideoScriptwriter(String videoScriptwriter) {
		this.videoScriptwriter = videoScriptwriter;
	}
	public String getVideoActor() {
		return videoActor;
	}
	public void setVideoActor(String videoActor) {
		this.videoActor = videoActor;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getVideoWheelPicUrl() {
		return videoWheelPicUrl;
	}
	public void setVideoWheelPicUrl(String videoWheelPicUrl) {
		this.videoWheelPicUrl = videoWheelPicUrl;
	}
	public String getVideoPreviewPicUrl() {
		return videoPreviewPicUrl;
	}
	public void setVideoPreviewPicUrl(String videoPreviewPicUrl) {
		this.videoPreviewPicUrl = videoPreviewPicUrl;
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
	public String getVideoBrief() {
		return videoBrief;
	}
	public void setVideoBrief(String videoBrief) {
		this.videoBrief = videoBrief;
	}
	public String getVideoDesc() {
		return videoDesc;
	}
	public void setVideoDesc(String videoDesc) {
		this.videoDesc = videoDesc;
	}
	
	public List<Long> getLabels() {
		if(labels==null){
			labels=Lists.newArrayList();
		}
		return labels;
	}
	public void setLabels(List<Long> labels) {
		this.labels = labels;
	}
	public boolean isHot() {
		return hot;
	}
	public void setHot(boolean hot) {
		this.hot = hot;
	}
	public boolean isWheel() {
		return wheel;
	}
	public void setWheel(boolean wheel) {
		this.wheel = wheel;
	}
	public Long getVideoRegion() {
		return videoRegion;
	}
	public void setVideoRegion(Long videoRegion) {
		this.videoRegion = videoRegion;
	}
	public Long getVideoYear() {
		return videoYear;
	}
	public void setVideoYear(Long videoYear) {
		this.videoYear = videoYear;
	}
}
