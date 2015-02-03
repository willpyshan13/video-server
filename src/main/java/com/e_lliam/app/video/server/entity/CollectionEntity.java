package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 视频收藏
 * @author Landy
 *
 */
@Entity
public class CollectionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long collectionId;
	/**
	 * 用户编号
	 */
	private Long personId;
    /**
     * 账号
     */
    @Column(length = 30)
    private String userName;
    /**
     * 视频编号
     */
    private Long videoId;
    /**
     * 视频标题
     */
    @Column(length=50)
    private String videoTitle;
	/**
	 * 创建时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;

	public Long getCollectionId() {
		return collectionId;
	}
	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}