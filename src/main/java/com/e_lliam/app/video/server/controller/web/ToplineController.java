package com.e_lliam.app.video.server.controller.web;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.dao.IBusinessNewsDao;
import com.e_lliam.app.video.server.dao.IToplineDao;
import com.e_lliam.app.video.server.dao.IVideoDao;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.entity.ToplineEntity;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.pojo.ToplineType;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;

@Controller
@RequestMapping("/ext")
public class ToplineController {
	private static final Log LOG = LogFactory.getLog(ToplineController.class);
	@Resource
	private IToplineDao toplineDao;
	@Resource
	private IVideoDao videoDao;
	@Resource
	private IBusinessNewsDao businessNewsDao;
	
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/topline/{type}")
	@ResponseBody
	public ExtData add(@RequestBody String body,@PathVariable("type")String type) throws JsonMappingException, IOException
			 {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		if(type.equalsIgnoreCase(ToplineType.NewsWheel.getKey())){
			List<BusinessNewsEntity> entities = objectMapper.readValue(body, new TypeReference<List<BusinessNewsEntity>>(){});
			for(BusinessNewsEntity v:entities){
				ToplineEntity t=new ToplineEntity();
				t.setDataId(v.getNewsId());
				t.setTopType(ToplineType.toInt(type));
				toplineDao.save(t);
			}
			return new ExtGridData(true, entities.size(), entities);
		}else{
			List<VideoEntity> entities = objectMapper.readValue(body, new TypeReference<List<VideoEntity>>(){});
			for(VideoEntity v:entities){
				ToplineEntity t=new ToplineEntity();
				t.setDataId(v.getVideoId());
				t.setTopType(ToplineType.toInt(type));
				toplineDao.save(t);
			}
			return new ExtGridData(true, entities.size(), entities);
		}
	}

	@RequestMapping("/remove/topline/{type}")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body,@PathVariable("type")String type) throws JsonParseException,
			JsonMappingException, IOException {
		
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		if(type.equalsIgnoreCase(ToplineType.NewsWheel.getKey())){
			List<BusinessNewsEntity> list = objectMapper.readValue(body,new  TypeReference<List<BusinessNewsEntity>>() {});
			for(BusinessNewsEntity v:list){
				toplineDao.deleteByDataIdAndTopType(v.getNewsId(), ToplineType.toInt(type));
			}
			
			return new ExtGridData(true, list.size(), list);
		}else{
			List<VideoEntity> list = objectMapper.readValue(body,new  TypeReference<List<VideoEntity>>() {});
			for(VideoEntity v:list){
				toplineDao.deleteByDataIdAndTopType(v.getVideoId(), ToplineType.toInt(type));
			}
			
			return new ExtGridData(true, list.size(), list);
		}
	}
	
	@RequestMapping("/load/topline/{type}")
	@ResponseBody
	public ExtData load(@PathVariable("type")String type) {
		if(type.equalsIgnoreCase(ToplineType.NewsWheel.getKey())){
			List<BusinessNewsEntity> datas=businessNewsDao.getByTopline(ToplineType.NewsWheel.toInt());
			return new ExtGridData(true, datas.size(), datas);
		}else{
			List<VideoEntity> datas=videoDao.getByTopline(ToplineType.toInt(type));
			return new ExtGridData(true, datas.size(), datas);
		}
	}
}
