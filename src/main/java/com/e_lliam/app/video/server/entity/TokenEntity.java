package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

import com.e_lliam.app.video.server.utils.DateUtils;

@Entity
public class TokenEntity {
	@Id
	@GeneratedValue
	private Long tokenId;
	public TokenEntity() {
		super();
	}
	public TokenEntity(String token, Long personId) {
		super();
		this.token = token;
		this.personId = personId;
		this.updateTime=DateUtils.now();
	}
    @Column(length = 50)
	private String token;
	private Long personId;
    @Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;
	public Long getTokenId() {
		return tokenId;
	}
	public void setTokenId(Long tokenId) {
		this.tokenId = tokenId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
