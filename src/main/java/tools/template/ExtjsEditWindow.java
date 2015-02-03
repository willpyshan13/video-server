package tools.template;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.Id;

import com.e_lliam.app.video.server.entity.*;
import com.google.common.collect.Lists;

public class ExtjsEditWindow {
	private String root;
	public ExtjsEditWindow(String string) {
		root=string;
	}

	public static void main(String[] args) {
		ExtjsEditWindow em = new ExtjsEditWindow("/Users/Landy/Documents/workspace/video-server/src/main/webapp/WEB-INF/js/app/store/");
		try {
//			em.process(BusinessNewsEntity.class);
//			em.process(CollectionEntity.class);
//			em.process(CommentEntity.class);
//			em.process(FeedbackEntity.class);
//			em.process(PersonEntity.class);
//			em.process(PraiseEntity.class);
//			em.process(SearchEntity.class);
//			em.process(ToplineEntity.class);
//			em.process(VideoEntity.class);
//			em.process(VideoHistoryEntity.class);
//			em.process(VideoTypeEntity.class);
//			em.process(VideoUrlEntity.class);
//            em.process(AdminEntity.class);
//            em.process(HotHistoryEntity.class);
//            em.process(UpdateVersionEntity.class);
            em.process(PushMessageEntity.class);
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
		for(int i=0;i<props.size();i++){
			if(i!=0){
				sb.append(",\n");
			}
			String one = props.get(i);
			sb.append("{xtype:'textfield',fieldLabel:'"+one+"',name:'"+one+"',");
			if(one.equals(idName)){
				sb.append("readOnly:true,readOnlyCls: 'x-item-disabled'");
			}else{
				sb.append("allowBlank: false");
			}
			sb.append("}");
		}

		System.out.println("**************"+modelClass);
		System.out.println();
		System.out.println(sb.toString());
		System.out.println();
		
	}
}
