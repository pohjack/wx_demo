<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草检查（勘验)笔录模板</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
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
			#d1 h4 {
				color: black;
				margin: 30px 0 0 0;
				padding: 0;
			}
			#d1 h5 {
				color: black;
			}
			#d1 .inspect table td.small {
			    height: 1.5cm;
			    min-height: 1.5cm;
			}
			@media print{
				#table tr td {
					font-size:17px;
					border: 1px solid black; 
				}
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="2">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container" id="d1">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h4>广东省潮州市烟草专卖局</h4>
					<h5>检查（勘验)笔录</h5>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td>时 &nbsp;间</td>
							<td colspan="5" id="insTime"></td>
							<td>地 &nbsp; &nbsp;点</td>
							<td id="addr" style="font-weight:bolder; color:black"></td>
						</tr>
						<tr>
							<td>当事人</td>
							<td colspan="5" id="party"  style="font-weight:bolder; color:black"></td>
							<td>法定代表人<br>（负责人）</td>
							<td></td>
						</tr>
						<tr>
							<td width="15%">性 &nbsp;别</td>
							<td width="6%" id="sex"></td>
							<td width="7%">年龄</td>
							<td width="6%" id="age"></td>
							<td width="7%">职业</td>
							<td width="9%">个体户</td>
							<td width="15%">有效证件号码</td>
							<td width="45%" id="card"></td>
						</tr>
						<tr>
							<td>联系电话</td>
							<td colspan="7" id="phone"></td>
						</tr>
						<tr>
							<td>住 &nbsp;址</td>
							<td colspan="7" id="cardAddr"></td>
						</tr>
						<tr>
							<td class="text_button"><div style="width: 30px;margin-left: 30px;word-wrap: break-word; font-size:19px">检查︵勘验︶笔录</div>
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="7" class="text large" id="content"></td>
						</tr>
						<tr>
							<td>当事人</td>
							<td colspan="7" class="text small"></td>
						</tr>
						<tr>
							<td>执法人员</td>
							<td colspan="7" class="text small"></td>
						</tr>
						<tr>
							<td>见证人</td>
							<td colspan="7" class="text small"></td>
						</tr>
						<tr>
							<td>备注</td>
							<td colspan="7" class="text small"></td>
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
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
		


	</body>

</html>