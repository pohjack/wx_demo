/**    
* @Title: LogLogin.java
* @Package com.frame.core.entity
* @Description: 异常日志对象类
* @author: chensy
* @date 2016年10月13日 下午17:40:00
* @version V1.0
*/
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class LogLogin extends BizBaseEntity{
    
	private static final long serialVersionUID = 1L;
	
	private String type;		//登录类型
    private String loginIp;		//登录IP
    private String loginMac;	//登录MAC
    private String content;		//日志内容
    private String loginFail;   //登录失败原因
    
	public String getLoginFail() {
		return loginFail;
	}
	public void setLoginFail(String loginFail) {
		this.loginFail = loginFail;
	}
	public String getType(){
		return type;
	}
	public void setType(String type){
		this.type = type;
	}
	public String getLoginIp(){
		return loginIp;
	}
	public void setLoginIp(String loginIp){
		this.loginIp = loginIp;
	}
	public String getLoginMac(){
		return loginMac;
	}
	public void setLoginMac(String loginMac){
		this.loginMac = loginMac;
	}
	public String getContent(){
		return content;
	}
	public void setContent(String content){
		this.content = content;
	}
}
