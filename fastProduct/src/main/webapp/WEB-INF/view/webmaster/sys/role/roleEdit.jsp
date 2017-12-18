<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/plugins/ztree-3.5.24/css/zTreeStyle/zTreeStyle.css"/>
<title>Insert title here</title>
<script type="text/javascript">
	var setting = {
		check: {
			enable: true,
			chkboxType: { "Y": "p", "N": "ps" }
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",			
				pIdKey: "pid",			//父类id
				rootPId: 0				//最顶级父类的默认id
			}
		},
		callback: {
			onCheck: zTreeOnCheck,
			beforeCheck: zTreeBeforeCheck
		},
	};
	var zNodes = null;
	var zTree = null;
	$(function(){
		getAjaxTreeList();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree=$.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandAll(true);											//展开全部节点
	});
	function getAjaxTreeList() {																/* ajax拿到zTree数据 */
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/resources/getAjaxTreeList",
			data:{},
			async: false, 
			type: "post",
			dataType: "json",
			success: function(data) {
				zNodes = data.treeNodes;
			}
		});
	}
	

	var getResource = false;																	//判断是否拿到角色资源
	function toResouseByRoleId() {																/* ajax拿到角色拥有的资源 */
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/role/toResouseByRoleId",
			data:{
				"id":"${obj.id}"
			},
			async: false, 
			type: "post",
			dataType: "json",
			success: function(data) {
				var resList = data.resList;
				if(resList != null && resList.length>0) {
					var treeNode = null;
					for(var i=0; i<resList.length; i++) {
						treeNode = zTree.getNodeByParam("id", resList[i].menuId, null);
						zTree.checkNode(treeNode, true, false);
					}
				}
				getResource = true;
			}
		});
	}
	var getUser = false;																		//判断是否拿到用户
	function toUserByRoleId() {																/* ajax拿到角色拥有的用户 */
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/role/toUserByRoleId",
			data:{
				"id":"${obj.id}"
			},
			type: "post",
			dataType: "json",
			success: function(data) {
				var users = data.users;
				if(users!=null && users.length>0) {
					for(var i=0; i<users.length; i++) {
						$("#user").append("<option>" +users[i].realName +"</option>");
					}
				}
				getUser = true;
			},
			error: function(data) {
				msgLayer(data);
			}
		});
	}
	var resIds = new Array();
	function zTreeBeforeCheck(treeId, treeNode) {
		if("${obj.id}"!=null && "${obj.id}"!="") {				//判断是新增还是修改，新增的话没有roleId无法同步
			if(treeNode.checked == false) {												//选中节点时
				var parentNode = treeNode.getParentNode();								
				while(parentNode!=null && parentNode.checked==false) {	//如果父节点之前没有被选中，则添加进数组
					resIds.push(parentNode.id);
					parentNode = parentNode.getParentNode();
				}
			}
			return true;
		} 
		return false;
	}
	function zTreeOnCheck(event, treeId, treeNode) {											/* 单击复选框回调函数 */
		var children = treeNode.children;					//拿到当前选中节点的子节点
		resIds.push(treeNode.id);							//先存放选中的节点的id
		var nodes = zTree.transformToArray(children);		//拿到所有子节点
		for(var i=0; i<nodes.length; i++) {
			resIds.push(nodes[i].id);
		};
		if(treeNode.checked==false) {						//该节点的父节点只有该子节点时，顺带删除父节点
			var parentNode = treeNode.getParentNode();
			while(parentNode!=null && parentNode.checked==false) {
				resIds.push(parentNode.id);
				parentNode = parentNode.getParentNode();
			}
		} else {											//选中时勾选子节点
			for(var i=0; i<nodes.length; i++) {
				zTree.checkNode(nodes[i], true, true);
			}
			
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/role/amendRoleRes",
			data: {
				"resIds": resIds,
				"roleId":"${obj.id}",
				"flag": treeNode.checked
			},
			traditional: true,
			type: "post",
			dataType: "text",
			success: function(data) {
				resIds = [];
				if(data != "true") {
					msgLayer(data);
				}
			}
		});
	}
	function saveBtn() {																		/* 保存数据 */
		var resIds = new Array();													//存放资源id
		if("${obj.id}"==null || "${obj.id}"=="") {									//如果没有角色id，则是新增，去拿到选中资源
			resIds = getResIds();
		}
		var flag = notNull("code", "不能为空");
		flag = notNull("name", "不能为空")&&toSameName("name")&&flag;
		if(flag==false) {
			return;
		}
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/role/saveRole",
			data: {
				"id": "${obj.id}",
				"code":$("#code").val(),
				"name": $("#name").val(),
				"description": $("#description").val(),
				"resIds":resIds,
				"status":$('input:radio[name="status"]:checked').val()
			},
			traditional: true,
			type: "post",
			dataType: "text",
			success: function(data) {
				if(data == "true") {
					/* parent.editRefurbish();
					var index = parent.layer.getFrameIndex(window.name); 
					parent.layer.close(index); */
					msgLayer('保存成功');
				} else {
					msgLayer(data);
				}
			}
		});
	}
	function toSameName(id) {																	/* 判断是否重名 */
		if("${obj.name }" == $("#name").val()) {
			return true;
		}
		var flag = false;
		$.ajax({
			url: "${pageContext.request.contextPath}/webmaster/sys/role/toSameName",
			data: {
				"name": $("#name").val(),
			},
			type: "post",
			dataType: "text",
			async: false, 
			success: function(data) {
				if(data=="true") {
					$("#" +id).removeClass("errorInput");
					flag=true;
				} else {
					overlyTipsLayer("出现重名", "#FF4500", 6000, 2, $("#" +id), true);  
					$("#" +id).addClass("errorInput");
				}
			}
		});
		return flag;
	}
	function getResIds() {																		/* 拿到选中的资源id */
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		var nodes = zTree.getCheckedNodes(true);
		var resIds = new Array();
		for(var i=0; i<nodes.length; i++) {
			resIds.push(nodes[i].id);
		}
		return resIds;
	}
	function powerBtn() {																		/* 显示角色资源，隐藏其他*/
		if(!getResource) {
			toResouseByRoleId();
		}
	}
	function infoBtn() {																		/* 显示角色信息，隐藏其他 */		
		$("#infoDiv").removeClass("hidden");
		$("#powerDiv").addClass("hidden");
		$("#userDiv").addClass("hidden");
	}
	function userBtn() {																		/* 显示角色中的用户，隐藏其他 */
		if(!getUser) {
			toUserByRoleId();
		}
	}
	
	function notNull(id, msg) {
		var value = $("#" +id).val().trim();
		if(value!=null && value!="") {
			$("#" +id).removeClass("errorInput");
			return true;
		} else {
			overlyTipsLayer(msg, "#FF4500", 6000, 2, $("#" +id), true);  
			$("#" +id).addClass("errorInput");
			return false;
		}
	}
	
