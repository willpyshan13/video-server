package com.e_lliam.app.video.server.controller.web;



import cn.jpush.api.ErrorCodeEnum;
import cn.jpush.api.JPushClient;
import cn.jpush.api.MessageResult;
import com.e_lliam.app.video.server.dao.IPushMessageDao;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.entity.PushMessageEntity;
import com.e_lliam.app.video.server.pojo.extjs.ExtData;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.pojo.extjs.ExtGridData;
import com.e_lliam.app.video.server.utils.DateUtils;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/ext")
public class PushMessageController {
	private static final TypeReference<List<PushMessageEntity>> VALUE_TYPE_REF = new TypeReference<List<PushMessageEntity>>() {};
	private static final Log LOG = LogFactory.getLog(PushMessageController.class);
	@Resource
	private IPushMessageDao pushMessageDao;

    private JPushClient jpush;
//    String username = "yilianmei";
//    String password = "elliam2013";
    String appKey = "bbff74bf35ea621089a7bd6d";
    String MasterSecret = "9b35a1271954e12cf33a3ace";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping("/add/push")
	@ResponseBody
	public ExtData add(@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PushMessageEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (PushMessageEntity pushMessageEntity:entities){
            pushMessageEntity.setPushTime(sdf.format(DateUtils.now()));
        }
		pushMessageDao.save(entities);
        push();
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/remove/push")
	@ResponseBody
	public ExtData remove(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {

		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PushMessageEntity> list = objectMapper.readValue(body, VALUE_TYPE_REF);
		pushMessageDao.delete(list);
		return new ExtGridData(true, list.size(), list);
	}

	@RequestMapping("/update/push")
	@ResponseBody
	public ExtData update(
			@RequestBody String body) throws JsonParseException,
			JsonMappingException, IOException {
		if (!body.startsWith("[")) {
			body = "[" + body + "]";
		}
		List<PushMessageEntity> entities = objectMapper.readValue(body, VALUE_TYPE_REF);
        for (PushMessageEntity pushMessageEntity:entities){
            pushMessageEntity.setPushTime(sdf.format(DateUtils.now()));
        }
		pushMessageDao.save(entities);
        push();
		return new ExtGridData(true, entities.size(), entities);
	}

	@RequestMapping("/load/push")
	@ResponseBody
	public ExtData load(Pageable pr) throws JsonParseException, JsonMappingException, IOException {
		Page<PushMessageEntity> datas = pushMessageDao.findAll(pr);
		return new ExtGridData(true, datas.getTotalElements(), datas.getContent());
	}
	@RequestMapping("/loadOne/push")
	@ResponseBody
	public PushMessageEntity loadOne() {
        PushMessageEntity entity = pushMessageDao.findOne(1L);
		if(entity!=null){
			return entity;
		}else{
			return null;
		}
	}

    public void push(){
        if (jpush == null){
            jpush = new JPushClient(MasterSecret,appKey);
        }
        PushMessageEntity pushMessageEntity = pushMessageDao.findOne(1L);
        pushMessage(pushMessageEntity.getMessageTitle(),pushMessageEntity.getMessageContent());
    }

    private void pushMessage(String msgTitle, String msgContent) {
        // 开发者自己维护sendNo，sendNo的作用请参考文档。
        int sendNo = 1;

        MessageResult msgResult = jpush.sendNotificationWithAppKey(sendNo,
                msgTitle, msgContent);
        if (null != msgResult) {
            LOG.error("服务器返回数据: " + msgResult.toString());
            if (msgResult.getErrcode() == ErrorCodeEnum.NOERROR.value()) {
                LOG.error("发送成功， sendNo=" + msgResult.getSendno());
            } else {
                LOG.error("发送失败， 错误代码=" + msgResult.getErrcode() + ", 错误消息="
                        + msgResult.getErrmsg());
            }
        } else {
            LOG.error("无法获取数据，检查网络是否超时");
        }
    }
}
