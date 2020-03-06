<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
			
  </head>
  	<style>
		/* 必须修改form标签的action，修改为自己的servlet的url地址 */
		a {
			color: #f6f6f6;
			text-decoration: none;
		}
		/* 背景图片地址 */
		body{
			background: url("./2.jpg") repeat scroll top left;
		}
		/*最外面的大盒子*/
		
		.loginbox {
			width: 300px;
			height: 320px;
			position: relative;
			top: 150px;
			background-color: rgba(255, 255, 255, 0.5);
			border-radius: 10px;
			box-shadow: 0 0 1px #cccbce;
			padding: 12.5px;
			margin: 0 auto;
		}
		/* 盒子点击 */
		.loginbox:hover {
			box-shadow: 0 0 1px #25B15E;
			transition: All 0.3s;
		}
		/* 最上面的登录注册的盒子 */
		.logintext {
			height: 50px;
			text-align: center;
			background-color: #ffffff80;
			border-radius: 5px;
		}
		/* 登录注册的a标签 */
		.logintext a {
			position: relative;
			font-size: 20px;
			color: #58666E;
			padding-right: 40px;
			padding-left: 40px;
			top: 13px;
			font-weight: bold;
		}
		
		.logintext a:hover {
			color: #25B15E;
		}
		
		.logintext a:active {
			color: #25B15E;
		}
		
		.logintext a:focus {
			color: #25B15E;
		}
		/* 用户名密码的大盒子 */
		.logintext2 {
			position: relative;
			height: 250px;
			text-align: center;
			background-color: #ffffff80;
			border-radius: 5px;
			top: 17px;
			overflow: hidden;
		}
		/* 用户的盒子 */
		.user {
			position: relative;
			font-size: 16px;
			color: #58666E;
			top: 20px;
			font-weight: 550;
		}
		/* 密码的盒子 */
		.password {
			position: relative;
			font-size: 16px;
			color: #58666E;
			top: 30px;
			font-weight: 550;
		}
		
		.user input {
			height: 30px;
			border-radius: 5px;
			outline: none;
			border: 1.2px solid rgba(46, 49, 49, 0.2);
			background-color: transparent;
		}
		
		.password input {
			height: 30px;
			border-radius: 5px;
			outline: none;
			border: 1.2px solid rgba(46, 49, 49, 0.2);
			background-color: transparent;
		}
		/* 登录盒子装登录的input */
		.denglu {
			height: 40px;
			position: relative;
			top: 45px;
		}
		/* 登录按钮的样式 */
		.denglu input {
			/*background-color: transparent;*/
			outline: none;
			border: 0 none;
			background-color: #b1257f24;
			height: 40px;
			width: 180px;
			border-radius: 9px;
			font-size: 16px;
			font-weight: bold;
		}
		/* 动画时长 */
		.denglu input:hover {
			background-color: #25b15e96;
			transition: All 0.6s;
		}
		
		.logintext2 form {
			height: 252px;
		}

		/* 设置注册界面用户盒子距logintext2顶部的高度 他们共用user*/
		#register .user {
			top: 27px;
		}
		
		#register .password {
			top: 41px;
		}
		/* 设置注册界面第二个密码盒子距logintext2顶部的高度 */
		.password2 {
			position: relative;
			font-size: 16px;
			color: #58666E;
			top: 55px;
			font-weight: 550;
		}
		/* 设置注册界面第二个密码input距logintext2顶部的高度 */
		.password2 input {
			height: 30px;
			border-radius: 5px;
			outline: none;
			border: 1.2px solid rgba(46, 49, 49, 0.2);
			background-color: transparent;
		}
		/* 设置注册界面登录距logintext2顶部的高度 */
		#register .denglu {
			top: 80px;
		}
	</style>
  <body>
<div class="loginbox">
			<div class="logintext">
				<a href="login.jsp#login">登录</a>
				<a href="login.jsp#register">注册</a>
			</div>
			<div class="logintext2">
				<div id="login">
					<form action="/project201903/Login" method="post">
						<div class="user">
							用户:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="  请输入用户" autocomplete="off" name="username" />
						</div>
						<div class="password">
							密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="  请输入密码" autocomplete="off" name="password" />
						</div>
						<div class="password" style="top:40px;">
							爱好:&nbsp;<input type="checkbox" name="hobby" value="sing" style="height: 11px;"/>唱歌<input type="checkbox" name="hobby" value="dance" style="height: 11px;"/>跳舞<input type="checkbox" name="hobby" value="football" style="height: 11px;"/>足球
						<div class="password" style="top:10px;">
							性别:&nbsp;<input type="radio" name="sex" value="man" style="height: 11px;"/>男<input type="radio" name="sex" value="woman" style="height: 11px;"/>女
						</div>
						<div class="denglu">
							<input type="submit" value="登录" />
						</div>
					</form>
				</div>
				<!--使用id-->
				<div id="register">
					<form action="/project201902/Register" method="post">
						<div class="user">
							用户:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="  请输入用户" autocomplete="off" name="username" />
						</div>
						<div class="password">
							密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="  请输入密码" autocomplete="off" name="password" />
						</div>
						<div class="password2">
							密码:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" placeholder="  请再次输入密码" autocomplete="off" name="password" />
						</div>
						
						<div class="denglu">
							<input type="submit" value="注册" />
						</div>
					</form>
				</div>
			</div>
		</div>
  </body>
</html>
