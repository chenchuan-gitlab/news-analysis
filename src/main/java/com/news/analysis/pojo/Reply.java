package com.news.analysis.pojo;

import java.util.Date;

public class Reply {
    private Long id;

    private Long userId;

    private Long commentId;

    private Long replyTo;

    private Date replayTime;

    private Integer status;

    private String replyCotnet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Long getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Long replyTo) {
        this.replyTo = replyTo;
    }

    public Date getReplayTime() {
        return replayTime;
    }

    public void setReplayTime(Date replayTime) {
        this.replayTime = replayTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReplyCotnet() {
        return replyCotnet;
    }

    public void setReplyCotnet(String replyCotnet) {
        this.replyCotnet = replyCotnet == null ? null : replyCotnet.trim();
    }
}