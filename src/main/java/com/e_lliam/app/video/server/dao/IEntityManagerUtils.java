package com.e_lliam.app.video.server.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;

public interface IEntityManagerUtils {

	public abstract void persist(Iterable<?> entities);

	public abstract void remove(Iterable<Long> ids, Class<?> cls);

	public abstract void merge(Iterable<?> entities);

	public abstract Long count(Class<?> cls);

	public abstract List<?> findPage(Pageable pr, Class<?> cls);
	
	public abstract Object findOne(Long id,Class<?> cls);

}