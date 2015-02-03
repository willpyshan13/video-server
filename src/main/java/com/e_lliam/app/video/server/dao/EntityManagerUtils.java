package com.e_lliam.app.video.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class EntityManagerUtils implements IEntityManagerUtils{
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.e_lliam.app.video.server.dao.IEntityManagerUtils#persist(java.lang.Iterable)
	 */
	@Override
	@Transactional
	public void persist(Iterable<?> entities) {
		for(Object obj:entities){
			em.persist(obj);
		}
	}
	/* (non-Javadoc)
	 * @see com.e_lliam.app.video.server.dao.IEntityManagerUtils#remove(java.lang.Iterable, java.lang.Class)
	 */
	@Override
	@Transactional
	public void remove(Iterable<Long> ids,Class<?> cls) {
		System.out.println(".........."+ids);
		for(Long obj:ids){
			em.remove(em.getReference(cls, obj));
		}
	}
	/* (non-Javadoc)
	 * @see com.e_lliam.app.video.server.dao.IEntityManagerUtils#merge(java.lang.Iterable)
	 */
	@Override
	@Transactional
	public void merge(Iterable<?> entities) {
		for(Object obj:entities){
			em.merge(obj);
		}
	}
	/* (non-Javadoc)
	 * @see com.e_lliam.app.video.server.dao.IEntityManagerUtils#count(java.lang.Class)
	 */
	@Override
	@Transactional(readOnly=true)
	public Long count(Class<?> cls){
		return (Long)em.createQuery("select COUNT(p) from "+cls.getSimpleName()+" as p").getSingleResult();
	}
	/* (non-Javadoc)
	 * @see com.e_lliam.app.video.server.dao.IEntityManagerUtils#findPage(int, int, java.lang.Class)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<?> findPage(Pageable pr,Class<?> cls){
		return em.createQuery("select p from "+cls.getSimpleName()+" as p").setFirstResult(pr.getOffset()).setMaxResults(pr.getPageSize()).getResultList();
	}
	@Override
	public Object findOne(Long id, Class<?> cls) {
		return em.find(cls, id);
	}
	
}
