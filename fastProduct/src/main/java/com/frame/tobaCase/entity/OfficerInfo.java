/**    
* @Title: OfficerInfo.java
* @Package com.frame.tobaCase.entity
* @Description: 执法人员信息实体对象
* @author: shizh
* @date 2017年2月13日 下午2:51:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class OfficerInfo extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String code; // 执法人员编号
    private String name; // 执法人员姓名
    private String cardId; // 执法证编号
    private String gender; // 性别
    private Integer phone; // 联系电话

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

    public String getCardId() {
	return cardId;
    }

    public void setCardId(String cardId) {
	this.cardId = cardId;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public Integer getPhone() {
	return phone;
    }

    public void setPhone(Integer phone) {
	this.phone = phone;
    }
}
