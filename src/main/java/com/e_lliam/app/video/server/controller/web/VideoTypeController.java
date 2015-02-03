package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.dao.IVideoTypeDao;
import com.e_lliam.app.video.server.entity.VideoTypeEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

@Controller
@RequestMapping("/ext")
public class VideoTypeController {
	private static final TypeReference<List<VideoTypeEntity>> VALUE_TYPE_REF = new TypeReference<List<VideoTypeEntity>>() {
	};
	private static final Log LOG = LogFactory.getLog(VideoTypeController.class);
	@Resource
	private IVideoTypeDao videoTypeDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/videoType")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		for(VideoTypeEntity type:entities){
			type.setTypeId(null);
		}
		videoTypeDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/videoType")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		videoTypeDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/videoType")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		videoTypeDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/videoType")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			@RequestParam("filterType")String filterType,@RequestParam(required=false,value="typeParent")Long typeParent) {
		if(filterType.equals("leaf")){
			if(typeParent!=null&&typeParent!=-1){
				Collection<VideoTypeEntity> datas = videoTypeDao.findByTypeParent(typeParent);
				return new ExtGridData(true, datas.size(), datas);
			}
		}else if(filterType.equals("root")){
			Collection<VideoTypeEntity> datas = videoTypeDao.findByTypeParentIsNull();
			return new ExtGridData(true, datas.size(), datas);
		}
		return ExtData.EmptySuccess;
	}
	@RequestMapping("/load/videoType/{videoTypeName}")
	@ResponseBody
	public ExtData loadByVideoTypeName(@PathVariable("videoTypeName") String videoTypeName) {
		Collection<VideoTypeEntity> datas = videoTypeDao.findByTypeParentName(videoTypeName);
		return new ExtGridData(true, datas.size(), datas);
	}
}
