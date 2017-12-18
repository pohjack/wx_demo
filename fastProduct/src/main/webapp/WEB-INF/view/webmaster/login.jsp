<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>潮州烟草案件卷宗自动化系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/page/login-3.min.css" rel="stylesheet" type="text/css" />
</head>

<body class=" login">
<!-- BEGIN LOGO -->
        <div class="logo">
            
                <img src="${pageContext.request.contextPath}/statics/images/logo-big.png" alt="" /> 
        </div>
        <!-- END LOGO -->
        <!-- BEGIN LOGIN -->
        <div class="content">
            <!-- BEGIN LOGIN FORM -->
            <form class="login-form" action="" method="post" id="wyccn">
                <h3 class="form-title">后台登录</h3>
                <div class="alert alert-danger display-hide" id="message">
                    <button class="close" data-close="alert"></button>
                    <span> 账号或密码错误 </span>
                </div>
                <div class="form-group">
                    <!--ie8, ie9 does not support html5 placeholder, so we just show field title for that-->
                    <label class="control-label visible-ie8 visible-ie9">账号</label>
                    <div class="input-icon">
                        <i class="fa fa-user"></i>
                        <input class="form-control placeholder-no-fix" type="text" autocomplete="off" placeholder="登录账号" id="username" name="username" autofocus/> </div>
                         
                </div>
               
                <div class="form-group">
                    <label class="control-label visible-ie8 visible-ie9">密码</label>
                    <div class="input-icon">
                        <i class="fa fa-lock"></i>
                        <input class="form-control placeholder-no-fix" type="password" autocomplete="off" placeholder="登录密码" id="password" name="password" /> </div>
                	
                </div>
                <div class="form-actions">
                     <label class="rememberme mt-checkbox mt-checkbox-outline">
                       <!--  <input type="checkbox" name="remember" value="1" /> 记住我
                        <span></span> -->
                    </label> 
                    <button type="submit" class="btn green pull-right"> 登录 </button>
                </div>               
            </form>
            <!-- END LOGIN FORM -->
        </div>
        <!--[if lt IE 9]>
            <script src="${pageContext.request.contextPath}/statics/script/respond.min.js"></script>
            <script src="${pageContext.request.contextPath}/statics/script/excanvas.min.js"></script> 
		<![endif]-->
         <script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.min.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/page/login.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
         <script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
         <script type="text/javascript">
         $(function(){
        	 if(window !=top){  
         	    top.location.href=location.href;  
         	} 
         })
         function login(){
        	 $.ajax({
     	        cache: true,
     	        type: "POST",
     	        url:"${pageContext.request.contextPath}/doLogin",
     	        data:$("#wyccn").serialize(),// 你的formid
     	        async: false,
     	        dataType:"JSON",
     	        error: function(request) {
     	        	top.layer.alert("Connection error", {icon: 2,closeBtn:2,title: false,btn:false,time:2000});
     	        },
     	        success: function(data) {
     	        	if(!data.IsError){
     	            		window.location.href = "${pageContext.request.contextPath}/public/index";
     	        	}else{  
     	        			/* alertLayer(data.message, 2, 2); */
     		        		/* reloadValidateCode();
     		        		$("#submitCode").val(''); */
     		        		$("#message").css('display','block'); 
     		        		$("#username").focus();
     	        	}
     	        }
     	    });
         }         
         </script>
</body>
</html>
