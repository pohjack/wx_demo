<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-文书模板管理</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/script/layer/skin/default/layer.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			#docBtn a{
				cursor: pointer;
			}
			#choosed li div a{
				cursor: pointer;
			}
			.hoverflag{
				color: #CC9933 !important;
			}
			#choosed .box_c{   
			 	position: absolute;
		    	top: 9px;
		    	right: 30px;
		    }
		    #subDoc a{
			    position: relative;
			    display: inline-block;
			    height: 30px;
			    line-height: 30px;
			    width: 130px;
			    color: #666;
			    padding-left: 15px;
			    margin-bottom: 15px;
			    margin-right: 10px;
			    background: #fafafa;
			    font-size: 14px;
			    text-decoration: none;
			    overflow: hidden;
			    -moz-box-shadow: 0px 2px 5px #eaeaea;
			    -webkit-box-shadow: 0px 2px 5px #eaeaea;
			    box-shadow: 0px 2px 5px #eaeaea;
			    -webkit-transition: all .2s .0s ease-in-out;
			    -moz-transition: all .2s .0s ease-in-out;
			    -o-transition: all .2s .0s ease-in-out;
			    transition: all .2s .0s ease-in-out;
			    cursor: pointer;
		    }
		    #subDoc a:HOVER {
				background: #E7505A;
    			color: #fff;
			}
		    #subDoc .in {
				background: #E7505A;
    			color: #fff;
			}
			#subDoc a i{
				background:none !important;
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
				<div class="templet">
					<!-- <h3 class="page-title">文书模块管理 </h3> -->
					<div class="module" id="docBtn">
					<input type="hidden" id="docId" value="" />
						<a data-modelNo="2" class="btn-0 in"><i class="fa fa-pencil bg-blue"></i>检查（勘验）笔录</a>
						<a class="btn-2" data-modelNo="1"><i class="fa fa-book bg-red"></i>立案报告表</a>
						<a data-modelNo="3" class="btn-7"><i class="fa fa-eyedropper bg-blue-steel"></i>涉案烟草专卖品鉴定结论告知书</a>
						<a data-modelNo="4" class="btn-1"><i class="fa fa-inbox bg-green"></i>案件调查终结报告</a>
						<a data-modelNo="5" class="btn-3"><i class="fa fa-gavel bg-yellow"></i>案件处理审批表</a>
						<a data-modelNo="6" class="btn-8"><i class="fa fa-commenting-o bg-purple-sharp"></i>行政处罚事先告知书</a>
						<a data-modelNo="7" class="btn-5"><i class="fa fa-balance-scale bg-red-thunderbird"></i>行政处罚决定书</a>
						<a data-modelNo="8" class="btn-6"><i class="fa fa-hourglass-end bg-grey-mint"></i>结案报告表</a>
						<a data-modelNo="9" class="btn-6"><i class="fa-file-text"></i>案卷封页</a>
						<a data-modelNo="10" class="btn-4"><i class="fa fa-money bg-purple"></i>卷烟价值估算申请表</a>
					</div>
					<div class="submodule" style="padding: 15px 0 10px;">
							<input type="hidden" id="subTag" value="1" />
						<div id="subDoc">
						</div>
						<div class="list">
							<h5>基础模板</h5>
							<ul id="baseTempList">
							<c:forEach items="${baseTemps }" var="bt">
								<li style="cursor: pointer;"><a data-tempId="${bt.id }">${bt.name }</a><div class="box_b">${bt.content }</div></li>
							</c:forEach>
							</ul>
						</div>
						<div class="list selected">
							<h5>已选模块</h5>
							<ul id="choosed">
								<!-- <li><a href="">模版1模版1模版1模版1模版1</a><div class="box_a"><a><i class="fa fa-close font-red-thunderbird"></i></a></div></li> -->
							</ul>
						</div>
						<div class="look">
							<div class="inspect print" style="padding-top:0 ;">
								<div id="content">
								
								</div>
								<!-- <div class="button">
									<button type="submit" class="btn green-turquoise">
								    <i class="fa fa-check"></i> 保存</button>
								</div> -->
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
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script type="text/javascript">
			$("#docBtn").on("click","a",function(){
				$(this).siblings().removeClass("in");
				$(this).addClass("in");
				$("#subDoc").empty();
				var mid = $(this).attr("data-modelNo");
				$("#subTag").val("1");
				changeDocTemp(mid,"1");
				loadTags(mid);
			})
			function loadTags(mid){
				var tags = "";
				switch (mid) {
				case "1":
					tags = '<a class="btn-2 in" data-tag="1"><i class="fa fa-book bg-red"></i>案情摘要</a><a class="btn-2" data-tag="2"><i class="fa fa-book bg-red"></i>承办人意见</a>';
					break;
				case "4":
					tags = '<a class="btn-2 in" data-tag="1"><i class="fa fa-book bg-red"></i>调查事实</a>'
					+'<a class="btn-2" data-tag="2"><i class="fa fa-book bg-red"></i>处罚依据</a><a class="btn-2" data-tag="3"><i class="fa fa-book bg-red"></i>处理意见</a>';
					break;
				case "5":
					tags = '<a class="btn-2 in" data-tag="1"><i class="fa fa-book bg-red"></i>案件事实</a><a class="btn-2" data-tag="2"><i class="fa fa-book bg-red"></i>处罚依据</a>'
					+'<a class="btn-2" data-tag="3"><i class="fa fa-book bg-red"></i>承办人意见</a>';
					break;
				case "8":
					tags = '<a class="btn-2 in" data-tag="1"><i class="fa fa-book bg-red"></i>案情摘要</a><a class="btn-2" data-tag="2"><i class="fa fa-book bg-red"></i>处理决定</a>'
					+'<a class="btn-2" data-tag="3"><i class="fa fa-book bg-red"></i>执行情况</a><a class="btn-2" data-tag="4"><i class="fa fa-book bg-red"></i>承办人理由</a>';
					break;
				default:
					break;
				}
				$("#subDoc").append(tags);
			}
			
			$("#subDoc").on("click","a",function(){
				$(this).siblings().removeClass("in");
				$(this).addClass("in");
				var tag = $(this).attr("data-tag");
				$("#subTag").val(tag);
				changeDocTemp($("#docId").val(),tag);
			})
			function changeDocTemp(mid,tags){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/template/getTempInfo",
					dataType : "json",
					data : {
						"modelNo" : mid,
						"tags" : tags
					}, 
					success : function(data) {
						if(data != null && data != ""){
							$("#content").empty();
							$("#choosed").empty();
							$("#docId").val(mid);
							$("#content").html(data.autoGenerate);
							for(var i = 0; i < data.baseTemplates.length; i++){
								/* var $li = "<li class='dd-item'  data-id='"+data.baseTemplates[i].id+"'><div class='dd-handle' tempId='"+data.baseTemplates[i].id+"'>"+data.baseTemplates[i].name+"</div> </li>"; */
								var $li = "<li tempId='"+data.baseTemplates[i].bId+"' docId='"+data.baseTemplates[i].dId+"'><a>"+data.baseTemplates[i].name+"</a><div class='box_a'><a><i class='fa fa-close font-red-thunderbird'></i></a></div>"
								+ "<div class='box_c'><a><i class='fa fa-sort-numeric-asc'></i></a></li></div>";
								$("#choosed").append($li);
							}
						}
					}
				});
			}
			changeDocTemp("2","1");
			
			$("#choosed").on("mouseover","li",function(e){
				e.stopPropagation();
				var tid = $(this).attr("tempid");
				$("#content span[btid='"+tid+"']").addClass("hoverflag");
				$("#content span[btid='"+tid+"']").children().addClass("hoverflag");
			})
			$("#choosed").on("mouseout","li",function(e){
				e.stopPropagation();
				var tid = $(this).attr("tempid");
				$("#content span[btid='"+tid+"']").removeClass("hoverflag");
				$("#content span[btid='"+tid+"']").children().removeClass("hoverflag");
			})
			$("#choosed").on("click",".box_a",function(){//删除
				var docSlaveid = $(this).parent().attr("docId");
				var name = $(this).siblings().text();
				top.layer.confirm('确定要移除「'+ name +'」吗？', function(index){
					var docId = $("#docId").val();
					removeBaseFromDoc(docSlaveid,docId);	
					layer.close(index);
				});
			})
			$("#choosed").on("click",".box_c",function(){//排序
				var tid = $(this).parent().attr("docId");
				var tid2 = $(this).parent().next().attr("docId");
				var docId = $("#docId").val();
				if($(this).parent().index() != $("#choosed li").length -1){
					changeSort(tid,tid2);
					changeDocTemp(docId);
				}else{
					top.layer.open({
	        			  icon:7,
	        			  content: '已经在最底部了！' 
	        			});
				}
			})
			$("#baseTempList li").on("click","a",function(){
				var tempId = $(this).attr("data-tempId");
				var docId = $("#docId").val();
				var type = $("#subTag").val();
				addBaseToDoc(tempId,docId,type);
			})
			function addBaseToDoc(tempId,docId,tags){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/template/addBaseToDoc",
					dataType : "json",
					data : {
						"baseId" : tempId,
						"modelNo" : docId,
						"tags" : tags
					}, 
					success : function(data) {
						if(data != null && data != ""){
							if(data.result){
								changeDocTemp(docId,tags);
							}
						}
					}
				});
			}
			
			function removeBaseFromDoc(tid,docId){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/template/rempveBaseFromDoc",
					dataType : "json",
					data : {
						"baseId" : tid
					}, 
					success : function(data) {
						if(data != null && data != ""){
							if(data.result){
								$("#content span[btId='"+tid+"']").remove();
								$("#choosed li[tempId='"+tid+"']").remove();
								top.layer.open({
				        			  icon:1,
				        			  content: '移除成功！' 
				        			});
								changeDocTemp(docId,$("#subTag").val());
							}
						}
					}
				});
			}
			function changeSort(tid,tid2){
				$.ajax({
					type : "POST",
					url : "${pageContext.request.contextPath}/template/changeSort",
					dataType : "json",
					async:false,
					data : {
						"baseId" : tid,
						"baseIdAfter" : tid2
					}, 
					success : function(data) {
						if(data != null && data != ""){
							if(data.result){
								return true;
							}else{
								top.layer.open({
				        			  icon:7,
				        			  content: '系统异常，请稍候再试！' 
				        			});
							}
						}
					}
				});
			}
		</script>
	</body>

</html>