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
			var brethren = window.parent.editLeadNode("${o.id}", "${o.orgPid}");
			$("#orgPid").append(brethren);
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
		});
		
		function showRequest(formData, jqForm, options) {
			//formData: 数组对象，提交表单时，Form插件会以Ajax方式自动提交这些数据，格式如：[{name:user,value:val },{name:pwd,value:pwd}]  
			//jqForm:   jQuery对象，封装了表单的元素     
			//options:  options对象  
			var flag = notNull("orgCode", "不能为空");
			flag = notNull("orgName", "不能为空")&&flag;
			if(beforePid != $("#resPid").val()) {
				flag = flag&&window.parent.myMoveNode($("#id").val(), $("#orgPid").val());
			}
			var sort = formData[6].value;
			if(sort==null || sort=='') {
				formData[6].value = 0;
			}
			return flag;
		};
		function showResponse(responseText, statusText) {
			if(responseText.success == true) {
				if(beforeSort != $("#sort").val()) {
					window.parent.bySort($("#id").val(), $("#sort").val());
				}
				window.parent.queryMoveNode($("#id").val(), $("#orgPid").val());
				window.parent.treeNodeEdit($("#id").val(), $("#orgName").val(), $("#sort").val());
				msgLayer("提交完成");
				beforeSort = $("#sort").val();
				beforePid = $("#orgPid").val();
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
				<caption>${o.orgName }信息</caption>
				<tbody>
					<input type="hidden" name=creator class="form-control" value="1" />
					<input type="hidden" name="updator" class="form-control" value="1" />
					<input type="hidden" name="id" id="id" class="form-control" value="${o.id }" />
					<tr>
						<td>编号</td>
						<td><input type="text" name="orgCode" id="orgCode" class="form-control" readonly="readonly" value="${o.orgCode}"/></td>
					</tr>
					<tr>
						<td>名称</td>
						<td><input type="text" name="orgName" id="orgName" class="form-control" value="${o.orgName}"/></td>
					</tr>
					<tr>
						<td>地址</td>
						<td><input type="text" name="orgAddress" class="form-control" value="${o.orgAddress}"/></td>
					</tr>
					<tr>
						<td>上级</td>
						<td>
							<select id="orgPid" name="orgPid" class="form-control">
							</select>
						</td>
					</tr>
					<tr>
						<td>排列</td>
						<td><input type="text" name="sort" id="sort"  class="form-control" value="${o.sort}"  onkeyup="this.value=this.value.replace(/\D/g,'')"  onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="3"/></td>
					</tr>
					<tr>
						<td>部门负责人</td>
						<td>
							<select class="form-control" name="orgLeader">
								<c:choose>
									<c:when test="${not empty u }">
										<option></option>
										<c:forEach items="${u }" var="user">
											<option value="${user.id }" <c:if test="${user.id==o.orgLeader }">selected="selected"</c:if>>${user.realName }</option>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<option>--暂无相关人员--</option>
									</c:otherwise>
								</c:choose>
							</select>
						</td>
					</tr>
					<tr>
						<td>人员</td>
						<td>
							<c:choose>
									<c:when test="${not empty u }">
										<c:forEach items="${u }" var="user">
											<label>${user.realName }</label>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<label>--暂无相关人员--</label>
									</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td cols="2">描述</td>
						<td><textarea rows="2" cols="50" name="orgDescription" class="form-control">${o.orgDescription}</textarea>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="提交" class="btn btn-default"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<%-- <div style="height:96%; float:left; width:1px; background-color:#eee; margin-left:10px"></div>
	<p>人员信息</p>
	<div style="float:left;width:28%; margin:0px 10px 10px 10px; height:54%; overflow-y: auto; border: 1px solid #eee;">
			<table class="table table-hover">
				<tbody>
					<!-- <tr>
						<td>
							<select multiple class="form-control" style="overflow-y: hidden; height:272px">
								<option>1</option>
							    <option>2</option>
							    <option>3</option>
							    <option>4</option>
							    <option>5</option>
							</select>
						</td>
					</tr>
					<tr>
						<td>
							<button class="btn btn-default">添加</button>
 							<button class="btn btn-default">移除</button>
						</td>
					</tr> -->
					<c:if test="${not empty u }">
						<c:forEach items="${u }" var="user">
							<tr>
								<td>
									${user.realName }
								</td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
	</div> --%>
</body>
</html>