</script>
<style type="text/css">
	.errorInput{
		box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483;
	}
	
	.hidden {
		display:none;
	}
	.title {
		width:90%;
		padding:5px 5px 5px 5px;
		text-align:center;
		margin:10px
	}
	.control-label {
		float: left;
		margin-top: 5px;
		margin: 5px 5px 0 5px;
	}
	.form-group {
		width:300px;
		margin-bottom: 15px ;
	}
	input[type="text"] {
		width:250px;
	} 
	.myFrom{
		width: 70%;
    	margin: 0 auto;
    	padding: 20px;
    	border: 1px solid #eee;
	}
	a {
		margin:0 10px 0 10px;
	}
	a:hover {
		text-decoration:none;
		cursor:pointer;
	} 
	#powerDiv {
		padding: 5px 10px;
		border: 1px solid #eee;
    	margin: 0 auto;
    	width:70%;
	}
	.ztree {
		height: 350px;
    	overflow: auto;
	}
	#userDiv {
		width: 70%;
    	margin: 0 auto;
	}
	#user {
		overflow-y: hidden;
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
					<h3 class="page-title">角色信息 </h3>
					<div class="row">
					    <div class="col-md-12">
					        <div class="tabbable-line boxless tabbable-reversed">
					            <ul class="nav nav-tabs">
					                <li class="active">
					                    <a href="#tab_0" data-toggle="tab"   aria-expanded="true"> 角色信息 </a>
					                </li>
					                <li class="">
					                    <a href="#tab_1" data-toggle="tab" onclick="powerBtn();" aria-expanded="false">资源 </a>
					                </li>
					                <c:if test="${not empty obj.id}">
					                <li class="">
					                    <a href="#tab_2" data-toggle="tab" onclick="userBtn();" aria-expanded="false"> 拥有用户 </a>
					                </li>
					              	</c:if>
					            </ul>
					            <div class="tab-content">
					                <div class="tab-pane active" id="tab_0">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>案件信息 </div>
						                   </div>
						                   <div class="portlet-body form">
						                       <!-- BEGIN FORM-->
						                       <form action="#" onsubmit="return false" class="form-horizontal">
						                           <div class="form-body">					                           						                             
						                               <h3 class="form-section"></h3>						                     						                              
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">角色名</label>
						                                           <div class="col-md-8">
						                                               <input type="text"  id="name" name="name" value="${obj.name }" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">角色编号</label>
						                                           <div class="col-md-8">
						                                               <input type="text" id="code" name="code" value="${obj.code }"  class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                               </div>
						                               <!--/row-->
						                               <div class="row">
						                                   <div class="col-md-6">
						                                       <div class="form-group">
						                                           <label class="control-label col-md-4">角色描述</label>
						                                           <div class="col-md-8">
						                                               <input type="text" id="description" name="description" value="${obj.description }" class="form-control">
						                                           </div>
						                                       </div>
						                                   </div>
						                                   <!--/span-->
						                                   <div class="col-md-6">
						                                     <div class="form-group">
					                                             <div class="mt-radio-inline">
					                                             		<label class="control-label col-md-4">状态</label>					                   
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="status" value="1" <c:if test="${obj.status != '0'}">checked="checked"</c:if>> 启用
					                                                        <span></span>
					                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="status" value="0" <c:if test="${obj.status == '0'}">checked="checked"</c:if>> 禁用
					                                                        <span></span>
					                                                    </label>
					                                              </div>
					                                       </div>
					                                       </div>
						                               </div>
						                           </div>
						                           <div class="form-actions right">
					                                    <button type="button" class="btn default" onclick="window.history.back();">返回</button>
					                                    <button type="submit" onclick="saveBtn()" class="btn green-turquoise">
					                                        <i class="fa fa-check"></i> 保存</button>
					                                </div>
						                       </form>
						                       <!-- END FORM-->
						                   </div>
						               </div>
					                </div>
					                <div class="tab-pane" id="tab_1">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>资源 </div>
						                   </div>
						                   <div class="portlet-body form">
						                     	<ul id="treeDemo" class="ztree"></ul>						                      
						                   </div>
						               </div>
					                </div>
					                <div class="tab-pane" id="tab_2">
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption">
						                           <i class="fa fa-plus-square"></i>用户</div>
						                   </div>
						                   <div class="portlet-body form">
						                       <!-- BEGIN FORM-->
						                       <select id="user" class="form-control" size="10" >
												</select>
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
		<!-- END CONTAINER -->

		<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
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
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.core.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.excheck.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.exedit.js"></script>
			<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
	<script>
	</script>
	</body>

</html>