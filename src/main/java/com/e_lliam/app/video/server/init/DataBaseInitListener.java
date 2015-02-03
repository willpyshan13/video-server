package com.e_lliam.app.video.server.init;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.annotation.Resource;

import com.e_lliam.app.video.server.dao.*;
import com.e_lliam.app.video.server.entity.*;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.e_lliam.app.video.server.pojo.Status;
import com.e_lliam.app.video.server.utils.DateUtils;

@Component
public class DataBaseInitListener implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger LOG=Logger.getLogger(DataBaseInitListener.class);
	@Resource
	private IVideoTypeDao videoTypeDao;
	@Resource
    private IAdminDao adminDao;
    @Resource
    private ICompanyInfoDao companyInfoDao;
    @Resource
    private ILoadingAnimationDao loadingAnimationDao;
	@Resource
	private ISystemConfigDao systemConfigDao;
    @Resource
    private IPushMessageDao pushMessageDao;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		XmlWebApplicationContext app=(XmlWebApplicationContext)arg0.getSource();
		if("dispatcher-servlet".equals(app.getNamespace())){
			LOG.info("初始化数据库数据");
			if(videoTypeDao.count()<1){
				VideoTypeEntity obj=null;
				Long typeParent=null;
				String [] name,desc;

				/**
				 * 插入标签
				 */
				obj=new VideoTypeEntity();
				obj.setTypeName("label");
				obj.setTypeDesc("标签");
				videoTypeDao.save(obj);
				typeParent=obj.getTypeId();

				name=new String[]{"label_comedy","label_love","label_action","label_science","label_kungfu","label_war","label_story"};
				desc=new String[]{"喜剧","爱情","动作","科幻","武侠","战争","剧情"};
				for(int i=0;i<name.length;i++){
					obj=new VideoTypeEntity();
					obj.setTypeParent(typeParent);
					obj.setTypeDesc(desc[i]);
					obj.setTypeName(name[i]);
					videoTypeDao.save(obj);
				}
				/**
				 * 插入热度
				 */
				obj=new VideoTypeEntity();
				obj.setTypeName("hot");
				obj.setTypeDesc("热度");
				videoTypeDao.save(obj);
				typeParent=obj.getTypeId();

				name=new String[]{"hot_today","hot_weekly","hot_month","hot_history","hot_latest","hot_praise"};
				desc=new String[]{"今日最多","本周最多","本月最多","历史最多","最新上映","用户好评"};
				for(int i=0;i<name.length;i++){
					obj=new VideoTypeEntity();
					obj.setTypeParent(typeParent);
					obj.setTypeDesc(desc[i]);
					obj.setTypeName(name[i]);
					videoTypeDao.save(obj);
				}
				/**
				 * 插入时间
				 */
				obj = new VideoTypeEntity();
				obj.setTypeName("year");
				obj.setTypeDesc("年份");
				videoTypeDao.save(obj);
				typeParent = obj.getTypeId();

				name = new String[]{"1990","2012","2013","2014"};
				desc = new String[]{"1990","2012","2013","2014"};
				for (int i = 0; i < desc.length; i++) {
					obj = new VideoTypeEntity();
					obj.setTypeParent(typeParent);
					obj.setTypeDesc(desc[i]);
					obj.setTypeName(name[i]);
					videoTypeDao.save(obj);
				}
				/**
				 * 插入地区
				 */

				obj = new VideoTypeEntity();
				obj.setTypeName("area");
				obj.setTypeDesc("地区");
				videoTypeDao.save(obj);
				typeParent = obj.getTypeId();

				name = new String[]{"area_asia","area_america","area_HKtaiwan","area_euorp"};
				desc = new String[]{"亚洲","美洲","港台","欧洲"};
				for (int i = 0; i < desc.length; i++) {
					obj = new VideoTypeEntity();
					obj.setTypeParent(typeParent);
					obj.setTypeDesc(desc[i]);
					obj.setTypeName(name[i]);
					videoTypeDao.save(obj);
				}
			}
			if (systemConfigDao.count() < 1) {
				SystemConfigEntity entity = new SystemConfigEntity();
				entity.setEmailSubject("找回密码");
				entity.setServerPort(22);
				entity.setServerSmtp("smtp.163.com");
				entity.setSystemEmail("test@163.com");
				entity.setSystemPassword("123456");
				entity.setSystemUsername("test");
				systemConfigDao.save(entity);
			}
            if (pushMessageDao.count()<1){
                PushMessageEntity pushMessageEntity = new PushMessageEntity();
                pushMessageEntity.setMessageContent("有更新新的电影请注意！");
                pushMessageEntity.setMessageTitle("更新内容");
                pushMessageDao.save(pushMessageEntity);
            }
            if(adminDao.count()<1){
                AdminEntity admin=new AdminEntity();
                admin.setAdminName("admin");
                admin.setAdminPass("adminpass");
                admin.setRole("admin");
                admin.setCreateTime(DateUtils.now());
                adminDao.save(admin);
            }
            if(companyInfoDao.count()<1){
                CompanyInfoEntity companyInfoEntity = new CompanyInfoEntity();
                companyInfoEntity.setInfoContent("公司信息");
                companyInfoEntity.setInfoLogoUrl("");
                companyInfoEntity.setInfoTitle("东与文化传媒有限公司");
                companyInfoEntity.setCreateTime("2013-11-11");
                companyInfoDao.save(companyInfoEntity);
            }
            if (loadingAnimationDao.count()<1){
                LoadingAnimationEntity loadingAnimationEntity = new LoadingAnimationEntity();
                loadingAnimationEntity.setLoadingImgUrl("");
                loadingAnimationEntity.setCreateTime("2013-11-11");
                loadingAnimationDao.save(loadingAnimationEntity);
            }
		}
	}
}
