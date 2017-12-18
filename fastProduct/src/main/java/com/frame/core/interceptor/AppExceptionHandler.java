/**    
* @Title: AppExceptionHandler.java
* @Package com.frame.core.interceptor
* @Description: 项目统一异常处理
* @author: lzl
* @date 2016年10月29日 上午10:37:51
* @version V1.0
*/
package com.frame.core.interceptor;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.HttpUtil;
import com.frame.core.commons.utils.PropertiesUtil;
import com.frame.core.exception.BaseAppException;
import com.frame.sys.dao.ILogExceptionDao;
import com.frame.sys.entity.LogException;
import com.frame.sys.entity.LogLogin;
import com.frame.sys.entity.User;
import com.frame.sys.service.IUserService;

public class AppExceptionHandler implements HandlerExceptionResolver{
	protected Logger logger = LoggerFactory.getLogger (this.getClass ());
	
	@Resource
    private ILogExceptionDao logExceptionDao;
	@Resource
	public IUserService userService;
	
	/**
	 * 
	* @Description: springmvc统一异常处理
	* @param @param request
	* @param @param response
	* @param @param handler
	* @param @param ex
	* @author shizh
	* @date 2016年11月17日 上午10:54:57
	* @throws
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){
		logger.info ("=====App Exception start or get ====");
		
		//HandlerMethod handlerMethod = (HandlerMethod)handler;
		//MethodParameters methodParameters = handlerMethod.getMethodParameters(); //获取被拦截方法的参数,通过MethodParameters对象可以获取参数名，类型等
		//MethodParameter returnType = handlerMethod.getReturnType();
		
		String[] split = request.getRequestURI().split(SysConstant.PAGE_PATH_SEPARATOR);
		String type = split[split.length - 1];
		String code = "";
		Object[] args;
		if (ex instanceof BaseAppException) {
			BaseAppException baseException = (BaseAppException) ex;
			code = baseException.getCode();
			args = baseException.getValues();
			if((PropertiesUtil.getLogProperty("loginMethodName")).equals(type)){//如果是登录方法则记录登录失败日志
				String[] parameterValues = request.getParameterValues(PropertiesUtil.getLogProperty("userName"));
				LogLogin login = new LogLogin();
				login.setCreator(parameterValues[0]);
				login.setLoginIp(HttpUtil.getRemoteHost(request));//登入客户端IP
					login.setType("登录失败");
					login.setContent("登录失败");
					login.setLoginFail(ex.getMessage());
					if(!code.equals(PropertiesUtil.getLogProperty("UNKNOWCOUNT"))){//如果不是账号不存在异常，则修改用户登录失败次数
						User user = new User();
						user.setLoginName(parameterValues[0]);;
						user.setLoginTimes(1);
						user.setLoginTime(new Date());
						userService.updateUserLFTimes(user);
					}
			}
		}else if("org.apache.shiro.authz.UnauthorizedException".equals(ex.getClass().getName())){
			return new ModelAndView("unauth");
		}else{
			code = "404";
			args = new String[]{"系统异常，请联系管理员处理！"};
		}
		saveException(ex, code);
		boolean isAjax = request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString());
		if(!isAjax){
			Map<String, Object> model = new HashMap<>();
			model.put("errorCode", code);
			model.put("message", args);
			return new ModelAndView("/404", model);
		}else{
			HttpUtil.ajaxResponse(response, code, args);
		}
		return null;
	}
	
	
	/**
	 * 
	* @Description: 异常日志持久化到数据库
	* @param @param ex
	* @param @param code
	* @author ShiZH
	* @date 2016年11月16日 上午10:54:27
	* @throws
	 */
	public void saveException(Exception ex, String code){
		LogException logEx = new LogException();
		Throwable initCause = ex.getCause();
		StackTraceElement[] stackTrace = ex.getStackTrace();
		String className = "";
		String fileName = "";
		int lineNumber = 0;
		String methodName = "";
		for (StackTraceElement stackTraceElement : stackTrace) {
			className = stackTraceElement.getClassName();
			fileName = stackTraceElement.getFileName();
			lineNumber = stackTraceElement.getLineNumber();
			methodName = stackTraceElement.getMethodName();
			if (className.contains("controller")) {//只记录自己包中的代码错误
				break;
			}
		}
		logEx.setNamePackage(className);
		logEx.setNameClass(fileName);
		logEx.setNameFunction(methodName);
		logEx.setRumException(lineNumber);
		logEx.setNameException(ex.getClass().getName());
		logEx.setExceptionMesg(initCause==null?null:initCause.toString());
		String Modular = fileName.replace(".java", "");
		//Modular = Modular.replace(".class", "");
		String logProperty = PropertiesUtil.getLogProperty(Modular);
		if(StringUtils.isEmpty(logProperty))
			logProperty="未知异常";
		logEx.setNameMethod(logProperty);
		logEx.setExceptionCode(code);
		logExceptionDao.save(logEx);
	}
}
