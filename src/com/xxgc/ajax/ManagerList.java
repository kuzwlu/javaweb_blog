package com.xxgc.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.model.Manager;

public class ManagerList extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO=new ManagerDAO();
		
		String pages=request.getParameter("page");
		int page=Integer.parseInt(pages);
		managerDAO.setPagesize(5);
		ArrayList<Manager> managerlists=managerDAO.page(page);
		JSONArray managerlist=JSONArray.fromObject(managerlists);
		//System.out.println(managerlist);
		out.print(managerlist);
		//request.setAttribute("managerlist", managerlist);
	}

}
