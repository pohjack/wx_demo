<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草卷烟价格估算申请表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#table tr td {
				font-size:21px;
				border: 1px solid black; 
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="10">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h4>卷烟价格估算申请表</h4>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td width="15%">申请单位<br>（盖章）</td>
							<td width="35%">广东省潮州市烟草专卖局</td>
							<td width="15%">申请日期</td>
							<td width="35%" id="applyDate"></td>
						</tr>
						<tr>
							<td>案件名称</td>
							<td id="cause" style="font-size:15px"></td>
							<td>案件号</td>
							<td id="regiNo"></td>
						</tr>
						<tr>
							<td class="text_button"><div style="width:25px; margin:0 auto">案 情 摘 要</div>
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="3" class="text xlarge" id="content"></td>
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
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
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