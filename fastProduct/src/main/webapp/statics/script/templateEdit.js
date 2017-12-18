var id;
if(caseId == '' || caseId == 'undefined'){
	caseId = "1";
}



function getInfo(){
	id = $("body").attr("switchId");
	$(".switch a").removeClass("in");
	$("#"+id).addClass("in");
	var tags = "";
	if("1" == id){
		tags = '1,2';
	}else if("4" == id || "5" == id ){
		tags = '1,2,3';
	}else if("8"==id){
		tags='1,2,3,4';
	}
	
	$.ajax({
		type : "POST",
		url : "../docTemplate/getInfo",
		dataType : "json",
		data : {
			"caseId" : caseId,
			"modelNo" : id,
			"tags" : tags
		},
		success : function(data) {
			
			if(data.caseInfo.status==0){
				$("#contEdit").hide();
				$("#rebuild").hide();
				
			}
			
			$("#content").empty();
			if(data != null && data != ""){
				var tag2 = data.map.tag2==null?"":data.map.tag2;
				var tag3 = data.map.tag3==null?"":data.map.tag3;
				var tag4 = data.map.tag4==null?"":data.map.tag4;
				if("6" == id || "7" == id){
					var regin = data.caseInfo.regiNo;
					postAgency = postAgency + regin.substring(regin.indexOf("["));
					$("#postAgency").html(postAgency);
				}else if("1" == id){
					$("#opinion").html(tag2);
				}else if("4" == id){
					$("#punishBasic").html(tag2);
					$("#handling").html(tag3);
				}else if("5" == id){
					$("#punishBase").html(tag2);
					$("#dealing").html(tag3);
				}else if("8" == id ){
					$("#decision").html(tag2);
					$("#exeCondition").html("　　"+tag3);
					$("#closeReason").html(tag4);
				}
				if(data.caseInfo != null && data.caseInfo != "" ){
					var barTab=$('#barTab', window.parent.document).find("span").html();
					var ccode = data.caseInfo.caseCauseCode == null?"":data.caseInfo.caseCauseCode;
					var csource = data.caseInfo.caseSource == null?"":data.caseInfo.caseSource;
					if(barTab=="历史案件"){
						$('#editCase').hide();
						$('#hisCase').show();
					}
					if(ccode != null && ccode != '' && "1" == id){
						var code = ccode.split(",");
						for(var i = 0; i < code.length; i++){
							$("#caseCau input:checkbox[value='"+code[i]+"']").attr("checked","true");
							
						}
						if(barTab=="历史案件"){
							$("#caseCau input").each(function(index){
								 $(this).attr("disabled","disabled");
								 
							});
							
						}
					}
					if(csource != null && csource != '' && "1" == id){
						if("市场调查举报上级移交".indexOf(csource) < 0){
							$("#writesouce").val(csource);
							$("#writesouce").show();
							csource = "其它";
						}
						$("#source input:radio[value='"+csource+"']").attr("checked","true");
						if(barTab=="历史案件"){
							$("#source input").each(function(index){
								 $(this).attr("disabled","disabled");
							});
							
						}
					}
				/*
						$("#source input:radio[value='"+csource+"']").attr("readonly","readonly");
						$("#caseCau").attr("id","case");
					    $("#source").attr("id","sor");*/
					
					var startDate = isNullDate(data.caseInfo.inquStartDate, "yyyy年MM月dd日");
					var endDate = isNullDate(data.caseInfo.inquEndDate, "yyyy年MM月dd日");
					var startTime = isNullDate(data.caseInfo.inquStartTime, "hh时mm分");
					var endTime = isNullDate(data.caseInfo.inquEndTime, "hh时mm分");
					var crimeDate = isNullDate(data.caseInfo.crimeDate, "yyyy年MM月dd日");
	
					$("#insTime").html(startDate + startTime + "至" + endDate + endTime);
					$("#addr").html(data.caseInfo.caseAddr==null?"":data.caseInfo.caseAddr);
					$("#cause").html(data.caseInfo.caseCause==null?"":data.caseInfo.caseCause);
					$("#regiNo").html(data.caseInfo.regiNo==null?"":data.caseInfo.regiNo);
					var regiNo=data.caseInfo.regiNo;
					var yearDu=regiNo.substr(4,4);
					var acct=regiNo.substr(0,2);
					var numb=regiNo.substr(regiNo.indexOf("]")+1);
					var fengNo= yearDu+"&nbsp;年度&nbsp;"+acct+"&nbsp;"+numb;
					$("#fengYeNo").html(fengNo);
					$("#officer").html(data.caseInfo.catchpoleName==null?"":data.caseInfo.catchpoleName);
					$("#caseAttr").html(data.caseInfo.caseCause==null?"":data.caseInfo.caseCause.substr(2));
					$("#cause").html(data.caseInfo.caseCause==null?"":data.caseInfo.caseCause);
					$("#applyDate").html(data.caseInfo.caseCause==null?"":data.caseInfo.caseCause);
					var time = isNullDate(data.caseInfo.crimeDate, "yyyy年MM月dd日");
					var crimeStartTime = isNullDate(data.caseInfo.crimeStartTime, "hh时mm分");
					$("#regTime").html(time);
					$("#regTime2").html(time +crimeStartTime);
				}
				$("#content").html(data.map.tag1==null?"":data.map.tag1);
				if(data.result != null && data.result != "" ){
					$("#corpName").html(data.result.corp_name==null?"":data.result.corp_name);
					$("#age").html(data.result.age);
					//$("#regDate").html(new Date(parseInt(data.caseInfo.crimeDate==null?"":data.caseInfo.crimeDate.time)).Format("yyyy年MM月dd日"));
					$("#regDate").html(crimeDate);
					$("#sex").html(data.result.sex==0?"女":"男");
					$("#party").html(data.result.per_respon==null?"":data.result.per_respon);
					$("#partyName").html("当事人：" + (data.result.per_respon==null?"":data.result.per_respon));
					$("#nation").html(data.result.nation==null?"":data.result.nation);
					$("#card").html("身份证号码：" + (data.result.id_card==null?"":data.result.id_card));
					$("#Idcard").html("身份证号码" + (data.result.id_card==null?"":data.result.id_card));
					$("#cardAddr").html(data.result.id_card_addr==null?"":data.result.id_card_addr);
					$("#phone").html(data.result.phone==null?"":data.result.phone);
					var birth = getBirthdayFromIdCard(data.result.id_card);
					$("#birth").html(birth);
				}
				if(data.caseSlave != null && data.caseSlave != "" ){
					$("#contactMan").html(data.caseSlave.userName);
					var in_inform_date = isNullDate(data.caseSlave.inInformDate, "yyyy年MM月dd日");
					$("#in_inform_date").html(in_inform_date);
					var evalDate = isNullDate(data.caseSlave.evalDate, "yyyy年MM月dd日");
					var penaltyDate = isNullDate(data.caseSlave.penaltyDate, "yyyy年MM月dd日");
					$("#penaltyDate").html(penaltyDate);
					$("#chengPers").html(data.caseSlave.userName==null?"":data.caseSlave.userName);
					$("#applyDate").html(evalDate);
					var endDate=isNullDate(data.caseSlave.endCase, "yyyy年MM月dd日");
					$("#endCase").html(endDate);
					var archiveDate=isNullDate(data.caseSlave.archiveDate, "yyyy年MM月dd日");
					$("#archiveDate").html(archiveDate);
				}
			}
		}
	})
}
		getInfo();
		/**
		 * 内容编辑按钮点击事件
		 * 
		 */
		$("#contEdit").click(function(){
			top.layer.open({
				  type: 2, //2表示弹出层是iframe
				  id:"caseTempEdit",
				  title: "案件内容编辑",
				  area: ['90%', '70%'],
				  maxmin: true, //是否显示最大化最小化按钮
				  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
				  closeBtn: 1,
				  shadeClose: true,
				  content: ["../template/toCaseDocsEdit?caseId="+caseId + "&modelNo="+ id,'no'], //no表示不显示滚动条
				  btn:['保存', '关闭'],
				  yes: function(index, layero){
						var cont = $(layero).find("iframe")[0].contentWindow.getContent();
						/*var tempName = $(layero).find("iframe").contents().find("#tempName").val();
						updateBaseTemp(tid,cont,tempName,modelNo);*/
						saveCaseTemp(caseId,id,cont);
						top.layer.close(index);
				  }
				});
		})


		/**
		 * 重新生成按钮点击事件
		 */
		$("#rebuild").click(function(){
			//getInfo();
			rebuildSysTemp();
		})

		/**
		 * 导出Excel
		 */
		$("#export").click(function(){
			var tags = "";
			if("1" == id){
				tags = '1,2';
			}else if("4" == id || "5" == id || "8" == id){
				tags = '1,2,3';
			}
			window.location.href="../template/exportExcel?caseId="+caseId+"&modelNo="+id+"&tags="+tags;
		})
		
		/**
		 * 模块编辑按钮点击事件
		 */
		$("#modelEdit").click(function(){
			/* top.layer.open({
				  type: 2, //2表示弹出层是iframe
				  id:"baseTempEdit",
				  title: "模块编辑",
				  area: ['90%', '88%'],
				  maxmin: true, //是否显示最大化最小化按钮
				  skin: 'layui-layer-molv', //'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
				  closeBtn: 1,
				  shadeClose: true,
				  content: ["${pageContext.request.contextPath}/template/toTempAdd?tid="+tid + "&modelNo="+ modelNo,'no'], //no表示不显示滚动条
				  btn:['确认', '取消'],
				  yes: function(index, layero){
						var cont = $(layero).find("iframe")[0].contentWindow.getContent();
						var tempName = $(layero).find("iframe").contents().find("#tempName").val();
						updateBaseTemp(tid,cont,tempName,modelNo);
						top.layer.close(index);
					   	
				  },
				  end:function(){
					  if(parent.$("#status").val() != ""){
						  rollBack(tid);
					  }
				  }
				});*/
		})


		function saveCaseTemp(caseId,id,cont){
			$.ajax({
				type : "POST",
				url : "../template/saveCaseTemp",
				dataType : "json",
				data : {
					"caseId" : caseId,
					"docsId" : id,
					"content" : cont
				},
				success : function(data) {
					var result = '保存失败，请稍后再试！';
					if(data.res){
						result= '保存成功！';
					}
					layer.open({
	        			  icon:1,
	        			  content: result 
	        			});
					getInfo();
				}
			})
	
		};
		
		/**
		 * 按照系统模板重新生成
		 */
		function rebuildSysTemp(){
			$.ajax({
				type : "POST",
				url : "../template/rebuildSysTemp",
				dataType : "json",
				data : {
					"caseId" : caseId,
					"docsId" : id
				},
				success : function(data) {
					var result = '保存失败，请稍后再试！';
					if(data.res){
						result= '保存成功！';
					}
					layer.open({
	        			  icon:1,
	        			  content: result 
	        			});
					getInfo();
				}
			})
		}
		function printme() {
			if($(".text_button").find("span").length > 0) {
				$(".text_button").find("span").hide();
			} else {
				$(".text_button").hide();
			}
			var text = $(".button").hide();
			saveToHis();
			window.print();
			if($(".text_button").find("span").length > 0) {
				$(".text_button").find("span").show();
			} else {
				$(".text_button").show();
			}
			var text = $(".button").show();
		}
		
		function isNullDate(data, format) {
			if(data == null) {
				return "";
			}
			var date = new Date(parseInt(data.time)).Format(format);
			return date;
		}
		function getBirthdayFromIdCard(idCard) {  
	        var birthday = "";  
	        if(idCard != null && idCard != ""){  
	            if(idCard.length == 15){  
	                birthday = "19"+idCard.substr(6,6);  
	            } else if(idCard.length == 18){  
	                birthday = idCard.substr(6,8);  
	            }  
	          
	            birthday = birthday.replace(/(.{4})(.{2})/,"$1-$2-"); 
	            birthday = birthday.replace("-", "年");
	            birthday = birthday.replace("-", "月");
	            birthday += "日";
	        }  
	        return birthday;  
      	}
		
		function saveToHis(){
			$.ajax({
				type : "POST",
				url : "../template/savaToHis",
				dataType : "json",
				data : {
					"caseId" : caseId,
					"docsId" : id
				},
				success : function(data) {
					
				}
			})
		}