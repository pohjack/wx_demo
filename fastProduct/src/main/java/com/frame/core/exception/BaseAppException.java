/**    
* @Title: BaseAppException.java
* @Package com.frame.core.exception
* @Description: 基类异常
* @author: lzl
* @date 2016年10月25日 上午11:24:24
* @version V1.0
*/
package com.frame.core.exception;
public class BaseAppException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	/**
	* @ code : 异常代码
	*/
	private String code;

	/**
	* @ values : 异常消息值
	*/
	private Object[] values;

	public BaseAppException(){
		super ();
	}

	public BaseAppException(
	    String message,Throwable cause,String code,Object[] values){
		super (message, cause);
		this.code = code;
		this.values = values;
	}

	public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public Object[] getValues(){
		return values;
	}

	public void setValues(Object[] values){
		this.values = values;
	}
}
