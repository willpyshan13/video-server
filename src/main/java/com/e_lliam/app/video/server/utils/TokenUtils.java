package com.e_lliam.app.video.server.utils;

import java.util.UUID;

public class TokenUtils {
	public static String makeToken(){
		return UUID.randomUUID().toString();
	}
}
