package com.e_lliam.app.video.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.e_lliam.app.video.server.dao.custom.IBusinessNewsDaoCustom;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;

public interface IBusinessNewsDao extends JpaRepository<BusinessNewsEntity, Long>,IBusinessNewsDaoCustom{
	@Query(nativeQuery=true,value="select v.* from BusinessNewsEntity v inner join ToplineEntity t on v.newsId=t.dataId where t.topType=?1 order by t.topId desc")
	List<BusinessNewsEntity> getByTopline(int int1);
    Page<BusinessNewsEntity> getByNewsTitleIsNotNullOrderByNewsIdDesc(Pageable pageable);
}
