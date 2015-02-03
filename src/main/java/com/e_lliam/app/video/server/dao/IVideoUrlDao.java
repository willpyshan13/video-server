package com.e_lliam.app.video.server.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_lliam.app.video.server.entity.VideoUrlEntity;

public interface IVideoUrlDao extends JpaRepository<VideoUrlEntity, Long>{
	Collection<VideoUrlEntity> findByVideoId(Long videoId);
	Collection<VideoUrlEntity> findByVideoIdOrderByVideoUrlIndexAsc(Long videoId);
}
