<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>花怜小屋提醒您，访问出错了！</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="UTF-8">
		<title>404页面</title>
		<meta name="renderer" content="webkit" />
		<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="/favicon.ico">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <div class="container">
			<img class="bg" src="404.png"/>
			<div class="btn">
				<a href="/project201903/" class="goindex">返回首页</a>
				<a href="http://wpa.qq.com/msgrd?v=3&uin=1726539504&site=qq&menu=yes" target="_blank" class="lx">咨询站长</a>
				<div style="clear:both;"></div>
			</div>
		</div>
  </body>
</html>
