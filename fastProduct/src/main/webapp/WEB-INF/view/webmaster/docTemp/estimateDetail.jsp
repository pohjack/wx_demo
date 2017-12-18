<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>涉案烟草估价明细表</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
			}
			
			table {
				border: 1;
				cellpadding: 0;
				cellspacing: 0;
				width: 100%;
				table-layout: fixed;
			}
			
			#table tr td {
				font-family: "宋体";
				font-size: 16px;
				border: 1px solid black;
			}
			
			.regiNo {
				font-size: 16px;
				margin: 0;
				padding-top: 0.3cm;
				text-align: left;
				vertical-align: bottom;
			}
			
			.tableholder {
				font-weight: bold;
			}
			
			#table .tableholder td {
				white-space: nowrap;
				overflow: hidden;
				word-break: keep-all;
			}
			
			#table .ofhead td {
				padding: 0px;
			}

		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="11">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<table  id="table">
						<tr class="ofhead">
							<td  style=" width:13.8mm ; font-family: 宋体; font-size: 4.218mm;">序号</td>
							<td  style=" width:34.66mm; font-family: 宋体; font-size: 4.218mm;">条包条形码</td>
							<td  style=" width:33mm; font-family: 宋体; font-size: 4.218mm;">品牌规格</td>
							<td  style="height:10.3mm;width:17.2mm; font-family: 宋体; font-size: 4.218mm;">数量</td>
							<td  style="width:18.42mm; font-family: 宋体; font-size: 4.218mm;">建议零售<br>价格</td>
							<td   style="width:18.42mm; font-family: 宋体; font-size: 4.218mm;">价格单位</td>
							<td  style="width:18.42mm; font-family: 宋体; font-size: 4.218mm;">金额</td>
							<td  style="width:18.42mm; font-family: 宋体; font-size: 4.218mm;">备注</td>
						</tr >
						<c:set var="str" value="${fn:length(caseCigar)}"></c:set>
						<c:forEach items="${caseCigar}" var="cigar" varStatus="status">
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;">${status.count}</td>
						  		<td  style=" font-family: 宋体; font-size: 4.218mm;">${cigar.barCode }</td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"><!-- <div
									style="overflow: hidden; display: block"> -->${cigar.name }<!-- </div> --></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"><c:if test="${cigar.number != 0}">${cigar.number  }</c:if></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"><%-- <fmt:formatNumber value="${cigar.retailPrice }" type="currency" pattern="#0.00"/> --%></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;">元</td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
						  </tr>
						</c:forEach>
						<c:if test="${str < 10}">
						<c:forEach begin="1" end="${10-str}" >
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style="height:8.56mm; font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
						  </tr>
						</c:forEach>
						</c:if>
						<tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;">合计</td>
							<td  style="height:8.56mm; font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;" id="total">${total }</td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
						  </tr>
						<tr class="ofhead">
						  	<td  colspan="4" style=" font-family: 宋体; font-size: 4.218mm;text-align: left;border: none"><span>&nbsp;&nbsp;填表人：${info.catchpoleName }</span></td>
						
						  	<td  colspan="4" style=" height:13.0032mm;font-family: 宋体; font-size: 4.218mm;border: none">联系电话：0768-2210293</td>
						</tr>
						
					</table>
					<div class="button">
						<button type="submit" class="btn blue"  onclick="printme()">
					    <i class="fa fa-print"></i> 打印</button>
						<button type="submit" class="btn btn-warning" id="export">
					    <i class="fa fa-table"></i> 导出Excel</button>
					    <button type="submit" class="btn btn-warning" id="exportWord">
					    <i class="fa fa-file-word-o" aria-hidden="true"></i> 导出Word</button>
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
       <script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/dateTimeInit.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
        	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
			<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
	<script type="text/javascript">
	    var caseId="${caseId}";
		function goback(){
			window.history.back();
		}
		function printme() {
			$(".button").hide();//隐藏
			window.print();
			var text = $(".button").show();
		}
		
		/**
		 * 导出
		 */
		$("#export").click(function(){
			if(caseId!=null&&caseId!=""){
				var url="${pageContext.request.contextPath}/template/exportExcel2?modelNo=11&caseId="+caseId;
				window.location.href = encodeURI(url);
				
			}else{
				layer.msg("案件不存在，导出失败！");
			}
			
		})
		
		$("#exportWord").click(function(){
			if(caseId!=null&&caseId!=""){
				var url="${pageContext.request.contextPath}/template/exportWord?modelNo=11&caseId="+caseId;
				window.location.href = encodeURI(url);
				
			}else{
				layer.msg("案件不存在，导出失败！");
			}
			
		})
		
		</script>
	</body>

</html>