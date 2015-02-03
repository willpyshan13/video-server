package com.e_lliam.app.video.server.dao.custom;

import com.e_lliam.app.video.server.entity.CollectionEntity;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICollectionDaoCustom {
	Page<CollectionEntity> findAllWithFilters(Pageable pr,
                                           ExtFilters filters);
	
}
