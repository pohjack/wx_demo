/**    
* @Title: BaseTemplateSlave.java
* @Package com.frame.tobaCase.entity
* @Description: 基础模板从表实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class BaseTemplateSlave extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String masterId; // 基础模板表主表ID，外键
    private String quoteId; // 引用编号，可以是数据字典id或者模板id
    private Integer type; // 引用类型，0表示未引用，1表示引用数据字典，2表示引用模板
    private String value; // 本条数据的值
    private String code; // 代码
    private Integer category; // 本条数据的类别，1表示XX,2表示XX

    public String getMasterId() {
	return masterId;
    }

    public void setMasterId(String masterId) {
	this.masterId = masterId;
    }

    public String getQuoteId() {
	return quoteId;
    }

    public void setQuoteId(String quoteId) {
	this.quoteId = quoteId;
    }

    public Integer getType() {
	return type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Integer getCategory() {
	return category;
    }

    public void setCategory(Integer category) {
	this.category = category;
    }
}
