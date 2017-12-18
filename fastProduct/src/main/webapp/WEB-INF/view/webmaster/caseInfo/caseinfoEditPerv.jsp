<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-涉案证据登记</title>
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
		<style type="text/css">
			.dropdown-menu {
				width: 92% !important;
				overflow: scroll;
    			max-height: 200px;
			}
			.dropdown-menu li {
				display:block !important;
				margin: 0 !important;
			}
			.typeaheadLi {
			}
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<form onsubmit="return false;" id="myForm">
		<input type="hidden" name="caseInfoId" id="caseInfoId" value="${caseInfoId }">
		<input type="hidden" name="id" value="${pers.id }">
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="row">
					    <div class="col-md-12">
					        <div class="tabbable-line boxless tabbable-reversed">
					            <ul class="nav nav-tabs">
					                <li class="">
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase')"> 案件信息登记 </a>
					                </li>
					                <li class="active">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="true"> 涉案人员登记 </a>
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
					                
					                
					                <c:if test="${((tagId != null && tagId!='') || type==null) && caseInfoId!=null }">
					                	<li class="skipModel">
						                  <!--  <a href="#tab_4" onclick="skipInspect('${pageContext.request.contextPath}', '${caseInfoId}')" class="c-btn-border-1x c-btn-green-turquoise">文书预览</a> --> 
						                <button type="button" onclick="skipInspect('${pageContext.request.contextPath}','${tag }', '${caseInfoId}')" class="btn btn-sm green btn-outline ">文书预览</button>
						                </li>
					                </c:if>
					            </ul>
					            <div class="tab-content">
					                <div class="tab-pane active" id="tab_1">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>涉案人员 </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <!-- BEGIN FORM-->
						                       <div action="#" class="form-horizontal">
						                           <div class="form-body">
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4"><span style="color:red; float: right">*</span>当事人</label>
						                                           <div class="col-md-8">
						                                          	 	<div class="input-group" style="width:100%; float:left">
						                                          	 	 	<input type="hidden" name="perRespon" id="perRespon" value="${pers.perRespon }">
						                                               		<input type="text" id="search" value="${pers.perRespon }" maxlength="32" class="form-control" autocomplete="off">
					                                                      	<span class="input-group-addon">按Enter键或鼠标选择</span>
						                                                </div>
						                                              
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">案发地点</label>
						                                           <div class="col-md-8">
						                                               <input type="text" id="premises" name="premises" value="${pers.premises }" class="form-control"/>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">性别</label>
						                                           <div class="col-md-8">
							                                           <div class="mt-radio-inline">
						                                                    <label class="mt-radio mt-radio-outline" style="margin-bottom: 0;">
						                                                        <input type="radio" name="sex" value="1" checked="checked"> 男
						                                                        <span></span>
						                                                    </label>
						                                                    <label class="mt-radio mt-radio-outline" style="margin-bottom: 0;">
						                                                        <input type="radio" name="sex" value="0"> 女
						                                                        <span></span>
						                                                    </label>
						                                                </div>
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">年龄</label>
						                                           <div class="col-md-8">
						                                               <input type="number" name="age" id="age" value="${pers.age }" class="form-control" oninput="if(value.length>5)value=value.slice(0,5)" >
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">职业</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="jobs" id="jobs" value="个体户" maxlength="20" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">民族</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="nation" id="nation" value="${pers.nation }" maxlength="100" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">身份证号码</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="idCard" id="idCard" value="${pers.idCard }" maxlength="32" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">身份证地址</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="idCardAddr" id="idCardAddr" value="${pers.idCardAddr }" maxlength="255" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">联系电话</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="phone" id="phone" value="${pers.phone }" maxlength="100" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">当事人电话</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="partyPhone" id="partyPhone" value="${pers.partyPhone }" maxlength="32" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">企业名称</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="corpName" id="corpName" value="${pers.corpName }" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">许可证号</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="licNo" id="licNo" value="${pers.licNo }" maxlength="32" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">零售户编号</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="retailCode" id="retailCode" value="${pers.retailCode }" maxlength="32" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">稽查部门</label>
						                                           <div class="col-md-8">
						                                               <input type="text" name="audiDept" id="audiDept" value="${pers.audiDept }" maxlength="64" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                           </div>
						                           <div class="form-actions right">
					                                    <button type="button" class="btn default" id="goBack">返回</button>
					                                    <button type="button" class="btn green-turquoise" id="saveBtn">
					                                        <i class="fa fa-check"></i> 保存</button>
					                                </div>
						                       </div>
						                       <!-- END FORM-->
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
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/caseInfo.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap3-typeahead.min.js" type="text/javascript"></script>

		<script type="text/javascript">
			var caseInfoId = "${caseInfoId}";
			var tagId = '${tagId}';
			var tag='${tag}';
			init();
			//初始化数据
			function init() {
				var flag = isInfo("${pageContext.request.contextPath}", caseInfoId,tagId);
				if(!flag) return;
				showData();
				typeahead();
			}
			//修改时回显数据
			function showData() {
				var sex = "${pers.sex}";	//回显性别
				$("input[name='sex'][value='" +sex +"']").attr("checked",true); 
				var jobs = "${pers.jobs}";	//回显工作
				if(jobs == "") {
					$("#jobs").val("个体户");
				} else {
					$("#jobs").val(jobs);
				}
				var perRespon = "${pers.perRespon}";
				var licNo = "${pers.licNo}";		//回显当事人
				if(perRespon!='' && licNo!='') {
					var search = perRespon +"(" +licNo +")";
					$("#search").val(search);
				}
				if("${pers.age}" == "0") {
					$("#age").val("");
				}
			}
			//下拉搜索插件
			function typeahead() {
				var lic = "${lics}";
				lic = lic.substr(1, lic.length-2);
				lics = lic.split(",");
				$('#search').typeahead({
					source: lics,
					items: 999,
					updater: function (item) {
						setValue(item);
						return item;
					}
				})
			}
			//保存
			$("#saveBtn").click(function(){
				var flag = notNull("search", "当事人不能为空");
				if(!flag) return;
				saveData();
				var index = top.layer.load(2);
				$.ajax({
			        cache: true,
			        type: "POST",
			        url:"${pageContext.request.contextPath}/webmaster/caseInfo/saveEditPer",
			        data:$('#myForm').serialize(),// 你的formid
			        async: false,
			        dataType:"JSON",
			        error: function(request) {
			        	top.layer.msg("Connection error");
			        },
			        success: function(data) {
			        	top.layer.close(index);
			        	if(data.status == "1") {
			        		var type = "${type}";
			        		if(type != "") {
			        			$("#caseInfoId").val(data.caseInfoId);
				        		caseInfoId = data.caseInfoId;
				       			skip("${pageContext.request.contextPath}/webmaster/caseInfo/CaseCigar");
			        		} else {
			        			tipSkip("${pageContext.request.contextPath}", caseInfoId,tag);
			        		}
			        	} else {
			        		top.layer.msg('保存失败，请稍后再试');
			        	}
			        }
			    });
			});
			//保存前设置数据
			function saveData() {
				var perRespon = $("#search").val().trim();
				if(perRespon != '') {
					var site = perRespon.indexOf("(");
					if(site != -1) {
						perRespon = perRespon.substr(0, site);
					}
					$("#perRespon").val(perRespon);
				}
				if($("#age").val() == "") {
					$("#age").val(0);
				}
			}
			//拿到当事人信息
			function setValue(item) {
				if(item=="" && item==null) return;
				var left = item.indexOf("(");
				var right = item.indexOf(")");
				item = item.substr(left+1, right-left-1);
				$.ajax({
			        type: "POST",
			        url: "${pageContext.request.contextPath}/webmaster/caseInfo/getLicValue",
			        data: {"licNo": item},
			        dataType:"JSON",
			        error: function(request) {
			        	top.layer.msg("Connection error");
			        },
			        success: function(data) {
			        	if(data.status == "1") {
			        		var lic = data.lic;
			        		$("#premises").val(lic.premises);
			        		$("input[name='sex'][value='" +countSex(lic.idCard) +"']").attr("checked",true); 
			        		$("#age").val(countAge(lic.idCard));
			        		$("#idCard").val(lic.idCard);
			        		$("#phone").val(lic.phone);
			        		$("#corpName").val(lic.corpName);
			        		$("#licNo").val(lic.licNo);
			        		$("#retailCode").val(lic.retailCode);
			        		$("#audiDept").val(lic.audiDept);
			        	} 
			        }
			    });
			}
			//计算年龄
			function countAge(idCard) {
				idCard = idCard.trim();
				if(idCard=="" && idCard==null) return;
				var date = new Date();
				var year = date.getFullYear();
				var age = 0;
				if(idCard.length == 15) {
					idCard = idCard.substr(6,2);
					age = year - idCard - 1900;
				} else if (idCard.length == 18) {
					idCard = idCard.substr(6,4);
					age = year - idCard;
				}
				return age;
			}
			//计算性别
			function countSex(idCard) {
				if(idCard=="" && idCard==null) return;
				var sex = 0;//0为女，1为男
				if(idCard.length == 15) {
					idCard = idCard.substr(idCard.length-1);
				} else if (idCard.length == 18) {
					idCard = idCard.substr(idCard.length-2,1);
				}
				if(idCard%2 == 1) {
					sex = 1;
				}
				return sex;
			}
			$("#goBack").click(function(){
				window.history.back();
			})
			function skip(url) {
				var tagId='${tagId}';
				if('${type}' != '') {
					window.location.href = url +"?type=${type}&caseInfoId=" +caseInfoId+"&tagId="+tagId;
				} else {
					window.location.href = url +"?caseInfoId=" +caseInfoId+"&tagId="+tagId;
				}
			} 
			
			
			 $(document).on("change", "input[name='idCard']", function(){
				//获取输入身份证号码 
				var UUserCard = $(this).val(); 
				  var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
				if(reg.test(UUserCard) === true){
					//获取出生日期 
					UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14); 
					/* //获取性别 
					if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) { 
					alert("男"); 
					//是男则执行代码 ... 
					} else { 
					alert("女"); 
					//是女则执行代码 ... 
					}  */
					//获取年龄 
					var myDate = new Date(); 
					var month = myDate.getMonth() + 1; 
					var day = myDate.getDate(); 
					var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1; 
					if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) { 
					age++; 
					} 
					$("input[name='age']").val(age);
					
				}else{
					top.layer.msg("身份证格式有误！");
					 $("input[name='age']").val("");
				}
				
			
			 });
		</script>
	</body>

</html>