<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草结案报告表</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#table tr td {
				font-size:17px;
				border: 1px solid black; 
				padding: 0.25cm 0.15cm;
			}
			#table2 tr td {
				font-size:17px;
				border: 1px solid black; 
				padding: 0.25cm 0.15cm;
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="8">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3>广东省潮州市烟草专卖局</h3>
					<h5 style="padding: 0;margin-bottom: 0;">结案报告表</h5>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td>案&nbsp;&nbsp;由</td>
							<td colspan="3" id="caseAttr"></td>
						</tr>
						<tr>
							<td width="15%">立案日期</td>
							<td width="35%" id="regDate"></td>
							<td width="15%">调查人</td>
							<td width="35%" id="officer"></td>
						</tr>
						<tr>
							<td>当 事 人</td>
							<td id="party"></td>
							<td>地址</td>
							<td id="cardAddr"></td>
						</tr>
						<tr>
							<td class="text_button">案情摘要
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="3" class="text xxlarge" id="content" style="font-size:15px"></td>
						</tr>
					</table>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table2">
						<tr>
							<td width="15%">处理决定</td>
							<td width="85%" class="text large" id="decision"></td>
						</tr>
						<tr>
							<td>执行情况</td>
							<td class="text medium" id="exeCondition"></td>
						</tr>
						<tr>
							<td>承办人<br />结案理由</td>
							<td class="text medium" id="closeReason"></td>
						</tr>
						<tr>
							<td>承办人<br />部门意见</td>
							<td class="text medium"></td>
						</tr>
						<tr>
							<td>负责人<br />意见</td>
							<td class="text medium"></td>
						</tr>
						<tr>
							<td>备&nbsp;&nbsp;注</td>
							<td class="text medium"></td>
						</tr>
					</table>
					<div class="button">
						<!-- <button type="submit" class="btn green-turquoise">
					    <i class="fa fa-check"></i> 保存</button> -->
						<button type="submit" class="btn blue"  onclick="printme()">
					    <i class="fa fa-print"></i> 打印</button>
					    <button type="submit" class="btn btn-warning" id="export">
					    <i class="fa fa-table"></i> 导出Excel</button>
					</div>
				</div>
				<%@ include file="templateSwitch.jsp"%>
			</div>
		</div>
		<!-- END CONTAINER -->

		<!--[if lt IE 9]>
            <script src="${pageContext.request.contextPath}/statics/script/respond.min.js"></script>
            <script src="${pageContext.request.contextPath}/statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
			<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
	<script type="text/javascript">
	</script>
	</body>

</html>