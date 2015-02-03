package com.e_lliam.app.video.server.controller.mobile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.e_lliam.app.video.server.dao.*;
import com.e_lliam.app.video.server.entity.*;
import com.e_lliam.app.video.server.pojo.mobile.*;
import com.e_lliam.app.video.server.utils.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.e_lliam.app.video.server.pojo.ToplineType;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilter;
import com.e_lliam.app.video.server.pojo.extjs.ExtFilters;
import com.e_lliam.app.video.server.utils.extjs.ExtPageRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/mobile")
public class MobileApiController {
    private String imageUrl = "http://115.29.34.81:8888/video-server/";
    @Resource
    IPersonDao personDao;

    @Resource
    IFeedbackDao feedbackDao;

    @Resource
    IVideoDao videoDao;

    @Resource
    IVideoTypeDao videoTypeDao;

    @Resource
    IVideoUrlDao videoUrlDao;

    @Resource
    IVideoHistoryDao videoHistoryDao;

    @Resource
    IVideoTypeRecordDao videoTypeRecordDao;

    @Resource
    ICollectionDao collectionDao;

    @Resource
    ICommentDao commentDao;

    @Resource
    IBusinessNewsDao businessNewsDao;

    @Resource
    ISearchDao searchDao;

    @Resource
    ITokenDao tokenDao;

    @Resource
    IPraiseDao praiseDao;

    @Resource
    IToplineDao toplineDao;

    @Resource
    ISystemConfigDao systemConfigDao;

    @Resource
    IThirdplatDao thirdplatDao;

    @Resource
    IHotHistoryDao iHotHistoryDao;

    @Resource
    IUpdateVersionDao updateVersionDao;

    @Resource
    ITeamDao teamDao;

    @Resource
    ILoadingAnimationDao loadingAnimationDao;

    @Resource
    ICompanyInfoDao companyInfoDao;

    @RequestMapping("/index")
    @ResponseBody
    public MobileResult index() {
        return new MobileResult();
    }

