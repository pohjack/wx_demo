/**    
* @Title: ControllerException.java
* @Package com.frame.core.exception
* @Description: 控制层异常处理
* @author: lzl
* @date 2016年10月25日 上午11:39:08
* @version V1.0
*/
package com.frame.core.exception;
public class ControllerException extends BaseAppException{
	private static final long serialVersionUID = 1L;

	public ControllerException(
	    String code){
		super (code, null, code, null);
	}

	public ControllerException(
	    Throwable cause,String code){
		super (code, cause, code, null);
	}

	public ControllerException(
	    String code,Object[] values){
		super (code, null, code, values);
	}

	public ControllerException(
	    Throwable cause,String code,Object[] values){
		super (code, null, code, values);
	}
}
