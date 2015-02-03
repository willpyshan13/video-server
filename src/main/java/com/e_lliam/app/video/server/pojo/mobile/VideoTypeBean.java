package com.e_lliam.app.video.server.pojo.mobile;

import java.util.Collection;

import com.e_lliam.app.video.server.entity.VideoTypeEntity;

public class VideoTypeBean {
	private Long typeId; 
	private String typeName;
	private String typeDesc;
	private Collection<VideoTypeEntity> list;
	
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public Collection<VideoTypeEntity> getList() {
		return list;
	}
	public void setList(Collection<VideoTypeEntity> list) {
		this.list = list;
	}
	
	
}
