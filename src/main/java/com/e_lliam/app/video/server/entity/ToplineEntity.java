package com.e_lliam.app.video.server.entity;

import javax.persistence.*;

@Entity
public class ToplineEntity {
	/**
	 * 头条编号
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long topId;
	/**
	 * 对应数据的编号
	 */
	private Long dataId;
	/**
	 * 头条类型
	 */
    @Column(length=1)
	private Integer topType;
	/**
	 * 优先级
	 */
    @Column(length = 2)
	private Integer priority;
	public Long getTopId() {
		return topId;
	}
	public void setTopId(Long topId) {
		this.topId = topId;
	}
	public Long getDataId() {
		return dataId;
	}
	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	public Integer getTopType() {
		return topType;
	}
	public void setTopType(Integer topType) {
		this.topType = topType;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
