<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件处理审批表</title>
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
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="5">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3 style="font-size:38px">广东省潮州市烟草专卖局</h3>
					<h5 style="font-size:32px; margin: 0; padding-bottom: 5px;">案件处理审批表</h5>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td>案&nbsp;&nbsp;由</td>
							<td colspan="8" id="cause"></td>
						</tr>
						<tr>
							<td width="13%">立案编号</td>
							<td width="37%" colspan="5" id="regiNo"></td>
							<td width="20%" colspan="2">立案日期</td>
							<td width="30%" id="regDate"></td>
						</tr>
						<tr>
							<td rowspan="4">当事人</td>
							<td>名称</td>
							<td colspan="4" id="corpName"></td>
							<td colspan="2">法定代表人<br>（负责人）</td>
							<td><div class="blankness"><img src="${pageContext.request.contextPath}/statics/images/blankness.png" /></div></td>
						</tr>
						<tr>
							<td>地址</td>
							<td colspan="4" id="addr"></td>
							<td colspan="2">联系电话</td>
							<td><div class="blankness"><img src="${pageContext.request.contextPath}/statics/images/blankness.png" /></div></td>
						</tr>
						<tr>
							<td width="5%">姓名</td>
							<td id="party"></td>
							<td width="5%">性别</td>
							<td id="sex"></td>
							<td width="5%">民族</td>
							<td id="nation"></td>
							<td width="12%">证件类型<br>及号码</td>
							<td id="card"></td>
						</tr>
						<tr>
							<td>住址</td>
							<td colspan="5" id="cardAddr"></td>
							<td>联系电话</td>
							<td id="phone"></td>
						</tr>
						<tr>
							<td>同案人</td>
							<td colspan="8">无</td>
						</tr>
						<tr>
							<td class="text_button" style="font-size:15px">案件事实
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="8" class="text xlarge" id="content" style="font-size:13px"></td>
						</tr>
					</table>
					<table width="100%" border="1" cellpadding="0" cellspacing="0">
						<tr>
							<td width="13%">处罚依据</td>
							<td width="87%" class="text medium" id="punishBase" style="font-size:15px"></td>
						</tr>
						<tr>
							<td>承办人<br>意见</td>
							<td class="text large autograph"  style="font-size:15px"><span id="dealing"></span><p class="ppp"><span>签名：</span><span>日期：</span></p></td>
						</tr>
						<tr>
							<td>承办部门<br>意见</td>
							<td class="text medium autograph"><p  class="ppp"><span>签名：</span><span>日期：</span></p></td>
						</tr>
						<tr>
							<td>法制部门<br>意见</td>
							<td class="text medium autograph"><p  class="ppp"><span>签名：</span><span>日期：</span></p></td>
						</tr>
						<tr>
							<td>负责人<br>意见</td>
							<td class="text medium autograph"><p  class="ppp"><span>签名：</span><span>日期：</span></p></td>
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
		<script type="text/javascript">
		</script>
	</body>

</html>