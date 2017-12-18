<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-案件信息登记</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.form_datetime {
				font-size: 12px
			}
			.table-condensed {
				font-size: 12px
			}
			.prinNameDiv {
				display:none;
			}
			.select2-selection__choice {
				font-size: 17px;
			}
			#caseLeft{
				float: left;
			    margin-top: 8px;
			    outline: none;
			}
			.con {
				vertical-align: bottom;
				text-align: left;
   				margin-bottom: 0;
    			padding-top: 7px;
			
			}
		
  .output-body{readonly:expression(this.readOnly=true);border:0px;}

		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<form onsubmit="return false;" id="myForm">
		<input type="hidden" name="id" value="${caseInfoId }">
		<input type="hidden" name="caseInfoId" value="${caseInfoId }">
		<input type="hidden" name="masterId" value="${caseInfoId }">
		<input type="hidden" name="caseCause" id="caseCause" value="${info.caseCause }">
		<input type="hidden" name="caseAddr" value="${info.caseAddr }">
		<input type="hidden" name="fakeRange" value="${info.fakeRange }">
		<input type="hidden" name="nonRange" value="${info.nonRange }">
		<input type="hidden" name="proxyUrl" id="proxyUrl"  value="">
		<input type="hidden" name="caseSource" id="caseSource"  value="">
		<input type="hidden" name="proxyIdCardUrl" id="proxyIdCardUrl" value="">
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="row">
					    <div class="col-md-12">
					        <div class="tabbable-line boxless tabbable-reversed">
					            <ul class="nav nav-tabs">
					                <li class="active">
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="true"> 案件信息 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toPervView')"> 涉案人员 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_2" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toCigarView')"> 涉案卷烟 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_4" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toNonDetail')">非烟条码明细 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_3" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toDenceView')"> 涉案证据 </a>
					                </li>
					                
					                
					                <c:if test="${((tagId != null && tagId!='')  || type==null) && caseInfoId!=null }">
					                	<li class="skipModel">
						                  <!--  <a href="#tab_4" onclick="skipInspect('${pageContext.request.contextPath}', '${caseInfoId}')" class="c-btn-border-1x c-btn-green-turquoise">文书预览</a> --> 
						                <button type="button" onclick="skipInspect('${pageContext.request.contextPath}','${tag }', '${caseInfoId}')" class="btn btn-sm green btn-outline ">文书预览</button>
						                </li>
					                </c:if>
					            </ul>
					            <div class="tab-content">
					                <div class="tab-pane active" id="tab_0">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>案件信息 </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <div action="#" class="form-horizontal">
						                           <div class="form-body">
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                       		<input type="hidden" name="regiNo" id="regiNo">
						                                           <label class="control-label col-md-4">案件编号</label>
						                                           <div class="col-md-8 con con">
						                                            	 ${info.regiNo }
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">先行登记号</label>
						                                           <div class="col-md-8 con con">
						                                                ${info.advRegiNo }
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">勘验开始时间</label>
						                                           <div class="col-md-8 con">
						                                           	    <div class="input-group" style="width:100%;float:left">
						                                           	    <fmt:formatDate value='${info.inquStartDate}' pattern='yyyy-MM-dd hh:mm'/>
						                                           		</div>
						                                           		<input type="hidden" id="inquStartTime" name="inquStartTime">
						                                               <%--   <label style="width: 10%;text-align: center;padding-top: 7px;">--</label>
						                                                <div class="input-group" style="width:45%; float:right">
						                                           	    	<input type="text" name="inquStartTime" value="<fmt:formatDate value='${info.inquStartTime}' pattern='HH:mm'/>" class="form-control timepicker timepicker-24">
						                                           		</div> --%>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">勘验结束时间</label>
						                                           <div class="col-md-8 con">
						                                           	    <div class="input-group" style="width:100%; float:left">
						                                           	    <fmt:formatDate value='${info.inquEndDate}' pattern='yyyy-MM-dd hh:mm'/>
						                                           		</div>	
						                                           		<input type="hidden" id="inquEndTime" name="inquEndTime">					                                             
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">案发日期</label>
						                                           <div class="col-md-8 con">
						                                           		<fmt:formatDate value='${info.crimeDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">案发时间</label>
						                                           <div class="col-md-8 con">
						                                           	  
						                                           	   <fmt:formatDate value='${info.crimeStartTime}' pattern='HH:mm'/>
						                                               --
						                                               <fmt:formatDate value='${info.crimeEndTime}' pattern='HH:mm'/>
						                                           	   
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               
						                               
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">承办人</label>
						                                           <div class="col-md-8 con">
						                                           ${slave.userName }
						                                           	   
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">调查人员</label>
						                                           <div class="col-md-8 con">
						                                           ${info.catchpoleName }
						                                           	   
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">立案案由</label>
						                                           <div class="col-md-8 con">
						                                           ${info.caseCause }
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
					                                                <label class="col-md-4 control-label">购烟途径</label>
					                                                <div class="col-md-8 con">
					                                                   ${info.buyWay }
					                                                </div>
					                                            </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">案件来源</label>
						                                           <div class="col-md-8 con">
						                                           ${ info.caseSource}
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">录入人员</label>
						                                           <div class="col-md-8 con">
						                                               <shiro:principal property='name'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               
						                                <div class="row">
						                                   <div class="col-md-6">
						                                      <div class="form-group">
						                                           <label class="control-label col-md-4">当事人是否在场</label>
						                                           <div class="col-md-8 con">
						                                           <c:if test="${info.isSite == '0'}">否</c:if>
						                                            <c:if test="${info.isSite == '1'}">是</c:if>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">委托人</label>
						                                           <div class="col-md-8 con" >
						                                              ${info.prinName }
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                               <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">罚款幅度</label>
						                                           <div class="col-md-8 con" >
						                                               <label class="range">假 </label>
						                                           	      ${info.fakeRange }%
						                                               <label class="range" style="margin-left:5%">非</label>
						                                           	      ${info.nonRange }%
						                                           </div>
						                                       </div>
						                                   </div>
						                                   
						                               </div>
						                               
						                               
						                               
						                               <h3 class="form-section"></h3>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">送检时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.inspDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">呈批时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.batchsDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">事先告知时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.inInformDate}' pattern='yyyy-MM-dd'/>
						                                               
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">估价时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.evalDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">结案时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.endCase}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">处罚决定书时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.penaltyDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">调查终结时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.endInquDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">归档时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.archiveDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">检验报告时间</label>
						                                           <div class="col-md-8 con">
						                                           <fmt:formatDate value='${slave.inspRepoDate}' pattern='yyyy-MM-dd'/>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                           </div>
						                           <div class="form-actions right">
					                                    <button type="button" class="btn default" id="goBack">返回</button> 
					                                </div>
						                       </div>
						                   </div>
						               </div>
					                </div>
					            </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
		</form>
		<!-- END CONTAINER -->

		<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.ui.widget.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.fileupload.js" type="text/javascript"></script>
	  	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.iframe-transport.js" type="text/javascript"></script>
	   	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.fileupload-process.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.min.js" type="text/javascript"></script>

       <script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/dateTimeInit.js" type="text/javascript"></script> 
        <script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>		        
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/select2.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/components-select2.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
        
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

		<script type="text/javascript">
			var caseInfoId = "${caseInfoId}";
		
			
			$(document).ready(function() {
				
				initDataTimeRange("inquStartDate","inquEndDate","yyyy-MM-dd HH:mm","inquEndDate","crimeDate");
				initDateTime("crimeDate","yyyy-MM-dd" ,"crimeStartTime");
				initTimeRange("crimeStartTime","crimeEndTime","HH:mm","crimeEndTime");
				
				initDateLinkTime("inspDate","yyyy-MM-dd","batchsDate",null,null);
				initDateLinkTime("batchsDate","yyyy-MM-dd","inInformDate",null,null);
				initDateLinkTime("inInformDate","yyyy-MM-dd","evalDate",null,null); //事先告知时间决定 呈批 决定 时间
				initDateLinkTime("evalDate","yyyy-MM-dd","endCase","endInquDate",1);
				initDateLinkTime("endCase","yyyy-MM-dd","penaltyDate","archiveDate",1);
				initDateLinkTime("penaltyDate","yyyy-MM-dd","endInquDate",null,null);
				initDateLinkTime("endInquDate","yyyy-MM-dd","archiveDate","inInformDate",0);
				initDateLinkTime("archiveDate","yyyy-MM-dd","inspRepoDate",null,null);
				initDateLinkTime("inspRepoDate","yyyy-MM-dd",null,null,null);
			
			
			});
			
			
			
			$("#userId").change(function(){
				var i = 0;
				$(this).next().find("li").each(function(){
					i++;
				});
				if(i > 3) {
					$("#select2-userId-results").hide();
				} else {
					$("#select2-userId-results").show();
				}
			});

			
			//保存前设置隐藏值
			function submitData() {
				
			    $("#inquStartTime").val($("#inquStartDate").val());  //保存勘验时间
			    $("#inquEndTime").val($("#inquEndDate").val()); 

				var regiNo = $("#caseLeft").text() +$("#caseNo").val() +$("#caseRight").text();
				$("#regiNo").val(regiNo);          				//设置立案编号
				var catchpoleNo = "";
				var userId = "";
				$("#catchpoleNo").next().find(".select2-selection__choice").each(function(){
					catchpoleNo += "、" +$(this).text();
				})
				catchpoleNo = catchpoleNo.replace(/×/g, "");
				$("#catchpoleName").val(catchpoleNo.substr(1)); //设置执法人员名称
				$("#userId").next().find(".select2-selection__choice").each(function(){
					userId += "," +$(this).text();
				})
				userId = userId.replace(/×/g, "");
				$("#userName").val(userId.substr(1));      		//设置承办人名称
				var isSite = $("input[name=isSite]:checked").val();
				if(isSite == "1") {								//设置委托人名称
					$("#prinName").val("");
				}
				$("#caseCause").val(setCaseCauses());      		//设置承办人名称
				if($("[name='caseSource1']:checked").val()=="其它"){   //选择其它
					$("#caseSource").val($("#other").val());
				}else{
					$("#caseSource").val($("[name='caseSource1']:checked").val());
				}
				var caseSource1 = $("input[name='caseSource1']:checked").val();
				if(caseSource1 != 4) {
					$("input[name='caseSource1']:checked").val("");
				}
			}
			//获得案由内容
			function setCaseCauses() {
				var caseCauses = new Array();
				$("input:checkbox[name=caseCauseCode]:checked").each(function() {
					caseCauses.push("销售" +$(this).attr("data-content"));
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
			//保存按钮事件
			$("#saveBtn").click(function(){
				saveCaseInfo();
			});
			//保存数据
			var isNextPage = "${caseInfoId}";
			function saveCaseInfo() {
				var index = top.layer.load(2);
				submitData();
				$.ajax({
			        cache: true,
			        type: "POST",
			        url:"${pageContext.request.contextPath}/webmaster/caseInfo/doSaveCase",
			        data:$('#myForm').serialize(),
			        async: false,
			        dataType:"JSON",
			        success: function(data) {
			        	top.layer.close(index);
			        	if(data.status == "1") {
			        		if(isNextPage != "") {
			        			tipSkip();
			        		} else {
			        			window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer?type=add&caseInfoId=" +data.id;
			        		}
			        	} else {
			        		top.layer.msg('保存失败，请稍后再试');
			        	}
			        }
			    });
			}
			/* 
			离开页面时保存
			window.onbeforeunload=function(event){
				if(event.clientX>document.body.clientWidth && event.clientY < 0 || event.altKey){
					saveCaseInfo();
				}else{
					saveCaseInfo();
				}
			} */
			/* 
			定时自动保存
			setTimeout(autoSave(), 30000); */
			function autoSave() {
				if(caseInfoId!=null && caseInfoId!="") {
					saveCaseInfo();
				}
			}
			//设置是否显示委托人
			$("input[name=isSite]").click(function(){
				var flag = $(this).val();
				if(flag == "1") {
					$(".prinNameDiv").hide();
					$("")
				} else {
					$(".prinNameDiv").show();
				}
			})
			
			//修改时回显数据
			function showData() {
				var userId = "${slave.userId}";
				var catchpoleNo = "${info.catchpoleNo}";
				var caseCauseCode = "${info.caseCauseCode}";
				var isSite = "${info.isSite}";
				var proxyUrl="${info.proxyUrl}";
				var proxyIdCardUrl ="${info.proxyIdCardUrl}";
				var entryPerson = "${info.entryPerson }";
				var caseSource = "${info.caseSource}";
				if(caseSource!=""){
					/* $(":radio[name='caseSource1'][value='" + caseSource.split(":")[0] + "']").prop("checked", "checked");
					if(caseSource.split(":")[0]=="其它"){
						$("#other").val(caseSource.split(":")[1]);
						$("#other").show();
					} */
					if("自查举报上级移交".indexOf(caseSource) < 0){
						$("input[name='caseSource1'][value='其它']").attr("checked",true); 
						$("#other").val(caseSource);
						$("#other").show();
					} else {
						$("input[name='caseSource1'][value='" +caseSource +"']").attr("checked",true); 
					}
				}	
				$("input[name='isSite'][value='" +isSite +"']").attr("checked",true);	//回显当事人是否在现场
				if(userId!=null && userId.length>0) {			//回显承办人
					userId = userId.split(",");
					var $userId = $("#userId").select2();
					$userId.val(userId).trigger("change");
				}
				if(catchpoleNo!=null && catchpoleNo.length>0) { //回显调查人员
					catchpoleNo = catchpoleNo.split(",");
					var $catchpoleNo = $("#catchpoleNo").select2();
					$catchpoleNo.val(catchpoleNo).trigger("change");
				}
				if(caseCauseCode != null) {							//回显案由
					caseCauseCode = caseCauseCode.split(",");
					for(var i=0; i<caseCauseCode.length; i++) {
						$("input[name='caseCauseCode']:checkbox[value='" +caseCauseCode[i] +"']").attr('checked','true')
					}
				}
				if(isSite == "0") {
					$(".prinNameDiv").show();
				}
				if(entryPerson != "") {
					$("#entryPerson").val(entryPerson);
				}
			}
			$("#goBack").click(function(){
				window.history.back();
			})
			function skip(url) {
				var tagId='${tagId}';
				window.location.href = url +"?caseInfoId=" +caseInfoId+"&tagId="+tagId;
			}
			function tipSkip() {
				top.layer.confirm('修改成功，是否跳转到模板或列表页面', {
				    btn: ['模板', '列表', '取消']
				  },function(index, layero) {
					  top.layer.close(index);
					  window.location.href = "${pageContext.request.contextPath}/docTemplate/toInspect?caseId=" +caseInfoId;
				  },function() {
					  window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toList";
				  });
			}
			$("input[name='caseSource1']").change(function(){
				var value = $("input[name='caseSource1']:checked").val();
				if(value == "其它") {
					$("#other").show();
				} else {
					$("#other").hide();
				}
			})
			//跳转到文书模板
		function skipInspect(tip,tag, caseId) {
			window.location.href = tip +"/docTemplate/"+tag+"&caseId=" +caseId;
		}
		</script>
	</body>
</html>