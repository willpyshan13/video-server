package com.e_lliam.app.video.server.pojo.mobile;

/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-10-29
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 */
public class UpdateVersionBean {
    /**
     * 软件更新地址
      */
    private String url;
    /**当前版本**/
    private String version;
    /**当前版本描述**/
    private String desc;

    private boolean isUpdate;

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean update) {
        isUpdate = update;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
