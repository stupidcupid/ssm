package com.sms.filter;

import com.sms.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by nanzhou on 2017/8/31.
 */
public class LoginFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();
        //System.out.println(path);

        User user = (User) session.getAttribute("userSession");
        //创建类Constants.java，里面写的是无需过滤的页面
        for (int i = 0; i < Constants.NoFilter_Pages.length; i++) {
           if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
          }
         }
        // 登陆页面无需过滤
        if (path.indexOf("/user/toLoginPage") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (null == user) {
            // 跳转到登陆页面
            servletResponse.sendRedirect("/user/toLoginPage");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
           // servletResponse.sendRedirect("/user/toSuccessPage");
        }
    }

    public void destroy() {

    }
}
