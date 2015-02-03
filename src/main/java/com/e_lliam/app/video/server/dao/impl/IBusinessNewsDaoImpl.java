package com.e_lliam.app.video.server.dao.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.e_lliam.app.video.server.dao.custom.IBusinessNewsDaoCustom;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.pojo.ToplineType;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.SqlUtils;
import com.google.common.collect.Lists;
import org.springframework.data.domain.Sort;

public class IBusinessNewsDaoImpl implements IBusinessNewsDaoCustom{
	private static final List<String> NumberFields=Arrays.asList("newsId","videoId","BusinessNewsEntity","status");
	private static final List<String> StringFields=Arrays.asList("newsTitle","newsContent");
	private List<ExtFilter> dbFilter(List<ExtFilter> filters) {
		List<ExtFilter> result=Lists.newArrayList();
		for(ExtFilter f:filters){
			if(f.getProperty().equalsIgnoreCase("keyword")){
				for(String s:StringFields){
					result.add(new ExtFilter(s, f.getValue()));
				}
			}else{
				result.add(f);
			}
		}
		return result;
	}
	@PersistenceContext
	private EntityManager em;
	@Override
	public Page<BusinessNewsEntity> findAll(ExtFilters filters, Pageable pr) {
		List<ExtFilter> nfilters=dbFilter(filters.getFilters());
		
		String from="from  BusinessNewsEntity b left join (select * from ToplineEntity where topType="+ToplineType.NewsWheel.toInt()+") t on b.newsId=t.dataId ";
		StringBuffer whereSb=new StringBuffer("where 1=1 ");
		StringBuffer likeSb=new StringBuffer();
		for(ExtFilter f:nfilters){
			if(NumberFields.contains(f.getProperty())){
				whereSb.append("and b."+f.getProperty()+"="+f.getValue()+" ");
			}else if(StringFields.contains(f.getProperty())){
				if(likeSb.length()>0){
					likeSb.append(" or ");
				}
				likeSb.append("b."+f.getProperty()+" like "+SqlUtils.toLikeWithEscapeChar(f.getValue().toString()));
			}
		}
		from+=whereSb.toString();
		if(likeSb.length()>0){
			from+=" and ("+likeSb.toString()+") ";
		}
		Long total=Long.valueOf(em.createNativeQuery("select count(*) "+from).getSingleResult().toString());

		if(total>0){
            Sort.Order order = null;
            if(pr.getSort()!=null){
                order=pr.getSort().iterator().next();
            }else{
                order=new Sort.Order(Sort.Direction.DESC, "newsId");
            }
            List<Object[]> objs=em.createNativeQuery("select b.*,t.topId as wheelId "+from+" order by "+order.getProperty()+" "+order.getDirection().name(), "NewsWithTopline").setFirstResult(pr.getOffset()).setMaxResults(pr.getPageSize()).getResultList();
			List<BusinessNewsEntity> data=Lists.newArrayList();
			for(Object[] tmp:objs){
				BusinessNewsEntity e=(BusinessNewsEntity)tmp[0];
				e.setWheel(tmp[1]!=null);
				data.add(e);
			}
		 	return new PageImpl<BusinessNewsEntity>(data, pr, total);
		}else{
			return new PageImpl<BusinessNewsEntity>(Collections.EMPTY_LIST, pr, total);
		}
	
	}
	
}
