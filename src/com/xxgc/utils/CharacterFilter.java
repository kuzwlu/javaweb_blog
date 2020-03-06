package com.xxgc.utils;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class CharacterFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {	
		
		if(((HttpServletRequest) request).getMethod().equals("GET")){
			MyRequest er=new MyRequest((HttpServletRequest)request);
			chain.doFilter(er, response);			
		}else if(((HttpServletRequest) request).getMethod().equals("POST")){
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			chain.doFilter(request, response);
		}
		
	}

	public void init(FilterConfig arg0) throws ServletException {

		
	}
	

	
}
