package com.e_lliam.app.video.server.pojo.extjs;

import java.util.List;

public class ExtFilters {
	private List<ExtFilter> filters;

	public ExtFilters() {
		super();
	}
	public ExtFilters(List<ExtFilter> filters) {
		super();
		this.filters = filters;
	}
	public List<ExtFilter> getFilters() {
		return filters;
	}

	public void setFilters(List<ExtFilter> filters) {
		this.filters = filters;
	}
}
