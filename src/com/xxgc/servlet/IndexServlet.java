package com.xxgc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xxgc.dao.ArticleDAO;
import com.xxgc.dao.CatalogDAO;
import com.xxgc.dao.ManagerDAO;
import com.xxgc.dao.MessageDAO;
import com.xxgc.dao.UserDAO;
import com.xxgc.model.Article;
import com.xxgc.model.Catalog;
import com.xxgc.model.Message;
import com.xxgc.model.MessageReplay;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String type = request.getParameter("type");
		if(type==null){
			index(request, response);
		}else if(type.endsWith("findid")){
			findid(request, response);
		}else if(type.endsWith("ajaxhome")){
			ajaxhome(request, response);
		}else if(type.endsWith("click")){
			click(request, response);
		}else if(type.endsWith("index")){
			index(request, response);
		}else if(type.endsWith("nextpage")){
			nextpage(request, response);
		}
	}
	public void nextpage(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ArticleDAO articleDAO = new ArticleDAO();
		MessageDAO messageDAO = new MessageDAO();
		CatalogDAO catalogDAO = new CatalogDAO();
		String nextpages = request.getParameter("nextpage");
		int nextpage = Integer.parseInt(nextpages);
		String maxpages = request.getParameter("maxpage");
		int maxpage = Integer.parseInt(maxpages);
		//nextpage++;
		int max=7;
		String arstate="1";
		String castate="1";
		ArrayList<Article> articles = articleDAO.findbystate(nextpage, max, arstate, castate);
		String blog ="<div id='remove'>";
		for(Article p :articles){
			
			int arid = p.getArid();
			int replaynumber =0;
			String mestate="1";
			ArrayList<Message> message = messageDAO.findbyarid(arid,mestate);
			ArrayList<Catalog> catalogs = catalogDAO.finabystate(castate);
			for(Message m:message){
				int meid = m.getMeid();
				ArrayList<MessageReplay> replay = messageDAO.findbymeid(meid);
				replaynumber+=replay.size();
			}
			int thenumber = message.size()+replaynumber;
			p.setMessage(thenumber);
			
			String title = p.getArtitle();
			if(title.length()>20){
				title=title.substring(0, 20);
			}else{
				if(title.length()==0){
					title="作者忘记写题目了！";
				}
				title=title.substring(0, title.length());
				
			}
			p.setArtitle(title);
			
			String htmlTag = p.getArcontent();
			//去除html标签，并且截取前30个字
			String html = articleDAO.removeHtmlTag(htmlTag);
			if(html.length()>107){
				html=html.substring(0, 107);
			}else {
				html=html.substring(0, html.length());
				if(html.length()==0 || html.length()==1){
					html="作者暂时没写内容！";
				}
				for(int i=1;i<=2;i++){
					html = html+"<br>";
					//System.out.println(147-html.length());
				}
			}

			String time=articleDAO.stampToDate(p.getArtime());
			
			String image = p.getArimage();
			image="upload/"+image;
			
			int caid = p.getCaid();
			String caname="";
			ArrayList<Catalog> catalog=articleDAO.finacaid(caid);
			for(Catalog c:catalog){
				caname=c.getCaname();
			}
			
			blog+="<div class='blogs' >" +
					"<figure><img src='"+image+"' onerror='whenError(this)'></figure>" +
					"<ul>" +
					"<h3><a onclick='getimage("+arid+")'>"+title+"</a></h3>" +
					"<p id='blogsp'>"+html+"...</p>" +
					"<p class='autor'><span class='lm f_l'>" +
					"<a href=''>"+caname+"</a></span>" +
					"<span class='dtime f_l'>"+time+"</span>" +
					"<span class='viewnum f_r'>浏览（<a href=''>"+p.getClick()+"</a>）</span>" +
					"<span class='pingl f_r'>评论（<a onclick='getimage("+arid+")' >"+thenumber+"</a>）</span></p>" +
					"</ul></div>";
		}
		//System.out.println(blog);
		String blogsend="</div>";
		out.print(blog+blogsend);
	}
	public void click(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		ArticleDAO articleDAO = new ArticleDAO();
		String arids = request.getParameter("arid");
		int arid = Integer.parseInt(arids);
		Article article = new Article();
		article.setArid(arid);
		Article articles = articleDAO.findbyarid(article);
		int click = articles.getClick();
		click+=1;
		articleDAO.click(click, arid);
	}
	public void ajaxhome(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		MessageDAO messageDAO = new MessageDAO();
		ArticleDAO articleDAO = new ArticleDAO();
		UserDAO userDAO = new UserDAO();
		
		HttpSession session=request.getSession();
		String managername=(String) session.getAttribute("managername");
		String username=(String) session.getAttribute("username");
//		String path="articlefindid.jsp";
//		request.getRequestDispatcher(path).forward(request, response);
		if(managername==null){
			if(username!=null){
				managername=username;
				managername="尊敬的"+managername+",您好！";
			}else{
				managername="未登录!";
			}
		}else{
			managername="尊敬的"+managername+"（管理员）,您好！";
		}
		
		String arids = request.getParameter("arid");
		int arid = Integer.parseInt(arids);
		PrintWriter out = response.getWriter();
		Article article = new Article();
		article.setArid(arid);
		article = articleDAO.findbyarid(article);
		
		String articles = article.getArcontent();
		String articelss=articleDAO.removeHtmlTag(articles);
		int articleslengh=articelss.length();
		String aruser = article.getAruser();
		
		String artitle = article.getArtitle();
		
		String thedate = article.getArtime();
		thedate = articleDAO.stampToDate(thedate);
		
		int replaynumber =0;
		String mestate="1";
		ArrayList<Message> messagecount = messageDAO.findbyarid(arid,mestate);
		for(Message m:messagecount){
			int meid = m.getMeid();
			ArrayList<MessageReplay> replay = messageDAO.findbymeid(meid);
			replaynumber+=replay.size();
		}
		int thenumber = messagecount.size()+replaynumber;
		
		int click = article.getClick();
		
		article = articleDAO.findbyarid(article);
		//System.out.println(article);
		String caname="";
		ArrayList<Catalog> catalog=articleDAO.finacaid(article.getCaid());
		System.out.println(catalog);
		for(Catalog c:catalog){
			caname=c.getCaname();
		}
		String articlenews = "";
		articlenews ="<div id='contenttext'><p id='artitle'>"+artitle+"</p><div id='articlenews'><span>作者：<a>"+aruser+"</a></span>&nbsp;&nbsp;" +
		"<span>时间：<a>"+thedate+"</a></span>&nbsp;&nbsp;" +
		"<span>分类：<a>"+caname+"</a></span>&nbsp;&nbsp;" +
		"<span>评论：<a>"+thenumber+"</a></span>&nbsp;&nbsp;" +
		
		"<span>浏览：<a>"+click+"</a></span>&nbsp;&nbsp;" +
		"<span>字数：<a>"+articleslengh+"</a></span>&nbsp;&nbsp;" +
				"</div>";
		String message = "<div id='messagecontent'></div><div id='user'>"+managername+"<form id='themessage'>"+
		"<input type='hidden' name='arid' value='"+arid+"'/><br>"+
		"<div id='thecontent'>请输入评论内容：</div><textarea rows='4' name='mecontent' id='mecontent' class='text'></textarea>" +
		"<div class='operator-box-btn'><span class='face-icon'  ><img src='upload/qq.png'style='width: 30px;'></span></div>"+
		"<input id='messagebiu' type='button' value='发射' onclick='postmessage()' style='margin: 5px 5px 5px 0px;display:block;padding: 0;border: 5px solid transparent;'>" +
		"<input id='replaybiu' type='button' value='发射' onclick='postreplay("+arid+")' style='margin: 5px 5px 5px 0px; display:none;padding: 0;border: 5px solid transparent;'>" +
		"</form></div></div><script type='text/javascript' src='js/face.js'></script>" +
		"<script type='text/javascript' src='js/sinaFaceAndEffec.js'></script>" +
		"<script type='text/javascript'>$('.face-icon').SinaEmotion($('.text'));</script>";
		ArrayList<Message> messagelists = messageDAO.findbyarid(arid,mestate);
		
		int a = 1;
		String html="<div id='message'>评论区：";
		for(Message messagelist :messagelists){
			
			int meid = messagelist.getMeid();
			ArrayList<MessageReplay> mrplaylists = messageDAO.findbymeid(meid);
			String htmls="";
			for(MessageReplay mrplaylist :mrplaylists){
				if(mrplaylist.getMrmanager()==0){
					int userid1 = mrplaylist.getMruser();
					String username1 = messageDAO.username(userid1);
					mrplaylist.setUsername(username1);
					
					String userimage = userDAO.getimage(mrplaylist.getUsername());
					
					String date = mrplaylist.getMrtime();
					date = articleDAO.stampToDate(date);
					mrplaylist.setMrtime(date);
					
					int num =(int) (Math.random() * 9999 + 1);
					
					htmls+="<div class='replaytext' onmouseover='move("+num+")' onmouseout='moveout("+num+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+num+"' " +
					"onclick='replay("+mrplaylist.getMeid()+", &quot;"+mrplaylist.getMrcontent()+"&quot;, &quot;"+userimage+"&quot;, &quot;"+mrplaylist.getUsername()+"&quot; ,&quot;"+mrplaylist.getMrtime()+"&quot;)'>回复</a><img src='"+userimage+"'><p style='margin-left: 50px;'>"+mrplaylist.getUsername()+"</p>" +
					"<p style='margin-left: 50px;'>"+mrplaylist.getMrtime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
							"margin-left: 55px;'>"+mrplaylist.getMrcontent()+"</p></div>";
					
				}else{
				int id = mrplaylist.getMrmanager();
				String managersname = messageDAO.managername(id);
				mrplaylist.setManagername(managersname+"（管理员）");
				
				ManagerDAO managerDAO = new ManagerDAO();
				String images = managerDAO.getimage(managersname);
				
				String date = mrplaylist.getMrtime();
				System.out.println(date);
				date = articleDAO.stampToDate(date);
				mrplaylist.setMrtime(date);
				int num =(int) (Math.random() * 9999 + 1);
				
				htmls+="<div class='replaytext' onmouseover='move("+num+")' onmouseout='moveout("+num+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+num+"' " +
						"onclick='replay("+mrplaylist.getMeid()+", &quot;"+mrplaylist.getMrcontent()+"&quot;, &quot;"+images+"&quot;, &quot;"+mrplaylist.getManagername()+"&quot; ,&quot;"+mrplaylist.getMrtime()+"&quot;)'>回复</a><img src='"+images+"'><p style='margin-left: 50px;'>"+mrplaylist.getManagername()+"</p>" +
						"<p style='margin-left: 50px;'>"+mrplaylist.getMrtime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
								"margin-left: 55px;'>"+mrplaylist.getMrcontent()+"</p></div>";
				}
			}
			
			int managerid = messagelist.getManagerid();
			String managername1 = messageDAO.managername(managerid);
			messagelist.setManagername(managername1+"（管理员）");
			
			int userid = messagelist.getUserid();
			String username1 = messageDAO.username(userid);
			messagelist.setUsername(username1);
			
			ManagerDAO managerDAO = new ManagerDAO();
			String managerimage = managerDAO.getimage(managername1);
			
			String userimage = userDAO.getimage(messagelist.getUsername());
			
			
			String date = messagelist.getMetime();
			date = articleDAO.stampToDate(date);
			messagelist.setMetime(date);
			a+=a;
			//System.out.println(messagelist.getMeid());
			if(messagelist.getManagerid()==0){
				html +="<div class='messagetext' onmouseover='move("+a+")' onmouseout='moveout("+a+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+a+"' " +
				"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMecontent()+"&quot;, &apos;"+userimage+"&apos; , &quot;"+messagelist.getUsername()+"&quot; ,&quot;"+messagelist.getMetime()+"&quot;)'>回复</a><img src='"+userimage+"'><p style='margin-left: 50px;'>"+messagelist.getUsername()+"</p>" +
				"<p style='margin-left: 50px;'>"+messagelist.getMetime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
						"margin-left: 55px;'>"+messagelist.getMecontent()+"</p>"+htmls+"</div>";
			}else{
			html +="<div class='messagetext' onmouseover='move("+a+")' onmouseout='moveout("+a+")'><a style='float: right;visibility:hidden;margin-right: 5px;' id='"+a+"' " +
					"onclick='replay("+messagelist.getMeid()+", &quot;"+messagelist.getMecontent()+"&quot;, &apos;"+managerimage+"&apos; , &quot;"+messagelist.getManagername()+"&quot; ,&quot;"+messagelist.getMetime()+"&quot;)'>回复</a><img src='"+managerimage+"'><p style='margin-left: 50px;'>"+messagelist.getManagername()+"</p>" +
					"<p style='margin-left: 50px;'>"+messagelist.getMetime()+"</p><p style='margin-top: 5px;border-top: 1px solid #99999969;" +
							"margin-left: 55px;'>"+messagelist.getMecontent()+"</p>"+htmls+"</div>";
			}
		}
		
		out.print(articlenews+articles + html +message);
	}
	public void findid(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String arids = request.getParameter("arid");
		int arid = Integer.parseInt(arids);
		Article article = new Article();
		article.setArid(arid);
		ArticleDAO articleDAO = new ArticleDAO();
		article = articleDAO.findbyarid(article);
		request.setAttribute("article", article);
		String path="articlefindid.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
	public void index(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO = new ArticleDAO();
		MessageDAO messageDAO = new MessageDAO();
		String nowpages = request.getParameter("nowpage");
		if(nowpages==null){
			nowpages="1";
		}
		int nowpage = Integer.parseInt(nowpages);
		int max=7;
		String arstate="1";
		String castate="1";
		ArrayList<Article> articles = articleDAO.findbystate(nowpage, max, arstate, castate);
		for(Article p :articles){
			int arid = p.getArid();
			int replaynumber =0;
			String mestate="1";
			ArrayList<Message> message = messageDAO.findbyarid(arid,mestate);
			for(Message m:message){
				int meid = m.getMeid();
				ArrayList<MessageReplay> replay = messageDAO.findbymeid(meid);
				replaynumber+=replay.size();
			}
			int thenumber = message.size()+replaynumber;
			p.setMessage(thenumber);
			
			String title = p.getArtitle();
			if(title.length()>20){
				title=title.substring(0, 20);
			}else{
				if(title.length()==0){
					title="作者忘记写题目了！";
				}
				title=title.substring(0, title.length());
				
			}
			p.setArtitle(title);
			
			String htmlTag = p.getArcontent();
			//去除html标签，并且截取前30个字
			String html = articleDAO.removeHtmlTag(htmlTag);
			if(html.length()>107){
				html=html.substring(0, 107);
			}else {
				html=html.substring(0, html.length());
				if(html.length()==0 || html.length()==1){
					html="作者暂时没写内容！";
				}
				for(int i=1;i<=2;i++){
					html = html+"<br>";
					//System.out.println(147-html.length());
				}
			}
			p.setArcontent(html);

			String time=articleDAO.stampToDate(p.getArtime());
			p.setArtime(time);
			
			String image = p.getArimage();
			image="upload/"+image;
			p.setArimage(image);
			//System.out.println(image);
			
			
			
		}
		int count=articleDAO.count();
		int maxpage = count/max;
		if(count%max>0){
			maxpage++;
		}
		String nextpage="<a onclick='nextpage("+maxpage+")'>点击加载更多</a>";
		CatalogDAO catalogDAO = new CatalogDAO();
		ArrayList<Catalog> catalogs = catalogDAO.finabystate(castate);
		request.setAttribute("catalogs", catalogs);
		request.setAttribute("articles", articles);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("count", count);
		request.setAttribute("nowpage", nowpage);
		request.setAttribute("nextpage", nextpage);
		String path="tuxiaobai.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}

}
