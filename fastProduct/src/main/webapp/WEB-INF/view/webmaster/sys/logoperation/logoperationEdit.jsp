<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html> 
    <head>  
        <meta charset="utf-8" />  
        <title>潮州烟草案件卷宗自动化系统</title>  
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
				width: 80px;
    			text-align: right;
			}
			.form{
				float: left;
				/* margin-bottom: 10px; */
				width: 90%;
			}
			.col{
				padding-top: 20px;
				padding-left: 25px;
				overflow: auto;
			    table-layout: fixed;
			    word-break: break-all;
			}
		</style>
    </head>  
    <body> 
    <div >
			<div class="form">
 				     <label class="lab">操作类型:</label>
  					    <div class="col">
   					      ${obj.type}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">操作对象:</label>
  					    <div class="col">
   					     ${obj.objOperation}
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">操作路径:</label>
  					    <div class="col">
   					      ${obj.operationUrl} 
     					</div>
   			</div>
   			<div class="form">
 				     <label class="lab">操作表名:</label>
  					    <div class="col" >
   					      ${obj.objName}
     					</div>
   			</div>
   			<c:if test="${not empty obj.objKey }">
   			<div class="form">
 				     <label class="lab">操作数据ID:</label>
  					    <div class="col">
   					      ${obj.objKey}
     					</div>
   			</div>
   			</c:if>
   			<c:if test="${not empty obj.oldValue }">
   			 <div class="form">
 				     <label class="lab">原始值:</label>
  					    <div class="col">
   					      ${obj.oldValue}
     					</div>
   			</div>
   			</c:if>
   			<c:if test="${not empty obj.newValue }">
   			 <div class="form">
 				     <label class="lab">修改值:</label>
  					    <div class="col">
   					      ${obj.newValue}
     					</div>
   			</div>
   			</c:if>
   			</div> 
       	
</body>    
</html> 