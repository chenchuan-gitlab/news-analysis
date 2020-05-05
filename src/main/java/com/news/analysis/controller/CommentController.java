package com.news.analysis.controller;

import com.news.analysis.pojo.Comment;
import com.news.analysis.service.CommentService;
import com.news.analysis.utils.PageForm;
import com.news.analysis.utils.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/list.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getUserList(PageForm pageForm, String startTime, String endTime) {
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put("startTime", startTime);
            paramsMap.put("endTime", endTime);
            pageForm = commentService.getList(pageForm, paramsMap);
            return ResponseBuilder.custom().success().data(pageForm);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/getDataByID.action", method = RequestMethod.GET)
    @ResponseBody
    public Object getDataByID(String id) {
        try {
            return ResponseBuilder.custom().success().data(commentService.getDataByID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }

    }

    @RequestMapping(value = "/updateStatus.action")
    @ResponseBody
    public Object updateStatus(String id, int status) {
        try {
            Comment comment = new Comment();
            status = status == 0 ? 1 : 0;
            comment.setId(Long.valueOf(id));
            comment.setStatus(status);
            return ResponseBuilder.custom().success("success", commentService.update(comment));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBuilder.custom().faild();
        }
    }
}
