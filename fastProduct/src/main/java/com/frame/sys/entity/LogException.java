/**    
* @Title: LogException.java
* @Package com.frame.core.entity
* @Description: 异常日志对象类
* @author: chensy
* @date 2016年10月13日 下午17:40:00
* @version V1.0
*/
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class LogException extends BizBaseEntity{
    
	private static final long serialVersionUID = 1L;
	
	private String nameClass;		//类名称
    private String nameFunction;	//方法名称
    private String nameException;	//异常名称
    private String nameMethod;		//模块名称
    private Integer rumException;	//受影响行数
    private String namePackage;	//包名称
    private String exceptionMesg; //异常信息
    private String exceptionCode; //异常代码
    
	public String getExceptionMesg() {
		return exceptionMesg;
	}
	public void setExceptionMesg(String exceptionMesg) {
		this.exceptionMesg = exceptionMesg;
	}
	public String getExceptionCode() {
		return exceptionCode;
	}
	public void setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}
	public String getNameClass(){
		return nameClass;
	}
	public void setNameClass(String nameClass){
		this.nameClass = nameClass;
	}
	public String getNameFunction(){
		return nameFunction;
	}
	public void setNameFunction(String nameFunction){
		this.nameFunction = nameFunction;
	}
	public String getNameException(){
		return nameException;
	}
	public void setNameException(String nameException){
		this.nameException = nameException;
	}
	public String getNameMethod(){
		return nameMethod;
	}
	public void setNameMethod(String nameMethod){
		this.nameMethod = nameMethod;
	}
	public Integer getRumException(){
		return rumException;
	}
	public void setRumException(Integer rumException){
		this.rumException = rumException;
	}
	public String getNamePackage(){
		return namePackage;
	}
	public void setNamePackage(String namePackage){
		this.namePackage = namePackage;
	}
	
}
