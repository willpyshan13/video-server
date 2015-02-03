package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 用户反馈信息
 * @author Landy
 *
 */
@Entity
public class FeedbackEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long feedbackId;
	/**
	 * 反馈内容
	 */
    @Column(columnDefinition = "text")
	private String feedbackContent;
	/**
	 * 用户编号
	 */
	private Long personId;
    /**
     * 用户编号
     */
    private String username;
	/**
	 * 创建时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;
	public Long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackContent() {
		return feedbackContent;
	}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFeedbackContent(String feedbackContent) {
		this.feedbackContent = feedbackContent;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
