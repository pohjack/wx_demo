<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE HTML >
<html> 
    <head>  
        <meta charset="utf-8" >  

        <title>异常日志管理</title>  


        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css"/>  
        <!-- 弹框引用JS CSS -->		
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
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/datatable.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>	
	
    </head>  
    <body> 
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
							<a href="javascript:;" class="expand" data-original-title=""
								title=""> </a>
						</div>
					</div>
					<div class="portlet-body form" style="display: none;">
						<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal ">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">异常类型检索</label>
											<div class="col-md-8">
												<input type="text" id="loginNameSearch" class="form-control"
													placeholder="异常类型检索..">
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4 ">
										<div class="form-group">
											<label class="control-label col-md-4">起止时间</label>

											   <div class="col-md-8 timeDiv">
                                                     <!-- <input type="text" id="" class="form-control" placeholder="选择时间段"> -->
                                                     <div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd" id="datetimepicker1">
	                                                     <input type="text" id="startTime" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endTime\')||\'new Date()\'}',skin:'twoer'})" class="form-control toDate" name="from">
	                                                     <span class="input-group-addon"> 至  </span>
	                                                     <input type="text" id="endTime"  onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',skin:'twoer'})" class="form-control toDate" name="to">
	                                                  </div>
                                               </div>

										</div>
									</div>


								</div>
							</div>
							<div class="form-actions" style="padding: 10px;">
								<div class="btn-set pull-right">
									   <button type="button" class="btn default reset">重置</button>
									<span class=" btn btn-primary" onclick="search()"
										style="margin-left: 25px;">搜索</span>
									
										<shiro:hasPermission name="logexception:remove">
				<button class="btn btn-danger navbar-btn"
					onclick="confirmLayer('确定要清空所有日志吗?',null,'confirmDo');">
					<!-- disabled="disabled" -->
					<i class="glyphicon glyphicon-trash"></i>&nbsp;清空日志
				</button>
			</shiro:hasPermission> 

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
									<i class="icon-notebook font-dark"></i> <span
										class="caption-subject bold uppercase"> 异常日志列表</span>
								</div>

							</div>
							<div class="portlet-body">
								<table
									class="table table-striped table-bordered table-hover table-checkable order-column"
									id="tb">
									<thead>
										 <tr> 
	            	<th>序号</th>
	                <th>类名称</th>    
	                <th>方法名</th> 
	                <th>异常名</th>  
	                <th>模块名</th>
	            	<th>所在行</th>   
	            	<th>时间</th>   
	            	<th>代码</th>   
	            </tr>    
									</thead>
									<tbody></tbody>
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
	<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>   
    <script type="text/javascript"> 
        var table ;   
        function  initData(){        	
        	 var d ; // 弹框的全局变量
        	 table = $("#tb").DataTable({
        		"bLengthChange": false,   //去掉每页显示多少条数据方法
        		 "searching":false,
        		 "bStateSave": false,      //本地缓存
                "bProcessing": true,                   // 是否显示取数据时的那个等待提示    
                "bServerSide": true,                    //这个用来指明是通过服务端来取数据 */               
                "sAjaxSource": "${pageContext.request.contextPath}/webmaster/sys/logexception/getPagingList",      //这个是请求的地址    
                "fnServerData": retrieveData ,       // 获取数据的处理函数    
                "oLanguage" : {"sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"},
    			"ordering":false,
    			"bPaginate":true,
    			"sPaginationType":"full_numbers", //详细分页组，可以支持直接跳转到某页  
    			"bAutoWidth": false,                    //不自动计算列宽度   
                 "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空      
                               { "mData": 'id', "sWidth": '28px'},                             
                               {"mData": 'class_name', "sWidth": '150px'},                             
                         	   {"mData": 'method_name', "sWidth": '100px'},
                         	 	 {"mData": 'exception_name', "sWidth": '280px'},
                         		 {"mData": 'model_name', "sWidth": '10%'},
                         	 	{"mData": 'exception_row', "sWidth": '48px'},
                         	 	{"mData": 'created', "sWidth": '180px'},
                         	 	{"mData": 'exception_code', "sWidth": '10%'}
                           ], 
               "fnRowCallback": function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 
                  //修改第二列为序号
                  var secondTDHtml = iDisplayIndex+1 +"<input type='hidden' value='"+aData.id+"' />";
                  $('td:eq(0)', nRow).html(secondTDHtml);
                  for(var i = 1; i <6; i++){
                	  $('td:eq('+i+')', nRow).addClass("toLong");
                  }
                  return nRow;
              }
            });  
            
        } 
        $(document).ready(function() { 
        	initData(); 
        	 $('#tb tbody').on( 'click', 'tr', function () {
                 $("#tb tbody tr").removeClass('selected');
                 $(this).addClass("selected");
                 var id = $(this).find("input").val();
                 var title = "查看异常详细信息";
             	var url ="${pageContext.request.contextPath}/webmaster/sys/logexception/toEdit?id="+id;
             	iframeLayer(title, [0.8, '#393D49'], 0, false, url, 600,500,3);   	
             } );
        });   
    	//初始化搜索条件隐藏域
       	sethideSearch();
       	//设置搜索条件隐藏域
       	function sethideSearch() {
       		hideSearch(["loginNameSearch", "startTime", "endTime"]);
       	}
         function  search(){
        	 sethideSearch();//重新设置搜索条件隐藏域
        	table.ajax.reload();
        } 
        
     	$(".reset").click(function() {
    		$("#startTime").val("");
    		$("#endTime").val("");
    		$("#loginNameSearch").val("");
            var input = '<div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd" id="datetimepicker1"><input type="text" id="startTime" class="form-control toDate" name="from">'
                		+'<span class="input-group-addon"> 至  </span>'
                		+'<input type="text" id="endTime" class="form-control toDate" name="to"></div>';
            $(".timeDiv").html(input);
            ComponentsDateTimePickers.init();            
    	});
         
         function confirmDo(param){
  		   $.ajax({    
                 url : "${pageContext.request.contextPath}/webmaster/sys/loglogin/removeAll",                              
                 data : {},   
                 type : 'post',    
                 dataType : 'json',    
                 async : false,    
                 success : function(result) {
              	   if(result.success = "true"){
              		   table.ajax.reload(); 
              	   }else{
              		   alertLayer(result.msg, 2, 2);
              	   }
                 },    
                 error : function(msg) {    
                 }    
         	})
     }
        // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
        function retrieveData( sSource,aoData, fnCallback) {
        	/* var  loginNameSearch =$("#loginNameSearch").val();
        	var  startTime=$("#startTime").val();
        	var  endTime =$("#endTime").val();       	
        	var searchDate= '{loginNameSearch:"'+loginNameSearch+'",startTime:"'+startTime+'",endTime:"'+endTime+'"}'; */       						
        	/* var searchDate = eval('([' + str + '])');    //拼接查詢參數 */
        	var searchDate = tableSearch();//获取查询条件的json数据，可以传入特定的id数组获得特定查询条件
        	searchDate = JSON.stringify(searchDate);//json转字符串
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
        
    </script>    
        
</body>    
</html> 