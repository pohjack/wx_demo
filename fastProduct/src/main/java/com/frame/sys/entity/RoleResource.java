/**
 * @Title: RoleResource.java
 * @Package com.frame.sys.entity
 * @Description: 角色资源关联表
 * @author: lzl
 * @date 2016年7月13日 上午9:42:11
 * @version V1.0
 */
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class RoleResource extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ roleId : 角色编号
    */
    private String roleId;
    
    /**
    * @ resourceId : 资源编号
    */
    private String resourceId;
    
    public String getRoleId(){
        return roleId;
    }
    
    public void setRoleId(String roleId){
        this.roleId = roleId;
    }
    
    public String getResourceId(){
        return resourceId;
    }
    
    public void setResourceId(String resourceId){
        this.resourceId = resourceId;
    }
}
