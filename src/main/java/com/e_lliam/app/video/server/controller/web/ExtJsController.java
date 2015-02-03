package com.e_lliam.app.video.server.controller.web;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.e_lliam.app.video.server.dao.IEntityManagerUtils;
import com.e_lliam.app.video.server.dao.IVideoTypeDao;
import com.e_lliam.app.video.server.dao.IVideoUrlDao;
import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.entity.PersonEntity;
import com.e_lliam.app.video.server.entity.ToplineEntity;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.entity.VideoTypeEntity;
import com.e_lliam.app.video.server.entity.VideoUrlEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.BeanTools;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/ext")
public class ExtJsController {
	private static final Log LOG = LogFactory.getLog(ExtJsController.class);
	@Resource
	private IEntityManagerUtils emu;
	// @Resource
	// private IVideoDao videoDao;
	@Resource
	private IVideoUrlDao videoUrlDao;
	@Resource
	private IVideoTypeDao videoTypeDao;
	// @Resource
	// private IVideoTypeRecordDao videoTypeRecordDao;
	// @Resource
	// private IPraiseDao praiseDao;
	// @Resource
	// private ICommentDao commentDao;
	// @Resource
	// private ICollectionDao collectionDao;
	@Resource
	private ObjectMapper objectMapper;

	private static Map<String, Class[]> TMS = Maps.newHashMap();
	static {
		TMS.put("person", new Class[] { PersonEntity.class,
				PersonEntity[].class });
		TMS.put("video", new Class[] { VideoEntity.class, VideoEntity[].class });
		TMS.put("comment", new Class[] { CommentEntity.class,
				CommentEntity[].class });
		TMS.put("videoType", new Class[] { VideoTypeEntity.class,
				VideoTypeEntity[].class });
		TMS.put("topline", new Class[] { ToplineEntity.class,
				ToplineEntity[].class });
		TMS.put("videoUrl", new Class[] { VideoUrlEntity.class,
				VideoUrlEntity[].class });
		TMS.put("businessNews", new Class[] { BusinessNewsEntity.class,
				BusinessNewsEntity[].class });
	}

	@RequestMapping("/add/{type}")
	@ResponseBody
	public ExtData add(@PathVariable("type") String type,
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		if (TMS.containsKey(type)) {
			Class cls = TMS.get(type)[1];
			Object[] entities = (Object[]) objectMapper.readValue(body, cls);
			emu.persist(Arrays.asList(entities));
			return new ExtGridData(true, entities.length, entities);
		}
		return ExtData.EmptySuccess;
	}

	@RequestMapping("/remove/{type}")
	@ResponseBody
	public ExtData remove(@PathVariable("type") String type,
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		if (TMS.containsKey(type)) {
			Class cls = TMS.get(type)[1];
			Class singleType = TMS.get(type)[0];
			Object[] list = (Object[]) objectMapper.readValue(body, cls);
			List<Long> ids = Lists.newArrayList();
			for (Object u : list) {
				ids.add(BeanTools.getPrimaryKeyValue(u));
			}
			emu.remove(ids, singleType);
			return new ExtGridData(true, list.length, list);
		}
		return ExtData.EmptySuccess;
	}

	@RequestMapping("/update/{type}")
	@ResponseBody
	public ExtData update(@PathVariable("type") String type,
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		if (TMS.containsKey(type)) {
			Class cls = TMS.get(type)[1];
			Object[] entities = (Object[]) objectMapper.readValue(body, cls);
			emu.merge(Arrays.asList(entities));
			return new ExtGridData(true, entities.length, entities);
		}
		return ExtData.EmptySuccess;
	}
	@RequestMapping("/loadOne/{type}")
	@ResponseBody
	public ExtData loadOne(
			@PathVariable("type") String type,@RequestParam("id") Long id) {
		
		if (TMS.containsKey(type)) {
			Class cls = TMS.get(type)[0];
			Object data = emu.findOne(id, cls);
			if(data!=null){
				return new ExtData(true, data);
			}else{
				return new ExtData(false);
			}
		}

		return ExtData.EmptyFailure;
	}
	@RequestMapping("/load/{type}")
	@ResponseBody
	public ExtData load(
			@PathVariable("type") String type,
			Pageable pr,
			HttpServletRequest request) {
		if (type.equals("videoType")) {
			String filterType = request.getParameter("filterType");
			Collection<VideoTypeEntity> datas = null;
			if (filterType.equals("root")) {
				datas = videoTypeDao.findByTypeParentIsNull();
			} else {
				Long typeParent = Long.parseLong(request.getParameter("typeParent"));
				datas = videoTypeDao.findByTypeParent(typeParent);
			}
			return new ExtGridData(true, datas.size(), datas);
		} else if (TMS.containsKey(type)) {
			Class cls = TMS.get(type)[0];
			Long total = emu.count(cls);
			List<?> datas = emu.findPage(pr, cls);
			return new ExtGridData(true, total.intValue(), datas);
		}

		return ExtData.EmptySuccess;
	}


	@RequestMapping("/upload")
	@ResponseBody
	public ExtData uploadFile(
			@RequestParam(value = "uploadFile") MultipartFile image,
			@RequestParam("type") String type, HttpServletRequest request) {
//		LOG.info("Upload type is : " + type + "..............");
		try {
			String uploadDir = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/";
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			int day = cal.get(Calendar.DAY_OF_MONTH);
			String dest = "upload/" + type + "/" + month + "/" + day + "/";
			File parentDir = new File(uploadDir + dest);
			if (!parentDir.exists()) {
				parentDir.mkdirs();
			}
			dest = dest + System.currentTimeMillis() + "."
					+ FilenameUtils.getExtension(image.getOriginalFilename());
			File destFile = new File(uploadDir + dest);
			image.transferTo(destFile);
//			LOG.info(destFile.getAbsolutePath());
			return new ExtGridData(true, 0, dest);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);

			ExtData extData = new ExtData(false);
			extData.setErrors(e);
			return extData;
		}
	}

}
