package com.hxdavid.hxframework.sampleweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommonController {

    @RequestMapping(value = "/", method = { RequestMethod.GET})
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie cookie = new Cookie("mds", "secret 4");
        cookie.setDomain("www.hxdavid.com");
        response.addCookie(cookie);
        return "index.html";
    }

    @RequestMapping("/getCookie")
    public String getCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return "index.html";
        }
        for (Cookie cookie : cookies) {
            System.out.printf("%s:%s=%s\n", cookie.getPath(), cookie.getName(), cookie.getValue());
        }
        return "index.html";
    }

    @RequestMapping("/setSession")
    public String setSession(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("p", "hxdavid");
        return "index.html";
    }

    @RequestMapping("/getSession")
    public String getSession(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getSession().getAttribute("p"));
        return "index.html";
    }
}
