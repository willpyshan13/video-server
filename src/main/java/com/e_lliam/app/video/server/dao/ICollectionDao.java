package com.e_lliam.app.video.server.dao;

import java.util.List;

import com.e_lliam.app.video.server.dao.custom.ICollectionDaoCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.e_lliam.app.video.server.entity.CollectionEntity;

public interface ICollectionDao extends JpaRepository<CollectionEntity, Long>,ICollectionDaoCustom{
	Page<CollectionEntity> findByPersonId(Long personId,Pageable page);
	List<CollectionEntity> findByPersonId(Long personId);
	List<CollectionEntity> findByVideoId(Long videoId);
	List<CollectionEntity> findByPersonIdAndVideoId(Long personId,Long videoId);
}
