package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
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

import com.e_lliam.app.video.server.dao.ISearchDao;
import com.e_lliam.app.video.server.entity.PersonEntity;
import com.e_lliam.app.video.server.entity.SearchEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class SearchController {
	private static final TypeReference<List<SearchEntity>> VALUE_TYPE_REF = new TypeReference<List<SearchEntity>>() {};
	private static final Log LOG = LogFactory.getLog(SearchController.class);
	@Resource
	private ISearchDao searchDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/search")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SearchEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		searchDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/search")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SearchEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		searchDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/search")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<SearchEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		searchDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/search")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) {
		Page<SearchEntity> datas = searchDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
}
