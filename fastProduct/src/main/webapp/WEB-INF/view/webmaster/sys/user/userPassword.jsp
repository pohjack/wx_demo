<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
	<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
	<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
	
	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
	<script	src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js"	type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/password.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.extend.js"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<style type="text/css">
		.btn {
			color: #FFFFFF;
		    background-color: #36D7B7;
		    border-color: #36D7B7;
		    border-radius: 0;
		    line-height: 1.44;
		}
	</style>
<script type="text/javascript">
	$(function() {
		$('#password').password().on('show.bs.password', function(e) {}).on('hide.bs.password', function(e) {});
		$('#passwordConfirm').password().on('show.bs.password',	function(e) {}).on('hide.bs.password', function(e) {});
		$("#form").validate({
			debug : true, //调试模式，即使验证成功也不会跳转到目标页面
			onblur : true,
			onkeyup : false,
			onclick : false,
			focusInvalid : false,
			rules : {
				password : {
					required : true,
					rangelength : [ 6, 12 ]
				},
				passwordConfirm : {
					required : true,
					equalTo : '#password' //表示和id="password"的值相同
				}
			},
			messages : {
				password : {
					required : "请输入密码",
					rangelength : $.validator.format("字段长度必须为：{0}-{1}之间")
				},
				passwordConfirm : {
					required : "请再次输入密码",
					equalTo : "两次密码必须一致" //表示和id="spass"的值相同
				}
			},
			errorPlacement : function(error, element) {
				tipsLayer(error.text(), "#FF4500", 3000, 3, element);
			}
		})
	});

	function checkNode() { //userlist页面需要,不然会报未定义错误			
	}

	function validate() { //判断校验是否全部通过
		var r = $('#form').valid();
		return r;
	}
	function save() {
		var flag = validate();
		var id = "${id}";
		if(flag) {
			$.ajax({
				url: "${pageContext.request.contextPath}/webmaster/sys/user/oneselfChPwd",
				data: {
					"id": id,
					"password": $("#password").val()
				},
				type: "post",
				dataType: "json",
				success: function(data){
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				},
			})
		}
	}
</script>
</head>
<body>
	<div style="margin: 20px 55px;">
	 <form class="form-horizontal" id="form" method="post">
	 		<input type="hidden" id="id" name="id" value="${id}">
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-7" style="">
						<input type="password"  class="form-control" name="password" id="password"
							placeholder="请输入新密码" value="">
					</div>
				</div>
				<div class="form-group">
					<label for="passwordConfirm" class="col-sm-4 control-label">确认密码</label>
					<div class="col-sm-7">
						<input type="password"  class="form-control" name="passwordConfirm" id ="passwordConfirm" value="" placeholder="确认密码" >
					</div>
				</div>
				<div style="text-align:center">
                        <button type="button" class="btn green-turquoise" onclick="save()"> 
                        	<i class="fa fa-check"></i> 保存
                        </button>
                </div>
		</form> 
	</div>	
</body>
</html>