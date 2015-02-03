package com.e_lliam.app.video.server.dao;

import java.util.List;

import com.e_lliam.app.video.server.dao.custom.IPraiseDaoCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.e_lliam.app.video.server.entity.PraiseEntity;

public interface IPraiseDao extends JpaRepository<PraiseEntity, Long>,IPraiseDaoCustom{
	List<PraiseEntity> findByVideoId(Long videoId);
	List<PraiseEntity> findByPersonIdAndVideoId(Long personId,Long videoId);
}
