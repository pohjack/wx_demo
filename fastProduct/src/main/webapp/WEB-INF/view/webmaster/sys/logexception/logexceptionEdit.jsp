<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html> 
    <head>  
        <meta charset="utf-8" />  
        <title>潮州烟草案件卷宗自动化系统-日志异常编辑</title>  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/style/jqDataTables/jquery.dataTables.min.css"/>  
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css"/>  
        <%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/style/jqDataTables/dataTables.bootstrap.min.css"/> --%> 
        <!-- 弹框引用JS CSS -->		
		<link rel="stylesheet" href="${pageContext.request.contextPath}/statics/style/ui-dialog.css"/>
		<script src="${pageContext.request.contextPath}/statics/My97DatePicker/WdatePicker.js"></script>
		
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<style type="text/css">
			.toLong{
				text-overflow: ellipsis;
				 white-space: nowrap; 
				 overflow: hidden;
				 text-align: center;
			}
			th{
				text-align: center;
			}
			.lab{
				margin: 20px 0px 5px 20px;
				float: left;
				width: 135px;
    			text-align: right;
			}
			.form{
				float: left;
				margin-bottom: 10px;
				width: 90%;
			}
			.col{
				margin-top: 20px;
				margin-left: 180px;
			}
		</style>
    </head>  
    <body> 
    <div >
			<div class="form">
 				     <label class="lab">出异常类名:</label>
  					    <div class="col">
   					      ${obj.nameClass}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">方法名称:</label>
  					    <div class="col">
   					     ${obj.nameFunction}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">异常名称:</label>
  					    <div class="col" >
   					      ${obj.nameException}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">模块名称:</label>
  					    <div class="col">
   					      ${obj.nameMethod}
     					</div>
   			</div>
   			 <div class="form">
 				     <label class="lab">异常所在行:</label>
  					    <div class="col">
   					      ${obj.rumException}
     					</div>
   			</div>
   			 <div class="form">
 				     <label class="lab">包名:</label>
  					    <div class="col">
   					      ${obj.namePackage}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">异常信息:</label>
  					    <div class="col">
   					      ${obj.exceptionMesg} 
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">异常代码:</label>
  					    <div class="col">
  					    ${obj.exceptionCode}	     
     					</div>
   			</div>
   			</div> 
       	
</body>    
</html> 