<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta charset="UTF-8">
<title>潮州烟草案件卷宗自动化系统-用户列表</title>
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

<style type="text/css">
.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
ul.ztree {
    margin-top: 0px;
    border: 1px solid #617775;
   background: #fdfdfd;
    overflow-y: scroll;
    overflow-x: auto;
    width:100% ;
    display: none;
    position: absolute;
}
.toLong{
	text-overflow: ellipsis;
	white-space: nowrap; 
	overflow: hidden;
	text-align: center;
}


.switch  {
	position: initial;
}
.form-group ul li {
    display: block;
    margin: 0;
    min-width: 0;
}
</style>
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
							<a href="javascript:;" class="expand"> </a>
						</div>
					</div>
					<div class="portlet-body form" style="display: none;">
						<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">用户名</label>
											<div class="col-md-8">
												<input type="text" id="name" class="form-control" placeholder="">
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">真实姓名</label>
											<div class="col-md-8">
												<input type="text" id="realName" class="form-control">
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
                                         <div class="form-group">
                                             <label class="control-label col-md-4">手机号</label>
                                             <div class="col-md-8">
                                                 <input type="text" id="mobile" class="form-control">
                                             </div>
                                         </div>
                                     </div>
                                     <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">所属机构</label>
                                                 <div class="col-md-8">
                                                 	<div style="width:100%; position:relative">
	                                                 	<input type="text" value="" id="orgValue" readonly="readonly" class="form-control">
	                                                 	<input type="hidden" id="orgId">
	                                                 	<ul id="treeDemo" class="ztree" ></ul>
                                                 	</div>
                                                 </div>
                                             </div>
                                         </div> 
                                          <div class="col-md-4">
                                             <div class="form-group">
                                                 <label class="control-label col-md-4">启用状态</label>
                                                 <div class="col-md-8">
                                                     <select name="foo" id="state" class="form-control">
                                                       <option value="">全部</option> 
                                                       <option value="1">启用</option> 
                                                       <option value="0">禁用</option> 
                                                     </select>
                                                 </div>
                                             </div>
                                         </div> 
                                      
								</div>
							</div>
							<div class="form-actions" style="padding: 10px;">
								<div class="btn-set pull-right">
										<button type="button" id="reset" class="btn default">重置</button>
									<button type="button" id="sure" class="btn green-turquoise">确定</button>
									<button type="button" id="export" class="btn btn-primary">筛选导出</button>
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
									<i class="icon-notebook font-dark"></i> <span class="caption-subject bold uppercase"> 用户列表</span>
								</div>
								<div class="actions">
									<form id="upform"  action="${pageContext.request.contextPath}/webmaster/sys/user/userExcelImport" method="POST" enctype="multipart/form-data">
									<!-- <a href="javascript:;" class="file btn btn-default btn-sm">导入excel<input id="file" type="file" name="file" onchange="upload()"></a>						 -->		
									<shiro:hasPermission name="user:edit"><a href="javascript:onclick=toEdit('')" class="btn btn-default btn-sm"> <i class="fa fa-plus"></i> 新建用户</a> </shiro:hasPermission>
									</form>
								</div>
							</div>
							<div class="portlet-body">
								<table style="table-layout: fixed" class="table table-striped table-bordered table-hover table-checkable order-column" id="licenceInfoList">
									<thead>
										<tr>
											<th width="5%">序号</th>
											<th width="7%">用户名</th>
											<th width="10%">真实姓名</th>
											<th width="8%">执法证号</th>
											<th width="8%">手机号</th>
											<th width="10%">所属机构</th>
											<th width="10%">所属角色</th>
											<th width="12%">上次登录时间</th>
											<th width="5%">状态</th>
											<th width="24%">操作</th>											
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
	<!-- END CONTAINER -->

	<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->

	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/ajaxFileUpload.js"></script>
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
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>	
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.exedit.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/plugins/ztree-3.5.24/css/zTreeStyle/zTreeStyle.css"/>
		
	<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>
	
	<script type="text/javascript">
	var setting = {
			view: {
	        	dblClickExpand: true,							//双击时是否展开节点
	        	expandSpeed: "fast",							//设置展开速度
	        },
	        check: {
	            enable: false,							//是否显示复选框
	            chkStyle: "checkbox",					//复选框类型，checkbox为多选，radio为单选
	            nocheckInherit: true,					//新增子节点时是否继承父类
	        },
	        callback:{					
	        	onClick: zTreeOnClick			//单击节点事件
	    	},
	    	data: {
	            simpleData: {
	                enable: true,			//设置树的数据模式，true为简单模式，false为复杂模式
	                idKey: "id",			
					pIdKey: "pid",			//父类id
					rootPId: 0				//最顶级父类的默认id
	            },
	        },
	        edit: {
	            enable: false,					//是否触发编辑状态
	            drag: {
	            	isCopy: false,				//是否复制节点
	            	isMove: false,				//是否允许拖动节点
	            },
	            showRenameBtn: false,			//是否显示修改按钮
	        },
	    };
	var zNodes = null;											//初始化数据
	$(function() {														//初始化zTree
		getAjaxTreeList();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree=$.fn.zTree.getZTreeObj("treeDemo");
		if(zNodes.length>0) {
			zTree.expandAll(true);											//展开全部节点
			/* var allNodes = zTree.getNodes();								
			zTree.selectNode(allNodes[0]);									//默认选中第一个
			$("#no1").attr("src", "toEditView?id=" +allNodes[0].id);			//开启Iframe */
		}
		$("#orgValue").on("click",function(){
			if($("#treeDemo").css("display")=='none'){
				$("#treeDemo").attr("style","display:block");
			}else{
				$("#treeDemo").attr("style","display:none");
			}
		})	 
	});
	
	function zTreeOnClick(event, treeId, treeNode) {								/* 单击事件 */
		var id = treeNode.id;
		var name = treeNode.name;
		$("#orgValue").val(name);
		$("#orgId").val(id);
		$("#treeDemo").attr("style","display:none");
	}
	
	
	function getAjaxTreeList() {														/* ajax拿到zTree数据 */
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/organization/getAjaxTreeList",
			data:{},
			async: false, 
			type: "post",
			dataType: "json",
			success: function(data) {
				zNodes = data.treeNodes;
			}
		});
	}
	
	
	
	
		var table;
		//添加卷烟时 触发datatable查询所有卷烟数据
		function showList() {
			table = $("#licenceInfoList").DataTable({
				"bLengthChange": false,
    			"searching": false,//本地搜索
    			"bStateSave" : false, //本地缓存
    			"serverSide": true, 
    			"bPaginate" : true,
    			"deferRender": true,
    			"pagingType":   "full_numbers",
				"bServerSide" : true, //这个用来指明是通过服务端来取数据 */  
				"sAjaxSource" : "${pageContext.request.contextPath}/webmaster/sys/user/getPagingList", //这个是请求的地址    
				"fnServerData" : retrieveData, // 获取数据的处理函数    
				"order" : [ [ 2, "asc" ] ],
				"oLanguage" : {"sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"},
				"bAutoWidth" : false, //不自动计算列宽度   
				"aoColumns" : [ //这个属性下的设置会应用到所有列，按顺序没有是空      从0开始
				                {"mData" : null,  "bSortable" : false, "sClass" : "text-center"}, 
								{"mData" : 'user_name',  "sClass" : "text-center"}, 
								{"mData" : 'real_name',"sClass" : "text-center"}, 
								{"mData" : 'card_id', "sClass" : "text-center"},
								{"mData" : 'mobile', "sClass" : "text-center"}, 
								{"mData" : 'orgName',"sClass" : "text-center"}, 
								{"mData" : 'roleName', "sClass" : "text-center"}, 
								{"mData" : 'login_time',  "sClass" : "text-center"}, 
								{"mData" : null, "sClass" : "text-center"}, 
								{"mData" : null, "sClass" : "text-center"} 
				],
				"aoColumnDefs" : [//和aoColums类似，但他可以给指定列附近爱属性
					{"bSortable" : false, "aTargets" : [ 0,1,3,4,5,6,7,8,9]}, //这句话意思是第6列（从0开始算） 能排序
						/*   {"bSearchable": false, "aTargets": [1, 2, 3, 4, 5, 6, 7, 8, 9]}, //bSearchable 这个属性表示是否可以全局搜索，其实在服务器端分页中是没用的 */
				],
				"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 		
					var secondTDHtml = iDisplayIndex + 1;
					$('td:eq(0)', nRow).html(
						"<span>" + secondTDHtml + "<input type='hidden' data-index='"+secondTDHtml+"' value='"+aData.id+"'></span>"
					);
					$('td:eq(9)', nRow).html(
						'<div class="margin-bottom-5">' +
						'<shiro:hasPermission name="user:edit"><button onclick="toEdit(\'' + aData.id + '\')" class="btn btn-sm blue btn-outline filter-submit margin-bottom"><i class="fa fa-edit"></i> 编辑' +
						'</button></shiro:hasPermission><shiro:hasPermission name="user:change_password"><button onclick="resetPassword(\'' + aData.id + '\')" class="btn btn-sm red btn-outline filter-cancel"> 密码重置</button>' +
						'</button></shiro:hasPermission><shiro:hasPermission name="user:remove"><button onclick="toDel(\'' + aData.id + '\')" class="btn btn-sm red btn-outline filter-cancel"><i class="fa fa-remove"></i> 删除</button></shiro:hasPermission>' +
						'</div>'
					);					
					if (aData.status == 1) {
						$('td:eq(8)', nRow).html("<i id='" + aData.id + "' class='fa fa-check' style='color:green;'></i>");
						//$('td:eq(11)', nRow).html("<div id='" + aData.id+"_state'><a onclick='UpdateState(\"" + aData.id+ "\")'><i class='fa fa-remove'>禁用</i></a></div>");
					} else {
						$('td:eq(8)', nRow).html("<i id='" + aData.id + "' class='fa fa-remove' style='color:red;'></i>");
						//$('td:eq(11)', nRow).html("<div id='" + aData.id + "_state'><a onclick='UpdateState(\"" + aData.id + "\")'><i class='fa fa-check'>启用</i></a></div>");
					}
				 	var b = [aData.user_name,aData.real_name,aData.card_id,aData.mobile,aData.orgName,aData.roleName,aData.login_time];
  				  	for(var i = 1; i <8; i++){				
  		        	  $('td:eq('+i+')', nRow).addClass("toLong");
  		        	  $('td:eq('+i+')', nRow).attr("title",b[i-1]);
  		          	}
					return nRow;
				},
				initComplete : function() {}
			});
		}
		//3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
		function retrieveData(sSource, aoData, fnCallback) {
			/* var userName = $("#userName").val().trim();
			var realName = $("#realName").val().trim();
			var mobile = $("#mobile").val().trim();
			var orgId = $("#orgId").val();
			var state =$("#state").val(); */
			var searchDate = tableSearch();//获取查询条件的json数据，可以传入特定的id数组获得特定查询条件
        	searchDate = JSON.stringify(searchDate);//json转字符串
			aoData.push({"name" : "searchDate", "value" : "" + searchDate + ""});//			
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
				 $(this).val("");
			});
			$("#orgVlue").val("");
			$("#orgId").val("");
		}
		$(function() {
			showList();
			//getAllOrganization();
			//初始化搜索条件隐藏域
        	sethideSearch();
        	//设置搜索条件隐藏域
        	function sethideSearch() {
        		hideSearch(["name", "realName", "mobile", "orgId", "state"]);
        	}
			$("#sure").on("click", function (){
				sethideSearch();
				search();
			});
			$("#reset").on("click", function (){
				reset();
			});
			$("#export").on("click", function (){
				search();
				var userName = $("#userName").val().trim();
				var realName = $("#realName").val().trim();
				var mobile = $("#mobile").val().trim();
				var state =$("#state").val(); 
				var orgId = $("#orgId").val();
				/* window.open(
					"${pageContext.request.contextPath}/webmaster/sys/user/userExcelExport?userName=" + userName +
					"&realName=" + realName +
					"&mobile=" + mobile +
					"&state=" + state
				); */
				window.location.href="${pageContext.request.contextPath}/webmaster/sys/user/userExcelExport?loginName=" + userName +
				"&realName=" + realName +
				"&mobile=" + mobile +
				"&status=" + state+
				"&orgId="+orgId ;
			}); 
		});
		function toEdit(id) {
			if (id != null && id != "") {
				window.location.href = "${pageContext.request.contextPath}/webmaster/sys/user/toEditUser?id=" + id;
			} else {
				window.location.href = "${pageContext.request.contextPath}/webmaster/sys/user/toEditUser";
			}
		}

		function toDel(id){
			top.layer.confirm('是否确定删除？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
				        url:"${pageContext.request.contextPath}/webmaster/sys/user/removeUser",    
				        type:"POST",  
				        data:{"ids" : id},  
				        dataType:"text",  
				        success: function(data){ 
				        	top.layer.closeAll('dialog'); 
							search();
							layer.msg("删除成功");
				        },error : function(msg) {
				        	top.layer.closeAll('dialog');
							layer.msg("失败");
						}
				    });
				}, function(){
				});
		}
		
		function upload() {
			 var  i = ityzl_SHOW_LOAD_LAYER();  
			 $.ajaxFileUpload({  
                 url : '${pageContext.request.contextPath}/webmaster/sys/user/userExcelImport',  
                 type: 'post',  
                 secureuri : false,  
                 fileElementId : 'file',//input 的id  
                 dataType : 'json',   
                 success : function(data) { 
                	  ityzl_CLOSE_LOAD_LAYER(i);  
                      ityzl_SHOW_TIP_LAYER();          
                 },  
                 error : function(data, status, e) {  
                	 ityzl_CLOSE_LOAD_LAYER(i);  
               	 	 ityzl_SHOW_ERROR_LAYER();
                 }  
             })  
		}
		
		
		
		//条件搜索
		function  search(){
			table.ajax.reload();
		}
		
		
		function resetPassword(obj){
			top.layer.confirm('是否要重置密码(默认密码:123456)？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					$.ajax({
				        url:"${pageContext.request.contextPath}/webmaster/sys/user/resetPassword",    
				        type:"POST",  
				        data:{"id" : obj},  
				        dataType:"text",  
				        success: function(data){ 
				        	top.layer.closeAll('dialog'); 
							search();
							layer.msg("重置成功");
				        },error : function(msg) {
				        	top.layer.closeAll('dialog');
							layer.msg("重置失败");
						}
				    });
				}, function(){
				});
		}
	</script>
</body>

</html>