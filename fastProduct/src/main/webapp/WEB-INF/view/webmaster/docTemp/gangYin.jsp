<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>走私烟钢印</title>
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

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" switchId="32">
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
							<td  style="height:13.58mm; width:18.42mm; font-family: 宋体; font-size: 4.518mm;font-weight: bold;">序号</td>
							<td  style="width:19mm; font-family: 宋体; font-size: 4.518mm;font-weight: bold;">客户代码</td>
							<td  style="width:29.93mm; font-family: 宋体; font-size: 4.518mm;font-weight: bold;">品牌</td>
							<td  style="width:19mm; font-family: 宋体; font-size: 4.518mm;font-weight: bold;">所属企业</td>
							<td  style="width:19mm; font-family: 宋体; font-size: 4.518mm;font-weight: bold;">数量</td>
						</tr >
						<c:set var="str" value="${fn:length(caseGangYin)}"></c:set>
						<c:forEach items="${caseGangYin}" var="gangYin" varStatus="status">
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;"><span data-id="${gangYin.id}" class="editInput">${status.count}</span></td>
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;" class="edit" id="customerCode"><span data-name='customerCode'>${gangYin.customerCode}</span></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm; " class="edit" id="name"><span data-name='name'>${gangYin.name}</span></td>
							<td  style=" font-family: 宋体; font-size: 3.866mm;"  class="edit" id="company"><span data-name='company'>${gangYin.company}</span></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;" id="number"><span data-name='number'>${gangYin.number}</span></td>
						  </tr>
						</c:forEach>
						 <c:if test="${str < 10}">
						<c:forEach begin="1" end="${10-str}" >
						  <tr class="datas">
						  	<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
						  	<td  style=" height:8.56mm;font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
							<td  style=" font-family: 宋体; font-size: 3.866mm;"></td>
							<td  style=" font-family: 宋体; font-size: 4.218mm;"></td>
						  </tr>
						</c:forEach>
						</c:if> 
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
	    var barName=$('#barTab', window.parent.document).find("span").html();
	    
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
				var url="${pageContext.request.contextPath}/template/exportExcel2?modelNo=32&caseId="+caseId;
				window.location.href = encodeURI(url);
				
			}else{
				layer.msg("案件不存在，导出失败！");
			}
			
		})
		if(barName!="历史案件"){
			//单元格变成输入框
			$(document).on("click" ,"table .edit" ,function(){
				var c = $(this).find("input");
				if(c.length == 0) {
					var value = $(this).text();
					var name = $(this).find("span").attr("data-name");
					if(name=="number") {
						$(this).html("<input class='editInput form-control' style='width:104px;height:21px' id='editInput' name='" +name +"' data-value='" +value +"' min ='0'  type='number' oninput='if(value.length>5)value=value.slice(0,5)' />");
					} else if(name=="name"){
						$(this).html("<input class='editInput form-control' style='width:185px;height:21px' id='editInput' name='" +name +"' data-value='" +value +"'  />");
					}else {
						$(this).html("<input class='editInput form-control' style='width:104px;height:21px' id='editInput' name='" +name +"' data-value='" +value +"'  />");
					}
					$(".editInput").val(value);
					$(".editInput").focus();
				}
			});
			//输入框变成单元格
			$(document).on("blur", "table .edit" ,function(){
				var value = $("#editInput").val();
				var name = $(this).find("input").attr("name");
				var oldValue = $("#editInput").attr("data-value");
				$(this).html("<span data-name=" +name +">" +value +"</span>");
				if(value == oldValue) return;//如果不改变值，则不触发保存事件
				var id = $(this).parent().find("td:first span").attr("data-id");
				var names =null;
				var values=null;
					names=[name];
					values=[value];
				updateCaseCigar(id, names, values);
			});
			//修改数据
			function updateCaseCigar(id, name, value) {
				/* if(value[0] == "") {
					search2();
					return;
				}; */
				$.ajax({
			         url : "${pageContext.request.contextPath}/webmaster/caseGangYin/updateGangYin",
			         data : {
			        	 "id": id,
			        	 "name": name,
			        	 "value": value 
			         },
			         traditional: true,
			         type : 'post',
			         dataType : 'json',
			         success : function(data) {
			        	 if(data.status != "1") {
			        		 top.layer.msg('数据异常，请稍后再试！');
			        		
			        	 }
			         }, 
			         error : function(msg) {    
			        	 top.layer.msg('未知错误，请稍后再试！');
		        		
			         }    
			     });    
			}
			
		}
		
		
		
		
		
		
		
		
		</script>
	</body>

</html>