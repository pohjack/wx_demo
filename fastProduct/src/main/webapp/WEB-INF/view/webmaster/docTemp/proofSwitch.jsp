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
<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
<style type="text/css">
	td p{
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
			onclick="javascript:window.location.href='${pageContext.request.contextPath}/webmaster/caseInfo/toEditCase?caseInfoId=${caseId}'"><i class="fa fa-edit"></i>编辑案件
		</button>
		<button id="hisCase" class="btnEdit btn red-mint"  style="display:none;"
			onclick="javascript:window.location.href='${pageContext.request.contextPath}/webmaster/caseInfo/toHisView?caseInfoId=${caseId}'"><i class="fa fa-search"></i>登记查看
		</button>
		<a id="21" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=1&caseId=${caseId}" class="in">先行登记保存证据处理通知书</a>
		<a id="22" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=2&caseId=${caseId}">涉案烟草专卖品鉴定结论告知书</a>
		<a id="23" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=3&caseId=${caseId}">行政处罚事先告知书</a>
		<a id="24" href="${pageContext.request.contextPath}/docTemplate/toProof?flag=4&caseId=${caseId}">行政处罚决定书</a>
	</div>
	
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/templateEdit.js"></script>
</html>
