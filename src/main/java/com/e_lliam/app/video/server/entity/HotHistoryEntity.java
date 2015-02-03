package com.e_lliam.app.video.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: will
 * Date: 13-10-25
 * Time: 下午3:39
 */
@Entity
public class HotHistoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long hotId;
    private Long videoId;
    private String videoTitle;
    /**今日统计个数**/
    private Integer todayCount;
    /**当前日期**/
    private String currentDay;
    /**本周统计个数**/
    private Integer weekCount;
    /**当前周日期**/
    private String currrentWeek;
    /**月份统计**/
    private Integer monthCount;
    /**当前月份**/
    private String currrentMonth;
    private Long typeId;
    /**最后一次播放时间**/
    private Date createTime;
    /**历史纪录**/
    private Integer count;

    public Long getHotId() {
        return hotId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public Integer getTodayCount() {
        return todayCount;
    }

    public void setTodayCount(Integer todayCount) {
        this.todayCount = todayCount;
    }

    public String getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(String currentDay) {
        this.currentDay = currentDay;
    }

    public Integer getWeekCount() {
        return weekCount;
    }

    public void setWeekCount(Integer weekCount) {
        this.weekCount = weekCount;
    }

    public String getCurrrentWeek() {
        return currrentWeek;
    }

    public void setCurrrentWeek(String currrentWeek) {
        this.currrentWeek = currrentWeek;
    }

    public Integer getMonthCount() {
        return monthCount;
    }

    public void setMonthCount(Integer monthCount) {
        this.monthCount = monthCount;
    }

    public String getCurrrentMonth() {
        return currrentMonth;
    }

    public void setCurrrentMonth(String currrentMonth) {
        this.currrentMonth = currrentMonth;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setHotId(Long hotId) {
        this.hotId = hotId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
