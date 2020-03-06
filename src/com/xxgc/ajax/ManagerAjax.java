package com.xxgc.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.model.Manager;

public class ManagerAjax extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("code")){
			code(request, response);
		}else if(type.endsWith("name")){
			name(request, response);
		}else if(type.endsWith("pwd")){
			pwd(request, response);
		}
		
	}
	public void code(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String codeSession=(String)request.getSession().getAttribute("code");
		
		String codes=request.getParameter("codecheck");
		//System.out.println(codeSession);
		//System.out.println(codes);
		if(codes==""){
			out.print("");
		}else if(codes.endsWith(codeSession)){
			out.print("<span id='yes'></span>");
		}else{
			out.print("<span id='no'></span>");
		}
	}
	public void name(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		ManagerDAO managerDAO=new ManagerDAO();
		Manager u=managerDAO.findname(name);
		System.out.println(u);
		if(name==""){
			out.print("");
		}else if(u.getManagername()==null){
			out.print("<span id='yes'></span>");
		}else{
			out.print("<font color='red'>&nbsp;&nbsp;用户名已被注册</font>");
		}
	}
	public void pwd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String pwdid=request.getParameter("pwd");
		String pwd2id=request.getParameter("pwd2");
		//System.out.println(pwdid);
		//System.out.println(pwd2id);
		if(pwdid==""){
			out.print("");
		}else if(pwdid.endsWith(pwd2id)){
			out.print("<span id='yes'></span>");
		}else{
			out.print("<font color='red'>&nbsp;&nbsp;密码不一样</font>");
		}
	}
	

}
