package com.xxgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Manager;
import com.xxgc.model.User;

public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("register")){
			register(request, response);
		}else if(type.endsWith("loginout")){
			loginout(request, response);
		}else if(type.endsWith("page")){
			page(request, response);
		}else if(type.endsWith("findall")){
			findall(request, response);
		}else if(type.endsWith("del")){
			del(request, response);
		}else if(type.endsWith("image")){
			image(request, response);
		}else if(type.endsWith("update")){
			update(request, response);
		}else if(type.endsWith("rec")){
			rec(request, response);
		}
	}
	public void rec(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		UserDAO userDAO=new UserDAO();
		User user = new User();
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		String pwd=request.getParameter("mpwd");
		String pwd2=request.getParameter("mpwd2");
		String mstate=request.getParameter("mstates");
		String path="background/userupdate.jsp";		
		if(pwd!=""&(pwd).endsWith(pwd2)){
			user.setUserid(id);
			user.setPasswd(pwd);
			user.setUserstate(mstate);
			if(userDAO.update(user)){
				path="/UserServlet?type=page&page=1";
			}
		}else{
			out.flush();
			out.println("<script>");
			out.println("alert('密码不一样，请重新输入！');");
			out.println("history.back();");
			out.println("</script>");
				
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int id=Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO=new UserDAO();
		User userall=userDAO.findbyid(id);
		
		request.setAttribute("userall", userall);
		String path="background/userupdate.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public void image(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO=new UserDAO();
		String image = "";
		if(image!=null){
			image = userDAO.getimage(id);
		}else{
			image="upload/err.png";
		}
		request.setAttribute("id", id);
		request.setAttribute("image", image);
		request.getRequestDispatcher("background/userheadimage.jsp").forward(request, response);
	}
	
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int id=Integer.parseInt(request.getParameter("id"));
		UserDAO userDAO=new UserDAO();
		User user = new User();
		user.setUserid(id);
		System.out.println(userDAO.delete(user));
		request.getRequestDispatcher("UserServlet?type=page&page=1").forward(request, response);
	}
	
	public void findall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		UserDAO userDAO=new UserDAO();
		ArrayList<User> userList=userDAO.finaAll();
		
		request.setAttribute("userList", userList);
		request.getRequestDispatcher("background/userlist.jsp").forward(request, response);
	}
	
	public void page(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		UserDAO userDAO=new UserDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		int startpage=page;//页码
		//int pagesize=managerDAO.setPagesize(5);//显示条数
		String path="UserServlet?type=findall";
		ArrayList<User> pageList=userDAO.page(startpage);
		for(User p:pageList){
			if(p.getUserstate().endsWith("1")){
				p.setUserstate("启用");
			}
			if(p.getUserstate().endsWith("2")){
				p.setUserstate("未启用");
			}
		}
		request.setAttribute("pageList", pageList);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", userDAO.getPagesize());
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//loginout
	public void loginout(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.getSession().removeAttribute("username");
		PrintWriter out = response.getWriter();
		Cookie userautologin = new Cookie("userautologin", "msg");
		userautologin.setMaxAge(0);
		response.addCookie(userautologin);
		out.print("<script>alert('注销成功!');window.location.href='IndexServlet'</script>");
	}
	
	public void register(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String codes=request.getParameter("codes");
		String codeSession=(String)request.getSession().getAttribute("code");
		String path="background/register.jsp";
		
		String userstate="1";
		
		UserDAO userDAO=new UserDAO();
		User useradd=new User();
		User m=userDAO.findname(username);
		if((m.getUsername())==null){
			if(codes!=null){
				if(codes.endsWith(codeSession)){
					if(password!=""&(password).endsWith(password2)){
						useradd.setUsername(username);
						useradd.setPasswd(password);
						useradd.setUserstate(userstate);
						if(userDAO.insert(useradd)){
							path="/background/login.jsp";
						}
					}else{
						out.flush();
					    out.println("<script>");
					    out.println("alert('密码不一样，请重新输入！');");
					    out.println("history.back();");
					    out.println("</script>");
					}
				}else{
					out.flush();
				    out.println("<script>");
				    out.println("alert('验证码输入错误！');");
				    out.println("history.back();");
				    out.println("</script>");
				}
			}
			System.out.println(useradd);
		}else{
			out.flush();
		    out.println("<script>");
		    out.println("alert('此用户已注册，请重新输入！');");
		    out.println("history.back();");
		    out.println("</script>");
		    
		}
		String newpath=response.encodeRedirectURL(path);
		response.sendRedirect(newpath);		
	}

}
