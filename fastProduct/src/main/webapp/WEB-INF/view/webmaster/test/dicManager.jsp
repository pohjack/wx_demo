<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>数据字典管理页面</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/statics/style/datatables.min.css"/>
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js"></script> 
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<style type="text/css">
		</style>
	</head>

	<body> 
	<div>
		<button id="add">新增</button>
	</div>
    <div class="conDiv">
    <table id="tb" class="row-border stripe hover" cellspacing="0" width="100%" style="table-layout: fixed;text-align: center;">    
        <thead>    
            <tr> 
            	<th>序号</th>
                <th>名称</th>    
                <th>代码</th> 
                <th>值</th>  
                <th>分类</th>
                <th>类型</th>
            	<th>描述</th>   
            </tr>    
        </thead>      
        <tbody style="cursor:pointer;">    
        </tbody>  
    </table> 
    </div>
    <script type="text/javascript"> 
        var table ;   
        function  initData(){        	
        	 var d ; // 弹框的全局变量
        	 table = $("#tb").DataTable({
        		 "bStateSave": true,      
                "bProcessing": false, 
                "bServerSide": true,               
                "sAjaxSource": "${pageContext.request.contextPath}/template/getPagingList",
    			"iDisplayLength": 30,
    			"fnServerData": retrieveData,
                 "aoColumns": [  
                               { "mData": 'id', "sWidth": '28px'},                             
                               {"mData": 'name', "sWidth": '80px'},                             
                         	   {"mData": 'code', "sWidth": '100px'},
                         	 	 {"mData": 'value', "sWidth": '150px'},
                         		 {"mData": 'category', "sWidth": '20px'},
                         	  {"mData": 'type', "sWidth": '30px'},
                         	 	{"mData": 'description', "sWidth": '250px'}
                           ], 
               "fnRowCallback": function(nRow, aData, iDisplayIndex) {
                  var secondTDHtml = iDisplayIndex+1;
                  $('td:eq(0)', nRow).html(secondTDHtml);
                  return nRow;
              }
            });  
            
        } 
        $(document).ready(function() { 
        	initData();            
        });   
        
        function retrieveData( sSource,aoData, fnCallback) {
            $.ajax({    
                url : sSource,                              
                data : {"aoData":JSON.stringify(aoData)}, 
                type : 'post',    
                dataType : 'json',    
                async : false,    
                success : function(result) {    
                    fnCallback(result);         
                },    
            });    
        }  
        
        $("#add").click(function(){
        	window.location.href = "${pageContext.request.contextPath}/template/toSaveData";
        });
    </script>    
        
</body> 

</html>