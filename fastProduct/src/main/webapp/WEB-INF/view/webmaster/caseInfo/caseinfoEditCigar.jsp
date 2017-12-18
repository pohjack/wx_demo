<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-涉案人员登记</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.dataTables_scrollHeadInner {
				width:100% !important;
			}
			.dataTables_scrollHeadInner table {
				width: 100% !important;
			}
			div.dataTables_scrollBody table {
				width: 100% !important;
			}
			.center {
				text-align: center;
			}
		</style>
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
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase')"> 案件信息登记 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toEditPer')"> 涉案人员登记 </a>
					                </li>
					                <li class="active">
					                    <a href="#tab_2" data-toggle="tab" aria-expanded="true"> 涉案卷烟登记</a>
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
					                <div class="tab-pane active" id="tab_2">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>涉案卷烟 </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <!-- BEGIN FORM-->
						                      
						                           <div class="form-body">
						                               <div class="table-toolbar" style="margin-bottom:10px">
					                                       <div class="row">
					                                           <div class="col-md-6" style="width:100%">
					                                               <div class="btn-group">
					                                                   <button id="" class="btn sbold green" data-toggle="modal" onclick="skip('${pageContext.request.contextPath}/webmaster/caseCigar/toAccount')">进出存帐预览
					                                                   </button>
					                                               </div>
					                                               
					                                           
					                                               <div class="btn-group">
					                                                   <button id="sample_editable_1_new" class="btn sbold green" data-toggle="modal" onclick="toAdd()"> 添加涉案卷烟
					                                                       <i class="fa fa-plus"></i>
					                                                   </button>
					                                               </div>
					                                               <div style="float: right;">
                                   	 									<shiro:hasPermission name='case:edit'>
                                     										<a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/template/exportExcel2?modelNo=33&caseId=${caseInfoId}">
                                            										<i class="fa fa-table"></i> 导出数据 </a>
                                    									</shiro:hasPermission>
                                    								</div>
					                                               
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <table class="table table-striped table-bordered table-hover table-checkable order-column" style="table-layout: fixed" id="cigarTable2" >
					                                        <thead>
					                                            <tr>
					                                                <th style="width:8%;text-align:center;">序号</th>
					                                                <th style="width:9%;text-align:center;">条形码</th>
					                                                <th style="width:10%;text-align:center;">卷烟名称</th>
					                                                <th style="width:8%;text-align:center;">零售价</th>
					                                                <th style="width:7%;text-align:center;">数量</th>
					                                                <th style="width:8%;text-align:center;">总值</th>
					                                                <th style="width:7%;text-align:center;">初步定性</th>
					                                                <th style="width:7%;text-align:center;">单位(条)</th>
					                                                <th style="width:7%;text-align:center;">送检数量</th>
																	<th style="width:7%;text-align:center;">返还数量</th>
					                                                <th style="width:7%;text-align:center;">检查结果</th>
					                                                <th style="width:7%;text-align:center;">卷烟属性</th>
					                                                <th style="width:8%;text-align:center;">操作</th>
					                                            </tr>
					                                        </thead>			                                  
					                                        <tbody id="selectCigar">					                                            
					                                        </tbody>
					                                     
					                                    </table>
					                                   
						                           </div>
						                           <div  class="form-actions right">
					                                    <button type="button" class="btn default" id="goBack">返回</button>
					                                    <button type="button" onclick="save()" class="btn green-turquoise">
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
		<form id="form" onsubmit="return false;" method="post" >	
			<input type="hidden" name="caseInfoId" id="caseInfoId" value="${caseInfoId }">
			<div id="formDiv"></div>	
		</form>		                                     

		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/datatable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/caseInfo.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

		<script type="text/javascript">
		var table2;
		var caseInfoId = "${caseInfoId}";
		var tagId='${tagId}';
		function search2(){
		 	table2.ajax.reload();
		}
		var tag="${tag}";
		init();
		function init() {
			var flag = isInfoAndPerv("${pageContext.request.contextPath}", caseInfoId,tagId);
			if(!flag) return;
			showCigar();
		}
		function showCigar(){
			table2 = $("#cigarTable2").DataTable({
					"bLengthChange": false,  //去掉每页显示多少条数据方法
					"searching":false,
					"ordering": false, // 禁止排序
					"bStateSave": false,      //本地缓存
			       "bProcessing": false,     // 是否显示取数据时的那个等待提示    
			       "bServerSide": true,      //这个用来指明是通过服务端来取数据 
			       "sAjaxSource": "${pageContext.request.contextPath}/webmaster/caseCigar/getPagingList",      //这个是请求的地址    
			       "fnServerData": retrieveData2,       // 获取数据的处理函数    
			       "order": [[ 2, "desc" ]],
			       "pagingType":   "full_numbers",
			       "oLanguage" : { // 汉化
			    	   "sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"
					},
				   "bAutoWidth": false,                    //不自动计算列宽度   
			       "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空      从0开始
			            {"mData" : null}, 
			            {"mData" : 'barCode'}, 
						{"mData" : 'name'}, 
						{"mData" : null,
							"className": "edit center"}, 
						{"mData" : null,
							"className": "edit center"}, 
						{"mData" : 'totalValue'},
						{"mData" : null,
							"className": "editSelect center"},
						{"mData" : 'unit'},
						
						{"mData" : 'inspectNum',"className": "edit center"},
						{"mData" : 'returnNum',"className": "edit center"},
						
						{"mData" : null,
							"className": "editSelect center"},
						{"mData" : null,
							"className": "editSelect2 center"},
						{"mData" : null},
					],
					columnDefs: [//和aoColums类似，但他可以给指定列附近爱属性
                        {"className": "center", "aTargets": [0,1,2,3,4,5,6,7,8,9]},
                    ],
					"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 	
						var secondTDHtml = iDisplayIndex+1;	
						$('td:eq(0)', nRow).html("<span data-cigarId=" +aData.cigarId +" class='editInput'>" +secondTDHtml +"</span>");
						/* $('td:eq(1)', nRow).html("<span data-name='name'>" +aData.name +"</span>"); */
						$('td:eq(3)', nRow).html("<span data-name='retailPrice'>" +aData.retailPrice +"</span>");
						$('td:eq(4)', nRow).html("<span data-name='number'>" +aData.number +"</span>");
						$('td:eq(6)', nRow).html("<span data-name='preQualitative'>" +aData.preQualitative +"</span>");
						$('td:eq(8)', nRow).html("<span data-name='inspectNum'>" +aData.inspectNum +"</span>");
						$('td:eq(9)', nRow).html("<span data-name='returnNum'>" +aData.returnNum +"</span>");
						$('td:eq(10)', nRow).html("<span data-name='inspectResult'>" +aData.inspectResult +"</span>");
						$('td:eq(11)', nRow).html("<span data-name='cigarAttr'>" +aData.cigarAttr +"</span>");
						//$('td:eq(10)', nRow).html("<button class='btn btn-danger removeBtn' data-id=" +aData.id +">删除</button>");
						$('td:eq(12)', nRow).html("<button class='btn btn-sm red btn-outline filter-cancel removeBtn' data-id=" +aData.id +"><i class='fa fa-remove'></i>删除</button>");
						return nRow;
					},
					initComplete : function() {
					}
			});
		 }
		 function retrieveData2( sSource,aoData, fnCallback) {		
				var searchDate= '{caseInfoId:"' +caseInfoId +'"}';       						
				aoData.push( { "name": "searchDate", "value": ""+searchDate+""}       			
							);//添加自己的额外参数		
		     $.ajax({    
		         url : sSource,                              //这个就是请求地址对应sAjaxSource    
		         data : {"aoData":JSON.stringify(aoData)},   //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
		         type : 'post',    
		         dataType : 'json',    
		         async : false,    
		         success : function(result) {    
		             fnCallback(result);                     //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
		         },    
		         error : function(msg) {    
		         }    
		     });    
		 }
		//拿到当前所拥有卷烟的id
		function getCaseCigarId() {
			var CaseCigarIds = new Array();;
			$("#cigarTable2 tr td:nth-child(1)").each(function(){
				CaseCigarIds.push($(this).find("span").attr("data-cigarId")); 
			});
			return CaseCigarIds;
		}
		 
		//单元格变成输入框
		$(document).on("click" ,"tbody .edit" ,function(){
			var c = $(this).find("input");
			if(c.length == 0) {
				var value = $(this).text();
				var name = $(this).find("span").attr("data-name");
				if(name=="number") {
					$(this).html("<input class='editInput form-control' id='editInput' name='" +name +"' data-value='" +value +"' min ='0'  type='number' oninput='if(value.length>5)value=value.slice(0,5)' />");
				}/* else  if(name=="name"){
					$(this).html("<input class='editInput form-control' id='editInput' name='" +name +"' data-value='" +value +"'  />");
				} */else  if(name=="retailPrice"){
					$(this).html("<input class='editInput form-control' id='editInput' name='" +name +"' data-value='" +value +"' onkeyup='isDouble(this)' />");
				}else if(name=="returnNum"){
					var max = $(this).prev().find("span").text() ;   //返还数量不能大于送检数量
					$(this).html("<input class='editInput form-control' id='editInput' name='" +name +"' data-value='" +value +"' min ='0' max='"+max+"'  type='number' oninput='if(value.length>5)value=value.slice(0,5)' onkeyup='check(this,"+max+")'/>");
				}else{
					var max = $(this).parent().find("td").eq(4).text();   //送检数量不能大于数量
					$(this).html("<input class='editInput form-control' id='editInput' name='" +name +"' data-value='" +value +"' min ='0' max='"+max+"'  type='number' oninput='if(value.length>5)value=value.slice(0,5)' onkeyup='check(this,"+max+")' />");
				}
				//送检数量要大于等于返还数量 undo 
				$(".editInput").val(value);
				$(".editInput").focus();
			}
		});
		
		//校验
		function check(obj,max){
			 var str = obj.value;  
			 if (str == "")  
			        return;  
			 if (/[^0-9]/g.test(str)) {  
			        obj.value = str.substr(0, str.length - 1);  
			 }  
			 if(str>max){
				 obj.value= max ;
			 }
		}
		function setTotalValue(value, placeNumber, jqThis) {
			var tr =  jqThis.parent();
			tr.find("td:eq(" +placeNumber +")").text(value);
		}
		//输入框变成单元格
		$(document).on("blur", "tbody .edit" ,function(){
			var value = $("#editInput").val();
			var name = $(this).find("input").attr("name");
			var oldValue = $("#editInput").attr("data-value");
			$(this).html("<span data-name=" +name +">" +value +"</span>");
			if(value == oldValue) return;//如果不改变值，则不触发保存事件
			var id = $(this).parent().find("td:last button").attr("data-id");
			var sum = 0;
			var names =null;
			var values=null;
			if(name == "number") {//获取总值
				var price = $(this).prev().find("span").text();
				sum = price*value;
				sum = sum.toFixed(2);
				setTotalValue(sum, 5, $(this));
				names = [name, "totalValue"];
				values = [value, sum];
			}/*  else if(name=="name") {
				names = [name];
				values = [value];
				
			} */else if(name=="retailPrice") {
				var price = $(this).next().find("span").text();
				sum = price*value;
				sum = sum.toFixed(2);
				setTotalValue(sum, 5, $(this));
				names = [name, "totalValue"];
				values = [value, sum];
			}else {
				names=[name];
				values=[value];
			}
			updateCaseCigar(id, names, values);
		});
		//单元格变成下拉框
		$(document).on("click", "tbody .editSelect", function(){
			var c = $(this).find("select");
			if(c.length == 0) {
				var value = $(this).text();
				var name = $(this).find("span").attr("data-name");
				if(name=="inspectResult"){
					$(this).html("<select class='editSel form-control' name='" +name +"' data-value='" +value +"'><option value=''></option><option value='非'>非</option><option value='私'>私</option><option value='假'>假</option></select>");
				}else{
					$(this).html("<select class='editSel form-control' name='" +name +"' data-value='" +value +"'><option value='非'>非</option><option value='私'>私</option><option value='假'>假</option></select>");
				}
				$(".editSel").val(value);
				$(".editSel").focus();
			}
		});
		//下拉框变成单元格
		$(document).on("blur", "tbody .editSelect", function(){
			var value = $(".editSel").val();
			var id = $(this).parent().find("td:last button").attr("data-id");
			var name = $(this).find("select").attr("name");
			var oldValue = $(".editSel").attr("data-value");
			$(this).html("<span data-name=" +name +">" +value +"</span>");
			//if(value == oldValue) return;
			//updateCaseCigar(id, name, value);
		});
		//单元格变成下拉框2
		$(document).on("click", "tbody .editSelect2", function(){
				var c = $(this).find("select");
				if(c.length == 0) {
					var value = $(this).text();
					var name = $(this).find("span").attr("data-name");
					$(this).html("<select class='editSel form-control' name='" +name +"'><option value=''></option><option value='假国'>假国</option><option value='假外'>假外</option> <option value='专供'>专供</option> <option value='外国'>外国</option></select>");
					$(".editSel").val(value);
					$(".editSel").focus();
				}
		});
		//下拉框变成单元格2
		$(document).on("blur", "tbody .editSelect2" ,function(){
			var value = $(".editSel").val();
			var id = $(this).parent().find("td:last button").attr("data-id");
			var name = $(this).find("select").attr("name");
			$(this).html("<span data-name=" +name +">" +value +"</span>");
			updateCaseCigar(id, name, value);
		}); 
		//修改数据
		function updateCaseCigar(id, name, value) {
			/* if(value[0] == "") {
				search2();
				return;
			}; */
			$.ajax({
		         url : "${pageContext.request.contextPath}/webmaster/caseCigar/updateCaseCigar",
		         data : {
		        	 "id": id,
		        	 "name": name,
		        	 "value": value,
		        	 "caseId":caseInfoId
		        	 
		         },
		         traditional: true,
		         type : 'post',
		         dataType : 'json',
		         success : function(data) {
		        	 if(data.status != "1") {
		        		 top.layer.msg('数据异常，请稍后再试！');
		        		 search2();
		        	 }
		         }, 
		         error : function(msg) {    
		        	 top.layer.msg('未知错误，请稍后再试！');
	        		 search2();
		         }    
		     });    
		}
		//删除数据
		$(document).on("click", ".removeBtn", function(){
			var id = $(this).attr("data-id");
			top.layer.confirm('是否确定删除，一旦删除无法还原', {
			    btn: ['确定', '取消']
			  },function() {
				  $.ajax({
			         url : "${pageContext.request.contextPath}/webmaster/caseCigar/removeCaseCigar",
			         data : {
			        	 "id": id,
			        	 "caseId":caseInfoId
			        },
			         type : 'post',
			         dataType : 'json',
			         success : function(data) {
			        	 if(data.status == "1") {
			        		top.layer.msg('删除成功', {icon: 1});
							var length = $("tbody tr").length;
							if(length > 1) {
								table2.draw(false);
							} else {
								table2.draw(true);
							}
			        	 } else {
				        	layer.msg('删除失败');
			        	 }
			         },    
			         error : function(msg) {    
			         }    
			      });
			  });
		});
		/* $(document).on("change", "input[name='name']", function(){
			var id = $(this).parent().parent().parent().find("td:last button").attr("data-id");
			var nameS=$(this).parent().parent().parent().find("td:eq(1)").find("span");
			var value;
			var name;
			value = [$(this).val()];
			name = [nameS.attr("data-name")];
			updateCaseCigar(id, name, value);
		}) */
		$(document).on("change", "select", function(){
			var id = $(this).parent().parent().find("td:last button").attr("data-id");
			var value;
			var name;
			if($(this).attr("name") == "preQualitative") {
				var inspectResult = $(this).parent().parent().find("td:eq(10)").find("span");
				var cigarAttr = $(this).parent().parent().find("td:eq(11)").find("span");
				inspectResult.text($(this).val());
				/* if($(this).val() == '私') {
					cigarAttr.text("假外");
				} else {
					cigarAttr.text("");
				} */
				value = [$(this).val(), $(this).val(), cigarAttr.text()];
				name = [$(this).attr("name"), inspectResult.attr("data-name"), cigarAttr.attr("data-name")];
			} else if($(this).attr("name") == "inspectResult") {
				var cigarAttr = $(this).parent().next().find("span");
				/* if($(this).val() == '私') {
					cigarAttr.text("假外");
				} else {
					cigarAttr.text("");
				} */
				value = [$(this).val(), cigarAttr.text()];
				name = [$(this).attr("name"), cigarAttr.attr("data-name")];
			} else if($(this).attr("name") == "cigarAttr") {
				value = $(this).val();
				name = $(this).attr("name");
			} else {
				return;
			}
			updateCaseCigar(id, name, value);
		})
		//验证是否是double，并且只能保留2个小数点
		function isDouble(obj){
			obj.value = obj.value.replace(/[^\d.]/g,"");
			obj.value = obj.value.replace(/^\./g,"");
			obj.value = obj.value.replace(/\.{2,}/g,".");
			obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
			obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
		}
		$("#goBack").click(function(){
			window.history.back();
		})
		function skip(url) {
			
			if('${type}' != '') {
				
				window.location.href = url +"?type=${type}&caseInfoId=" +caseInfoId+"&tagId="+tagId;
			} else {
				window.location.href = url +"?caseInfoId=" +caseInfoId+"&tagId="+tagId;
			}
		}
		//添加
		function toAdd() {
			/* if(caseInfoId!=""&&caseInfoId!=null){ */
			var url = "${pageContext.request.contextPath}/webmaster/caseInfo/toAddCigar?caseInfoId=" +caseInfoId;
			mediaLayer2("添加涉案卷烟", [ 0.8, '#393D49' ], 1, false,url, 1000, 760, 2);
			/* }else{
	 			layer.msg('案件信息为空，请先填写案件信息！');
	 		} */
		}
		
	 	function mediaLayer2(title, shade, closeBtn, shadeClose, url, width, height, num){
	 		  top.layer.open({
	 		  type: 2, //2表示弹出层是iframe
	 		  title: title,
	 		  area: [width + 'px', height + 'px'],
	 		  maxmin: true, //是否显示最大化最小化按钮
	 		  shade: shade,
	 		  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
	 		  shift: num,
	 		  closeBtn: closeBtn,
	 		  shadeClose: shadeClose,
	 		  content: [url], 
	 		});
	 	}
	 	function setCaseInfoId(infoId) {
	 		caseInfoId = infoId;
	 	}
	 	
	 	function save() {
	 		var type = '${type}';
	 		if(type == '') {
    			tipSkip("${pageContext.request.contextPath}", caseInfoId,tag);
	 		} else {
	 			window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toEditNonDetail?type=${type}&caseInfoId=" +caseInfoId;
	 		}
	 	}
		</script>
	</body>

</html>