/**    
* @Title: CaseCigar.java
* @Package com.frame.tobaCase.entity
* @Description: 涉案卷烟实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class CaseGangYin extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String caseId; // 案件信息id
    private String cigarId; // 卷烟数据库ID，外键
    private String customerCode; // 客户代码
    private String name; // 卷烟品牌
    private String company;// 所属公司
    private int number; // 卷烟数量

    public String getCaseId() {
	return caseId;
    }

    public void setCaseId(String caseId) {
	this.caseId = caseId;
    }

    public String getCigarId() {
	return cigarId;
    }

    public void setCigarId(String cigarId) {
	this.cigarId = cigarId;
    }

    public String getCustomerCode() {
	return customerCode;
    }

    public void setCustomerCode(String customerCode) {
	this.customerCode = customerCode;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getCompany() {
	return company;
    }

    public void setCompany(String company) {
	this.company = company;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

}
