package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.e_lliam.app.video.server.dao.*;
import com.e_lliam.app.video.server.entity.*;
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

import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.DateUtils;
import com.e_lliam.app.video.server.utils.MD5Utils;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;

@Controller
@RequestMapping("/ext")
public class PersonController {
	private static final TypeReference<List<PersonEntity>> VALUE_TYPE_REF = new TypeReference<List<PersonEntity>>() {};
	private static final Log LOG = LogFactory.getLog(PersonController.class);
	@Resource
	private IPersonDao personDao;
	
	@Resource
	private ITokenDao tokenDao;
	@Resource
	private IThirdplatDao thirdplatDao;

    @Resource
    private ICommentDao commentDao;
    @Resource
    private ICollectionDao collectionDao;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/person")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PersonEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		for (PersonEntity personEntity : entities) {
			String password = personEntity.getPassWord();
			personEntity.setPassWord(MD5Utils.String2MD5(password));
			personEntity.setCreateTime(DateUtils.now());
		}
		personDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}
	
	@RequestMapping("/change/person")
	@ResponseBody
	public ExtData changePassword(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PersonEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		for (PersonEntity personEntity : entities) {
			String password = personEntity.getPassWord();
			password = MD5Utils.String2MD5(password);
			Long personId = personEntity.getPersonId();
			personDao.updatePassword(personId, password);
		}
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/person")
	@ResponseBody
	public ExtData remove(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PersonEntity> list = objectMapper
				.readValue(body, VALUE_TYPE_REF);
		personDao.delete(list);
        ThirdplatEntity thirdplatEntity;
        List<CollectionEntity> collectionEntity ;
        List<CommentEntity> commentEntity;
        TokenEntity tokenEntity ;
        //用户被删除之后，删除 token 评论 第三方登录信息 收藏信息
       for (PersonEntity person :list){
           Long personId = person.getPersonId();
           tokenEntity = tokenDao.findByPersonId(personId);
           if (tokenEntity!=null){
               tokenDao.delete(tokenEntity);
           }
           collectionEntity =  collectionDao.findByPersonId(personId);
           if (collectionEntity!=null){
               collectionDao.delete(collectionEntity);
           }
           commentEntity = commentDao.findByPersonId(personId);
           if (commentEntity!=null){
               commentDao.delete(commentEntity);
           }
           thirdplatEntity =  thirdplatDao.findByPersonId(personId);
           if (thirdplatEntity!=null){
                 thirdplatDao.delete(thirdplatEntity);
           }
       }
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/person")
	@ResponseBody
	public ExtData update(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PersonEntity> entities = objectMapper.readValue(body,
				VALUE_TYPE_REF);
		
		personDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/person")
	@ResponseBody
	public ExtData load(Pageable pr,ExtFilters filters) {
		Page<PersonEntity> datas = personDao.findAllWithFilters(pr,filters);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
}
