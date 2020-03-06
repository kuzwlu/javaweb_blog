<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>灞曞紑鍚堥棴鎸夐挳</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/css.css" type="text/css" rel="stylesheet" />
	<meta content="text/html; charset=utf-8" http-equiv="Content-Type"/>
	<script language="javascript">
	function switchSysBar(){
	 if (parent.document.getElementById('attachucp').cols=="194,12,*"){
	 document.getElementById('leftbar').style.display="";
	 parent.document.getElementById('attachucp').cols="0,12,*";
	 }
	 else{
	 parent.document.getElementById('attachucp').cols="194,12,*";
	 document.getElementById('leftbar').style.display="none"
	 }
	}
	function load(){
	 if (parent.document.getElementById('attachucp').cols=="0,12,*"){
	 document.getElementById('leftbar').style.display="";
	 }
	}
	</script>
  </head>
  
	<body marginwidth="0" marginheight="0" onLoad="load()" topmargin="0" leftmargin="0" onselectstart="return false" oncontextmenu=return(false) style="overflow-x:hidden;">
		<center>
			<table height="100%" cellspacing="0" cellpadding="0" border="0" width="100%">
				<tbody>
					<tr>
						<td bgcolor="#ededb1" width="1">
						</td>
						<td id="leftbar" style="display: none; background:url(images/main/switchbg.jpg) repeat-y #d2d2d0 0px 0">
						<a onClick="switchSysBar()" href="javascript:void(0);">
						<img src="images/main/pic24.jpg" width="12" height="72" border="0" alt="闅愯棌宸︿晶鑿滃崟" >
						</a>
						</td>
						<td id="rightbar"style="background:url(images/main/switchbg.jpg) repeat-y #f2f0f5 0px 0">
						<a onClick="switchSysBar()" href="javascript:void(0);">
						<img src="images/main/pic23.jpg" width="12" height="72" border="0" alt="闅愯棌宸︿晶鑿滃崟" >
						</a>
						</td>
					</tr>
				</tbody>
			</table>
		</center>
	</body>
</html>
