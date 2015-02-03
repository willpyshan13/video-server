package com.e_lliam.app.video.server.dao.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;

import java.util.List;

public interface IVideoDaoCustom {
	Page<VideoEntity> findVideoWithTopline(Pageable page, ExtFilters filters);
	VideoEntity findOneVideoWhithTopLine(Long id);
    Page<VideoEntity> findVideoWithHotHistory(Pageable page,Long typeId,String date);
}
