package com.news.analysis.controller.api;

import com.news.analysis.pojo.Comment;
import com.news.analysis.pojo.Reply;
import com.news.analysis.service.api.ApiCommentService;
import com.news.analysis.utils.DateUtil;
import com.news.analysis.utils.ResponseBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 评论和回复controller
 */
@RestController
@RequestMapping("/api/comment")
@CrossOrigin
public class ApiCommentController {

    private Logger logger = LoggerFactory.getLogger(ApiCommentController.class);

    @Autowired
    private ApiCommentService commentService;

    @RequestMapping(value = "/saveComment.action", method = RequestMethod.POST)
    public Object saveComment(@RequestBody Comment comment) {
        try {
            comment.setStatus(1);
            comment.setTime(DateUtil.getDate_yMdhms());
            commentService.saveComment(comment);
            return ResponseBuilder.custom().success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存异常");
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/saveReply.action", method = RequestMethod.POST)
    public Object saveReply(@RequestBody Reply reply) {
        try {
            reply.setStatus(1);
            reply.setReplayTime(DateUtil.getDate_yMdhms());
            commentService.saveReply(reply);
            return ResponseBuilder.custom().success();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("保存异常");
            return ResponseBuilder.custom().faild();
        }
    }

    @RequestMapping(value = "/getCommentData.action")
    public Object getCommentData(){
        try {
            List data = commentService.getCommentData();
            return ResponseBuilder.custom().success().data(data);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取数据异常");
            return ResponseBuilder.custom().faild();
        }
    }
}
