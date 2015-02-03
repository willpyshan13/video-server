package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.dao.custom.IFeedbackDaoCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.e_lliam.app.video.server.entity.FeedbackEntity;

public interface IFeedbackDao extends JpaRepository<FeedbackEntity, Long>,IFeedbackDaoCustom{
}
