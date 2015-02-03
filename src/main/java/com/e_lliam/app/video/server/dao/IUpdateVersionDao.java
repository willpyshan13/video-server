package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.entity.UpdateVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUpdateVersionDao extends JpaRepository<UpdateVersionEntity, Long>{

}
