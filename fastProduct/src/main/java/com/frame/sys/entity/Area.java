
package com.frame.sys.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.frame.core.entity.BizBaseEntity;

public class Area extends BizBaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
    * @ code : 区域编码
    */
    private String code ;
    
    /**
    * @ name : 区域名称
    */
    private String name;
    
  
    /**
    * @ pid : 区域父编号
    */
    private String pid;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPid() {
		return pid;
	}


	public void setPid(String pid) {
		this.pid = pid;
	}
    
	
   
}
