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

public class ExtjsStore {
	private String root;
	public ExtjsStore(String string) {
		root=string;
	}

	public static void main(String[] args) {
		ExtjsStore em = new ExtjsStore("/Users/Landy/Documents/workspace/video-server/src/main/webapp/WEB-INF/js/app/store/");
		try {
			em.process(BusinessNewsEntity.class);
			System.out.println("*************************");
			em.process(CollectionEntity.class);
			System.out.println("*************************");
			em.process(CommentEntity.class);
			System.out.println("*************************");
			em.process(FeedbackEntity.class);
			System.out.println("*************************");
			em.process(PersonEntity.class);
			System.out.println("*************************");
			em.process(PraiseEntity.class);
			System.out.println("*************************");
			em.process(SearchEntity.class);
			System.out.println("*************************");
			em.process(ToplineEntity.class);
			System.out.println("*************************");
			em.process(VideoEntity.class);
			System.out.println("*************************");
			em.process(VideoHistoryEntity.class);
			System.out.println("*************************");
			em.process(VideoTypeEntity.class);
			System.out.println("*************************");
			em.process(VideoUrlEntity.class);
			System.out.println("************************* over.....");
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
		sb.append("Ext.define('dy.store."+modelClass+"', {");
		sb.append("	extend : 'Ext.data.Store',");
		sb.append("	model : 'dy.model."+modelClass+"',");
		sb.append("	remoteSort : true,");
		sb.append("	remoteFilter : true,");
		sb.append("	pageSize : 20,");
		sb.append("	autoLoad : false");
		sb.append("});");

		String fileName=root+modelClass+".js";
		FileUtils.write(new File(fileName),sb.toString(),"utf8");
	}
}
