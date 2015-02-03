package com.e_lliam.app.video.server.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.e_lliam.app.video.server.entity.VideoTypeRecordEntity;

public interface IVideoTypeRecordDao extends JpaRepository<VideoTypeRecordEntity, Long>{

	Collection<VideoTypeRecordEntity> findByVideoIdIn(List<Long> videoIds);
	Collection<VideoTypeRecordEntity> findByVideoId(Long videoId);
	@Modifying
	@Query("delete from VideoTypeRecordEntity r where r.videoId=?1")
	@Transactional
	void deleteByVideoId(Long videoId);
}
