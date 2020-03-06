package com.xxgc.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import com.xxgc.dao.ArticleDAO;


public class MyRequest extends HttpServletRequestWrapper {
	private HttpServletRequest req;
	public MyRequest(HttpServletRequest request) {
		super(request);
		this.req=request;
	}
	public String getParameter(String name){
		String value=req.getParameter(name);
		//处理编码问题
		ArticleDAO articleDAO = new ArticleDAO();
		if(articleDAO.getEncode(value).endsWith("GBK")){
			try {
				value=new String(value.getBytes("GBK"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(articleDAO.getEncode(value).endsWith("GB2312")){
			try {
				value=new String(value.getBytes("GB2312"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(articleDAO.getEncode(value).endsWith("UTF-8")){
				value=value;
		}else{
			try {
				value=new String(value.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return value;
	}
}