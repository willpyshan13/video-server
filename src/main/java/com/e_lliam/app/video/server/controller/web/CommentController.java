package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.dao.ICommentDao;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
import com.google.common.collect.Lists;

@Controller
@RequestMapping("/ext")
public class CommentController {
	private static final TypeReference<List<CommentEntity>> VALUE_TYPE_REF = new TypeReference<List<CommentEntity>>() {};
	private static final Log LOG = LogFactory.getLog(CommentController.class);
	@Resource
	private ICommentDao commentDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/comment")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CommentEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		for(CommentEntity e:entities){
			e.setCommentId(null);
			e.setCreateTime(DateUtils.now());
		}
		commentDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/comment")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CommentEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		commentDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/comment")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CommentEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		commentDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/comment")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) throws JsonParseException, JsonMappingException, IOException {
		Page<CommentEntity> datas = commentDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	@RequestMapping("/loadOne/comment")
	@ResponseBody
	public ExtData loadOne(@RequestParam("id")Long id) {
		CommentEntity entity = commentDao.findOne(id);
		if(entity!=null){
			return new ExtData(true, entity);
		}else{
			return ExtData.EmptyFailure;
		}
	}
}
