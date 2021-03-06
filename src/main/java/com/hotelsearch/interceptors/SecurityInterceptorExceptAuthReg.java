package com.hotelsearch.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class SecurityInterceptorExceptAuthReg implements HandlerInterceptor {

    @Autowired
    HttpSession httpSession;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (httpSession.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/auth");
            return false;
        } else {
            if (request.getServletPath().equals("/auth") || request.getServletPath().equals("/registration"))
                response.sendRedirect(request.getContextPath() + "/main");
            else return true;
        }
        return false;
    }
}

