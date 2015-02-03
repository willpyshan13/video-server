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

import com.e_lliam.app.video.server.dao.IPraiseDao;
import com.e_lliam.app.video.server.entity.PraiseEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class PraiseController {
	private static final TypeReference<List<PraiseEntity>> VALUE_TYPE_REF = new TypeReference<List<PraiseEntity>>() {};
	private static final Log LOG = LogFactory
			.getLog(PraiseController.class);
	@Resource
	private IPraiseDao praiseDao;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/praise")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PraiseEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		praiseDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/praise")
	@ResponseBody
	public ExtData remove(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PraiseEntity> list = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		praiseDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/praise")
	@ResponseBody
	public ExtData update(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PraiseEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		praiseDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/praise")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) {
		Page<PraiseEntity> datas = praiseDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
}
