<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>没收财物清单</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1" name="viewport" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/statics/style/components.css"
	rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/style.css"
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

.inspect h4 {
	padding: 0
}

.inspect h5 {
	padding: 0
}
</style>
</head>

<body
	class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed"
	switchId="13">
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="inspect print">
				<h4 style="font-family: 宋体; font-size: 7mm">没收财物清单</h4>
				<div style="font-family: 宋体; font-size: 4.518mm;">　　当事人：<span>${per.per_respon }</span>　　　　　　　　　　　　　　　案件编号：<span>${info.regiNo }</span>
				</div>
				<table id="table">
				
					<tr class="ofhead">
						<td
							style="border-bottom-style: none; width: 43.4378mm; font-family: 宋体; font-size: 4.518mm;">品种</td>
						<td rowspan="2"
							style="width: 24mm; font-family: 宋体; font-size: 4.518mm;">规格</td>
						<td
							style="border-bottom-style: none; height: 8.6688mm; width: 22.862mm; font-family: 宋体; font-size: 4.518mm;">数量</td>
						<td
							style="border-bottom-style: none; width: 42mm; font-family: 宋体; font-size: 4.518mm;">品种</td>
						<td rowspan="2"
							style="width: 24mm; font-family: 宋体; font-size: 4.518mm;">规格</td>
						<td
							style="border-bottom-style: none; width: 22.862mm; font-family: 宋体; font-size: 4.518mm;">数量</td>
					</tr>
					<tr class="ofhead">
						<td
							style="border-top-style: none; width: 43.4378mm; font-family: 宋体; font-size: 3.390mm;">假冒注册商标且伪劣卷烟</td>
						<td
							style="border-top-style: none; height: 9.23mm; width: 22.862mm; font-family: 宋体; font-size: 3.390mm;">条</td>
						<td
							style="border-top-style: none; width: 42mm; font-family: 宋体; font-size: 3.390mm;">走私烟</td>
						<td
							style="border-top-style: none; width: 22.862mm; font-family: 宋体; font-size: 3.390mm;">条</td>
					</tr>
					<c:forEach begin="1" end="${rows }" varStatus="status">
						<tr class="datas">
							<td style="font-family: 宋体; font-size: 4.518mm;"
								class="jiaCigarName${status.count}"></td>
							<td
								style="height: 9.7524mm; font-family: 宋体; font-size: 4.518mm;"
								class="edit"><span data-id='' data-name='format'></span></td>
							<td style="font-family: 宋体; font-size: 4.518mm;"
								class="jiaCigarNum${status.count}"></td>

							<td style="font-family: 宋体; font-size: 4.518mm;"
								class="siCigarName${status.count}"></td>
							<td style="font-family: 宋体; font-size: 4.518mm;" class="edit"><span
								data-name='format'></span></td>
							<td style="font-family: 宋体; font-size: 4.518mm;"
								id="siCigarNum${status.count}"></td>

						</tr>
					</c:forEach>
					<c:if test="${rows < 10}">
						<c:forEach begin="1" end="${10-rows}">
							<tr class="datas">
								<td style="font-family: 宋体; font-size: 4.518mm;"></td>
								<td style="height: 8.56mm; font-family: 宋体; font-size: 4.518mm;"></td>
								<td style="font-family: 宋体; font-size: 4.518mm;"></td>
								<td style="font-family: 宋体; font-size: 4.518mm;"></td>
								<td style="font-family: 宋体; font-size: 4.518mm;"></td>
								<td style="font-family: 宋体; font-size: 4.518mm;"></td>
							</tr>
						</c:forEach>
					</c:if>
					<tr class="ofhead">
						<td colspan="6"
							style="height: 8.15mm; font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">&nbsp;</td>
					</tr>


					<tr class="ofhead">
						<td
							style="height: 6mm; font-family: 宋体; font-size: 4.920mm; border: none"></td>
						<td
							style="height: 6mm; font-family: 宋体; font-size: 4.920mm; border: none"></td>
						<td
							style="height: 6mm; font-family: 宋体; font-size: 4.920mm; border: none"></td>
						<td colspan="3"
							style="height: 6mm; font-family: 宋体; font-size: 4.920mm; border: none">广东省潮州市烟草专卖局</td>
					</tr>
					<tr class="ofhead">
						<td colspan="6"
							style="height: 13.82mm; font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">&nbsp;</td>
					</tr>
					<tr class="ofhead">
						<td colspan="2"
							style="height: 6.15mm; font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">案件当事人签名：</td>
						<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
						<td colspan="2"
							style="font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">案件承办人签名：</td>
						<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
					</tr>
					<tr class="ofhead">
						<td colspan="6"
							style="height: 4.93mm; font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">&nbsp;</td>
					</tr>
					<tr class="ofhead">
						<td colspan="2"
							style="height: 6.15mm; font-family: 宋体; font-size: 4.920mm; border: none">&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</td>
						<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
						<td colspan="2"
							style="font-family: 宋体; font-size: 4.920mm; border: none">&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日</td>
						<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
					</tr>
				</table>
				<div class="button">
					<button type="submit" class="btn blue" onclick="printme()">
						<i class="fa fa-print"></i> 打印
					</button>
					<button type="submit" class="btn btn-warning" id="export">
						<i class="fa fa-table"></i> 导出Excel
					</button>
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
		App
				.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
	</script>
	<script type="text/javascript">
		var caseId = "${caseId}";
		var jiaCigar = '${jia}';
		var siCigar = '${si}';
		var rows = "${rows }";
		var barName = $('#barTab', window.parent.document).find("span").html();

		var jia = eval('(' + jiaCigar + ')');
		var si = eval('(' + siCigar + ')');

		for ( var p in jia) {//遍历json对象的每个key/value对,p为key
			var jiaCigars = jia[p];
			for (var i = 0; i < jiaCigars.length; i++) {
				var jiaCigar = jiaCigars[i];

				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(0)")
						.html(jiaCigar.name);
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(1)")
						.find("span").html(jiaCigar.format);
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(1)")
						.find("span").attr("data-id", jiaCigar.id);

				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(2)")
						.html(jiaCigar.number);

			}
		}
		for ( var p in si) {//遍历json对象的每个key/value对,p为key
			var siCigars = si[p];
			for (var i = 0; i < siCigars.length; i++) {
				var siCigar = siCigars[i];
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(3)")
						.html(siCigar.name);
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(4)")
						.find("span").html(siCigar.format);
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(4)")
						.find("span").attr("data-id", siCigar.id);
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(5)")
						.html(siCigar.number);
			}
		}
		//删除无用规格
		for (var i = 0; i < rows; i++) {
			var jiaName = $("#table").find("tr:eq(" + (i + 2) + ")").find(
					"td:eq(2)").html().length;
			var siName = $("#table").find("tr:eq(" + (i + 2) + ")").find(
					"td:eq(5)").html().length;

			if (jiaName == 0) {
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(1)")
						.attr("class", "noEdit");
			}
			if (siName == 0) {
				$("#table").find("tr:eq(" + (i + 2) + ")").find("td:eq(4)")
						.attr("class", "noEdit");
			}
		}

		if (barName != "历史案件") {
			//单元格变成输入框
			$(document)
					.on(
							"click",
							"table .edit",
							function() {
								var c = $(this).find("input");
								if (c.length == 0) {
									var value = $(this).text();
									var name = $(this).find("span").attr(
											"data-name");
									var id = $(this).find("span").attr(
											"data-id");
									$(this)
											.html(
													"<input class='editInput form-control' style='width:20mm;height:6mm' id='editInput' name='" +name +"'  data-id='"+id+"'  data-value='" +value +"'  />");
									$(".editInput").val(value);
									$(".editInput").focus();
								}
							});
			//输入框变成单元格
			$(document).on(
					"blur",
					"table .edit",
					function() {
						var value = $("#editInput").val();
						var name = $(this).find("input").attr("name");
						var oldValue = $("#editInput").attr("data-value");
						var id = $(this).find("input").attr("data-id");
						$(this).html(
								"<span data-name=" +name +"  data-id="+id+">"
										+ value + "</span>");
						if (value == oldValue)
							return;//如果不改变值，则不触发保存事件
						updateCaseCigar(id, value);
					});
			//修改数据
			function updateCaseCigar(id, value) {
				$.ajax({
							url : "${pageContext.request.contextPath}/webmaster/caseCigar/updateCigarFormat",
							data : {
								"id" : id,
								"value" : value
							},
							traditional : true,
							type : 'post',
							dataType : 'json',
							success : function(data) {
								if (data.status != "1") {
									top.layer.msg('数据异常，请稍后再试！');

								}
							},
							error : function(msg) {
								top.layer.msg('未知错误，请稍后再试！');

							}
						});
			}
		}
		function goback() {
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
		$("#export")
				.click(
						function() {
							if (caseId != null && caseId != "") {
								var url = "${pageContext.request.contextPath}/template/exportExcel2?modelNo=13&caseId="
										+ caseId;
								window.location.href = encodeURI(url);

							} else {
								layer.msg("案件不存在，导出失败！");
							}

						})
	</script>
</body>

</html>