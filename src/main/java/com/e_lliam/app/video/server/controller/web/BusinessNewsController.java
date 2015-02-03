package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import com.e_lliam.app.video.server.dao.IVideoDao;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
import com.google.common.collect.Lists;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.service.BusinessService;
import com.e_lliam.app.video.server.utils.JsonUtils;

@Controller
@RequestMapping("/ext")
public class BusinessNewsController {
    private static final TypeReference<List<BusinessNewsEntity>> VALUE_TYPE_REF = new TypeReference<List<BusinessNewsEntity>>() {
    };
    private static final Log LOG = LogFactory
            .getLog(BusinessNewsController.class);
    @Resource
    private ObjectMapper objectMapper;
    @Resource
    private BusinessService businessService;
    @Resource
    private IVideoDao videoDao;

    @RequestMapping("/add/businessNews")
    @ResponseBody
    public ExtData add(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        body = JsonUtils.toArrayStr(body);
        List<ExtFilter> list = Lists.newArrayList();
        ExtFilter filter;
        ExtFilters filters;
        Page<VideoEntity> page;
        List<BusinessNewsEntity> entities = objectMapper.readValue(body,
                VALUE_TYPE_REF);
        for (BusinessNewsEntity businessNewsEntity : entities) {
            filter = new ExtFilter("videoTitle", businessNewsEntity.getVideoTitle());
            list.add(filter);
            filters = new ExtFilters(list);
            page = videoDao.findVideoWithTopline(new ExtPageRequest(1, 5), filters);
            if (page.getNumberOfElements() > 0) {
                VideoEntity videoEntity = page.getContent().get(0);
                businessNewsEntity.setVideoTitle(videoEntity.getVideoTitle());
                businessNewsEntity.setVideoId(videoEntity.getVideoId());
            }
        }
        businessService.add(entities);
        return new ExtGridData(true, entities.size(), entities);
    }

    @RequestMapping(value = "/remove/businessNews")
    @ResponseBody
    public ExtData remove(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        body = JsonUtils.toArrayStr(body);
        List<BusinessNewsEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
        businessService.delete(list);
        return new ExtGridData(true, list.size(), list);
    }

    @RequestMapping("/update/businessNews")
    @ResponseBody
    public ExtData update(@RequestBody String body) throws JsonParseException,
            JsonMappingException, IOException {
        body = JsonUtils.toArrayStr(body);
        List<ExtFilter> list = Lists.newArrayList();
        ExtFilter filter;
        ExtFilters filters;
        Page<VideoEntity> page;
        List<BusinessNewsEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (BusinessNewsEntity businessNewsEntity : entities) {
            filter = new ExtFilter("videoTitle", businessNewsEntity.getVideoTitle());
            list.add(filter);
            filters = new ExtFilters(list);
            page = videoDao.findVideoWithTopline(new ExtPageRequest(1, 5), filters);
            if (page.getNumberOfElements() > 0) {
                VideoEntity videoEntity = page.getContent().get(0);
                businessNewsEntity.setVideoTitle(videoEntity.getVideoTitle());
                businessNewsEntity.setVideoId(videoEntity.getVideoId());
            }
        }
        businessService.update(entities);
        return new ExtGridData(true, entities.size(), entities);
    }

    @RequestMapping("/load/businessNews")
    @ResponseBody
    public ExtData load(Pageable pr, ExtFilters filters) throws JsonParseException, JsonMappingException, IOException {
        Page<BusinessNewsEntity> pageData = businessService.findAll(filters, pr);
        return new ExtGridData(true, pageData.getTotalElements(), pageData.getContent());
    }

    @RequestMapping("/loadOne/businessNews")
    @ResponseBody
    public ExtData loadOne(@RequestParam("id") Long id) {
        BusinessNewsEntity entity = businessService.findOne(id);
        if (entity != null) {
            return new ExtData(true, entity);
        } else {
            return ExtData.EmptyFailure;
        }
    }
}
