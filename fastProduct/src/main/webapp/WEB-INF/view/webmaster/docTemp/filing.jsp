<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草立案报告表</title>
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

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="1">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3 style="font-size:38px; margin-top:5px">广东省潮州市烟草专卖局</h3>
					<h5 style="font-size:30px">立 案 报 告 表</h5>
					<p id="regiNo"></p>
					<table width="100%" border="1" cellpadding="0" cellspacing="0" id="table">
						<tr>
							<td>案&nbsp;&nbsp;由</td>
							<td colspan="3" id="caseCau">
								<div id="CaseCauValue" style="font-size:17px"></div>
								<div id="caseCauCheckBox">
								<c:forEach items="${laws }" var="law">
									<label class="mt-checkbox mt-checkbox-outline">
	                                    <input type="checkbox" id="inlineCheckbox21" value="${law.caseCauseNo }" content="${law.caseCauseCont }" name="caseCause">${law.caseCauseCont }
	                                    <span></span>
                                	</label>
								</c:forEach>
								</div>
							</td>
						</tr>
						<tr>
							<td>案由来源</td>
							<td colspan="3" id="source">
								<label class="mt-radio mt-checkbox-outline">
                                    <input type="radio" name="optionsRadios" id="" value="市场调查"> 市场调查
                                    <span></span>
                                </label>
								<label class="mt-radio mt-checkbox-outline">
                                    <input type="radio" name="optionsRadios" id="" value="举报"> 举报
                                    <span></span>
                                </label>
								<label class="mt-radio mt-checkbox-outline">
                                    <input type="radio" name="optionsRadios" id="" value="上级移交"> 上级移交
                                    <span></span>
                                </label>
								<label class="mt-radio mt-checkbox-outline">
                                    <input type="radio" name="optionsRadios" id="" value="其它"> 其它
                                    <span></span>
                                </label>
                                <input type="text" class="form-control" id="writesouce" style="display: none;">
							</td>
							<td colspan="3" id="sourceValue" style="display:none;"></td>
						</tr>
						<tr>
							<td width="20%">发案时间</td>
							<td width="26%" id="regTime2" style="font-size:15px"></td>
							<td width="22%">案发地点</td>
							<td width="32%" id="addr" style="font-size:16px"></td>
						</tr>
						<tr>
							<td>当 事 人</td>
							<td id="party"></td>
							<td>证件类型及号码</td>
							<td id="card"></td>
						</tr>
						<tr>
							<td>地&nbsp;&nbsp;址</td>
							<td colspan="3" id="cardAddr"></td>
						</tr>
						<tr>
							<td class="text_button">案情摘要
								<span>
									<button type="submit" class="btn green-turquoise" id="contEdit">内容编辑</button>
									<button type="submit" class="btn green-turquoise" id="rebuild">重新生成</button>
									<!-- <button type="submit" class="btn green-turquoise" id="modelEdit">模块编辑</button> -->
							    </span>
							</td>
							<td colspan="3" class="text large" id="content" style="font-size:16px"></td>
						</tr>
						<tr>
							<td>承办人意见</td>
							<td colspan="3" class="text small" id="opinion" style="font-size:14px"></td>
						</tr>
						<tr>
							<td>承办部门意见</td>
							<td colspan="3" class="text small"></td>
						</tr>
						<tr>
							<td>负责人意见</td>
							<td colspan="3" class="text small"></td>
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
			
			
			$("#caseCau").on("change","input",function(){
				var caseNo = '';
				var caseCause = '涉嫌';
				$("input[name='caseCause']:checked").each(function(){
					caseNo += $(this).val() + ",";
					caseCause += $(this).attr("content") + "、";
				})
				if(caseNo !== ''){
					caseNo = caseNo.substring(0, caseNo.length - 1);
					caseCause = caseCause.substring(0, caseCause.length - 1).replace("外国卷烟、销售专供","外国卷烟、专供");
				}
				updateCause(caseId,caseNo,caseCause,null);
			})
		
			
			$("#source").on("change","input",function(){
				var s = this.value;
				if(s != '其它'){
					$("#writesouce").hide();
					updateCause(caseId, null, null, s);
				}else{
					$("#writesouce").show();
					$("#writesouce").focus();
				}
			})
			
			
				function updateCause(caseId,caseNo,caseCause,source){
					$.ajax({
						type : "POST",
						url : "${pageContext.request.contextPath}/docTemplate/updateCaseCause",
						dataType : "json",
						data : {
							"caseId" : caseId,
							"caseNo" : caseNo,
							"caseCause" : caseCause,
							"source" : source
						},
						success : function(data) {
							window.location.reload();
						}
					})
				}
				//打印设置
				function printFiling() {
					setCaseCause();
					setCaseSource();
					$("#caseCauCheckBox").hide();//隐藏案由复选框
					$("#CaseCauValue").show();//显示打印案由内容
					$("#source").hide();//隐藏案由来源单选框
					$("#sourceValue").show();//显示打印案由来源
					$(".text_button").find("span").hide();//隐藏按钮
					$(".button").hide();//隐藏按钮
					window.print();
					
					$("#caseCauCheckBox").show();
					$("#CaseCauValue").hide();
					$("#source").show();
					$("#sourceValue").hide();
					$(".text_button").find("span").show();
					$(".button").show();
					setCaseCause();
				}
				//获得案由
				function setCaseCause() {
					var value = setCaseCauses();
					value = value.join("、");
					$("#CaseCauValue").text(value);
				}
				//拼接案由
				function setCaseCauses() {
					var caseCauses = new Array();
					$("input[name='caseCause']:checked").each(function() {
						caseCauses.push("销售" +$(this).attr("content"));
					});
					if(caseCauses!=null && caseCauses.length>0) {
						var flag1 = $.inArray("销售无标志外国卷烟", caseCauses);
						var flag2 = $.inArray("销售专供出口卷烟", caseCauses);
						if(caseCauses[0] == "销售未在当地烟草专卖批发企业进货") {
							caseCauses[0] = caseCauses[0].substr(2);
						}
						if(flag1!=-1 && flag2!=-1) {
							caseCauses[caseCauses.length-1] = caseCauses[caseCauses.length-1].substring(2);
						}
						caseCauses[0] = "涉嫌" +caseCauses[0];
					}
					return caseCauses;
				}
				function setCaseSource() {
					var value = $("input[name='optionsRadios']:checked").val();
					if(value == "其它") {
						value = $("#writesouce").val();
					}
					$("#sourceValue").text(value);
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