/**
 * @Title: Resource.java
 * @Package com.frame.sys.entity
 * @Description: 资源实体类
 * @author: lzl
 * @date 2016年7月13日 上午9:38:36
 * @version V1.0
 */
package com.frame.sys.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frame.core.entity.BizBaseEntity;

public class Resources extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ name : 资源名称
    */
    private String resName;
    
    /**
    * @ url : 资源地址
    */
    private String resUrl;
    
    /**
    * @ description : 资源描述
    */
    private String resDescription;
    
    /**
    * @ icon : 资源图标
    */
    @JsonProperty("iconCls")
    private String resIcon;
    
    /**
    * @ pid : 资源父编号
    */
    private String resPid;
    
    /**
    * @ resourcetype : 资源类型
    */
    private Integer resType;
    
    /**
    * @ hasSub : 是否有子资源
    */
    private boolean hasSub;
    
    /**
    * @ subMenu : 子类资源
    */
    private List<Resources> subMenu = new ArrayList<Resources>();
    
    /**
     * @ subMenu : 权限标识
     */
    private String permission;
    
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getResName() {
		return resName;
	}

	public void setResName(String resName) {
		this.resName = resName;
	}

	public String getResUrl() {
		return resUrl;
	}

	public void setResUrl(String resUrl) {
		this.resUrl = resUrl;
	}

	public String getResDescription() {
		return resDescription;
	}

	public void setResDescription(String resDescription) {
		this.resDescription = resDescription;
	}

	public String getResIcon() {
		return resIcon;
	}

	public void setResIcon(String resIcon) {
		this.resIcon = resIcon;
	}

	public String getResPid() {
		return resPid;
	}

	public void setResPid(String resPid) {
		this.resPid = resPid;
	}

	public Integer getResType() {
		return resType;
	}

	public void setResType(Integer resType) {
		this.resType = resType;
	}

	public boolean isHasSub() {
		return hasSub;
	}

	public void setHasSub(boolean hasSub) {
		this.hasSub = hasSub;
	}

	public List<Resources> getSubMenu() {
		return subMenu;
	}

	public void setSubMenu(List<Resources> subMenu) {
		this.subMenu = subMenu;
	}
    
   
}
