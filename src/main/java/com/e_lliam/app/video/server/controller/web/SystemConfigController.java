package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.dao.ISystemConfigDao;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.entity.SystemConfigEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class SystemConfigController {
	private static final TypeReference<List<SystemConfigEntity>> VALUE_TYPE_REF = new TypeReference<List<SystemConfigEntity>>() {};
	private static final Log LOG = LogFactory.getLog(SystemConfigController.class);
	@Resource
	private ISystemConfigDao systemConfigDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/config")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SystemConfigEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		systemConfigDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/config")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SystemConfigEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		systemConfigDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/config")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SystemConfigEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		systemConfigDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/config")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<SystemConfigEntity> datas = systemConfigDao.findAll(new ExtPageRequest(currentPage,pageSize));
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	
	@RequestMapping("/loadone/config")
	@ResponseBody
	public SystemConfigEntity loadOne() {
		SystemConfigEntity entity = systemConfigDao.findOne(1L);
		if(entity!=null){
			return entity;
		}else{
			return null;
		}
	}
}
