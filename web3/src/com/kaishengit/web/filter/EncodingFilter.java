package com.kaishengit.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.commons.lang3.StringUtils;


 /*
  *  ×Ö·û¼¯¹ýÂËÆ÷
  */
 

public class EncodingFilter extends AbstractFilter{
	
	private String encoding = "UTF-8";
	
	

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		String encoding = filterConfig.getInitParameter("encoding");
		if(StringUtils.isNotEmpty(encoding)){
			this.encoding = encoding;
		}
		
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse sertvletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		
		servletRequest.setCharacterEncoding(encoding);
		sertvletResponse.setCharacterEncoding(encoding);
		
		filterChain.doFilter(servletRequest, sertvletResponse);
		
	}
	
}
