package com.e_lliam.app.video.server.dao.impl;

import com.e_lliam.app.video.server.dao.custom.IVideoHistoryDaoCustom;
import com.e_lliam.app.video.server.entity.VideoHistoryEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.SqlUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IVideoHistoryDaoImpl implements IVideoHistoryDaoCustom {
	private static final List<String> NumberFields=Arrays.asList("historyId","personId","videoId");
	private static final List<String> StringFields=Arrays.asList("userName","videoTitle","createTime");
	@PersistenceContext
	private EntityManager em;
	@Override
	public Page<VideoHistoryEntity> findAllWithFilters(Pageable pr,
			ExtFilters filters) {
		String from="from VideoHistoryEntity ";
		StringBuffer whereSb=new StringBuffer("where 1=1 ");
		for(ExtFilter f:filters.getFilters()){
			if(NumberFields.contains(f.getProperty())){
				whereSb.append("and "+f.getProperty()+"="+f.getValue()+" ");
			}else if(StringFields.contains(f.getProperty())){
				whereSb.append("and "+f.getProperty()+" like "+SqlUtils.toLikeWithEscapeChar(f.getValue().toString()));
			}
		}
		from+=whereSb.toString();
		Long total=Long.valueOf(em.createNativeQuery("select count(*) "+from).getSingleResult().toString());
		if(total>0){
            Sort.Order order = null;
            if(pr.getSort()!=null){
                order=pr.getSort().iterator().next();
            }else{
                order=new Sort.Order(Sort.Direction.DESC, "historyId");
            }

		 	List<VideoHistoryEntity> data=em.createNativeQuery("select * "+from+" order by "+order.getProperty()+" "+order.getDirection().name(), VideoHistoryEntity.class).setFirstResult(pr.getOffset()).setMaxResults(pr.getPageSize()).getResultList();
		 	return new PageImpl<VideoHistoryEntity>(data, pr, total);
		}else{
			return new PageImpl<VideoHistoryEntity>(Collections.EMPTY_LIST, pr, total);
		}
	}

}
