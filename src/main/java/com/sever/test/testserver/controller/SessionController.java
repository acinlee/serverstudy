package com.sever.test.testserver.controller;

import com.sever.test.testserver.model.TestEntity;
import com.sever.test.testserver.service.TestService;
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Random;

@RestController
public class SessionController {
    private static final String MEMBER_KEY = "memberKey";

    @Autowired
    TestService testService;

    @RequestMapping("/user/login")
    public int login(@RequestParam String userId, @RequestParam String userPw, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        Cookie [] cookies = request.getCookies();
        boolean isSid = false;
        if(cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("sid".equals(cookie.getName())) {
                    isSid = true;
                }
            }
        }

        if(isSid) {
            if( session != null && session.getAttribute("userSid") != null ){
                if (userId.equals(session.getAttribute("id"))){
                    if (userPw.equals(session.getAttribute("pw"))) {
                        response.setStatus(200);
                    } else {
                        response.setStatus(400);
                    }
                }
            }
        } else {
            Random random = new Random();
            String userSid = "A" + random.nextInt(9999999);
            session.setAttribute("userSid", userSid);
            session.setAttribute("id", userId);
            session.setAttribute("pw", userPw);
            // 쿠키 생성
            Cookie cookie = new Cookie("sid", userSid);
            cookie.setDomain("localhost");
            cookie.setPath("/");
            cookie.setMaxAge(30);
            response.addCookie(cookie);
            response.setStatus(400);
        }
        return response.getStatus();
    }
    @PostMapping("/user/join")
    public ResponseEntity<TestEntity> userJoin(@RequestBody TestEntity test){
        testService.join(test.getUserId(), test.getUserPw());
        return new ResponseEntity<>(test, HttpStatus.OK);
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
