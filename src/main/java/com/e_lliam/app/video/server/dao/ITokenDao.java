package com.e_lliam.app.video.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.e_lliam.app.video.server.entity.TokenEntity;

public interface ITokenDao extends JpaRepository<TokenEntity, Long> {
	@Modifying
	@Query("delete from TokenEntity t where t.personId=?1")
	void deleteByPersonId(Long personId);
	
	@Query("select t.personId from TokenEntity t where t.token=?1")
	Long getPersonIdByToken(String token);
	
	TokenEntity findByPersonId(Long personId);
}
