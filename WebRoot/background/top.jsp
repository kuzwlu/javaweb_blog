<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>后台页面头部</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/css.css" type="text/css" rel="stylesheet" />
  </head>
  
	<body onselectstart="return false" oncontextmenu=return(false) style="overflow-x:hidden;">
	
	<!--禁止网页另存为-->
	<noscript><iframe scr="*.htm"></iframe></noscript>
	<!--禁止网页另存为-->
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="header">
	  <tr>
	    <td rowspan="2" align="left" valign="top" id="logo"><img src="images/main/logo.jpg" width="74" height="64"></td>
	    <td align="left" valign="bottom">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td align="left" valign="bottom" id="header-name">花怜小屋</td>
	        <td align="right" valign="top" id="header-right">
	        	<a target="_parent" onFocus="this.blur()" class="admin-out" onclick="del()">注销</a>
	            <a href="background/index.jsp" target="_parent" onFocus="this.blur()" class="admin-home">管理首页</a>
	        	<a href="" target="_blank" onFocus="this.blur()" class="admin-index">网站首页</a>       	
	            <span>
	<!-- 日历 -->
	<SCRIPT type=text/javascript src="js/clock.js"></SCRIPT>
	<SCRIPT type=text/javascript>showcal();</SCRIPT>
	            </span>
	        </td>
	      </tr>
	    </table></td>
	  </tr>
	  <tr>
	    <td align="left" valign="bottom">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td align="left" valign="top" id="header-admin">后台管理系统</td>
	        <td align="left" valign="bottom" id="header-menu">
	        <a href="background/main.jsp" target="mainFrame" onFocus="this.blur()" id="m1">后台首页</a>
	        <a href="ManagerServlet?type=page&page=${1}" target="mainFrame" onFocus="this.blur()" id="m2">用户管理</a>
	        <a href="CatalogServlet?type=cataloglist&page=${1 }" target="mainFrame" onFocus="this.blur()" id="m3">栏目管理</a>
	        <a href="ArticleServlet?type=page&page=${1 }" target="mainFrame" onFocus="this.blur()" id="m5">文章管理</a>
	        <a href="MessageServlet?type=page&page=1" target="mainFrame" onFocus="this.blur()" id="m5">留言管理</a>
	        <a href="backgorund/" target="mainFrame" onFocus="this.blur()" id="m6">附件管理</a>
	        <a href="index.jsp" target="mainFrame" onFocus="this.blur()" id="m7">站点管理</a>
	        </td>
	      </tr>
	    </table></td>
	  </tr>
	</table>
	<script type="text/javascript">
		function del(){
		   if(confirm("您确定要注销吗？")){
		   		window.parent.location.href='ManagerServlet?type=loginout';
		   		return true;
			}
			return false;
		}
		
     </script>
	</body>
	
</html>
