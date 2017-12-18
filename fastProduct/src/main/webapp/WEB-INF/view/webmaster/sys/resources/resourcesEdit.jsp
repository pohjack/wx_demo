<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script src="${pageContext.request.contextPath}/statics/script/jquery.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/jquery-form.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
	<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
	<link href="${pageContext.request.contextPath}/statics/style/font-awesome.min.css" rel="stylesheet" type="text/css" />
	<title>Insert title here</title>
	<script type="text/javascript">
		var beforeSort;
		var beforePid = "${r.resPid}";
		$(function(){
			beforeSort = $("#sort").val();
			var brethren = window.parent.editLeadNode("${r.id}", "${r.resPid}");
			$("#resPid").append(brethren);
			var options = {  
			   target: '#output',          //把服务器返回的内容放入id为output的元素中      
			   url: "doSave",        
			   type: "post",            
			   dataType: "json",           //html(默认), xml, script, json...接受服务端返回的类型  
			   beforeSubmit: showRequest,  //提交前的回调函数  
			   success: showResponse,      //提交后的回调函数  
			   timeout: 3000               //限制请求的时间，当请求大于3秒后，跳出请求  
			};
			$("#myForm").ajaxForm(options);  
			$("#chooseImge").click(function(){
				var img = window.parent.chooseImage();
			});
		});
		
		function showRequest(formData, jqForm, options) {
			//formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
			//jqForm:   jQuery对象，封装了表单的元素     
			//options:  options对象  
			var flag = notNull("resName", "不能为空");
			if(beforePid != $("#resPid").val()) {
				flag = flag&&window.parent.myMoveNode($("#id").val(), $("#resPid").val());
			}
			return flag;
		};
		function showResponse(responseText, statusText) {
			if(responseText.success == true) {
				if(beforeSort != $("#sort").val()) {
					window.parent.bySort($("#id").val(), $("#sort").val());
				}
				window.parent.queryMoveNode($("#id").val(),$("#resPid").val());
				window.parent.treeNodeEdit($("#id").val(), $("#resName").val(), $("#sort").val(), $("#inputImage").val());
				msgLayer("提交完成");
				beforeSort = $("#sort").val();
				beforePid = $("#resPid").val();
			} else {
				msgLayer(responseText.message);
				
			}
		};
		function errors(result){
			msgLayer("数据提交异常，异常名称：" +result.responseText);
        }
		function sureImage(img) {
			$("#inputImage").val(img);
			$("#inputImage").prev().removeClass();
			$("#inputImage").prev().addClass("fa " +img);
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
		.ui-dialog-button {
			padding-right:38%;
	</style>
	</style>
</head>
<body>
	<div style="padding:10px">
		<form id="myForm">
			<table class="table table-bordered" style="width:70%">
				<caption>${r.resName }信息</caption>
				<tbody>
					<input type="hidden" name=creator class="form-control" value="1" />
					<input type="hidden" name="updator" class="form-control" value="1" />
					<input type="hidden" name="id" id="id" class="form-control" value="${r.id }" />
					<tr>
						<td>名称</td>
						<td><input type="text" name="resName" id="resName" class="form-control" value="${r.resName}"/></td>
					</tr>
					<tr>
						<td>路径</td>
						<td><input type="text" name="resUrl" class="form-control" value="${r.resUrl}"/></td>
					</tr>
					<tr>
						<td>图标</td>
						<td>
							<button id="chooseImge" type="button" class="btn btn-default">图标</button>
							<i class="fa ${r.resIcon}"></i>
							<input type="hidden" id="inputImage" name="resIcon" value="${r.resIcon }">
						</td>
					</tr>
					<tr>
						<td>上级</td>
						<td>
							<select id="resPid" name="resPid" class="form-control">
							</select>
						</td>
					</tr>
					<tr>
						<td>类型</td>
						<td>
							<c:if test="${r.resType==0}">
								<input type="radio" name="resType" value="0" checked="checked">菜单
									<input type="radio" name="resType" value="1">按钮
							</c:if>
							<c:if test="${r.resType==1}">
								<input type="radio" name="resType" value="0">菜单
								<input type="radio" name="resType" value="1" checked="checked">按钮
							</c:if>
						</td>
					</tr>
  					<tr>
						<td>排列</td>
						<td><input type="text" name="sort" id="sort"  class="form-control" value="${r.sort}"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3"/></td>
					</tr>
					<tr>
						<td cols="2">描述</td>
						<td><textarea rows="2" cols="50" name="resDescription" class="form-control">${r.resDescription}</textarea>
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