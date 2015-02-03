package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.entity.CompanyInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICompanyInfoDao extends JpaRepository<CompanyInfoEntity, Long>{
}
