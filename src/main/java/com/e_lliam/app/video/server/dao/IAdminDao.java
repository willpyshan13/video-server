package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.entity.AdminEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: landy
 * Date: 13-10-15
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public interface IAdminDao extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByAdminName(String adminName);
    AdminEntity findByAdminNameAndAdminPass(String adminName,String adminPass);
    Page<AdminEntity> findByAdminNameNot(Pageable pageable,String adminName);
}
