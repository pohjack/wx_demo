﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
		<style>
		body.main{
			overflow: hidden;
		}
		.page-content-wrapper .page-content{
			padding: 25px 0 10px;
		}
		.page-content-white .page-bar{
			margin: -25px 0 0;
		}
		#tempEdit .modal-dialog{
			width:1024px;
		}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed main">
		<!-- BEGIN HEADER -->
		<input id="status" value="" hidden="hidden"><!-- 子模板是否修改标识 -->
		<div class="page-header navbar navbar-fixed-top">
			<!-- BEGIN HEADER INNER -->
			<div class="page-header-inner ">
				<!-- BEGIN LOGO -->
				<div class="page-logo">
					<a href="index.html">
						<img src="${pageContext.request.contextPath}/statics/images/logo.png" alt="logo" class="logo-default" /> </a>
					<div class="menu-toggler sidebar-toggler">
						<span></span>
					</div>
				</div>
				<!-- END LOGO -->
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse">
					<span></span>
				</a>
				<!-- END RESPONSIVE MENU TOGGLER -->
				<!-- BEGIN TOP NAVIGATION MENU -->
				<div class="top-menu">
					<ul class="nav navbar-nav pull-right">
					
						
						<!-- BEGIN USER LOGIN DROPDOWN -->
						<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
					<li class="dropdown dropdown-user">
						<a href="javascript:;"
							class="dropdown-toggle" data-toggle="dropdown"
							data-hover="dropdown" data-close-others="true">
							<span class="username username-hide-on-mobile">
							欢迎您，${username} </span> <i class="fa fa-angle-down"></i>
						</a>

						<ul role="menu" class="dropdown-menu dropdown-menu-right">
							<li>
                               <a href="#" onclick="toEdit('${id}')" >
                               <i class="icon-user"></i>修改个人信息</a>
                            </li>
                            <li>
                               <a href="#" onclick="toPassword('${id}')" >
                               <i class="icon-lock"></i> 更改密码 </a>
                            </li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
						<!-- BEGIN QUICK SIDEBAR TOGGLER -->
						<!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
						<li class="dropdown dropdown-quick-sidebar-toggler">
							<a href="#" class="dropdown-toggle" 	onclick="confirmLayer('确定要退出本系统吗？',null,'loginOut')">
								 退出</a>
							</a>
						</li>
						<!-- END QUICK SIDEBAR TOGGLER -->
					</ul>
				</div>
				<!-- END TOP NAVIGATION MENU -->
			</div>
			<!-- END HEADER INNER -->
		</div>
		<!-- END HEADER -->
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN SIDEBAR -->
			<div class="page-sidebar-wrapper">
				<!-- BEGIN SIDEBAR -->
				<!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
				<!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
				<div class="page-sidebar navbar-collapse collapse">
					<!-- BEGIN SIDEBAR MENU -->
					<ul id="menu" class="page-sidebar-menu page-header-fixed" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="10">

						<c:forEach items="${menuList}" var="m">
						<li class="nav-item start">
							<a href="javascript:;" class="nav-link nav-toggle">
								<i class="${m.resIcon}"></i>
								<span class="title">${m.resName}</span>
								<span class="selected"></span>
								<span class="arrow"></span>
							</a>
							<c:if test="${m.hasSub}">
								<ul class="sub-menu">
							<c:forEach items="${m.subMenu}" var="mp">
								<li class="nav-item start index">
									<a href="${pageContext.request.contextPath}${mp.resUrl}" target="iframe" class="nav-link">
										<span class="title" >${mp.resName}</span>
									</a>	
								</li>
							</c:forEach>
								</ul>
							</c:if>
						</li>
						</c:forEach>
					</ul>
					<!-- END SIDEBAR MENU -->
					<!-- END SIDEBAR MENU -->
				</div>
				<!-- END SIDEBAR -->
			</div>
			<!-- END SIDEBAR -->
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<!-- BEGIN PAGE HEADER-->

					<!-- BEGIN PAGE BAR -->
					<div class="page-bar">
						<ul class="page-breadcrumb" id="barTab">
							<li>
							</li>
						</ul>

					</div>
					<!-- END PAGE BAR -->
                    <!-- BEGIN PAGE TITLE-->
                    <iframe id="iframe" class="J_iframe" name="iframe" scrolling="auto"  width="100%" height="100%"   frameborder="0"  border="0" marginwidth="0" marginheight="0" ></iframe> 
                   </div>
				<!-- END CONTENT BODY -->
			</div>
		</div>
		<!-- END CONTAINER -->

		<!--[if lt IE 9]>
            <script src="${pageContext.request.contextPath}/statics/script/respond.min.js"></script>
            <script src="${pageContext.request.contextPath}/statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/datatable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
			<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

	<script type="text/javascript">
	$(function() {
		$("#iframe").load(function () {
			  changeFrameHeight();
		});
 		$(".nav-item .start").click(function() {			
			$(this).siblings().removeClass("active open");
			$(this).parent().parent().siblings().find("li").removeClass("active open");
			$(this).addClass("active open");
			$(this).parent().parent().addClass("active open");
			$(this).parent().parent().siblings().removeClass("active open"); //菜单选中样式
			$("#barTab").find("li").not(":first").remove();
			var  content = $(this).find("span").html();
			var html='<li><span>'+content+'</span></li>';
			$("#barTab").html(html);                  //上方添加目录
		});
 		$(".index").each(function(){
 			if("案件信息查询"==$(this).text().trim()){    //跳转到首页
 				$(this).find('span').click();
 			}
 		})	
	})
	
	window.onresize=function(){   //窗口改变触发事件
    	 changeFrameHeight();  
	} 
	
	function changeFrameHeight(){
		 var page_bar_height = $(".page-bar").height();
		 var page_header_height = $(".page-header").height();
		 var top_height = eval(page_bar_height+page_header_height);
		 var p_height = eval($(window).height()-top_height);			
		 $("#iframe").height(p_height);
	}
	
	   //登录退出
	 	function loginOut() {
			window.location.href = "${pageContext.request.contextPath}/logout";
		} 
	   //修改个人信息
	 	function toEdit(id) {
			if (id != null && id != "") {
				var url = "${pageContext.request.contextPath}/webmaster/sys/user/toOneselfUserEdit?id=" + id;
				mediaLayer2("修改个人信息", [ 0.8, '#393D49' ], 1, false,
						url, 340, 330, 2);
			} else {
				window.location.href = "${pageContext.request.contextPath}/webmaster/sys/user/toEditUser";
			}
		}
	   //修改密码
	 	function toPassword(id){
	 		if (id != null && id != "") {
				var url = "${pageContext.request.contextPath}/webmaster/sys/user/toPassword?id=" + id;
				mediaLayer2("修改密码", [ 0.8, '#393D49' ], 1, false,
						url, 400, 300, 2);
			} else {
				window.location.href = "${pageContext.request.contextPath}/webmaster/sys/user/toEditUser";
			}
	   }
	   //个人信息弹出框
	 	function mediaLayer2(title, shade, closeBtn, shadeClose, url, width, height, num){
	 		  top.layer.open({
	 		  type: 2, //2表示弹出层是iframe
	 		  title: title,
	 		  area: [width + 'px', height + 'px'],
	 		  maxmin: true, //是否显示最大化最小化按钮
	 		  shade: shade,
	 		  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
	 		  shift: num,
	 		  closeBtn: closeBtn,
	 		  shadeClose: shadeClose,
	 		  content: [url], 
	 		});
	 	}
	   //子页面调用子页面资源的方法
	   function iframe() {
		   var iframe=document.getElementById('iframe');
		   return iframe;
	   }  
	</script>
	</body>

</html>