package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.e_lliam.app.video.server.dao.*;
import com.e_lliam.app.video.server.entity.HotHistoryEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.entity.ToplineEntity;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.entity.VideoTypeRecordEntity;
import com.e_lliam.app.video.server.pojo.ToplineType;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.pojo.extjs.ExtSort;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/ext")
public class VideoController {
    private static final TypeReference<List<VideoEntity>> VALUE_TYPE_REF = new TypeReference<List<VideoEntity>>() {
    };
    private static final Log LOG = LogFactory.getLog(VideoController.class);
    @Resource
    private IVideoDao videoDao;
    @Resource
    private IVideoTypeRecordDao videoTypeRecordDao;

    @Resource
    private IHotHistoryDao hotHistoryDao;

    @Resource
    private IPraiseDao praiseDao;

    @Resource
    private ICollectionDao collectionDao;

    @Resource
    private IVideoHistoryDao videoHistoryDao;

    @Resource
    private IVideoUrlDao videoUrlDao;

    @Resource
    IVideoTypeRecordDao typeRecordDao;

    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping("/add/video")
    @ResponseBody
    public ExtData add(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoEntity> entities = objectMapper.readValue(body,
                VALUE_TYPE_REF);
        for (VideoEntity v : entities) {
            v.setVideoId(null);
            v.setCreateTime(DateUtils.now());
            VideoEntity videoEntity = videoDao.save(v);
            //保存到统计信息表格中
            HotHistoryEntity hotHistoryEntity = new HotHistoryEntity();
            hotHistoryEntity.setTypeId(14L);
            hotHistoryEntity.setVideoId(videoEntity.getVideoId());
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setCount(1);
            hotHistoryDao.save(hotHistoryEntity);

            for (Long typeId : v.getLabels()) {
                VideoTypeRecordEntity record = new VideoTypeRecordEntity();
                record.setTypeId(typeId);
                record.setVideoId(v.getVideoId());
                videoTypeRecordDao.save(record);
            }

        }
        return new ExtGridData(true, entities.size(), entities);
    }

    @RequestMapping("/remove/video")
    @ResponseBody
    public ExtData remove(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {

        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
        Long videoId;
        for (VideoEntity videoEntity : list) {
            videoId = videoEntity.getVideoId();
            videoHistoryDao.delete(videoHistoryDao.findByVideoId(videoId));
            videoUrlDao.delete(videoUrlDao.findByVideoId(videoId));
            videoTypeRecordDao.delete(videoTypeRecordDao.findByVideoId(videoId));
            hotHistoryDao.delete(hotHistoryDao.findByVideoId(videoId));
        }
        videoDao.delete(list);
        return new ExtGridData(true, list.size(), list);
    }

    @RequestMapping("/update/video")
    @ResponseBody
    public ExtData update(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoEntity> entities = objectMapper.readValue(body,
                VALUE_TYPE_REF);
        for (VideoEntity v : entities) {
            videoDao.save(v);
            if (different(videoTypeRecordDao.findByVideoId(v.getVideoId()),
                    v.getLabels())) {
                // 如果标签有改动,则全部删除,重新插入
                videoTypeRecordDao.deleteByVideoId(v.getVideoId());
                for (Long typeId : v.getLabels()) {
                    VideoTypeRecordEntity record = new VideoTypeRecordEntity();
                    record.setTypeId(typeId);
                    record.setVideoId(v.getVideoId());
                    videoTypeRecordDao.save(record);
                }
            }

        }
        return new ExtGridData(true, entities.size(), entities);
    }

    private boolean different(Collection<VideoTypeRecordEntity> findByVideoId,
                              List<Long> labels) {
        if (findByVideoId.size() == labels.size()) {
            for (VideoTypeRecordEntity e : findByVideoId) {
                if (!labels.contains(e.getTypeId())) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    @RequestMapping("/load/video")
    @ResponseBody
    public ExtData load(
            Pageable pr,
            ExtFilters filters) throws JsonParseException, JsonMappingException, IOException {

        Page<VideoEntity> pageData = videoDao.findVideoWithTopline(pr, filters);

        List<Long> videoIds = Lists.newArrayList();
        for (VideoEntity v : pageData.getContent()) {
            videoIds.add(v.getVideoId());
        }
        if (videoIds.size() > 0) {
            Collection<VideoTypeRecordEntity> records = videoTypeRecordDao
                    .findByVideoIdIn(videoIds);
            for (VideoEntity v : pageData.getContent()) {
                for (VideoTypeRecordEntity r : records) {
                    if (r.getVideoId().equals(v.getVideoId())) {
                        v.getLabels().add(r.getTypeId());
                    }
                }
                videoIds.add(v.getVideoId());
            }
        }

        return new ExtGridData(true, pageData.getTotalElements(),
                pageData.getContent());
    }

    @RequestMapping("/loadOne/video")
    @ResponseBody
    public ExtData loadOne(@RequestParam("id") Long id) {
        VideoEntity entity = videoDao.findOneVideoWhithTopLine(id);
        if (entity != null) {
            Collection<VideoTypeRecordEntity> labels = videoTypeRecordDao.findByVideoId(id);
            for (VideoTypeRecordEntity vtre : labels) {
                entity.getLabels().add(vtre.getTypeId());
            }
            return new ExtData(true, entity);
        } else {
            return ExtData.EmptyFailure;
        }
    }
}
