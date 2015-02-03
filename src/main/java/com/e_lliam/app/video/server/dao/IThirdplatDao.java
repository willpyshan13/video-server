package com.e_lliam.app.video.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.e_lliam.app.video.server.entity.ThirdplatEntity;

public interface IThirdplatDao extends JpaRepository<ThirdplatEntity, Long>{
	@Modifying
	@Query("delete from TokenEntity t where t.personId=?1")
	void deleteByPersonId(Long personId);
	ThirdplatEntity findByThirdId(String thirdId);
	ThirdplatEntity findByPersonId(Long personId);
}
