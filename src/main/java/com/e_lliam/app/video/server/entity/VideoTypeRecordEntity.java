package com.e_lliam.app.video.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 视频分类记录(记录视频和分类之间的关系)
 * @author Landy
 *
 */
/**
 * @author Landy
 *
 */
@Entity
public class VideoTypeRecordEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long typeRecordId;
	/**
	 * 视频编号
	 */
	private Long videoId;
	
	/**
	 *视频类别编号 
	 */
	private Long typeId;
	public Long getTypeRecordId() {
		return typeRecordId;
	}
	public void setTypeRecordId(Long typeRecordId) {
		this.typeRecordId = typeRecordId;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}
