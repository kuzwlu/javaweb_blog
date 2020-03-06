<%@ page language="java" import="java.util.*,java.net.URLEncoder,java.net.URLDecoder" contentType="text/html; charset=utf-8"%>
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
	<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="ueditor/ueditor.all.js"></script>
    <link rel =stylesheet href="ueditor/themes/default/css/ueditor.css">
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
	#imagepath{
	width: 150px;border-radius: 10px;border: 4px solid #d4d4d4;position: relative;top: 10px;
	}
	#qq{
		position: relative;left: 250px;
	}
	</style>
	
  </head>
  
  <body>
	<!--main_top-->
	<table width="99%" border="0" cellspacing="0" cellpadding="0" id="searchmain">
	  <tr>
	    <td width="99%" align="left" valign="top">您的位置：文章管理&nbsp;&nbsp;>&nbsp;&nbsp;编辑文章</td>
	  </tr>
	  <tr>
	    <td align="left" valign="top" id="addinfo">
	    <a href="ArticleServlet?arid=${requestScope.articlelist.arid }&type=updatejsp" target="mainFrame" onFocus="this.blur()" class="add">编辑文章</a>
	    </td>
	  </tr>
	  <tr>
	    <td align="left" valign="top">
	    <form method="post" action="ArticleServlet?type=update&arimage=${requestScope.imagepath}" enctype="multipart/form-data">
	    <input type="hidden" value="${requestScope.articlelist.arid }" name="arid">
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="main-tab">
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章栏目：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for" >
	        <select name="caid" >
	        <option value="${requestScope.articlelist.caid }" >&nbsp;&nbsp;${ requestScope.articlelist.caids}</option>
	        <c:forEach items="${requestScope.catalog}" var="c">
				<option value="${c.caid }" >&nbsp;&nbsp;${ c.caname}</option>
		   	</c:forEach>
	        </select>
	        </td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray" >文章序号：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="arnumber" value="${requestScope.articlelist.arnumber }" class="text-word" readonly="readonly">
	        </td>
	        </tr>
	       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章标题：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="artitle" value="${requestScope.articlelist.artitle }" class="text-word" ><span id="pspan"></span>
	        </td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">文章内容：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for"><script id="container" name="content"  type="text/plain" style="width:700px;height:350px;">	${requestScope.articlelist.arcontent}</script>
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">发布者：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="text" name="aruser" value="${requestScope.articlelist.aruser}" class="text-word" >
	        </td>
	        </tr>
	       <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">发布时间：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input type="datetime-local" name="artime" value="" id="times" class="text-word" style="height:22;line-height:none" step="1">
	        </td>
	        </tr>
	        <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">首页图片：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for" style="position: relative;">
	        <input type="file" name="arimages" style="top: 40%;position: absolute;" accept="image/gif,image/jpeg,image/jpg,image/png,image/svg"/>
	        <div id="qq"><span style="font-size: 14px;" id="theman">图片：</span><img src="${requestScope.imagepath}" id="imagepath">
	        <a href="ArticleServlet?type=down&filename=${requestScope.imagepaths}" ><span id="thewoman">下载这张图片</span></a></div>
	        </td>
	        </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">操作：</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <select name="arstate" >
	        <c:if test="${requestScope.articlelist.arstate eq '1'}">
		        	<option value="1" >&nbsp;&nbsp;显示</option>
		   		 	<option value="2" >&nbsp;&nbsp;隐藏</option>
	        </c:if>
		    <c:if test="${requestScope.articlelist.arstate eq '2'}">
		        	<option value="2" >&nbsp;&nbsp;隐藏</option>
		   		 	<option value="1" >&nbsp;&nbsp;显示</option>
	        </c:if>
	        </select>
	        </td>
	      </tr>
	      <tr onMouseOut="this.style.backgroundColor='#ffffff'" onMouseOver="this.style.backgroundColor='#edf5ff'">
	        <td align="right" valign="middle" class="borderright borderbottom bggray">&nbsp;</td>
	        <td align="left" valign="middle" class="borderright borderbottom main-for">
	        <input name="" type="submit" value="提交" class="text-but">
	        </tr>
	    </table>
	    </form>
	    </td>
	    </tr>
	</table>
	<c:set var="cp" value="<%=basePath%>" />
	<c:set var="path" value="<%=path%>" />
  </body>
 <script type="text/javascript">
 	sz();
	image();
	var ue = UE.getEditor('container',{ initialFrameWidth: null , autoHeightEnabled: false});
	
	ue.ready(function(){
		if(ue.getContent()==""){
			ue.setContent("");
		}
	});
		function sz(){
			var time=new Date;
			var yyyy=time.getFullYear();
			var MM=time.getMonth()+1;
			var dd=time.getDate();
			var hh=time.getHours();
			var mm=time.getMinutes();
			var ss=time.getSeconds();
			MM = checkTime(MM);
            dd = checkTime(dd);
            hh = checkTime(hh);
            mm = checkTime(mm);
            ss = checkTime(ss);
			var artimes=yyyy+"-"+MM+"-"+dd+"T"+hh+":"+mm+":"+ss;
			var artime=new Date(artimes);
			document.getElementById("times").value=artimes;
			function checkTime(i) {
                if (i < 10) {
                    i = "0" + i;
                }
                return i;
            }
		}
		
		
       function image(){
       var imagepath=document.getElementById("imagepath").src;
       var path="${cp}upload/err.png";
       		if(imagepath == path){
       			document.getElementById("theman").style.display="none";
       			document.getElementById("thewoman").style.display="none";
       			document.getElementById("imagepath").style.top="0px";
       		}
       }
    </script>
</html>
