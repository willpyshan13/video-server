package com.e_lliam.app.video.server.utils.hibernate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateDaoBase<T> {
	@Autowired
	protected HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	protected Class<?> clazz;

	@SuppressWarnings("unchecked")
	protected HibernateDaoBase() {
		clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		//System.out.println(clazz.getSimpleName());
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	protected List<T> findPageByQuery(final String query, final int first,
			final int size) {
		return hibernateTemplate.execute(new HibernateCallback<List<T>>() {

			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(query).setFirstResult(first)
						.setMaxResults(size).list();
			}
		});
	}
	public void saveOrUpdate(T user) {
		hibernateTemplate.saveOrUpdate(user);
	}
	public void update(T user) {
		hibernateTemplate.update(user);
	}
	public Serializable save(T t){
		return hibernateTemplate.save(t);
	}

	public void delete(Long uid) {
		Object user = hibernateTemplate.get(this.clazz, uid);
		hibernateTemplate.delete(user);
	}

	@SuppressWarnings("unchecked")
	public T findById(Long uid) {
		return (T) hibernateTemplate.get(this.clazz, uid);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return hibernateTemplate.find("from "+this.clazz.getSimpleName());
	}

	public int findCount() {
		return hibernateTemplate.execute(new HibernateCallback<Integer>() {

			@Override
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				Object uniqueResult = session.createQuery("select count(*) from "+clazz.getSimpleName()).uniqueResult();
				return ((Number)uniqueResult).intValue();
			}
		});
	}

	public List<T> findPage(int start, int size) {
		return findPageByQuery("from "+this.clazz.getSimpleName(), start, size);
	}
}
