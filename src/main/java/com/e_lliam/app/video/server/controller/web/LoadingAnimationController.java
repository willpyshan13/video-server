package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.ILoadingAnimationDao;
import com.e_lliam.app.video.server.entity.CompanyInfoEntity;
import com.e_lliam.app.video.server.entity.LoadingAnimationEntity;
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
public class LoadingAnimationController {
	private static final TypeReference<List<LoadingAnimationEntity>> VALUE_TYPE_REF = new TypeReference<List<LoadingAnimationEntity>>() {};
	private static final Log LOG = LogFactory.getLog(LoadingAnimationController.class);
	@Resource
	private ILoadingAnimationDao loadingAnimationDao;
    private SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/loading")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<LoadingAnimationEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		for (LoadingAnimationEntity feedbackEntity : entities) {
			feedbackEntity.setCreateTime(smf.format(DateUtils.now()));
		}
		loadingAnimationDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/loading")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<LoadingAnimationEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		loadingAnimationDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/loading")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<LoadingAnimationEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (LoadingAnimationEntity feedbackEntity : entities) {
            feedbackEntity.setCreateTime(smf.format(DateUtils.now()));
        }
		loadingAnimationDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/loading")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<LoadingAnimationEntity> datas = loadingAnimationDao.findAll(new ExtPageRequest(currentPage,pageSize));
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}

    @RequestMapping("/loadone/loading")
    @ResponseBody
    public LoadingAnimationEntity loadOne() {
        LoadingAnimationEntity entity = loadingAnimationDao.findOne(1L);
        if(entity!=null){
            return entity;
        }else{
            return null;
        }
    }
}
