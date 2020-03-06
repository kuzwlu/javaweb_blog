<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<link rel="stylesheet" href="sweetalert/example/buttons.css">
	<link rel="stylesheet" href="fontawesome/css/font-awesome.min.css">
	<script src="sweetalert/lib/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="sweetalert/dist/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="sweetalert/dist/sweetalert2.min.css">
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
	#main-tab td{ font-size:12px; line-height:40px;}
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
	.main-for input.text-word{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; padding:0 10px;}
	.main-for select{ width:310px; height:36px; line-height:36px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#666;}
	.main-for input.text-but{ width:100px; height:40px; line-height:30px; border: 1px solid #cdcdcd; background:#e6e6e6; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; color:#969696; float:left; margin:0 10px 0 0; display:inline; cursor:pointer; font-size:14px; font-weight:bold;}
	.main-for textarea{ width:100%; height:150px; line-height:24px; border:#ebebeb 1px solid; background:#FFF; font-family:"Microsoft YaHei","Tahoma","Arial",'宋体'; padding:10px; color:#666}
	#addinfo a{ font-size:14px; font-weight:bold; background:url(images/main/replayblack.jpg) no-repeat 0 0px; padding:0px 0 0px 20px; line-height:45px;}
	#addinfo a:hover{ background:url(images/main/replayblue.jpg) no-repeat 0 0px;}
	</style>
  </head>
  
  <body>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：留言板&nbsp;&nbsp;>&nbsp;&nbsp;留言回复</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top" id="addinfo">
	    <a href="MessageServlet?type=page&page=1" target="mainFrame" onFocus="this.blur()" class="add">返回上一级</a>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <form method="post" action="MessageServlet?type=replay">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">评论id：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${requestScope.message.meid }</td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${requestScope.message.artitle }</td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">评论用户：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${requestScope.message.managername }</td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">评论时间：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${requestScope.message.metime }</td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">评论内容：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for" style="line-height:24px;">${requestScope.message.mecontent }</td>
	      </tr>
	      
	      <c:forEach items="${requestScope.messageplay}" var="m">
	      <tr >
	        <td align="right" valign="middle" class="borderright borderbottom bggray"></td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for" style="color: #565656;font-size: 20px;">
	      </tr>
	      <tr >
	        <td align="right" valign="middle" class="borderright borderbottom bggray" style="background-color: #dce5f0;">回复时间：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${m.mrtime }&nbsp;&nbsp;
	        <a target="mainFrame" onFocus="this.blur()" class="add" onclick="del('${m.mrid}','${m.meid }')">删除</a></td>
	      </tr>
	      <tr >
	        <td align="right" valign="middle" class="borderright borderbottom bggray" style="background-color: #dce5f0;">回复内容：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${m.mrcontent }</td>
	      </tr>
	      <tr >
	        <td align="right" valign="middle" class="borderright borderbottom bggray" style="background-color: #dce5f0;">回复用户：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">${m.managername }</td>
	      </tr>
	      </c:forEach>
	      
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">回复名称：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for"><input type="text" name="mruser" value="${sessionScope.managername }" readonly="readonly"  class="text-word"></td>
	      </tr>
	      
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">回复内容：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for"><textarea name="mrcontent" cols="" rows=""></textarea></td>
	        
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="hidden" name="meid" value="${requestScope.message.meid }" />
	        <input name="" type="submit" value="确定" class="text-but">
	        <input name="" type="reset" value="重置" class="text-but"></td>
	      </tr>
	    </table>
	    </form>
	    </td>
	    </tr>
	</table>
	<script type="text/javascript">
		function del(mrid,meid){
			swal({
				title: '确定删除吗',
				text: "您将无法恢复该数据",
				type: 'warning',
				showCancelButton: true,
				confirmButtonColor: '#3085d6',
				cancelButtonColor: '#d33',
				confirmButtonText: '删除',
				cancelButtonText: '取消',
				confirmButtonClass: 'btn btn-success',
				cancelButtonClass: 'btn btn-danger',
				buttonsStyling: false
				}).then(function(isConfirm) {
				if (isConfirm === true) {
					window.location.href='MessageServlet?type=the&meid='+meid+'&mrid='+mrid;
					swal(
					  '删除成功',
					  '',
					  'success'
					);
				} else if (isConfirm === false) {
					swal(
					  '已取消',
					  '',
					  'error'
					);
				} else {
				// Esc, close button or outside click
				// isConfirm is undefined
				}
				})
		}
     </script>
  </body>
</html>
