/**
 * 时间日期范围 初始化
 * 
 * @param start
 *            开始时间（对应页面id名）
 * @param end
 *            结束时间（对应页面id名）
 * @param dateFmt
 *            时间格式 例如 yyyy-MM-dd HH:mm
 * @param nextShow
 *            对应选择开始时间完成后触发对应的页面id
 * @param nextEndShow
 *            对应选择结束时间完成后触发对应的页面id
 */
function initDataTimeRange(start, end, dateFmt, nextShow, nextEndShow,nextTime1,nextTime2) {
	$("#" + start).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			maxDate : '#F{$dp.$D(\'' + end + '\')||\'new Date()\'}',
			autoPickDate : true,
			readOnly : true,
			onpicked : function() {
				$("#" + nextShow).focus();
				/*var stringTime = $("#" + start).val();
				var timestamp2 = new Date(stringTime);
				//timestamp2 = timestamp2 / 1000;
				var nian = timestamp2.getFullYear();
				var youe = timestamp2.getMonth() + 1;
				var day = timestamp2.getDate();
				var hour = timestamp2.getHours();
				var min = timestamp2.getMinutes();
				var miao = timestamp2.getSeconds();*/
				var index=$("#" + start).val().indexOf(" ");
				if (nextEndShow != null) {
						$("#" + nextEndShow).val($("#" + start).val().substring(0,index));
				}
				if (nextTime1 != null) {
					
						$("#" + nextTime1).val($("#" + start).val().substring(index+1));
				}
			},
			lang : 'zh-cn',
			skin : 'twoer'
		});
		
		
	});

	$("#" + end).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			minDate : '#F{$dp.$D(\'' + start + '\')}',
			maxDate : '#F{\'new Date()\'}', // 不能大于当前时间
			autoPickDate : true, // 选中日期
			readOnly : true, // 只读
			onpicked : function() {
				//$("#" + nextEndShow).focus();
				if (nextTime2 != null) {
					var index=$("#" + end).val().indexOf(" ");
						$("#" + nextTime2).val($("#" + end).val().substring(index+1));
				}
			},
			lang : 'zh-cn', // 中文语音
			skin : 'twoer' // 皮肤
		});
	});
	
	
}

/**
 * 时间范围 初始化
 * 
 * @param start
 *            开始时间（对应页面id名）
 * @param end
 *            结束时间（对应页面id名）
 * @param dateFmt
 *            时间格式 例如 yyyy-MM-dd HH:mm
 * @param nextShow
 *            对应选择完成后触发对应的页面id
 */
function initTimeRange(start, end, dateFmt, nextShow) {
	$("#" + start).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			maxDate : '#F{$dp.$D(\'' + end + '\')}',
			autoPickDate : true,
			readOnly : true,
			onpicked : function() {
				/*if (nextShow != null) {
					$("#" + nextShow).focus();
				}*/
			},
			lang : 'zh-cn',
			skin : 'twoer'
		});
	});

	$("#" + end).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			minDate : '#F{$dp.$D(\'' + start + '\')||\'new Date()\'}',
			autoPickDate : true, // 选中日期
			readOnly : true,
			lang : 'zh-cn', // 中文语音
			skin : 'twoer' // 皮肤
		});
	});
}


/**
 * 时间初始化
 * 
 * @param args
 *            当前对象对应页面id名
 * @param dateFmt
 *            时间格式 例如 yyyy-MM-dd HH:mm
 * @param nextShow
 *            对应选择完成后触发对应的页面时间控件id
 */
function initDateTime(args, dateFmt, nextShow) {
	$("#" + args).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			maxDate : '#F{\'new Date()\'}',
			autoPickDate : true,
			readOnly : true,
			position : {},
			onpicked : function() {
				/*if (nextShow != null) {
					$("#" + nextShow).focus();
				}*/		
			},
			lang : 'zh-cn',
			skin : 'twoer'
		});
	});
}

/**
 * 时间初始化
 * 
 * @param args
 *            当前对象对应页面id名
 * @param dateFmt
 *            时间格式 例如 yyyy-MM-dd HH:mm
 * @param nextShow
 *            对应选择完成后触发对应的页面时间控件id
 * @param nextLineDate
 *            对应选择完成后关联下一个日期控件初始化的id
  * @param day
 *           推移天数
 */
function initDateLinkTime(args, dateFmt, nextShow, nextLineDate,day) {
	$("#" + args).on("focus", function() {
		WdatePicker({
			dateFmt : dateFmt,
			// maxDate : '#F{\'new Date()\'}',
			autoPickDate : true,
			readOnly : true,
			position : {},
			onpicked : function() {
				if (nextShow != null) {
					$("#" + nextShow).focus();
				}
				if (nextLineDate != null) {
					if(day==0){
						$("#" + nextLineDate).val($("#" + args).val());
					}else{
						$("#" + nextLineDate).val(getFinalDate($("#" + args).val(),day));
					}
				}
				if (args=="inInformDate"){  //特殊处理
					$("#batchsDate").val($("#" + args).val());  //与呈批相同时间
					$("#penaltyDate").val(getFinalDate($("#" + args).val(),4));//处罚决定书日期推迟4天
				}
			},
			lang : 'zh-cn',
			skin : 'twoer'
		});
	});
}

/**
 * 获取下day日期
 * 
 * @param d
 * @param day
 *            天数
 * @returns {String}
 */
function getNextDay(d, day) {
	d = new Date(d);
	d = +d + 1000 * 60 * 60 * 24 * (day);
	d = new Date(d);
	//格式化
	return d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate();
}

/**
 * 判断是否是工作日（除周六周日）
 * 
 * @param date
 * @returns {Boolean}
 */
function isWorkday(date) {
	var date = !date ? new Date() : new Date(date);
	var getday = date.getDay();
	if (getday == 0 || getday == 6) {
		return false;
	} else {
		return true;
	}
}

/**
 * 获取接下几天中是周末的天数
 * 
 * @param d
 * @param day
 * @returns {Number}
 */
function getNotWorkDay(d, day) {
	var num = 0;
	for (var i = 1; i <= day; i++) {
		if (!isWorkday(getNextDay(d, i))) {
			num++;
		}
	}
	return num;
}
/**
 * 递归获取得到工作日
 * 
 * @param str
 * @returns
 */
function getWorkDay(str) {
	if (isWorkday(str)) {
		return str;
	} else {
		return getWorkDay(getNextDay(str, 1));
	}
}

/**
 * 获取str后 day 后的 工作日
 * 
 * @param str
 *            当前日期
 * @param day
 *            推迟天数
 */
function getFinalDate(str, day) {
	var dayAll = day + parseInt(getNotWorkDay(str, day)); // 排除周末天数 往后推移
	var strNew = getNextDay(str, dayAll);
	return getWorkDay(strNew);
}
