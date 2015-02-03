package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 视频评论
 * @author Landy
 *
 */
@Entity
public class CommentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long commentId;
	/**
	 * 用户编号
	 */
	private Long personId;
    /**
     *用户名
     */
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
	 * 评论内容
	 */
	private String commentContent;
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
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
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
