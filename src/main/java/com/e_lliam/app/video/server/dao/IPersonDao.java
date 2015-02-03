package com.e_lliam.app.video.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_lliam.app.video.server.dao.custom.IPersonDaoCustom;
import com.e_lliam.app.video.server.entity.PersonEntity;

public interface IPersonDao extends JpaRepository<PersonEntity, Long>,IPersonDaoCustom{
	
	PersonEntity findByPersonId(Long personId);
	PersonEntity findByUserName(String userName);
	
}
