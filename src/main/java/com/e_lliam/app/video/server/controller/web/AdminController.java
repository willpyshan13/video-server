package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.IAdminDao;
import com.e_lliam.app.video.server.entity.AdminEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.shiro.ShiroDBRealm;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.UserInfo;
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
import java.util.List;

@Controller
@RequestMapping("/ext")
public class AdminController {
	private static final TypeReference<List<AdminEntity>> VALUE_TYPE_REF = new TypeReference<List<AdminEntity>>() {};
	private static final Log LOG = LogFactory.getLog(AdminController.class);
	@Resource
	private IAdminDao adminDao;

	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/admin")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<AdminEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for(AdminEntity adminEntity:entities){
            adminEntity.setCreateTime(DateUtils.now());
        }
		adminDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/admin")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<AdminEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		adminDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/admin")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<AdminEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for(AdminEntity adminEntity:entities){
            adminEntity.setCreateTime(DateUtils.now());
        }
		adminDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/admin")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<AdminEntity> datas = adminDao.findByAdminNameNot(new ExtPageRequest(currentPage,pageSize),UserInfo.username);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	
	@RequestMapping("/loadone/admin")
	@ResponseBody
	public AdminEntity loadOne() {
        AdminEntity entity = adminDao.findByAdminNameAndAdminPass(UserInfo.username,UserInfo.password);
		if(entity!=null){
			return entity;
		}else{
			return null;
		}
	}
}
