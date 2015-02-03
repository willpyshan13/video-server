package com.e_lliam.app.video.server.dao.custom;

import com.e_lliam.app.video.server.entity.PersonEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;


public interface IPersonDaoCustom {
	@Modifying
	@Transactional
	void updatePassword(Long Id,String password);
    Page<PersonEntity> findAllWithFilters(Pageable pr, ExtFilters filters);

}
