package com.e_lliam.app.video.server.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.e_lliam.app.video.server.entity.ToplineEntity;

public interface IToplineDao extends JpaRepository<ToplineEntity, Long>{

	Collection<ToplineEntity> findByTopTypeIn(List<Integer> types);
	List<ToplineEntity> findByTopTypeOrderByPriorityDesc(Integer type);
	
	@Modifying
	@Query("delete from ToplineEntity t where t.dataId=?1 and t.topType=?2")
	@Transactional
	void deleteByDataIdAndTopType(Long videoId, Integer videohot);

	Collection<ToplineEntity> findByDataId(Long videoId);
}
