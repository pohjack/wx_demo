<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>潮州烟草案件卷宗自动化系统-数据字典编辑</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />

<link href="${pageContext.request.contextPath}/statics/style/plugins/time/daterangepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.form_datetime {
	font-size: 12px
}

.table-condensed {
	font-size: 12px
}

.col-md-8 {
	margin-bottom: 15px
}
</style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<form onsubmit="return false;" id="myForm">
		<input type="hidden" name="id" id="id" value="${obj.id }">
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					
					<div class="row">
						<div class="col-md-12">
							<div class="tabbable-line boxless tabbable-reversed">
								
								
										<div class="portlet box green-turquoise">
											<div class="portlet-title">
												<div class="caption">
													<i class="fa fa-plus-square"></i><c:if test="${not empty obj.id}" >编辑</c:if><c:if test="${empty obj.id}" >新建</c:if>数据字典 
												</div>
											</div>
											<div class="portlet-body form">
												<div class="form-body">
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">数据字典名称</label>
																<div class="col-md-8">
																	<input type="text" name="name" id="name" value="${obj.name }" maxlength="20" class="form-control">
																</div>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">数据字典代码</label>
																<div class="col-md-8">
																	<input type="text" name="code" id="code" value="${obj.code }" <c:if test="${not empty obj.id}" > readonly </c:if> maxlength="20" class="form-control">
																</div>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">数据字典值</label>
																<div class="col-md-8">
																	<input type="text" name="value" id="value" value="${obj.value }"  class="form-control">
																</div>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">排序字段</label>
																<div class="col-md-8">
																	<input type="text" name="sort" id="sort"    value="<c:if test="${not empty obj.id}" >${obj.sort }</c:if><c:if test="${empty obj.id}" >0</c:if>" maxlength="20" class="form-control">
																</div>
															</div>
														</div>
													</div>
													
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">数据类别</label>
																<div class="col-md-8">
																	<div class="mt-radio-inline">
																		<label class="mt-radio mt-radio-outline"> 
																			<input type="radio" name="category" value="0" <c:if test="${empty obj.id}" >checked = "checked"</c:if> <c:if test="${obj.category < 1}">checked = "checked"</c:if>>
																			常量 <span></span>
																		</label> 
																		<label class="mt-radio mt-radio-outline"> 
																			<input type="radio" name="category" value="1" <c:if test="${obj.category == 1}">checked = "checked"</c:if>>
																			变量 <span></span>
																		</label> <label class="mt-radio mt-radio-outline"> 
																			<input type="radio" name="category" value="2" <c:if test="${obj.category > 1}">checked = "checked"</c:if>>
																			判别量 <span></span>
																		</label>
																	</div>
																</div>
															</div>
														</div>
														
													</div>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label col-md-4">描述</label>
																<div class="col-md-8">
																	<textarea class="form-control" name="description" id="description" rows="3" maxlength="100">${obj.description }</textarea>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="form-actions right">
												<button type="submit" class="btn green-turquoise" id="saveBtn">
														<i class="fa fa-check"></i> 保存
													</button>
													<button type="button" class="btn default" onclick="window.history.back();">返回</button>
												</div>
											</div>
										</div>
									
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!-- END CONTAINER -->

	<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->

	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-fileinput.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>

	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/moment.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/daterangepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/bootstrap-datepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/bootstrap-timepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/time/components-date-time-pickers.js" type="text/javascript"></script>

	<script src="${pageContext.request.contextPath}/statics/script/plugins/select/select2.full.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/select/components-select2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>

	<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>
	
	<script type="text/javascript">
			function submitClickOut(){
				if($("#name").val() == ""){
					layer.tips('[数据字典名称]不能为空', '#name', {
					  tips: [1, '#3595CC'],
					  time: 4000
					});
					return false;
				}
				if($("#code").val() == ""){
					layer.tips('[数据字典代码]不能为空', '#code', {
					  tips: [1, '#3595CC'],
					  time: 4000
					});
					return false;
				}
				if($("#value").val() == ""){
					layer.tips('[数据字典值]不能为空', '#value', {
					  tips: [1, '#3595CC'],
					  time: 4000
					});
					return false;
				}
				if($("#sort").val() == ""){
					layer.tips('[排序字段]不能为空', '#sort', {
					  tips: [1, '#3595CC'],
					  time: 4000
					});
					return false;
				}
				return true;
			}
			$("#saveBtn").click(function(){
				var submitClick = submitClickOut();
				if(submitClick){
					$.ajax({
				        cache: true,
				        type: "POST",
				        url:"${pageContext.request.contextPath}/webmaster/dictData/doSave",
							data : $('#myForm').serialize(),// 你的formid
							async : false,
							dataType : "JSON",
							error : function(request) {
								layer.msg("Connection error");
							},
							success : function(data) {
								var Mesg = "保存";
								if($("#id").val()!=""){
									Mesg="修改";
								}
								if (data.success) {
									top.layer.msg(Mesg+"成功");
									window.location.href="${pageContext.request.contextPath}/webmaster/dictData/toList";
								} else {
									top.layer.msg(Mesg+"失败");
								}
							}
						});
					}
				});

			function skip(url) {
				window.location.href = url;
			}
		</script>
</body>

</html>