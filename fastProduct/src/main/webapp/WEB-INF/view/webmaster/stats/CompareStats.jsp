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
<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/statics/style/plugins/time/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
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
							<a href="javascript:;" class="collapse" data-original-title="" title=""> </a>
						</div>
					</div>
					<div class="portlet-body form" style="display: block;">
						<!-- BEGIN FORM-->
						<form action="#" class="form-horizontal">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group">
											<label class="control-label col-md-4">日期范围</label>
											<div class="col-md-8">
												<div class="input-group date-picker input-daterange" data-date-format="yyyy-mm-dd" id="datetimepicker1">
                                                    <input type="text" id="startDate" class="form-control toDate" name="from">
                                                    <span class="input-group-addon"> 至  </span>
                                                    <input type="text" id="endDate" class="form-control toDate" name="to">
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
								<div class="caption font-dark">
									<i class="icon-notebook font-dark"></i> <span class="caption-subject bold uppercase"> 数据统计</span>
								</div>
								<div class="actions">
									<span style="margin:0 5px">立案数量：</span>
									<span style="margin:0 5px">结案数量：</span>
								</div>
							</div>
							<div class="portlet-body">
								<div class="portlet box blue">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-gift"></i>Scrollable Content 
	                                    </div>
	                                    <div class="tools"></div>
	                                </div>
	                                <div class="portlet-body portlet-empty"> 
	                                	<div>
	                                		<button id="month" class="form-control" style="width:9%; float:left">最近一月</button>
	                                		<button id="year" class="form-control" style="width:9%">最近一年</button>
	                                	</div>
	                                	<div id="canvasDiv1"></div>
	                                </div>
	                            </div>
								<div class="portlet box blue">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-gift"></i>Scrollable Content 
	                                    </div>
	                                    <div class="tools"></div>
	                                </div>
	                                <div class="portlet-body portlet-empty"> 
	                                	<div id="canvasDiv2"></div>
	                                </div>
	                            </div>
	                            <div class="portlet box blue">
	                                <div class="portlet-title">
	                                    <div class="caption">
	                                        <i class="fa fa-gift"></i>Scrollable Content 
	                                    </div>
	                                    <div class="tools"></div>
	                                </div>
	                                <div class="portlet-body portlet-empty">
	                                	<div id="canvasDiv3"></div>
	                                </div>
	                            </div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<button onclick="ccc()">测试</button>
	<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
	
	        
    <script src="${pageContext.request.contextPath}/statics/script/plugins/time/moment.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/script/plugins/time/bootstrap-datepicker.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/script/plugins/time/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/script/plugins/time/components-date-time-pickers.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/statics/script/plugins/ichart/ichart.1.2.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap3-typeahead.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
	<script> App.setAssetsPath(""); App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/"); </script>
	<script src="${pageContext.request.contextPath}/statics/script/InitializeLayer.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$("#year").click(function(){
			var labels = new Array();
			var myDate = new Date();
			var d = myDate.setMonth(myDate.getMonth() -4);
			for(var i=0; i<12; i++) {
				var dd = myDate.setMonth(myDate.getMonth() +1);
				labels.push(new Date(dd).Format("yyyy/MM"));
			}
			alert(labels);
			ccc(new Date().Format("yyyy-MM-dd"), '', labels);
		});
		function ccc(date, cigarName, labels) {
			$.ajax({
				url: "${pageContext.request.contextPath}/webmaster/caseCigar/searchCampareStats",
				data: {
					"date": date,
					"cigarName": cigarName,
				},
				type: "post",
				dataType: "json",
				success: function(data) {
					var value = "[" +data.nonList +"]";
					var json1 = setJson("非烟", value, "color:'#9d4a4a'");
					value = "[" +data.fakeList +"]";
					var json2 = setJson("假烟", value, "color:'#1f7e92'");
					value = "[" +data.privList +"]";
					var json3 = setJson("私烟", value, "color:'#76a871'");
					var jsonDate = [strFormatJson(json1), strFormatJson(json2), strFormatJson(json3)];
					//var labels = ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"];
					initIcharts(jsonDate, labels, "", "canvasDiv1")

					value = "[" +data.sumList +"]";
					var json4 = setJson("卷烟总数", value, "color:'#76a871'");
					jsonDate = [strFormatJson(json4)];
					initIcharts(jsonDate, labels, "", "canvasDiv2");
					

					value = "[" +data.caseList +"]";
					var json5 = setJson("案件数量", value, "color:'#76a871'");
					jsonDate = [strFormatJson(json5)];
					initIcharts(jsonDate, labels, "", "canvasDiv3");
					
				}
			})
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
		//初始化图表
		function initIcharts(data, labels, title, divId) {
			var scaleSpace = (parseInt(getMaxValue(data)/10)+1)*2;
			var chart = new iChart.Area2D({
				render : divId,
				data: data,
				title : title,
				width : 800,
				height : 494,
				area_opacity:0.15,
				legend : {
					enable : true
				},
				sub_option:{
					label:false
				},
				crosshair:{
					enable:true,
					line_color:'#62bce9'
				},
				tip:{
					enable : true,
					listeners:{
						 //tip:提示框对象、name:数据名称、value:数据值、text:当前文本、i:数据点的索引
						parseText:function(tip,name,value,text,i){
							return "<span style='color:#005268;font-size:11px;font-weight:600; '>"+name+
									"</span> <span style='color:#005268;font-size:20px;font-weight:600;'>"+value+"</span>";
						}
					}
				},
				coordinate:{
					background_color:'#ffffff',
					height:'90%',
					valid_width:'94%',
					height : 260,
					scale2grid: true,
					scale:[{
						 position:'left',	
						 start_scale:0,
						 scale_space: scaleSpace,
					},{
						 position:'bottom',	
						 start_scale:1,
						 end_scale:12,
						 parseText:function(t,x,y){
							return {textY:y+10}
						 },
						 labels:labels
					}]
				},
				sub_option:{
					listeners:{
						click:function(r,e,m){
							console.log(1);
							console.log(1);
							toAdd();
						}
					}
				}
			});
			chart.draw();
		}
		//添加
		var flag = true;
		function toAdd() {
			if(!flag) return;
			var url = "${pageContext.request.contextPath}/webmaster/caseCigar/stats2";
			mediaLayer2("添加涉案卷烟", [ 0.8, '#393D49' ], 1, true,url, 960, 705, 2);
			flag = false;
		}
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
	 		  end: function(index, layero) {
	 			 flag = true;
	 		  }
	 		});
	 	}
		
		//计算数组最大值
		function getMaxValue(data) {
			var max = 0;
			if(data!=null && data.length>0) {
				for(var i=0; i<data.length; i++) {
					var value = Math.max.apply(null, data[i].value);
					if(value > max) {
						max = value;
					}
				}
			}
			return max;
		}
		
		Date.prototype.Format = function (fmt) { //author: meizz 
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
	</script>
</body>

</html>