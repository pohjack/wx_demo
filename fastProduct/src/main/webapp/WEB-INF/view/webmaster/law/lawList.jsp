<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>潮州烟草案件卷宗自动化系统-法律法规列表</title>
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
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE TITLE-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-filter"></i>条件筛选
						</div>
						<div class="tools">
							<a href="javascript:;" class="expand" data-original-title="" title=""> </a>
						</div>
					</div>
					<div class="portlet-body form" style="display: none;">
						<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal">
							<div class="form-body">
								<div class="row">
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">案由编号</label>
											<div class="col-md-8">
												<input type="text" id="caseCauseNo" class="form-control" placeholder=""  >
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-3">
										<div class="form-group">
											<label class="control-label col-md-4">案由内容</label>
											<div class="col-md-8">
												<input type="text" id="caseCauseCont" class="form-control">
											</div>
										</div>
									</div>
									<div class="col-md-3">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">鉴定结果</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="authenRes" class="form-control">
                                                     
                                                 </div>
                                             </div>
                                         </div>
                                         <div class="col-md-3">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">处罚法规</label>
                                                 <div class="col-md-8">
                                                     <input type="text" id="punishLaw" class="form-control">
                                                     
                                                 </div>
                                             </div>
                                         </div>
									
								</div>
							</div>
							<div class="form-actions" style="padding: 10px;">
								<div class="btn-set pull-right">
									<button type="button" id="reset" class="btn default">重置</button>
									<button type="button" id="sure" class="btn green-turquoise">确定</button>
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
									<i class="icon-notebook font-dark"></i> <span class="caption-subject bold uppercase"> 法律法规列表</span>
								</div>
								<div class="actions">
									<a href="javascript:onclick=toEdit('')" class="btn btn-default btn-sm"> <i class="fa fa-plus"></i> 新建法律法规 </a> 
									<!-- <a href="javascript:;" class="btn btn-default btn-sm"> <i class="fa fa-print"></i> 打印列表</a> -->
								</div>
							</div>
							<div class="portlet-body">
								<table class="table table-striped table-bordered table-hover table-checkable order-column" id="lawList">
									<thead>
										
										<tr>
											<th width="5%">序号</th>
											<th width="5%">案由编号</th>
											<th width="14%">案由内容</th>
											<th width="14%">鉴定结果</th>
											<th width="20%">违反法规内容</th>
											<th width="20%">处罚法规</th>
											<th width="15%">操作</th>
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
	<script src="${pageContext.request.contextPath}/statics/script/datatable.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>
	
	<script type="text/javascript">
		var table;
		//添加卷烟时 触发datatable查询所有卷烟数据
		function showList() {
			table = $("#lawList").DataTable({
				"bLengthChange": false,
				"searching" : false,
				"bStateSave" : false, //本地缓存
				"bProcessing" : false, // 是否显示取数据时的那个等待提示    
				"bServerSide" : true, //这个用来指明是通过服务端来取数据 */  
				"pagingType":   "full_numbers",
				"sAjaxSource" : "${pageContext.request.contextPath}/webmaster/law/getPagingList", //这个是请求的地址    
				"fnServerData" : retrieveData, // 获取数据的处理函数    
				"order" : [ [ 2, "desc" ] ],
				"oLanguage" :  {"sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"},
				"bAutoWidth" : true, //不自动计算列宽度   
				"aoColumns" : [ //这个属性下的设置会应用到所有列，按顺序没有是空      从0开始
					{"mData" : null,"sClass" : "text-center", "bSortable" : false}, 
					{"mData" : 'case_cause_no' ,"sClass" : "text-center","defaultContent": ''}, 
					{"mData" : 'case_cause_cont' ,"sClass" : "text-center","defaultContent": ''}, 
					{"mData" : 'authen_res' ,"sClass" : "text-center","defaultContent": ''}, 
					{"mData" : 'break_law' ,"sClass" : "text-center","defaultContent": ''}, 
					{"mData" : 'punish_law' ,"sClass" : "text-center","defaultContent": ''}, 
					{"mData" : null,"sClass" : "text-center", "bSortable" : false}
				],
				"aoColumnDefs" : [//和aoColums类似，但他可以给指定列附近爱属性
					{"bSortable" : true, "aTargets" : [ 0 ]}, //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
				/*   {"bSearchable": false, "aTargets": [1, 2, 3, 4, 5, 6, 7, 8, 9]}, //bSearchable 这个属性表示是否可以全局搜索，其实在服务器端分页中是没用的 */
				],
				"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 		
					var secondTDHtml = iDisplayIndex + 1;
					$('td:eq(0)', nRow).html(
						"<span>" + 
						secondTDHtml + "<input type='hidden' data-index='"+secondTDHtml+"' value='"+aData.id+"'>" + 
						"</span>"
					);
					$('td:eq(6)', nRow).html(
							'<div class="margin-bottom-5">' +
							'<button onclick="toEdit(\'' + aData.id + '\')" class="btn btn-sm blue btn-outline filter-submit margin-bottom"><i class="fa fa-edit"></i> 编辑' +
							'</button><button onclick="toDel(\'' + aData.id + '\')" class="btn btn-sm red btn-outline filter-cancel"><i class="fa fa-remove"></i> 删除</button>' +
							'</div>'
					);
					return nRow;
				},
				initComplete : function() {
				}
			});
		}
		//3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		function retrieveData(sSource, aoData, fnCallback) {
			/* var caseCauseNo = $("#caseCauseNo").val().trim();
			var caseCauseCont = $("#caseCauseCont").val().trim();
			var authenRes = $("#authenRes").val().trim();
			var punishLaw = $("#punishLaw").val().trim();
			var searchDate = '{caseCauseNo:"' + caseCauseNo + '",caseCauseCont:"' + caseCauseCont +
				',authenRes:"' + authenRes + ',punishLaw:"' + punishLaw + '"}'; */
			/* var searchDate = eval('([' + str + '])');    //拼接查詢參數 */
			var searchDate = tableSearch();//获取查询条件的json数据，可以传入特定的id数组获得特定查询条件
             searchDate = JSON.stringify(searchDate);//json转字符串
			aoData.push({ "name" : "searchDate", "value" : "" + searchDate + "" });//添加自己的额外参数		
			$.ajax({
				url : sSource, //这个就是请求地址对应sAjaxSource    
				data : {
					"aoData" : JSON.stringify(aoData)
				}, //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
				type : 'post',
				dataType : 'json',
				async : false,
				success : function(result) {
					fnCallback(result); //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的    
				},
				error : function(msg) {
				}
			});
		}
		function reset(){
			$(".col-md-8").find("input[type='text']").each(function (){
				$(this).val("");
			});
			$(".col-md-8").find("select").each(function (){
				$(this).val("0");
			});
			search();
		}
		$(function() {
			showList();
			//初始化搜索条件隐藏域
        	sethideSearch();
        	//设置搜索条件隐藏域
        	function sethideSearch() {
        		hideSearch(["caseCauseNo", "caseCauseCont","authenRes","punishLaw"]);
        	}
			$("#sure").on("click", function (){
				sethideSearch();
				search();
			});
			$("#reset").on("click", function (){
				reset();
				sethideSearch();
				search();
			});
		});
		function toEdit(id) {
			if (id != null && id != "") {
				window.location.href = "${pageContext.request.contextPath}/webmaster/law/toEdit?id=" + id;
			} else {
				window.location.href = "${pageContext.request.contextPath}/webmaster/law/toEdit";
			}
		}
		function toDel(id){
			 top.layer.confirm('是否确定删除？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
				        url:"${pageContext.request.contextPath}/webmaster/law/doRemove",    
				        type:"POST",  
				        data:{"id" : id},  
				        dataType:"text",  
				        success: function(data){ 
				        	if(data.success) {
				        		top.layer.msg('删除失败');
				        		top.layer.msg('删除成功');
								search();
				        	 } else {
				        		 top.layer.msg('删除成功');
									search();
				        	 }
				        },error : function(msg) {
				        	layer.closeAll('loading');
							layer.msg("删除失败");
						}
				    });
				}, function(){
				});
		}

		//条件搜索
		function  search(){
			table.ajax.reload();
		}
	</script>
</body>

</html>