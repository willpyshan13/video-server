package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.dao.custom.IPushMessageDaoCustom;
import com.e_lliam.app.video.server.entity.PushMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: landy
 * Date: 13-10-15
 * Time: 上午11:35
 * To change this template use File | Settings | File Templates.
 */
public interface IPushMessageDao extends JpaRepository<PushMessageEntity, Long>,IPushMessageDaoCustom {

}
