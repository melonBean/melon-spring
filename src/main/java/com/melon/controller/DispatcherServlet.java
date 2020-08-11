package com.melon.controller;

import com.melon.controller.frontend.MainPageController;
import com.melon.controller.superadmin.HeadLineOperationController;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author czm 2020/8/11.
 *
 * / 不会对 .jsp请求拦截
 * /* 会对 .jsp请求拦截
 * 在 tomcat 配置文件中可以看到相应的配置原则，/ 匹配优先级低于 /* 类型
 */
@Slf4j
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        log.info("request path is {}", request.getServletPath());
        log.info("request method is {}", request.getMethod());
        if (request.getServletPath().equals("/frontend/getmainpageinfo") && request.getMethod().equals("GET")) {
            new MainPageController().getMainPageInfo(request, response);
        } else if (request.getServletPath().equals("/superadmin/addheadline") && request.getMethod().equals("POST")) {
            new HeadLineOperationController().addHeadLine(request, response);
        }
    }

}
