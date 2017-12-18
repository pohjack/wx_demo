/**
 * @Title: UserRole.java
 * @Package com.frame.sys.entity
 * @Description: 用户角色关联
 * @author: lzl
 * @date 2016年7月13日 上午9:25:55
 * @version V1.0
 */
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class UserRole extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    public UserRole(){
    	
    }
    /**
     * 
    * @Title: 传参构造器
    * @Description: 目前只在com.frame.sys.service.impl.UserServiceImpl中的saveOrUpdateUserAndUserRole()使用
    * @param id
    * @param userId
    * @param roleId
     */
    public UserRole(String id, String userId, String roleId){
    	this.id = id;
    	this.userId = userId;
    	this.roleId = roleId;
	}
    
    private String userId;
    
    private String roleId;
    
    public String getUserId(){
        return userId;
    }
    
    public void setUserId(String userId){
        this.userId = userId;
    }
    
    public String getRoleId(){
        return roleId;
    }
    
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    
}
