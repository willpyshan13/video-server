package com.e_lliam.app.video.server.dao.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;

public interface IBusinessNewsDaoCustom {
	
	Page<BusinessNewsEntity> findAll(ExtFilters filters,Pageable p);
}
