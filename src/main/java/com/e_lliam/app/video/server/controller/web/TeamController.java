package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.ITeamDao;
import com.e_lliam.app.video.server.entity.*;
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
public class TeamController {
	private static final TypeReference<List<TeamEntity>> VALUE_TYPE_REF = new TypeReference<List<TeamEntity>>() {};
	private static final Log LOG = LogFactory.getLog(TeamController.class);
	@Resource
	private ITeamDao teamDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/team")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<TeamEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		for (TeamEntity teamEntity:entities){
            teamEntity.setCreateTime(DateUtils.now());
            if (teamEntity.getTeamTypeName().equals("艺人")){
                teamEntity.setTeamTypeId(1L);
            }else if(teamEntity.getTeamTypeName().equals("导演")){
                teamEntity.setTeamTypeId(2L);
            }else{
                teamEntity.setTeamTypeId(3L);
            }
        }
		teamDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/team")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<TeamEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		teamDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/team")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<TeamEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (TeamEntity teamEntity:entities){
            teamEntity.setCreateTime(DateUtils.now());
            if (teamEntity.getTeamTypeName().equals("艺人")){
                teamEntity.setTeamTypeId(1L);
            }else if(teamEntity.getTeamTypeName().equals("导演")){
                teamEntity.setTeamTypeId(2L);
            }else{
                teamEntity.setTeamTypeId(3L);
            }
        }
		teamDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/team")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) {
		Page<TeamEntity> datas = teamDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}


    @RequestMapping("/loadOne/team")
    @ResponseBody
    public ExtData loadOne(@RequestParam("id") Long id) {
        TeamEntity entity = teamDao.findOne(id);
        if (entity != null) {
            return new ExtData(true, entity);
        } else {
            return ExtData.EmptyFailure;
        }
    }
}
