package com.e_lliam.app.video.server.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-10-30
 * Time: 上午9:44
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class UpdateVersionEntity {
    @Id
    @GeneratedValue
    private Long updateId;
    /**
     * android版本号
     */
    @Column(length = 50)
    private String androidVersion;
    /**
     * ios版本号
     */
    @Column(length = 50)
    private String iosVersion;
    /**
     * android更新地址
     */
    @Column(length = 100)
    private String androidUpdateUrl;
    /**
     * ios更新地址
     */
    @Column(length = 100)
    private String iosUpdateUrl;
    /**
     * android版本描述
     */
    @Column(length = 200)
    private String androidVersionDesc;
    /**
     * ios版本描述
     */
    @Column(length = 200)
    private String iosVersionDesc;
    /**
     * 更新次数统计
     */
    private int count;

    private String updateTime;

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public String getIosVersion() {
        return iosVersion;
    }

    public void setIosVersion(String iosVersion) {
        this.iosVersion = iosVersion;
    }

    public String getAndroidUpdateUrl() {
        return androidUpdateUrl;
    }

    public void setAndroidUpdateUrl(String androidUpdateUrl) {
        this.androidUpdateUrl = androidUpdateUrl;
    }

    public String getIosUpdateUrl() {
        return iosUpdateUrl;
    }

    public void setIosUpdateUrl(String iosUpdateUrl) {
        this.iosUpdateUrl = iosUpdateUrl;
    }

    public String getAndroidVersionDesc() {
        return androidVersionDesc;
    }

    public void setAndroidVersionDesc(String androidVersionDesc) {
        this.androidVersionDesc = androidVersionDesc;
    }

    public String getIosVersionDesc() {
        return iosVersionDesc;
    }

    public void setIosVersionDesc(String iosVersionDesc) {
        this.iosVersionDesc = iosVersionDesc;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
