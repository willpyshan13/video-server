package com.e_lliam.app.video.server.controller.mobile;

import java.util.List;
import java.util.Map;

import org.hibernate.classic.Validatable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.e_lliam.app.video.server.pojo.mobile.MobileResult;
import com.e_lliam.app.video.server.pojo.mobile.VideoBriefBean;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/mobile/mobiletest")
public class MobileApiTestController {
	@RequestMapping("/index")
	public String mobileIndex()
	{
		return "mobile/index";
	}
	
	@RequestMapping("/home")
	@ResponseBody
	public MobileResult index(){
		
		MobileResult result = new MobileResult();
		
		
		List<VideoBriefBean> wheels=Lists.newArrayList();
		VideoBriefBean wb=null;
		
		wb=new VideoBriefBean();
		wb.setVideoId(1L);
		wb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/1.png");
		wb.setVideoBrief("喜羊羊和灰太狼的幸福");
		wheels.add(wb);
		
		wb=new VideoBriefBean();
		wb.setVideoId(2L);
		wb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/2.png");
		wb.setVideoBrief("喜羊羊和灰太狼的幸福");
		wheels.add(wb);
		
		wb=new VideoBriefBean();
		wb.setVideoId(3L);
		wb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/3.png");
		wb.setVideoBrief("喜羊羊和灰太狼的幸福");
		wheels.add(wb);
		
		wb=new VideoBriefBean();
		wb.setVideoId(4L);
		wb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/4.png");
		wb.setVideoBrief("喜羊羊和灰太狼的幸福");
		wheels.add(wb);
		
		
		List<VideoBriefBean> hots=Lists.newArrayList();
		VideoBriefBean hb=null;
		
		hb=new VideoBriefBean();
		hb.setVideoId(21L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/11.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(22L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/21.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(23L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/31.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(24L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/41.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(25L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/51.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(26L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/6.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(27L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/7.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(28L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/8.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		hb=new VideoBriefBean();
		hb.setVideoId(29L);
		hb.setPicUrl("http://27.154.234.10:8880/wordpress/wp-content/uploads/2013/09/9.png");
		hb.setVideoTitle("美少年之恋");
		hb.setVideoBrief("千古绝唱，东邪西毒");
		hb.setPraise(100);
		hb.setComment(54);
		hots.add(hb);
		
		Map<String, Object> container=Maps.newHashMap();
		container.put("wheel", wheels);
		container.put("hot", hots);
		result.setData(container);
		result.setStatus(200);
		return result;
	}
}
