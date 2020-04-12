package com.news.analysis.service.api;

import com.news.analysis.dao.CommentMapper;
import com.news.analysis.dao.ReplyMapper;
import com.news.analysis.pojo.Comment;
import com.news.analysis.pojo.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ApiCommentService
 */
@Service
public class ApiCommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ReplyMapper replyMapper;

    public int saveComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    public int saveReply(Reply reply) {
        return replyMapper.insert(reply);
    }

    public List getCommentData() {
        return commentMapper.getCommentData();
    }
}
