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
			.dataTables_scroll {
				margin-bottom: 0;
			}
			.dataTables_scrollHeadInner table {
				width: 100% !important;
			}
			.table-scrollable {
			 	border:0;
			 	overflow-x: hidden;
			}
			div.dataTables_scrollBody table {
				width: 100% !important;
			}
			.center {
				text-align: center;
			}
			#b { 
			    position:relative; 
			}
            #a { 
                position:absolute;
                top:0; 
                right:0; 
                z-index:99;
            }
            .checkBtn {
            	height: 30px;
			    padding: 6px 12px;
			    background-color: #fff; 
			    border: 1px solid #c2cad8;
            }
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed"  id="b">
	<div class="form-body">
		<div class="modal-body"  >
			<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
				<div class="table-scrollable">
					<table
						class="table table-striped table-bordered table-hover table-checkable order-column dataTable no-footer"
						id="cigarTable" role="grid" aria-describedby="sample_1_info"
						style="width: 0px;">
						<thead>
							<tr role="row">
								<th class="sorting_disabled" rowspan="1" colspan="1"
									aria-label="" style="width: 0px;">序号</th>
								<th class="sorting_asc" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1" aria-sort="ascending"
									aria-label="序号: activate to sort column descending"
									style="width: 0px;">案件编号</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="牌名称号: activate to sort column ascending"
									style="width: 0px;">卷烟名称</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="零售价: activate to sort column ascending"
									style="width: 0px;">卷烟数量</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="零售价: activate to sort column ascending"
									style="width: 0px;">卷烟单价</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="零售价: activate to sort column ascending"
									style="width: 0px;">卷烟总价</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="零售价: activate to sort column ascending"
									style="width: 0px;">卷烟类型</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center">
		<button type="button" class="btn default" id="quit">关闭</button>
	</div>
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
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

		<script type="text/javascript">
		 $(function(){
			 addCigar();
		 });
		var table;
		//添加卷烟时 触发datatable查询所有卷烟数据
		function  addCigar(){
			 table = $("#cigarTable").DataTable({
				 	"scrollY": "470px",
	                "scrollCollapse": "true",
	                "paging": "false",
					"bLengthChange": false,   //去掉每页显示多少条数据方法
					"searching":false,
					"bStateSave": false,      //本地缓存
			       "bProcessing": false,                   // 是否显示取数据时的那个等待提示    
			       "bServerSide": true,                    //这个用来指明是通过服务端来取数据 */  
			       "bSort":true,
			       "sAjaxSource": "${pageContext.request.contextPath}/webmaster/caseCigar/getPagingList2",      //这个是请求的地址    
			       "pagingType":   "full_numbers",
			       "fnServerData": retrieveData,       // 获取数据的处理函数    
			       "oLanguage" : { // 汉化
			    	   "sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"
					},
					"bAutoWidth": false,                    //不自动计算列宽度   
			       "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空      从0开始
			            {"mData" : null, "bSortable" : false}, 
						{"mData" : 'regiNo'}, 
						{"mData" : 'name'}, 
						{"mData" : 'number'}, 
						{"mData" : 'retailPrice', "bSortable" : true}, 
						{"mData" : 'totalValue'}, 
						{"mData" : 'inspectResult'}
						],
						"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 		
							var secondTDHtml = iDisplayIndex + 1;
							$('td:eq(0)', nRow).html("<span>"+secondTDHtml+"<input type='hidden' data-index='"+secondTDHtml+"' value='"+aData.id+"'></span>"); 
							return nRow;
						},
						initComplete : function() {
						}
					});
		 }
		
		//3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		 function retrieveData( sSource,aoData, fnCallback) {
			 	var cigarType = "${cigarType}";
				var startDate = "${startDate}";
				var endDate = "${endDate}";
				var name = "${name}";
				var ids = '';
				var searchDate= '{cigarType:"'+cigarType
					+'",startDate:"'+startDate +'",endDate:"' +endDate
					+'",name:"'+name +'",ids:"'+ids +'"}';       						
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
		 //条件搜索
		 function search(){
		 	table.ajax.reload();
		 }
		 $("#quit").click(function(){
			 var index = parent.layer.getFrameIndex(window.name);
			 parent.layer.close(index);
		 })
		</script>
	</body>

</html>