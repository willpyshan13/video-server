package com.e_lliam.app.video.server.entity;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-7
 * Time: 上午8:51
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class LoadingAnimationEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long loadingId;
    private String loadingImgUrl;
    private String createTime;

    public Long getLoadingId() {
        return loadingId;
    }

    public void setLoadingId(Long loadingId) {
        this.loadingId = loadingId;
    }

    public String getLoadingImgUrl() {
        return loadingImgUrl;
    }

    public void setLoadingImgUrl(String loadingImgUrl) {
        this.loadingImgUrl = loadingImgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String updateTime) {
        this.createTime = updateTime;
    }
}
