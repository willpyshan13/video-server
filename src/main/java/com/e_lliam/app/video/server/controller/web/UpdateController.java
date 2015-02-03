package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.IUpdateVersionDao;
import com.e_lliam.app.video.server.entity.AdminEntity;
import com.e_lliam.app.video.server.entity.UpdateVersionEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/ext")
public class UpdateController {
	private static final TypeReference<List<UpdateVersionEntity>> VALUE_TYPE_REF = new TypeReference<List<UpdateVersionEntity>>() {};
	private static final Log LOG = LogFactory.getLog(UpdateController.class);
	@Resource
	private IUpdateVersionDao updateVersionDao;

	@Resource
	private ObjectMapper objectMapper;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping("/add/update")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<UpdateVersionEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for(UpdateVersionEntity updateVersionEntity:entities){
            updateVersionEntity.setUpdateTime(simpleDateFormat.format(DateUtils.now()));
        }
		updateVersionDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/update")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<UpdateVersionEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		updateVersionDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/update")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<UpdateVersionEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for(UpdateVersionEntity updateVersionEntity:entities){
            updateVersionEntity.setUpdateTime(simpleDateFormat.format(DateUtils.now()));
        }
		updateVersionDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/update")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<UpdateVersionEntity> datas = updateVersionDao.findAll(new ExtPageRequest(currentPage,pageSize));
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	
	@RequestMapping("/loadone/update")
	@ResponseBody
	public UpdateVersionEntity loadOne() {
        UpdateVersionEntity entity = updateVersionDao.findOne(1L);
		if(entity!=null){
			return entity;
		}else{
			return null;
		}
	}
}
