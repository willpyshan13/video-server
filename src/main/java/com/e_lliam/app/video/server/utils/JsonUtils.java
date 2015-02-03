package com.e_lliam.app.video.server.utils;

public class JsonUtils {
	public static String toArrayStr(String body){
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		return body;
	}
}
