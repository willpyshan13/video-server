package com.e_lliam.app.video.server.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-7
 * Time: 上午9:06
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class TeamEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long teamId;
    /**团队标题**/
    private String teamTitle;
    /**团队类型编号**/
    private Long teamTypeId;
    /**团队类型名字**/
    private String teamTypeName;
    /**团队图片预览地址**/
    private String teamImgPreviewUrl;
    /**团队图片内容地址**/
    private String teamImgContentUrl;
    /**寻求合作地址**/
    private String partnerUrl;
    /**团队内容信息**/
    @Column(nullable = false,columnDefinition = "text")
    private String teamContent;
    /**创建时间**/
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamTitle() {
        return teamTitle;
    }

    public void setTeamTitle(String teamTitle) {
        this.teamTitle = teamTitle;
    }

    public String getPartnerUrl() {
        return partnerUrl;
    }

    public void setPartnerUrl(String partnerUrl) {
        this.partnerUrl = partnerUrl;
    }

    public Long getTeamTypeId() {
        return teamTypeId;
    }

    public void setTeamTypeId(Long teamTypeId) {
        this.teamTypeId = teamTypeId;
    }

    public String getTeamTypeName() {
        return teamTypeName;
    }

    public void setTeamTypeName(String teamTypeName) {
        this.teamTypeName = teamTypeName;
    }

    public String getTeamImgPreviewUrl() {
        return teamImgPreviewUrl;
    }

    public void setTeamImgPreviewUrl(String teamImgPreviewUrl) {
        this.teamImgPreviewUrl = teamImgPreviewUrl;
    }

    public String getTeamImgContentUrl() {
        return teamImgContentUrl;
    }

    public void setTeamImgContentUrl(String teamImgContentUrl) {
        this.teamImgContentUrl = teamImgContentUrl;
    }

    public String getTeamContent() {
        return teamContent;
    }

    public void setTeamContent(String teamContent) {
        this.teamContent = teamContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
