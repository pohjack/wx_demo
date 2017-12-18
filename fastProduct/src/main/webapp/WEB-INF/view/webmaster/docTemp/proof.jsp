<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>送达回证</title>
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
			#table tr:last-child td{border:none;}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="2${flag}">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="inspect print">
					<h3 style="font-size:38px"></h3>
					<h3 style="font-family:黑体;font-size:7.5mm">广东省潮州市烟草专卖局</h3>
					<h5 style="font-family:黑体;font-size:6.326mm; margin: 0; padding-bottom: 5px;">送&nbsp;达&nbsp;回&nbsp;证</h5>
					<div ><h3 style="height:14.0868mm;line-height:14.0868mm; font-family:宋体;font-size:5.623mm;" id="songNo">${songNo }</h3></div>
				<table id="table">
					<tr>
						<td style="width: 38.9mm; font-family: 宋体; font-size: 4.920mm">送标文书<br>名&nbsp;&nbsp;称
						</td>
						<td
							style="width: 47.5mm; font-family: 宋体; font-size: 4.518mm; font-weight: bold;"
							id="proofName">${proofName }</td>
						<td
							style="width: 43.4378mm; height: 29.1mm; font-family: 宋体; font-size: 4.920mm">送标文书<br>文&nbsp;&nbsp;号
						</td>
						<td style="width: 43.4378mm; font-family: 宋体; font-size: 4.920mm"
							id="chuNo">${chuNo }</td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">受送达人</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm"
							id="receivePer">${per.per_respon }</td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">送达地点</td>
						<td colspan="3" id="placeP" style="font-family: 宋体; font-size: 4.920mm"><input style="width:120mm;" value="${caseSend.place }" id="place"></td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">送达方式</td>
						<td colspan="3" id="wayP" style="font-family: 宋体; font-size: 4.920mm"><input  style="width:120mm;" value="${caseSend.way }" id="way"></td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">收件人签名<br>或盖章
						</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm"></td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">签收日期</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm">年&nbsp;&nbsp;月&nbsp;&nbsp;日</td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 29.1mm; font-family: 宋体; font-size: 4.920mm">代收人注明<br>代收理由
						</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm"></td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">见证人签名<br>或盖章</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm"></td>
					</tr>
					<tr>
						<td
							style="font-size: 11px; height: 14.53mm; font-family: 宋体; font-size: 4.920mm">送达人签名</td>
						<td colspan="3" id="" style="font-family: 宋体; font-size: 4.920mm"></td>
					</tr>
					<tr>
						<td style="height: 28.1736mm; font-family: 宋体; font-size: 4.920mm">备注</td>
						<td colspan="3" style="font-family: 宋体; font-size: 4.920mm"></td>
					</tr>
					<tr style="border: 0">
						<td colspan="4"
							style="height: 20.3mm; font-family: 宋体; font-size: 4.920mm">

							<br> <br> <br> <font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(印章)</font>
							<br>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span id="proofTime">年&nbsp;&nbsp;月&nbsp;&nbsp;日</span>

						</td>
					</tr>

				</table>


				<div class="button">
						<!-- <button type="submit" class="btn green-turquoise"  onclick="javaScript:goback();">
					              返回</button> -->
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
		var flag="${flag}";
		if(flag==2){
			$("#chuNo").html('<input type="text" id="wenhao" style="width:40mm;height:14mm" value="${caseSend.wenhao}"/>');
		}else{
			$("#chuNo").html("${chuNo }");
		}
		if(flag==3||flag==4){
			var proofTime="${time}";
			if(proofTime!=""&&proofTime!=null){
			$("#proofTime").html(proofTime);
			}
		}
		var barName=$('#barTab', window.parent.document).find("span").html();
		if(barName=="历史案件"){
			$("#placeP").html("${caseSend.place }");
			$("#wayP").html("${caseSend.way }");
		}
		var value="";
		$(document).on("change","#place",function() {
			value=$(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/webmaster/caseInfo/updateSend",
				data : {
					"caseInfoId" : caseInfoId,
					"name":"place",
					"value" : value,
					"flag":flag
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
			
		})
		
		$(document).on("change","#place",function() {
			value=$(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/webmaster/caseInfo/updateSend",
				data : {
					"caseInfoId" : caseInfoId,
					"name":"place",
					"value" : value,
					"flag":flag
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
			
		})
		$(document).on("change","#wenhao",function() {
			value=$(this).val();
			$.ajax({
				url : "${pageContext.request.contextPath}/webmaster/caseInfo/updateSend",
				data : {
					"caseInfoId" : caseInfoId,
					"name":"wenhao",
					"value" : value,
					"flag":flag
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
			
		})
		
		
		function goback(){
			window.history.back();
		}
		function printme() {
			var place=$("#place").val();
			$("#placeP").html(place);
			var way=$("#way").val();
			$("#wayP").html(way);
			$(".button").hide();//隐藏
			window.print();
			var text = $(".button").show();
			if(barName!="历史案件"){
			var $input = $("<input></input>"); 
			$input.attr("id","place");
			$input.attr("value",place);
			$input.attr("style","width:120mm;");
			$("#placeP").html("");
			$("#placeP").append($input);
			var $input2 = $("<input></input>"); 
			$input2.attr("id","way");
			$input2.attr("value",way);
			$input2.attr("style","width:120mm;");
			$("#wayP").html("");
			$("#wayP").append($input2);
			}
		}
		
		/**
		 * 导出Excel
		 */
		$("#export").click(function(){
			var caseId="${caseId}";
			if(caseId!=null&&caseId!=""){
				var url="${pageContext.request.contextPath}/template/exportProof?caseId=${caseId}&modelNo=2"+flag+"&flag="+flag;
				window.location.href = encodeURI(url);
				
			}else{
				layer.msg("案件不存在，导出失败！");
			}
			
		})
		</script> 
	</body>

</html>