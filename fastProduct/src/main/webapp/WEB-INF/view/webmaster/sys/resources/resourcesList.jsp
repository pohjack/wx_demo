<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no"/>
<title>用户中心</title>
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/skin/dermadefault.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/skin/dermagreen.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/skin/dermaorange.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/templatecss.css" title="" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/statics/style/style.css" title="" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/statics/script/jquery.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree-3.5.24/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree-3.5.24/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/ztree-3.5.24/js/jquery.ztree.exedit.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/ztree-3.5.24/css/zTreeStyle/zTreeStyle.css"/>

<link href="${pageContext.request.contextPath}/statics/style/font-awesome.min.css" rel="stylesheet" type="text/css" />
<script>
	var setting = {
		view: {
        	dblClickExpand: false,							//双击时是否展开节点
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


	var zTree;
	var zNodes = null;											//初始化数据
	$(function() {														//初始化zTree
		getAjaxTreeList();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree=$.fn.zTree.getZTreeObj("treeDemo");
		if(zNodes.length>0) {
			zTree.expandAll(true);											//展开全部节点
			/* var allNodes = zTree.getNodes();								
			zTree.selectNode(allNodes[0]);									//默认选中第一个
			if(allNodes[0].status==0) {										//判断是否被禁用
				$("#changeState").text("启用");
			} else {
				$("#changeState").text("禁用");
			}
			$("#no1").attr("src", "toEditView?id=" +allNodes[0].id);		//开启Iframe */
		}
	});
	function getAjaxTreeList() {														/* ajax拿到zTree数据 */
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
	function addNode() {																/* 添加节点 */
		var treeNode = zTree.getSelectedNodes();
		if(treeNode[0].level>2) {
			msgLayer("不能大于4级菜单");
			return;
		}
		var id = treeNode[0].id;
		var name = treeNode[0].name;
		$("#no1").attr("src", "toAddView?id=" +id +"&name=" +name);
	}
	function addRootNode() {															/* 添加根节点 */
		$("#no1").attr("src", "toAddView?id=0");
	}
	
	
	function changeStateNode() {																/* 禁用ajax */
		var treeNode = zTree.getSelectedNodes();
		var changeStateName = (treeNode[0].status==0)?"启用":"禁用";
		var mesg = "确认" +changeStateName +" 节点 -- " + treeNode[0].name + " 吗？，该操作会" +changeStateName +"该节点下所有子节点";
		layer.msg(mesg , {
			icon:3,
		  	time: 0 //不自动关闭
		  	,btn: ['确定', '取消']
		  	,offset: '70px'
		  	,yes: function(index){
			  	layer.close(index);
			  	var childrenNodes = zTree.transformToArray(treeNode);
				var arr = new Array();									//存放节点里面的id		
				for(var i=0; i<childrenNodes.length; i++) {
					arr[i] = childrenNodes[i].id;
				}
				if(treeNode[0].status==0) {								//当选中禁用时
					var treeNodeParent = treeNode[0].getParentNode();
					while(treeNodeParent!=null && treeNodeParent.status==0) {
						arr.push(treeNodeParent.id);
						childrenNodes.push(treeNodeParent);
						treeNodeParent = treeNodeParent.getParentNode();
					}
				}
				$.ajax({
					url: "changeState",
					data: {
						"arr": arr,
						"status": treeNode[0].status
					},  
					async: false, 
					traditional: true,
					type: "post",
					dataType: "text",
					success: function(data) {
						if(data == "true") {
							var childrenNodesStatus = childrenNodes[0].status;
							for(var i=0; i<childrenNodes.length; i++) {
								if(childrenNodesStatus == 0) {
									$("#" +childrenNodes[i].tId +"_span").css("color","black");
									$("#" +childrenNodes[i].tId +"_a").css("color","black");
									childrenNodes[i].status=1;
								} else {
									$("#" +childrenNodes[i].tId +"_span").css("color","red");
									childrenNodes[i].status=0;
								}
								zTree.updateNode(childrenNodes[i]);
							}
							$("#changeState").text((treeNode[0].status==0)?"启用":"禁用");
						}else {
							msgLayer(data);
						}
					}
				});
		 	}
		});
	}
	function removeNode() {																	/* 删除ajax */
		var treeNode = zTree.getSelectedNodes();	
		var mesg = "确认删除 节点 -- " + treeNode[0].name + " 吗？，该操作会删除该节点下所有子节点";
		layer.msg(mesg , {
			icon:3,
		  	time: 0 //不自动关闭
		  	,btn: ['确定', '取消']
		  	,offset: '70px'
		  	,yes: function(index){
			 	 layer.close(index);
			 	var childrenNodes = zTree.transformToArray(treeNode);
				var arr = new Array();									//存放节点里面的id		
				for(var i=0; i<childrenNodes.length; i++) {
					arr[i] = childrenNodes[i].id;
				}
				$.ajax({
					url: "removeRes",
					data: {
						"resIds": arr
					},  
					async: false, 
					traditional: true,
					type: "post",
					dataType: "text",
					success: function(data) {
						if(data == "true") {
							zTree.removeNode(treeNode[0]);
							$("#no1").attr("src", "");		//关闭Iframe
						}else {
							msgLayer(data);
						}
					}
				});
		 	}
		});
	}
	
	function zTreeOnClick(event, treeId, treeNode) {				//单击事件
		if(treeNode.status==0) {
			$("#changeState").text("启用");
		} else {
			$("#changeState").text("禁用");
		}
		$("#no1").attr("src", "toEditView?id=" +treeNode.id);	//跳转至编辑页面
	}
	$(function(){
		$(".navul li").mouseenter(function(){						//样式
			$(this).addClass("navulmouse");
			$(".vertical").removeClass("navulmouse");
	    });
		$(".navul li").mouseleave(function(){
			$(this).removeClass("navulmouse");
		});
	});
	function treeNodeEdit(id, name, sort, img) {					//编辑节点		
		var treeNode = zTree.getNodeByParam("id", id, null);
		treeNode.name = name;
		treeNode.sort = sort;
		$("#" +treeNode.tId +"_a").find("i").removeClass();
		$("#" +treeNode.tId +"_a").find("i").addClass("fa " +img);
		zTree.updateNode(treeNode);
	}
	function myMoveNode(id, pId) {								//判断移动后菜单是否大于4级
		var treeNode = zTree.getNodeByParam("id", id, null);
		var parentNode = zTree.getNodeByParam("id", pId, null);
		var allChildren = treeNode.children;
		var maxLevel=0;
		if(allChildren!=null && allChildren.length>0) {
			for(var i=0; i<allChildren.length; i++) {
				if(maxLevel<allChildren[i].level) {
					maxLevel = allChildren[i].level;
				}
			}
		}
		maxLevel -=treeNode.level;
		var parentNodeLevel = 0;
		if(parentNode != null) {
			parentNodeLevel = parentNode.level;
		}
		if((maxLevel+parentNodeLevel)>2) {
			msgLayer("菜单不能大于4级");
			return false;
		} else {
			
		}
	}
	
	function queryMoveNode(id, pId) {
		var treeNode = zTree.getNodeByParam("id", id, null);
		var parentNode = zTree.getNodeByParam("id", pId, null);
		zTree.moveNode(parentNode, treeNode, "inner");
	}
	
	function editLeadNode(id, pId) {								//拿到除自己以及自己子类以外的所有节点
		var treeNode = zTree.getNodeByParam("id", id, null);
		if(treeNode.level == 0) {
			return "<option value='0' readonly='readonly'>最上级</option>";
		}
		var opetion;
		var rootNodes = zTree.getNodes();							//拿到根节点
		var all = zTree.transformToArray(rootNodes);				//拿到所有节点
		var indent;
		opetion = "<option value='0' selected='selected'>最上级</option>";
		for(var i=0; i<all.length; i++) {
			if(id!=all[i].id && id!=all[i].pid && all[i].level<3) {
				if(all[i].level==1){
					indent = "&nbsp&nbsp&nbsp&nbsp┗";
				} else if(all[i].level==2) {
					indent = "&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp┗";
				} else {
					indent = "";
				}
				if(pId == all[i].id) {
					opetion +="<option value=" +all[i].id +" selected='selected'>" +indent +all[i].name +"</option>";
				} else {
					opetion +="<option value=" +all[i].id +">" +indent +all[i].name +"</option>";
				}
			}
		}
		return opetion;
	}
	function bySort(id, sort) {										//修改节点顺序
		var treeNode = zTree.getNodeByParam("id", id, null);
		var brethrenNodes = treeNode.getParentNode();		//拿到父节点
		if(brethrenNodes == null) {							//如果为根节点是返回null
			brethrenNodes = zTree.getNodes();
		} else {
			brethrenNodes = brethrenNodes.children;
		}
		var bretherSort = 0;
		for(var i=0; i<brethrenNodes.length; i++) {
			sort = parseInt(sort);
			bretherSort = parseInt(brethrenNodes[i].sort);
			if(sort<=bretherSort) {
				zTree.moveNode(brethrenNodes[i], treeNode, "prev");
				return;
			}
		}
		zTree.moveNode(brethrenNodes[brethrenNodes.length-1], treeNode,  "next");
	}
	function chooseImage() {
		relationIframeLayer(false, false, "toChooseImages", 850, 200);
	}
	function sureImage(img) {
		no1.window.sureImage(img);
	}
</script>
	<style type="text/css">
		.statusClass {
			color:red;
		}
		a{
			color:black;
			text-decoration:none;
		}
		.navul li a:hover
		{ 
			color:black;
			text-decoration:none;
			background-color:white;
		}
		ul li{
			list-style-type: none;
		}
		.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
		.form-inline input {
			margin:5px 5px 5px 15px;
		}
		.form-inline select {
			margin:5px 5px 5px 15px;
		}
		.ui-popup-show {
			z-index:9999;
		}
	 	ul.ztree {
	 		border-radius:5px; 
			border: 1px solid #eee;
			width:15%;
    		float: left;
    		height: 80%;
    		overflow-y: auto;
		} 
		.right-full {
			left: 10px;
		}
		.down-main { 
			top: 0px;
			margin-left:15px;
			width:98%
		}
		.navul {
			border: 1px solid #eee;
			border-radius:5px; 
			width:100%; 
			height:50px;
			padding:5px;
			margin-top:10px;
			margin-bottom:0px;
		}
		.navul li{
			 width:100px; 
			 height:100%; 
			 text-align:center;
			 padding-top:10px;
			 float: left; 
		}
		.navul li a{
			 width:100px; 
			 height:100%; 
			 text-align:center;
			 padding-top:10px;
			 cursor:pointer;
			 font-size:12px;
		}
		.navul li a:hover
		{ 
			color:#fff;
			background-color:#578EBE;
		}
		.navul .vertical {
			width:5px;
			color:#c0c0c0;
		}
		.rootBtn {
			width:100%;
			height:35px;
			background-color: #f7f7f7;
			border-color: #adadad;
			border: 1px solid transparent
		}
		.fa {
			width: 18px;
    		font-size: 14px;
		}
	</style>
</head>

<body>


<!-- Copyright � 2008. Spidersoft Ltd -->
<style>
A.applink:hover {border: 2px dotted #DCE6F4;padding:2px;background-color:#ffff00;color:green;text-decoration:none}
A.applink       {border: 2px dotted #DCE6F4;padding:2px;color:#2F5BFF;background:transparent;text-decoration:none}
A.info          {color:#2F5BFF;background:transparent;text-decoration:none}
A.info:hover    {color:green;background:transparent;text-decoration:underline}
</style>
<div class="down-main">
	<div class="content_wrap" style="height: 98%;">
			<div>
				<h4 style="font-weight: bold;">菜单资源</h4>
				<hr style="margin:0">
			</div>
			<ul class="navul">
				<li>
					<a onclick="addRootNode()">添加根节点</a>
				</li>
				<li>
					<a onclick="addNode()">添加子节点</a>
				</li>
				<li>
					<a id="changeState" onclick="changeStateNode()">禁用</a>
				</li>
				<li>
					<a onclick="removeNode()">删除</a>
				</li>
			</ul>
			<ul id="treeDemo" class="ztree" ></ul>
			<div style="height:80%; width:84.5%; position:relative;float:right; border: 1px solid #eee;border-radius:5px; ">
				<iframe id="no1" name="no1"  height="100%" width="100%"  frameborder=0  noresize >
			</div>
	</div>
	<input type="hidden" id="img">
</div>
<script type="text/javascript">

</script>
</body>
</html>
