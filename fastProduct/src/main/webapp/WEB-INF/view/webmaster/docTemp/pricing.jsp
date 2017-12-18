<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>涉案烟草专卖品核价表</title>
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

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="12">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<div class="h4" style=" font-family: 宋体; font-size:6.326mm ;text-align: center;font-weight: bold;padding: 0cm 0;">潮州市烟草专卖局</div>
					<div class="h5" style=" font-family: 宋体; font-size:5.623mm;text-align: center;font-weight: bold;">涉案烟草专卖品核价表</div>
					<div style="font-family: 宋体; font-size: 4.920mm;">&nbsp;&nbsp;潮州市烟草专卖局<span id="date"><fmt:formatDate value='${info.crimeDate }' pattern='yyyy年MM月dd日'/></span>（<span id="regiNo">${info.regiNo}</span>）查获的涉案烟草专卖品价格如下：</div>
					<table  id="table">
						<tr class="ofhead">
							<td  style=" width:21.4mm ; font-family: 宋体; font-size: 4.518mm;">序号</td>
							<td  style=" width:48mm; font-family: 宋体; font-size: 4.518mm;">品种规格</td>
							<td  style="height:9.3912mm;width:24.22mm; font-family: 宋体; font-size: 4.518mm;">数量（条）</td>
							<td  style="width:24.22mm; font-family: 宋体; font-size: 4.518mm;">单价（元）</td>
							<td   style="width:25.72mm; font-family: 宋体; font-size: 4.518mm;">合计（元）</td>
							<td  style="width:32.26mm; font-family: 宋体; font-size: 4.518mm;">备注</td>
						</tr >
						<c:set var="str" value="${fn:length(caseCigar)}"></c:set>
						<c:forEach items="${caseCigar}" var="cigar" varStatus="status">
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.518mm;">${status.count}</td>
							<td  style="height:9.3912mm; font-family: 宋体; font-size: 4.518mm;  "><div
									style="overflow: hidden; display: block">${cigar.name }</div></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"><c:if test="${cigar.number != 0}">${cigar.number  }</c:if></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"><fmt:formatNumber value="${cigar.retailPrice }" type="currency" pattern="#0.00"/></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"><fmt:formatNumber value="${cigar.totalValue }" type="currency" pattern="#0.00"/></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
						  </tr>
						</c:forEach>
						<c:if test="${str < 10}">
						<c:forEach begin="1" end="${10-str}" >
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
							<td  style="height:8.56mm; font-family: 宋体; font-size: 4.518mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.518mm;"></td>
						  </tr>
						</c:forEach>
						</c:if>
						<tr class="ofhead">
						  	<td  colspan="6" style=" font-family: 宋体; font-size: 4.920mm;text-align: left;border: none">&nbsp;&nbsp;依据广东烟草潮州市有限责任公司涉案烟草专卖品估价管理小组出具的《涉案烟草专卖品价格证明》。</td>
						</tr>
						<tr class="ofhead">
						  	<td  colspan="2" style=" height:13.0032mm;font-family: 宋体; font-size: 4.920mm;text-align: left;border: none">&nbsp;&nbsp;经办人：</td>
						</tr>
						<tr class="ofhead">
						  	<td  colspan="2" style=" height:11.32mm;font-family: 宋体; font-size: 4.920mm;text-align: left;border: none">&nbsp;&nbsp;当事人：</td>
						</tr>
						
						<tr class="ofhead">
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
						  	<td  colspan="3" style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none">潮州市烟草专卖局</td>
						</tr>
						<tr class="ofhead">
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
							<td style=" height:7.63mm;font-family: 宋体; font-size: 4.920mm;border: none"></td>
						  	<td  colspan="3" style=" height:7mm;font-family: 宋体; font-size: 4.920mm;border: none"><fmt:formatDate value='${caseSlave.evalDate }' pattern='yyyy年MM月dd日'/></td>
						</tr>
					</table>
					<div class="button">
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
		 * 导出Excel
		 */
		$("#export").click(function(){
			if(caseId!=null&&caseId!=""){
				var url="${pageContext.request.contextPath}/template/exportExcel2?modelNo=12&caseId="+caseId;
				window.location.href = encodeURI(url);
				
			}else{
				layer.msg("案件不存在，导出失败！");
			}
			
		})
		</script>
	</body>

</html>