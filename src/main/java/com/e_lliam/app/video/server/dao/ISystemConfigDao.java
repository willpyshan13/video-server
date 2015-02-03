package com.e_lliam.app.video.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.e_lliam.app.video.server.entity.SystemConfigEntity;

public interface ISystemConfigDao extends JpaRepository<SystemConfigEntity, Long>{

}
