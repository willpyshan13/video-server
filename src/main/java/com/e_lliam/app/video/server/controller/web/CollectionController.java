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

import com.e_lliam.app.video.server.dao.ICollectionDao;
import com.e_lliam.app.video.server.entity.CollectionEntity;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class CollectionController {
	private static final TypeReference<List<CollectionEntity>> VALUE_TYPE_REF = new TypeReference<List<CollectionEntity>>() {
	};
	private static final Log LOG = LogFactory
			.getLog(CollectionController.class);
	@Resource
	private ICollectionDao collectionDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/collection")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CollectionEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		for (CollectionEntity collectionEntity : entities) {
			collectionEntity.setCreateTime(DateUtils.now());
		}
		collectionDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/collection")
	@ResponseBody
	public ExtData remove(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CollectionEntity> list = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		collectionDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/collection")
	@ResponseBody
	public ExtData update(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CollectionEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		for (CollectionEntity collectionEntity : entities) {
			collectionEntity.setCreateTime(DateUtils.now());
		}
		collectionDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/collection")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) {
		Page<CollectionEntity> datas = collectionDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(),
				datas.getContent());
	}
}
