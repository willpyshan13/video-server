package com.e_lliam.app.video.server.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {

	static String usernameRex = "/^[a-zA-Z][a-zA-Z0-9_]*$/";
	// 以字母开头，长度在6-20之间，只能包含字符、数字和下划线
	static String passwordRex = "((?=.*\\d)(?=.*[a-z]).{6,20})";
	// 正确的邮箱地址
	static String emailRex = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$";

	/**
	 * 检查密码是否符合格式
	 * @param password
	 * @return
	 */
	public static boolean checkPassword(String password) {
		Pattern pattern = Pattern.compile(passwordRex);
		pattern.matcher(password);
		password.matches(passwordRex);
		return password.matches(passwordRex);
	}

	/**
	 * 去除html代码
	 * @param input
	 * @param length
	 * @return
	 */
	public static String splitAndFilterString(String input, int length) {      
        if (input == null || input.trim().equals("")) {      
            return "";      
        }      
        // 去掉所有html元素,      
        String str = input.replaceAll("//&[a-zA-Z]{1,10};", "").replaceAll(      
                "<[^>]*>", "");      
        str = str.replaceAll("[(/>)<]", "");      
        int len = str.length();      
        if (len <= length) {      
            return str;      
        } else {      
            str = str.substring(0, length);      
            str += "......";      
        }      
        return str;      
    } 

	/**
	 * 检查邮箱格式是否正确
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		return email.matches(emailRex);
	}

	public static void main(String[] args) {
		System.out.println(checkPassword("sd"));
		System.out.println(checkEmail("sdfadsd@fsdf.com"));
	}
	
	public static String getIpAddr(HttpServletRequest request) {  
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("PRoxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }  
	    return ip;  
	}  
}
