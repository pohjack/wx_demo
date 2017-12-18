/**    
* @Title: ServiceException.java
* @Package com.frame.core.exception
* @Description: 服务层异常管理
* @author: lzl
* @date 2016年10月25日 上午11:37:53
* @version V1.0
*/
package com.frame.core.exception;
public class ServiceException extends BaseAppException{
	private static final long serialVersionUID = 1L;

	public ServiceException(
	    String code){
		super (code, null, code, null);
	}

	public ServiceException(
	    Throwable cause,String code){
		super (code, cause, code, null);
	}

	public ServiceException(
	    String code,Object[] values){
		super (code, null, code, values);
	}

	public ServiceException(
	    Throwable cause,String code,Object[] values){
		super (code, null, code, values);
	}
}
