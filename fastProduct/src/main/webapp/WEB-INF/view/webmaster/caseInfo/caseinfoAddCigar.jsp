<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta charset="UTF-8">
		<title>潮州烟草案件卷宗自动化系统-涉案人员登记</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1" name="viewport" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/font-awesome.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/plugins/datatables.bootstrap.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/components.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/plugins.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/layout.min.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/themes/light2.min.css" rel="stylesheet" type="text/css" id="style_color" />
		<link href="${pageContext.request.contextPath}/statics/style/layout/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/statics/style/bootstrap-fileinput.css" rel="stylesheet" type="text/css" />
		<link href="${pageContext.request.contextPath}/statics/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
			.dataTables_scrollHeadInner {
				width:100% !important;
			}
			.dataTables_scroll {
				margin-bottom: 0;
			}
			.dataTables_scrollHeadInner table {
				width: 100% !important;
			}
			.table-scrollable {
			 	border:0;
			 	overflow-x: hidden;
			}
			div.dataTables_scrollBody table {
				width: 100% !important;
			}
			.center {
				text-align: center;
			}
			#b { 
			    position:relative; 
			}
            #a { 
                position:absolute;
                top:0; 
                right:0; 
                z-index:99;
            }
            .checkBtn {
            	height: 30px;
			    padding: 6px 12px;
			    background-color: #fff; 
			    border: 1px solid #c2cad8;
            }
		</style>
	</head>

	<body class="page-header-fixed page-sidebar-closed-hide-logo page-content-white page-sidebar-fixed"  id="bIframe">
	<div class="form-body" id="addDiv">
		<div class="modal-body"  >
			<div>
				卷烟名称：<input id="name" type="text" class="checkBtn"/> 
				卷烟条形码：<input id="barCode" type="text" class="checkBtn"/> 
				<input type="button" value="检索" onclick="sureBtn()" class="btn green-turquoise sureBtn" style="position:relative; top:-1px; line-height: 1.20;">
			</div>
			<div id="sample_1_wrapper" class="dataTables_wrapper no-footer">
				<div class="table-scrollable">
					<table
						class="table table-striped table-bordered table-hover table-checkable order-column dataTable no-footer"
						id="cigarTable" role="grid" aria-describedby="sample_1_info"
						style="width: 0px;">
						<thead>
							<tr role="row">
								<th class="sorting_disabled" rowspan="1" colspan="1"
									aria-label="" style="width: 0px;">勾选</th>
								<th class="sorting_asc" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1" aria-sort="ascending"
									aria-label="序号: activate to sort column descending"
									style="width: 0px;">序号</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="牌名称号: activate to sort column ascending"
									style="width: 0px;">卷烟名称</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="条包条形码: activate to sort column ascending"
									style="width: 0px;">卷烟条形码</th>
								<th class="sorting" tabindex="0" aria-controls="sample_1"
									rowspan="1" colspan="1"
									aria-label="零售价: activate to sort column ascending"
									style="width: 0px;">卷烟零售价</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div style="text-align:center" id="btnDiv">
		<button type="submit" class="btn green-turquoise" id="saveBtn" target="iframe" > 
			<i class="fa fa-check"></i> 确认
		</button>
		<button type="button" class="btn default" id="quit">关闭</button>
	</div>

	<form id="form" onsubmit="return false;" method="post">
		<input type="hidden" name="caseInfoId" value="${caseInfoId }">
		<div id="formDiv"></div>
	</form>
	<!-- END CONTAINER -->

		<!--[if lt IE 9]>
            <script src="../statics/script/respond.min.js"></script>
            <script src="../statics/script/excanvas.min.js"></script> 
		<![endif]-->
	
		<script src="${pageContext.request.contextPath}/statics/script/jquery.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/bootstrap.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/js.cookie.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-hover-dropdown.min.js"></script>
		<script src="${pageContext.request.contextPath}/statics/script/plugins/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/datatable.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/datatables.bootstrap.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-switch.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/statics/script/plugins/bootstrap-fileinput.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/app.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layer/layer.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/forbitBackSpace.js" type="text/javascript"></script>
		<script>
			App.setAssetsPath("");
			App.setGlobalImgPath("${pageContext.request.contextPath}/statics/images/");
		</script>
        <script src="${pageContext.request.contextPath}/statics/script/table-datatables-managed.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/statics/script/layout.js" type="text/javascript"></script>

		<script type="text/javascript">
		 $(function(){
			 addCigar();
		 });
		var table;
		var caseInfoId = "${caseInfoId}"
		//添加卷烟时 触发datatable查询所有卷烟数据
		function  addCigar(){
			 table = $("#cigarTable").DataTable({
				 	"scrollY": "470px",
	                "scrollCollapse": "true",
	                "paging": "false",
					"bLengthChange": false,   //去掉每页显示多少条数据方法
					"searching":false,
					"bStateSave": true,      //本地缓存
			       "bProcessing": false,                   // 是否显示取数据时的那个等待提示    
			       "bServerSide": true,                    //这个用来指明是通过服务端来取数据 */  
			       "iDisplayLength":25,
			       "sAjaxSource": "${pageContext.request.contextPath}/webmaster/cigarInfo/getPagingList2",      //这个是请求的地址    
			       "pagingType":   "full_numbers",
			       "fnServerData": retrieveData,       // 获取数据的处理函数    
			       "order": [[ 2, "desc" ]],
			       "oLanguage" : { // 汉化
			    	  /*  "sUrl":"${pageContext.request.contextPath}/statics/script/dataTables-lang.json" */
			    			"sProcessing" : "数据正在加载中...",
			    			"sLengthMenu" : "每页显示 _MENU_ 条",
			    			"sZeroRecords" : "没有找到符合条件的数据",
			    			"sInfo" : "当前第 _START_ 至 _END_ 条，共 _TOTAL_ 条",
			    			"sInfoEmpty" : "",
			    			"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
			    			"sInfoPostFix" : "",
			    			"sUrl" : "",
			    			"sEmptyTable" : runTo,
			    			"sLoadingRecords" : "数据正在加载中...",
			    			"sInfoThousands" : ",",
			    			"oPaginate" : {
			    				"sFirst" : "首页",
			    				"sPrevious" : "上页",
			    				"sNext" : "下页",
			    				"sLast" : "末页"
			    			}
			    		
					},
					"bAutoWidth": false,                    //不自动计算列宽度   
			       "aoColumns": [ //这个属性下的设置会应用到所有列，按顺序没有是空      从0开始
			            {"mData" : null, "bSortable" : false}, 
						{"mData" : null, "bSortable" : false}, 
						{"mData" : 'name'}, 
						{"mData" : 'barCode'}, 
						{"mData" : 'retailPrice'}
						],
				   "aoColumnDefs" : [//和aoColums类似，但他可以给指定列附近爱属性
							{
								"bSortable" : true,
								"aTargets" : [0]
							}, //这句话意思是第1,3,6,7,8,9列（从0开始算） 不能排序
								/*   {"bSearchable": false, "aTargets": [1, 2, 3, 4, 5, 6, 7, 8, 9]}, //bSearchable 这个属性表示是否可以全局搜索，其实在服务器端分页中是没用的 */
						],
						"fnRowCallback" : function(nRow, aData, iDisplayIndex) {// 当创建了行，但还未绘制到屏幕上的时候调用，通常用于改变行的class风格 		
							var secondTDHtml = iDisplayIndex + 1;
							var html="<label class='mt-checkbox mt-checkbox-single mt-checkbox-outline'>"
                            +"<input type='checkbox' name='cigarate' value='" +aData.id +"' data-id='"+aData.id+"'  data-name='"+aData.name+"' data-price='"+aData.retailPrice+"' data-barCode='"+aData.barCode+"' class='group-checkable'>"
                            +'<span></span></label>';
							$('td:eq(0)', nRow).html(html);
							$('td:eq(1)', nRow).html("<span>"+secondTDHtml+"<input type='hidden' data-index='"+secondTDHtml+"' value='"+aData.id+"'></span>"); 
							return nRow;
						},
						"fnDrawCallback": function() {
							setCheckBox();
					    },
						initComplete : function() {
						}
					});
		 }
	
		
	
			function runTo() {
				/* layer.msg('抱歉，没有找到符合条件的数据！是否添加？', {
					time : 4000,
					btn : [ '添加', '取消' ],
					yes : function() {
						var name = $("#name").val().trim();
						var barCode = $("#barCode").val().trim();
						mediaLayer2("新增卷烟", [ 0.8, '#393D49' ], 1, false,"${pageContext.request.contextPath}/webmaster/cigarInfo/toAddCigar?name="+name+"&barCode="+barCode, 600, 480, 2);
					},
					btn2 : function() {
						layer.closeAll();
					}
				}); */
				layer.confirm('抱歉，没有找到符合条件的数据！是否新增？', {
					  btn: ['新增','取消'] //按钮
					}, function(){
						var name = $("#name").val().trim();
						var barCode = $("#barCode").val().trim();
						mediaLayer2("新增卷烟", [ 0.8, '#393D49' ], 1, false,"${pageContext.request.contextPath}/webmaster/cigarInfo/toAddCigar?name="+name+"&barCode="+barCode, 340, 330, 2);
					    layer.closeAll();
					}, function(){
					    layer.closeAll();
					});
			}

			function mediaLayer2(title, shade, closeBtn, shadeClose, url,
					width, height, num) {
				top.layer.open({
					type : 2, //2表示弹出层是iframe
					title : title,
					area : [ width + 'px', height + 'px' ],
					maxmin : true, //是否显示最大化最小化按钮
					shade : shade,
					skin : 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
					shift : num,
					closeBtn : closeBtn,
					shadeClose : shadeClose,
					content : [ url ],
				});
			}
			function toAdd() {
				window.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/toAddCigar?caseInfoId="
						+ caseInfoId;

			}

			//3个参数的名字可以随便命名,但必须是3个参数,少一个都不行    
			function retrieveData(sSource, aoData, fnCallback) {
				/* var name = $("#name").val().trim();
				var barCode = $("#barCode").val().trim();
				var ids = '';
				var searchDate = '{name:"' + name + '",barCode:"' + barCode
						+ '",ids:"' + ids + '"}'; */
				var searchDate =  tableSearch();//获取查询条件的json数据，可以传入特定的id数组获得特定查询条件
				searchDate['ids'] = '';
				searchDate = JSON.stringify(searchDate);//json转字符串
				aoData.push({
					"name" : "searchDate",
					"value" : "" + searchDate + ""
				});//添加自己的额外参数		
				$.ajax({
					url : sSource, //这个就是请求地址对应sAjaxSource    
					data : {
						"aoData" : JSON.stringify(aoData)
					}, //这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数 ,分页,排序,查询等的值   
					type : 'post',
					dataType : 'json',
					async : false,
					success : function(result) {
						fnCallback(result); //把返回的数据传给这个方法就可以了,datatable会自动绑定数据的  
						if (result.aaData.length == 0) {
							// layer.msg("未找到卷烟信息,可在卷烟基础数据模块进行添加!");

						}
					},
					error : function(msg) {
					}
				});
			}

			//初始化搜索条件隐藏域
        	sethideSearch();
        	//设置搜索条件隐藏域
        	function sethideSearch() {
        		hideSearch(["name", "barCode"]);
        	}
			function sureBtn() {
				sethideSearch();//重新设置搜索条件隐藏域
				search();
			}
			
			//条件搜索
			function search() {
				table.ajax.reload();
			}
			//行绑定事件 选中或取消					
			$("#cigarTable tbody")
					.on(
							"click",
							"tr",
							function() {
								if (this.cells[0].getElementsByTagName("input")[0].checked == true) {
									this.cells[0].getElementsByTagName("input")[0].checked = false;
								} else {
									this.cells[0].getElementsByTagName("input")[0].checked = true;
								}
							});
			//保存
			$("#saveBtn")
					.click(
							function() {

								if ($("#formDiv").html().length == 0) {
									top.layer.msg("请选择！");
									return;
								}
								$.ajax({
											type : "POST",
											url : "${pageContext.request.contextPath}/webmaster/caseCigar/saveCaseCigar",
											data : $("#form").serialize(),// 你的formid
											dataType : "json",
											success : function(data) {
												if (data.status == "1") {
													var iframe = parent
															.iframe();
													if (caseInfoId == undefined) {
														var caseInfoId = data.caseInfoId;
														iframe.contentWindow
																.setCaseInfoId(data.caseInfoId);
													}
													iframe.contentWindow
															.search2()
													var index = parent.layer
															.getFrameIndex(window.name);
													parent.layer.close(index);
													/* parent.location.href = "${pageContext.request.contextPath}/webmaster/caseInfo/CaseCigar?caseInfoId="
															+ caseInfoId; */
												} else {
													top.layer.msg("保存失败");
												}
											}
										});
							})
			var checkList = new Array();//存放卷烟id集合
			//绑定checkbox事件
			$(document).on("click", "tbody tr", function() {
				var checkbox = $(this).find("td:eq(0)").find("input");
				var flag = checkbox.prop("checked");
				var id = checkbox.attr("data-id");
				if (flag) {
					var isTwoClick = checkList.indexOf(id);//是否已经有卷烟id，如果没有则新增一条
					if (isTwoClick == -1) {
						checkList.push(id);
						setHiddenInput(checkbox);
					}
				} else {
					checkList.remove(id);
					$("#" + id).remove();
				}
			});
			//js数组根据值删除
			Array.prototype.remove = function(val) {
				var index = this.indexOf(val);
				if (index > -1) {
					this.splice(index, 1);
				}
			};
			function setCheckBox() {
				if (checkList != null && checkList.length > 0) {
					for (var i = 0; i < checkList.length; i++) {
						$(
								"input[name='cigarate']:checkbox[value='"
										+ checkList[i] + "']").attr('checked',
								'true');
					}
				}
			}
			var i = 0;
			function setHiddenInput(checkbox) {
				var html = "";
				var id = checkbox.attr("data-id");
				var name = checkbox.attr("data-name");
				var barCode = checkbox.attr("data-barCode");
				var retailPrice = checkbox.attr("data-price");
				html = '<div id="' +id +'" data-index=' +i +'>'
						+ '<input type="hidden" name="caseCigars['+i+'].cigarId" value="'+id+'">'
						+ '<input type="hidden" name="caseCigars['+i+'].name" value="'+name+'">'
						+ '<input type="hidden" name="caseCigars['+i+'].barCode" value="'+barCode+'">'
						+ '<input type="hidden" name="caseCigars['+i+'].number" value="1">'
						+ '<input type="hidden" name="caseCigars['+i+'].unit" value="条">'
						+ '<input type="hidden" name="caseCigars['+i+'].retailPrice" value="' +retailPrice +'">'
						+ '<input type="hidden" name="caseCigars['+i+'].totalValue" value="' +retailPrice +'">'
						+ '<input type="hidden" name="caseCigars['+i+'].preQualitative" value="非">'
						+ '<input type="hidden" name="caseCigars['+i+'].inspectResult" value="非">'
						+ '<input type="hidden" name="caseCigars['+i+'].cigarAttr" value="">'
						+ '<input type="hidden" name="caseCigars['+i+'].inspectNum" value="0">'
						+ '<input type="hidden" name="caseCigars['+i+'].returnNum" value="0">';
				i++;
				$("#formDiv").append(html);
			}
			$("#quit").click(function() {
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			})
		</script>
	</body>

</html>