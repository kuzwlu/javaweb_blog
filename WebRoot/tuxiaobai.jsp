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
    
    <title>花怜小屋</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="icon" href="images/favicon.ico" type="image/x-icon" />
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="css/base.css" rel="stylesheet">
	<link href="css/index.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/sinaFaceAndEffec.css" />
	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/kuzwlu.js"></script>
	<script type="text/javascript" src="js/sliders.js"></script>
	<link rel="stylesheet" href="sweetalert/example/buttons.css">
	<link rel="stylesheet" href="fontawesome/css/font-awesome.min.css">
	<script src="sweetalert/lib/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script src="sweetalert/dist/sweetalert2.min.js"></script>
	<link rel="stylesheet" href="sweetalert/dist/sweetalert2.min.css">

	
	<!--[if lt IE 9]>
	<script src="js/modernizr.js"></script>
	<![endif]-->
	<!-- 返回顶部调用 begin -->
	<style type="text/css">
		#message{
			font-size: 20px;
    		color: #48AE15;
    		border: 1px solid rgb(230, 230, 230);
		}
		#user{
			margin: 10px;
			font-size :15px;
			color :red;
		}
		.messagetext{
			background-color: #eaeaea7a;
			border-radius: 5px;
			color: #1567ae;
			 margin-left: 10px;
    		 margin-right: 10px;
   			 font-size: 16px;
   			 border: 1px solid #99999969;
   			 margin-bottom: 20px;
		}
		.messagetext>img{
			float: left;
    		width: 45px;
    		border-radius: 15%;
    		margin: 5px 5px 5px 5px;
		}	
		#mecontent{
			width: 608.5px;
			max-width: 608.5px;
		}
		.replaytext{
		border-radius: 5px;
			    margin: 5px 10px 0px 50px;
			    color: #ae1515;
   			 font-size: 16px;
   			 border: 1px solid #99999969;
		}
		.replaytext>img{
			float: left;
    		width: 45px;
    		border-radius: 15%;
    		margin: 5px 5px 5px 5px;
		}
		#articlenews{
			outline: rgb(230, 230, 230) solid 1px;
			font-size:15px;
		}
		#articlenews>span{
			margin-left: 15px;
		}
		#articlenews>span>a{
			font-size:13px;
			color: #03A9F4;
		}
		#artitle{
			margin: 5px 5px 5px 0px;
   			font-size: 25px;

		}
		#contenttext{
			margin:20px;
		}
		.messagetext p img {
    		width: 23px;
		}
		#pageone,#pagetwo,#pagethere{
			font-size: 18px;
    		color: #7ea5de;
		}
	</style>
  </head>
	<body>
	&nbsp;&nbsp;&nbsp;<script id="ilt" src="https://music.kuzwlu.xyz/player/js/player.js" key="e22ecb425e2e4017bed7b22ae731fb4d"></script>


	<header >
	  <div class="logo f_l"> <a href=""><img src="images/logo.png"></a> </div>
	  <nav id="topnav" class="f_r">
	    <ul>
	      <a href="")">首页</a> <a href="" >关于我</a> <a href="" >文章</a> <a href="" >心情</a> <a href="" >相册</a> <a href="background/index.jsp" >登录</a>
	    </ul>
	    <script src="js/nav.js"></script> 
	  </nav>
	</header>
	<article>
	
	  <div class="l_box f_l">
	    <div class="banner" id="banner">
	      <div id="slide-holder">
	        <div id="slide-runner"> <a href="/" target="_blank"><img id="slide-img-1" src="images/a1.jpg"  alt="" /></a> <a href="/" ><img id="slide-img-2" src="images/a2.jpg"  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-3" src="images/a3.jpg"  alt="" /></a> <a href="/" target="_blank"><img id="slide-img-4" src="images/a4.jpg"  alt="" /></a>
	          <div id="slide-controls">
	            <p id="slide-client" class="text"><strong></strong><span></span></p>
	            <p id="slide-desc" class="text"></p>
	            <p id="slide-nav"></p>
	          </div>
	        </div>
	      </div>
	      <script>
		  if(!window.slider) {
			var slider={};
		}
	
		slider.data= [
	    {
	        "id":"slide-img-1", // 与slide-runner中的img标签id对应
	        "client":"标题1",
	        "desc":"这里修改描述 这里修改描述 这里修改描述" //这里修改描述
	    },
	    {
	        "id":"slide-img-2",
	        "client":"标题2",
	        "desc":"add your description here"
	    },
	    {
	        "id":"slide-img-3",
	        "client":"标题3",
	        "desc":"add your description here"
	    },
	    {
	        "id":"slide-img-4",
	        "client":"标题4",
	        "desc":"add your description here"
	    } 
		];
	
		  </script> 
	    </div>
	   <script>
	   var arids ="";
	   		var nextpages=1;
	    	var nowpages=1;
	   var username="${sessionScope.username}";
	   var managername="${sessionScope.managername}";
		window.onload = function ()
		{
			
			var oLi = document.getElementById("tab").getElementsByTagName("li");
			var oUl = document.getElementById("ms-main").getElementsByTagName("div");
			
			for(var i = 0; i < oLi.length; i++)
			{
				oLi[i].index = i;
				oLi[i].onmouseover = function ()
				{
					for(var n = 0; n < oLi.length; n++) oLi[n].className="";
					this.className = "cur";
					for(var n = 0; n < oUl.length; n++) oUl[n].style.display = "none";
					oUl[this.index].style.display = "block"
				}	
			}
		}

	
	function getimage(arid) {
		arids=arid;
				$.ajax({
					url: "IndexServlet?type=ajaxhome",
					data: {arid:arid},
					type: "POST",
					dataType: 'html',
					//contentType : "application/x-www-form-urlencoded;charset=utf-8",
					async:false, 
					success:function(data) {
						click();
						$("#topnews").empty();
						$("#topnews").append(data);
						$("#topnews").css({"outline": "1px solid #e6e6e6"});
						
						var t = $("#topnav").offset().top;// 获取需要跳转到标签的top值
						$(window).scrollTop(t);
						//console.log(data);
						$("#tit01").remove();
						$("#gzwm").remove();
						$("#ad").remove();
						$("#ad300x100").remove();
						$("#banner").remove();
						$("#thepage").remove();
					}
				});
	}
	
	function postmessage() {
		//mecontents=$('#themessage').serializeArray();
		//var arid = mecontents[0].value;
		//var mecontent=mecontents[1].value;
		//mecontent=AnalyticEmotion(mecontent);
		//console.log(mecontent);
		if(username==''){
			if(managername==''){
				swal({
				  title: '用户未登录',
				  text: '您必须要登录才能发表评论',
				  type: 'error',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  cancelButtonText: '取消',
				  confirmButtonText: '登录'
				}).then(function(isConfirm) {
				  if (isConfirm) {
					//swal('正在登录', '', 'success');
					window.location.href='background/login.jsp';
				  }
				});
			}else{
			    $.ajax({  
			            type: "POST",   //提交的方法
			            url:"MessageServlet?type=ajax", //提交的地址  
			            data:$('#themessage').serialize(),// 序列化表单值  
			            async: true,  
			            dataType: 'html',
			            //dataType:'json',	
			            error: function(request) { //失败的话
			                 swal('添加失败'); 
			            },  
			            success: function(data) { 
			            if(data=="输入为空"){
			            	swal('输入为空，请重新输入');
			            }else if(data=="系统出错"){
			            	swal('提交失败，系统出错');
			            }else{
			            	swal({
								  title: '评论成功',
								  text: '2秒后关闭',
								  timer: 2000
							});
			            	$("#messagecontent").append(data);
			                //console.log(data);  //就将返回的数据显示出来
			                $("#mecontent").val("");
			            }
			            }  
			         });
	         	}
			}else{
				$.ajax({  
			            type: "POST",   //提交的方法
			            url:"MessageServlet?type=ajax", //提交的地址  
			            data:$('#themessage').serialize(),// 序列化表单值  
			            async: true,  
			            dataType: 'html',
			            //dataType:'json',	
			            error: function(request) { //失败的话
			                 swal('添加失败'); 
			            },  
			            success: function(data) { 
			            if(data=="输入为空"){
			            	swal('输入为空，请重新输入');
			            }else if(data=="系统出错"){
			            	swal('提交失败，系统出错');
			            }else{
			            	swal({
								  title: '评论成功',
								  text: '2秒后关闭',
								  timer: 2000
							});
			            	$("#messagecontent").append(data);
			                //console.log(data);  //就将返回的数据显示出来
			                $("#mecontent").val("");
			            }
			            }  
			         });
			}
		}
	  function postreplay(arid) {
	  	if(username==''){
			if(managername==''){
				swal({
				  title: '用户未登录',
				  text: '您必须要登录才能发表评论',
				  type: 'error',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  cancelButtonText: '取消',
				  confirmButtonText: '登录'
				}).then(function(isConfirm) {
				  if (isConfirm) {
					//swal('正在登录', '', 'success');
					window.location.href='background/login.jsp';
				  }
				});
			}else{
			    $.ajax({  
			            type: "POST",   //提交的方法
			            url:"MessageServlet?type=text&arid="+arid, //提交的地址  
			            data:$('#themessage').serialize(),// 序列化表单值  
			            async: true,  
			            dataType: 'html',
			            //dataType:'json',	
			            error: function(request) { //失败的话
			                 swal('添加失败'); 
			            },  
			            success: function(data) { 
			            if(data=="输入为空"){
			            	swal('输入为空，请重新输入');
			            }else if(data=="系统出错"){
			            	swal('提交失败，系统出错');
			            }else{
			            	swal({
								  title: '评论成功',
								  text: '2秒后关闭',
								  timer: 2000
							});
			            	getimage(arid);
			            }
			            }  
			         });
	         	}
			}else{
				$.ajax({  
			            type: "POST",   //提交的方法
			            url:"MessageServlet?type=text&arid="+arid, //提交的地址  
			            data:$('#themessage').serialize(),// 序列化表单值  
			            async: true,  
			            dataType: 'html',
			            //dataType:'json',	
			            error: function(request) { //失败的话
			                 swal('添加失败'); 
			            },  
			            success: function(data) { 
			            if(data=="输入为空"){
			            	swal('输入为空，请重新输入');
			            }else if(data=="系统出错"){
			            	swal('提交失败，系统出错');
			            }else{
			            	swal({
								  title: '评论成功',
								  text: '2秒后关闭',
								  timer: 2000
							});
			            	getimage(arid);
			            }
			            }  
			         });
			}
	       }
	    function replay(meid, content ,image , managername ,metime){
	    	//console.log(meid);
	    	//console.log(content);
	    	//console.log(image);
	    	//console.log(managername);
	    	//console.log(metime);
	    	
	    	$("#thecontent").html("<p>正在回复：<a id='change' onclick='change()'style='margin-right: 10px;float: right;color: green;'>我要评论</a></p>"+
	    	"<div class='messagetext'><img src='"+image+"'><p style='margin-left: 50px;'>"+managername+"</p>" +
					"<p style='margin-left: 50px;'>"+metime+"</p><p style='margin-top: 5px;border-top: 1px solid #9e9e9e;" +
							"margin-left: 55px;'>"+content+"</p></div><br>"+
							"<input type='hidden' name='meid' value='"+meid+"'/>"+
							"请输入回复内容：");
			$("#messagebiu").css('display', 'none');
			$("#replaybiu").css('display', 'block');
	    	
	    }
	    function click(){
	    	//console.log(arids);
	    	$.ajax({  
		            type: "POST",   //提交的方法
		            url:"IndexServlet?type=click&arid="+arids, //提交的地址  
		            data:{},// 序列化表单值  
		            //async: true,  
		            //dataType: 'html',
		            //dataType:'json',	 
		         });
	    }
	    var loadpages=2;
	    function nextpage(maxpage){
	    	var maxpages=maxpage;
	    	$.ajax({
					url: "IndexServlet?type=nextpage",
					data: {"nextpage":loadpages,"maxpage":maxpages},
					type: "POST",
					dataType: 'html',
					success:function(data) {
						if(loadpages>maxpages){
							$("#thepage").remove();
							swal({
							  title: '已经是最后一页了',
							  text: '2秒后关闭',
							  timer: 2000
							});
						}else{
							$("#topnews").append(data);
							loadpages=loadpages+1;
							$("#pageone").remove();
							$("#pagetwo").remove();
						}
					}
				});
	    }
	    
	    function home(maxpage,nowpage){
	    	nowpages=nowpage;
	    	var maxpages=maxpage;
	    	$.ajax({
					url: "IndexServlet?type=nextpage",
					data: {"nextpage":1,"maxpage":maxpages},
					type: "POST",
					dataType: 'html',
					success:function(data) {
						if(nowpages>1){
							swal({
							  title: '已经是第一页了',
							  text: '2秒后关闭',
							  timer: 2000
							});
						}else{
							nextpages=1;
							$("#remove").remove();
							$("#topnews").append(data);
							$("#pagethere").remove();
							$("#pageone").text("1/"+maxpage+"页");
							var t = $("#topnav").offset().top;
							$(window).scrollTop(t);
						}
					}
				});
	    }
	    function up(maxpage,nowpage){
	    	var maxpages=maxpage;
	    	if(nextpages <= 1){
				swal({
					title: '已经是第一页了！',
					text: '2秒后关闭',
					timer: 2000
				});
			}else{
				
				console.log("thenextpages="+nextpages);
				console.log("thenowpages="+nowpages);
		    	//nextpages=nextpages-1;
		    	nextpages--;
		    	nowpages--;
		    	$.ajax({
					url: "IndexServlet?type=nextpage",
					data: {"nextpage":nextpages,"maxpage":maxpages},
					type: "POST",
					dataType: 'html',
					success:function(data) {
							console.log(nextpages);
							console.log(nowpages);
							$("#remove").remove();
							$("#topnews").append(data);
							$("#pagethere").remove();
							$("#pageone").text(nowpages+"/"+maxpage+"页");
							var t = $("#topnav").offset().top;
							$(window).scrollTop(t);
						
					}
				});
				}
	    }
	    
	    function next(maxpage){
	    var maxpages=maxpage;
	    	if(nextpages>=maxpages){
				//nextpages=2;
				//nowpages=1;
				swal({
					title: '已经是最后一页了！！',
					text: '2秒后关闭',
					timer: 2000
				});
			}else{
	    	
	    	console.log("thenextpages="+nextpages);
			console.log("thenowpages="+nowpages);
			nextpages++;
		    nowpages++;
		    console.log(nextpages);
			console.log(nowpages);
	    	$.ajax({
					url: "IndexServlet?type=nextpage",
					data: {"nextpage":nextpages,"maxpage":maxpages},
					type: "POST",
					dataType: 'html',
					success:function(data) {
							//nextpages++;
							
							$("#remove").remove();
							$("#topnews").append(data);
							$("#pagethere").remove();
							$("#pageone").text(nowpages+"/"+maxpage+"页");
							var t = $("#topnav").offset().top;
						$(window).scrollTop(t);
						}
				});
			}
	    }
	    function end(maxpage,nowpage){
	    	var maxpages=maxpage;
	    	//nowpages++;
	    	$.ajax({
					url: "IndexServlet?type=nextpage",
					data: {"nextpage":maxpages,"maxpage":maxpages},
					type: "POST",
					dataType: 'html',
					success:function(data) {
						nextpages=maxpages;
						nowpages=maxpages;
						$("#remove").remove();
						$("#topnews").append(data);
						$("#pagethere").remove();
						$("#pageone").text(maxpage+"/"+maxpage+"页");
						var t = $("#topnav").offset().top;
						$(window).scrollTop(t);
						
					}
				});
	    }
	    function change(){
	    	$("#thecontent").html("请输入评论内容：");
	    	$("#replaybiu").css('display', 'none');
			$("#messagebiu").css('display', 'block');
	    }
	    function move(a){
  			$("#"+a).css('visibility', 'visible');
	    }
	    function moveout(a){
	    	$("#"+a).css('visibility', 'hidden');
	    }
	</script>
	    <!-- banner代码 结束 -->
	    <div class="topnews" id="topnews" style="box-shadow: 1px 1px 2px #eee;">
	    
	      <h2><span><a href="" target="_blank" >${requestScope.catalogs[0].caname }</a>
	      <a href="" target="_blank">${requestScope.catalogs[1].caname }</a>
	      <a href="" target="_blank">${requestScope.catalogs[2].caname }</a>
	      </span><b>文章</b>推荐</h2>
	      <div id="remove">
	      <div class="blogs" >
	      
	        <figure><img src="${requestScope.articles[0].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[0].arid }')" >${requestScope.articles[0].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[0].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[0].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[0].artime }</span>
	          <span class="viewnum f_r">浏览（<a href="">${requestScope.articles[0].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[0].arid }')" >${requestScope.articles[0].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[1].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[1].arid }')">${requestScope.articles[1].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[1].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[1].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[1].artime }</span>
	          <span class="viewnum f_r">浏览（<a href="">${requestScope.articles[1].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[1].arid }')" >${requestScope.articles[1].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[2].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[2].arid }')">${requestScope.articles[2].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[2].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[2].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[2].artime }</span>
	          <span class="viewnum f_r">浏览（<a href="">${requestScope.articles[2].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[2].arid }')" >${requestScope.articles[2].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[3].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[3].arid }')">${requestScope.articles[3].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[3].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[3].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[3].artime }</span>
	          <span class="viewnum f_r">浏览（<a >${requestScope.articles[3].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[3].arid }')" >${requestScope.articles[3].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[4].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[4].arid }')">${requestScope.articles[4].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[4].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[4].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[4].artime }</span>
	          <span class="viewnum f_r">浏览（<a>${requestScope.articles[4].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[4].arid }')" >${requestScope.articles[4].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[5].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[5].arid }')">${requestScope.articles[5].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[5].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[5].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[5].artime }</span>
	          <span class="viewnum f_r">浏览（<a href="">${requestScope.articles[5].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[5].arid }')" >${requestScope.articles[5].message }</a>）</span></p>
	        </ul>
	      </div>
	      <div class="blogs">
	        <figure><img src="${requestScope.articles[6].arimage }" onerror="whenError(this)"></figure>
	        <ul>
	          <h3><a onclick="getimage('${requestScope.articles[6].arid }')">${requestScope.articles[6].artitle }</a></h3>
	          <p id="blogsp">${requestScope.articles[6].arcontent }...</p>
	          <p class="autor"><span class="lm f_l">
	          <a href="">
	          	<c:forEach items="${requestScope.catalogs}" var="catalog">
	          		<c:if test="${requestScope.articles[6].caid eq catalog.caid}">
	          			${catalog.caname }
	          		</c:if>
	          	</c:forEach>
	          </a></span>
	          <span class="dtime f_l">${requestScope.articles[6].artime }</span>
	          <span class="viewnum f_r">浏览（<a href="">${requestScope.articles[6].click }</a>）</span>
	          <span class="pingl f_r">评论（<a onclick="getimage('${requestScope.articles[6].arid }')" >${requestScope.articles[6].message }</a>）</span></p>
	        </ul>
	      </div>
	      </div>
		</div>
	  <span id="thepage" >
	  	<span align="left" valign="top" class="fenye" style="text-align: center;">
	  	<p id="pagethere">${requestScope.nextpage }</p>
	  	<p id="pageone">${requestScope.nowpage }/${requestScope.maxpage } 页</p>
	  	<p id="pagetwo" style="margin: 20px 20px 40px 20px;">
	  	<a onclick="home('${requestScope.maxpage }','1')" onFocus="this.blur()">首页</a>&nbsp;&nbsp;
	  	<a onclick="up('${requestScope.maxpage }','${requestScope.nowpage }')" onFocus="this.blur()">上一页</a>&nbsp;&nbsp;
		<a onclick="next('${requestScope.maxpage }')" onFocus="this.blur()" >下一页</a>&nbsp;&nbsp;
	  	<a onclick="end('${requestScope.maxpage }','${requestScope.nowpage }')" onFocus="this.blur()">尾页</a>&nbsp;&nbsp;
	  	</p>
	  	</span>
	  </span>
	   </div>
	    
	  <div class="r_box f_r">
	    <div class="tit01" id="tit01">
	      <h3>关注我</h3>
	      <div class="gzwm" id="gzwm">
	        <ul>
	          <li><a class="xlwb" href="#" target="_blank">新浪微博</a></li>
	          <li><a class="txwb" href="#" target="_blank">腾讯微博</a></li>
	          <li><a class="rss" href="portal.php?mod=rss" target="_blank">RSS</a></li>
	          <li><a class="wx" href="mailto:admin@admin.com">邮箱</a></li>
	        </ul>
	      </div>
	    </div>
	    <!--tit01 end-->
	    <div class="ad300x100" id="ad300x100"> <img src="images/ad300x100.jpg"> </div>
	    <div class="moreSelect" id="lp_right_select"> 
	
	      <div class="ms-top">
	        <ul class="hd" id="tab">
	          <li class="cur"><a href="/">点击排行</a></li>
	          <li><a href="/">最新文章</a></li>
	          <li><a href="/">站长推荐</a></li>
	        </ul>
	      </div>
	      <div class="ms-main" id="ms-main">
	        <div style="display: block;" class="bd bd-news" >
	          <ul>
	            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
	            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
	            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
	            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
	            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
	            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
	          </ul>
	        </div>
	        <div  class="bd bd-news">
	          <ul>
	            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
	            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
	            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
	            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
	            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
	            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
	          </ul>
	        </div>
	        <div class="bd bd-news">
	          <ul>
	            <li><a href="/" target="_blank">手机的16个惊人小秘密，据说99.999%的人都不知</a></li>
	            <li><a href="/" target="_blank">你面对的是生活而不是手机</a></li>
	            <li><a href="/" target="_blank">住在手机里的朋友</a></li>
	            <li><a href="/" target="_blank">豪雅手机正式发布! 在法国全手工打造的奢侈品</a></li>
	            <li><a href="/" target="_blank">教你怎样用欠费手机拨打电话</a></li>
	            <li><a href="/" target="_blank">原来以为，一个人的勇敢是，删掉他的手机号码...</a></li>
	          </ul>
	        </div>
	      </div>
	      <!--ms-main end --> 
	    </div>
	    <!--切换卡 moreSelect end -->
	    
	    <div class="cloud">
	      <h3>标签云</h3>
	      <ul>
	        <li><a href="/">个人博客</a></li>
	        <li><a href="/">web开发</a></li>
	        <li><a href="/">前端设计</a></li>
	        <li><a href="/">Html</a></li>
	        <li><a href="/">CSS3</a></li>
	        <li><a href="/">Html5+css3</a></li>
	        <li><a href="/">百度</a></li>
	        <li><a href="/">Javasript</a></li>
	        <li><a href="/">web开发</a></li>
	        <li><a href="/">前端设计</a></li>
	        <li><a href="/">Html</a></li>
	        <li><a href="/">CSS3</a></li>
	        <li><a href="/">Html5+css3</a></li>
	        <li><a href="/">百度</a></li>
	      </ul>
	    </div>
	    <div class="tuwen">
	      <h3>图文推荐</h3>
	      <ul>
	        <li><a href="/"><img src="images/01.jpg"><b>住在手机里的朋友</b></a>
	          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
	        </li>
	        <li><a href="/"><img src="images/02.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
	          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
	        </li>
	        <li><a href="/" title="手机的16个惊人小秘密，据说99.999%的人都不知"><img src="images/03.jpg"><b>手机的16个惊人小秘密，据说...</b></a>
	          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
	        </li>
	        <li><a href="/"><img src="images/06.jpg"><b>住在手机里的朋友</b></a>
	          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
	        </li>
	        <li><a href="/"><img src="images/04.jpg"><b>教你怎样用欠费手机拨打电话</b></a>
	          <p><span class="tulanmu"><a href="/">手机配件</a></span><span class="tutime">2015-02-15</span></p>
	        </li>
	      </ul>
	    </div>
	    <div class="ad" id="ad"> <img src="images/03.jpg"> </div>
	    <div class="links">
	      <h3><span>[<a href="/">申请友情链接</a>]</span>友情链接</h3>
	      <ul>
	        <li><a href="/">杨青个人博客</a></li>
	        <li><a href="/">web开发</a></li>
	        <li><a href="/">前端设计</a></li>
	        <li><a href="/">Html</a></li>
	        <li><a href="/">CSS3</a></li>
	        <li><a href="/">Html5+css3</a></li>
	        <li><a href="/">百度</a></li>
	      </ul>
	    </div>
	  </div>
	  <!--r_box end --> 
	</article>
	<footer>
	  <p class="ft-copyright">花怜小屋 Design by DanceSmile 蜀ICP备11002373号-1</p>
	  <div id="tbox"> 
	  <a id="togbook" href="#message"></a> 
	  <a id="gotop" href="#topnav"></a> </div>
	</footer>
	</body>
</html>
