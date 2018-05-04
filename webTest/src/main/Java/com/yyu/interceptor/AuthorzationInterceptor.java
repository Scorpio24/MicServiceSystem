package com.yyu.interceptor;

import domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorzationInterceptor implements HandlerInterceptor{
    private static final String[] IGNORE_URI = {"/loginForm", "/login", "/index"};

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception excption)throws Exception{
        System.out.println("AuthorizationInterceptor afterCompletion --> ");
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView mv)throws Exception{
        System.out.println("AuthorizationInterceptor postHandle --> ");
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler)throws Exception{
        System.out.println("AuthorizationInterceptor preHandle -->");
        boolean flag = false;
        String servletPath = request.getServletPath();
        for (String s : IGNORE_URI){
            if (servletPath.contains(s)){
                flag = true;
                break;
            }
        }
        if (!flag){
            User user = (User)request.getSession().getAttribute("user");
            if(user == null){
                System.out.println("AuthorizationInterceptor拦截请求：");
                request.setAttribute("message", "请先登录再访问网站");
                request.getRequestDispatcher("loginForm").forward(request, response);
            }
            else{
                System.out.println("AuthorizationInterceptor放行请求：");
                flag = true;
            }
        }
        return flag;
    }
}
