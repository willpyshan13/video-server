package com.e_lliam.app.video.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.e_lliam.app.video.server.dao.custom.ICommentDaoCustom;
import com.e_lliam.app.video.server.entity.CommentEntity;

public interface ICommentDao extends JpaRepository<CommentEntity, Long>,ICommentDaoCustom{

	Page<CommentEntity> findByCommentContentLike(String content,Pageable pr);
	List<CommentEntity> findByVideoId(Long videoId);
	List<CommentEntity> findByPersonId(Long personId);
	Page<CommentEntity> findByVideoId(Long videoId,Pageable pr);

}
