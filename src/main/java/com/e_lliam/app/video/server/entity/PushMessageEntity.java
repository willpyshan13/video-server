package com.e_lliam.app.video.server.entity;

import javax.persistence.*;
/**
 * Created with IntelliJ IDEA.
 * User: ys.peng
 * Date: 13-11-27
 * Time: 上午9:18
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class PushMessageEntity {
    @Id
    @GeneratedValue
    private Long pushMessageId;
    private String messageTitle;
    private String messageContent;
    private String pushTime;

    public Long getPushMessageId() {
        return pushMessageId;
    }

    public void setPushMessageId(Long pushMessageId) {
        this.pushMessageId = pushMessageId;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getPushTime() {
        return pushTime;
    }

    public void setPushTime(String pushTime) {
        this.pushTime = pushTime;
    }
}
