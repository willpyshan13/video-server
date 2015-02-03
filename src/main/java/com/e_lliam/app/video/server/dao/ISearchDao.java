package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.dao.custom.ISearchDaoCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.e_lliam.app.video.server.entity.SearchEntity;

public interface ISearchDao extends JpaRepository<SearchEntity, Long>,ISearchDaoCustom {
	SearchEntity findBySearchKey(String searchKey);
    Page<SearchEntity> findBySearchKeyIsNotNullOrderBySearchCountDesc(Pageable pageable);
}
