package com.e_lliam.app.video.server.dao.impl;

import java.math.BigInteger;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.e_lliam.app.video.server.dao.custom.IVideoDaoCustom;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.pojo.ToplineType;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.SqlUtils;
import com.google.common.collect.Lists;

public class IVideoDaoImpl implements IVideoDaoCustom {
	private static final String TYPE_ID = "typeId";

	@PersistenceContext
	private EntityManager em;
	
	private static final List<String> NumberFields=Arrays.asList("videoId","videoYear","videoRegion","status");
	private static final List<String> StringFields=Arrays.asList("videoTitle","videoBrief","videoDesc","videoDirector","videoScriptwriter","videoActor");
	private List<ExtFilter> dbfilter(List<ExtFilter> olds) {
		List<ExtFilter> result=Lists.newArrayList();
		for(ExtFilter o:olds){
			if(o.getProperty().equalsIgnoreCase("keyword")){
				for(String kw:StringFields){
					result.add(new ExtFilter(kw	, o.getValue()));
				}
			}else if(o.getProperty().equalsIgnoreCase("videoLabel")){
				result.add(new ExtFilter(TYPE_ID, o.getValue()));
			}
			else{
				result.add(o);
			}
		}
		return result;
	}
	@Override
	public Page<VideoEntity> findVideoWithTopline(Pageable p,ExtFilters filters) {
		List<ExtFilter> nfilters=dbfilter(filters.getFilters());
		String from="from VideoEntity v left join (select * from ToplineEntity where topType="+ToplineType.VideoHot.toInt()+") t1 on v.videoId=t1.dataId left join (select * from ToplineEntity where topType="+ToplineType.VideoWheel.toInt()+") t2 on v.videoId=t2.dataId";
		StringBuffer whereNumber=new StringBuffer(" where 1=1 ");
		StringBuffer whereString=new StringBuffer();
		for(ExtFilter f:nfilters){
			if(NumberFields.contains(f.getProperty())){
				whereNumber.append(" and v."+f.getProperty()+"="+f.getValue());
			}else if(f.getProperty().equalsIgnoreCase(TYPE_ID)){
				from+=" inner join VideoTypeRecordEntity r on v.videoId=r.videoId";
				whereNumber.append(" and r."+TYPE_ID+"="+f.getValue());
			}else if(StringFields.contains(f.getProperty())){
				if(whereString.length()!=0){
					whereString.append(" or ");
				}
				whereString.append(f.getProperty()+" like '%"+SqlUtils.escapeLike(f.getValue().toString())+"%' "+SqlUtils.ESCAPE_STR);
			}
		}
		from+=whereNumber.toString();
		if(whereString.length()>0){
			from+=" and ("+whereString.toString()+")";
		}
		Long total=Long.valueOf(em.createNativeQuery("select count(*) "+from).getSingleResult().toString());
		if(total>0){
		Order order = null;
		if(p.getSort()!=null){
			order=p.getSort().iterator().next();
		}else{
			order=new Order(Direction.DESC, "videoId");
		}
		Query q=em.createNativeQuery("select v.*,t1.topId as hotId ,t2.topId as wheelId "+from+" order by "+order.getProperty()+" "+order.getDirection().name(),"VideoWithTopline");
		q.setFirstResult(p.getOffset()).setMaxResults(p.getPageSize());
		List<VideoEntity> result=new ArrayList<VideoEntity>();
		List<Object[]> list=q.getResultList();
		for(Object[] os:list){
			VideoEntity ve=(VideoEntity) os[0];
			ve.setHot(os[1]!=null);
			ve.setWheel(os[2]!=null);
			
			result.add(ve);
		}
		return new PageImpl<VideoEntity>(result, p, total);
		}else{
			return new PageImpl<VideoEntity>(Collections.EMPTY_LIST, p, 0);
			
		}
	}

	@Override
	public VideoEntity findOneVideoWhithTopLine(Long id) {
		String from="from VideoEntity v left join (select * from ToplineEntity where topType="+ToplineType.VideoHot.toInt()+") t1 on v.videoId=t1.dataId left join (select * from ToplineEntity where topType="+ToplineType.VideoWheel.toInt()+") t2 on v.videoId=t2.dataId where v.videoId="+id;
		Query q=em.createNativeQuery("select v.*,t1.topId as hotId ,t2.topId as wheelId "+from,"VideoWithTopline");
		Object[] os=(Object[])q.getSingleResult();
		if(os!=null){
			VideoEntity ve=(VideoEntity) os[0];
			ve.setHot(os[1]!=null);
			ve.setWheel(os[2]!=null);
			return ve;
		}else{
			return null;
		}
			
	}

    @Override
    public Page<VideoEntity> findVideoWithHotHistory(Pageable page,Long typeId,String date) {
        String dayFrom = " from VideoEntity v inner join HotHistoryEntity h on v.videoId=h.videoId WHERE h.typeId = 10 and h.currentDay="+date+" order by h.todayCount desc";
        String weekFrom = "from VideoEntity v inner join HotHistoryEntity h on v.videoId=h.videoId WHERE h.typeId = 10 and h.currrentWeek="+date+" order by h.weekCount desc";
        String monthFrom = "from VideoEntity v inner join HotHistoryEntity h on v.videoId=h.videoId where h.typeId = 10 and h.currrentMonth="+date+" order by h.monthCount desc";
        String countFrom = "from VideoEntity v inner join HotHistoryEntity h on v.videoId=h.videoId where h.typeId = 10 order by h.count desc";
        Query q = null;
        if (typeId == 10){
             q = em.createNativeQuery("select v.* "+dayFrom);
        }else if (typeId==11){
            q = em.createNativeQuery("select v.* "+weekFrom);
        }else if(typeId ==12){
            q = em.createNativeQuery("select v.* "+monthFrom);
        }else if(typeId == 13){
            q = em.createNativeQuery("select v.* "+countFrom);
        }
        q.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize());
        List<VideoEntity> result=new ArrayList<VideoEntity>();
        List<Object[]> list=q.getResultList();
        VideoEntity ve;
        for(Object[] os:list){
            ve=new VideoEntity();
            ve.setCreateTime((Date)os[1]);
            ve.setVideoId(((BigInteger)os[0]).longValue());
            ve.setVideoTitle((String) os[10]);
            ve.setVideoBrief((String) os[4]);
            ve.setVideoPreviewPicUrl((String)os[7]);
            ve.setVideoRegion(((BigInteger)os[8]).longValue());
            ve.setVideoYear(((BigInteger)os[12]).longValue());
            result.add(ve);
        }
        return new PageImpl<VideoEntity>(result, page , page.getPageNumber());
    }
}
