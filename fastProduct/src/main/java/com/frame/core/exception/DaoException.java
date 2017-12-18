/**    
* @Title: DaoException.java
* @Package com.frame.core.exception
* @Description: 数据访问层异常
* @author: lzl
* @date 2016年10月25日 上午11:30:45
* @version V1.0
*/
package com.frame.core.exception;
public class DaoException extends BaseAppException{
	private static final long serialVersionUID = 1L;

	public DaoException(
	    String code){
		super (code, null, code, null);
	}

	public DaoException(
	    Throwable cause,String code){
		super (code, cause, code, null);
	}

	public DaoException(
	    String code,Object[] values){
		super (code, null, code, values);
	}

	public DaoException(
	    Throwable cause,String code,Object[] values){
		super (code, null, code, values);
	}
}
