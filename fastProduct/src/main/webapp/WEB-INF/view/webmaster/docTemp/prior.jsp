<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>行政处罚事先告知书</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#table tr td {
				font-size:21px;
			}
			/* #content div span:nth-child(1)  {
				display:none;
			} */
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="6">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3>广东省潮州市烟草专卖局</h3>
					<h5 style="padding: 0; margin: 5px 0;">行政处罚事先告知书</h5>
					<p id="postAgency"></p>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="noborder" style="border: 0;">
						<tr>
							<td colspan="2" style="font-size: 21px;"><span id="party"></span>：</td>
						</tr>
						<tr>
							<td colspan="2" class="text xxlarge" style="font-size:  21px;" id="content"></td>
						</tr>
						<tr>
							<!-- <td style="font-size: 21px;">联系人：<span id="contactMan"></span></td>
							<td style="font-size: 21px;">联系电话：2210293</td> -->
							<td style="font-size: 21px;">联系人：<span></span></td>
							<td style="font-size: 21px;">联系电话：</td>
						</tr>
						<tr>
							<td colspan="2" style="font-size: 21px;">地址：潮州市新洋路中段烟草大厦</td>
						</tr>
						<tr>
							<td colspan="2" class="medium" style="font-size: 21px; text-align: right;">广东省潮州市烟草专卖局<br><span id="in_inform_date"></span></td>
						</tr>
						<tr>
							<td colspan="2" class="text_button">
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							</td>
						</tr>
					</table>
					<div class="button">
						<!-- <button type="submit" class="btn green-turquoise">
					    <i class="fa fa-check"></i> 保存</button> -->
						<button type="submit" class="btn blue" onclick="printme()">
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
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

	</body>

</html>