package com.xxgc.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Manager;
import com.xxgc.model.User;

public class AutoLoginFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest req, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		Cookie[] cookies = request.getCookies();
		String autologin = null;
		String userautologin = null;
		for(int i = 0;cookies != null && i<cookies.length;i++){
			if("autologin".equals(cookies[i].getName())){
   				autologin = cookies[i].getValue();
   				break;
   			}
		}
		for(int i = 0;cookies != null && i<cookies.length;i++){
			if("userautologin".equals(cookies[i].getName())){
   				userautologin = cookies[i].getValue();
   				break;
   			}
		}
		if(autologin !=null){
			String[] parts = autologin.split("-");
			String managername = parts[0];
			String password = parts[1];
			//System.out.println(password);
			managername = AESUtil.decrypt(managername);
			password = AESUtil.decrypt(password);
			//System.out.println(password);
			ManagerDAO managerDAO=new ManagerDAO();
			Manager manager =new Manager();
			manager.setManagername(managername);
			manager.setPasswd(password);
			String z="3";
			if(managerDAO.User(manager).getManagername()!=null && !(managerDAO.User(manager).getMatate()).equals(z)){
				HttpSession session = request.getSession();
				session.setAttribute("managername", managername);
			}
		}
			if(userautologin!=null){
				String[] parts = userautologin.split("-");
				String username = parts[0];
				String password = parts[1];
				//System.out.println(password);
				username = AESUtil.decrypt(username);
				password = AESUtil.decrypt(password);
				//System.out.println(password);
				UserDAO userDAO = new UserDAO();
				User user = new User();
				user.setUsername(username);
				user.setPasswd(password);
				String z="3";
				if(userDAO.User(user).getUsername()!=null && !(userDAO.User(user).getUserstate()).equals(z)){
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
				}
			}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
