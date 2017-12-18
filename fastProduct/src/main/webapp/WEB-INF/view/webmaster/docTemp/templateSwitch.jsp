<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板切换</title>
<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/script/layer/skin/default/layer.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/script/wangEditor/dist/css/wangEditor.min.css" rel="stylesheet" type="text/css" />
<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/dateFormat.js" type="text/javascript"></script>
<style type="text/css">
td p {
	margin: 0 !important;
}
/* #content{
		text-indent:25px;
	}
	#dealing{
		text-indent:25px;
	}
	#caseAttr{
		text-indent:25px;
	}
	#punishBasic{
		text-indent:25px;
	}
	#handling{
		text-indent:25px;
	}
	#decision{
		text-indent:25px;
	}
	.inspect table td span.text{
	text-align: left;
    font-size: 0.3cm;
    vertical-align: middle;
	} */


</style>
<script type="text/javascript">
	var caseId = '${caseId}';
	var postAgency = "${postAgency}";
</script>

	<script src="${pageContext.request.contextPath}/statics/script/wangEditor/dist/js/wangEditor.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
</head>
	<div class="switch">
		<button id="editCase" class="btnEdit btn red-mint"  
			onclick="linkTo('${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase')"><i class="fa fa-edit"></i>编辑案件
		</button>
		<button id="hisCase" class="btnEdit btn red-mint"  style="display:none;"
			onclick="linkTo('${pageContext.request.contextPath}/webmaster/caseInfo/toHisView')"><i class="fa fa-search"></i>登记查看
		</button>
		<a id="2" href="${pageContext.request.contextPath}/docTemplate/toInspect?caseId=${caseId}">检查（勘验）笔录</a>
		<a id="1" href="${pageContext.request.contextPath}/docTemplate/toFiling?caseId=${caseId}" class="in">立案报告表</a>
		<a id="3" href="${pageContext.request.contextPath}/docTemplate/toconclusion?caseId=${caseId}">涉案烟草专卖品鉴定结论告知书</a>
		<a id="4" href="${pageContext.request.contextPath}/docTemplate/toFinality?caseId=${caseId}">案件调查终结报告</a>
		<a id="5" href="${pageContext.request.contextPath}/docTemplate/toExamine?caseId=${caseId}">案件处理审批表</a>
		<a id="6" href="${pageContext.request.contextPath}/docTemplate/toPrior?caseId=${caseId}">行政处罚事先告知书</a>
		<a id="7" href="${pageContext.request.contextPath}/docTemplate/toPunish?caseId=${caseId}">行政处罚决定书</a>
		<a id="8" href="${pageContext.request.contextPath}/docTemplate/toClosed?caseId=${caseId}">结案报告表</a>
		<a id="9" href="${pageContext.request.contextPath}/docTemplate/toClosure?caseId=${caseId}">案卷封页</a>
		<a id="10" href="${pageContext.request.contextPath}/docTemplate/toEstimate?caseId=${caseId}">卷烟价值估算申请表</a>
		<a id="11" href="${pageContext.request.contextPath}/docTemplate/toEstimateDetail?caseId=${caseId}">估价明细表</a>
		<a id="12" href="${pageContext.request.contextPath}/docTemplate/toPricing?caseId=${caseId}">涉案烟草专卖品核价表</a>
		<a id="13" href="${pageContext.request.contextPath}/docTemplate/toSeizureList?caseId=${caseId}">没收财物清单</a>
		<a id="32" href="${pageContext.request.contextPath}/docTemplate/toGangYin?caseId=${caseId}">走私烟钢印</a>
		<a id="21" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=1&caseId=${caseId}" class="in">送达  证据处理通知书</a>
		<a id="22" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=2&caseId=${caseId}">送达  鉴定结论告知书</a>
		<a id="23" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=3&caseId=${caseId}">送达   行政处罚事先告知书</a>
		<a id="24" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=4&caseId=${caseId}">送达   行政处罚决定书</a>
	</div>
	
	
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/statics/script/templateEdit.js"></script>
<script type="text/javascript">
	var tagId;
	tagId= $("body").attr("switchId");
	var caseInfoId="${caseId}";
	
	function linkTo(url){
		window.location.href = url + "?caseInfoId="+caseInfoId+"&tagId="+tagId;
	}
	</script>
</html>
