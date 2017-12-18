$(function(){
	//数字限制
	$(".intNum").keyup(function(){
		   this.v=function(){
			   this.value=this.value.replace(/[^0-9]+/,'');
	   		}.call(this)
	   })
	$(".intNum").blur(function(){
		   this.v=function(){
			   this.value=this.value.replace(/[^0-9]+/,'');
			}.call(this)
	})
})
//非空判断
function notNull(id, msg) {
	var v = $("#" +id).val();
	if(v != null) {
		var value = v.trim();
		if(value!=null && value!="") {
			return true;
		} else {
			overlyTipsLayer(msg, "#FF4500", 3000, 4, $("#" +id), true);  
			return false;
		}
	}
}
//页面跳转
function tipSkip(tip, caseInfoId,tag) {
	top.layer.confirm('修改成功，是否跳转到文书预览或列表页面', {
	    btn: ['文书预览', '案件查询列表', '取消']
	  },function(index, layero) {
		  top.layer.close(index);
		  window.location.href = tip +"/docTemplate/"+tag+"&caseId=" +caseInfoId;
	  },function() {
		  window.location.href = tip +"/webmaster/caseInfo/toList";
	  });
}
//跳转到文书模板
function skipInspect(tip,tag, caseId) {
	window.location.href = tip +"/docTemplate/"+tag+"&caseId=" +caseId;
}
//判断是否保存了基本信息
function isInfo(tip, caseInfoId,tagId) {
	if(caseInfoId == '') {
		parent.layer.open({
			closeBtn: 0,
			content: '请先完善基本信息',
			yes: function(index, layero){
				parent.layer.close(index);
			    window.location.href = tip +"/webmaster/caseInfo/toEditCase?tagId="+tagId;
			}
		});   
		return false;
	}
	return true;
}
//判断是否保存了基本信息以及涉案人员
function isInfoAndPerv(tip, caseInfoId,tagId) {
		var flag = false;
	if(caseInfoId == '') {
		parent.layer.open({
			closeBtn: 0,
			content: '请先完善基本信息',
			yes: function(index, layero){
			    window.location.href = tip +"/webmaster/caseInfo/toEditCase?tagId="+tagId;
				parent.layer.close(index);
			}
		});
	} else {
		$.ajax({
	         url : tip +"/webmaster/caseInfo/isPersWrite",
	         data : {"caseInfoId": caseInfoId},
	         type : 'post',
	         async: false,
	         dataType : 'json',    
	         success : function(data) {    
	        	 if(data.write == 0) {
	        		 parent.layer.open({
	        			closeBtn: 0,
	 					content: '请先完善涉案人员',
	 					yes: function(index, layero){
	 					    window.location.href = tip +"/webmaster/caseInfo/toEditPer?&type=add&caseInfoId=" +caseInfoId+"&tagId="+tagId;
	 						parent.layer.close(index);
	 					}
	 				});
	        	 } else {
	        		 flag = true;
	        	 }
	         },
	     });  
	}
	return flag;
}