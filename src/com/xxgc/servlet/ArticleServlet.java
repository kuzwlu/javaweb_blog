package com.xxgc.servlet;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;


import com.xxgc.dao.ArticleDAO;
import com.xxgc.dao.CatalogDAO;
import com.xxgc.model.Article;
import com.xxgc.model.Catalog;
import com.xxgc.utils.DelHTMLTag;

public class ArticleServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String type=request.getParameter("type");
		if(type.endsWith("article")){
			article(request, response);
		}else if(type.endsWith("add")){
			add(request, response);
		}else if(type.endsWith("page")){
			page(request, response);
		}else if(type.endsWith("findall")){
			findall(request, response);
		}else if(type.endsWith("exnumber")){
			exnumber(request, response);
		}else if(type.endsWith("name")){
			name(request, response);
		}else if(type.endsWith("test")){
			test(request, response);
		}else if(type.endsWith("updatejsp")){
			updatejsp(request, response);
		}else if(type.endsWith("update")){
			update(request, response);
		}else if(type.endsWith("delete")){
			delete(request, response);
		}else if(type.endsWith("down")){
			down(request, response);
		}
	}
	//下载图片
	public void down(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		 //获得请求文件名  
        String filename = request.getParameter("filename");  
        //filename = new String(filename.trim().getBytes("ISO-8859-1"),"UTF-8");
        //System.out.println(filename);  
          
        //设置文件MIME类型  
        response.setContentType(getServletContext().getMimeType(filename));  
        //设置Content-Disposition  
        response.setHeader("Content-Disposition", "attachment;filename="+filename);  
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  
        String fullFileName = getServletContext().getRealPath("/upload/" + filename);  
        //System.out.println(fullFileName);  
        //读取文件  
        InputStream in = new FileInputStream(fullFileName);  
        OutputStream out = response.getOutputStream();  
          
        //写文件  
        int b;  
        while((b=in.read())!= -1)  
        {  
            out.write(b );  
        }  
          
        in.close();  
        out.close();  
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int arid = Integer.parseInt(request.getParameter("arid"));
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = new Article();
		article.setArid(arid);
		String path="ArticleServlet?type=page&page=1";
	
		articleDAO.delete(article);

		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//添加文章
	public void add(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = new Article();
		String path="background/articleadd.jsp";
		//上传首页图片
        try {
        	//创建DiskFileItemFactory工厂对象
        	response.setContentType("text/html; charset=UTF-8");
        	DiskFileItemFactory factory = new DiskFileItemFactory();
        	//设置文件缓存目录，如果不存在就创建
        	File f = new File("E:\\TempFolder");
        	if(!f.exists()){
        		f.mkdirs();
        	}
        	//设置文件缓存路径
        	factory.setRepository(f);
        	//创建ServletFileUpload对象
        	ServletFileUpload fileupload = new ServletFileUpload(factory);
        	//设置字符编码
        	fileupload.setHeaderEncoding("utf-8");
        	//解析request，得到上传文件的FileItem对象
        	List<FileItem> fileitems = fileupload.parseRequest(request);
        	//获取字符流
        	//遍历集合
        	for(FileItem fileitem : fileitems){
        		//判断是否为普通字段
        		if(fileitem.isFormField()){
        			//获得字段名和字段值
        			String name = fileitem.getFieldName();
        			if(name.equals("arid")){
        				if(!fileitem.getString().equals("")){
        					String arids = fileitem.getString("utf-8");
        					int arid = Integer.parseInt(arids);
        					//System.out.println(arid);
        					article.setArid(arid);
        				}
        			}
        			if(name.equals("caid")){
        				if(!fileitem.getString().equals("")){
        					String caids = fileitem.getString("utf-8");
        					int caid = Integer.parseInt(caids);
        					//System.out.println(caid);
        					article.setCaid(caid);
        				}
        			}
        			if(name.equals("arnumber")){
        				if(!fileitem.getString().equals("")){
        					String arnumber = fileitem.getString("utf-8");
        					//System.out.println(arnumber);
        					article.setArnumber(arnumber);
        				}
        			}
        			if(name.equals("artitle")){
        					String artitle = fileitem.getString("utf-8");
        					//System.out.println(artitle);
        					article.setArtitle(artitle);
        			}
        			if(name.equals("aruser")){
        					String aruser = fileitem.getString("utf-8");
        					//System.out.println(aruser);
        					article.setAruser(aruser);
        			}
        			if(name.equals("artime")){
        				if(!fileitem.getString().equals("")){
        					String artimes = fileitem.getString("utf-8");
        					articleDAO.dateToStamp(artimes);
        					String artime=articleDAO.getNowtime();
        					//System.out.println(artime);
        					article.setArtime(artime);
        				}
        			}
        			if(name.equals("arstate")){
        				if(!fileitem.getString().equals("")){
        					String arstate = fileitem.getString("utf-8");
        					//System.out.println(arstate);
        					article.setArstate(arstate);
        				}
        			}
        			if(name.equals("content")){
        					String content = fileitem.getString("utf-8");
        					article.setArcontent(content);
        					
        			}
        		}else{
        			//获得上传文件名
	        			String filename = fileitem.getName();
	        			//出来上传文件
	        			if(filename != null && !filename.equals("")){
	        				
	        				//取出文件名
	        				filename = filename.substring(filename.lastIndexOf("\\")+1);
	        				//文件名唯一
	        				filename = UUID.randomUUID().toString() + "_" + filename;
	        				//在服务器创建同名文件
	        				String imagepath="/upload/";
	        				//将服务器中文件路径与文件名组成完整的服务器端路径
	        				
	        				String webpath= getServletContext().getRealPath(imagepath+filename);

	        				article.setArimage(filename);
	        				//创建文件
	        				File file= new File(webpath);
	        				file.getParentFile().mkdirs();
	        				file.createNewFile();
	        				//获得上传文件流
	        				InputStream in = fileitem.getInputStream();
	        				//使用FileOutputStream打开服务器端上传的文件
	        				FileOutputStream out = new FileOutputStream(file);
	        				//流的对拷
	        				byte[] buffer = new byte[1024];
	        				int len;
	        				//开始读取上传的字节
	        				while((len = in.read(buffer))>0)
	        					out.write(buffer,0,len);
	        					//关闭流
	        					in.close();
	        					out.close();
	        					//删除临时文件
	        					fileitem.delete();
	        			}else{
	        				article.setArimage("err.png");
	        			}
        		}
        	}
        	
        	
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误");
        }
		
		if(articleDAO.add(article)){
			path="ArticleServlet?type=page&page=1";
		}
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	//编辑
	private static final long serialVersionUID = 1L;
	public void update(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String arimage = request.getParameter("arimage");
		arimage = arimage.substring(7, arimage.length());
		ArticleDAO articleDAO = new ArticleDAO();
		//System.out.println(articleDAO.getEncode(arimage));
		Article article = new Article();
		String path="background/articleupdate.jsp";
		//上传首页图片
        try {
        	//创建DiskFileItemFactory工厂对象
        	response.setContentType("text/html; charset=UTF-8");
        	DiskFileItemFactory factory = new DiskFileItemFactory();
        	//设置文件缓存目录，如果不存在就创建
        	File f = new File("E:\\TempFolder");
        	if(!f.exists()){
        		f.mkdirs();
        	}
        	//设置文件缓存路径
        	factory.setRepository(f);
        	//创建ServletFileUpload对象
        	ServletFileUpload fileupload = new ServletFileUpload(factory);
        	//设置字符编码
        	fileupload.setHeaderEncoding("utf-8");
        	//解析request，得到上传文件的FileItem对象
        	List<FileItem> fileitems = fileupload.parseRequest(request);
        	//获取字符流
        	//遍历集合
        	for(FileItem fileitem : fileitems){
        		//判断是否为普通字段
        		if(fileitem.isFormField()){
        			//获得字段名和字段值
        			String name = fileitem.getFieldName();
        			if(name.equals("arid")){
        				if(!fileitem.getString().equals("")){
        					String arids = fileitem.getString("utf-8");
        					int arid = Integer.parseInt(arids);
        					//System.out.println(arid);
        					article.setArid(arid);
        				}
        			}
        			if(name.equals("caid")){
        				if(!fileitem.getString().equals("")){
        					String caids = fileitem.getString("utf-8");
        					int caid = Integer.parseInt(caids);
        					//System.out.println(caid);
        					article.setCaid(caid);
        				}
        			}
        			if(name.equals("arnumber")){
        				if(!fileitem.getString().equals("")){
        					String arnumber = fileitem.getString("utf-8");
        					//System.out.println(arnumber);
        					article.setArnumber(arnumber);
        				}
        			}
        			if(name.equals("artitle")){
        					String artitle = fileitem.getString("utf-8");
        					//System.out.println(artitle);
        					article.setArtitle(artitle);
        			}
        			if(name.equals("aruser")){
        					String aruser = fileitem.getString("utf-8");
        					//System.out.println(aruser);
        					article.setAruser(aruser);
        				
        			}
        			if(name.equals("artime")){
        				if(!fileitem.getString().equals("")){
        					String artimes = fileitem.getString("utf-8");
        					articleDAO.dateToStamp(artimes);
        					String artime=articleDAO.getNowtime();
        					//System.out.println(artime);
        					article.setArtime(artime);
        				}
        			}
        			if(name.equals("arstate")){
        				if(!fileitem.getString().equals("")){
        					String arstate = fileitem.getString("utf-8");
        					//System.out.println(arstate);
        					article.setArstate(arstate);
        				}
        			}
        			if(name.equals("content")){
        					String content = fileitem.getString("utf-8");
        					article.setArcontent(content);
        			}
        		}else{
        			//获得上传文件名
        			String filename = fileitem.getName();
        			//出来上传文件
        			if(filename != null && !filename.equals("")){
        				//取出文件名
        				filename = filename.substring(filename.lastIndexOf("\\")+1);
        				//文件名唯一
        				filename = UUID.randomUUID().toString() + "_" + filename;
        				//在服务器创建同名文件
        				String imagepath="/upload/";
        				//将服务器中文件路径与文件名组成完整的服务器端路径
        				String webpath= getServletContext().getRealPath(imagepath+filename);
        				article.setArimage(filename);
        				//创建文件
        				File file= new File(webpath);
        				file.getParentFile().mkdirs();
        				file.createNewFile();
        				//获得上传文件流
        				InputStream in = fileitem.getInputStream();
        				//使用FileOutputStream打开服务器端上传的文件
        				FileOutputStream out = new FileOutputStream(file);
        				//流的对拷
        				byte[] buffer = new byte[1024];
        				int len;
        				//开始读取上传的字节
        				while((len = in.read(buffer))>0)
        					out.write(buffer,0,len);
        					//关闭流
        					in.close();
        					out.close();
        					//删除临时文件
        					fileitem.delete();
        			}else{
        				
        				article.setArimage(arimage);
        				//System.out.println("这是不上传图片的编码"+articleDAO.getEncode(arimage));
        				//System.out.println("这是else的"+arimage);
        			}
        		}
        	}
        	
        	
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("错误");
        }
		if(articleDAO.update(article)){
			path="ArticleServlet?type=page&page=1";
		}
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	
	//跳转更新页面
	public void updatejsp(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		response.setContentType("text/html;chatest=utf-8");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String arids=request.getParameter("arid");
		int arid = Integer.parseInt(arids);
		
		ArticleDAO articleDAO = new ArticleDAO();
		Article article = new Article();
		article.setArid(arid);
		Article articlelist= articleDAO.findbyarid(article);
		
		ArrayList<Catalog> catalog=articleDAO.findcatalog(articlelist.getCaid());
		
		ArrayList<Catalog> caidlist=articleDAO.finacaid(articlelist.getCaid());
		for(Catalog caname:caidlist){
			if(caname.getCaname()==(null)){
				articlelist.setCaids("栏目出错");
			}
			articlelist.setCaids(caname.getCaname());
		}
		request.setAttribute("catalog", catalog);
		
		request.setAttribute("articlelist", articlelist);
		String imagepaths = articlelist.getArimage();
		System.out.println(articleDAO.getEncode(imagepaths));
		String thepath="upload/";
		String imagepath = thepath+imagepaths;
//		System.out.println(articleDAO.getEncode(imagepath));
//		System.out.println(imagepath);
		request.setAttribute("imagepaths", imagepaths);
		request.setAttribute("imagepath", imagepath);
		request.getRequestDispatcher("background/articleupdate.jsp").forward(request, response);
	}
	
	//搜索之后分页的跳转
	public void test(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int page=Integer.parseInt(request.getParameter("page"));
		String artitle=request.getParameter("artitle");
		ArticleDAO articleDAO = new ArticleDAO();
		//转码get,以通过过滤器,现更改servler.xml无需修改编码了
		//System.out.println(articleDAO.getEncode(artitle));
		System.out.println(artitle);
		request.getRequestDispatcher("ArticleServlet?type=name&page="+page+"&artitle="+artitle).forward(request, response);
	}
	
	//搜索文章名的分页articlefind
	public void name(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO=new ArticleDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		int startpage=page;//页码
		int pagesize=articleDAO.setPagesize(1);//显示条数
		String artitle=request.getParameter("artitle");
		
		ArrayList<Article> pageLists=articleDAO.namepage(startpage, artitle);
		
		for(Article p:pageLists){
			String title = p.getArtitle();
			if(title.length()>10){
				title=title.substring(0, 10);
			}else{
				title=title.substring(0, title.length());
				
			}
			p.setArtitle(title);
			String htmlTag = p.getArcontent();
			//去除html标签，并且截取前30个字
			String html = articleDAO.removeHtmlTag(htmlTag);
			if(html.length()>20){
				html=html.substring(0, 20);
			}else {
				html=html.substring(0, html.length());
			}
			p.setArcontent(html);
			ArrayList<Catalog> caid=articleDAO.finacaid(p.getCaid());
			for(Catalog c:caid){
				if(c.getCaname()==(null)){
					p.setCaids("栏目出错");
				}
				p.setCaids(c.getCaname());
			}
			String time=articleDAO.stampToDate(p.getArtime());
			p.setArtime(time);
			if(p.getArstate().endsWith("1")){
				p.setArstate("显示");
			}
			if(p.getArstate().endsWith("2")){
				p.setArstate("隐藏");
			}
		}
		request.setAttribute("pageLists", pageLists);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", pagesize);
		request.setAttribute("artitle", artitle);
		
		ArrayList<Article> articleList=articleDAO.nameAll(artitle);
		
		request.setAttribute("articleList", articleList);
		request.getRequestDispatcher("background/articlefind.jsp").forward(request, response);
	}
	
	//上移
	public void exnumber(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String arnumber=request.getParameter("arnumber");
		ArticleDAO articleDAO=new ArticleDAO();
		String canumbers=articleDAO.prenumber(arnumber);
		
		articleDAO.exnumber(arnumber, canumbers);
		String path="ArticleServlet?type=page&page=1";
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	//全查
	public void findall(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO=new ArticleDAO();
		ArrayList<Article> articleList=articleDAO.finaAll();
		request.setAttribute("articleList", articleList);
		
		request.getRequestDispatcher("background/articlelist.jsp").forward(request, response);
	}
	
	//全查的分页
	public void page(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO=new ArticleDAO();
		int page=Integer.parseInt(request.getParameter("page"));
		int startpage=page;//页码
		//int pagesize=articleDAO.setPagesize(5);//显示条数
		String path="ArticleServlet?type=findall";
		ArrayList<Article> pageList=articleDAO.page(startpage);
		

		for(Article p:pageList){
			String title = p.getArtitle();
			if(title.length()>10){
				title=title.substring(0, 10);
			}else{
				title=title.substring(0, title.length());
				
			}
			p.setArtitle(title);
			String htmlTag=p.getArcontent();
			//去除html标签，并且截取前20个字
			String html = DelHTMLTag.delHTMLTag(htmlTag);
				if(html.length()>20){
					html=html.substring(0, 20);
				}else {
					html=html.substring(0, html.length());
				}
			p.setArcontent(html);
			ArrayList<Catalog> caid=articleDAO.finacaid(p.getCaid());
				for(Catalog c:caid){
					if(c.getCaname()==(null)){
						p.setCaids("栏目出错");
					}
					p.setCaids(c.getCaname());
				}
			String time=articleDAO.stampToDate(p.getArtime());
			p.setArtime(time);
				if(p.getArstate().endsWith("1")){
					p.setArstate("显示");
				}
				if(p.getArstate().endsWith("2")){
					p.setArstate("隐藏");
				}
		}
		
		request.setAttribute("pageList", pageList);
		request.setAttribute("startpage", startpage);
		request.setAttribute("pagesize", articleDAO.getPagesize());
		request.getRequestDispatcher(path).forward(request, response);
		
	}
	
	
	//跳转添加文章
	public void article(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		ArticleDAO articleDAO = new ArticleDAO();
		ArrayList<Catalog> catalog=articleDAO.findcatalog();
		request.setAttribute("catalog", catalog);
		
		String arnumberstr = articleDAO.number();
		if(arnumberstr==null){
			arnumberstr = "0";
		}
		int arnumbers = Integer.parseInt(arnumberstr)+1;
		String arnumber = arnumbers+"";
		request.setAttribute("arnumber", arnumber);
		request.getRequestDispatcher("background/articleadd.jsp").forward(request, response);
	}
}
