/**
 * @Title: User.java
 * @Package com.frame.sys.entity
 * @Description: 系统用户
 * @author: lzl
 * @date 2016年7月11日 下午5:13:10
 * @version V1.0
 */
package com.frame.sys.entity;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.frame.core.entity.BizBaseEntity;
import com.frame.tobaCase.utils.ExcelAnnotation;

public class User extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ loginname : 登录名
    */
    @ExcelAnnotation(exportName="用户名")
    private String loginName;
    
    /**
    * @ password : 密码
    */
    private String password;
    
    /**
    * @ realName : 真实姓名
    */
    @ExcelAnnotation(exportName="真实姓名")
    private String realName;
    /**
     * @ mobile : 手机号码
     */
    @ExcelAnnotation(exportName="手机号码")
    private String mobile;
    /**
     * @ email : 电子邮箱
     */
    @ExcelAnnotation(exportName="电子邮箱")
    private String email;
    
    /**
     * @ orgID : 组织机构id
     */
    private String  orgId;
    /**
     * @ loginTimes : 登录失败次数
     */
    private Integer loginTimes;
    /**
     * @ failTime : 上次登录时间
     */
    private Date loginTime;
    
    @ExcelAnnotation(exportName="执法证号")
    private String cardId; //执法证号码
    
    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getLoginTimes() {
		return loginTimes;
	}

	public void setLoginTimes(Integer loginTimes) {
		this.loginTimes = loginTimes;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginName(){
        return loginName;
    }
    
    public void setLoginName(String loginName){
        this.loginName = loginName;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getRealName(){
        return realName;
    }
    
    public void setRealName(String realName){
        this.realName = realName;
    }

	public String getOrgId(){
		return orgId;
	}

	public void setOrgId(String orgId){
		this.orgId = orgId;
	}

	public String getCardId(){
		return cardId;
	}

	public void setCardId(String cardId){
		this.cardId = cardId;
	}
	
	/**
	 * 获取表头字段名称
	 * @return
	 */
	public Map<String, String> getHeader(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("用户名", "loginName");
		map.put("真实姓名", "realName");
		map.put("执法证号", "cardId");
		map.put("手机号码", "mobile");
		map.put("邮箱地址", "email");
		return map;
	}
	/**
	 * 获取表头字段名称
	 * @return
	 */
	public Map<String, String> setHeader(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("loginName", "用户名");
		map.put("realName", "真实姓名");
		map.put("cardId", "执法证号");
		map.put("mobile", "手机号码");
		map.put("email", "邮箱地址");
		return map;
	}
	
}
