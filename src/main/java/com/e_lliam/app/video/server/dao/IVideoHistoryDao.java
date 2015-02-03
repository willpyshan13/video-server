package com.e_lliam.app.video.server.dao;

import java.util.List;

import com.e_lliam.app.video.server.dao.custom.IVideoHistoryDaoCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import com.e_lliam.app.video.server.entity.VideoHistoryEntity;

public interface IVideoHistoryDao extends JpaRepository<VideoHistoryEntity, Long>,IVideoHistoryDaoCustom {
	List<VideoHistoryEntity> findByPersonId(Long personId);
    List<VideoHistoryEntity> findByVideoId(Long videoId);
}
