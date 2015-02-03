package com.e_lliam.app.video.server.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.entity.PersonEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.SqlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.e_lliam.app.video.server.dao.custom.IPersonDaoCustom;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IPersonDaoImpl implements IPersonDaoCustom {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void updatePassword(Long Id, String password) {
		String update="update PersonEntity set passWord=:passWord where personId=:personId";
		System.out.println(update);
		Query q=em.createNativeQuery(update).setParameter("passWord", password)
				.setParameter("personId", Id);
		q.executeUpdate();
	}

    private static final List<String> NumberFields= Arrays.asList("personId");
    private static final List<String> StringFields=Arrays.asList("userName","mobileSerial","mobileNumber","gender","nickName","birthday","createTime");

    @Override
    public Page<PersonEntity> findAllWithFilters(Pageable pr,ExtFilters filters) {
        String from="from PersonEntity ";
        StringBuffer whereSb=new StringBuffer("where 1=1 ");
        for(ExtFilter f:filters.getFilters()){
            if(NumberFields.contains(f.getProperty())){
                whereSb.append("and "+f.getProperty()+"="+f.getValue()+" ");
            }else if(StringFields.contains(f.getProperty())){
                whereSb.append("and "+f.getProperty()+" like "+ SqlUtils.toLikeWithEscapeChar(f.getValue().toString()));
            }
        }
        from+=whereSb.toString();
        Long total=Long.valueOf(em.createNativeQuery("select count(*) "+from).getSingleResult().toString());
        if(total>0){
            Sort.Order order = null;
            if(pr.getSort()!=null){
                order=pr.getSort().iterator().next();
            }else{
                order=new Sort.Order(Sort.Direction.DESC, "personId");
            }

            List<PersonEntity> data=em.createNativeQuery("select * "+from+" order by "+order.getProperty()+" "+order.getDirection().name(), PersonEntity.class).setFirstResult(pr.getOffset()).setMaxResults(pr.getPageSize()).getResultList();
            return new PageImpl<PersonEntity>(data, pr, total);
        }else{
            return new PageImpl<PersonEntity>(Collections.EMPTY_LIST, pr, total);
        }
    }
}
