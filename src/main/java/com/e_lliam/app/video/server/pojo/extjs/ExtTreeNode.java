package com.e_lliam.app.video.server.pojo.extjs;

import java.util.Collection;
import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.e_lliam.app.video.server.utils.extjs.ExtTreeNodeSerializer;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@JsonSerialize(using=ExtTreeNodeSerializer.class)
public class ExtTreeNode {
	private boolean leaf;
	private boolean expanded;
	private Collection<ExtTreeNode> data;
	private Map<String, Object> others=Maps.newHashMap();
	
	public boolean isLeaf() {
		return leaf;
	}
	public Map<String, Object> getOthers() {
		return others;
	}
	public void setOthers(Map<String, Object> others) {
		this.others = others;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public void addChildren(ExtTreeNode node){
		if(this.data==null){
			this.data=Lists.newArrayList();
		}
		this.data.add(node);
	}
	public void put(String key,Object value){
		others.put(key, value);
	}
	public Object get(String key){
		return others.get(key);
	}
	public Collection<ExtTreeNode> getData() {
		return data;
	}
	public void setData(Collection<ExtTreeNode> data) {
		this.data = data;
	}
}
