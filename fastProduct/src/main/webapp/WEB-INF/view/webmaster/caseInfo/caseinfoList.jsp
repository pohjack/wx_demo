<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-涉案信息查询</title>
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
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style>
		.toLong{
				text-overflow: ellipsis;
				 white-space: nowrap; 
				 overflow: hidden;
				 text-align: center;
			}
		</style>

	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content">
                    <!-- BEGIN PAGE TITLE-->
                    <div class="portlet box grey-cascade">
                         <div class="portlet-title" style="cursor:pointer;">
                             <div class="caption">
                                 <i class="fa fa-filter"></i>条件筛选<span style="color:yellow;">(点击此处进行条件筛选)</span></div>
                             <div class="tools">
                                 <a href="javascript:;" class="expand" data-original-title="" title=""> </a>
                             </div>
                         </div>
                         <div class="portlet-body form" style="display: none;">
                             <!-- BEGIN FORM-->
                             <form action="#" class="form-horizontal">
                                 <div class="form-body">
                                     <div class="row">
                                   <!--   <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">案件状态</label>
                                                 <div class="col-md-8">
                                                     <select id="caseSta" class="form-control">
                                                     	<option value="1">进行中</option>
                                                     	<option value="0">已结案</option>
                                                     	<option value="">全部</option>
                                                     </select>
                                                 </div>
                                             </div>
                                         </div> -->
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">案发时间</label>
                                                 <div class="col-md-8 timeDiv">
                                                     <!-- <input type="text" id="" class="form-control" placeholder="选择时间段"> -->
                                                     <div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd" id="datetimepicker1">
	                                                     <input type="text" id="startDate" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endDate\')||\'new Date()\'}',skin:'twoer'})"  class="form-control toDate" name="from">
	                                                     <span class="input-group-addon"> 至  </span>
	                                                     <input type="text" id="endDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}',skin:'twoer'})" class="form-control toDate" name="to">
	                                                  </div>
                                                 </div>
                                             </div>
                                         </div>
                                         <!--/span-->
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">当事人</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="perRespon" class="form-control">
                                                 </div>
                                             </div>
                                         </div>
                                         <!--/span-->
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">立案编号</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="regiNo" class="form-control">
                                                 </div>
                                             </div>
                                         </div>
                                         <!--/span-->
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">身份证号</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="idCard" class="form-control">
                                                 </div>
                                             </div>
                                         </div>
                                         <!--/span-->
                                         <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">案发地点</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="premises" class="form-control">
                                                 </div>
                                             </div>
                                         </div>
                                         <!-- <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">案件阶段</label>
                                                 <div class="col-md-8">
                                                     <select name="foo" class="form-control">
                                                         <option value="1">登记</option>
                                                         <option value="1">立案</option>
                                                     </select>
                                                 </div>
                                             </div>
                                         </div> -->
                                     </div>
                                 </div>
                                 <div class="form-actions" style="padding: 10px;">
                                    <div class="btn-set pull-right">
	                                    <button type="button" class="btn default reset">重置</button>
	                                    <button type="button" class="btn green-turquoise sureBtn">确定</button>
	                                   <!--  <button type="button" id="export" class="btn btn-primary">数据统计导出</button>
	                                    <button type="button" id="exportNon" class="btn btn-primary">非烟上报表导出</button> -->
	                                </div>
                                 </div>
                             </form>
                             <!-- END FORM-->
                         </div>
                     </div>
                    <!-- END PAGE TITLE-->
                    <div class="row">
                        <div class="col-md-12">
                            <!-- BEGIN EXAMPLE TABLE PORTLET-->
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption font-dark">
                                        <i class="icon-notebook font-dark"></i>
                                        <span class="caption-subject bold uppercase"> 案件列表</span>
                                    </div>
                                    <div class="actions">
                                    <shiro:hasPermission name='case:edit'>
                                        <a id="export" href="#" class="btn btn-default btn-sm"><i class="fa fa-table"></i> 数据统计导出</a>&nbsp;
	                                    <a id="exportNon" href="#" class="btn btn-default btn-sm"><i class="fa fa-table"></i> 非烟上报表导出</a>&nbsp;
                                     	<a href="${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase?tagId=2" class="btn btn-default btn-sm">
                                            <i class="fa fa-plus"></i> 新建案件 </a>
                                            
                                            
                                    </shiro:hasPermission>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <table style="table-layout: fixed" class="table table-striped table-bordered table-hover table-checkable order-column" id="caseInfoList">
                                        <thead>
                                            <tr>
                                                <th width="4%">序号</th>
                                                <th width="12%">立案编号</th>
                                                <th width="5%">当事人</th>
                                                <th width="12%">案发时间</th>
                                                <th width="9%">当事人电话</th>
                                                <th width="10%">联系电话</th>
                                                <th width="10%">案发地点</th>
                                                <th width="8%">身份证号</th>
                                                <th width="23%">操作</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                            <!-- END EXAMPLE TABLE PORTLET-->
                        </div>
                    </div>
				</div>
				<!-- END CONTENT BODY -->
			</div>
		</div>
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
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/post.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
		
        <script type="text/javascript" >
        	var table;
        	var recordsTotal =0;
        	$(function(){
    			$("#export").on("click", function (){   //筛选导出
    				sethideSearch();//重新设置搜索条件隐藏域
    				search();
    				if(recordsTotal!=0){
    					var caseStatus = "1";        //表示案件进行中
                     	var startDate = $("#startDate").val();
                    	var endDate = $("#endDate").val();
                    	var perRespon = $("#perRespon").val();
                    	var regiNo = $("#regiNo").val();
                    	var idCard = $("#idCard").val();
                    	var premises = $("#premises").val();
        				post("${pageContext.request.contextPath}/webmaster/caseInfo/caseExcelExport", {
        					"caseStatus": caseStatus,
        					"startDate": startDate,
        					"endDate": endDate,
        					"perRespon": perRespon,
        					"regiNo": regiNo,
        					"idCard": idCard,
        					"premises": premises,   					
        				});
    				}else{
    					top.layer.msg('导出数据为空，请重新筛选!');
    				}
                	
    			});   
    			
    			$("#exportNon").on("click", function (){   //筛选导出
        			sethideSearch();//重新设置搜索条件隐藏域
    				search();
    				if(recordsTotal!=0){
    					var caseStatus = "1";        //表示案件进行中
                     	var startDate = $("#startDate").val();
                    	var endDate = $("#endDate").val();
                    	var perRespon = $("#perRespon").val();
                    	var regiNo = $("#regiNo").val();
                    	var idCard = $("#idCard").val();
                    	var premises = $("#premises").val();
        				post("${pageContext.request.contextPath}/webmaster/caseInfo/nonExcelExport", {
        					"caseStatus": caseStatus,
        					"startDate": startDate,
        					"endDate": endDate,
        					"perRespon": perRespon,
        					"regiNo": regiNo,
        					"idCard": idCard,
        					"premises": premises,   					
        				});
    				}else{
    					top.layer.msg('导出数据为空，请重新筛选!');
    				}
                	
    			});
        		var i = 0;
        		table = $("#caseInfoList").DataTable({
        			"bLengthChange": false,
        			"searching": false,//本地搜索
        			"bStateSave" : false, //本地缓存
        			"serverSide": true, 
        			"bPaginate" : true,
        			"deferRender": true,
        			"bSort":true,
        			"pagingType":   "full_numbers",
        			"sAjaxSource": "${pageContext.request.contextPath}/webmaster/caseInfo/getPagingList?t="+Math.random(),      //这个是请求的地址    
        			 "oLanguage": {//国际语言转化
                         "sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"
                     },
                     "aoColumns": [ 
                    				{"mData" : null, "sClass" : "text-center","bSortable" : false}, 
                    				{"mData" : "regi_no", "sClass" : "text-center"},
                    				{"mData" : "per_respon",  "sClass" : "text-center"}, 
                    				{"mData" : null, "sClass" : "text-center"}, 
                    				{"mData" : "party_phone", "sClass" : "text-center"}, 
                    				{"mData" : "phone",  "sClass" : "text-center"}, 
                    				{"mData" : "premises",  "sClass" : "text-center"}, 
                    				{"mData" : "id_card", "sClass" : "text-center"}, 
                    				{"mData" : null, "sClass" : "text-center","bSortable" : false}
                    				],
                     "fnServerData" : function(sSource, aoData, fnCallback) {
                    	/* var caseStatus = $("#caseSta").val().trim();  */
                     	/* var startDate = $("#startDate").val();
                    	var endDate = $("#endDate").val();
                    	var perRespon = $("#perRespon").val();
                    	var regiNo = $("#regiNo").val();
                    	var idCard = $("#idCard").val();
                    	var premises = $("#premises").val();
                    	var searchDate = '{removeStatue:0, caseStatus:1, perRespon:"' +perRespon +'",regiNo:"' +regiNo +'",idCard:"' +idCard +'",premises:"' +premises +'",startDate:"' +startDate +'",endDate:"' +endDate +'"}'; */
                    	var searchDate = tableSearch();//获取查询条件的json数据，可以传入特定的id数组获得特定查询条件
                    	searchDate["removeStatue"] = 0;
                    	searchDate["caseStatus"] = 1;
                    	searchDate = JSON.stringify(searchDate);//json转字符串
                    	aoData.push({"name": "searchDate", "value": ""+searchDate+""});	
         				$.ajax({
         					"type" : 'post',
         					"url" : sSource,
         					"dataType" : "json",
         					"async":false,
         					"data" : {
         						aoData : JSON.stringify(aoData)
         					},
         					"success" : function(resp) {
         						recordsTotal =resp.recordsTotal;
         						fnCallback(resp);       						
         					}
         				});
         	
         			},"order" : [ [ 1, "desc" ] ],
         			"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
						var secondTDHtml = iDisplayIndex+1;
	                  	$('td:eq(0)', nRow).html("<span data-id=" +aData.id +">" +secondTDHtml +"</span>");			//序号
	                  	$('td:eq(3)', nRow).html(""+fmat(aData)+"");
	                  	var endHtml ="";
	                  	if(aData.status==1){
	                  		endHtml="<a class='btn btn-sm purple btn-outline' onclick='endCase(\"" + aData.id + "\")'>结案</a>";
	                  	}
	                  	var optHtml= "<div class='margin-bottom-5'><shiro:hasPermission name='case:edit'><a class='btn  btn-sm blue btn-outline filter-submit margin-bottom' href=\"${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase?tagId=2&caseInfoId="+aData.id+"\"><i class='fa fa-edit'></i>编辑</a></shiro:hasPermission>" ;
	                  	optHtml += "<shiro:hasPermission name='case:remove'><button class='btn btn-sm red btn-outline filter-cancel' onclick='toDel(\"" + aData.id + "\")' data-id=" +aData.id +"><i class='fa fa-remove'></i>删除</button></shiro:hasPermission>" ;
	                  	optHtml += "<a class='btn btn-sm green btn-outline' href='${pageContext.request.contextPath}/docTemplate/toInspect?caseId=" +aData.id +"'>预览</a>" ;
	                  	/* optHtml += "<a class='btn btn-sm yellow btn-outline' href='${pageContext.request.contextPath}/docTemplate/toProof?flag=1&caseId=" +aData.id +"'>送达回证</a>" ; */
	                  	optHtml += endHtml+"</div>" ;
	                  	$('td:eq(8)', nRow).html(optHtml);
	                  	var b = [aData.regi_no,aData.per_respon,fmat(aData),aData.party_phone,aData.phone,aData.premises,aData.id_card];
	  				  	for(var i = 1; i <8; i++){				
	  		        	  $('td:eq('+i+')', nRow).addClass("toLong");
	  		        	  $('td:eq('+i+')', nRow).attr("title",b[i-1]);
	  		          	}		                  	
						return nRow;
					}
         		});
        		
        	})
        	//案发时间处理
        	function fmat(aData){
        		var crimeData = "" ;
 				if(aData.crime_date != undefined){
 					crimeData = aData.crime_date.replace("-","年").replace("-","月")+"日";
 					if(aData.crime_start_time != undefined){
     					crimeData += " "+aData.crime_start_time.substring(0,aData.crime_start_time.length-2).replace(":","时").replace(":","分");
     				}
 					if(aData.crime_end_time != undefined && aData.crime_start_time != undefined){
     					crimeData += "至"+aData.crime_end_time.substring(0,aData.crime_end_time.length-2).replace(":","时").replace(":","分");
     				}
 				}
 				return crimeData;
        	}
        	
        	
        	
        	function search(){
    			table.ajax.reload();
    		}
        	//删除
        	function  toDel(obj){
        		top.layer.confirm('是否确定删除，一旦删除无法还原', {
    			    btn: ['确定', '取消']
    			  },function() {
    				  $.ajax({
    			         url : "${pageContext.request.contextPath}/webmaster/caseInfo/doRemove",
    			         data : {"id": obj},
    			         type : 'post',
    			         dataType : 'json',
    			         success : function(data) {
    			        	 if(data.success) {
    			        		top.layer.msg('删除成功');
    			        		var length = $("tbody tr").length;
    							if(length > 1) {
    								table.draw(false);
    							} else {
    								table.draw(true);
    							}
    			        	 } else {
    			        		top.layer.msg('删除失败');
    			        	 }
    			         },    
    			         error : function(msg) {   
    			         }    
    			      });
    			  });
        	}
        	//结案
        	function  endCase(obj){
        		top.layer.confirm('是否确定结案，一旦结案无法还原', {
    			    btn: ['确定', '取消']
    			  },function() {
    				  $.ajax({
    			         url : "${pageContext.request.contextPath}/webmaster/caseInfo/endCase",
    			         data : {"caseId": obj},
    			         type : 'post',
    			         dataType : 'json',
    			         success : function(data) {
    			        	 if(data.result) {
    			        		top.layer.msg('结案成功');
    			        		var length = $("tbody tr").length;
    							if(length > 1) {
    								table.draw(false);
    							} else {
    								table.draw(true);
    							}
    			        	 } else {
    			        		top.layer.msg('结案失败');
    			        	 }
    			         },    
    			         error : function(msg) {   
    			         }    
    			      });
    			  });
        	}
        	//初始化搜索条件隐藏域
        	sethideSearch();
        	//设置搜索条件隐藏域
        	function sethideSearch() {
        		hideSearch(["startDate", "endDate", "perRespon", "regiNo", "idCard", "premises"]);
        	}
        	$(".sureBtn").click(function() {
        		sethideSearch();//重新设置搜索条件隐藏域
        		search();
        	})
        	$(".reset").click(function() {
        		$("#startDate").val("");
        		$("#endDate").val("");
        		$("#perRespon").val("");
            	$("#regiNo").val("");
            	$("#idCard").val("");
            	$("#premises").val("");   
            	search();
        	});
        	$(".toDate").click(function() {
        		if($(this).val() == "") {
        			$(".range").each(function() {
            			$(this).removeClass("range");
            		});
            		$(".active").removeClass("active");
        			$(".selected").each(function() {
            			$(this).removeClass("range-start");
            			$(this).removeClass("range-end");
            			$(this).removeClass("selected");
        			});
        		}
        	})
        </script>
        
	</body>

</html>