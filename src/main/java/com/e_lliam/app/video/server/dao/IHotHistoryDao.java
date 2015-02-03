package com.e_lliam.app.video.server.dao;

import com.e_lliam.app.video.server.dao.custom.IHotHistoryDaoCustom;
import com.e_lliam.app.video.server.entity.HotHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHotHistoryDao extends JpaRepository<HotHistoryEntity, Long>,IHotHistoryDaoCustom {
       List<HotHistoryEntity> findByVideoId(Long videoId);
    HotHistoryEntity findByVideoIdAndTypeId(Long videoId,Long typeId);
    Page<HotHistoryEntity> findByTypeId(Pageable pageable,Long typeId);
}
