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

import com.e_lliam.app.video.server.dao.IVideoTypeRecordDao;
import com.e_lliam.app.video.server.entity.VideoTypeRecordEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class VideoTypeRecordController {
	private static final TypeReference<List<VideoTypeRecordEntity>> VALUE_TYPE_REF = new TypeReference<List<VideoTypeRecordEntity>>() {
	};
	private static final Log LOG = LogFactory.getLog(VideoTypeRecordController.class);
	@Resource
	private IVideoTypeRecordDao videoTypeRecordDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/videoTypeRecord")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeRecordEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		videoTypeRecordDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/videoTypeRecord")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeRecordEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		videoTypeRecordDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/videoTypeRecord")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<VideoTypeRecordEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		videoTypeRecordDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/videoTypeRecord")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<VideoTypeRecordEntity> datas = videoTypeRecordDao.findAll(new ExtPageRequest(currentPage,pageSize));
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
}
