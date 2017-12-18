/**    
* @Title: HttpUtil.java
* @Package com.frame.sys.interceptor
* @Description: http工具类
* @author: shizh
* @date 2016年11月17日 上午10:53:06
* @version V1.0
*/
package com.frame.core.commons.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HttpUtil {

	/**
	 * 
	* @Description: 获取客户端IP127.0.0.1则为本机IP
	* @param @param request
	* @param @return
	* @author: Chensy
	* @date 2016年10月19日 上午10:14:57
	* @throws
	 */
	public static String getRemoteHost(HttpServletRequest request){
	    String ip = request.getHeader("x-forwarded-for");
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getHeader("WL-Proxy-Client-IP");
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
	        ip = request.getRemoteAddr();
	    }
	    return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
	}
	
	/**
	 * 
	* @Description: ajax请求异常返回
	* @param @param response
	* @param @param errorCode
	* @param @param message
	* @author ShiZH
	* @date 2016年11月16日 上午10:54:57
	* @throws
	 */
	public static void ajaxResponse(HttpServletResponse response, String errorCode, Object[] message){
		String array = "";
		for(int i = 0; i < message.length; i++){
			if(i > 0){
				array += ",";
			}
			array += (String)message[i];
		}
		String jsonRes = "{\"message\":\"" + array+ "\",\"errorCode\":\"" + errorCode + "\",\"IsError\":\"true\"}";// 自定义结构和前台对接
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
	}
}
