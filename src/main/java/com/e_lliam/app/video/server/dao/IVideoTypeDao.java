package com.e_lliam.app.video.server.dao;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.e_lliam.app.video.server.entity.VideoTypeEntity;

public interface IVideoTypeDao extends JpaRepository<VideoTypeEntity, Long>{
	Collection<VideoTypeEntity> findByTypeParentIsNull();
	
	Collection<VideoTypeEntity> findByTypeParent(Long typeParent);
	
	@Query("select v from VideoTypeEntity v where v.typeParent=(select p.typeId from VideoTypeEntity p where p.typeName = ?)")
	Collection<VideoTypeEntity> findByTypeParentName(String videoTypeName);

	VideoTypeEntity findByTypeName(String string);
}
