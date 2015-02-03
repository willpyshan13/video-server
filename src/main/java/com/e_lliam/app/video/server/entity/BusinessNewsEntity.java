package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 业界新闻
 * @author Landy
 *
 */
@SqlResultSetMapping(name="NewsWithTopline",entities={
		@EntityResult(entityClass=BusinessNewsEntity.class)
},columns={
		@ColumnResult(name="wheelId")
})
@Entity
public class BusinessNewsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long newsId;
	@Transient
	private boolean wheel;
	/**
	 * 新闻标题
	 */
    @Column(nullable = false)
	private String newsTitle;
	/**
	 * 新闻轮播图片
	 */
	private String newsWheelPicUrl;
	/**
	 * 新闻预览图片
	 */
	private String newsPreviewPicUrl;
	/**
	 * 新闻内容
	 */
    @Column(nullable = false,columnDefinition = "text")
	private String newsContent;
	/**
	 * 发布时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;
	/**
	 * 相关视频编号
	 */
	private Long videoId;
    /**
     * 相关视频编号
     */
    private String videoTitle;
	/**
	 * 状态
	 */
    @Column(length = 2)
	private Integer status;
	public Long getNewsId() {
		return newsId;
	}
	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}
	public String getNewsTitle() {
		return newsTitle;
	}
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	public String getNewsWheelPicUrl() {
		return newsWheelPicUrl;
	}
	public void setNewsWheelPicUrl(String newsWheelPicUrl) {
		this.newsWheelPicUrl = newsWheelPicUrl;
	}
	public String getNewsPreviewPicUrl() {
		return newsPreviewPicUrl;
	}
	public void setNewsPreviewPicUrl(String newsPreviewPicUrl) {
		this.newsPreviewPicUrl = newsPreviewPicUrl;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	public Long getVideoId() {
		return videoId;
	}
	public void setVideoId(Long videoId) {
		this.videoId = videoId;
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
	public boolean isWheel() {
		return wheel;
	}
	public void setWheel(boolean wheel) {
		this.wheel = wheel;
	}

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }
}
