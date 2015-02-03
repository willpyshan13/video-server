package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * 搜索记录
 * @author Landy
 *
 */
@Entity
public class SearchEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long searchId;
	/**
	 * 搜索关键字
	 */
    @Column(length=30)
	private String searchKey;
	/**
	 * 用户id
	 */
	private Long personId;
    /**统计**/
    private Integer searchCount;
	/**
	 * 搜索时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;
	public Long getSearchId() {
		return searchId;
	}
    public void setSearchId(Long searchId) {
        this.searchId = searchId;
    }
    public String getSearchKey() {
        return searchKey;
    }

    public Integer getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }

    public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
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
