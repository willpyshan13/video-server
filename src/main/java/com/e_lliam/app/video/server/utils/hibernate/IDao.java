package com.e_lliam.app.video.server.utils.hibernate;

import java.io.Serializable;
import java.util.List;

public interface IDao<T> {
	Serializable save(T obj);
	void update(T obj);
	void saveOrUpdate(T obj);
	void delete(Long uid);
	T findById(Long uid);
	List<T> findAll();
	int findCount();
	List<T> findPage(int start,int size);
}
