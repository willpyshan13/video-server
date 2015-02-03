package com.e_lliam.app.video.server.utils.extjs;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class ExtPageRequest extends PageRequest {

	public ExtPageRequest(int page, int size) {
		super(page-1, size);
	}

	public ExtPageRequest(int page, int size, Direction direction,
			String... properties) {
		super(page-1, size, direction, properties);
	}

	public ExtPageRequest(int page, int size, Sort sort) {
		super(page-1, size, sort);
	}
	public int getExtPageNumber() {
		return super.getPageNumber()+1;
	}

}
