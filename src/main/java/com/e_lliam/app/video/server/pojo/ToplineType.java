package com.e_lliam.app.video.server.pojo;


public enum ToplineType {
	VideoWheel("wheel",0),VideoHot("hot",1),NewsWheel("news",2);
	private String key;
	private int value;
	private ToplineType(String key,int value){
		this.key=key;
		this.value=value;
	}
	public String getKey() {
		return key;
	}
	public int toInt() {
		return value;
	}
	public static boolean containsKey(String key){
		return getByKey(key)!=null;
	}
	private static  ToplineType getByKey(String key){
		for(ToplineType tt:ToplineType.values()){
			if(tt.getKey().equals(key)){
				return tt;
			}
		}
		return null;
	}
	public static int toInt(String key){
		return getByKey(key).toInt();
	}
}
