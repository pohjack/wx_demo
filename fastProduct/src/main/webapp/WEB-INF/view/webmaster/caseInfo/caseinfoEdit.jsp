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
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
			    outline: none;
			    border-bottom: 1px solid #000;
			    height: 34px;
			    line-height: 34px;
			}
		
  			.output-body{
  				readonly:expression(this.readOnly=true);
  				border:0px;
  			}
			.col-md-4 span {
				float:right;
				color:red;
			}
			.range {
				width: 5%;
				text-align: center;
				padding-top: 7px; 
				float:left !important;
			}
			.input-group-addon {
				font-family: 华文细黑;
				font-size: 16px; 
				background-color: #eef1f5;
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<form onsubmit="return false;" id="myForm">
		<input type="hidden" name="id" value="${caseInfoId }">
		<input type="hidden" name="flag" id="flag" value="${flag }" >
		<input type="hidden" name="caseInfoId" value="${caseInfoId }">
		<input type="hidden" name="masterId" value="${caseInfoId }">
		<input type="hidden" name="caseCause" id="caseCause" value="${info.caseCause }">
		<input type="hidden" name="caseAddr" value="${info.caseAddr }">
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
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="true"> 案件信息登记  </a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer')"> 涉案人员登记 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_2" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/CaseCigar')"> 涉案卷烟登记 </a>
					                </li>
					                 <li class="">
					                    <a href="#tab_4" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditNonDetail')">非烟条码明细 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_3" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditDence')"> 涉案证据登记 </a>
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
						                           <i class="fa fa-plus-square"></i>案件信息  </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <div action="#" class="form-horizontal">
						                           <div class="form-body">
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                       		<input type="hidden" name="regiNo" id="regiNo">
						                                           <label class="control-label col-md-4"><span>*</span>案件编号</label>
						                                           <div class="col-md-8">
						                                            	<div id="caseLeft" contenteditable="true">潮烟立[<fmt:formatDate value="${now}" pattern="yyyy" />]第</div>
						                                            	<input type="text" name="caseNo" id="caseNo" value="${caseNo }" maxlength="12" class="form-control" style="width: 60px; display: inline-block; margin: 0 10px;">
						                                            	<span id="caseRight">号</span>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>先行登记号</label>
						                                           <div class="col-md-8">
						                                        
						                                               <input type="text" name="advRegiNo" id="advRegiNo" value="${info.advRegiNo }" maxlength="20" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>勘验开始时间</label>
						                                           <div class="col-md-8">
						                                           	    <div class="input-group" style="width:100%;float:left">
						                                           	    	<input type="text" name="inquStartDate" id="inquStartDate" value="<fmt:formatDate value='${info.inquStartDate}' pattern='yyyy-MM-dd HH:mm'/>"  class="form-control date-picker " readonly>
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
						                                           <label class="control-label col-md-4"><span>*</span>勘验结束时间</label>
						                                           <div class="col-md-8">
						                                           	    <div class="input-group" style="width:100%; float:left">
						                                           	    	<input type="text" name="inquEndDate" id="inquEndDate" value="<fmt:formatDate value='${info.inquEndDate}' pattern='yyyy-MM-dd HH:mm'/>"  class="form-control date-picker" readonly>
						                                           		</div>	
						                                           		<input type="hidden" id="inquEndTime" name="inquEndTime">					                                             
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>案发日期</label>
						                                           <div class="col-md-8">
						                                           		<input type="text" name="crimeDate" id="crimeDate" value="<fmt:formatDate value='${info.crimeDate}' pattern='yyyy-MM-dd'/>" data-date-format="yyyy-MM-dd" data-date="2017-03-04" class="form-control date-picker" readonly>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>案发时间</label>
						                                           <div class="col-md-8">
						                                           	   <div class="input-group" style="width:45%; float:left">
						                                           	      <input type="text" name="crimeStartTime" id="crimeStartTime" value="<fmt:formatDate value='${info.crimeStartTime}' pattern='HH:mm'/>" class="form-control timepicker timepicker-24" readonly>
						                                               </div>
						                                               <label style="width: 10%;text-align: center;padding-top: 7px;">--</label>
						                                               <div class="input-group" style="width:45%; float:right">
						                                           	       <input type="text" name="crimeEndTime" id="crimeEndTime" value="<fmt:formatDate value='${info.crimeEndTime}' pattern='HH:mm'/>" class="form-control timepicker timepicker-24" readonly>
						                                           	   </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               
						                               
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>承办人</label>
						                                           <div class="col-md-8 userName">
						                                           	   <input type="hidden" name="userName" id="userName" value="${slave.userName }"  maxlength="20">
						                                               <select name="userId" id="userId" class="form-control select2-multiple" multiple>
						                                               </select>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>调查人员</label>
						                                           <div class="col-md-8 catchpoleName">
						                                           	   <input type="hidden" name="catchpoleName" id="catchpoleName" maxlength="20" value="${info.catchpoleName }">
						                                               <select name="catchpoleNo" id="catchpoleNo" class="form-control select2-multiple" multiple>
						                                               </select>
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span>*</span>立案案由</label>
						                                           <div class="col-md-8">
						                                               <div class="mt-checkbox-inline">
						                                                    <c:if test="${not empty laws }">
							                                                    <c:forEach var="item" items="${laws }">
								                                                    <label class="mt-checkbox mt-checkbox-outline">
								                                                        <input type="checkbox" name="caseCauseCode" value="${item.caseCauseNo}" data-content="${item.caseCauseCont }">${item.caseCauseCont }
								                                                        <span></span>
								                                                    </label><br>
							                                                    </c:forEach>
						                                                    </c:if>
						                                                </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
					                                                <label class="col-md-4 control-label"><span>*</span>购烟途径</label>
					                                                <div class="col-md-8">
					                                                    <textarea class="form-control" name="buyWay" id="buyWay" rows="6"  maxlength="100">${info.buyWay }</textarea>
					                                                </div>
					                                            </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">案件来源</label>
						                                           <div class="col-md-8">
							                                           <div class="mt-radio-inline">
						                                                    <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="caseSource1" value="市场调查" checked="checked"> 市场调查
						                                                        <span></span>
						                                                    </label>
						                                                    <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="caseSource1" value="举报"> 举报
						                                                        <span></span>
						                                                    </label>
						                                                     <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="caseSource1" value="上级移交"> 上级移交
						                                                        <span></span>
						                                                    </label>
						                                                     <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="caseSource1" value="其它"> 其它						                                                    
						                                                        <span></span>
						                                                    </label>
						                                                    <input id="other" type="text" class="form-control" style="display:none" placeholder="填写其它内容" >
						                                                </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">录入人员</label>
						                                           <div class="col-md-8">
						                                               <%-- <input type="text" name="entryPerson" value="${info.entryPerson }"  maxlength="10" class="form-control"> --%>
						                                               <input type="text"  id="entryPerson" readonly="true" name="entryPerson" value="<shiro:principal property='name'/>"  maxlength="10" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">当事人是否在场</label>
						                                           <div class="col-md-8">
							                                           <div class="mt-radio-inline">
						                                                    <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="isSite" value="1" checked> 是
						                                                        <span></span>
						                                                    </label>
						                                                    <label class="mt-radio mt-radio-outline">
						                                                        <input type="radio" name="isSite" value="0"> 否
						                                                        <span></span>
						                                                    </label>
						                                                </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">委托人</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="prinName" id="prinName" value="${info.prinName }" maxlength="20" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                               </div>
						                               <div class="row">
						                               <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">罚款幅度</label>
						                                           <div class="col-md-8">
						                                               <label class="range">假 </label>
						                                           	   <div class="input-group" style="width:40%; float:left">
						                                           	      <input type="text" name="fakeRange" id="fakeRange" value="${info.fakeRange }" class="form-control intNum" maxlength="2">
					                                                      <span class="input-group-addon">%</span>
						                                               </div>
						                                               <label class="range" style="margin-left:5%">非</label>
						                                               <div class="input-group" style="width:40%; float:left">
						                                           	      <input type="text" name="nonRange" id="nonRange" value="${info.nonRange }" class="form-control intNum" maxlength="2">
						                                           	      <span class="input-group-addon">%</span>
						                                           	   </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   
						                               </div>
						                               <h3 class="form-section"></h3>
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">1 送检时间</label>
																	<div class="col-md-8">
																		<input type="text" id="inspDate" name="inspDate"
																			value="<fmt:formatDate value='${slave.inspDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">2 检验报告时间</label>
																	<div class="col-md-8">
																		<input type="text" name="inspRepoDate"
																			id="inspRepoDate"
																			value="<fmt:formatDate value='${slave.inspRepoDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
															
														</div>
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">3 估价时间</label>
																	<div class="col-md-8">
																		<input type="text" name="evalDate" id="evalDate"
																			value="<fmt:formatDate value='${slave.evalDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">4 调查终结时间</label>
																	<div class="col-md-8">
																		<input type="text" name="endInquDate" id="endInquDate"
																			value="<fmt:formatDate value='${slave.endInquDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>


														</div>
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">5 呈批时间</label>
																	<div class="col-md-8">
																		<input type="text" name="batchsDate" id="batchsDate"
																			value="<fmt:formatDate value='${slave.batchsDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">6 事先告知时间</label>
																	<div class="col-md-8">
																		<input type="text" name="inInformDate"
																			id="inInformDate"
																			value="<fmt:formatDate value='${slave.inInformDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">7 处罚决定书时间</label>
																	<div class="col-md-8">
																		<input type="text" name="penaltyDate" id="penaltyDate"
																			value="<fmt:formatDate value='${slave.penaltyDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>
															<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">8 结案时间</label>
																	<div class="col-md-8">
																		<input type="text" name="endCase" id="endCase"
																			value="<fmt:formatDate value='${slave.endCase}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
															</div>

														</div>

														<div class="row">
														<div class="col-md-6">
																<div class="form-group">
																	<label class="control-label col-md-4">9 归档时间</label>
																	<div class="col-md-8">
																		<input type="text" name="archiveDate" id="archiveDate"
																			value="<fmt:formatDate value='${slave.archiveDate}' pattern='yyyy-MM-dd'/>"
																			data-date-format="yyyy-MM-dd"
																			class="form-control date-picker" readonly>
																	</div>
																</div>
																
															</div>
															

														</div>

													</div>
						                           <div class="form-actions right">
					                                    <button type="button" class="btn default" id="goBack">返回</button> 
					                                    <button type="button" class="btn green-turquoise" id="saveBtn">
					                                        <i class="fa fa-check"></i> 保存</button>
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
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
       	<script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/dateTimeInit.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/select/select2.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/components-select2.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/caseInfo.js" type="text/javascript"></script>
        
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

		<script type="text/javascript">
			var caseInfoId = "${caseInfoId}";
			var tag="${tag }";
		
			
			$(document).ready(function() {
				
				initDataTimeRange("inquStartDate","inquEndDate","yyyy-MM-dd HH:mm","inquEndDate","crimeDate","crimeStartTime","crimeEndTime");
				initDateTime("crimeDate","yyyy-MM-dd" ,"crimeStartTime");
				initTimeRange("crimeStartTime","crimeEndTime","HH:mm","crimeEndTime");

				initDateLinkTime("inspDate","yyyy-MM-dd","inspRepoDate",null,null);
				initDateLinkTime("inspRepoDate","yyyy-MM-dd","evalDate","endInquDate",1);
				initDateLinkTime("evalDate","yyyy-MM-dd","endInquDate",null,null);
				initDateLinkTime("endInquDate","yyyy-MM-dd","batchsDate","inInformDate",0);
				initDateLinkTime("batchsDate","yyyy-MM-dd","inInformDate",null,null);
				initDateLinkTime("inInformDate","yyyy-MM-dd","penaltyDate",null,null); //事先告知时间决定 呈批 决定 时间
				initDateLinkTime("penaltyDate","yyyy-MM-dd","endCase",null,null);
				initDateLinkTime("endCase","yyyy-MM-dd","archiveDate",null,null);
				initDateLinkTime("archiveDate","yyyy-MM-dd",null,null,null);
				
			});
			
			
			init();			
			function init() {
				getUserData();
			}
			//为null判断
			function nullWarning() {
				var flag = notNull("caseNo", "案件编号不能为空");
				flag = notNull("advRegiNo", "先行登记号不能为空")&&flag;
				flag = notNull("inquStartDate", "请选择勘验开始时间")&&flag;
				flag = notNull("inquEndDate", "请选择勘验结束时间")&&flag;
				flag = notNull("crimeDate", "请选择案发日期")&&flag;
				flag = notNull("crimeStartTime", "请选择案发时间")&&flag;
				flag = notNull("crimeEndTime", "请选择案发时间")&&flag;
				flag = notNull("buyWay", "购烟途径不能为空")&&flag;
				var d = $("input[name='caseCauseCode']:checked").length;
				if(d <= 0) {
					flag = false;
					overlyTipsLayer('立案案由不能为空', "#FF4500", 3000, 4, $("input[name='caseCauseCode']:first").next(), true);
				}
				var ca = $("#catchpoleNo").next().find(".select2-selection__choice");
				var us = $("#userId").next().find(".select2-selection__choice");
				if(ca.length <= 0) {
					flag = false;
					overlyTipsLayer('请选择调查人员', "#FF4500", 3000, 4, $("#catchpoleNo").next(), true);  
				} else {
					if(ca.length>6) {
						overlyTipsLayer('调查人员数量不能大于6人', "#FF4500", 3000, 4, $("#catchpoleNo").next(), true); 
						flag = false; 
					}
				}
				if(us.length <= 0) {
					flag = false;
					overlyTipsLayer('请选择承办人', "#FF4500", 3000, 4, $("#userId").next(), true);  
				} else {
					if(us.length>6) {
						overlyTipsLayer('承办人数量不能大于6人', "#FF4500", 3000, 4, $("#userId").next(), true); 
						flag = false; 
					}
				}
				flag = isSame()&&flag;
				if(!flag) {
					$('html, body').animate({scrollTop:0}, 'fast');
				}
				return flag;
			}
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
			function saveCaseInfo() {
				var flag = nullWarning();
				if(!flag) return;
				submitData();
				var index = top.layer.load(2);
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
			        		var caseId = "${caseInfoId}";
			        		var type = "${type}";
			        		if(caseId != '' && type == '') {
			        			tipSkip("${pageContext.request.contextPath}", caseInfoId,tag);
			        		} else {
			        			caseInfoId = data.id;
			        			skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer");
			        		}
			        	} else {
			        		top.layer.msg('保存失败，请稍后再试');
			        	}
			        }
			    });
			}
			//设置是否显示委托人
			$("input[name=isSite]").click(function(){
				var flag = $(this).val();
				if(flag == "1") {
					$(".prinNameDiv").hide();
				} else {
					$(".prinNameDiv").show();
				}
			})
			//拿到人员组
			function getUserData() {
				$.ajax({    
	                url : "${pageContext.request.contextPath}/webmaster/sys/user/findNotAdminAndRoot",                              
	                type : 'post',    
	                dataType : 'json',    
	                success : function(result) {
	             	   if(result.status == "1") {
	             		   var userList = result.userList;
	             		   if(userList != null && userList.length>0) {
	             			   for(var i=0; i<userList.length; i++) {
	             				  var userName = "<option value='" +userList[i].id +"'>" +userList[i].realName +"</option>";
	             				  var catchpoleName = "<option value='" +userList[i].cardId +"'>" +userList[i].realName +"</option>";
	             				  $("#userId").append(userName);
	             				  $("#catchpoleNo").append(catchpoleName);
	             			   }
	             			if(caseInfoId != '') {
	             				showData();
	             			}
	             		   }
	             	   }
	                },    
	        	});
			}
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
					if("市场调查举报上级移交".indexOf(caseSource) < 0){
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
				var caseId = '${caseInfoId}';
				var type = '${type}';
				var tagId='${tagId}';
				if(type != '') {
					window.location.href = url +"?type=${type}&caseInfoId=" +caseInfoId+"&tagId="+tagId;
				} else {
					if(caseId != '') {
						window.location.href = url +"?caseInfoId=" +caseInfoId+"&tagId="+tagId;
					} else {
						window.location.href = url +"?type=add&caseInfoId=" +caseInfoId+"&tagId="+tagId;
					}
				}
			}
			$("input[name='caseSource1']").change(function(){
				var value = $("input[name='caseSource1']:checked").val();
				if(value == "其它") {
					$("#other").show();
				} else {
					$("#other").hide();
				}
			})
			
			//判断案件编号是否已经存在
			$("#caseNo").blur(function(){
				isSame();
			})
			//判断案件编号是否已经存在
			function isSame() {
				if('${caseNo }' == $("#caseNo").val()) {
					return true;
				}
				flag = true;
				var caseNo = $("#caseNo").val();
				var regiNo = $("#caseNo").prev().text() +caseNo +"号";
				$.ajax({    
	                url : "${pageContext.request.contextPath}/webmaster/caseInfo/isSame",      
	                data: {"regiNo": regiNo},
	                type : 'post',
	                async: false,
	                dataType : 'json',
	                success : function(result) {
	             	   if(result.status == "1") {
	             		   if(result.isSame == true) {
	             			  overlyTipsLayer("案件编号已经存在", "#FF4500", 3000, 4, $("#caseNo"), true);
	             			  flag = false;
	             		   }
	             	   }
	                },    
	        	});
				return flag;
			}
		</script>
	</body>
</html>