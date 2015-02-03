package com.e_lliam.app.video.server.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.e_lliam.app.video.server.dao.IBusinessNewsDao;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.DateUtils;

@Component
public class BusinessService {
	@Resource
	private IBusinessNewsDao businessNewsDao;

	public void add(List<BusinessNewsEntity> entities) {
		for(BusinessNewsEntity e:entities){
			e.setNewsId(null);
			e.setCreateTime(DateUtils.now());
		}
		businessNewsDao.save(entities);
	}

	public void delete(List<BusinessNewsEntity> list) {
		businessNewsDao.delete(list);
	}

	public Page<BusinessNewsEntity> findAll(ExtFilters filters,
			Pageable extPageRequest) {
		return businessNewsDao.findAll(filters,extPageRequest);
	}

	public BusinessNewsEntity findOne(Long id) {
		return businessNewsDao.findOne(id);
	}

	public void update(List<BusinessNewsEntity> entities) {
		businessNewsDao.save(entities);
	}
}
