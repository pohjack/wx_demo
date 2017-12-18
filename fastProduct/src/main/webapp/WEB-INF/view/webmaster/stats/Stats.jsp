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
<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/script/layer/skin/default/layer.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	.typeahead {
		width: 92% !important;
		overflow: scroll;
	 			max-height: 200px;
	}
	.typeahead li {
		display:block !important;
		margin: 0 !important;
	}
	.form-group ul li {
		margin:0;
		display:block;
		
	}
	.dropdown-toggle {
		background-color: #e1e5ec;
    	border-color: #e1e5ec;
	}
	.portlet-body > div{
		padding: 20px;
		margin-bottom: 30px;
	}
</style>
</head>

<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed">
	<!-- BEGIN HEADER & CONTENT DIVIDER -->
	<div class="clearfix"></div>
	<!-- END HEADER & CONTENT DIVIDER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container">
		<!-- BEGIN CONTENT -->
		<div class="page-content-wrapper">
			<div class="page-content">
				<!-- BEGIN PAGE TITLE-->
				<div class="portlet box grey-cascade">
					<div class="portlet-title">
						<div class="caption">
							<i class="fa fa-filter"></i>条件筛选
						</div>
						<div class="tools">
							<a href="javascript:;" class="expand" data-original-title="" title=""> </a>
						</div>
					</div>
					<div class="portlet-body form" style="display: none;">
						<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal" onsubmit="return false">
							<div class="form-body">
								<!-- <div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">快捷查询</label>
											<div class="col-md-8">
												<button class="btn default dateBtn" data-value="month" style="font-size: 12px;">最近一月</button>
												<button class="btn default dateBtn" data-value="season" style="font-size: 12px;">最近一季</button>
												<button class="btn default dateBtn" data-value="year" style="font-size: 12px;">最近一年</button>
												<div class="btn-group" style="color: #666;background-color: #c2cad8;border-color: #bcc5d4; font-size:12px">
													<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="font-size:12px">
														更多<span class="caret"></span>
													</button>
													<ul class="dropdown-menu" role="menu">
														<li><a href="javascript:void(0);">当前月</a></li>
														<li><a href="javascript:void(0);">上一月</a></li>
														<li><a href="javascript:void(0);">当前季度</a></li>
														<li><a href="javascript:void(0);">上一季度</a></li>
														<li><a href="javascript:void(0);">当前年</a></li>
														<li><a href="javascript:void(0);">上一年</a></li>
													</ul>
												</div>
											</div>
										</div>
									</div>
								</div> -->
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">日期范围</label>
											<div class="col-md-8">
												<div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd" id="datetimepicker1">
                                                    <input type="text" id="startDate" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'endDate\')||\'new Date()\'}',skin:'twoer'})"  class="form-control toDate" name="from">
	                                                <span class="input-group-addon"> 至  </span>
	                                                <input type="text" id="endDate" onfocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')||\'new Date()\'}',skin:'twoer'})" class="form-control toDate" name="to">
                                                 </div>
											</div>
										</div>
									</div>
									<!--/span-->
									<div class="col-md-4">
                                        <div class="form-group">
                                            <label class="control-label col-md-4">名称</label>
                                            <div class="col-md-8">
                                            	<input type="text" id="cigarName" maxlength="32" class="form-control" autocomplete="off">
                                            </div>
                                        </div>
                                    </div>
								</div>
							</div>
							<div class="form-actions" style="padding: 10px;">
								<div class="btn-set pull-right">
									<button type="button" id="reset" class="btn default reset">重置</button>
									<button type="button" id="sure" class="btn green-turquoise" onclick="getStats()">确定</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet light bordered">
							<div class="portlet-title">
								<div class="caption font-dark" style="padding:0">
									<button class="btn default dateBtn" data-value="month" style="font-size: 12px;">最近一月</button>
									<button class="btn default dateBtn" data-value="season" style="font-size: 12px;">最近一季</button>
									<button class="btn default dateBtn" data-value="year" style="font-size: 12px;">最近一年</button>
									<div class="btn-group" style="color: #666;background-color: #c2cad8;border-color: #bcc5d4; font-size:12px">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="font-size:12px">
											更多<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><a href="javascript:void(0);">当前月</a></li>
											<li><a href="javascript:void(0);">上一月</a></li>
											<li><a href="javascript:void(0);">当前季度</a></li>
											<li><a href="javascript:void(0);">上一季度</a></li>
											<li><a href="javascript:void(0);">当前年</a></li>
											<li><a href="javascript:void(0);">上一年</a></li>
										</ul>
									</div>
								</div>
								<div class="actions">
									<span style="margin:0 5px">立案数量：</span>
									<span style="margin:0 5px">结案数量：</span>
								</div>
							</div>
							<div class="portlet-body">
								<div style="float:left">
									<div id='canvasDiv1' style="float:left"></div>
									<div id='canvasDiv11'></div>
								</div>
								<div  style="float:left">
									<div id='canvasDiv2'  style="float:left"></div>
									<div id='canvasDiv22'></div>
								</div>
								<div  style="float:left">
									<div id='canvasDiv3'  style="float:left"></div>
									<div id='canvasDiv33'></div>
								</div>
								<div>
									<div id='canvasDiv4'  style="float:left"></div>
									<div id='canvasDiv44'></div>
								</div>
								<div id='warning' style="text-align:center"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
	
    <script src="${pageContext.request.contextPath}/statics/script/plugins/ichart/ichart.1.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap3-typeahead.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>
	
	<script type="text/javascript">
		init();
		function init() {
			getStats();
			typeahead();
		}
		
		function getStats() {
			var startDate = $("#startDate").val();
			var endDate = $("#endDate").val();
			if(endDate != '') {
				var end = new Date(endDate);
				end.setDate(end.getDate()+1);
				var endDate = new Date(end).Format("yyyy-MM-dd");
			}
			var cigarName = $("#cigarName").val().trim();
			$.ajax({
				url: "${pageContext.request.contextPath}/webmaster/caseCigar/searchStats",
				data: {
					"startDate": startDate,
					"endDate": endDate,
					"cigarName": cigarName
				},
				type: "post",
				dataType: "json",
				success: function(data){
					if(data.data != undefined) {
						setData(data.data);
					} else {
						nullData();
					}
				}
			})
		}
		
		function setData(data) {
			var nonValue = 0;
			var fakeValue = 0;
			var privValue = 0;
			var nonCount = 0;
			var fakeCount = 0;
			var privCount = 0;
			if(!$.isEmptyObject(data)) {
				if(!$.isEmptyObject(data.非)) {
					nonValue = data.非.totalValue;
					nonCount = data.非.number;
				}
				if(!$.isEmptyObject(data.假)) {
					fakeValue = data.假.totalValue;
					fakeCount = data.假.number;
				}if(!$.isEmptyObject(data.私)) {
					privValue = data.私.totalValue;
					privCount = data.私.number;
				}
			}
			var sumValue = (Number(nonValue) +Number(fakeValue) +Number(privValue)).toFixed(2);
			nonValue = setJson("非烟价值", nonValue, "color:'#9d4a4a'");
			fakeValue = setJson("假烟价值", fakeValue, "color:'#5d7f97'");
			privValue = setJson("私烟价值", privValue, "color:'#97b3bc'");
			var data1 = [strFormatJson(nonValue), strFormatJson(fakeValue), strFormatJson(privValue)];
			setChart("canvasDiv1", data1, "总价值：" +sumValue, "卷烟价值统计");
			setColumn("canvasDiv11", data1, "总价值：" +sumValue);
			
			var sumNumber = Number(nonCount) +Number(fakeCount) +Number(privCount);
			var inventory = Number(nonCount) +Number(fakeCount); //库存数量
			nonCount = setJson("非烟数量", nonCount, "color:'#9d4a4a'");
			fakeCount = setJson("假烟数量", fakeCount, "color:'#5d7f97'");
			privCount = setJson("私烟数量", privCount, "color:'#97b3bc'");
			var data2 = [strFormatJson(nonCount), strFormatJson(fakeCount), strFormatJson(privCount)];
			setChart("canvasDiv2", data2, "总数量：" +sumNumber, "卷烟数量统计");
			setColumn("canvasDiv22", data2, "总数量：" +sumNumber);
			
			var sumRange = 0;
			var sumNonRange = 0;
			var sumFakeRange = 0;
			var startCase = 0;
			var endCase = 0;
			if(!$.isEmptyObject(data)) {
				sumRange = isNull(data.sumValue, 0);
				sumNonRange = isNull(data.sumNonValue, 0);
				sumFakeRange = isNull(data.sumFakeValue, 0);
				startCase = data.startCase;
				endCase = data.endCase;
			}
			var fakeRange = setJson("假烟罚款总值", sumFakeRange.toFixed(2), "color:'#5d7f97'");
			var nonRange = setJson("非烟罚款总值", sumNonRange.toFixed(2), "color:'#9d4a4a'");
			var data3 = [strFormatJson(nonRange), strFormatJson(fakeRange)];
			setChart("canvasDiv3", data3, "罚款总值：" +sumRange.toFixed(2), "卷烟罚款统计");
			setColumn("canvasDiv33", data3, "罚款总值：" +sumRange.toFixed(2));
			
			$(".actions").find("span:eq(0)").text("立案数量：" +startCase); 
			$(".actions").find("span:eq(1)").text("结案数量：" +endCase); 
			
			var data4 = [strFormatJson(fakeCount), strFormatJson(privCount)];
			setChart("canvasDiv4", data4, "", "库存统计");
			setColumn("canvasDiv44", data4, "总库存：" +inventory);
		}
		function nullData() {
			$("#canvasDiv1").html("");
			$("#canvasDiv2").html("");
			$("#canvasDiv3").html("");
			$("#warning").text("暂无记录");
			$(".actions").find("span:eq(0)").text("立案数量：0"); 
			$(".actions").find("span:eq(1)").text("结案数量：0"); 
			
		}
		//圆形报表初始化
		function setChart(id, json, text, title) {
			var chart = new iChart.Pie2D({
				render : id,
				data: json,
				animation:true,
				showpercent:true,
				width : 600,
				height : 247,
				radius:140,
				title : title,
				border:{enable: false},
				tip:{
					enable:true,
					listeners:{
						parseText:function(tip,name,value,text){
							return name+":" +value;
						}
					}
				},
			});
			chart.draw();
		}
		//柱状图初始化
		function setColumn(id, json, title) {
			new iChart.Column2D({
				render : id,
				data: json,
				title : title,
				showpercent:false,
				border:{enable: false},
				decimalsnum:2,
				width : 600,
				height : 247,
				sub_option:{
					listeners:{
						click:function(r,e,m){
							var cigarType = r.options.name;
							cigarType = cigarType.substr(0, 1);
							var startDate = $("#startDate").val();
							var endDate = $("#endDate").val();
							if(endDate != '') {
								var end = new Date(endDate);
								end.setDate(end.getDate()+1);
								var endDate = new Date(end).Format("yyyy-MM-dd");
							}
							var name = $("#cigarName").val();
							var data = "cigarType=" +cigarType +"&startDate=" +startDate +"&endDate=" +endDate +"&name=" +name;
							var url = "${pageContext.request.contextPath}/webmaster/caseCigar/toCigarStatsList?" +data;
							mediaLayer2("涉案卷烟", [ 0.8, '#393D49' ], 1, true,url, 960, 605, 2);
						}
					}
				}
			}).draw();
		}
		//重置按钮
		$(".reset").click(function() {
    		$("#cigarName").val("");
    		$("#startDate").val("");
    		$("#endDate").val("");
    	});
		//快捷时间按钮
		$(".dateBtn").click(function() {
			var startDate = new Date();
			var endDate = new Date();
			var btnValue = $(this).attr("data-value");
			if(btnValue == "month") {
				startDate.setMonth(startDate.getMonth()-1);
			} else if(btnValue == "season") {
				startDate.setMonth(startDate.getMonth()-3);
			} else if(btnValue == "year") {
				startDate.setFullYear(startDate.getFullYear()-1);
			}
    		$("#startDate").val(startDate.Format("yyyy-MM-dd"));
    		$("#endDate").val(endDate.Format("yyyy-MM-dd"));
    		getStats();
    	});
		$(".dropdown-menu li").click(function(){
			var value = $(this).text();
			var date = new Date();
			switch(value) {
				case "当前月":
					var startDate = new Date();
					startDate.setDate(1);
					setSearchDate(startDate, date);
					break;
				case "上一月":
					var endDate = date.setDate(0);
					var startDate = date.setDate(1);
					setSearchDate(startDate, endDate);
					break;
				case "当前季度":
					var startDate = checkSeason(new Date());
					setSearchDate(startDate, date);
					break;
				case "上一季度":
					var startDate = new Date();
					startDate.setMonth(startDate.getMonth()-3);
					startDate = checkSeason(startDate);
					var endDate = checkSeason(new Date());
					setSearchDate(startDate, endDate);
					break;
				case "当前年":
					var startDate = new Date();
					startDate.setDate(1);
					startDate.setMonth(0);
					setSearchDate(startDate, date);
					break;
				case "上一年":
					date.setMonth(0);
					var endDate = date.setDate(0);
					date.setMonth(0);
					var startDate = date.setDate(1);
					setSearchDate(startDate, endDate);
					break;
			}
    		getStats();
		})
		function checkSeason(seasonStartDate) {
			seasonStartDate.setDate(1);
			var month = seasonStartDate.getMonth()+1;
			if(1<=month && month<=3) {
				seasonStartDate.setMonth(0);
			} else if(4<=month && month<=6) {
				seasonStartDate.setMonth(3);
			} else if(7<=month && month<=9) {
				seasonStartDate.setMonth(6);
			} else if(10<=month && month<=12) {
				seasonStartDate.setMonth(9);
			}
			return seasonStartDate;
		}
		function setSearchDate(startDate, endDate) {
			$("#startDate").val(new Date(startDate).Format("yyyy-MM-dd"));
			$("#endDate").val(new Date(endDate).Format("yyyy-MM-dd"));
		}
		//下拉搜索插件
		function typeahead() {
			var cigars = "${cigars}";
			cigars = cigars.substr(1, cigars.length-2);
			var list = cigars.split(",");
			console.log(list.length);
			$('#cigarName').typeahead({
				source: list,
				items: 999,
				updater: function (item) {
					return item;
				}
			})
		}
		//为null判断
    	function isNull(value, returnType) {
    		if(value=="" || value==undefined || isNaN(value)) {
    			return returnType;
    		}
    		return value;
    	}
    	//拼接json格式
		function setJson(name, value, extra) {
			var json = "{";
			if(name != "") {
				json += "name:'" +name +"'";
				json += ",value:" +value;
				json += "," +extra;
			}
			json += "}";
			return json;
		}
		//字符串转json
		function strFormatJson(str) {
			if(str != "") {
				str = eval('(' + str + ')');
			};
			return str;
		}
    	//格式化时间
    	Date.prototype.Format = function (fmt) {
    		var o = {
    			"M+": this.getMonth() + 1, //月份 
    			"d+": this.getDate(), //日 
    			"h+": this.getHours(), //小时 
    			"m+": this.getMinutes(), //分 
    			"s+": this.getSeconds(), //秒 
    			"q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    			"S": this.getMilliseconds() //毫秒 
    		};
    		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    		for (var k in o)
    		if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    		return fmt;
    	}
    	//弹出框
    	function mediaLayer2(title, shade, closeBtn, shadeClose, url, width, height, num){
	 		  top.layer.open({
	 		  type: 2, //2表示弹出层是iframe
	 		  title: title,
	 		  area: [width + 'px', height + 'px'],
	 		  maxmin: true, //是否显示最大化最小化按钮
	 		  shade: shade,
	 		  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
	 		  shift: num,
	 		  closeBtn: closeBtn,
	 		  shadeClose: shadeClose,
	 		  content: [url], 
	 		});
	 	}
	</script>
</body>

</html>