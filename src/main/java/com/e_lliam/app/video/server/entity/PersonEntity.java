package com.e_lliam.app.video.server.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.beans.factory.annotation.Qualifier;
/**
 * 注册用户信息
 * @author Landy
 *
 */
@Entity
public class PersonEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long personId;
	/**
	 * 账号
	 */
    @Column(length = 30)
	private String userName;
	/**
	 * 密码
	 */
    @Column(length = 50,updatable = false)
	private String passWord;
	/**
	 * 手机编号(备用)
	 */
    @Column(length = 50)
	private String mobileSerial;
	/**
	 * 手机号
	 */
    @Column(length = 11)
	private String mobileNumber;
	/**
	 * 性别
	 */
	private Boolean gender;
	/**
	 * 昵称
	 */
    @Column(length = 20)
	private String nickName;
	/**
	 * 个人简介
	 */
    @Column(length = 200)
	private String personDesc;
	/**
	 * 生日
	 */
    @Column(length = 8)
	private String birthday;
	/**
	 * 头像
	 */
	private String photoUrl;
	/**
	 * 状态
	 */
    @Column(length = 2)
	private Integer status;
	/**
	 * 注册时间
	 */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
	private Date createTime;
	public Long getPersonId() {
		return personId;
	}
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMobileSerial() {
		return mobileSerial;
	}
	public void setMobileSerial(String mobileSerial) {
		this.mobileSerial = mobileSerial;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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
	public String getPersonDesc() {
		return personDesc;
	}
	public void setPersonDesc(String personDesc) {
		this.personDesc = personDesc;
	}
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
