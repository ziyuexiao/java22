package com.kaishengit.web.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by lenovo on 2016/12/16.
 */
public abstract class AbstractFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public abstract void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void destroy() {

    }
}
