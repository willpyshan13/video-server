package tools.template;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;

import org.apache.commons.io.FileUtils;

import com.e_lliam.app.video.server.entity.BusinessNewsEntity;
import com.e_lliam.app.video.server.entity.CollectionEntity;
import com.e_lliam.app.video.server.entity.CommentEntity;
import com.e_lliam.app.video.server.entity.FeedbackEntity;
import com.e_lliam.app.video.server.entity.PersonEntity;
import com.e_lliam.app.video.server.entity.PraiseEntity;
import com.e_lliam.app.video.server.entity.SearchEntity;
import com.e_lliam.app.video.server.entity.ToplineEntity;
import com.e_lliam.app.video.server.entity.VideoEntity;
import com.e_lliam.app.video.server.entity.VideoHistoryEntity;
import com.e_lliam.app.video.server.entity.VideoTypeEntity;
import com.e_lliam.app.video.server.entity.VideoUrlEntity;
import com.google.common.collect.Lists;

public class ExtjsModel {
	private boolean toFile=false;
	private String root;
	public ExtjsModel(String string) {
		root=string;
	}

	public static void main(String[] args) {
		ExtjsModel em = new ExtjsModel("/Users/Landy/Documents/workspace/video-server/src/main/webapp/WEB-INF/js/app/model/");
		try {
			em.process(BusinessNewsEntity.class);
			
			em.process(CollectionEntity.class);
			em.process(CommentEntity.class);
			em.process(FeedbackEntity.class);
			em.process(PersonEntity.class);
			em.process(PraiseEntity.class);
			em.process(SearchEntity.class);
			em.process(ToplineEntity.class);
			em.process(VideoEntity.class);
			em.process(VideoHistoryEntity.class);
			em.process(VideoTypeEntity.class);
			em.process(VideoUrlEntity.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void process(Class<?> cls) throws IOException {
		String modelClass = cls.getSimpleName();
		modelClass = modelClass.substring(0,
				modelClass.length() - "entity".length());
		String api=(modelClass.charAt(0)+"").toLowerCase()+modelClass.substring(1);
		String idName = null;
		List<String> props = Lists.newArrayList();
		for (Field f : cls.getDeclaredFields()) {
			if (f.getAnnotation(Id.class) != null) {
				idName = f.getName();
			}
			props.add(f.getName());
		}
		StringBuffer sb = new StringBuffer();
		sb.append("Ext.define('dy.model.");
		sb.append(modelClass);
		sb.append("',{\n" + "	extend : 'Ext.data.Model',\n" + " idProperty : '");
		sb.append(idName);
		sb.append("',\n" + " fields : [");
		for (int i = 0; i < props.size(); i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append("'");
			sb.append(props.get(i));
			sb.append("'");
		}
		sb.append("],\n" + " proxy : {\n" + "type : 'ajax',\n" + "api : {\n"
				+ "create : 'ext/add/"
				+ api
				+ "',\n"
				+ "read : 'ext/load/"
				+ api
				+ "',\n"
				+ "update : 'ext/update/"
				+ api
				+ "',\n"
				+ "destroy : 'ext/remove/"
				+ api
				+ "'\n"
				+ "},\n"
				+ "headers : {\n"
				+ "'Content-Type' : 'application/json; charset=UTF-8'\n"
				+ "},\n"
				+ "reader : {\n"
				+ "type : 'json',\n"
				+ "root : 'datas'\n"
				+ "},\n"
				+ "writer : {\n"
				+ "encode : false,\n"
				+ "listful : true,\n"
				+ "writeAllFields : true\n" + "}\n" + "}\n" + "});");
		if(toFile){
			String fileName=root+modelClass+".js";
			FileUtils.write(new File(fileName),sb.toString(),"utf8");
		}else{
			System.out.println(sb.toString());
		}
		System.out.println("*************************");
	}
}
