<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
<title>Insert title here</title>
<style type="text/css">
	input[type="text"] {
		width: 15%;
	    height: 15px;
	    padding: 6px 12px;
	    background-color: #fff;
	    border: 1px solid #c2cad8;
	}
	input[type="number"] {
		width: 15%;
	    height: 15px;
	    padding: 6px 12px;
	    background-color: #fff;
	    border: 1px solid #c2cad8;
	}
	input[type="submit"] {
		display: inline-block;
	    margin-bottom: 0;
	    font-weight: 400;
	    text-align: center;
	    vertical-align: middle;
	    touch-action: manipulation;
	    cursor: pointer;
	    border: 1px solid transparent;
	    white-space: nowrap;
	    padding: 6px 12px;
	    font-size: 14px;
	    line-height: 1.42857;
	    user-select: none;
	    color: #333;
	    background-color: #fff;
	    border-color: #ccc;
	}
	label {
		margin-right: 10px;
	}
</style>
</head>
<body>
	<form action="${pageContext.request.contextPath}/template/doSaveData" method="post" style="text-align: center; margin: 100px 0;">
			<input type="hidden" name="id" value="${id }"/>
			<label>名称</label><input type="text" name="name" value="${name }"/>
			<br /><br />
			<label>代码</label><input type="text" name="code" value="${code }"/>
			<br /><br />
			<label>值  &nbsp;&nbsp;</label><input type="text" name="value" value="${value }"/>
			<br /><br />
			<label>类别</label><input type="number" name="category" value="${category }"/>
			<br /><br />
			<label>描述</label><input type="text" name="description" value="${description }"/>
			<br /><br />
			<input type="submit" value="保存">
	</form>
	<script type="text/javascript">
		function formSubmit()
	 	{
	 	 	document.getElementById("myForm").submit()
	  	}
	</script>
</body>
</html>