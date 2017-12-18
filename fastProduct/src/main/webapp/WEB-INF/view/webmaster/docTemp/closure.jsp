<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>案卷封页</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#table tr td {
				font-size:20px;
				border: 1px solid black; 
				color:black;
			}
			
			.print {
				padding: 1cm 1.1cm;
			}
			@media print{
				#caseCau {
					line-height: 1.2;
					padding: 0.1cm 0.2cm;
				}
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="9">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3 style="font-size:38px; margin-top:5px">广东省潮州市烟草专卖局</h3>
					<h5 style="font-size:30px">卷&nbsp;&nbsp;宗</h5>
					<p id="fengYeNo" style="font-size:6.326mm;"></p>
					<table width="100%" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td style="font-size:6.326mm">案由</td>
							<td colspan="3" id="caseAttr" style="font-size:4.920mm;font-weight: bold;"></td>
						</tr>
						<tr>
							<td style="font-size:6.326mm">当事人</td>
							<td colspan="3" id="party" style="font-size:6.326mm">
							</td>
						</tr>
						<tr>
							<td style="font-size:6.326mm">承办人</td>
							<td colspan="3" id="chengPers" style="font-size:6.326mm">
							</td>
						</tr>
						<tr>
							<td class="text_button" style="font-size:6.326mm">处理结果
							</td>
							<td colspan="3" class="text large" id="content" style="font-size:4.218mm"></td>
						</tr>
						<tr>
							<td width="20%" style="font-size:6.326mm">立案日期</td>
							<td width="26%" id="regDate" style="font-size:6.326mm"></td>
							<td width="22%" style="font-size:6.326mm">结案日期</td>
							<td width="32%" id="endCase" style="font-size:6.326mm"></td>
						</tr>
						<tr>
							<td width="20%" style="font-size:6.326mm">归档日期</td>
							<td width="26%" id="archiveDate" style="font-size:6.326mm"></td>
							<td width="22%" style="font-size:6.326mm">保存期限</td>
							<td width="32%"  style="font-size:6.326mm">永久</td>
						</tr>
						<tr>
							<td style="font-size:6.326mm;height:3cm">审批人</td>
							<td colspan="3" ></td>
						</tr>
						<tr  >
							<td style="border: none;"></td>
							<td style="border: none;"></td>
							<td style="border: none;"></td>
							<td  style="border: none;text-align: right;font-size:4.218mm" >此卷共计   页   &nbsp; </td>
						</tr>
					</table>
					<div class="button">
						<!--<button type="submit" class="btn green-turquoise">
					     <i class="fa fa-check"></i> 保存</button> -->
						<button type="submit" class="btn blue"  onclick="printFiling()">
					    <i class="fa fa-print"></i> 打印</button>
					    <button type="submit" class="btn btn-warning" id="export">
					    <i class="fa fa-table"></i> 导出Excel</button>
					</div>
				</div>
			<%@ include file="templateSwitch.jsp"%>
			</div>
			<script type="text/javascript">
			
				//打印设置
				function printFiling() {
					$(".button").hide();//隐藏按钮
					window.print();
					$(".button").show();
				}
			</script>
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