package com.news.analysis.pojo;

import java.util.List;
import java.util.Map;

public class CommentVo {
    private Long id;

    private Long userId;

    private String userName;

    private String commentTime;

    private String content;

    private List<Map> replyList;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Map> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<Map> replyList) {
        this.replyList = replyList;
    }
}
