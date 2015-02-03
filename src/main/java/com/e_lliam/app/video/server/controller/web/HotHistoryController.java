package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.IHotHistoryDao;
import com.e_lliam.app.video.server.entity.HotHistoryEntity;
import com.e_lliam.app.video.server.entity.UpdateVersionEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/ext")
public class HotHistoryController {
	private static final TypeReference<List<HotHistoryEntity>> VALUE_TYPE_REF = new TypeReference<List<HotHistoryEntity>>() {};
	private static final Log LOG = LogFactory.getLog(HotHistoryController.class);
	@Resource
	private IHotHistoryDao hotHistoryDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/hot")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<HotHistoryEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (HotHistoryEntity hotHistoryEntity:entities){
            hotHistoryEntity.setCurrentDay(DateUtils.getDay());
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setCurrrentMonth(DateUtils.getMonthDay());
            hotHistoryEntity.setCurrrentWeek(DateUtils.getWeekFirshDay());
        }
		hotHistoryDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/hot")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<HotHistoryEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		hotHistoryDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/hot")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<HotHistoryEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (HotHistoryEntity hotHistoryEntity:entities){
            hotHistoryEntity.setCurrentDay(DateUtils.getDay());
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setCurrrentMonth(DateUtils.getMonthDay());
            hotHistoryEntity.setCurrrentWeek(DateUtils.getWeekFirshDay());
        }
		hotHistoryDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/hot")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filterst) {
		Page<HotHistoryEntity> datas = hotHistoryDao.findAllWithFilters(pr,filterst);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	
	@RequestMapping("/loadone/hot")
	@ResponseBody
	public HotHistoryEntity loadOne() {
        HotHistoryEntity entity = hotHistoryDao.findOne(1L);
		if(entity!=null){
			return entity;
		}else{
			return null;
		}
	}
}
