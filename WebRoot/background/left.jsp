<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>左侧导航menu</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/sdmenu.js"></script>
	<script src="js/jquery-3.3.1.min.js"></script>
	<script type="text/javascript">
		// <![CDATA[
		var myMenu;
		window.onload = function() {
			myMenu = new SDMenu("my_menu");
			myMenu.init();
			
		};
		
		// ]]>
	</script>
	<style type=text/css>
	html{ SCROLLBAR-FACE-COLOR: #538ec6; SCROLLBAR-HIGHLIGHT-COLOR: #dce5f0; SCROLLBAR-SHADOW-COLOR: #2c6daa; SCROLLBAR-3DLIGHT-COLOR: #dce5f0; SCROLLBAR-ARROW-COLOR: #2c6daa;  SCROLLBAR-TRACK-COLOR: #dce5f0;  SCROLLBAR-DARKSHADOW-COLOR: #dce5f0; overflow-x:hidden;}
	body{overflow-x:hidden; background:url(images/main/leftbg.jpg) left top repeat-y #f2f0f5; width:194px;}
	</style>
  </head>
	
	<body onselectstart="return false;" ondragstart="return false;" oncontextmenu="return false;">
	<div id="left-top">
		<div><a href="background/headimage.jsp" target="mainFrame" onFocus="this.blur()"><img src="headimage/zzw.jpg" width="44" height="44" id="qqq"/></a></div>
	    <span>用户：${sessionScope.managername}<br>角色：超级管理员</span>
	</div>
	    <div style="float: left" id="my_menu" class="sdmenu">
	      <div class="collapsed">
	        <span>管理员设置</span>
	        <a href="background/headimage.jsp" target="mainFrame" onFocus="this.blur()">头像设置</a>
	        <a href="ManagerServlet?type=page&page=${1}" target="mainFrame" onFocus="this.blur()">管理员列表</a>
	        <a href="background/main_info.jsp" target="mainFrame" onFocus="this.blur()">添加管理员</a>
	        <a href="ManagerServlet?type=rec&page=${1 }" target="mainFrame" onFocus="this.blur()">回收站</a>
	      </div>
	      <div>
	        <span>用户设置</span>
	        <a href="UserServlet?type=page&page=${1}" target="mainFrame" onFocus="this.blur()">用户列表</a>
	      </div>
	      <div>
	        <span>栏目设置</span>

	        <a href="CatalogServlet?type=cataloglist&page=${1 }" target="mainFrame" onFocus="this.blur()">栏目列表</a>
	        <a href="CatalogServlet?type=count" target="mainFrame" onFocus="this.blur()">添加栏目</a>
	      </div>
	      
	      <div>
	        <span>文章设置</span>
	        <a href="ArticleServlet?type=page&page=${1 }" target="mainFrame" onFocus="this.blur()">文章列表</a>
	        <a href="ArticleServlet?type=article" target="mainFrame" onFocus="this.blur()">添加文章</a>
	      </div>
	      
	      <div>
	        <span>评论设置</span>
	        <a href="MessageServlet?type=page&page=1" target="mainFrame" onFocus="this.blur()">评论板</a>
	      </div>
	    <script type="text/javascript">
	    	imagesAjax();
	    	function imagesAjax() {
				$.ajax({
					url: "HeadImage?type=getimage",
					data: {},
					type: "POST",
					//dataType: 'json',
					success:function(data) {
						//console.log(src);
							$("#qqq").attr("src",data );
					}
				});
				setTimeout("imagesAjax()", 10000);
			}
			
	    </script>
	</body>
</html>
