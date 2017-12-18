/**    
* @Title: CaseInfoSlave.java
* @Package com.frame.tobaCase.entity
* @Description: 案件基本信息对象
* @author: linpy
* @date 2017年2月20日 下午2:40:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class CaseInvoPers extends BizBaseEntity {
    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String caseInfoId; // 案件基本信息编号，外键
    private String perRespon; // 负责人(经营者)姓名
    private String premises; // 经营场所，即经营地址
    private String licNo; // 许可证号码
    private String audiDept; // 稽查部门
    private String partyPhone; // 当事人联系电话
    private String retailCode; // 零售户编号
    private String idCard; // 证件号码
    private String idCardAddr; // 身份证地址
    private String corpName; // 企业名称
    private String sex; // 性别.0->女，1->男
    private String age; // 年龄
    private String jobs; // 职业
    private String nation; // 民族
    private String phone; // 联系电话

    public String getCaseInfoId() {
	return caseInfoId;
    }

    public void setCaseInfoId(String caseInfoId) {
	this.caseInfoId = caseInfoId;
    }

    public String getPerRespon() {
	return perRespon;
    }

    public void setPerRespon(String perRespon) {
	this.perRespon = perRespon;
    }

    public String getPremises() {
	return premises;
    }

    public void setPremises(String premises) {
	this.premises = premises;
    }

    public String getLicNo() {
	return licNo;
    }

    public void setLicNo(String licNo) {
	this.licNo = licNo;
    }

    public String getAudiDept() {
	return audiDept;
    }

    public void setAudiDept(String audiDept) {
	this.audiDept = audiDept;
    }

    public String getPartyPhone() {
	return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
	this.partyPhone = partyPhone;
    }

    public String getRetailCode() {
	return retailCode;
    }

    public void setRetailCode(String retailCode) {
	this.retailCode = retailCode;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getIdCardAddr() {
	return idCardAddr;
    }

    public void setIdCardAddr(String idCardAddr) {
	this.idCardAddr = idCardAddr;
    }

    public String getCorpName() {
	return corpName;
    }

    public void setCorpName(String corpName) {
	this.corpName = corpName;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getAge() {
	return age;
    }

    public void setAge(String age) {
	this.age = age;
    }

    public String getJobs() {
	return jobs;
    }

    public void setJobs(String jobs) {
	this.jobs = jobs;
    }

    public String getNation() {
	return nation;
    }

    public void setNation(String nation) {
	this.nation = nation;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

}
