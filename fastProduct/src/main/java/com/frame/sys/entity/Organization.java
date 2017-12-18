/**
 * @Title: Organization.java
 * @Package com.frame.sys.entity
 * @Description: 组织机构
 * @author: lzl
 * @date 2016年7月13日 上午9:16:42
 * @version V1.0
 */
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class Organization extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    private String orgCode;
    private String orgName;
    private String orgDescription;
    private String orgAddress;
    private String orgPid;
    private String orgIcon;
    private String orgLeader;
    
	public String getOrgCode(){
		return orgCode;
	}
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
	public String getOrgName(){
		return orgName;
	}
	public void setOrgName(String orgName){
		this.orgName = orgName;
	}
	public String getOrgDescription(){
		return orgDescription;
	}
	public void setOrgDescription(String orgDescription){
		this.orgDescription = orgDescription;
	}
	public String getOrgAddress(){
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress){
		this.orgAddress = orgAddress;
	}
	public String getOrgPid(){
		return orgPid;
	}
	public void setOrgPid(String orgPid){
		this.orgPid = orgPid;
	}
	public String getOrgIcon(){
		return orgIcon;
	}
	public void setOrgIcon(String orgIcon){
		this.orgIcon = orgIcon;
	}
	public String getOrgLeader(){
		return orgLeader;
	}
	public void setOrgLeader(String orgLeader){
		this.orgLeader = orgLeader;
	}
    
}
