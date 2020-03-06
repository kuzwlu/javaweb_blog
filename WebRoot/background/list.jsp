<%@ page language="java" import="java.util.*,java.sql.*,com.xxgc.model.*,com.xxgc.servlet.*,com.xxgc.utils.*,com.xxgc.dao.*" contentType="text/html; charset=utf-8"%>
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
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<link href="css/main.css" type="text/css" rel="stylesheet" />
	<link rel="shortcut icon" href="images/main/favicon.ico" />
	<script src="js/jquery-3.3.1.min.js"></script>
	<style>
	body{overflow-x:hidden; background:#f2f0f5; padding:15px 0px 10px 5px;}
	#searchmain{ font-size:12px;}
	#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF; float:left}
	#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
	#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
	#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
	#search a.add{ background:url(images/main/add.jpg) no-repeat -3px 7px #548fc9; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF; float:right}
	#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
	#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
	#main-tab th{ font-size:12px; background:url(images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;width: 50px;}
	#main-tab td{ font-size:12px; line-height:40px;width: 50px;}
	#main-tab td a{ font-size:12px; color:#548fc9;}
	#main-tab td a:hover{color:#565656; text-decoration:underline;}
	.bordertop{ border-top:1px solid #ebebeb}
	.borderright{ border-right:1px solid #ebebeb}
	.borderbottom{ border-bottom:1px solid #ebebeb}
	.borderleft{ border-left:1px solid #ebebeb}
	.gray{ color:#dbdbdb;}
	td.fenye{ padding:10px 0 0 0; text-align:right;}
	.bggray{ background:#f9f9f9}
	
	</style>
  </head>
  
  <body>
  	<script>
	  	$(function(){
	  		list.loadlist(list.pageIndex);
	  	})
  		var list={
  			pageIndex:1
  		};
	  list.loadlist=function managerlist(pageIndex){
	  	$.ajax({
				type:'post',
				url:"ManagerList",
				data:{"page":pageIndex},
				dataType:'json',			
				success:function(data){
					var html="";
					var pagesize=data.length;
					console.log(data);
					for(var i=0;i<data.length;i++){	
						if(data[i].matate=="1"){
							data[i].matate="启用";
						}else if(data[i].matate=="2"){
							data[i].matate="未启用";
						}				
						html +="<tr >"+
						"<td align='center' valign='middle' class='borderright borderbottom'>"+data[i].managerid+"</td>"+
						"<td align='center' valign='middle' class='borderright borderbottom'>"+data[i].managername+"</td>"+
						"<td align='center' valign='middle' class='borderright borderbottom'>"+data[i].passwd+"</td>"+
						"<td align='center' valign='middle' class='borderright borderbottom'>"+data[i].matate+"</td>"+
						"<td align='center' valign='middle' class='borderright borderbottom'>"+
						"<a href='' target='mainFrame' onFocus='this.blur()' class='add'>编辑</a>"+
						"<span class='gray'>&nbsp;|&nbsp;</span>"+
						"<a href='' target='mainFrame' onFocus='this.blur()' class='add'>删除</a>"+
						"</td>"+"</tr>"
						//
					}
					$(".managerlist").html(html);
					$("#pagesize").html("共有"+data.length+"条数据");
					list.pagelist(pagesize,pageIndex);
				}
  		})
	  }
  	list.pagelist=function managerpage(pagesize,pageIndex){
  		//console.log(pagesize,pageIndex);
  		$("#firstpage").click(function (){
  			list.pageIndex=1;
  			//console.log(pageIndex);
  			list.loadlist(list.pageIndex);
  		})
  		$("#pageup").click(function(){
  			list.pageIndex=list.pageIndex-1;
  			list.loadlist(list.pageIndex);
  			//console.log(pageIndex);
  		})
  		$("#pagedown").click(function(){
  			list.pageIndex=list.pageIndex+1;
  			list.loadlist(list.pageIndex);
  			console.log(pageIndex);
  		})
  		$("#pagelast").click(function(){
  			list.pageIndex=1;
  			list.loadlist(list.pageIndex);
  			//console.log(pageIndex);
  		})
  	}
  	</script>
	<!--main_top-->
	
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：用户管理&nbsp;&nbsp;>&nbsp;&nbsp;管理员列表</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="search">
	  		<tr>
	   		 <td width="90%" align="left" valign="middle">
		         <form method="post" action="ManagerFindNameServlet">
		         <span>管理员：</span>
		         <input type="text" name="managername" placeholder="请输入用户名" class="text-word">
		         <input name="" type="submit" value="查询" class="text-but">
		         </form>
	         </td>
	  		  <td width="10%" align="center" valign="middle" style="text-align:right; width:150px;"><a href="background/main_info.jsp" target="mainFrame" onFocus="this.blur()" class="add">新增管理员</a></td>
	  		</tr>
		</table>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab" >
	    <tr>
	        <th align="center" valign="middle" class="borderright">编号</th>
	        <th align="center" valign="middle" class="borderright">帐号</th>
	        <th align="center" valign="middle" class="borderright">密码</th>
	        <th align="center" valign="middle" class="borderright">用户状态</th>
	        <th align="center" valign="middle">操作</th>
	      </tr>
	      </table>
	      <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab" class="managerlist" >

	    	</table>
	    </td>
	    </tr>
	  <tr>
	    <td align="left" valign="top" class="fenye"><span id="pagesize"></span>&nbsp;&nbsp;<a href="javascript:void(0)" id="firstpage">首页</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="pageup">上一页</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="pagedown">下一页</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="pagelast">尾页</a></td>
	  </tr>
	  
	</table>
  </body>
</html>
