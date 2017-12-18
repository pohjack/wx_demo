<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script src="${pageContext.request.contextPath}/statics/script/jquery.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/statics/style/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript">
		//保存
		function save(){
			if($("#MENU_ICON").val()==""){
				alert('请选择图标');
				return false;
			}
			$("#menuForm").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		}
		function seticon(icon){
			$("#MENU_ICON").val(icon);
		}
		
		var index = parent.layer.getFrameIndex(window.name); 
		$(function(){
			$("#sure").click(function(){
				var img = $("input[type='radio'][checked]").val(); 
				parent.sureImage(img);
				parent.layer.close(index);
			});
			$("#close").click(function(){
				parent.layer.close(index);
			});
			$("input[type='radio']").click(function(){
				$(this).attr("checked", "checked");
			})
		})
	</script>
	<style type="text/css">
		body {
			margin: 5px 5px
		}
	</style>
</head>
<body>
	<form action="">
			<div id="zhongxin">
			<table id="table_report" class="table table-striped table-bordered table-hover">
				<tr>
					<td><input name="form-field-radio" type="radio" value="fa-user" ><span class="lbl">&nbsp;<i class="fa fa-user"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-home"><span class="lbl">&nbsp;<i class="fa fa-home"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-desktop"><span class="lbl">&nbsp;<i class="fa fa-desktop"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-edit"><span class="lbl">&nbsp;<i class="fa fa-edit"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-bar-chart-o"><span class="lbl">&nbsp;<i class="fa fa-bar-chart-o"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-cog"><span class="lbl">&nbsp;<i class="fa fa-cog"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-question-circle"><span class="lbl">&nbsp;<i class="fa fa-question-circle"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-external-link"><span class="lbl">&nbsp;<i class="fa fa-external-link"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-trash"><span class="lbl">&nbsp;<i class="fa fa-trash"></i></span></td>
				</tr>
				<tr>
					<td><input name="form-field-radio" type="radio" value="fa-tasks"><span class="lbl">&nbsp;<i class="fa fa-tasks"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-sitemap"><span class="lbl">&nbsp;<i class="fa fa-sitemap"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-navicon"><span class="lbl">&nbsp;<i class="fa fa-navicon"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-music"><span class="lbl">&nbsp;<i class="fa fa-music"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-flag"><span class="lbl">&nbsp;<i class="fa fa-flag"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-bookmark"><span class="lbl">&nbsp;<i class="fa fa-bookmark"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-book"><span class="lbl">&nbsp;<i class="fa fa-book"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-calendar-o"><span class="lbl">&nbsp;<i class="fa fa-calendar-o"></i></span></td>
					<td><input name="form-field-radio" type="radio" value="fa-area-chart"><span class="lbl">&nbsp;<i class="fa fa-area-chart"></i></span></td>
				</tr>
			</table>
				<div style="text-align: center">
					<input type="button" value="确定" id="sure" class="btn btn-primary">
					<input type="button" value="取消" id="close" class="btn btn-default">
				</div>
			</div>
		</form>
</body>
</html>