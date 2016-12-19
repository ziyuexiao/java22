package com.kaishengit.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lenovo on 2016/12/19.
 */
public class LoginFilter extends AbstractFilter {

    private List<String> urlList = null;

    //拿到需要验证的url
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String validateurl = filterConfig.getInitParameter("validateurl");
        urlList = Arrays.asList(validateurl.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取客户要访问的url
        String requesturl = httpServletRequest.getRequestURI();
        if(urlList != null && urlList.contains(requesturl)) {
            if(httpServletRequest.getSession().getAttribute("curr_user") == null) {
                //去登录页面
                httpServletResponse.sendRedirect("/login?redirect="+requesturl);
            } else {
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }
        } else {
            filterChain.doFilter(httpServletRequest,httpServletResponse);
        }

    }
}
