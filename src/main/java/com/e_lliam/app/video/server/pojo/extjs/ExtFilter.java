package com.e_lliam.app.video.server.pojo.extjs;

public class ExtFilter {
	private String property;
	private Object value;
	public ExtFilter(String property, Object value) {
		super();
		this.property = property;
		this.value = value;
	}
	public ExtFilter() {
		super();
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public Object getValue() {
		return value;
	}
	public int getInt(){
		return Integer.parseInt(value.toString());
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
}
