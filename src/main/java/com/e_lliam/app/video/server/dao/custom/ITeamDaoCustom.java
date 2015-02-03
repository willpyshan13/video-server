package com.e_lliam.app.video.server.dao.custom;

import com.e_lliam.app.video.server.entity.TeamEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITeamDaoCustom {
	Page<TeamEntity> findAllWithFilters(Pageable pr,
                                           ExtFilters filters);
	
}
