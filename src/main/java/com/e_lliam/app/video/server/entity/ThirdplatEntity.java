package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * @author yushan.peng
 *
 * @Description: 第三方登录实体类
 * @since 2013年10月17日
 * @version V1.0
 */
@Entity
public class ThirdplatEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long platId;
    private String thirdId;
    private Long personId;
    private String platform;
    private Date createTime;
	public Long getPlatId() {
		return platId;
	}
	public void setPlatId(Long platId) {
		this.platId = platId;
	}
	public String getThirdId() {
		return thirdId;
	}
	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
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
