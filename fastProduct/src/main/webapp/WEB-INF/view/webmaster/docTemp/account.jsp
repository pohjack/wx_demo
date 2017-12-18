<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>进出存帐</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/datatables.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/datatables.bootstrap.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/components.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css"
	rel="stylesheet" type="text/css" id="style_color" />
<link
	href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/style.css"
	rel="stylesheet" type="text/css" />
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

<body
	class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed"
	switchId="5">
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="inspect print">
				<div
					style="float: right; font-family: 宋体; font-size: 4.518mm; line-height: 10.836mm; height: 10.836mm">${info.caseNo }</div>
				<h3 style="line-height: 10.836mm; height: 10.836mm; font-size: 32px">进出存帐</h3>
				<div class="regiNo">${info.regiNo }</div>
				<table id="table" style="table-layout: fixed;">
					<tr class="ofhead">
						<td rowspan="3" class="tableholder"
							style="width: 4%; font-size: 16px;">编<br>号
						</td>
						<td rowspan="3" class="tableholder"
							style="width: 22.9%; font-size: 21.3px;">物品名称</td>
						<td colspan="2" class="tableholder"
							style="height: 1.05cm; font-size: 21.3px;">入库</td>
						<td colspan="2" class="tableholder" style="font-size: 21.3px;">出库</td>
						<td colspan="3" class="tableholder" style="font-size: 21.3px;">库存(计算单位：条)</td>
					</tr>
					<tr class="ofhead">
						<td colspan="2" id="inDate"
							style="font-size: 11px; padding: 0; height: 0.6cm"><fmt:formatDate
								value='${info.crimeDate}' pattern='yyyy年MM月dd日' /></td>
						<td colspan="2" id="outDate" style="font-size: 11px; padding: 0;" >
							<input id="outDat" type="text" value="<fmt:formatDate value='${info.cigarOutdate}' pattern='yyyy年MM月dd日'/>" />
						</td>
						<td rowspan="2" id="" width="10.1%">假</td>
						<td rowspan="2" id="" width="10.1%">私</td>
						<td rowspan="2" id="" width="11.4%">备注</td>
					</tr>
					<tr class="ofhead">
						<td style="height: 0.76cm; width: 9.5%">数量</td>
						<td width="9.5%">送检</td>
						<td width="12.6%">数量</td>
						<td width="9.9%">备注</td>
					</tr>
					<c:set var="str" value="${fn:length(caseCigar)}"></c:set>
					<c:forEach items="${caseCigar}" var="cigar" varStatus="status">
						<tr class="datas">
							<td>${status.count}</td>
							<td
								style="font-size: 15px; height: 0.5cm; over-flow: hidden; text-overflow: ellipsis; white-space: nowrap;"><div
									style="overflow: hidden; display: block">${cigar.name }</div></td>
							<!-- <fmt:formatNumber value="${cigar.totalValue }" type="currency" pattern="#0.00"/> -->
							<td class="number"><c:if test="${cigar.number != 0}">${cigar.number  }</c:if></td>
							<td class="inspect"><c:if test="${cigar.inspectNum != 0}">${cigar.inspectNum }</c:if></td>
							<c:if test="${cigar.inspectResult eq '非'}">
								<td><c:if
										test="${(cigar.number-cigar.inspectNum+cigar.returnNum ) != 0}">${cigar.number-cigar.inspectNum+cigar.returnNum  }</c:if></td>
							</c:if>
							<c:if test="${cigar.inspectResult  ne '非'}">
								<td></td>
							</c:if>

							<td></td>
							<c:if test="${cigar.inspectResult eq '假'}">
								<td><c:if
										test="${(cigar.number-cigar.inspectNum+cigar.returnNum ) != 0}">${cigar.number-cigar.inspectNum+cigar.returnNum  }</c:if></td>
								<td></td>
							</c:if>
							<c:if test="${cigar.inspectResult eq '私'}">
								<td></td>
								<td><c:if
										test="${(cigar.number-cigar.inspectNum+cigar.returnNum ) != 0}">${cigar.number-cigar.inspectNum+cigar.returnNum  }</c:if></td>
							</c:if>
							<c:if test="${cigar.inspectResult eq '非'}">
								<td></td>
								<td></td>
							</c:if>
							<c:if test="${cigar.inspectResult eq ''}">
								<td></td>
								<td></td>
							</c:if>
							<td></td>
						</tr>

					</c:forEach>
					<c:if test="${str < 10}">
						<c:forEach begin="1" end="${10-str}">
							<tr class="ofhead">
								<td></td>
								<td style="height: 8.56mm;"></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</c:forEach>
					</c:if>

					<tr>
						<td></td>
						<td>合计</td>
						<td id="inAccount" style="height: 0.77cm"></td>
						<td id="inCheckAccount"></td>
						<td id="outAccount"></td>
						<td></td>
						<td id="jia"></td>
						<td id="si"></td>
						<td></td>
					</tr>
					<tr>
						<td style="height: 1.5cm">备<br>注
						</td>
						<td colspan="8"></td>
					</tr>

				</table>

				<div class="button">
					<button type="submit" class="btn blue" onclick="printme()">
						<i class="fa fa-print"></i> 打印
					</button>
					<button type="submit" class="btn btn-warning" id="export">
						<i class="fa fa-table"></i> 导出Excel
					</button>
					<button type="submit" class="btn green-turquoise"
						onclick="javaScript:goback();">返回</button>
				</div>
			</div>
		</div>
	</div>
	<!-- END CONTAINER -->

	<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->

	<script
		src="${pageContext.request.contextPath}/statics/script/jquery.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/app.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/dateTimeInit.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/layout.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js"
		type="text/javascript"></script>
	<script>
		App.setAssetsPath("");
		App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
	</script>
	<script type="text/javascript">
		var tb = document.getElementById("table"); //根据id找到这个表格
		var inAccount = 0;
		var inCheckAccount = 0;
		var outAccount = 0;
		var cunJia = 0;
		var cunSi = 0;
		var rows = tb.rows;
		var $tr = "${str}";
		var caseInfoId = "${caseInfoId}";
		

		//取得这个table下的有效行
		for (var i = 0; i < $tr; i++) {
			var cell = rows[i + 3].cells[2].innerHTML;
			if (cell == "" || cell == null) {
				inAccount += 0;
			} else {
				inAccount += parseInt(cell);
			}
			var cell2 = rows[i + 3].cells[3].innerHTML;
			if (cell2 == "" || cell2 == null) {
				inCheckAccount += 0;
			} else {
				inCheckAccount += parseInt(cell2);
			}

			var cell3 = rows[i + 3].cells[4].innerHTML;
			if (cell3 == "" || cell3 == null) {
				outAccount += 0;
			} else {
				outAccount += parseInt(cell3);
			}
			var cell4 = rows[i + 3].cells[6].innerHTML;
			if (cell4 == "" || cell4 == null) {
				cunJia += 0;
			} else {
				cunJia += parseInt(cell4);
			}
			var cell5 = rows[i + 3].cells[7].innerHTML;
			if (cell5 == "" || cell5 == null) {
				cunSi += 0;
			} else {
				cunSi += parseInt(cell5);
			}
		}
		var barName=$('#barTab', window.parent.document).find("span").html();
		if(barName=="历史案件"){
			$("#outDate").html("<fmt:formatDate value='${info.cigarOutdate}' pattern='yyyy年MM月dd日'/>");
		}
		$("#inAccount").html(inAccount != 0 ? inAccount : "");
		$("#inCheckAccount").html(inCheckAccount != 0 ? inCheckAccount : "");
		$("#outAccount").html(outAccount != 0 ? outAccount : "");
		$("#jia").html(cunJia != 0 ? cunJia : "");
		$("#si").html(cunSi != 0 ? cunSi : "");
		var value="";
		$(document).on("click","#outDat",function() {
			WdatePicker({
				isShowWeek:true,
				dateFmt:'yyyy年MM月dd日',
				onpicking:function(dq){
					value=dq.cal.getNewDateStr();
					$.ajax({
						url : "${pageContext.request.contextPath}/webmaster/caseInfo/updateOutdate",
						data : {
							"caseInfoId" : caseInfoId,
							"outdate" : value
						},
						traditional : true,
						type : 'post',
						dataType : 'json',
						success : function(data) {
							if (data.status != "1") {
								top.layer.msg('数据异常，请稍后再试！');

							}
						}
					});
					}  
			});
			
		});


		function goback() {
			window.history.back();
		}
		function printme() {
			var date = $("#outDat").val();
			$("#outDate").html(date);
			$(".button").hide();//隐藏
			window.print();
			var text = $(".button").show();
			if(barName!="历史案件"){
			var $input = $("<input></input>");
			$input.attr("id", "outDat");
			$input.attr("onfocus",
					"WdatePicker({isShowWeek:true,dateFmt:'yyyy年MM月dd日',})");
			$input.attr("placeholder", "请选择时间");
			$input.attr("value", date);
			$("#outDate").html("");
			$("#outDate").append($input);//时间控件回显
			}

		}

		/**
		 * 导出Excel
		 */
		$("#export")
				.click(
						function() {
							var date = $("#outDat").val();
							if (caseInfoId != null && caseInfoId != "") {
								var url = "${pageContext.request.contextPath}/template/exportExcel2?caseId="
										+ caseInfoId
										+ "&modelNo=14";
								window.location.href = encodeURI(url);

							} else {
								layer.msg("案件不存在，导出失败！");
							}

						})
	</script>
</body>

</html>