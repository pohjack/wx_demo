<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>潮州烟草案件卷宗自动化系统-涉案卷烟登记</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
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

#table {
	cellpadding: 0;
	cellspacing: 0;
	width: 100%;
	table-layout: fixed;
}

#table .tablehead td, #table tbody td {
	border-right: 1px solid #000;
	border-bottom: 1px solid #000;
}

#table .tablehead td {
	border-top: 1px solid #000;
	border-left: 1px solid #000;
}
</style>



</head>

<body
	class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
	<div class="clearfix"></div>
	<div class="page-container">
		<div class="page-content-wrapper">
			<div class="page-content">
				<div class="row">
					<div class="col-md-12">
						<div class="tabbable-line boxless tabbable-reversed">
							<ul class="nav nav-tabs" id="navbar">
								<li class=""><a href="#tab_0" data-toggle="tab"
									aria-expanded="false"
									onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase")'>
										案件信息登记 </a></li>
								<li class=""><a href="#tab_1" data-toggle="tab"
									aria-expanded="false"
									onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer")'>
										涉案人员登记 </a></li>
								<li class=""><a href="#tab_2" data-toggle="tab"
									aria-expanded="false"
									onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/CaseCigar")'>
										涉案卷烟登记 </a></li>
								<li class="active"><a href="#tab_4" data-toggle="tab"
									aria-expanded="true">非烟条码明细 </a></li>
								<li class=""><a href="#tab_3" data-toggle="tab"
									aria-expanded="false"
									onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditDence')">
										涉案证据登记 </a></li>
								<c:if
									test="${((tagId != null && tagId!='')  || type==null) && caseInfoId!=null }">
									<li class="skipModel">
										<!--  <a href="#tab_4" onclick="skipInspect('${pageContext.request.contextPath}', '${caseInfoId}')" class="c-btn-border-1x c-btn-green-turquoise">文书预览</a> -->
										<button type="button"
											onclick="skipInspect('${pageContext.request.contextPath}','${tag }', '${caseInfoId}')"
											class="btn btn-sm green btn-outline ">文书预览</button>
									</li>
								</c:if>
							</ul>
							<div id="borderTop" style="border-top: 1px solid #eef1f5;"></div>
							<div class="inspect print">
								<h4 style="font-family: 宋体; font-size: 7mm; font-weight: bold;">涉嫌未在当地烟草批发企业进货卷烟的条盒打码明细表</h4>
								<div
									style="font-family: 宋体; font-size: 4.518mm; position: relative;">
									<span>当事人：<span>${pers.perRespon}</span></span> <span
										style="position: absolute; right: 0cm;">案件编号：<span>${info.regiNo }</span></span>
								</div>
								<table id="table">

									<tr class="tablehead">
										<td
											style="width: 13mm; font-family: 宋体; font-size: 4.518mm; text-align: center;">序号</td>
										<td
											style="width: 58mm; font-family: 宋体; font-size: 4.518mm; text-align: center;">品牌</td>
										<td
											style="width: 50mm; font-family: 宋体; font-size: 4.518mm; text-align: center;">条盒打码1</td>
										<td
											style="width: 50mm; font-family: 宋体; font-size: 4.518mm; text-align: center;">条盒打码2</td>
										<td
											style="height: 8.6688mm; width: 16mm; font-family: 宋体; font-size: 4.518mm; text-align: center;">数量<br>(条)
										</td>
									</tr>
									<tbody>
										<c:set var="str" value="${fn:length(nons)}"></c:set>
										<c:forEach items="${nons }" var="non" varStatus="status">
											<tr class="datas">
												<td
													style="font-family: 宋体; font-size: 4.518mm; border-left: 1px solid #000; text-align: center;"><span
													data-id="${non.id}">${status.count}</span></td>
												<td
													style="font-family: 宋体; font-size: 4.518mm; text-align: center;"
													class="edit"><span data-name='name'>${non.name}</span></td>
												<td
													style="height: 13.7524mm; font-family: 宋体; font-size: 4.518mm; text-align: center;"
													class="edit"><span data-name='code1'>${non.code1}</span></td>
												<td
													style="font-family: 宋体; font-size: 4.518mm; text-align: center;"
													class="edit"><span data-name='code2'>${non.code2}</span></td>
												<td
													style="font-family: 宋体; font-size: 4.518mm; text-align: center;">${non.number}</td>


											</tr>

										</c:forEach>

										<c:if test="${str < 10}">
											<c:forEach begin="1" end="${10-str}">
												<tr class="datas">
													<td style="border-left: 1px solid #000;"></td>
													<td style="height: 8.56mm;"></td>
													<td></td>
													<td></td>
													<td></td>
												</tr>
											</c:forEach>
										</c:if>
										<tr class="ofhead">
											<td colspan="6"
												style="height: 8.15mm; font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">备注：无法识别的数字或字母以"*"代替；条盒打码被破坏的，数字或字母以“-”代替。</td>
										</tr>



										<tr class="ofhead">
											<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
											<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
											<td
												style="font-family: 宋体; font-size: 4.920mm; text-align: right; border: none">当事人签名：</td>
											<td
												style="font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">&nbsp;</td>
											<td
												style="font-family: 宋体; font-size: 4.920mm; text-align: left; border: none">&nbsp;</td>
										</tr>
										<tr class="ofhead">
											<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>
											<td style="font-family: 宋体; font-size: 4.920mm; border: none"></td>

											<td
												style="font-family: 宋体; font-size: 4.920mm; border: none; text-align: right">时间：</td>
											<td colspan="2"
												style="font-family: 宋体; font-size: 4.920mm; border: none">年
												&nbsp;&nbsp;月&nbsp;&nbsp; 日</td>
										</tr>
									</tbody>
								</table>
								<div class="button">
									<button type="submit" class="btn blue" onclick="printme()">
										<i class="fa fa-print"></i> 打印
									</button>
									<button type="submit" class="btn btn-warning" id="export">
										<i class="fa fa-table"></i> 导出Excel
									</button>
									<button type="button" class="btn green-turquoise"
										onclick="nextDo()">
										<i class="fa fa-check"></i>保存
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" value="${caseInfoId}" id="caseInfoId">
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
		src="${pageContext.request.contextPath}/statics/script/plugins/jquery.ui.widget.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/plugins/jquery.iframe-transport.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.min.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/layer/layer.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/caseInfo.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js"
		type="text/javascript"></script>
	<script
		src="${pageContext.request.contextPath}/statics/script/app.min.js"
		type="text/javascript"></script>
	<script>
		App.setAssetsPath("");
		App
				.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
	</script>

	<script type="text/javascript">
		//获取文件按钮单击事件，传入参数调用uploadFiles方法
		var caseInfoId = "${caseInfoId}";
		/* init();
		function init() {
			var flag = isInfoAndPerv("${pageContext.request.contextPath}", caseInfoId);
			if(!flag) return;
		} */
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
								if (name == "number") {
								} else {
									$(this)
											.html(
													"<input class='editInput form-control' style='width:43mm;height:0.8cm' id='editInput' name='" +name +"' data-value='" +value +"'  />");
								}
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
					$(this).html(
							"<span data-name=" +name +">" + value + "</span>");
					if (value == oldValue)
						return;//如果不改变值，则不触发保存事件
					var id = $(this).parent().find("td").eq(0).find("span")
							.attr("data-id");
					var names = null;
					var values = null;
					names = [ name ];
					values = [ value ];
					updateNonCigar(id, names, values);
				});

		//修改数据
		function updateNonCigar(id, name, value) {
			/* if(value[0] == "") {
				search2();
				return;
			}; */
			$
					.ajax({
						url : "${pageContext.request.contextPath}/webmaster/non/updateNon",
						data : {
							"id" : id,
							"name" : name,
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

		//页面跳转
		function skip(url) {
			var tagId = '${tagId}';
			if ('${type}' != '') {
				window.location.href = url + "?type=${type}&caseInfoId="
						+ caseInfoId + "&tagId=" + tagId;
			} else {
				window.location.href = url + "?caseInfoId=" + caseInfoId
						+ "&tagId=" + tagId;
			}
		}

		function check(obj, max) {
			var str = obj.value;
			if (str == "")
				return;
			if (/[^0-9]/g.test(str)) {
				obj.value = str.substr(0, str.length - 1);
			}
			if (str > max) {
				obj.value = max;
			}
		}
		/**
		 *打印
		 */
		function printme() {
			$(".button").hide();//隐藏
			$("#navbar").hide();
			$("#borderTop").hide();
			window.print();
			var text = $(".button").show();
			$("#navbar").show();
			$("#borderTop").show();
		}

		/**
		 * 导出Excel
		 */
		$("#export")
				.click(
						function() {
							if (caseInfoId != null && caseInfoId != "") {
								var url = "${pageContext.request.contextPath}/template/exportExcel2?modelNo=34&caseId="
										+ caseInfoId;
								window.location.href = encodeURI(url);

							} else {
								layer.msg("案件不存在，导出失败！");
							}

						})
		$("#goBack").click(function() {
			window.history.back();
		})
		$("#saveBtn")
				.click(
						function() {
							/* if(caseId!=""&&caseId!=null){ */
							var myArray = new Array();
							$(".editInput").each(
									function(index, element) {
										// 将所有匹配元素的value值封装为结果数组，并返回封装了这个结果数组的jQuery对象  
										if (!isNaN(parseInt(this.value))) {
											var editEvidence = {
												evidNo : parseInt(this.value),
												evidType : parseInt($(this)
														.data("type")),
												caseInfoId : caseInfoId
											};
											myArray.push(editEvidence);
										}

									});
							$
									.ajax({
										url : "${pageContext.request.contextPath}/webmaster/caseEvidence/saveEvidence?caseId="
												+ caseInfoId,
										type : 'post',
										dataType : 'json',
										data : JSON.stringify(myArray),
										contentType : "application/json",
										async : true,
										success : function(data) {
											if (data.result) {
												caseInfoId = data.caseInfoId;
												top.layer
														.confirm(
																'案件登记成功，是否跳转到文书模板或列表页面',
																{
																	btn : [
																			'文书预览',
																			'案件查询列表',
																			'取消' ]
																},
																function(index,
																		layero) {
																	top.layer
																			.close(index);
																	window.location.href = "${pageContext.request.contextPath}/docTemplate/toInspect?caseId="
																			+ caseInfoId;
																},
																function() {
																	window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toList";
																});
											} else {
												msgLayer("保存失败(案件信息为空，请重新写入！)");
											}
										}
									});
							/* 	}else{
									layer.msg('案件信息为空，请先填写案件信息！');
								} */
						});
		function nextDo() {
			var type = '${type}';
			var tagId = '${tagId}';
			var tag = '${tag}';
			if (type == '') {
				tipSkip("${pageContext.request.contextPath}", caseInfoId, tag);
			} else {
				window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toEditDence?type=${type}&caseInfoId="
						+ caseInfoId + "&tagId=" + tagId;
			}
		}
	</script>
</body>

</html>