<%@ page language="java" import="java.util.*,java.text.SimpleDateFormat" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主要内容区main</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://pv.sohu.com/cityjson?ie=utf-8"></script>
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<link href="css/main.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="images/main/favicon.ico" />
	<script src="js/jquery-3.3.1.min.js"></script>
	<style>
	body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
	#main{ font-size:12px;}
	#main span.time{ font-size:14px; color:#528dc5; width:100%; padding-bottom:10px; float:left}
	#main div.top{ width:100%; background:url(images/main/main_r2_c2.jpg) no-repeat 0 10px; padding:0 0 0 15px; line-height:35px; float:left}
	#main div.sec{ width:100%; background:url(images/main/main_r2_c2.jpg) no-repeat 0 15px; padding:0 0 0 15px; line-height:35px; float:left}
	.left{ float:left}
	#main div a{ float:left}
	#main span.num{  font-size:30px; color:#538ec6; font-family:"Georgia","Tahoma","Arial";}
	.left{ float:left}
	div.main-tit{ font-size:14px; font-weight:bold; color:#4e4e4e; background:url(images/main/main_r4_c2.jpg) no-repeat 0 33px; width:100%; padding:30px 0 0 20px; float:left}
	div.main-con{ width:100%; float:left; padding:10px 0 0 20px; line-height:36px;}
	div.main-corpy{ font-size:14px; font-weight:bold; color:#4e4e4e; background:url(images/main/main_r6_c2.jpg) no-repeat 0 33px; width:100%; padding:30px 0 0 20px; float:left}
	div.main-order{ line-height:30px; padding:10px 0 0 0;}
	</style>
  </head>
  
  <body>
	<!--main_top-->
	
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="main">
	  <tr>
	    <td colspan="2" align="left" valign="top">
	    <span class="time"><strong>上午好！${sessionScope.username}</strong><u>[超级管理员]</u></span>
	    <div class="top"><span class="left">当前时间：<span id="times"></span></span></div>
	    <div class="top"><span class="left">您上次的登灵时间：${cookie.lastAccess.value} &nbsp;&nbsp; 登录IP：${cookie.ip.value}&nbsp;&nbsp;&nbsp;&nbsp;如非您本人操作，请及时</span><a href="ManagerServlet?type=page&page=${1}" target="mainFrame" onFocus="this.blur()" style="color: #528dc5;">&nbsp;更改密码</a></div>
	    <div class="sec">这是您第<span class="num">${cookie.number.value}</span>次登录！</div>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top" width="50%">
	    <div class="main-tit">网站信息</div>
	    <div class="main-con">
	    会员注册：开启<br/>
	会员投稿：开启<br/>
	管理员个数：<font color="#538ec6"><strong><span id="number"></span></strong></font> 人<br/>
	登陆者IP：<span id="ip"></span><br/>
	程序编码：UTF-8<br/>
	    </div>
	    </td>
	    <td align="left" valign="top" width="49%">
	    <div class="main-tit">服务器信息</div>
	    <div class="main-con">
	服务器软件：Apache/2.2.6 (Win32) PHP/5.2.5<br/>
	PHP版本：5.2.5<br/>
	MYSQL版本：5.0.45-community-nt<br/>
	魔术引用：开启 (建议开启)<br/>
	使用域名：192.168.1.124 <br/>
	    </div>
	    </td>
	  </tr>
	  <tr>
	    <td colspan="2" align="left" valign="top">
	    <div class="main-corpy">系统提示</div>
	    <div class="main-order">1=>如您在使用过程有发现出错请及时与我们取得联系；为保证您得到我们的后续服务，强烈建议您购买我们的正版系统或向我们定制系统！<br/>
	2=>强烈建议您将IE7以上版本或其他的浏览器</div>
	    </td>
	  </tr>
	</table>
	<script type="text/javascript">
	var times=document.getElementById("times");
	sz();
	ip();
	time();
	a();
	function a(){
			$.ajax({
				type:'post',
				url:"ManagerServlet?type=count",
				dataType:'json',
				success:function(data){
					//alert(data);
					//console.log(data);
					var number=data.length;
					$("#number").html(number);
				}
			})
		}
	
		function time(){
			var dd=new Date;
			var y=dd.getFullYear();
			var m=dd.getMonth()+1;
			var d=dd.getDate();
			var h=dd.getHours();
			var i=dd.getMinutes();
			var s=dd.getSeconds();
			var wkday = dd.getDay();
			times.innerHTML=y+"年"+m+"月"+d+"日"+"   "+h+":"+i+":"+s+" "+"星期"+"日一二三四五六".charAt(wkday);
		}
		function sz(){
			t=setInterval(function(){
			var dd=new Date;
			var y=dd.getFullYear();
			var m=dd.getMonth()+1;
			var d=dd.getDate();
			var h=dd.getHours();
			var i=dd.getMinutes();
			var s=dd.getSeconds();
			var wkday = dd.getDay();
			times.innerHTML=y+"年"+m+"月"+d+"日"+"   "+h+":"+i+":"+s+" "+"星期"+"日一二三四五六".charAt(wkday);
		},1000)
	}
		function ip(){
		var ip=document.getElementById("ip");
		var ips=returnCitySN["cip"];
		ip.innerHTML=ips;
	}
	</script>
  </body>
</html>
