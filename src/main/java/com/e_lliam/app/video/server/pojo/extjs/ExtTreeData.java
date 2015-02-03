package com.e_lliam.app.video.server.pojo.extjs;

import java.util.Collection;

import com.google.common.collect.Lists;


public class ExtTreeData extends ExtData{
	private Collection<ExtTreeNode> data;
	public Collection<ExtTreeNode> getChildren() {
		return data;
	}
	public void setChildren(Collection<ExtTreeNode> children) {
		this.data = children;
	}
	public ExtTreeData() {
		super();
	}
	public ExtTreeData(boolean success) {
		super();
		this.setSuccess(success);
	}
	public ExtTreeData(boolean success,Collection<ExtTreeNode> children){
		this.setSuccess(success);
		this.setChildren(children);
	}
	
	public void addChildren(ExtTreeNode node){
		if(this.data==null){
			this.data=Lists.newArrayList();
		}
		this.data.add(node);
	}
}
