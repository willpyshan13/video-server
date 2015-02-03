package com.e_lliam.app.video.server.entity;

import javax.persistence.*;

/**
 *	播放地址
 * @author Landy
 *
 */
@Entity
public class VideoUrlEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long videoUrlId;
	/**
	 * 视频编号
	 */
	private Long videoId;
	/**
	 * 剧集编号
	 */
    @Column(length=3)
	private Integer videoUrlIndex;
	/**
	 * 剧集描述
	 */
    @Column(length = 50)
	private String videoUrlDesc;
	/**
	 * 视频播放地址
	 */
	private String videoPlayUrl;
	/**
	 * 视频原始网页地址
	 */
	private String videoWebUrl;
	/**
	 * 视频格式(MP4 ...)
	 */
    @Column(length = 10)
	private String videoFormat;
	/**
	 * 状态
	 */
    @Column(length = 2)
	private Integer status;
	public Long getVideoUrlId() {
		return videoUrlId;
	}
	public void setVideoUrlId(Long videoUrlId) {
		this.videoUrlId = videoUrlId;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Integer getVideoUrlIndex() {
		return videoUrlIndex;
	}
	public void setVideoUrlIndex(Integer videoUrlIndex) {
		this.videoUrlIndex = videoUrlIndex;
	}
	public String getVideoPlayUrl() {
		return videoPlayUrl;
	}
	public void setVideoPlayUrl(String videoPlayUrl) {
		this.videoPlayUrl = videoPlayUrl;
	}
	public String getVideoWebUrl() {
		return videoWebUrl;
	}
	public void setVideoWebUrl(String videoWebUrl) {
		this.videoWebUrl = videoWebUrl;
	}
	public String getVideoFormat() {
		return videoFormat;
	}
	public void setVideoFormat(String videoFormat) {
		this.videoFormat = videoFormat;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getVideoUrlDesc() {
		return videoUrlDesc;
	}
	public void setVideoUrlDesc(String videoUrlDesc) {
		this.videoUrlDesc = videoUrlDesc;
	}
}
