function getRootPath() {

	// 获取当前网址，如： http://localhost:9527/zdss-web/login/login.do

	var curWwwPath = window.document.location.href;

	// console.log("当前网址：" + curWwwPath);

	// 获取主机地址之后的目录，如：zdss-web/login/login.do

	var pathName = window.document.location.pathname;

	// console.log("当前路径：" + pathName);

	var pos = curWwwPath.indexOf(pathName);

	// console.log("路径位置：" + pos);

	// 获取主机地址，如： http://localhost:9527

	var localhostPath = curWwwPath.substring(0, pos);

	// 获取带"/"的项目名，如：/zdss-web

	var projectName = pathName
			.substring(0, pathName.substr(1).indexOf('/') + 1);

	return localhostPath;

}
/*var URL = getRootPath();
document.write("<script src='" + URL
		+ "/statics/script/layer/layer.js'><\/script>");*/

/**
 * 提示框
 * 
 * @param msg
 *            内容
 * @param icon
 *            图标
 * @param closeBtn
 *            关闭按钮样式，0取消按钮，1,2分别为两种样式
 */
function alertLayer(msg, icon, closeBtn) {
	top.layer.alert(msg, {
		icon : icon,
		closeBtn : closeBtn,
		title : false,
		btn : false,
		time : 2000
	});
}
/**
 * 表单提交前,确定框
 * 
 * @param mesg
 *            内容
 * @param param
 *            业务可能要携带的参数
 */
function confirmLayer(mesg, param, myaction) {
	top.layer.msg(mesg, {
		icon : 3,
		shade : [ 0.3, '#393D49' ],
		shadeClose : false, // 开启遮罩关闭
		time : 0 // 不自动关闭
		,
		btn : [ '确定', '取消' ],
		offset : [ '350px', '45%' ],
		yes : function(index) {
			top.layer.close(index);
			eval(myaction + "('" + param + "')");
			// confirmDo(param);
		}
	});
}
/**
 * 小tips
 * 
 * @param msg
 *            内容
 * @param color
 *            颜色
 * @param time
 *            停留时间
 * @param position
 *            停留方位 1：上；2：右；3：下；4：左
 * @param id
 *            传入格式为#id 或 #class 即该提示要出现在哪个id或者class的周围
 */
function tipsLayer(msg, color, time, position, id) {
	layer.tips(msg, id, {
		tips : [ position, color ],
		time : time,
		tipsMore : true
	});
}
/**
 * 消息框
 * 
 * @param msg
 *            内容
 */
function msgLayer(msg) {
	top.layer.msg(msg);
}
/**
 * 加载框
 * 
 * @param type
 * @param msg
 */
function loadLayer(type, msg) {
	if (type == 0) {
		layer.load();
	} else if (type == 1) {
		layer.load(1);
	} else if (type == 2) {
		layer.load(2);
	} else {
		layer.msg(msg, {
			icon : 16
		});
	}

}
/**
 * 关闭所有加载框
 */
function closeAllLayer() {
	layer.closeAll('loading');
}

/**
 * 正上方
 * 
 * @param shift
 *            弹出方式 如6：震动
 */
function offsetLayer(shift) {
	layer.msg('灵活运用offset', {
		offset : 0,
		shift : shift
	});
}
/**
 * iframe层-多媒体
 * 
 * @param title
 *            标题，为空传入false
 * @param shade
 *            透明度，范围0-1，允许小数点后一位
 * @param closeBtn
 *            是否显示按钮 0：不显示；>0：显示
 * @param shadeClose
 *            周边点击关闭窗口，false：不关闭；true：关闭
 * @param url
 *            iframe 访问地址
 * @param width
 *            显示宽度
 * @param height
 *            显示高度
 * @param num
 *            弹出动画类型，可选数字0-6，分别表示不同的动画效果
 */
function mediaLayer(title, shade, closeBtn, shadeClose, url, width, height, num) {
	top.layer.open({
		type : 2, // 2表示弹出层是iframe
		title : title,
		area : [ width + 'px', height + 'px' ],
		maxmin : true, // 是否显示最大化最小化按钮
		shade : shade,
		skin : 'layui-layer-lan', // 'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
		shift : num,
		closeBtn : closeBtn,
		shadeClose : shadeClose,
		content : [ url, 'no' ], // no表示不显示滚动条
		btn : [ '确认', '取消' ],
		yes : function(index, layero) {
			submitForm(index, layero);
		}
	});
}

function iframeLayer(title, shade, closeBtn, shadeClose, url, width, height,
		num) {
	top.layer.open({
		type : 2, // 2表示弹出层是iframe
		title : title,
		area : [ width + 'px', height + 'px' ],
		maxmin : true, // 是否显示最大化最小化按钮
		shade : shade,
		skin : 'layui-layer-lan', // 'layui-layer-molv',这是插件提供的两个皮肤，也可以填自己写的样式类名
		shift : num,
		closeBtn : closeBtn,
		shadeClose : shadeClose,
		content : [ url ], // no表示不显示滚动条
		btn : '关闭'
	});
}
/**
 * iframe层-父子操作
 * 
 * @param fix
 *            是否固定 false：不固定；true：固定
 * @param maxmin
 *            是否支持最大最小化 false：不支持；true：支持
 * @param url
 *            iframe 访问地址
 * @param width
 *            显示宽度
 * @param height
 *            显示高度
 */
function relationIframeLayer(fix, maxmin, url, width, height) {
	layer.open({
		type : 2,
		area : [ width + 'px', height + 'px' ],
		fix : fix, // 不固定
		maxmin : maxmin,
		content : url
	});
}
/**
 * 小tips
 * 
 * @param msg
 *            内容
 * @param color
 *            颜色
 * @param time
 *            停留时间
 * @param position
 *            停留方位 1：上；2：右；3：下；4：左
 * @param position
 *            tipsMore 是否销毁之前的
 * @param id
 *            传入格式为#id 或 #class 即该提示要出现在哪个id或者class的周围
 */
function overlyTipsLayer(msg, color, time, position, id, tipsMore) {
	layer.tips(msg, id, {
		tips : [ position, color ],
		time : time,
		tipsMore : tipsMore
	});
}

/**
 * 文件上传加载层
 * 
 * @returns
 */
function ityzl_SHOW_LOAD_LAYER() {
	return top.layer.msg('导入中...', {
		icon : 16,
		shade : [ 0.5, '#f5f5f5' ],
		scrollbar : false,
		time : 100000
	});
}
function ityzl_CLOSE_LOAD_LAYER(index) {
	layer.close(index);
}
function ityzl_SHOW_TIP_LAYER() {
	top.layer.msg('导入成功！', {
		time : 1000
	});
}
function ityzl_SHOW_ERROR_LAYER() {
	top.layer.msg('导入失败！', {
		time : 1000
	});
}
