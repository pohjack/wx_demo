/**    
* @Title: ExceptionHandler.java
* @Package com.frame.core.interceptor
* @Description: TODO(用一句话描述该文件做什么)
* @author: Chensy
* @date 2016年10月12日 下午4:14:18
* @version V1.0
*/
package com.frame.sys.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.frame.core.commons.utils.PropertiesUtil;
import com.frame.sys.entity.LogException;


public class ExceptionHandler implements HandlerExceptionResolver{
	/*@Resource
    private ILogExceptionDao exceptionDao;*/
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		/* 区分ajax */
		boolean isAjax = request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString());
		
		String errorCode = "";
		String message = "";
		LogException logEx = new LogException();
		StackTraceElement stackTraceElement= ex.getStackTrace()[0];
		logEx.setNamePackage(stackTraceElement.getClassName());
		logEx.setNameClass(stackTraceElement.getFileName());
		logEx.setNameFunction(stackTraceElement.getMethodName());
		logEx.setRumException(stackTraceElement.getLineNumber());
		logEx.setNameException(ex.getClass().getName());
		if("org.apache.shiro.authz.UnauthorizedException".equals(ex.getClass().getName()))
			return new ModelAndView("unauth");
		String Modular = logEx.getNameClass().replace(".java", "");
		Modular = Modular.replace(".class", "");
		String logProperty = PropertiesUtil.getLogProperty(Modular);
		if(StringUtils.isEmpty(logProperty))
			logProperty="系统异常";
		logEx.setNameMethod(logProperty);
		
		/*System.out.println("File="+logEx.getNamePackage());// 包
		System.out.println("Line="+logEx.getNameClass());// 类
		System.out.println("Method="+logEx.getNameFunction());// 方法
		System.out.println("Method="+logEx.getRumException());// 行数
		System.out.println("Method="+logEx.getNameMethod());// 模块
*/		
		if(ex.getClass().equals(DataAccessException.class)){
			errorCode = "SQL_operation_fail";
			message = "数据库操作失败！";
		}else if(ex.getClass().toString().equals(NullPointerException.class.toString())){
			errorCode = "NULL_pointer";
			message = "调用了未经初始化的对象或者是不存在的对象！";
		}else if(ex.getClass().equals(IOException.class)){
			errorCode = "IO_exception";
			message = "IO异常！";
		}else if(ex.getClass().equals(ClassNotFoundException.class)){
			errorCode = "CLASS_not_found";
			message = "指定的类不存在！";
		}else if(ex.getClass().equals(ArithmeticException.class)){
			errorCode = "Arithmetic_exception";
			message = "数学运算异常！";
		}else if(ex.getClass().equals(ArrayIndexOutOfBoundsException.class)){
			errorCode = "IO_exception";
			message = "数组下标越界！";
		}else if(ex.getClass().equals(IllegalArgumentException.class)){
			errorCode = "IllegalArgument_exception";
			message = "方法的参数错误！";
		}else if(ex.getClass().equals(ClassCastException.class)){
			errorCode = "CLASS_Cast";
			message = "类型强制转换错误！";
		}else if(ex.getClass().equals(SecurityException.class)){
			errorCode = "Security_exception";
			message = "违背安全原则异常！";
		}else if(ex.getClass().equals(SQLException.class)){
			errorCode = "SQL_exception";
			message = "操作数据库异常！";
		}else if(ex.getClass().equals(NoSuchMethodError.class)){
			errorCode = "NoSuchMethod_exception";
			message = "方法末找到异常！";
		}else if(ex.getClass().equals(InternalError.class)){
			errorCode = "Internal_exception";
			message = "Java虚拟机发生了内部错误！";
		}
		logEx.setExceptionCode(errorCode);
		//exceptionDao.save(logEx);
		if(!isAjax){
			ModelAndView mv = new ModelAndView();
			if(errorCode.length() + message.length() == 0){
				return new ModelAndView("/error/500");
			}
			/*if(ex instanceof NullPointerException){
				return new ModelAndView("/404");
			}else if(ex instanceof Exception){
				return new ModelAndView("/404");
			}else{
				return new ModelAndView("/error/500");
			}*/
			mv.addObject("errorCode", errorCode);
			mv.addObject("message", message);
			mv.setViewName("/404");
			return mv;
		}
		String jsonRes = "{\"message\":\"" + message + "\",\"errorCode\":\"" + errorCode + "\",\"IsError\":\"true\"}";// 自定义结构和前台对接
		PrintWriter out = null;
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/json;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonRes);
			out.flush();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			out.close();
		}
		return null;
	}
}
