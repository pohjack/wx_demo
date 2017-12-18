/**
 * @Title: Role.java
 * @Package com.frame.sys.entity
 * @Description: 角色
 * @author: lzl
 * @date 2016年7月13日 上午9:18:10
 * @version V1.0
 */
package com.frame.sys.entity;

import com.frame.core.entity.BizBaseEntity;

public class Role extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ name : 角色名称
    */
    private String name;
    /**
     * @ name : 角色编号
     */
     private String code;
    
    /**
    * @ description : 角色描述
    */
    private String description;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    
    public String getCode(){
		return code;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
}
