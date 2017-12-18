/**    
* @Title: DictionaryData.java
* @Package com.frame.tobaCase.entity
* @Description: 数据字典实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class DictionaryData extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String name; // 数据名称
    private String code; // 数据代码
    private String value; // 数据值,可以是固定数据或者是替换数据
    private Integer category; // 数据类别，0为常量，1为变量，2为判别量
    private String description; // 描述

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

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public Integer getCategory() {
	return category;
    }

    public void setCategory(Integer category) {
	this.category = category;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

}
