package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.dao.custom.ITeamDaoCustom;
import com.e_lliam.app.video.server.entity.TeamEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITeamDao extends JpaRepository<TeamEntity, Long>,ITeamDaoCustom{
    Page<TeamEntity> findByTeamTypeIdOrderByTeamIdDesc(Pageable pageable,Long teamType);
    Page<TeamEntity> findByTeamTitleIsNotNullOrderByTeamIdDesc(Pageable pageable);
}
