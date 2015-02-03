package com.e_lliam.app.video.server.pojo.extjs;

public class ExtSort {
	private String direction;
	private String property;
	public ExtSort(String direction, String property) {
		super();
		this.direction = direction;
		this.property = property;
	}
	public ExtSort() {
		super();
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public boolean isAsc(){
		return direction==null||direction.equalsIgnoreCase("asc");
	}
	public boolean isDesc(){
		return direction.equalsIgnoreCase("desc");
	}
}
