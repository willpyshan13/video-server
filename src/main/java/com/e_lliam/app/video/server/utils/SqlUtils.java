package com.e_lliam.app.video.server.utils;

import org.springframework.util.StringUtils;

public class SqlUtils {
	public static final char ESCAPE_CHAR='/';
	public static final String ESCAPE_STR=" ESCAPE '"+ESCAPE_CHAR+"' ";
	public static String escapeLike(String txt){
		if(StringUtils.hasText(txt)){
			txt=txt.replaceAll("'", "''");
			txt=txt.replaceAll("[\\[\\]_%]", ESCAPE_CHAR+"$0");
			return txt;
		}else{
			return "";
		}
	}
	public static String toLike(String txt){
		return "'%"+escapeLike(txt)+"%'";
	}
	public static String toLikeWithEscapeChar(String txt){
		return toLike(txt)+ESCAPE_STR;
	}
}
