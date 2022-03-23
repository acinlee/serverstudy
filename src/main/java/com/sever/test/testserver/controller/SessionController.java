package com.sever.test.testserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
public class SessionController {
    private static final String MEMBER_KEY = "memberKey";

    @RequestMapping("/user/login")
    public HttpServletResponse login(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if( session == null && session.getAttribute("userSid") == null ){
            Random random = new Random();
            String userSid = "A" + random.nextInt(9999999);
            session.setAttribute("userSid", userSid);
            // 쿠키 생성
            Cookie cookie = new Cookie("sid", userSid);
            response.addCookie(cookie);
        }
        return response;
    }
    @RequestMapping("/session/create")
    public String sessionCreate(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println(session.toString());
        String sessionString = "helloSessionWorld";
        session.setAttribute("helloSession", sessionString);

        // cookie 생성

        return session.getId();
    }

    @RequestMapping("/session/confirm")
    public String sessionConfirm(HttpServletRequest request) {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("helloSession"));
        if(session.getAttribute("helloSession") != null) {
            return "success";
        } else{
            return "false";
        }
    }
}
