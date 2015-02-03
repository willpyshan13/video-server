package com.e_lliam.app.video.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SystemConfigEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long configId;
	/**
	 * 系统邮箱
	 **/
	private String systemEmail;
	/**
	 * 系统邮箱用户名
	 **/
	private String systemUsername;
	/**
	 * 系统邮箱密码
	 **/
	private String systemPassword;
	/**
	 * 邮件主题
	 **/
	private String emailSubject;
	/**
	 * 邮件服务器
	 **/
	private String serverSmtp;
	/**
	 * 邮件服务器端口号
	 **/
	private Integer serverPort;
	public Long getConfigId() {
		return configId;
	}
	public void setConfigId(Long configId) {
		this.configId = configId;
	}
	public String getSystemEmail() {
		return systemEmail;
	}
	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}
	public String getSystemUsername() {
		return systemUsername;
	}
	public void setSystemUsername(String systemUsername) {
		this.systemUsername = systemUsername;
	}
	public String getSystemPassword() {
		return systemPassword;
	}
	public void setSystemPassword(String systemPassword) {
		this.systemPassword = systemPassword;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getServerSmtp() {
		return serverSmtp;
	}
	public void setServerSmtp(String serverSmtp) {
		this.serverSmtp = serverSmtp;
	}
	public Integer getServerPort() {
		return serverPort;
	}
	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}
	
	
}
