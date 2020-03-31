package com.news.analysis.controller;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    @RequestMapping(value = "login.action",method = RequestMethod.GET)
    @ResponseBody
    public String login(@RequestParam("username") String username,@RequestParam("password") String password){
        if("admin".equals(username)&&"12345678".equals(password)){
            return "success";
        }
        return "error";
    }
}