    @RequestMapping("/api/{type}")
    public String api(@PathVariable("type") String type) {
        return "mobile/api/" + type;
    }

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    @ResponseBody
    public MobileLoginResult registerPerson(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "headImage", required = false) MultipartFile image,
            HttpServletRequest request) {
        String uploadDir = request.getSession().getServletContext()
                .getRealPath("/")
                + "/";
        MobileLoginResult mobileResult = new MobileLoginResult();
        if (image != null && image.getSize() > 1024 * 1024 * 1) {
            mobileResult.setMessage("请上传小于1M的图片文件！");
            mobileResult.setStatus(10000);
            return mobileResult;
        }
        if (username != null && !StringUtils.checkEmail(username)) {
            mobileResult.setMessage("请输入正确的邮箱格式！");
            mobileResult.setStatus(10000);
            return mobileResult;
        }
        // 判读用户名是否存在
        if (personDao.findByUserName(username) == null) {
            PersonEntity person = new PersonEntity();
            if (image != null) {
                try {
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    String dest = "upload/headImage/" + month + "/" + day + "/";
                    File parentDir = new File(uploadDir + dest);
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
                    String extension = FilenameUtils.getExtension(image
                            .getOriginalFilename());
                    if (!org.springframework.util.StringUtils
                            .hasText(extension)) {
                        extension += "png";
                    }
                    dest = dest + System.currentTimeMillis() + "." + extension;
                    File destFile = new File(uploadDir + dest);
                    person.setPhotoUrl(dest);
                    image.transferTo(destFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 将密码转换成md5值
            person.setPassWord(MD5Utils.String2MD5(password));
            person.setCreateTime(DateUtils.now());
            TokenEntity token = new TokenEntity(TokenUtils.makeToken(),
                    personDao.save(person).getPersonId());
            person.setUserName(username);
            person.setNickName(nickname);

            // 注册用户
            person = personDao.save(person);
            // 返回的信息
            PersonBean personBean = new PersonBean();
            personBean.setNickName(person.getNickName());
            personBean.setPassword(password);
            if (person.getPhotoUrl() != null) {
                personBean.setPhotoUrl(person.getPhotoUrl());
            }
            personBean.setUserName(person.getUserName());
            tokenDao.save(token);

            mobileResult.setToken(token.getToken());
            mobileResult.setStatus(200);
            mobileResult.setMessage("注册成功");
            mobileResult.setData(personBean);
        } else {
            mobileResult.setMessage("用户名已存在，请重新输入！");
            mobileResult.setStatus(10001);
        }
        return mobileResult;
    }

    @RequestMapping(value = "/user/modify", method = RequestMethod.POST)
    @ResponseBody
    public MobileResult changePersonInfo(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam(value = "gender", required = true) boolean gender,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "nickName", required = false) String nickName,
            @RequestParam(value = "birthday", required = false) String birthday,
            @RequestParam(value = "desc", required = false) String desc,
            @RequestParam(value = "headImage", required = false) MultipartFile image,
            HttpServletRequest request) {

        String uploadDir = request.getSession().getServletContext()
                .getRealPath("/")
                + "/";
        MobileResult result = new MobileResult();
        Long personIdd = tokenDao.getPersonIdByToken(token);
        PersonEntity person;
        if (personIdd != null) {
            person = personDao.findByPersonId(personIdd);
            person.setGender(gender);
            if (password != null) {
                person.setPassWord(MD5Utils.String2MD5(password));
            }
            if (nickName != null) {
                person.setNickName(nickName);
            }
            if (birthday != null) {
                person.setBirthday(birthday);
            }
            if (desc != null) {
                person.setPersonDesc(desc);
            }
            if (image != null) {
                try {
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH) + 1;
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    String dest = "upload/headImage/" + month + "/" + day + "/";
                    File parentDir = new File(uploadDir + dest);
                    if (!parentDir.exists()) {
                        parentDir.mkdirs();
                    }
                    String extension = FilenameUtils.getExtension(image
                            .getOriginalFilename());
                    if (!org.springframework.util.StringUtils.hasText(extension)) {
                        extension += "png";
                    }
                    dest = dest + System.currentTimeMillis() + "." + extension;
                    File destFile = new File(uploadDir + dest);
                    person.setPhotoUrl(dest);
                    image.transferTo(destFile);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            PersonBean personBean = new PersonBean();
            personBean.setNickName(person.getNickName());
            person = personDao.save(person);
            personBean.setPassword(password);
            if (person.getGender() != null) {
                personBean.setGender(person.getGender());
            }
            personBean.setUserName(person.getUserName());
            if (person.getBirthday() != null) {
                personBean.setBirthday(person.getBirthday());
            }
            if (person.getPersonDesc() != null) {
                personBean.setPersonDesc(person.getPersonDesc());
            }
            if (person.getPhotoUrl() != null) {
                personBean.setPhotoUrl(person.getPhotoUrl());
            }
            result.setData(personBean);
            result.setMessage("修改成功");
            result.setStatus(200);
        } else {
            result.setMessage("token无效，请重新登录！");
            result.setStatus(10000);
        }
        return result;
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public MobileLoginResult getPersonInfo(
            @RequestParam("username") String userName,
            @RequestParam("password") String passWord) {
        MobileLoginResult result = new MobileLoginResult();
        PersonEntity person;
        person = personDao.findByUserName(userName);
        // 判断用户名是否存在
        if (personDao.findByUserName(userName) == null) {
            result.setMessage("用户名不存在，请重新输入正确的用户名！");
            result.setStatus(10000);
        } else {
            if (!person.getPassWord().equals(MD5Utils.String2MD5(passWord))) {
                result.setMessage("密码不正确，请重新输入!");
                result.setStatus(10001);
            } else {
                person.setPassWord(passWord);
                TokenEntity token = tokenDao.findByPersonId(person
                        .getPersonId());

                String newToken = TokenUtils.makeToken();
                token.setToken(newToken);
                token.setUpdateTime(DateUtils.now());
                tokenDao.save(token);

                PersonBean personBean = new PersonBean();
                personBean.setNickName(person.getNickName());
                personBean.setPassword(passWord);
                if (person.getGender() != null) {
                    personBean.setGender(person.getGender());
                }
                personBean.setUserName(person.getUserName());
                if (person.getBirthday() != null) {
                    personBean.setBirthday(person.getBirthday());
                }
                if (person.getPersonDesc() != null) {
                    personBean.setPersonDesc(person.getPersonDesc());
                }
                if (person.getPhotoUrl() != null) {
                    personBean.setPhotoUrl(person.getPhotoUrl());
                }

                result.setStatus(200);
                result.setData(personBean);
                result.setMessage("登录成功");
                result.setToken(newToken);
            }
        }
        return result;
    }

    @RequestMapping(value = "/third/login")
    @ResponseBody
    public MobileLoginResult getThirdLoginInfo(
            @RequestParam(value = "thirdId") String thirdId,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "platform") String platform,
            @RequestParam(value = "nickname") String nickname,
            @RequestParam(value = "gender") String gender,
            @RequestParam(value = "headImage", required = false) String headImage) {
        MobileLoginResult loginResult = new MobileLoginResult();
        ThirdplatEntity entity = thirdplatDao.findByThirdId(thirdId);
        TokenEntity token;
        PersonEntity person;
        String tokenStr = null;
        PersonBean bean = new PersonBean();
        if (entity == null) {
            // 生成新用户
            person = new PersonEntity();
            person.setUserName(username);
            person.setNickName(nickname);
            if (gender.equals("1") || gender.equals("m")) {
                person.setGender(true);
            } else {
                person.setGender(false);
            }
            if (headImage != null) {
                person.setPhotoUrl(headImage);
            }
            person.setCreateTime(DateUtils.now());
            person = personDao.save(person);

            // 生成新token
            token = new TokenEntity();
            tokenStr = TokenUtils.makeToken();
            token.setToken(tokenStr);
            token.setUpdateTime(DateUtils.now());
            token.setPersonId(person.getPersonId());
            tokenDao.save(token);

            // 保存第三方应用信息
            entity = new ThirdplatEntity();
            entity.setCreateTime(DateUtils.now());
            entity.setPersonId(person.getPersonId());
            entity.setPlatform(platform);
            entity.setThirdId(thirdId);
            thirdplatDao.save(entity);
        } else {
            person = personDao.findByPersonId(entity.getPersonId());
            token = tokenDao.findByPersonId(person.getPersonId());

            //更新token
            tokenStr = TokenUtils.makeToken();
            token.setToken(tokenStr);
            token.setUpdateTime(DateUtils.now());
            tokenDao.save(token);
        }

        bean.setNickName(person.getNickName());
        bean.setGender(person.getGender());
        if (person.getPhotoUrl() != null) {
            bean.setPhotoUrl(person.getPhotoUrl());
        }
        bean.setUserName(person.getUserName());
        if (person.getPersonDesc() != null)
            bean.setPersonDesc(person.getPersonDesc());
        if (person.getBirthday()!=null){
            bean.setBirthday(person.getBirthday());
        }
        loginResult.setData(bean);
        loginResult.setToken(tokenStr);
        loginResult.setMessage("登录成功");
        loginResult.setStatus(200);
        return loginResult;
    }

    @RequestMapping(value = "/user/record/add")
    @ResponseBody
    public MobileResult postVideoRecord(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "videoId", required = true) Long videoId) {
        MobileResult result = new MobileResult();
        VideoHistoryEntity entity = new VideoHistoryEntity();
        if (token != null) {
            Long personId = tokenDao.getPersonIdByToken(token);
            if (personId != null) {
                entity.setPersonId(personId);
                entity.setUserName(personDao.findByPersonId(personId).getUserName());
            } else {
                result.setMessage("token无效，请重新登录！");
                result.setStatus(10000);
                return result;
            }
        }
        entity.setVideoId(videoId);
        entity.setVideoTitle(videoDao.findByVideoId(videoId).getVideoTitle());
        entity.setCreateTime(DateUtils.now());
        videoHistoryDao.save(entity);

        VideoEntity videoEntity = videoDao.findByVideoId(videoId);

        HotHistoryEntity hotHistoryEntity = iHotHistoryDao.findByVideoIdAndTypeId(videoId, 10L);
        String currentDay = DateUtils.getDay();
        String currentWeek = DateUtils.getWeekFirshDay();
        String currentMonty = DateUtils.getMonthDay();
        if (hotHistoryEntity != null) {
            if (hotHistoryEntity.getCurrentDay().equals(currentDay)) {
                hotHistoryEntity.setTodayCount(hotHistoryEntity.getTodayCount() + 1);
            } else {
                hotHistoryEntity.setTodayCount(1);
                hotHistoryEntity.setCurrentDay(currentDay);
            }
            if (hotHistoryEntity.getCurrrentMonth().equals(currentMonty)) {
                hotHistoryEntity.setMonthCount(hotHistoryEntity.getMonthCount() + 1);
            } else {
                hotHistoryEntity.setCurrrentMonth(currentMonty);
                hotHistoryEntity.setMonthCount(1);
            }
            if (hotHistoryEntity.getCurrrentWeek().equals(currentWeek)) {
                hotHistoryEntity.setWeekCount(hotHistoryEntity.getWeekCount() + 1);
            } else {
                hotHistoryEntity.setWeekCount(1);
                hotHistoryEntity.setCurrrentWeek(currentWeek);
            }
            hotHistoryEntity.setCount(hotHistoryEntity.getCount() + 1);
            hotHistoryEntity.setCreateTime(DateUtils.now());
            iHotHistoryDao.save(hotHistoryEntity);
        } else {
            hotHistoryEntity = new HotHistoryEntity();
            hotHistoryEntity.setCount(1);
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setVideoTitle(videoEntity.getVideoTitle());
            hotHistoryEntity.setCurrentDay(DateUtils.getDay());
            hotHistoryEntity.setCurrrentMonth(DateUtils.getMonthDay());
            hotHistoryEntity.setCurrrentWeek(DateUtils.getWeekFirshDay());
            hotHistoryEntity.setTodayCount(1);
            hotHistoryEntity.setWeekCount(1);
            hotHistoryEntity.setMonthCount(1);
            hotHistoryEntity.setTypeId(10L);
            hotHistoryEntity.setVideoId(videoId);
            iHotHistoryDao.save(hotHistoryEntity);
        }

        result.setMessage("成功");
        result.setStatus(200);
        return result;
    }

    @RequestMapping(value = "/user/feedback")
    @ResponseBody
    public MobileResult postFeedback(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "content") String feedbackContent) {
        MobileResult result = new MobileResult();
        FeedbackEntity entity = new FeedbackEntity();
        entity.setCreateTime(new Date(System.currentTimeMillis()));
        if (token != null) {
            Long personId = tokenDao.getPersonIdByToken(token);
            if (personId != null) {
                entity.setPersonId(personId);
                entity.setUsername(personDao.findByPersonId(personId).getUserName());
            } else {
                result.setStatus(10000);
                result.setMessage("token无效，请重新登录!");
                return result;
            }
        }
        entity.setFeedbackContent(feedbackContent);
        feedbackDao.save(entity);
        result.setMessage("提交成功");
        result.setStatus(200);
        return result;
    }

    @RequestMapping(value = "/user/collection/add")
    @ResponseBody
    public MobileResult postCollection(
            @RequestParam(value = "token") String token,
            @RequestParam("videoId") Long videoId) {
        MobileResult result = new MobileResult();
        Long personId = tokenDao.getPersonIdByToken(token);
        CollectionEntity entity = new CollectionEntity();
        VideoEntity videoEntity = videoDao.findByVideoId(videoId);
        entity.setVideoId(videoId);
        entity.setVideoTitle(videoEntity.getVideoTitle());
        PersonEntity person = personDao.findByPersonId(personId);
        if (personId != null) {
            entity.setPersonId(personId);
            entity.setUserName(person.getUserName());
            entity.setCreateTime(DateUtils.now());
            if (collectionDao.findByPersonIdAndVideoId(personId, videoId).size() > 0) {
                result.setMessage("收藏失败,你已经收藏过该视频！");
                result.setStatus(10001);
            } else {
                CollectionEntity save = collectionDao.save(entity);
                result.setData(save);
                result.setMessage("收藏成功!");
                result.setStatus(200);
                HotHistoryEntity hotHistoryEntity = iHotHistoryDao.findByVideoIdAndTypeId(save.getVideoId(), 15L);
                if (hotHistoryEntity == null) {
                    hotHistoryEntity = new HotHistoryEntity();
                    hotHistoryEntity.setCount(1);
                    hotHistoryEntity.setCreateTime(DateUtils.now());
                    hotHistoryEntity.setVideoId(save.getVideoId());
                    hotHistoryEntity.setTypeId(15L);
                    iHotHistoryDao.save(hotHistoryEntity);
                } else {
                    hotHistoryEntity.setCount(hotHistoryEntity.getCount() + 1);
                    iHotHistoryDao.save(hotHistoryEntity);
                }
            }
        } else {
            result.setMessage("token无效，请重新登录！");
            result.setStatus(10000);
        }
        return result;
    }

    @RequestMapping("/user/collection/list")
    @ResponseBody
    public MobileResult getCollectionList(
            @RequestParam(value = "token") String token,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer size) {
        MobileResult result = new MobileResult();
        Long personId = tokenDao.getPersonIdByToken(token);
        if (personId != null) {
            Page<CollectionEntity> list = collectionDao.findByPersonId(personId,
                    new ExtPageRequest(page, size));
            List<CollectionBean> beans = Lists.newArrayList();
            CollectionBean bean;
            VideoEntity entity;
            for (CollectionEntity collectionEntity : list.getContent()) {
                bean = new CollectionBean();
                entity = videoDao.findByVideoId(collectionEntity.getVideoId());
                bean.setVideoId(collectionEntity.getVideoId());
                bean.setVideoLength("");
                bean.setVideoPreviewPicUrl(entity.getVideoPreviewPicUrl());
                bean.setVideoTitle(entity.getVideoTitle());
                beans.add(bean);
            }
            result.setData(beans);
            result.setMessage("获取成功");
            result.setStatus(200);
        } else {
            result.setMessage("token无效，请重新登录");
            result.setStatus(10000);
        }
        return result;
    }

    @RequestMapping(value = "/user/collection/del")
    @ResponseBody
    public MobileResult delCollection(
            @RequestParam(value = "token", required = true) String token,
            @RequestParam("videoId") Long videoId) {
        MobileResult result = new MobileResult();
        Long personId = tokenDao.getPersonIdByToken(token);
        if (personId != null) {
            List<CollectionEntity> list = collectionDao.findByPersonIdAndVideoId(
                    personId, videoId);
            if (list.size() != 0) {
                CollectionEntity entity = list.get(0);
                collectionDao.delete(entity);
                result.setMessage("取消收藏成功！");
                result.setStatus(200);
            } else {
                result.setMessage("取消收藏失败，您还没有对该视频进行收藏！");
                result.setStatus(10001);
            }

        } else {
            result.setMessage("token无效，请重新登录！");
            result.setStatus(10000);
        }
        return result;
    }

    @RequestMapping(value = "/user/findPassword")
    @ResponseBody
    public MobileResult findThePassword(
            @RequestParam("username") String userName) {
        MobileResult result = new MobileResult();
        PersonEntity person = personDao.findByUserName(userName);
        if (person == null) {
            result.setMessage("查无此用户，请重新输入！");
            result.setStatus(10001);
        } else {
            String password = MD5Utils.getPassWord(2, 6);
            SystemConfigEntity entity = systemConfigDao.findOne(1L);
            // 发送邮件
            // 主题
            String subject = entity.getEmailSubject();
            // 正文
            StringBuilder builder = new StringBuilder();
            builder.append("<html><head>");
            builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
            builder.append("</head><body>");
            builder.append("您好，" + person.getUserName() + "<br />");
            builder.append("\t系统已为您重置了您的密码，账户信息如下：<br />");
            builder.append("用户账户：" + person.getUserName() + "<br />用户密码："
                    + password + "<br />");
            builder.append("</a>");
            builder.append("</body></html>");
            String htmlContent = builder.toString();

            // 修改密码
            person.setPassWord(MD5Utils.String2MD5(password));
            personDao.updatePassword(person.getPersonId(), password);
            // if (personDao.save(person) != null) {
            result.setMessage("修改密码成功！已经将您的密码发送到您的安全邮箱，请注意查收！");
            result.setStatus(200);
            try {
                MailUtils.sendMail(person.getUserName(), subject, htmlContent,
                        entity);
            } catch (Exception e) {

            }
        }
        return result;
    }

    @RequestMapping("/guessLike")
    @ResponseBody
    public MobileResult getGuessLike() {
        MobileResult result = new MobileResult();
        List<SearchResultBean> list = Lists.newArrayList();
        Page<VideoEntity> page = videoDao.findByVideoTitleIsNotNullOrderByVideoIdDesc(new ExtPageRequest(1, 12));
        addBean(list, page.getContent());
        // TODO
        result.setMessage("获取成功");
        result.setStatus(200);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/user/praise/add")
    @ResponseBody
    public MobileResult postPraise(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "videoId") Long videoId,
            HttpServletRequest request) {
        MobileResult result = new MobileResult();
        PraiseEntity entity;
        Long personId = null;
        HotHistoryEntity hotHistoryEntity = iHotHistoryDao.findByVideoIdAndTypeId(videoId, 15L);
        if (hotHistoryEntity != null) {
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setCount(hotHistoryEntity.getCount() + 1);
            iHotHistoryDao.save(hotHistoryEntity);
        } else {
            hotHistoryEntity = new HotHistoryEntity();
            hotHistoryEntity.setCount(1);
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setTypeId(15L);
            hotHistoryEntity.setVideoId(videoId);
            iHotHistoryDao.save(hotHistoryEntity);
        }
        if (token != null) {
            entity = new PraiseEntity();
            personId = tokenDao.getPersonIdByToken(token);
            PersonEntity person = personDao.findByPersonId(personId);
            if (personId != null) {
                entity.setPersonId(personId);
                entity.setUserName(person.getUserName());
                if (videoDao.findOne(videoId) != null) {
                    entity.setCreateTime(DateUtils.now());
                    entity.setVideoId(videoId);
                    entity.setVideoTitle(videoDao.findByVideoId(videoId).getVideoTitle());
                    if (praiseDao.findByPersonIdAndVideoId(personId, videoId)
                            .size() > 0) {
                        result.setMessage("点赞失败，您已经赞过了!");
                        result.setStatus(10001);
                    } else {
                        praiseDao.save(entity);
                        result.setData(praiseDao.findByVideoId(videoId).size());
                        result.setMessage("点赞成功！");
                        result.setStatus(200);
                    }
                }
            } else {
                result.setMessage("token无效，请重新登录！");
                result.setStatus(10000);
            }
        } else {
            entity = new PraiseEntity();
            entity.setCreateTime(DateUtils.now());
            VideoEntity videoEntity = videoDao.findByVideoId(videoId);
            entity.setVideoId(videoEntity.getVideoId());
            entity.setVideoTitle(videoEntity.getVideoTitle());
            praiseDao.save(entity);
            result.setData(praiseDao.findByVideoId(videoId).size());
            result.setMessage("点赞成功！");
            result.setStatus(200);
        }
        return result;
    }

    @RequestMapping(value = "/user/praise/del")
    @ResponseBody
    public MobileResult delPraise(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "videoId") Long videoId) {
        MobileResult result = new MobileResult();
        if (token != null) {
            Long personId = tokenDao.getPersonIdByToken(token);
            if (personId != null) {


                List<PraiseEntity> list = praiseDao.findByPersonIdAndVideoId(
                        personId, videoId);
                if (list.size() > 0) {
                    PraiseEntity entity = list.get(0);
                    praiseDao.delete(entity);
                    result.setStatus(200);
                    result.setMessage("取消赞成功！");
                } else {
                    result.setMessage("取消赞失败,操作对象不存在!");
                    result.setStatus(10001);
                }
            } else {
                result.setMessage("token无效，请重新登录！");
                result.setStatus(10000);
            }
        } else {
            result.setMessage("取消赞成功!");
            result.setStatus(200);
        }
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping("/channel/videoType")
    @ResponseBody
    public MobileResult getVideoType() {
        MobileResult result = new MobileResult();
        List<VideoTypeBean> list = Lists.newArrayList();
        VideoTypeBean typeBean;
        Collection<VideoTypeEntity> col = videoTypeDao.findByTypeParentIsNull();
        for (Iterator i = col.iterator(); i.hasNext(); ) {
            VideoTypeEntity entity = (VideoTypeEntity) i.next();
            typeBean = new VideoTypeBean();
            typeBean.setTypeId(entity.getTypeId());
            typeBean.setTypeName(entity.getTypeName());
            typeBean.setTypeDesc(entity.getTypeDesc());
            typeBean.setList(videoTypeDao.findByTypeParent(entity.getTypeId()));
            list.add(typeBean);
        }

        result.setMessage("获取成功");
        result.setStatus(200);
        result.setData(list);
        return result;
    }

    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/video/detail")
    @ResponseBody
    public MobileResult getVideoDetail(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "videoId") Long videoId) {
        MobileResult result = new MobileResult();
        VideoEntity video = videoDao.findByVideoId(videoId);

        if (video == null) {
            result.setMessage("查无此视频记录");
        } else {
            VideoDetailBean detailBean = new VideoDetailBean();
            detailBean.setVideoId(video.getVideoId());
            detailBean.setVideoActor(video.getVideoActor());
            detailBean.setVideoDesc(video.getVideoDesc());
            detailBean.setVideoDirector(video.getVideoDirector());
            detailBean.setVideoPreviewPicUrl(video.getVideoPreviewPicUrl());
            detailBean.setVideoRegion(videoTypeDao.findOne(
                    video.getVideoRegion()).getTypeDesc());
            detailBean.setVideoTitle(video.getVideoTitle());
            detailBean.setVideoDesc(video.getVideoDesc());
            detailBean.setVideoYear(videoTypeDao.findOne(video.getVideoYear())
                    .getTypeDesc());
            detailBean.setPraise(praiseDao.findByVideoId(video.getVideoId())
                    .size());

            // 获取剧集信息
            Collection<VideoUrlEntity> col = videoUrlDao
                    .findByVideoIdOrderByVideoUrlIndexAsc(video.getVideoId());

            List<VideoUrlBean> urlList = Lists.newArrayList();
            VideoUrlEntity videoUrlEntity;
            VideoUrlBean urlBean;
            for (Iterator it = col.iterator(); it.hasNext(); ) {
                urlBean = new VideoUrlBean();
                videoUrlEntity = (VideoUrlEntity) it.next();
                urlBean.setVideoPlayUrl(videoUrlEntity.getVideoPlayUrl());
                urlBean.setVideoUrlDesc(videoUrlEntity.getVideoUrlDesc());
                urlBean.setVideoWebUrl(videoUrlEntity.getVideoWebUrl());
                urlBean.setVideoUrlIndex(videoUrlEntity.getVideoUrlIndex());
                urlList.add(urlBean);
            }
            detailBean.setList(urlList);

            if (token != null) {
                Long personId = tokenDao.getPersonIdByToken(token);
                // 是否收藏过
                if (collectionDao.findByPersonIdAndVideoId(personId, videoId)
                        .size() > 0) {
                    detailBean.setCollect(true);
                } else
                    detailBean.setCollect(false);
                // 是否赞过
                if (praiseDao.findByPersonIdAndVideoId(personId, videoId)
                        .size() > 0) {
                    detailBean.setPraised(true);
                } else
                    detailBean.setPraised(false);
            }
            result.setMessage("获取成功");
            result.setData(detailBean);
            result.setStatus(200);
        }
        return result;
    }

    @RequestMapping("/video/comment/list")
    @ResponseBody
    public MobileResult getCommentList(
            @RequestParam(value = "videoId") Long videoId,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        MobileResult result = new MobileResult();
        Page<CommentEntity> datas = commentDao.findByVideoId(videoId,
                new ExtPageRequest(currentPage, pageSize));
        List<CommentBean> commentList = Lists.newArrayList();
        CommentBean commentBean;
        CommentEntity commentEntity;
        for (int i = 0; i < datas.getNumberOfElements(); i++) {
            commentBean = new CommentBean();
            commentEntity = datas.getContent().get(i);
            commentBean.setCommentContent(commentEntity.getCommentContent());
            commentBean.setNickname(personDao.findByPersonId(
                    commentEntity.getPersonId()).getNickName());
            commentBean.setCommentTime(commentEntity.getCreateTime());
            commentList.add(commentBean);
        }
        result.setStatus(200);
        result.setMessage("success");
        result.setData(commentList);
        return result;
    }

    @RequestMapping(value = "/video/comment/add")
    @ResponseBody
    public MobileResult postComment(@RequestParam("token") String token,
                                    @RequestParam("content") String commentContent,
                                    @RequestParam("videoId") Long videoId) {
        MobileResult result = new MobileResult();
        CommentEntity entity = new CommentEntity();
        Long personId = tokenDao.getPersonIdByToken(token);
        PersonEntity person = personDao.findByPersonId(personId);
        VideoEntity videoEntity = videoDao.findByVideoId(videoId);
        if (personId != null) {
            entity.setPersonId(personId);
            entity.setVideoTitle(videoEntity.getVideoTitle());
            entity.setUserName(person.getUserName());
            entity.setCommentContent(commentContent);
            entity.setCreateTime(DateUtils.now());
            entity.setVideoId(videoId);
            entity.setStatus(0);
            entity = commentDao.save(entity);
            result.setMessage("评论成功");
            result.setStatus(200);
        } else {
            result.setMessage("token无效，请重新登录！");
            result.setStatus(10000);
        }
        HotHistoryEntity hotHistoryEntity = iHotHistoryDao.findByVideoIdAndTypeId(videoId, 15L);
        if (hotHistoryEntity == null) {
            hotHistoryEntity = new HotHistoryEntity();
            hotHistoryEntity.setCount(1);
            hotHistoryEntity.setCreateTime(DateUtils.now());
            hotHistoryEntity.setVideoId(entity.getVideoId());
            hotHistoryEntity.setTypeId(15L);
            iHotHistoryDao.save(hotHistoryEntity);
        } else {
            hotHistoryEntity.setCount(hotHistoryEntity.getCount() + 1);
            iHotHistoryDao.save(hotHistoryEntity);
        }
        return result;
    }

    @RequestMapping("/news/list")
    @ResponseBody
    public MobileResult getNewsList(
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        MobileResult result = new MobileResult();
        Map<String, Object> maps = Maps.newHashMap();
        List<NewsBriefBean> wheels = Lists.newArrayList();
        List<NewsBean> news = Lists.newArrayList();
        NewsBriefBean briefBean;
        List<BusinessNewsEntity> wheel = businessNewsDao
                .getByTopline(ToplineType.toInt("news"));
        for (BusinessNewsEntity businessNewsEntity : wheel) {
            briefBean = new NewsBriefBean();
            briefBean.setNewsId(businessNewsEntity.getNewsId());
            briefBean.setNewsWheelPicUrl(businessNewsEntity
                    .getNewsWheelPicUrl());
            briefBean.setNewsTitle(businessNewsEntity.getNewsTitle());
            wheels.add(briefBean);
        }
        List<BusinessNewsEntity> newsList = businessNewsDao.getByNewsTitleIsNotNullOrderByNewsIdDesc(
                new ExtPageRequest(currentPage, pageSize)).getContent();
        NewsBean newsBean;
        for (BusinessNewsEntity businessNewsEntity : newsList) {
            newsBean = new NewsBean();
            newsBean.setNewsId(businessNewsEntity.getNewsId());
            newsBean.setNewsTitle(businessNewsEntity.getNewsTitle());
            news.add(newsBean);
        }
        if (wheel.size() > 12) {
            wheel = wheel.subList(0, 12);
        }

        maps.put("wheels", wheels);
        maps.put("news", news);

        result.setData(maps);
        result.setMessage("成功");
        result.setStatus(200);
        return result;
    }

    @RequestMapping("/news/more")
    @ResponseBody
    public MobileResult getMoreNews(
            @RequestParam(value = "currentPage") Integer page,
            @RequestParam(value = "pageSize") Integer size) {
        MobileResult result = new MobileResult();
        List<NewsBean> news = Lists.newArrayList();
        NewsBean newsBean;
        List<BusinessNewsEntity> newsList = businessNewsDao.findAll(
                new ExtPageRequest(page, size)).getContent();
        for (BusinessNewsEntity businessNewsEntity : newsList) {
            newsBean = new NewsBean();
            newsBean.setNewsId(businessNewsEntity.getNewsId());
            newsBean.setNewsTitle(businessNewsEntity.getNewsTitle());
            news.add(newsBean);
        }
        result.setData(news);
        result.setMessage("成功");
        result.setStatus(200);
        return result;
    }

    private static final List<String> StringFields = Arrays.asList(
            "videoTitle", "videoBrief", "videoDesc", "videoDirector",
            "videoScriptwriter", "videoActor");

    @RequestMapping(value = "/search/video")
    @ResponseBody
    public MobileResult getSearchResult(
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "searchKey", required = false) String searchKey,
            @RequestParam(value = "typeId", required = true) Long typeId,
            @RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
            @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize) {
        MobileResult result = new MobileResult();
        Map<String, Object> maps = Maps.newHashMap();
        List<SearchResultBean> beans = Lists.newArrayList();
        List<ExtFilter> list;
        ExtFilter filter;
        ExtFilter filter2;
        ExtFilters filters;
        list = Lists.newArrayList();
        if (searchKey == null && typeId.intValue() == -1) {
            Page<VideoEntity> page = videoDao.findAll(new ExtPageRequest(
                    currentPage, pageSize));
            addBean(beans, page.getContent());
        } else {
            // 保存搜索关键词
            Long personId = tokenDao.getPersonIdByToken(token);
            if (searchKey != null) {
                SearchEntity lists = searchDao.findBySearchKey(searchKey);
                if (lists != null) {
                    lists.setSearchCount(lists.getSearchCount() + 1);
                } else {
                    lists = new SearchEntity();
                    lists.setSearchCount(1);
                }
                if (personId != null) {
                    lists.setPersonId(personId);
                }
                lists.setSearchKey(searchKey);
                lists.setCreateTime(DateUtils.now());
                searchDao.save(lists);

                for (String str : StringFields) {
                    filter2 = new ExtFilter(str, searchKey);
                    list.add(filter2);
                }
            } else if (typeId > 0) {
                if (typeId == 50) {
                    List<VideoEntity> page = videoDao.getByTopline(ToplineType.toInt("hot"));
                    addBean(beans, page);
                    if (beans.size() > 12){
                        beans = beans.subList(0,12);
                    }
                    maps.put("result", beans);
                    maps.put("like", null);
                    result.setMessage("共搜索到" + beans.size() + "个结果");
                    result.setData(maps);
                    result.setStatus(200);
                    return result;
                }
                Long parentId = videoTypeDao.findOne(typeId).getTypeParent();
                if (parentId != null) {
                    String type = videoTypeDao.findOne(parentId).getTypeName();
                    if (type.equals("year")) {
                        filter = new ExtFilter("videoYear", typeId);
                        list.add(filter);
                    } else if (type.equals("area")) {
                        filter = new ExtFilter("videoRegion", typeId);
                        list.add(filter);
                    } else if (type.equals("label")) {
                        filter = new ExtFilter("typeId", typeId);
                        list.add(filter);
                    } else {
                        List<VideoEntity> page = null;
                        if (typeId == 15) {
                            page = videoDao.getByHotHistory(typeId);
                        } else if (typeId == 14) {
                            page = videoDao.getByHotHistory(typeId);
                        } else if (typeId == 13) {
                            page = videoDao.findVideoWithHotHistory(new ExtPageRequest(currentPage, pageSize), typeId, null).getContent();
                        } else if (typeId == 10) {
                            page = videoDao.findVideoWithHotHistory(new ExtPageRequest(currentPage, pageSize), typeId, DateUtils.getDay()).getContent();
                        } else if (typeId == 11) {
                            page = videoDao.findVideoWithHotHistory(new ExtPageRequest(currentPage, pageSize), typeId, DateUtils.getWeekFirshDay()).getContent();
                        } else if (typeId == 12) {
                            page = videoDao.findVideoWithHotHistory(new ExtPageRequest(currentPage, pageSize), typeId, DateUtils.getMonthDay()).getContent();
                        }
                        addBean(beans, page);
                        maps.put("result", beans);
                        maps.put("like", null);
                        result.setMessage("共搜索到" + beans.size() + "个结果");
                        result.setData(maps);
                        result.setStatus(200);
                        return result;
                    }
                }
            }

            filters = new ExtFilters(list);
            Page<VideoEntity> page = videoDao.findVideoWithTopline(
                    new ExtPageRequest(currentPage, pageSize), filters);
            addBean(beans, page.getContent());
        }

        if (beans.size() != 0) {
            maps.put("result", beans);
            maps.put("like", null);
            result.setMessage("共搜索到" + beans.size() + "个结果");
        } else {
            List<SearchResultBean> like = Lists.newArrayList();
            Page<VideoEntity> page = videoDao.findByVideoTitleIsNotNullOrderByVideoIdDesc(new ExtPageRequest(1, 9));
            addBean(like, page.getContent());
            maps.put("result", null);
            maps.put("like", like);
            result.setMessage("查无结果！");
        }
        result.setData(maps);
        result.setStatus(200);
        return result;
    }

    public void addBean(List<SearchResultBean> beans, List<VideoEntity> page) {
        SearchResultBean bean;
        Long videoId;
//        if (page.getNumberOfElements() != 0) {
//            List<VideoEntity> entities = page;
        for (VideoEntity videoEntity : page) {
            bean = new SearchResultBean();
            videoId = videoEntity.getVideoId();
            bean.setVideoId(videoId);
            bean.setVideoActor(videoEntity.getVideoActor());
            bean.setVideoDirector(videoEntity.getVideoDirector());
            bean.setVideoPreviewPicUrl(videoEntity.getVideoPreviewPicUrl());
            bean.setVideoRegion(videoTypeDao.findOne(
                    videoEntity.getVideoRegion()).getTypeName());
            bean.setVideoTitle(videoEntity.getVideoTitle());
            bean.setVideoYear(videoTypeDao.findOne(
                    videoEntity.getVideoYear()).getTypeName());
            bean.setPraise(praiseDao
                    .findByVideoId(videoEntity.getVideoId()).size());
            bean.setCommentNumber(commentDao.findByVideoId(videoId).size());
            bean.setVideoBrief(videoEntity.getVideoBrief());
            beans.add(bean);
        }
//        }
    }

    @RequestMapping("/search/hotWord")
    @ResponseBody
    public MobileResult getHotWord() {
        MobileResult result = new MobileResult();
        Page<SearchEntity> page = searchDao.findBySearchKeyIsNotNullOrderBySearchCountDesc(new ExtPageRequest(1, 9));

        result.setMessage("热门搜索");
        result.setData(page.getContent());
        result.setStatus(200);
        return result;
    }

    @RequestMapping("/jsp")
    public String mobileIndex() {
        return "mobile/index";
    }

    @RequestMapping("/home")
    @ResponseBody
    public MobileResult getHomeInfo() {
        MobileResult result = new MobileResult();
        List<VideoBriefBean> wheels = Lists.newArrayList();
        VideoBriefBean wb = null;
        List<VideoEntity> wheel = videoDao.getByTopline(ToplineType
                .toInt("wheel"));
        List<SearchResultBean> beans = Lists.newArrayList();
        VideoEntity entity;
        for (int i = 0; i < wheel.size(); i++) {
            entity = wheel.get(i);
            wb = new VideoBriefBean();
            wb.setPicUrl(entity.getVideoWheelPicUrl());
            wb.setVideoBrief(entity.getVideoBrief());
            wb.setVideoTitle(entity.getVideoTitle());
            wb.setVideoId(entity.getVideoId());
            wb.setPraise(0);
            wb.setComment(0);
            wheels.add(wb);
        }

        List<VideoBriefBean> hots = Lists.newArrayList();
        List<VideoEntity> hot = videoDao.getByTopline(ToplineType.toInt("hot"));
        addBean(beans, hot);

        Map<String, Object> container = Maps.newHashMap();
        if (wheels.size() > 12) {
            wheels = wheels.subList(0, 12);
        }
        if (beans.size() > 12) {
            beans = beans.subList(0, 12);
        }
        container.put("wheel", wheels);
        container.put("hot", beans);
        result.setData(container);
        result.setMessage("获取成功");
        result.setStatus(200);
        return result;
    }

    @RequestMapping("/news/detail")
    @ResponseBody
    public MobileResult getNewsDetail(
            @RequestParam(value = "newsId") Long newsId) {
        MobileResult result = new MobileResult();
        BusinessNewsEntity businessNewsEntity = businessNewsDao.findOne(newsId);
        BusinessNewsBean newsBean = new BusinessNewsBean();
        newsBean.setCreateTime(businessNewsEntity.getCreateTime());
        newsBean.setNewsFrom("东娱文化传媒");
        newsBean.setNewsTitle(businessNewsEntity.getNewsTitle());
        newsBean.setNewsWheelPicUrl(businessNewsEntity.getNewsWheelPicUrl());
        String content = businessNewsEntity.getNewsContent();
        content = StringUtils.splitAndFilterString(content, content.length());
        content = "<div><b style='color:8d919d;margin:4px;'>" + content
                + "</b></div>";
        String div = "<div style='margin:4px;' align='center'><b style='color:black;margin:4px 4px 10px 4px;' font-size:9px>"
                + businessNewsEntity.getNewsTitle() + "</b><br></div>";
        div += "<p style='color:black;margin:4px;font-size:85%'>"
                + DateUtils.getFormat(businessNewsEntity.getCreateTime()) + ""
                + "&nbsp;&nbsp;&nbsp;来源：东娱文化传媒</p>";
        div += "<div><img style='width:100%;' src='" + imageUrl
                + businessNewsEntity.getNewsWheelPicUrl()
                + "'/></div><br>&nbsp;&nbsp;&nbsp;";
        newsBean.setNewsContent(div + content);
        result.setData(newsBean);
        result.setMessage("获取成功");
        result.setStatus(200);
        return result;
    }

    @RequestMapping("/play")
    @ResponseBody
    public MobileResult playVideo(@RequestParam("url") String htmlUrl, @RequestParam("urlFrom") String urlFrom) {
        MobileResult mobileResult = new MobileResult();
        List<String> list = Lists.newArrayList();
        if (urlFrom.equals("优酷")) {
            list = GetYoukuMP4Path.getPath(htmlUrl);
        } else if (urlFrom.equals("乐视")) {
            try {
                list = GetLeTVPath.GetLeTV(htmlUrl);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (urlFrom.equals("爱奇艺")) {
            list = GetAiqiyiMp4Path.getIQIYIUrl(htmlUrl);
        } else if (urlFrom.equals("搜狐")) {
            list = GetSouhuVideoMp4Path.GetSohuTV(htmlUrl);
        } else if (urlFrom.equals("腾讯")) {
            list = GetTencentMp4Path.getTencentUrl(htmlUrl);
        }

        mobileResult.setData(list);
        mobileResult.setMessage("获取成功");
        mobileResult.setStatus(200);
        return mobileResult;
    }

    @RequestMapping("/update")
    @ResponseBody
    public MobileResult checkUpdate(@RequestParam("client") Integer client, @RequestParam("currentVersion") String currentVersion) {
        MobileResult mobileResult = new MobileResult();
        UpdateVersionEntity updateVersionEntity = updateVersionDao.findOne(1L);
        //判断client是android还是ios的客户端，1是android，2是ios客户端；
        if (client == 1) {
            if (currentVersion.equals(updateVersionEntity.getAndroidVersion())) {
                mobileResult.setMessage("当前版本是最新版本！");
                mobileResult.setStatus(200);
                mobileResult.setData(null);
            } else {
                mobileResult.setStatus(200);
                mobileResult.setMessage("当前版本不是最新的版本！请更新成最新版本！");
                UpdateVersionBean updateVersionBean = new UpdateVersionBean();
                updateVersionBean.setDesc(updateVersionEntity.getAndroidVersionDesc());
                updateVersionBean.setUrl(updateVersionEntity.getAndroidUpdateUrl());
                updateVersionBean.setVersion(updateVersionEntity.getAndroidVersion());
                updateVersionBean.setUpdate(true);
                mobileResult.setData(updateVersionBean);
            }
        } else {
            if (currentVersion.equals(updateVersionEntity.getIosVersion())) {
                mobileResult.setMessage("当前版本是最新版本！");
                mobileResult.setStatus(200);
                mobileResult.setData(null);
            } else {
                mobileResult.setStatus(200);
                mobileResult.setMessage("当前版本不是最新的版本！请更新成最新版本！");
                UpdateVersionBean updateVersionBean = new UpdateVersionBean();
                updateVersionBean.setDesc(updateVersionEntity.getIosVersionDesc());
                updateVersionBean.setUrl(updateVersionEntity.getIosUpdateUrl());
                updateVersionBean.setVersion(updateVersionEntity.getIosVersion());
                updateVersionBean.setUpdate(true);
                mobileResult.setData(updateVersionBean);
            }
        }

        return mobileResult;
    }

    @RequestMapping("/company/info")
    @ResponseBody
    public MobileResult getCompany() {
        MobileResult mobileResult = new MobileResult();
        CompanyInfoEntity companyInfoEntity = companyInfoDao.findOne(1L);
        CompanyInfoBean companyInfoBean = new CompanyInfoBean();
        String content = companyInfoEntity.getInfoContent();

        content = "<div><b style='color:8d919d;margin:4px;'>" + content
                + "</b></div>";
        String div = "";
        div += "<div><img style='width:100%;' src='" + imageUrl
                + companyInfoEntity.getInfoLogoUrl()
                + "'/></div><br>&nbsp;&nbsp;&nbsp;";
        companyInfoBean.setCompanyContent(div + content);
        mobileResult.setData(companyInfoBean);
        mobileResult.setStatus(200);
        mobileResult.setMessage("获取成功");
        return mobileResult;
    }

    @RequestMapping("/loading/img")
    @ResponseBody
    public MobileResult getLoadingImg() {
        MobileResult mobileResult = new MobileResult();
        LoadingAnimationEntity loadingAnimationEntity = loadingAnimationDao.findOne(1L);
        LoadingImgBean loadingImgBean = new LoadingImgBean();
        loadingImgBean.setLoadingImgUrl(loadingAnimationEntity.getLoadingImgUrl());

        mobileResult.setData(loadingImgBean);
        mobileResult.setStatus(200);
        mobileResult.setMessage("获取成功");
        return mobileResult;
    }

    @RequestMapping("/team/info")
    @ResponseBody
    public MobileResult getTeamInfo(@RequestParam(value = "currentPage", required = false, defaultValue = "1") Integer currentPage,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "9") Integer pageSize,
                                    @RequestParam(value = "teamTypeId") Long teamTypeId) {
        MobileResult mobileResult = new MobileResult();
        Page<TeamEntity> page = teamDao.findByTeamTypeIdOrderByTeamIdDesc(new ExtPageRequest(currentPage, pageSize), teamTypeId);
        mobileResult.setData(page.getContent());
        mobileResult.setStatus(200);
        mobileResult.setMessage("获取成功");
        return mobileResult;
    }

    @RequestMapping("/team/detail")
    @ResponseBody
    public MobileResult getTeamInfoDetail(@RequestParam(value = "teamId") Long teamId) {
        MobileResult mobileResult = new MobileResult();
        TeamEntity teamEntity = teamDao.findOne(teamId);

        String content = teamEntity.getTeamContent();

        content = "<div><b style='color:8d919d;margin:4px;'>" + content
                + "</b></div>";
        String div = "<div style='margin:4px;' align='center'><b style='color:black;margin:4px 4px 10px 4px;' font-size:9px>"
                + teamEntity.getTeamTitle() + "</b><br></div>";
        div += "<div><img style='width:100%;' src='" + imageUrl
                + teamEntity.getTeamImgContentUrl()
                + "'/></div><br>&nbsp;&nbsp;&nbsp;";
        TeamDetailBean teamDetailBean = new TeamDetailBean();
        teamDetailBean.setTeamContent(div + content);
        teamDetailBean.setTeamUrl(teamEntity.getPartnerUrl());
        mobileResult.setData(teamDetailBean);
        mobileResult.setStatus(200);
        mobileResult.setMessage("获取成功");
        return mobileResult;
    }

    @RequestMapping("/company/web")
    @ResponseBody
    public MobileResult getWebSiteUrl(){
        MobileResult mobileResult = new MobileResult();
        CompanyInfoEntity companyInfoEntity = companyInfoDao.findOne(1L);
        Map<String,String> map = Maps.newHashMap();
        map.put("url",companyInfoEntity.getCompanyWebSiteUrl());
        mobileResult.setData(map);
        mobileResult.setStatus(200);
        mobileResult.setMessage("获取成功");
        return mobileResult;
    }
}
