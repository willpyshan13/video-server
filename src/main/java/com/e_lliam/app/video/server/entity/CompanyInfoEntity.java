package com.e_lliam.app.video.server.entity;


import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-6
 * Time: 下午4:37
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class CompanyInfoEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long infoId;
    /**公司信息标题**/
    private String infoTitle;

    /**公司信息logo连接地址**/
    private String infoLogoUrl;
    /**公司信息内容**/
    @Column(columnDefinition = "text")
    private String infoContent;
    /**公司网址**/
    private String companyWebSiteUrl;
    /**更新时间**/
    private String createTime;

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle;
    }

    public String getInfoLogoUrl() {
        return infoLogoUrl;
    }

    public void setInfoLogoUrl(String infoLogoUrl) {
        this.infoLogoUrl = infoLogoUrl;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent;
    }

    public String getCompanyWebSiteUrl() {
        return companyWebSiteUrl;
    }

    public void setCompanyWebSiteUrl(String companyWebSiteUrl) {
        this.companyWebSiteUrl = companyWebSiteUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
