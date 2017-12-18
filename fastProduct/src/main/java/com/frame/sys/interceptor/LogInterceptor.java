/**    
* @Title: LogInterceptor.java
* @Package com.frame.core.interceptor
* @Description: 统一日志处理实现类
* @author: Chensy
* @date 2016年9月27日 上午9:32:27
* @version V1.0
*/
package com.frame.sys.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.HttpUtil;
import com.frame.core.commons.utils.PropertiesUtil;
import com.frame.sys.dao.ILogLoginDao;
import com.frame.sys.dao.ILogOperationDao;
import com.frame.sys.entity.LogLogin;
import com.frame.sys.entity.LogOperation;
import com.frame.sys.entity.Resources;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.service.IResourcesService;
import com.frame.sys.utils.ToJson;

public class LogInterceptor extends HandlerInterceptorAdapter{
	@Resource
	private ILogOperationDao logOperationDao;
	@Resource
	public ILogLoginDao logLoginDao;
	@Resource
	public IResourcesService resourcesService;
	
	public String getUserId(){
		ShiroUser user = (ShiroUser)(SecurityUtils.getSubject().getPrincipal());
		return user==null?null:user.id;
	}
	/**  
	* @Description: 在执行方法前
	* @param @return
	* @author: chensy
	* @date 2016年10月17日 下午14:25
	* @throws
	*/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		String[] servletPath = request.getServletPath().split(SysConstant.PAGE_PATH_SEPARATOR);//访问方法路径切割数组
		String type = servletPath[servletPath.length-1];//方法名称，对应操作类型
	/*	if("doLogin".equals(type)){
			String code = (String) request.getSession().getAttribute("validateCode");
			String submitCode = request.getParameter("submitCode");
			if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(code.toLowerCase(), submitCode.toLowerCase())) {
				HttpUtil.ajaxResponse(response, "ValidateError", new String[]{"验证码错误！"});
				return false;
			}
		}*/
		String[] parameterValues = request.getParameterValues("id");
		if((type.toLowerCase().indexOf("save") >= 0 || type.toLowerCase().indexOf("update") >= 0) 
				&& parameterValues != null && parameterValues.length > 0){
					String id = parameterValues[0];
					String uri = SysConstant.PAGE_PATH_SEPARATOR + servletPath[servletPath.length-2] + SysConstant.PAGE_PATH_SEPARATOR + type;
					doSaveOperation(uri, id, request);
		}
		return true;
	}

	/**
	 * 
	* @Description: 如果是修改数据的方法，先将修改前的值保存到操作日志中
	* @param @param uri 操作路径
	* @param @param id 修改数据的id
	* @param @param request
	* @param @throws Exception
	* @author shizh
	* @date 2016年11月18日 上午11:51:09
	* @throws
	 */
	public void doSaveOperation(String uri, String id, HttpServletRequest request) throws Exception{
		Resources resources = resourcesService.findByURI(uri);
		LogOperation operation = new LogOperation();
		operation.setType(resources.getResName());//获取操作类型
		String path = request.getServletPath();
		String[] servletPath = path.split(SysConstant.PAGE_PATH_SEPARATOR);
		String objGuide = "";//log.properties 文件对象名称指针值
		for(int i = 0; i < servletPath.length-1; i++){
			objGuide += servletPath[i] + SysConstant.PAGE_PATH_SEPARATOR;
		}
		String objName = PropertiesUtil.getLogProperty(objGuide);//对象名称
		String tbName = PropertiesUtil.getLogProperty(objName);//操作对象

		//查询修改前的值
		Map<String, Object> sqlMap = findValue(tbName, id);
		
		//操作日志中保存原始值
		operation.setOldValue(new ToJson().outJson(sqlMap).toString());
		operation.setOperationUrl(path);
		operation.setObjOperation(objName);//对象名称
		operation.setObjName(tbName);//操作对象
		operation.setObjKey(id);
		operation.setCreator(getUserId());
		logOperationDao.save(operation);
		request.setAttribute("logid", operation);
	}
	
	/**
	 * 
	* @Description: 查询数据库中操作的值
	* @param @param tbName 表名
	* @param @param id 所操作的数据id
	* @param @return
	* @author shizh
	* @date 2016年11月18日 下午12:03:49
	* @throws
	 */
	public Map<String, Object> findValue(String tbName, String id){
		Map<String, Object> sqlMap = new HashMap<String, Object>();
		sqlMap.put("tbName", tbName);
		sqlMap.put("id", id);
		sqlMap = logOperationDao.findObjectById(sqlMap);
		return sqlMap;
	}
	
	/**  
	* @Description: 在执行方法后
	* @param @return
	* @author: chensy
	* @date 2016年10月17日 下午14:25
	* @throws
	*/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		String sPath = request.getServletPath();
		String[] servletPath = sPath.split(SysConstant.PAGE_PATH_SEPARATOR);//访问方法路径切割数组
		String type = servletPath[servletPath.length-1];//方法名称
		String passMethod = PropertiesUtil.getLogProperty(SysConstant.PASS_METHOD);
		if(modelAndView != null && servletPath.length > 3){
			if(passMethod != null && !(passMethod.contains(type))){
				String path = SysConstant.PAGE_PATH_SEPARATOR + servletPath[servletPath.length-2];//模块名称
				String view = sPath.substring(0, sPath.lastIndexOf(SysConstant.PAGE_PATH_SEPARATOR)) + path;
				String viewName = modelAndView.getViewName();
				String list = view + SysConstant.PAGE_JUMP_TOLIST;
				String edit = view + SysConstant.PAGE_JUMP_EDIT;
				String add = view + SysConstant.PAGE_JUMP_ADD;
				String imges = view + "images";
				if(!(viewName.equals(list) || viewName.equals(edit) || viewName.equals(add) || viewName.equals(imges))){
					modelAndView.setViewName("404");
				}
			}
		}
		LogLogin login = new LogLogin();
		
		if(type.indexOf("login") >= 0 ){
			return;
		}
		String getUserId = getUserId();
		if(type.indexOf("doLogin") >= 0 && StringUtils.isNotEmpty(getUserId)){
			
			login.setLoginIp(HttpUtil.getRemoteHost(request));//登入客户端IP
			login.setType("用户登录");
			login.setContent("登录成功");
			login.setCreator(getUserId);
		}else if(type.indexOf("logout") >= 0){
			login.setLoginIp(HttpUtil.getRemoteHost(request));//登出客户端IP
			login.setType("用户登出");
			login.setContent("登出");
			login.setCreator((String)request.getAttribute("id"));
		}else{
			
			if(request.getAttribute("logid") != null){
				LogOperation operation2 =  (LogOperation) request.getAttribute("logid");
				Map<String, Object> sqlMap = findValue(operation2.getObjName(), operation2.getObjKey());
				operation2.setNewValue(new ToJson().outJson(sqlMap).toString());
				logOperationDao.updateNewVal(operation2);
				return;
			}
			String uri = SysConstant.PAGE_PATH_SEPARATOR + servletPath[servletPath.length-2] + SysConstant.PAGE_PATH_SEPARATOR + type;
			Resources resources = resourcesService.findByURI(uri); 
			String objGuide = "";//log.properties 文件对象名称指针值
			for(int i = 0; i < servletPath.length-1; i++){
				objGuide += servletPath[i] + SysConstant.PAGE_PATH_SEPARATOR;
			}
			String objName = PropertiesUtil.getLogProperty(objGuide);//对象名称
			String tbName = PropertiesUtil.getLogProperty(objName);//操作对象
			String oldValue = null;
			String newValue = null;
			String objKey = null;
			if(type.toLowerCase().indexOf("remove") >= 0){
				oldValue = new ToJson().outJson(request.getParameterMap()).toString();
			}else if(type.toLowerCase().indexOf("to") >= 0 || type.toLowerCase().indexOf("get") >= 0){
				
			}else{
				newValue = new ToJson().outJson(request.getParameterMap()).toString();//操作值
				objKey = request.getParameter("id");
			}
			LogOperation operation = new LogOperation();
			operation.setOldValue(oldValue);
			operation.setNewValue(newValue);
			operation.setObjKey(objKey);
			operation.setCreator(getUserId);//操作用户
			operation.setObjName(tbName);
			operation.setObjOperation(objName);
			operation.setOperationUrl(request.getServletPath());
			operation.setType(resources==null?"未知操作":resources.getResName());//获取操作类型
			logOperationDao.save(operation);
			return;
			}
		logLoginDao.save(login);
	}

}