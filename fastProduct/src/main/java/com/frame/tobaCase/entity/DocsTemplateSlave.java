/**    
* @Title: DocsTemplateSlave.java
* @Package com.frame.tobaCase.entity
* @Description: 文书模板从表实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class DocsTemplateSlave extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String masterId; // 文书模板表主表ID，外键
    private String baseTempId; // 基础模板表主表ID，即引用的模板编号
    private Integer type; // 引用类型，0表示未引用，1表示引用数据字典，2表示引用模板

    public String getMasterId() {
	return masterId;
    }

    public void setMasterId(String masterId) {
	this.masterId = masterId;
    }

    public String getBaseTempId() {
	return baseTempId;
    }

    public void setBaseTempId(String baseTempId) {
	this.baseTempId = baseTempId;
    }

    public Integer getType() {
	return type;
    }

    public void setType(Integer type) {
	this.type = type;
    }

}
