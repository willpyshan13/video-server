package com.e_lliam.app.video.server.utils;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
 * 
 * @author yushan.peng
 *
 * @Description: 邮箱验证 
 * @since 2013年10月6日
 * @version V1.0
 */
public class MyAuthenticator extends Authenticator {
	private String username;
	private String password;

	public MyAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}
