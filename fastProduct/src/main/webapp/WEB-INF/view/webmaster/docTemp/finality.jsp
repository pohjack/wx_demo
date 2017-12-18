<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件调查终结报告</title>
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
			}
			#table #content {
				font-size:15px;
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="4">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3 style="font-size:38px">广东省潮州市烟草专卖局</h3>
					<h5 style="font-size:30px; margin: 0; padding-bottom: 5px;">案件调查终结报告</h5>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td>案&nbsp;&nbsp;由</td>
							<td colspan="3" id="cause"></td>
						</tr>
						<tr>
							<td width="15%">立案日期</td>
							<td width="30%" id="regDate"></td>
							<td width="20%">调查人</td>
							<td width="35%" id="officer"></td>
						</tr>
						<tr>
							<td>当 事 人</td>
							<td id="party"></td>
							<td>证件类型及号码</td>
							<td id="card"></td>
						</tr>
						<tr>
							<td class="text_button">调查事实
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="3" class="text xxlarge" id="content"></td>
						</tr>
					</table>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td width="15%">案件性质</td>
							<td width="85%"  class="text medium" style="font-size:15px;font-family:宋体;">　　<span  id="caseAttr"></span></td>
						</tr>
						<tr>
							<td>处罚依据</td>
							<td class="text medium" id="punishBasic" style="font-size:15px"></td>
						</tr>
						<tr>
							<td>处理意见</td>
							<td class="text large autograph"><span id="handling"></span><p class="ppp"><span>签名：</span><span>日期：</span></p></td>
						</tr>
						<tr>
							<td>备&nbsp;&nbsp;注</td>
							<td class="text medium"></td>
						</tr>
					</table>
					<div class="button">
						<!--<button type="submit" class="btn green-turquoise">
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
	</body>

</html>