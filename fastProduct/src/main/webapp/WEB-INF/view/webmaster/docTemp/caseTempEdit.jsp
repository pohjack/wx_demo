<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件模板编辑</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/datatables.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/script/wangEditor/dist/css/wangEditor.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/script/layer/skin/default/layer.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			input {
				outline: none;
			}
			#sysVars_paginate .paginate_button {
				border: solid 1px ;
				padding: 2px;
				color: #FFFFFF !important;
			    background-color: #217ebd;
			    border-color: #1f78b5;
			    margin-left: 5px;
			}
			.paginate_input{
				margin-left: 5px;
			}
			table.dataTable tbody tr{
				cursor: pointer;
			}
	</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" oncontextmenu="return false">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="templet">
				<input type="hidden" name="id" value="${id }" id="masterId" /> 
					<%-- <h3 class="page-title">编辑案件内容 </h3>
					<div class="title">
						<label>模板名称</label>
						<p>
							<input type="text" class="form-control" value="${name }" id="tempName">
						</p>
					</div> --%>
					<div class="main-fieldset">
						<div class="preview">
						    <fieldset>
						      <legend><span>效果预览</span></legend>
						      <div class="text">${preview }</div>
						    </fieldset>
					    </div>
						<div class="edit">
						    <fieldset class="left">
						      <legend><span>内容编辑</span></legend>
						      <div class="text" id="ed">
						      	<textarea class="form-control" id="contEdit">${edit }</textarea>
						      </div>
						    </fieldset>
						    <fieldset class="right">
						      <legend><span>系统变量选择</span></legend>
						      <div class="text">
						      	<div class="input-group">
                                     <input type="text" class="form-control" id="search" onkeyup="value=value.replace(/[\#\$\{\}]/g,'')" placeholder="输入要搜索的内容..." style="width: 512px;"/>
                                   <!--  <span class="input-group-btn">
                                        <button class="btn blue" type="button" id="searchBtn">搜索</button>
                                    </span> -->
                                </div>
                                <div class="variable">
                                	<table width="100%" border="1" cellpadding="0" cellspacing="0" id="sysVars" 
                                	class="hover" style="table-layout: fixed;border-collapse: collapse;">
                                		<thead>
	                                		<tr>
	                                			<td width="15%">序号</td>
	                                			<td width="40%">名称</td>
	                                			<td width="45%">值</td>
	                                			<!-- <td>描述</td> -->
	                                		</tr>
                                		</thead>
                                		<tbody>
                                		</tbody>
                                	</table>
						      </div>
						    </fieldset>
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
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/inputPagingExt.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/wangEditor/dist/js/wangEditor.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
	App.setAssetsPath("");
	App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>
		<script type="text/javascript">
			//var nTrs;
			var editor;
			function  sysVarInfo(){
				 table = $("#sysVars").DataTable({
					"bDestroy":true, 
					"searching":false,
					"bStateSave": true,      
				    "bProcessing": true,                   
				    "bServerSide": true, 
				    "iDisplayLength":10,
				    "ordering":false,
				    "pagingType": "input",
				    "dom":'<"top">rt<"bottom"ip><"clear">',
				    "sAjaxSource": "${pageContext.request.contextPath}/template/getPagingList",    
				    "fnServerData": retrieveData,
				    "autoWidth": false,
				    "oLanguage" : { // 汉化
				    	"sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json"
						},
				     "aoColumns": [ 
				            {"mData" : null, "bSortable" : false}, 
							{"mData" : 'name', "bSortable" : false}, 
							{"mData" : 'value',"bSortable" : false}
							/* {"mData" : 'description'} */
							],
					"fnRowCallback" : function(nRow, aData, iDisplayIndex) { 		
								var secondTDHtml = iDisplayIndex + 1;
								$('td:eq(0)', nRow).html("<span>"+secondTDHtml+"<input type='hidden' value='"+aData.id+"'></span>"); 
								return nRow;
							},
					"initComplete" : function() {
								 //nTrs = table.fnGetNodes();
							}
						});
			 }
			sysVarInfo();
			function retrieveData( sSource,aoData, fnCallback) {		
					var search =$("#search").val();
					var searchDate= '{"search":"'+ search +'"}';       						
					aoData.push( { "name": "searchDate", "value": ""+searchDate+""});		
			     $.ajax({    
			         url : sSource,                               
			         data : {"aoData":JSON.stringify(aoData)},   
			         type : 'post',    
			         dataType : 'json',    
			         async : true,    
			         success : function(result) {    
			             fnCallback(result);                    
			         },    
			     });    
			 }
			
			 function search(){
			 	table.ajax.reload();
			 }
			
			 $("#search").keyup(function(){
					search();
				})
			
			$(function(){
				wangEditor.config.printLog = false;
				editor = new wangEditor('contEdit');
				// 普通的自定义菜单
			    editor.config.menus = [
			        //'|',     // '|' 是菜单组的分割线
			        //'bold',
			        //'underline',
			        //'italic',
			       // 'strikethrough',
			        //'eraser',
			       // 'forecolor',
			       // 'bgcolor'
			     ];
				editor.create();//初始化编辑器
				$("#ed").attr('tabindex', 1).keydown(function(e){//给div绑定键盘事件，div是不能响应这个事件的，所以需要加上tabindex属性并且focus
					 var oEvent = document.all ? window.event : e; 
					 /* if (oEvent.keyCode == 8) {
					 	var selected = window.getSelection();
					 	//var range = selected.getRangeAt(0);
					 	var pare = selected.anchorNode.parentNode;
					 	var tag;
					 	if(pare.tagName.toLowerCase() == "span"){
					 		tag = pare; 
						 	oEvent.preventDefault();
					 	}else if(selected.anchorNode.tagName.toLowerCase() == "div"){
					 		var obj = $(selected.anchorNode).find("span").eq(selected.anchorOffset -1);
					 		tag = obj;
					 		oEvent.preventDefault();
					 	}
					 	parent.$("#status").val('1');
					 	$(tag).remove();
					 }else */ if(oEvent.ctrlKey && (oEvent.keyCode == 90 || oEvent.keyCode == 89 || oEvent.keyCode == 86)){ //禁用Ctrl+Z 和 Ctrl + Y 
						    oEvent.preventDefault();  
						 	window.event.returnValue = false; 
					 }
					
				}).focus();
				
				 
				//系统变量表行的点击事件
				$("#sysVars tbody").on("click","tr",function(){
					var data = table.row($(this)).data();
					//var d = um.selection;
					var cat = data.category;
					var range = editor._rangeData;
					var color = " ";
					if(1 == cat){
						color = "red";
					}else if(2 == cat){
						color = "green";
					}
					$(range.commonAncestorContainer.parentElement).after("<span style='color:"+ color +"'>"+ data.value + "</span>");     
				})
			}) 
			
			function getContent(){
				return editor.$txt.html()/*. replace(/<\/?span.*?>/gi,"") */;
			}
			
	</script>
	</body>

</html>