package com.kaishengit.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by lenovo on 2016/12/19.
 */
public class LoginFilter extends AbstractFilter {

    private List<String> urlList = null;
    private List<String> urlAdminList = null;

    //拿到需要验证的url
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String validateurl = filterConfig.getInitParameter("validateurl");
        urlList = Arrays.asList(validateurl.split(","));
        String validateAdminUrl = filterConfig.getInitParameter("validateAdminUrl");
        urlAdminList = Arrays.asList(validateAdminUrl.split(","));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        //获取客户要访问的url
        String requesturl = httpServletRequest.getRequestURI();
        if (requesturl.startsWith("/admin")){
            if(urlAdminList != null  && urlAdminList.contains(requesturl)){
                if(httpServletRequest.getSession().getAttribute("curr_admin") == null){

                    //去管理员登录页面
                    httpServletResponse.sendRedirect("/admin/login?redirect="+requesturl);

                }else{
                    filterChain.doFilter(httpServletRequest,httpServletResponse);
                }
            }else{
                filterChain.doFilter(httpServletRequest,httpServletResponse);
            }

        }else {
            if(urlList != null && urlList.contains(requesturl)) {
                if(httpServletRequest.getSession().getAttribute("curr_user") == null) {
                    Map map = httpServletRequest.getParameterMap();
                    Set paramet = map.entrySet();
                    Iterator iterator = paramet.iterator();//迭代
                    if(iterator.hasNext()){
                        requesturl +="?";

                        while (iterator.hasNext()){
                            Map.Entry me = (Map.Entry) iterator.next();
                            Object key = me.getKey();
                            Object value = me.getValue();
                            String valString[] =(String[]) value;
                            String params = "";
                            for (int i = 0;i<valString.length;i++){
                                params = key + "=" + valString[i] +"&";
                                requesturl += params;
                            }
                        }
                        requesturl = requesturl.substring(0,requesturl.length()-1);
                    }


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
}
