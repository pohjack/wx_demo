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
					                    <a href="#tab_0" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toHisView')"> 案件信息</a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toPervView')"> 涉案人员 </a>
					                </li>
					                <li class="active">
					                    <a href="#tab_2" data-toggle="tab" aria-expanded="true"> 涉案卷烟 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_4" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toNonDetail')">非烟条码明细 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_3" data-toggle="tab" aria-expanded="false" onclick="skip('${pageContext.request.contextPath}/webmaster/caseInfo/toDenceView')"> 涉案证据 </a>
					                </li>
					                
					                
					                <c:if test="${(tagId!=null || type==null) && caseInfoId!=null }">
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
						                            <div class="table-toolbar">
					                                       <div class="row">
					                                           <div class="col-md-6" style="width:100%">
					                                              
					                                               <div class="btn-group">
					                                                   <button id="" class="btn sbold green" data-toggle="modal" onclick="skip('${pageContext.request.contextPath}/webmaster/caseCigar/toAccount')">进出存帐预览
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
					                                               
					                                              
					                                            </tr>
					                                        </thead>			                                  
					                                        <tbody id="selectCigar">					                                            
					                                        </tbody>
					                                     
					                                    </table>
					                                   
						                           </div>
						                           <div  class="form-actions right">
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
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-fileinput.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
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
		function search2(){
		 	table2.ajax.reload();
		}
		function setTotalValue(value, placeNumber, jqThis) {
			var tr =  jqThis.parent();
			tr.find("td:eq(" +placeNumber +")").text(value);
		}
		showCigar();
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
						{"mData" : null}, 
						{"mData" : null}, 
						{"mData" : 'totalValue'},
						{"mData" : null},
						{"mData" : 'unit'},
						{"mData" : 'inspectNum'},
						{"mData" : 'returnNum'},
						{"mData" : null},
						{"mData" : null},
						
					],
					columnDefs: [//和aoColums类似，但他可以给指定列附近爱属性
                        {"className": "center", "aTargets": [0,1,2,3,4,5,6,7,8,9]},
                    ],
					"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 	
						var secondTDHtml = iDisplayIndex+1;	
						$('td:eq(0)', nRow).html("<span data-cigarId=" +aData.cigarId +" class='editInput'>" +secondTDHtml +"</span>");
						$('td:eq(3)', nRow).html("<span data-name='retailPrice'>" +aData.retailPrice +"</span>");
						$('td:eq(4)', nRow).html("<span data-name='number'>" +aData.number +"</span>");
						$('td:eq(6)', nRow).html("<span data-name='preQualitative'>" +aData.preQualitative +"</span>");
						$('td:eq(8)', nRow).html("<span data-name='inspectNum'>" +aData.inspectNum +"</span>");
						$('td:eq(9)', nRow).html("<span data-name='returnNum'>" +aData.returnNum +"</span>");
						$('td:eq(10)', nRow).html("<span data-name='inspectResult'>" +aData.inspectResult +"</span>");
						$('td:eq(11)', nRow).html("<span data-name='cigarAttr'>" +aData.cigarAttr +"</span>");
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
	
		$("#goBack").click(function(){
			window.history.back();
		})
		function skip(url) {
			var tagId='${tagId}';
			window.location.href = url +"?caseInfoId=" +caseInfoId+"&tagId="+tagId;
		}
		//跳转到文书模板
		function skipInspect(tip,tag, caseId) {
			window.location.href = tip +"/docTemplate/"+tag+"&caseId=" +caseId;
		}
		</script>
	</body>

</html>