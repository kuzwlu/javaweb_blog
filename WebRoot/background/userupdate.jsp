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
	#search{ font-size:12px; background:#548fc9; margin:10px 10px 0 0; display:inline; width:100%; color:#FFF}
	#search form span{height:40px; line-height:40px; padding:0 0px 0 10px; float:left;}
	#search form input.text-word{height:24px; line-height:24px; width:180px; margin:8px 0 6px 0; padding:0 0px 0 10px; float:left; border:1px solid #FFF;}
	#search form input.text-but{height:24px; line-height:24px; width:55px; background:url(images/main/list_input.jpg) no-repeat left top; border:none; cursor:pointer; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666; float:left; margin:8px 0 0 6px; display:inline;}
	#search a.add{ background:url(images/main/add.jpg) no-repeat 0px 6px; padding:0 10px 0 26px; height:40px; line-height:40px; font-size:14px; font-weight:bold; color:#FFF}
	#search a:hover.add{ text-decoration:underline; color:#d2e9ff;}
	#main-tab{ border:1px solid #eaeaea; background:#FFF; font-size:12px;}
	#main-tab th{ font-size:12px; background:url(images/main/list_bg.jpg) repeat-x; height:32px; line-height:32px;}
	#main-tab td{ font-size:12px; line-height:20px;}
	#main-tab td a{ font-size:12px; color:#548fc9;}
	#main-tab td a:hover{color:#565656; text-decoration:underline;}
	.bordertop{ border-top:1px solid #ebebeb}
	.borderright{ border-right:1px solid #ebebeb}
	.borderbottom{ border-bottom:1px solid #ebebeb}
	.borderleft{ border-left:1px solid #ebebeb}
	.gray{ color:#dbdbdb;}
	td.fenye{ padding:10px 0 0 0; text-align:right;}
	.bggray{ background:#f9f9f9; font-size:14px; font-weight:bold; padding:10px 10px 10px 0; width:120px;}
	.main-for{ padding:10px;}
	.main-for input.text-word{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; padding:0 10px; }
	.main-for select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666;}
	.main-for input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;}
	#addinfo a{ font-size:14px; font-weight:bold; background:url(images/main/addinfoblack.jpg) no-repeat 0 1px; padding:0px 0 0px 20px; line-height:45px;}
	#addinfo a:hover{ background:url(images/main/addinfoblue.jpg) no-repeat 0 1px;}
	#yes{
	width: 8px;
    height: 16px;
    border-color: rgba(117,200,43,1);
    border-style: solid;
    border-width: 0 2px 2px 0;
    transform: rotate(45deg);
    position: absolute;
    margin-left: -30px;
    margin-top: 5px;
	}
	</style>
  </head>
  
  <body>
  
  <script type="text/javascript">
  		//pwd判断是否为空   onblur事件
  		function a2(){		
			if(!$("#pwdId").val()){
				$("#pspan").html("");
			}
		}
		//oncahnge事件 判断pwd长度是否有变，设置pspan为空
		function a3(){
		var pwdid=$("#pwdId").val();
		var pwd2id=$("#pwd2Id").val();
			if(pwdid.length<pwd2id.length || pwdid.length>pwd2id.length){
				$("#pspan").html("");
			}
		}
		//onblur事件 ajax 后端判断密码是否已知，显示内容
		function a1(){
			$.ajax({
				type:'post',
				url:"ManagerAjax?type=pwd",
				data:{"pwd":$("#pwdId").val(),"pwd2":$("#pwd2Id").val()},
				success:function(data){
					$("#pspan").html = $('#pspan').html(data);
				}
			})
		}
  </script>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：用户管理&nbsp;&nbsp;>&nbsp;&nbsp;修改用户</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top" id="addinfo">
	    <a href="UserServlet?id=${requestScope.userall.userid}&type=update" target="mainFrame" onFocus="this.blur()" class="add">修改用户</a>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <form method="post" action="UserServlet">
	    <input type="hidden" value="rec" name="type">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	    <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'" hidden="hidden">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">id：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${requestScope.userall.userid }</td>
	        </tr>
	    	<tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">管理员名：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
			<input type="text" name="name" value="${requestScope.userall.username }" class="text-word" disabled="disabled">
			</td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">新的密码：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="mpwd" value="" class="text-word" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/ig,'')" id="pwdId" onblur="a2()" onchange="a3()">
	        </td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">确认密码：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="mpwd2" value="" class="text-word" onkeyup="value=value.replace(/[\u4e00-\u9fa5]/ig,'')"id="pwd2Id" onblur="a1()"><span id="pspan"></span>
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">用户状态：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <select name="mstates" id="level">
	        	<c:if test="${requestScope.userall.userstate eq '1'}">
		        	<option value="1" selected="selected">&nbsp;&nbsp;启用</option>
		        	<option value="2" >&nbsp;&nbsp;未启用</option>
	        	</c:if>
		    	<c:if test="${requestScope.userall.userstate eq '2'}">
		    	<option value="1" >&nbsp;&nbsp;启用</option>
	        	<option value="2" selected="selected">&nbsp;&nbsp;未启用</option>
		    	</c:if>
	        </select>
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input name="id" value="${requestScope.userall.userid }" type="hidden">
	        <input name="" type="submit" value="提交" class="text-but">
	        <input name="" type="reset" value="重置" class="text-but"></td>
	        </tr>
	    </table>
	    </form>
	    </td>
	    </tr>
	</table>
  </body>
</html>
