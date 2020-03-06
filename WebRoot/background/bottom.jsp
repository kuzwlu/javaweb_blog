<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>底部bottom</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<style>
	#footer{font-size:12px;}
	.footer_pad{padding:7px 9px 5px 9px;}
	</style>
  </head>
  
	<body style="overflow-x:hidden; background:url(images/main/bottombg.jpg) repeat-x top left;" onselectstart="return false" oncontextmenu=return(false) >
	<table width="100%" border="0" cellspacing="0" cellpadding="0" id="footer">
	  <tr>
	    <td align="left" valign="middle" class="footer_pad">COPYRIGHT©2012    更多模板：<a href="http://www.mycodes.net/" target="_blank">源码之家</a> </td>
	    <td align="right" valign="middle" class="footer_pad"><img src="images/main/why.gif" width="12" height="12" alt="疑问图标" align="absmiddle">&nbsp;&nbsp;如有疑问请您联系TEL：010-88312038-8015</td>
	  </tr>
	</table>
	</body>
</html>
