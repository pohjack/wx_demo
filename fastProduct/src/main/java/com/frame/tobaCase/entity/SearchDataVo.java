/**    
* @Title: SearchDataVo.java
* @Package com.frame.tobaCase.entity
* @Description: 案件搜索实体 
* @author: liy
* @date 2017年4月14日 上午9:36:19
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.util.List;

public class SearchDataVo {
    private String caseStatus; // 案件状态

    private String startDate; // 开始时间

    private String endDate; // 结束时间

    private String perRespon; // 案发当事人

    private String regiNo; // 立案编号

    private String premises; // 案发地点

    private String idCard; // 身份证号

    private List<String> orgs; // 当前登录人所在部门以及下属部门id集合

    public String getCaseStatus() {
	return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
	this.caseStatus = caseStatus;
    }

    public String getStartDate() {
	return startDate;
    }

    public void setStartDate(String startDate) {
	this.startDate = startDate;
    }

    public String getEndDate() {
	return endDate;
    }

    public void setEndDate(String endDate) {
	this.endDate = endDate;
    }

    public String getPerRespon() {
	return perRespon;
    }

    public void setPerRespon(String perRespon) {
	this.perRespon = perRespon;
    }

    public String getRegiNo() {
	return regiNo;
    }

    public void setRegiNo(String regiNo) {
	this.regiNo = regiNo;
    }

    public String getPremises() {
	return premises;
    }

    public void setPremises(String premises) {
	this.premises = premises;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public List<String> getOrgs() {
	return orgs;
    }

    public void setOrgs(List<String> orgs) {
	this.orgs = orgs;
    }
}
