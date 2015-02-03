package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.entity.FeedbackEntity;
import com.e_lliam.app.video.server.entity.LoadingAnimationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILoadingAnimationDao extends JpaRepository<LoadingAnimationEntity, Long>{
}
