<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草基础模板管理</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/script/layer/skin/default/layer.css" rel="stylesheet" type="text/css" />
		<%-- <link href="${pageContext.request.contextPath}/statics/script/plugins/jquery-nestable/jquery.nestable.css" rel="stylesheet" type="text/css" /> --%>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
		<!-- BEGIN HEADER & CONTENT DIVIDER -->
		<div class="clearfix"> </div>
		<!-- END HEADER & CONTENT DIVIDER -->
		<!-- BEGIN CONTAINER -->
		<div class="page-container">
			<!-- BEGIN CONTENT -->
			<div class="page-content-wrapper">
				<div class="templet">
					<!-- <h3 class="page-title">模块管理 </h3> -->
					<!-- <div class="module" id="docTemp">
						<a docId="2" class="btn-0 in"><i class="fa fa-pencil bg-blue"></i>检查（勘验）笔录</a>
						<a docId="4" class="btn-1"><i class="fa fa-inbox bg-green"></i>案件调查终结报告</a>
						<a docId="1" class="btn-2"><i class="fa fa-book bg-red"></i>立案报告表</a>
						<a docId="5" class="btn-3"><i class="fa fa-gavel bg-yellow"></i>案件处理审批表</a>
						<a docId="10" class="btn-4"><i class="fa fa-money bg-purple"></i>卷烟价值估算申请表</a>
						<a docId="7" class="btn-5"><i class="fa fa-balance-scale bg-red-thunderbird"></i>行政处罚决定书</a>
						<a docId="8" class="btn-6"><i class="fa fa-hourglass-end bg-grey-mint"></i>结案报告表</a>
					</div> -->
					<div class="submodule">
						<div class="list dd">
							<h5>模板列表<a class="plus" id="plusTemp"><i class="fa fa-plus"></i></a></h5>
							<ul id="subModel"  class="dd-list">
								<c:forEach items="${baseTemplates }" var="base">
									<li tempId="${base.id }"><a>${base.name }</a></li>
								</c:forEach>
							</ul>
						</div>
						<div class="look">
						    <fieldset>
						      <legend><span>效果预览</span></legend>
						      <div class="box">
						      	<div class="text" id="content">
						      	</div>
						      	<div class="button">
									<button type="submit" class="btn green-turquoise" id="modify">
								    <i class="fa fa-edit"></i> 修改</button>
						      	</div>
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
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<script type="text/javascript">
		/* $("#docTemp a").click(function(){
			$(this).siblings().removeClass("in");
			$(this).addClass("in");
			var id = $(this).attr("docId");
			$("#subModel").empty();
			getBaseTemp(id);
			$("#subModel").attr("modelNo",id);
		}) */
		
		/* $(function(){//拖动排序插件初始化
			  $('.dd').nestable({
				 "listNodeName":"ul"
			 }).click(cl); 
		}) 
		$(".dd").on('change', function(){
			var data = $('.dd').nestable('serialize');
			for ( var i in data) {//返回拖动排序后的结果
				//alert(data[i].id);
			}
		});
		function cl(e, obj){
			var tempid = $(obj).attr("tempId");
			$("#modify").attr("tempName",$(obj).text());
			var modelNo1 = $("#subModel").attr("modelNo");
			getSubTemp(tempid,modelNo1);
			//iframeAlert(tid,"编辑"+ name,modelNo);
		}*/
		
		$("#plusTemp").click(function(){
			iframeAlert("","添加基础模板");
		})
		
		$("#subModel").on("click","li",function(){
			$(this).siblings().find("a").removeClass("in");
			$(this).find("a").addClass("in");
			var tempid = $(this).attr("tempId");
			$("#modify").attr("tempName",$(this).text());
			getSubTemp(tempid);
		})
		
		$("#modify").click(function(){
			var tid = $("#tempid").val();
			var name = $("#modify").attr("tempName");
			if(tid != '' && typeof(tid) != 'undefined'){
				iframeAlert(tid,"编辑"+ name);
			}
		})
		
		function getSubTemp(id){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/template/getSubTemplate",
				dataType : "json",
				data : {
					"id" : id,
					"modelNo" : "7"
				}, 
				success : function(data) {
					if(data != null && data != ""){
						$("#content").empty();
						var	html = '<input id="tempid" value="'+id+'" type="hidden" />';
						$("#content").html(data.autoGenerate);
						$("#content").append(html);
					}
				}
			});
		}
		function initData(){
			var first = $("#subModel").find("li:first");
			first.find("a").addClass("in");
			$("#modify").attr("tempName",first.find("a").text());
			var baseId = first.attr("tempId");
			getSubTemp(baseId);
		}
		initData();
		/* function getBaseTemp(id){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/template/getBaseTemplate",
				dataType : "json",
				data : {
					"modelNo" : id
				}, 
				success : function(data) {
					if(data != null && data != ""){
						$("#content").empty();
						$("#subModel").empty();
						$("#content").html(data.autoGenerate);
						for(var i = 0; i < data.baseTemplates.length; i++){
							/* var $li = "<li class='dd-item'  data-id='"+data.baseTemplates[i].id+"'><div class='dd-handle' tempId='"+data.baseTemplates[i].id+"'>"+data.baseTemplates[i].name+"</div> </li>"; */
							/*var $li = "<li tempId='"+data.baseTemplates[i].id+"'><a>"+data.baseTemplates[i].name+"</a></li>";
							$("#subModel").append($li);
						}
						//$("#subModel").find('li:first a').addClass("in");
					}
				}
			});
		}
		getBaseTemp("2"); */
		
		/* $("#subModel").on("click","li",function(){
			var tid = $(this).attr("tempId");
			var name = $(this).find("div").text();
			var modelNo = $("#subModel").attr("modelNo");
			iframeAlert(tid,"编辑"+ name,modelNo);
		}) */
		
	/* 	$("#plusTemp").click(function(){
			iframeAlert("","添加子模板","");
		}) */
		
		function iframeAlert(tid,title,modelNo){
			 top.layer.open({
				  type: 2, //2表示弹出层是iframe
				  id:"baseTempEdit",
				  title: title,
				  area: ['90%', '88%'],
				  maxmin: true, //是否显示最大化最小化按钮
				  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
				  closeBtn: 1,
				  shadeClose: true,
				  content: ["${pageContext.request.contextPath}/template/toTempAdd?tid="+tid + "&modelNo="+ modelNo,'no'], //no表示不显示滚动条
				  btn:['确认', '退出'],
				  yes: function(index, layero){
						var cont = $(layero).find("iframe")[0].contentWindow.getContent();
						var tempName = $(layero).find("iframe").contents().find("#tempName").val();
						var tempId = $(layero).find("iframe").contents().find("#masterId").val();
						updateBaseTemp(tempId,cont,tempName);
						top.layer.close(index);
						initData();
				  },
				  end:function(){
					  //alert(parent.$("#status").val());
					 /*  if(parent.$("#status").val() != "" && parent.$("#status").val() != "undefined"){
						  rollBack(tid);
					  } */
				  }
				});
		}
		//回滚
		/* function rollBack(tid){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/template/rollBack",
				dataType : "json",
				data : {
					"tid" : tid
				}, 
				success : function(data) {
				}
			});
		} */
		//保存子模板更新
		function updateBaseTemp(tid,cont,tempName){
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}/template/saveBTEdit",
				dataType : "json",
				data : {
					"id" : tid,
					"content": cont,
					"name": tempName
				}, 
				success : function(data) {
					if(data){
						top.layer.open({
		        			  icon:1,
		        			  content: '保存成功！' 
		        			});
						getSubTemp(tid);
					}else{
						top.layer.open({
		        			  icon:2,
		        			  content: '保存失败，请稍候再试！' 
		        			});
					}
				}
			});
		}
	</script>
	</body>

</html>