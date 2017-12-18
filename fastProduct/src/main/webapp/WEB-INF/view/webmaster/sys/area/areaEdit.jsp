<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html style="height: 100%;">
<head>
	<title>潮州烟草案件卷宗自动化系统-组织机构编辑</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery-form.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
	<title>Insert title here</title>
	<script type="text/javascript">
		var beforeSort;
		var beforePid;
		$(function(){
			beforeSort = $("#sort").val();
			var brethren = window.parent.editLeadNode("${o.id}", "${o.pid}");
			$("#pid").append(brethren);
			var options = {  
					   target: '#output',          //把服务器返回的内容放入id为output的元素中      
					   url: "doSave",        
					   type: "post",            
					   async: false, 
					   dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型  
					   beforeSubmit: showRequest,  //提交前的回调函数  
					   success: showResponse,      //提交后的回调函数  
					   error:errors,
					   timeout: 3000               //限制请求的时间，当请求大于3秒后，跳出请求  
					};
			$("#myForm").ajaxForm(options);  
		});
		
		function showRequest(formData, jqForm, options) {
			//formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
			//jqForm:   jQuery对象，封装了表单的元素     
			//options:  options对象  
			var flag = notNull("code", "不能为空");
			flag = notNull("name", "不能为空")&&flag;
			if(beforePid != $("#id").val()) {
				flag = flag&&window.parent.myMoveNode($("#id").val(), $("#pid").val());
			}
			return flag;
		};
		function showResponse(responseText, statusText) {
			if(responseText.success == true) {
				
				window.parent.queryMoveNode($("#id").val(), $("#pid").val());
				window.parent.treeNodeEdit($("#id").val(), $("#name").val());
				msgLayer("提交完成");
				
				beforePid = $("#pid").val();
				window.parent.location.reload();//刷新页面
			} else {
				msgLayer(responseText.message);
				
			}
		};
		function errors(result){
        	alert("数据提交异常，异常名称：" +result.responseText);
        }
		function notNull(id, msg) {														/* 非空判断 */
			var value = $("#" +id).val().trim();
			if(value!=null && value!="") {
				$("#" +id).removeClass("errorInput");
				return true;
			} else {
				overlyTipsLayer(msg, "#FF4500", 6000, 2, $("#" +id), true);  
				$("#" +id).addClass("errorInput");
				return false;
			}
		}
	</script>
	<style type="text/css">
		.errorInput{
			box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483;
		}
		body {
			margin:0; 
			height:100%;
		}
	</style>
</head>
<body>
	<div style="padding:10px;">
		<form id="myForm">
			<table class="table table-bordered" style="width:70%; position: relative; float: left;">
				<caption>${o.name }信息</caption>
				<tbody>
					<input type="hidden" name=creator class="form-control" value="1" />
					<input type="hidden" name="updator" class="form-control" value="1" />
					<input type="hidden" name="id" id="id" class="form-control" value="${o.id }" />
					<tr>
						<td>编号</td>
						<td><input type="text" name="code" id="code" class="form-control" readonly="readonly" value="${o.code}"/></td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="name" id="name" class="form-control" value="${o.name}"/></td>
					</tr>
			
					<tr>
						<td>上级区域</td>
						<td>
							<select id="pid" name="pid" class="form-control">
							</select>
						</td>
					</tr>
					
					<tr>
						<td colspan="2"><input type="submit" value="提交" class="btn btn-default"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
</body>
</html>