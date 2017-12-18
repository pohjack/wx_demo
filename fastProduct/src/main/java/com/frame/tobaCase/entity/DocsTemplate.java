/**    
* @Title: DocsTemplate.java
* @Package com.frame.tobaCase.entity
* @Description: 文书模板主表对应实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class DocsTemplate extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String name; // 文书模板名称
    private String code; // 文书模板代码
    private String description; // 文书模板描述

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
