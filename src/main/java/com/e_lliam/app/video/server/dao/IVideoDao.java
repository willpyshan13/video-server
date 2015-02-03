package com.e_lliam.app.video.server.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.e_lliam.app.video.server.dao.custom.IVideoDaoCustom;
import com.e_lliam.app.video.server.entity.VideoEntity;

public interface IVideoDao extends JpaRepository<VideoEntity, Long>,IVideoDaoCustom{
	 
	@Query(nativeQuery=true,value="select v.* from VideoEntity v inner join ToplineEntity t on v.videoId=t.dataId where t.topType=?1 order by t.topId desc")
	List<VideoEntity> getByTopline(Integer topType);
	
	VideoEntity findByVideoId(Long videoId);

    Page<VideoEntity> findByVideoTitleIsNotNullOrderByVideoIdDesc(Pageable pageable);

    @Query(nativeQuery=true,value="select v.* from VideoEntity v inner join HotHistoryEntity h on v.videoId=h.videoId where h.typeId=?1 order by h.count desc LIMIT 0,12")
    List<VideoEntity> getByHotHistory(Long typeId);

}
