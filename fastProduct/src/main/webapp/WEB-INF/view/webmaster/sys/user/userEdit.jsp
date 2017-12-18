<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-用户编辑</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		
		
		<link href="${pageContext.request.contextPath}/statics/style/plugins/time/daterangepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/select/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.form_datetime {
				font-size: 12px
			}
			.table-condensed {
				font-size: 12px
			}
			.col-md-8 {
				margin-bottom: 15px
			}
			.errorInput{
					box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 6px #ce8483;
			}
			ul.ztree {
   				 margin-top: 0px;
   				 border: 1px solid #617775;
  				 background: #fdfdfd;
  				 overflow-y: scroll;
    			 overflow-x: auto;
   	 			 width:100% ;
                 display: none;
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
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<form onsubmit="return false;" id="myForm">
		<input type="hidden" id="id" name="id" value="${user.id }">
		<input type="hidden" id="roleids" name="roleids" value="">
		<input type ="hidden" name="password"  value="${user.password }">
		<div class="page-container" >
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="page-content" id="selfDivParent">
					<div class="row">
					    <div class="col-md-12" >
					        <div class="tabbable-line boxless tabbable-reversed" >
					                	<div class="portlet box green-turquoise">
						                   <div class="portlet-title">
						                       <div class="caption"><i class="fa fa-plus-square"></i>用户信息 </div>
						                   </div>
						                   <div class="portlet-body form" id="selfDiv">
					                           <div class="form-body">
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">用户名</label>
					                                           <div class="col-md-8">	                                           		
					                                            	<input type="text" name="loginName" id="loginName" value="${user.loginName }"  <c:if test="${empty user.id }">   onblur="checkLoginName();"</c:if> <c:if test="${not empty user.id }">    readonly="readonly"</c:if>  maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					              
					              						<div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">真实姓名</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="realName" id="realName" value="${user.realName }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   	</div>
					                               </div>
					                               <div class="row">					                                  
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">执法证号</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="cardId" id="cardId" value="${user.cardId}" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">邮箱</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="email" id="email" value="${user.email }" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>
					                               <div class="row">					                                  
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                             <div class="mt-radio-inline">
					                                             		<label class="control-label col-md-4">状态</label>
					                                             		  <div class="col-md-8">
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="status" value="1" <c:if test="${user.status != '0'}">checked="checked"</c:if>> 启用
					                                                        <span></span>
					                                                    </label>
					                                                    <label class="mt-radio mt-radio-outline">
					                                                        <input type="radio" name="status" value="0" <c:if test="${user.status == '0'}">checked="checked"</c:if>> 禁用
					                                                        <span></span>
					                                                    </label>
					                                                    </div>
					                                              </div>
					                                       </div>
					                                   </div>
					                                    <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">手机号</label>
					                                           <div class="col-md-8">
					                                               <input type="text" name="mobile" id="mobile" value="${user.mobile}" maxlength="20" class="form-control">
					                                           </div>
					                                       </div>
					                                   </div>
					                               </div>					                              
					                               <h3 class="form-section"></h3>
					                               <div class="row">
					                                   <div class="col-md-6">
					                                       <div class="form-group">
					                                           <label class="control-label col-md-4">机构选择</label>
					                                             <div class="col-md-8">
                                                     				 <input type="text" value="" id="orgValue" readonly="readonly" class="form-control">
                                                 					 <input type="hidden" name="orgId" id="orgId">
                                                 					 <ul id="treeDemo" class="ztree" ></ul>
                                                				 </div>
					                                       </div>
					                                   </div>
					                                   <div class="col-md-6">
					                                    <div class="form-group">
                                                				<label>角色选择</label>                                              				
                                                		<div class="mt-checkbox-line" >                                               		
                                                		  <c:forEach items="${roleList}" var="rl">
          														 <label class="mt-checkbox"> ${rl.name}
                                                       						 <input type="checkbox" value="${rl.id}" name="roleAll">
                                                       			 <span></span>
                                                    			</label>				
        												  </c:forEach> 
                                               			 </div> 
                                               			 </div>                                      				
					                                   </div>
					                               </div>					                          					                           
					                           </div>
					                           <div class="form-actions right">
													<button type="button" class="btn default" onclick="window.history.back();" id="backBtn">返回</button>
				                                    <button type="submit" class="btn green-turquoise" id="saveBtn"> 
				                                    	<i class="fa fa-check"></i> 保存
				                                    </button>
					                           </div>
						                   </div>
						               </div>
					        </div>
					    </div>
					</div>
				</div>
			</div>
		</div>
		</form>
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
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-fileinput.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/select2.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/select/components-select2.min.js" type="text/javascript"></script>
        
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>	
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/password.js"></script>
		
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/ztree-3.5.24/js/jquery.ztree.exedit.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/plugins/ztree-3.5.24/css/zTreeStyle/zTreeStyle.css"/>
		
		<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>

		<script type="text/javascript">
		
		//树
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
	var orgId= "${user.orgId}";
	$(function() {														//初始化zTree
		getAjaxTreeList();
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		zTree=$.fn.zTree.getZTreeObj("treeDemo");
		if(zNodes.length>0) {
			zTree.expandAll(true);	
			//展开全部节点
			var allNodes = zTree.getNodes();
			for(var i=0;i<allNodes.length;i++){
				if(orgId==allNodes[i].id){	
					zTree.selectNode(allNodes[i]);
					$("#orgId").val(allNodes[i].id);
				    $("#orgValue").val(allNodes[i].name);
				}else{
					   var  nodes=  allNodes[i].children ;	
						for(var j=0; j<nodes.length; j++) {
							if(orgId==nodes[j].id){
								zTree.selectNode(nodes[j]);
								$("#orgId").val(nodes[j].id);
							    $("#orgValue").val(nodes[j].name);
							}
						}			
				}
			}
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
		if(name=="办公室"){                                              //选中机构 中的办公室   角色默认为超级管理员
			  $("input[name='roleAll']").each(function(){   
			       if($(this).val()=="2")  {
			    	   this.checked=true;   
			       }else{
			    	   this.checked=false;   
			       }		       
			    });
		}else{
			  $("input[name='roleAll']").each(function(){   
			       if($(this).val()=="3")  {
			    	   this.checked=true;   
			       }else{
			    	   this.checked=false;   
			       }		       
			    });
		}
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
	
		
		var currentRole ="${currentRoleIds}";
		var type="${type}";
		
		function initRole(){			
			if(currentRole!=""){
				 $("[name = roleAll]:checkbox").each(function () {
					 for(var i = 0 ; i<currentRole.split(',').length; i++){
						 if (currentRole.split(',')[i]==($(this).val())) {
		                	   $(this).attr("checked",'checked')
		                   }
						}
	               });
			}
		}
		
		function getCheckBoxData(){
			 var aa = document.getElementsByName("roleAll");
             var ss = "";
             for (var i = 0; i < aa.length; i++) {
                 if (aa[i].checked) {
                     ss += aa[i].value+",";
                 }
             }
             if(ss.length>0){
            	ss= ss.substring(0,ss.length-1);
             }
             $("#roleids").val(ss);
		}
		
		$(function() {
			if(type=='self'){
				$("#backBtn").hide();//隐藏"返回"
				var selfDiv=$("#selfDiv").html();
				$("#selfDivParent").html(selfDiv);//调整页面结构
				
			}
			initRole(); //初始化角色
			$('#password').password().on('show.bs.password', function(e) {}).on('hide.bs.password', function(e) {});
			
		});
		function submitClickOut(){
			var flag =true;
			var realName = $("#realName").val();
			var cardId =$("#cardId").val();
			if(realName==""){
				overlyTipsLayer("真实姓名不能为空", "#FF4500", 6000, 2, $("#realName"), true);  
				$("#realName").addClass("errorInput");
				flag= false;
			}else if(cardId==""){
				overlyTipsLayer("执法证号不能为空", "#FF4500", 6000, 2, $("#cardId"), true);  
				$("#cardId").addClass("errorInput");
				flag= false;
			}
			return  flag;
		}
			$("#saveBtn").click(function(){
				getCheckBoxData(); 
				var submitClick = submitClickOut();
				var flag  = true;
				var  id = $("#id").val();  //判断是否是编辑
				if(id==""){
					flag=checkLoginName();
				}
				if(submitClick&&flag){				
					$.ajax({
				        type: "POST",
				        url:"${pageContext.request.contextPath}/webmaster/sys/user/saveOrUpdateUser",
				        data:$('#myForm').serialize(),// 你的formid
				        dataType:"JSON",
				        success: function(data) {
				        	top.layer.msg("编辑成功");
				        	window.location.href="${pageContext.request.contextPath}/webmaster/sys/user/toList";
				        	if(type=='self'){
				        	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				        	parent.layer.close(index);
				        	}				  
				        },
				        error: function(request) {
			        		 layer.msg("Connection error\n");
				        }
				    });
				}
			});
			
		function checkLoginName(){
			var loginName = $("#loginName").val();
			var msg ="";
			var flag =true;
			if(loginName!=""){
			$.ajax({
		        type: "POST",
		        url:"${pageContext.request.contextPath}/webmaster/sys/user/toCheckName",
		        data:{"loginName":$("#loginName").val()},// 你的formid
		        async: false,
		        dataType:"JSON",
		        success: function(data) {				        	
		        	if(data.msg=="true") {
						$("#loginName").removeClass("errorInput");
					} else {
						msg="用户名已存在";
						overlyTipsLayer(msg, "#FF4500", 6000, 2, $("#loginName"), true);  
						$("#loginName").addClass("errorInput");
						flag=false;
					}
		        },		    
		    });
			}else{
				msg="用户名不能为空";
				overlyTipsLayer(msg, "#FF4500", 6000, 2,$("#loginName"), true); 
				$("#loginName").addClass("errorInput");
				flag=false;
			}
			return flag;
		}	
		</script>
	</body>
</html>