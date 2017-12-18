<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<style type="text/css">
			#result{
				margin-top: 40px;
			    margin-left: 20px;
			    width: 570px;
			    font-size: 16px;
			    border: 2px solid lightblue;
			    border-radius: 10px;
			    height: 1300px;
			    padding: 20px 40px 0px 45px;
			    float: left;
   			 }
   			 #btn{
	   			top: 40px;
			    position: relative;
			    left: 40px;
		    }
		    input[type=button]{
		    	margin: 0 10px 15px 0;
			    height: 25px;
			    background: #9b77a9;
			    border-radius: 10px;
			    color: #e0e0e0;
			    outline: none;
		    }
		    #title{
			    left: -400px;
			    position: relative;
			    top: 15px;
			    font-size: 18px;
		    }
		    #rukou{
			    position: relative;
			    top: 230px;
			    left: 50px;
		    }
		    #rukou a{
		    	color: #fff;
			    background: #7d5959;
			    display: inline-block;
			    height: 30px;
			    width: 160px;
			    line-height: 30px;
			    text-align: center;
			    border-radius: 20px;
			    cursor: pointer;
			    text-decoration: none;
		    }
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed" style="background: #f3eded;">
		<span id="title"></span>
		<div id="result"></div>
		<div id="btn">
			<div style="margin-bottom: 20px;">
			<span>1号为林镇雄，不在现场</span>
			<span style="margin-left: 30px;">2号为林婉华，在现场</span>
			</div>
			<input type="button" id="lianbaogao" modelId="1" caseId="1" value="查看1号立案报告模板">
			<input type="button" id="jianchabiao" modelId="2" caseId="1" value="查看1号检查笔录模板">
			<input type="button" id="jiandingjielun" modelId="3" caseId="1"  value="查看1号鉴定结论模板">
			<input type="button" id="zhongjiebaogao" modelId="4" caseId="1"  value="查看1号终结报告模板">
			<input type="button" id="chulishenpi" modelId="5" caseId="1"  value="查看1号处理审批模板">
			<input type="button" id="chufagaozhi" modelId="6" caseId="1"  value="查看1号处罚告知模板">
			<input type="button" id="chufagaozhi" modelId="7" caseId="1"  value="查看1号处罚决定模板">
			<input type="button" id="chufagaozhi" modelId="8" caseId="1"  value="查看1号结案报告模板">
			<input type="button" id="lianbaogao2" modelId="1" caseId="2"  value="查看2号立案报告模板">
			<input type="button" id="jianchabiao2" modelId="2" caseId="2"  value="查看2号检查笔录模板">
			<input type="button" id="jiandingjielun2" modelId="3" caseId="2"  value="查看2号鉴定结论模板">
			<input type="button" id="zhongjiebaogao2" modelId="4" caseId="2"  value="查看2号终结报告模板">
			<input type="button" id="" modelId="5" caseId="2"  value="查看2号处理审批模板">
			<input type="button" id="" modelId="6" caseId="2"  value="查看2号处罚告知模板">
			<input type="button" id="" modelId="7" caseId="2"  value="查看2号处罚决定模板">
			<input type="button" id="" modelId="8" caseId="2"  value="查看2号结案报告模板">
		</div>
		<div id="rukou">
			<a href="${pageContext.request.contextPath}/template/toDicManager">数据字典管理入口</a>
			<a href="${pageContext.request.contextPath}/template/toModelManager">基础模板管理入口</a>
			<a href="${pageContext.request.contextPath}/template/toModelManager">文书模板管理入口</a>
		</div>
		<script type="text/javascript">
			$("#rukou a").mouseover(function(){
				this.style.background='#eaa9a9'
			})
			$("#rukou a").mouseout(function(){
				this.style.background='#7d5959'
			})
			$("#btn input").click(function(){
				var modelId = $(this).attr("modelId");
				var caseId = $(this).attr("caseId");
				getTemp(modelId,caseId);
			})
			function getTemp(id,caseId){
				$.ajax({
					url : "${pageContext.request.contextPath}/template/autoGenerate",    
					data : {
						"modelNo" : id,
						"caseId" : caseId
					},    
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(result) {
						$("#result").empty();
						$("#title").empty();
						var str;
						switch (id) {
						case "1":
							str = "立案报告模板";
							break;
						case "2":
							str = "检查（勘验）笔录模板";
							break;
						case "3":
							str = "鉴定结论告知书模板";
							break;
						case "4":
							str = "调查终结报告模板";
							break;
						case "5":
							str = "案件处理审批模板";
							break;
						case "6":
							str = "行政处罚事先告知书模板";
							break;
						case "7":
							str = "行政处罚决定书模板";
							break;
						case "8":
							str = "结案报告模板";
							break;
						default:
							break;
						}
						$("#title").html(caseId + "号" + str);
						$("#result").html(result.result);
					},
				})
			}
		</script>
	</body>

</html>