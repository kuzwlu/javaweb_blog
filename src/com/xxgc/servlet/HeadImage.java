package com.xxgc.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Manager;
import com.xxgc.model.User;

public class HeadImage extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	//private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("getimage")){
			getimage(request, response);
		}else if(type.endsWith("setimage")){
			setimage(request, response);
		}else if(type.endsWith("user")){
			user(request, response);
		}
	}
	public void user(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String src=request.getParameter("src");
		int id=Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO=new UserDAO();
		String msg = "1";
		if(userDAO.maimage(src, id)){
			out.print(msg);
		}
	}
	public void getimage(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ManagerDAO managerDAO = new ManagerDAO();
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String src = managerDAO.getimage(managername);
		out.print(src);
	}
	public void setimage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ManagerDAO managerDAO = new ManagerDAO();
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String src=request.getParameter("src");
		//System.out.println(src);
		//System.out.println(username);
		String msg = "1";
		if(managerDAO.maimage(src, managername)){
			out.print(msg);
		}
	}

}
