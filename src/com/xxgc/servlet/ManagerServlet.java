package com.xxgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Manager;
import com.xxgc.model.User;
import com.xxgc.utils.AESUtil;

public class ManagerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("login")){
			login(request, response);
		}else if(type.endsWith("page")){
			page(request, response);
		}else if(type.endsWith("findall")){
			findall(request, response);
		}else if(type.endsWith("manageradd")){
			manageradd(request, response);
		}else if(type.endsWith("recycled")){
			recycled(request, response);
		}else if(type.endsWith("update")){
			update(request, response);
		}else if(type.endsWith("updata")){
			updata(request, response);
		}else if(type.endsWith("rec")){
			rec(request, response);
		}else if(type.endsWith("recoall")){
			recoall(request, response);
		}else if(type.endsWith("recovery")){
			recovery(request, response);
		}else if(type.endsWith("del")){
			del(request, response);
		}else if(type.endsWith("search")){
			search(request, response);
		}else if(type.endsWith("index")){
			index(request, response);
		}else if(type.endsWith("loginout")){
			loginout(request, response);
		}else if(type.endsWith("count")){
			count(request, response);
		}else if(type.endsWith("username")){
			username(request, response);
		}
	}
	
	//ip
	public void count(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html; charset=UTF-8");
		ManagerDAO managerDAO=new ManagerDAO();
		
		ArrayList<Manager> managerLists=managerDAO.finaAll();
		JSONArray managerList=JSONArray.fromObject(managerLists);
		//System.out.println(managerList);
		out.print(managerList);
	}
	//搜索
	public void search(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO managerDAO=new ManagerDAO();
		String managername=request.getParameter("managername");
		ArrayList<Manager> managerFindName=managerDAO.finaname(managername);
		
		for(Manager manager:managerFindName){
			if(manager.getMatate().endsWith("1")){
				manager.setMatate("启用");
			}
			if(manager.getMatate().endsWith("2")){
				manager.setMatate("未启用");
			}
			if(manager.getMatate().endsWith("3")){
				manager.setMatate("已删除");
			}
		}
		if(managerFindName.size()<=0){
			out.flush();
		    out.println("<script>");
		    out.println("alert('查无此人！');");
		    out.println("history.back();");
		    out.println("</script>");
		    request.getRequestDispatcher("ManagerServlet?type=findall").forward(request, response);
		}
		request.setAttribute("managerFindName", managerFindName);
		String path="/background/managerfind.jsp";
		String newpath=response.encodeRedirectURL(path);
		request.getRequestDispatcher(newpath).forward(request, response);
	}
	
	//删除del
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//删除
		int id=Integer.parseInt(request.getParameter("id"));
		ManagerDAO mangerDAO=new ManagerDAO();
		Manager managerDel=new Manager();
		managerDel.setManagerid(id);
		System.out.println(mangerDAO.delete(managerDel));
		request.getRequestDispatcher("ManagerServlet?type=rec&page=1").forward(request, response);
	}
	//恢复recovery
	public void recovery(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//回收站恢复管理员
		int id=Integer.parseInt(request.getParameter("id"));
		String matate=request.getParameter("matate");
		//System.out.println(matate);
		ManagerDAO managerDAO=new ManagerDAO();
		Manager recovery=new Manager();
		recovery.setManagerid(id);
		recovery.setMatate("1");
		System.out.println(recovery);
		System.out.println(managerDAO.recovery(recovery));
		request.getRequestDispatcher("ManagerServlet?type=rec&page=1").forward(request, response);
	}
	
	//回收站全查页面
	public void recoall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	//跳转回收站列表，只用来查询全部数据
		ManagerDAO ManagerDAO=new ManagerDAO();
		ArrayList<Manager> managerList=ManagerDAO.recycled();
		request.setAttribute("managerList", managerList);
		String path="/background/recycled.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		System.out.println("这是recfindall");
	}
	
	//回收站跳转页面
	public void rec(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ManagerDAO managerDAO=new ManagerDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		int startpage=page;//页码
		int pagesize=managerDAO.setPagesize(5);
		String path="ManagerServlet?type=recoall";
		ArrayList<Manager> pageList=managerDAO.Recpage(startpage);
		for(Manager p:pageList){
			if(p.getMatate().endsWith("1")){
				p.setMatate("启用");
			}
			if(p.getMatate().endsWith("2")){
				p.setMatate("未启用");
			}
			if(p.getMatate().endsWith("3")){
				p.setMatate("已删除");
			}
		}
		request.setAttribute("pageList", pageList);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", pagesize);
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//编辑页面
	public void updata(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		ManagerDAO mangerDAO=new ManagerDAO();
		Manager managerupdate=new Manager();
		String ids=request.getParameter("id");
		int id=Integer.parseInt(ids);
		String pwd=request.getParameter("mpwd");
		String pwd2=request.getParameter("mpwd2");
		String mstate=request.getParameter("mstates");
		String path="background/managerUp.jsp";		
		if(pwd!=""&(pwd).endsWith(pwd2)){
			
			managerupdate.setManagerid(id);
			managerupdate.setPasswd(pwd);
			managerupdate.setMatate(mstate);
			if(mangerDAO.update(managerupdate)){
				path="/ManagerServlet?type=page&page=1";
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
	//编辑跳转
	public void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int id=Integer.parseInt(request.getParameter("id"));
		ManagerDAO managerDAO=new ManagerDAO();
		Manager managerAll=managerDAO.findbyid(id);
		
		request.setAttribute("managerAll", managerAll);
		String path="background/managerUp.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//伪删除
	public void recycled(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		//伪删除管理员
		int id=Integer.parseInt(request.getParameter("id"));

		ManagerDAO managerDAO=new ManagerDAO();
		Manager recycled=new Manager();
		recycled.setManagerid(id);
		
		System.out.println(managerDAO.recycled(recycled));
		request.getRequestDispatcher("/ManagerServlet?type=page&page=1").forward(request, response);
	}
	
	//manageradd
	public void manageradd(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		String pwd2=request.getParameter("pwd2");
		String mstate=request.getParameter("mstate");
		String path="background/main_info.jsp";
		
		ManagerDAO mangerDAO=new ManagerDAO();
		Manager manageradd=new Manager();
		Manager m=mangerDAO.findname(name);
		if((m.getManagername())==null){
			if(pwd!=""&(pwd).endsWith(pwd2)){
				manageradd.setManagername(name);
				manageradd.setPasswd(pwd);
				manageradd.setMatate(mstate);
				System.out.println(mangerDAO.update(manageradd));
				if(mangerDAO.insert(manageradd)){
					path="/ManagerServlet?type=page&page=1";
				}
			}else{
				out.flush();
			    out.println("<script>");
			    out.println("alert('密码不一样，请重新输入！');");
			    out.println("history.back();");
			    out.println("</script>");
			}
			
			System.out.println(manageradd);
		}else{
			out.flush();
		    out.println("<script>");
		    out.println("alert('此用户已注册，请重新输入！');");
		    out.println("history.back();");
		    out.println("</script>");
		    
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//findall
	public void findall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ManagerDAO ManagerDAO=new ManagerDAO();
		ArrayList<Manager> managerList=ManagerDAO.finaAll();
		
		request.setAttribute("managerList", managerList);
		request.getRequestDispatcher("background/main_list.jsp").forward(request, response);
		System.out.println("这是FindAll");
	}
	
	//page
	public void page(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ManagerDAO managerDAO=new ManagerDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		int startpage=page;//页码
		//int pagesize=managerDAO.setPagesize(5);//显示条数
		String path="ManagerServlet?type=findall";
		ArrayList<Manager> pageList=managerDAO.page(startpage);
		for(Manager p:pageList){
			if(p.getMatate().endsWith("1")){
				p.setMatate("启用");
			}
			if(p.getMatate().endsWith("2")){
				p.setMatate("未启用");
			}
		}
		request.setAttribute("pageList", pageList);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", managerDAO.getPagesize());
		request.getRequestDispatcher(path).forward(request, response);
	}
	

	//登录
	public void login(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String managername=request.getParameter("username");
		String username = managername;
		String password=request.getParameter("password");
		String autologin=request.getParameter("autologin");
		String codes=request.getParameter("codes");
		ManagerDAO managerDAO=new ManagerDAO();
		UserDAO userDAO=new UserDAO();
		Manager manager =new Manager();
		User user = new User();

		manager.setManagername(managername);
		manager.setPasswd(password);
		user.setUsername(username);
		user.setPasswd(password);
		
		System.out.println("输入的账户为："+managername);
		System.out.println("输入的密码为："+password);
		
		String codeSession=(String)request.getSession().getAttribute("code");
		//System.out.println(codeSession);
		//System.out.println(codes);
		String path="background/login.jsp";
		String z="3";//代表已已用的用户
		String zz="2";
		if(managerDAO.User(manager).getManagername()==null){
			if(userDAO.User(user).getUsername()!=null && !(userDAO.User(user).getUserstate()).equals(zz)){
				//System.out.println(userDAO.User(user));
				if(codes!=null){
					if(codes.endsWith(codeSession)){
						HttpSession session = request.getSession();
						session.setAttribute("username", username);
						request.setAttribute("password", password);
						request.setAttribute("autologin", autologin);
					    path="ManagerServlet?type=username";
					}else{
						out.flush();
					    out.println("<script>");
					    out.println("alert('验证码输入错误！');");
					    out.println("history.back();");
					    out.println("</script>");
					}
				}
			}else{
				out.flush();
			    out.println("<script>");
			    out.println("alert('查无此人或者密码错了！');");
			    out.println("history.back();");
			    out.println("</script>");
			}
		}else{
			if(managerDAO.User(manager).getManagername()!=null && !(managerDAO.User(manager).getMatate()).equals(z)){
				if(codes!=null){
					if(codes.endsWith(codeSession)){
						HttpSession session = request.getSession();
						session.setAttribute("managername", managername);
						request.setAttribute("password", password);
						request.setAttribute("autologin", autologin);
					    path="ManagerServlet?type=index";
					}else{
						out.flush();
					    out.println("<script>");
					    out.println("alert('验证码输入错误！');");
					    out.println("history.back();");
					    out.println("</script>");
					}
				}
			}else{
				out.flush();
			    out.println("<script>");
			    out.println("alert('查无此人或者密码错了！');");
			    out.println("history.back();");
			    out.println("</script>");
			}
		}
		request.getRequestDispatcher(path).forward(request, response);
		System.out.println(path);
	}
	
	public void username(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//创建session对象
		HttpSession session=request.getSession();
		String username=(String) session.getAttribute("username");
		String password=request.getParameter("password");
		username = AESUtil.encrypt(username);
		password = AESUtil.encrypt(password);
		String userautologin=request.getParameter("autologin");
		if(username==null){
			request.getRequestDispatcher("background/login.jsp").forward(request, response);
		}else{
			//Cookie[] cookies = request.getCookies();
			Cookie cookie = new Cookie("userautologin", username + "-"+password);
			cookie.setMaxAge(Integer.parseInt(userautologin));
			response.addCookie(cookie);
			response.sendRedirect("/");
		}
	}
	
	//session----index
	public void index(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//创建session对象
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String password=request.getParameter("password");
		managername = AESUtil.encrypt(managername);
		password = AESUtil.encrypt(password);
		String autologin=request.getParameter("autologin");

		if(managername==null){
			request.getRequestDispatcher("background/login.jsp").forward(request, response);
		}else{
			//获取所有的cookie，并将这些cookie存放在数组中
	   		Cookie[] cookies = request.getCookies();
	   		String LastAccessTime = null;
	   		String cnumber = "1";
	   		ManagerDAO managerDAO = new ManagerDAO();
	   		String ip = managerDAO.getRequestRealIp(request);
	   		
	   		//遍历cookie数组
		   		for(int i=0;cookies!=null && i<cookies.length;i++) {
		   			if((cookies[i].getName()).endsWith("ip")) {
		   				ip = cookies[i].getValue();
		   				break;
		   			}
		   		}
		   		for(int i=0;cookies!=null && i<cookies.length;i++) {
		   			if((cookies[i].getName()).endsWith("lastAccess")) {
		   				LastAccessTime = cookies[i].getValue();
		   				break;
		   			}
		   		}
		   		for(int i=0;cookies!=null && i<cookies.length;i++) {
		   			//如果cookie的名称为lastAccess，则获取该cookie的值
		   			if("number".equals(cookies[i].getName())){
		   				int numbers = Integer.parseInt(cookies[i].getValue())+1;
		   				cnumber = numbers+"";
		   				break;
		   			}
		   		}
		   		if(LastAccessTime == null) {
		   			LastAccessTime = "First Login!";
		   		}
		   	String nowtime = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss").format(new Date());
		   	Cookie cookienowtime = new Cookie("nowtime", nowtime);
	   		Cookie cookietime = new Cookie("lastAccess", LastAccessTime);
	   		
		   	Cookie cookienumber = new Cookie("number", cnumber);
			Cookie cookie = new Cookie("autologin", managername + "-"+password);
			Cookie cookieip = new Cookie("ip", ip);
			cookieip.setMaxAge(999999999);
			cookie.setMaxAge(Integer.parseInt(autologin));
			cookienumber.setMaxAge(999999999);
			cookienowtime.setMaxAge(999999999);
	   		cookietime.setMaxAge(999999999);
	   		response.addCookie(cookieip);
	   		response.addCookie(cookietime);
	   		response.addCookie(cookienowtime);
			response.addCookie(cookie);
			response.addCookie(cookienumber);
			//重写
			String path="background/index.jsp";
			String newpath=response.encodeRedirectURL(path);
			response.sendRedirect(newpath);
		}
	}
	//loginout
	public void loginout(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.getSession().removeAttribute("managername");
		Cookie[] cookies = request.getCookies();
		String ip=null;
		String nowtime = null;
		for(int i=0;cookies!=null && i<cookies.length;i++) {
   			//如果cookie的名称为lastAccess，则获取该cookie的值
   			if((cookies[i].getName()).endsWith("nowtime")) {
   				nowtime = cookies[i].getValue();
   				break;
   			}
		}
		for(int i=0;cookies!=null && i<cookies.length;i++) {
   			if((cookies[i].getName()).endsWith("ip")) {
   				ip = cookies[i].getValue();
   				break;
   			}
   		}
		Cookie cookieip = new Cookie("ip", ip);
		cookieip.setMaxAge(999999999);
		response.addCookie(cookieip);
		
		Cookie autologin = new Cookie("autologin", "msg");
		autologin.setMaxAge(0);
		response.addCookie(autologin);
		
		
		Cookie cookie = new Cookie("lastAccess", nowtime);
		cookie.setMaxAge(999999999);
		response.addCookie(cookie);
		PrintWriter out = response.getWriter();
		out.print("<script>alert('注销成功!');window.location.href='background/login.jsp'</script>");
	}
	
	
}
