<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-涉案卷烟登记</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<div class="clearfix"> </div>
		<div class="page-container">
			<div class="page-content-wrapper">
				<div class="page-content">
					<div class="row">
					    <div class="col-md-12">
					        <div class="tabbable-line boxless tabbable-reversed">
					            <ul class="nav nav-tabs">
					                <li class="">
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="false" onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase")'> 案件信息登记 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="false" onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer")'> 涉案人员登记 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_2" data-toggle="tab" aria-expanded="false" onclick='skip("${pageContext.request.contextPath}/webmaster/caseInfo/CaseCigar")'> 涉案卷烟登记 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_4" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditNonDetail')">非烟条码明细 </a>
					                </li>
					                <li class="active">
					                    <a href="#tab_3" data-toggle="tab" aria-expanded="true"> 涉案证据登记 </a>
					                </li>
					                
					                
					                <c:if test="${((tagId != null && tagId!='')  || type==null) && caseInfoId!=null }">
					                	<li class="skipModel">
						                  <!--  <a href="#tab_4" onclick="skipInspect('${pageContext.request.contextPath}', '${caseInfoId}')" class="c-btn-border-1x c-btn-green-turquoise">文书预览</a> --> 
						                <button type="button" onclick="skipInspect('${pageContext.request.contextPath}','${tag }', '${caseInfoId}')" class="btn btn-sm green btn-outline ">文书预览</button>
						                </li>
					                </c:if>
					            </ul>
					            <div class="tab-content">
					                <div class="tab-pane active" id="tab_3">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>涉案证据 </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <form action="#" class="form-horizontal">
												<div class="form-body">
													<div class="form-group">
														<label class="control-label col-md-3">许可证复印件</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_1' name=''
																data-type='1' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' />
														</div>
														<span>张</span>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">现场照片</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_7' name=''
																data-type='7' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' />
														</div>
														<span>张</span>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">店铺照片</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_5' name=''
																data-type='5' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' />
														</div>
														<span>张</span>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">涉案卷烟照片</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_6' name=''
																data-type='6' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' />
														</div>
														<span>张</span>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">涉嫌烟草条码明细表</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_2' name=''
																data-type='2' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' />
														</div>
														<span>张</span>
													</div>
													<div id="weiPer">
													<div class="form-group">
														<label class="control-label col-md-3">委托书</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_4' name=''
																data-type='4' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)'
																onchange='checkWPer()'/>
														</div>
														<span>张</span>
													</div>
													<div class="form-group">
														<label class="control-label col-md-3">委托人身份证</label>
														<div class="col-md-2">
															<input class='editInput form-control' id='file_3' name=''
																data-type='3' min='0' max='4' type='number'
																oninput='if(value.length>5)value=value.slice(0,5)'
																onkeyup='check(this,4)' 
																onchange='checkWPer()'/>
														</div>
														<span>张</span>
													</div>
													</div>
												</div>
												<div class="form-actions right">
						                                    <button type="button" class="btn default" id="goBack">返回</button>
						                                    <button type="button" class="btn green-turquoise" id="saveBtn">
						                              <i class="fa fa-check"></i> 完成</button>
						                                    
						                               </div>
						                       </form>
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
		<input type="hidden" value="${caseInfoId}"  id="caseInfoId">
		<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.ui.widget.js" type="text/javascript"></script>
	  	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.iframe-transport.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.validate.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/caseInfo.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
    
		<script type="text/javascript">
			//获取文件按钮单击事件，传入参数调用uploadFiles方法
		
			var caseId = "${caseInfoId}";
			var isSite = "${isSite}"
			var tagId = '${tagId}';
			var tag='${tag}';
			init();
			function init() { //初始化数据
				var flag = isInfoAndPerv("${pageContext.request.contextPath}",
						caseId,tagId);
				if (!flag)
					return;
				if (isSite == "1") {
					$(".entrust").remove();
				}
				getFile(); //初始化图片			
			}

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
															evidType : parseInt($(
																	this).data(
																	"type")),
															caseInfoId : caseId
														};
														myArray
																.push(editEvidence);
													}

												});
								$.ajax({
											url : "${pageContext.request.contextPath}/webmaster/caseEvidence/saveEvidence?caseId="
													+ caseId,
											type : 'post',
											dataType : 'json',
											data : JSON.stringify(myArray),
											contentType : "application/json",
											async : true,
											success : function(data) {
												if (data.result) {
													caseId = data.caseInfoId;
													top.layer
															.confirm(
																	'案件登记成功，是否跳转到文书模板或列表页面',
																	{
																		btn : [
																				'文书预览',
																				'案件查询列表',
																				'取消' ]
																	},
																	function(
																			index,
																			layero) {
																		top.layer
																				.close(index);
																		window.location.href = "${pageContext.request.contextPath}/docTemplate/"+tag+"&caseId="
																				+ caseId;
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
			//页面跳转
			function skip(url) {
				var tagId = '${tagId}';
				if ('${type}' != '') {

					window.location.href = url + "?type=${type}&caseInfoId="
							+ caseId + "&tagId=" + tagId;
				} else {
					window.location.href = url + "?caseInfoId=" + caseId
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

			
			function checkWPer(){
				$.ajax({
					url : "${pageContext.request.contextPath}/webmaster/caseInfo/isWPerWrite?caseInfoId="
							+ caseId,
					type : 'post',
					dataType : 'json',
					contentType : "application/json",
					async : true,
					success : function(data) {
				        	 if(data.status != "1") {
				        		 top.layer
									.confirm(
											'请先填写委托人！',
											{
												btn : ['确定','取消' ]
											},
											function(index,layero) {
												top.layer.close(index);
												skip("${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase");
											},
											function(){
												$("#file_4").val("");
												$("#file_3").val("");
												$("#weiPer").hide();
											}
											
									);
				        	 }
				         }, 
				});
				
			}
			// 通过异步 查询得到图片或文件 数组 json	
			function getFile() {
				$
						.ajax({
							url : "${pageContext.request.contextPath}/webmaster/caseEvidence/getFile",
							type : 'post',
							dataType : 'json',
							data : {
								"caseInfoId" : caseId
							},
							async : true,
							success : function(result) {
								for (var i = 1; i < 8; i++) {
									if (result[i + ''] != null) { //判断当前类型是否有图片
										for ( var j in result[i + '']) {
											$("#file_" + i).val(
													result[i + ''][j].evidNo);
										}
									}

								}
							}
						});
			}

			$("#goBack").click(function() {
				window.history.back();
			})
		</script>
	</body>

</html>