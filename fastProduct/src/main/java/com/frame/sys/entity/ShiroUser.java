/**
 * @Title: ShiroUser.java
 * @Package com.frame.sys.entity
 * @Description: 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author: lzl
 * @date 2016年7月11日 下午6:33:45
 * @version V1.0
 */
package com.frame.sys.entity;

import java.io.Serializable;
import java.util.List;

public class ShiroUser implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    public String id;
    
    public String loginName;
    
    public String name;
    
    public String orgId;
    
    public List<Role> roleList;
    
    public ShiroUser(){
    }
    
    
    
    public ShiroUser(String id, String loginName, String name, String orgId, List<Role> roleList) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
		this.orgId = orgId;
		this.roleList = roleList;
	}



	public String getName(){
        return name;
    }
    
    public List<Role> getRoleList(){
        return roleList;
    }

}
