package com.e_lliam.app.video.server.controller.web;

import com.e_lliam.app.video.server.dao.ICompanyInfoDao;
import com.e_lliam.app.video.server.entity.CompanyInfoEntity;
import com.e_lliam.app.video.server.entity.FeedbackEntity;
import com.e_lliam.app.video.server.entity.SystemConfigEntity;
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
public class CompanyInfoController {
	private static final TypeReference<List<CompanyInfoEntity>> VALUE_TYPE_REF = new TypeReference<List<CompanyInfoEntity>>() {};
	private static final Log LOG = LogFactory.getLog(CompanyInfoController.class);
	@Resource
	private ICompanyInfoDao companyInfoDao;
    private SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/companyinfo")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CompanyInfoEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (CompanyInfoEntity feedbackEntity : entities) {
            feedbackEntity.setCreateTime(smf.format(DateUtils.now()));
        }
		companyInfoDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/companyinfo")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CompanyInfoEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		companyInfoDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/companyinfo")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<CompanyInfoEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
		for (CompanyInfoEntity feedbackEntity : entities) {
			feedbackEntity.setCreateTime(smf.format(DateUtils.now()));
		}
		companyInfoDao.save(entities);
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/companyinfo")
	@ResponseBody
	public ExtData load(
			@RequestParam(value = "page", required = false,defaultValue="1") Integer currentPage,
			@RequestParam(required = false, value = "limit",defaultValue="10") Integer pageSize,
			HttpServletRequest request) {
		Page<CompanyInfoEntity> datas = companyInfoDao.findAll(new ExtPageRequest(currentPage,pageSize));
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}

    @RequestMapping("/loadone/companyinfo")
    @ResponseBody
    public CompanyInfoEntity loadOne() {
        CompanyInfoEntity entity = companyInfoDao.findOne(1L);
        if(entity!=null){
            return entity;
        }else{
            return null;
        }
    }
}
