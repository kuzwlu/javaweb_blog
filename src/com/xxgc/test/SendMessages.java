package com.xxgc.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendMessages extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String phone=request.getParameter("phone");
		String path="http://hz.typc.xyz/api.php?act=user&key=w1T5p0ZIKip6IqwxT0&phone="+phone+"";
		System.out.println(phone);
		System.out.println(path);
		int lengh=phone.length();
		if(lengh!=11){
			out.flush();
		    out.println("<script>");
		    out.println("alert('手机号码错误，请重新输入！');");
		    out.println("history.back();");
		    out.println("</script>");
		}
		out.flush();
	    out.println("<script>");
	    out.println("alert('正在轰炸，请耐心等待15秒！');");
	    out.println("history.back();");
	    out.println("</script>");
		response.sendRedirect(path);
		
	}

}
