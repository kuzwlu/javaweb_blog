package com.xxgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.xxgc.dao.ArticleDAO;
import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.MessageDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Manager;
import com.xxgc.model.Message;
import com.xxgc.model.MessageReplay;

public class MessageServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("page")){
			page(request, response);
		}else if(type.endsWith("findall")){
			findall(request, response);
		}else if(type.endsWith("content")){
			content(request, response);
		}else if(type.endsWith("del")){
			del(request, response);
		}else if(type.endsWith("update")){
			update(request, response);
		}else if(type.endsWith("message")){
			message(request, response);
		}else if(type.endsWith("replay")){
			replay(request, response);
		}else if(type.endsWith("the")){
			the(request, response);
		}else if(type.endsWith("ajax")){
			ajax(request, response);
		}else if(type.endsWith("text")){
			text(request, response);
		}
	}
	public void text(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		MessageDAO messageDAO = new MessageDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		UserDAO userDAO = new UserDAO();
		PrintWriter out = response.getWriter();
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		String mrcontent = request.getParameter("mecontent");
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String username=(String) session.getAttribute("username");
		int managerid = messageDAO.findbyname(managername);
		int userid = userDAO.findbyname(username);
		
		MessageReplay messageReplay= new MessageReplay();
		
		String mrtime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date());
		mrtime = articleDAO.dateToStamp(mrtime);
		String path="";
		
		if(mrcontent.equals("")){
			out.print("输入为空");
		}else{
			messageReplay.setMeid(meid);
			messageReplay.setMrmanager(managerid);
			messageReplay.setMruser(userid);
			messageReplay.setMrcontent(mrcontent);
			messageReplay.setMrtime(mrtime);
			if(messageDAO.replay(messageReplay)){
				ArrayList<MessageReplay> mrplaylist = messageDAO.findbymeid(meid);
				
				String html="";
				for(MessageReplay messagelist :mrplaylist){
					if( messagelist.getMrmanager()==0){
						int userid1 = messagelist.getMruser();
						String username1 = messageDAO.username(userid1);
						messagelist.setUsername(username1);
						
						String userimage = userDAO.getimage(messagelist.getUsername());
						
						String date = messagelist.getMrtime();
						date = articleDAO.stampToDate(date);
						messagelist.setMrtime(date);
						
						int num =(int) (Math.random() * 9999 + 1);
						
						html+="<div class='replaytext' onmouseover='move("+num+")' onmouseout='moveout("+num+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+num+"' " +
								"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMrcontent()+"&quot;, &quot;"+userimage+"&quot;, &quot;"+messagelist.getUsername()+"&quot; ,&quot;"+messagelist.getMrtime()+"&quot;)'>回复</a><img src='"+userimage+"'><p style='margin-left: 50px;'>"+messagelist.getUsername()+"</p>" +
								"<p style='margin-left: 50px;'>"+messagelist.getMrtime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
										"margin-left: 55px;'>"+messagelist.getMrcontent()+"</p></div>";
					}else{
					int id = messagelist.getMrmanager();
					String managername1 = messageDAO.managername(id);
					messagelist.setManagername(managername1+"（管理员）");
					
					ManagerDAO managerDAO = new ManagerDAO();
					String image = managerDAO.getimage(managername1);
					
					String date = messagelist.getMrtime();
					date = articleDAO.stampToDate(date);
					messagelist.setMrtime(date);
					
					int num =(int) (Math.random() * 9999 + 1);
					
					html+="<div class='replaytext' onmouseover='move("+num+")' onmouseout='moveout("+num+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+num+"' " +
							"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMrcontent()+"&quot;, &quot;"+image+"&quot;, &quot;"+messagelist.getManagername()+"&quot; ,&quot;"+messagelist.getMrtime()+"&quot;)'>回复</a><img src='"+image+"'><p style='margin-left: 50px;'>"+messagelist.getManagername()+"</p>" +
							"<p style='margin-left: 50px;'>"+messagelist.getMrtime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
									"margin-left: 55px;'>"+messagelist.getMrcontent()+"</p></div>";
					}
				}
				out.print(html);
			}else{
				out.print("系统出错");
			}
		}
		
	}
	public void ajax(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		MessageDAO messageDAO = new MessageDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		UserDAO userDAO  = new UserDAO();
		PrintWriter out = response.getWriter();
		String arids = request.getParameter("arid");
		int arid = Integer.parseInt(arids);
		String metitle = request.getParameter("metitle");
		String mecontent = request.getParameter("mecontent");
		
		System.out.println(mecontent);
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String username=(String) session.getAttribute("username");
		int managerid = messageDAO.findbyname(managername);
		int userid  = userDAO.findbyname(username);
		System.out.println(username);
		System.out.println(managername);
		
		Message message = new Message();
		
		String metime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date());
		metime = articleDAO.dateToStamp(metime);
		String mestate = "1";
		if(mecontent.equals("")){
			out.print("输入为空");
		}else{
			message.setArid(arid);
			message.setMetitle(metitle);
			message.setUserid(userid);
			message.setManagerid(managerid);
			message.setMecontent(mecontent);
			message.setMetime(metime);
			message.setMestate(mestate);
			if(messageDAO.add(message)){
				ArrayList<Message> messagelists = messageDAO.findbyarid(arid);
				String html="";
				int a = 1;
				for(Message messagelist :messagelists){
					if(messagelist.getManagerid()==0){
						int userid1 = messagelist.getUserid();
						String username1 = messageDAO.username(userid1);
						messagelist.setUsername(username1);
						
						String userimage = userDAO.getimage(messagelist.getUsername());
						
						String date = messagelist.getMetime();
						date = articleDAO.stampToDate(date);
						messagelist.setMetime(date);
						
						a+=a;
						
						html="<div class='messagetext' onmouseover='move("+a+")' onmouseout='moveout("+a+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+a+"' " +
						"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMecontent()+"&quot;, &quot;"+userimage+"&quot;, &quot;"+messagelist.getUsername()+"&quot; ,&quot;"+messagelist.getMetime()+"&quot;)'>回复</a><img src='"+userimage+"'><p style='margin-left: 50px;'>"+messagelist.getUsername()+"</p>" +
						"<p style='margin-left: 50px;'>"+messagelist.getMetime()+"</p><p style='margin-top: 5px;border-top: 1px solid #9e9e9e;" +
								"margin-left: 55px;'>"+messagelist.getMecontent()+"</p></div>";
						
					}else{
					int id = messagelist.getManagerid();
					String managername1 = messageDAO.managername(id);
					messagelist.setManagername(managername1+"（管理员）");
					
					ManagerDAO managerDAO = new ManagerDAO();
					String image = managerDAO.getimage(managername1);
					
					String date = messagelist.getMetime();
					date = articleDAO.stampToDate(date);
					messagelist.setMetime(date);
					
					a+=a;
					
					html="<div class='messagetext' onmouseover='move("+a+")' onmouseout='moveout("+a+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+a+"' " +
							"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMecontent()+"&quot;, &quot;"+image+"&quot;, &quot;"+messagelist.getManagername()+"&quot; ,&quot;"+messagelist.getMetime()+"&quot;)'>回复</a><img src='"+image+"'><p style='margin-left: 50px;'>"+messagelist.getManagername()+"</p>" +
							"<p style='margin-left: 50px;'>"+messagelist.getMetime()+"</p><p style='margin-top: 5px;border-top: 1px solid #9e9e9e;" +
									"margin-left: 55px;'>"+messagelist.getMecontent()+"</p></div>";
					}
				}
				out.print(html);
			}else{
				out.print("系统出错");
			}
		}
	}
	public void the(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		MessageDAO messageDAO = new MessageDAO();
		String mrids = request.getParameter("mrid");
		int mrid = Integer.parseInt(mrids);
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		String path="";
		if(messageDAO.delreplay(mrid)){
			path="MessageServlet?type=message&meid="+meid+"";
		}else{
			out.flush();
		    out.println("<script>");
		    out.println("alert('删除出错！');");
		    out.println("history.back();");
		    out.println("</script>");
		}
		System.out.println(messageDAO.delreplay(mrid));
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	public void replay(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messageDAO = new MessageDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		PrintWriter out = response.getWriter();
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		String mruser = request.getParameter("mruser");
		String mrcontent = request.getParameter("mrcontent");
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		int managerid = messageDAO.findbyname(managername);
		String mrtime = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss").format(new Date());
		mrtime = articleDAO.dateToStamp(mrtime);
		String path="";
		
		MessageReplay messageReplay= new MessageReplay();
		messageReplay.setMeid(meid);
		messageReplay.setMruser(managerid);
		messageReplay.setMrcontent(mrcontent);
		messageReplay.setMrtime(mrtime);
		
		if(messageDAO.replay(messageReplay)){
			path="MessageServlet?type=message&meid="+meid+"";
		}else{
			out.flush();
		    out.println("<script>");
		    out.println("alert('添加出错！');");
		    out.println("history.back();");
		    out.println("</script>");
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	public void message(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messageDAO = new MessageDAO();
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		Message message = messageDAO.findbyid(meid);
		ArrayList<MessageReplay> messageplay = messageDAO.findbymeid(meid);
		
		int arid = message.getArid();
		String artitle = messageDAO.artitle(arid);
		message.setArtitle(artitle);
		
		String date = message.getMetime();
		ArticleDAO articleDAO = new ArticleDAO();
		date = articleDAO.stampToDate(date);
		message.setMetime(date);
		
		int userid = message.getUserid();
		int id = message.getManagerid();
		if(userid==0){
			String managername = messageDAO.managername(id);
			message.setManagername(managername);
		}else{
			String username = messageDAO.username(userid);
			message.setManagername(username);
		}
		
		//System.out.println(meid);
		//System.out.println(messageplay);
		for(MessageReplay m:messageplay){
			String dates = m.getMrtime();
			dates = articleDAO.stampToDate(dates);
			m.setMrtime(dates);
			
			int managerid  = m.getMrmanager();
			int  users = m.getMruser();
			if(users==0){
				String mruser = messageDAO.managername(managerid);
				m.setManagername(mruser);
			}else{
				String mrmanager = messageDAO.username(users);
				m.setManagername(mrmanager);
			}
			
		}
		request.setAttribute("message", message);
		request.setAttribute("messageplay", messageplay);
		request.getRequestDispatcher("background/message_replay.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		MessageDAO messageDAO = new MessageDAO();
		
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		Message message = messageDAO.findbyid(meid);
		System.out.println(message.getMestate());
		if(message.getMestate().endsWith("1")){
			messageDAO.update(meid, "2");
		}else if(message.getMestate().endsWith("2")){
			messageDAO.update(meid, "1");
		}
		String path="MessageServlet?type=page&page=1";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//删除
	public void del(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messageDAO = new MessageDAO();
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		String path="MessageServlet?type=page&page=1";
		messageDAO.del(meid);
		request.getRequestDispatcher(path).forward(request, response);
	}
	//评论内容
	public void content(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messageDAO = new MessageDAO();
		String meids = request.getParameter("meid");
		int meid = Integer.parseInt(meids);
		Message messagelist = messageDAO.findbyid(meid);
		int arid = messagelist.getArid();
		String artitle = messageDAO.artitle(arid);
		messagelist.setArtitle(artitle);
		
		String date = messagelist.getMetime();
		ArticleDAO articleDAO = new ArticleDAO();
		date = articleDAO.stampToDate(date);
		messagelist.setMetime(date);
		
		int userid = messagelist.getUserid();
		int id = messagelist.getManagerid();
		if(userid==0){
			String managername = messageDAO.managername(id);
			messagelist.setManagername(managername);
		}else{
			String username = messageDAO.username(userid);
			messagelist.setManagername(username);
		}

		if(messagelist.getMestate().endsWith("1")){
			messagelist.setMestate("隐藏");
		}
		if(messagelist.getMestate().endsWith("2")){
			messagelist.setMestate("显示");
		}
		
		request.setAttribute("messagelist", messagelist);
		String path="background/message_info.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	//findall
	public void findall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messagerDAO=new MessageDAO();
		ArrayList<Message> messageList=messagerDAO.findall();
		request.setAttribute("messageList", messageList);
		request.getRequestDispatcher("background/main_message.jsp").forward(request, response);
	}
	
	public void page(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		MessageDAO messageDAO=new MessageDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		int startpage=page;//页码
		//int pagesize=messageDAO.setPagesize(5);//显示条数
		String path="MessageServlet?type=findall";
		ArrayList<Message> pageList=messageDAO.findbypage(startpage);
		for(Message p:pageList){
			int userid =p.getUserid();
			int id = p.getManagerid();
			if(userid==0){
				String managername = messageDAO.managername(id);
				p.setManagername(managername);
			}else{
				String username = messageDAO.username(userid);
				p.setManagername(username);
			}
			
			int arid = p.getArid();
			String artitle = messageDAO.artitle(arid);
			p.setArtitle(artitle);
			
			String date = p.getMetime();
			ArticleDAO articleDAO = new ArticleDAO();
			date = articleDAO.stampToDate(date);
			p.setMetime(date);
			
			String metitle = p.getMetitle();
			int maxlengh = 10;
			if(metitle.length() > maxlengh){
				metitle = metitle.substring(0, maxlengh);
			}else {
				metitle = metitle.substring(0, metitle.length());
			}
			p.setMetitle(metitle);
			
			String mecontent = p.getMecontent();
			if(mecontent.length() > maxlengh){
				mecontent = mecontent.substring(0, maxlengh);
			}else{
				mecontent = mecontent.substring(0, mecontent.length());
			}
			p.setMecontent(mecontent);
			
			if(p.getMestate().endsWith("1")){
				p.setMestate("隐藏");
			}
			if(p.getMestate().endsWith("2")){
				p.setMestate("显示");
			}
		}
		request.setAttribute("pageList", pageList);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", messageDAO.getPagesize());
		request.getRequestDispatcher(path).forward(request, response);
	}

}
