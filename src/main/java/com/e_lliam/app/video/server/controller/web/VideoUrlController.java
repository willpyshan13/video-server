package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import com.e_lliam.app.video.server.utils.*;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.dao.IVideoUrlDao;
import com.e_lliam.app.video.server.entity.VideoUrlEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;

@Controller
@RequestMapping("/ext")
public class VideoUrlController {
    private static final TypeReference<List<VideoUrlEntity>> VALUE_TYPE_REF = new TypeReference<List<VideoUrlEntity>>() {
    };
    private static final Log LOG = LogFactory.getLog(VideoUrlController.class);
    @Resource
    private IVideoUrlDao videoUrlDao;

    @Resource
    private ObjectMapper objectMapper;

    @RequestMapping("/add/videoUrl")
    @ResponseBody
    public ExtData add(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoUrlEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        String urlFrom = entities.get(0).getVideoPlayUrl();
        String htmlUrl = entities.get(0).getVideoWebUrl();
        List<String> list = Lists.newArrayList();
        if (urlFrom.equals("优酷")) {
            list = GetYoukuMP4Path.getPath(htmlUrl);
        } else if (urlFrom.equals("乐视")) {
            try {
                list = GetLeTVPath.GetLeTV(htmlUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (urlFrom.equals("爱奇艺")) {
            list = GetAiqiyiMp4Path.getIQIYIUrl(htmlUrl);
        } else if (urlFrom.equals("搜狐")) {
            list = GetSouhuVideoMp4Path.GetSohuTV(htmlUrl);
        } else if (urlFrom.equals("腾讯")) {
            list = GetTencentMp4Path.getTencentUrl(htmlUrl);
        }
        if (list.size() > 0) {
            videoUrlDao.save(entities);
            return new ExtGridData(true, entities.size(), entities);
        } else {
            ExtData extData = new ExtData();
            extData.setSuccess(false);
            extData.setErrors("");
            return extData;
        }
    }

    @RequestMapping("/remove/videoUrl")
    @ResponseBody
    public ExtData remove(
            @RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {

        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoUrlEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
        videoUrlDao.delete(list);
        return new ExtGridData(true, list.size(), list);
    }

    @RequestMapping("/update/videoUrl")
    @ResponseBody
    public ExtData update(
            @RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        if (!body.startsWith("[")) {
            body = "[" + body + "]";
        }
        List<VideoUrlEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        String urlFrom = entities.get(0).getVideoPlayUrl();
        String htmlUrl = entities.get(0).getVideoWebUrl();
        List<String> list = Lists.newArrayList();
        if (urlFrom.equals("优酷")) {
            list = GetYoukuMP4Path.getPath(htmlUrl);
        } else if (urlFrom.equals("乐视")) {
            try {
                list = GetLeTVPath.GetLeTV(htmlUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (urlFrom.equals("爱奇艺")) {
            list = GetAiqiyiMp4Path.getIQIYIUrl(htmlUrl);
        } else if (urlFrom.equals("搜狐")) {
            list = GetSouhuVideoMp4Path.GetSohuTV(htmlUrl);
        } else if (urlFrom.equals("腾讯")) {
            list = GetTencentMp4Path.getTencentUrl(htmlUrl);
        }
        if (list.size() > 0) {
            videoUrlDao.save(entities);
            return new ExtGridData(true, entities.size(), entities);
        } else {
            ExtData extData = new ExtData();
            extData.setSuccess(false);
            extData.setErrors("");
            return extData;
        }
    }


    @RequestMapping("/load/videoUrl")
    @ResponseBody
    public ExtData load(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(required = false, value = "limit", defaultValue = "10") Integer pageSize,
            @RequestParam("videoId") Long videoId) {

        Collection<VideoUrlEntity> datas = videoUrlDao.findByVideoIdOrderByVideoUrlIndexAsc(videoId);
        return new ExtGridData(true, datas.size(), datas);
    }
}
