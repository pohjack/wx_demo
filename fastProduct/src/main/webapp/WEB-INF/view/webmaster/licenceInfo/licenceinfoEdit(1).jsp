<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-许可证编辑</title>
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
        <link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
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
			.col-md-8 {
				margin-bottom: 15px
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<form onsubmit="return false;" id="myForm">
		<input type="hidden" name="id" id="id" value="${obj.id }">
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="row">
					    <div class="col-md-12">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption"><i class="fa fa-plus-square"></i><c:if test="${not empty obj.id}" >编辑</c:if><c:if test="${empty obj.id}" >新建</c:if>许可证信息 </div>
						                   </div>
						                   <div class="portlet-body form">
					                           <div class="form-body">
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">负责人(经营者)姓名</label>
					                                           <div class="col-md-8">
					                                            	<input type="text" name="perRespon" id="perRespon" value="${obj.perRespon }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">许可证号码</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="licNo" id="licNo" value="${obj.licNo }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">零售户编号</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="retailCode" id="retailCode" value="${obj.retailCode }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">工商营业执照编号</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="bizLicNo" id="bizLicNo" value="${obj.bizLicNo }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">发证单位</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="issueUnit" id="issueUnit" value="${obj.issueUnit }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">企业名称</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="corpName" id="corpName" value="${obj.corpName }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">经济性质</label>
					                                           <div class="col-md-8">
					                                                    <label class="mt-radio mt-radio-outline">
				                                                        <input type="radio" name="econNatu" value="集体企业" <c:if test="${obj.econNatu == '集体企业'}">checked="checked"</c:if>>集体企业
				                                                        <span></span>
				                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
				                                                        <input type="radio" name="econNatu"  value="个人独资" <c:if test="${obj.econNatu == '个人独资'}">checked="checked"</c:if>>个人独资
				                                                        <span></span>
				                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
				                                                        <input type="radio" name="econNatu"  value="有限公司" <c:if test="${obj.econNatu == '有限公司'}">checked="checked"</c:if>>有限公司
				                                                        <span></span>
				                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
				                                                        <input type="radio" name="econNatu"  value="国有企业" <c:if test="${obj.econNatu == '国有企业'}">checked="checked"</c:if>>国有企业
				                                                        <span></span>
				                                                    </label>
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">证件号码</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="idCard" id="idCard" value="${obj.idCard }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
				                                                <label class="col-md-4 control-label">地段属性</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="siteAttr" id="siteAttr" value="${obj.siteAttr }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">联系电话</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="phone" id="phone" value="${obj.phone }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
				                                                <label class="col-md-4 control-label">经营范围</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="bizScope" id="bizScope" value="${obj.bizScope }" maxlength="20" class="form-control">
					                                           </div>
				                                            </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
				                                                <label class="col-md-4 control-label">经营业态</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="operaForms" id="operaForms" value="${obj.operaForms }" maxlength="20" class="form-control">
					                                           </div>
				                                            </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">特群类型</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="specType" id="specType" value="${obj.specType }"  maxlength="10" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">经营场所</label>
				                                                <div class="col-md-8">
					                                               <input type="text" name="premises" id="premises" value="${obj.premises }" maxlength="20" class="form-control">
				                                                </div>
				                                            </div>
					                                   </div>
					                               </div>
					                               <h3 class="form-section"></h3>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">许可证状态</label>
					                                           <div class="col-md-8">
						                                           <div class="mt-radio-inline">
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="licStatus" value="1" <c:if test="${obj.licStatus != '0'}">checked="checked"</c:if>> 有效
					                                                        <span></span>
					                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="licStatus" value="0" <c:if test="${obj.licStatus == '0'}">checked="checked"</c:if>> 无效
					                                                        <span></span>
					                                                    </label>
					                                                </div>
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">零售户状态</label>
					                                           <div class="col-md-8">
						                                           <div class="mt-radio-inline">
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="retailStatus" value="1" <c:if test="${obj.retailStatus != '0'}">checked="checked"</c:if>> 有效
					                                                        <span></span>
					                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="retailStatus" value="0" <c:if test="${obj.retailStatus == '0'}">checked="checked"</c:if>> 无效
					                                                        <span></span>
					                                                    </label>
					                                                </div>
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">发证日期</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="certDate" id="certDate" onfocus="WdatePicker({skin:'twoer'})" value="<fmt:formatDate value='${obj.certDate}' pattern='yyyy-MM-dd'/>" data-date-format="yyyy-mm-dd" class="form-control date-picker">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">有效日期</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="effeDate" id="effeDate" onfocus="WdatePicker({skin:'twoer'})" value="<fmt:formatDate value='${obj.effeDate}' pattern='yyyy-MM-dd'/>" data-date-format="yyyy-mm-dd" class="form-control date-picker">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">营业执照有效期</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="certDate" id="certDate" onfocus="WdatePicker({skin:'twoer'})" value="<fmt:formatDate value='${obj.certDate}' pattern='yyyy-MM-dd'/>" data-date-format="yyyy-mm-dd" class="form-control date-picker">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">稽查部门</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="audiDept" id="audiDept" value="${obj.audiDept }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                           </div>
					                           <div class="form-actions right">
					                           <button type="submit" class="btn green-turquoise" id="saveBtn"> 
				                                    	<i class="fa fa-check"></i> 保存
				                                    </button>
													<button type="button" class="btn default" onclick="window.history.back();">返回</button>
				                                    
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
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-fileinput.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		
      	<script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>  
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/select2.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/components-select2.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		
		<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>

		<script type="text/javascript">
		function submitClickOut(){
			if($("#perRespon").val() == ""){
				layer.tips('[负责人(经营者)姓名]不能为空', '#perRespon', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#licNo").val() == ""){
				layer.tips('[许可证号码]不能为空', '#licNo', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#retailCode").val() == ""){
				layer.tips('[零售户编号]不能为空', '#retailCode', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#bizLicNo").val() == ""){
				layer.tips('[工商营业执照编号]不能为空', '#bizLicNo', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#issueUnit").val() == ""){
				layer.tips('[发证单位]不能为空', '#issueUnit', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#corpName").val() == ""){
				layer.tips('[企业名称]不能为空', '#corpName', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#siteAttr").val() == ""){
				layer.tips('[地段属性]不能为空', '#siteAttr', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#phone").val() == ""){
				layer.tips('[联系电话]不能为空', '#phone', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#bizScope").val() == ""){
				layer.tips('[经营范围]不能为空', '#bizScope', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#operaForms").val() == ""){
				layer.tips('[经营业态]不能为空', '#operaForms', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#specType").val() == ""){
				layer.tips('[特群类型]不能为空', '#specType', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#premises").val() == ""){
				layer.tips('[经营场所]不能为空', '#premises', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#certDate").val() == ""){
				layer.tips('[发证日期]不能为空', '#certDate', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#effeDate").val() == ""){
				layer.tips('[有效日期]不能为空', '#effeDate', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#certDate").val() == ""){
				layer.tips('[营业执照有效期]不能为空', '#certDate', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			if($("#audiDept").val() == ""){
				layer.tips('[稽查部门]不能为空', '#audiDept', {
				  tips: [1, '#3595CC'],
				  time: 4000
				});
				return false;
			}
			return true;
		}
			$("#saveBtn").click(function(){
				var submitClick = submitClickOut();
				if(submitClick){
					$.ajax({
				        type: "POST",
				        url:"${pageContext.request.contextPath}/webmaster/licenceInfo/doSave",
				        data:$('#myForm').serialize(),// 你的formid
				        dataType:"JSON",
				        success: function(data) {
			        		if($("#id").val()=="") {
			        			top.layer.msg("保存成功");
			        		} else {
			        			top.layer.msg("修改成功");
			        		}
			        		window.location.href="${pageContext.request.contextPath}/webmaster/licenceInfo/toList";
				        },
				        error: function(request) {
			        		  layer.msg("Connection error\n");
				        }
				    });
				}
			});
			
			function skip(url) {
				window.location.href = url;
			}
		</script>
	</body>
</html>