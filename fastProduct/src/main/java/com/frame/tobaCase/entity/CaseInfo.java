/**    
* @Title: CaseInfo.java
* @Package com.frame.tobaCase.entity
* @Description: 涉案信息实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.sql.Time;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.frame.core.entity.BizBaseEntity;

public class CaseInfo extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String regiNo; // 立案编号,如：潮烟立[2017]第001号
    private String caseNo; // 案件编号
    private String advRegiNo; // 先行登记号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date crimeDate; // 案发时间
    @DateTimeFormat(pattern = "HH:mm")
    private Date crimeStartTime; // 案发开始时间，如10时21分
    @DateTimeFormat(pattern = "HH:mm")
    private Date crimeEndTime; // 案发结束时间，如10时57分
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date inquStartDate; // 勘验开始日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date inquEndDate; // 勘验结束日期
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date inquStartTime; // 勘验开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date inquEndTime; // 勘验结束时间
    private String prinName; // 委托人
    private Integer isSite; // 当事人是否在现场，0表示不在现场，1表示在现场
    private String caseCauseCode; // 立案案由代码
    private String caseCause; // 立案案由内容
    private String caseAddr; // 案发地点
    private String entryPerson; // 录入人员
    private String buyWay; // 购烟途径
    private String proxyUrl; // 委托书地址
    private String proxyIdCardUrl; // 身份证图片地址,多个用逗号分开
    private String catchpoleName; // 执法人员
    private String catchpoleNo; // 执法人员证件号
    private Integer fakeRange; // 假烟罚款幅度
    private Integer nonRange; // 非烟罚款幅度
    private String caseSource; // 案件来源
    private String creatOrg; // 案件部门
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date cigarOutdate;// 出库时间

    public String getCaseSource() {
	return caseSource;
    }

    public void setCaseSource(String caseSource) {
	this.caseSource = caseSource;
    }

    public String getRegiNo() {
	return regiNo;
    }

    public void setRegiNo(String regiNo) {
	this.regiNo = regiNo;
    }

    public String getCaseNo() {
	return caseNo;
    }

    public void setCaseNo(String caseNo) {
	this.caseNo = caseNo;
    }

    public String getAdvRegiNo() {
	return advRegiNo;
    }

    public void setAdvRegiNo(String advRegiNo) {
	this.advRegiNo = advRegiNo;
    }

    public Date getCrimeDate() {
	return crimeDate;
    }

    public void setCrimeDate(Date crimeDate) {
	this.crimeDate = crimeDate;
    }

    public Date getCrimeStartTime() {
	return crimeStartTime;
    }

    public void setCrimeStartTime(Date crimeStartTime) {
	this.crimeStartTime = crimeStartTime;
    }

    public Date getCrimeEndTime() {
	return crimeEndTime;
    }

    public void setCrimeEndTime(Date crimeEndTime) {
	this.crimeEndTime = crimeEndTime;
    }

    public Date getInquStartDate() {
	return inquStartDate;
    }

    public void setInquStartDate(Date inquStartDate) {
	this.inquStartDate = inquStartDate;
    }

    public Date getInquEndDate() {
	return inquEndDate;
    }

    public void setInquEndDate(Date inquEndDate) {
	this.inquEndDate = inquEndDate;
    }

    public Date getInquStartTime() {
	return inquStartTime;
    }

    public void setInquStartTime(Date inquStartTime) {
	this.inquStartTime = inquStartTime;
    }

    public Date getInquEndTime() {
	return inquEndTime;
    }

    public void setInquEndTime(Date inquEndTime) {
	this.inquEndTime = inquEndTime;
    }

    public void setInquEndTime(Time inquEndTime) {
	this.inquEndTime = inquEndTime;
    }

    public String getPrinName() {
	return prinName;
    }

    public void setPrinName(String prinName) {
	this.prinName = prinName;
    }

    public Integer getIsSite() {
	return isSite;
    }

    public void setIsSite(Integer isSite) {
	this.isSite = isSite;
    }

    public String getCaseCauseCode() {
	return caseCauseCode;
    }

    public void setCaseCauseCode(String caseCauseCode) {
	this.caseCauseCode = caseCauseCode;
    }

    public String getCaseCause() {
	return caseCause;
    }

    public void setCaseCause(String caseCause) {
	this.caseCause = caseCause;
    }

    public String getCaseAddr() {
	return caseAddr;
    }

    public void setCaseAddr(String caseAddr) {
	this.caseAddr = caseAddr;
    }

    public String getEntryPerson() {
	return entryPerson;
    }

    public void setEntryPerson(String entryPerson) {
	this.entryPerson = entryPerson;
    }

    public String getBuyWay() {
	return buyWay;
    }

    public void setBuyWay(String buyWay) {
	this.buyWay = buyWay;
    }

    public String getProxyUrl() {
	return proxyUrl;
    }

    public void setProxyUrl(String proxyUrl) {
	this.proxyUrl = proxyUrl;
    }

    public String getProxyIdCardUrl() {
	return proxyIdCardUrl;
    }

    public void setProxyIdCardUrl(String proxyIdCardUrl) {
	this.proxyIdCardUrl = proxyIdCardUrl;
    }

    public String getCatchpoleName() {
	return catchpoleName;
    }

    public void setCatchpoleName(String catchpoleName) {
	this.catchpoleName = catchpoleName;
    }

    public String getCatchpoleNo() {
	return catchpoleNo;
    }

    public void setCatchpoleNo(String catchpoleNo) {
	this.catchpoleNo = catchpoleNo;
    }

    public Integer getFakeRange() {
	return fakeRange;
    }

    public void setFakeRange(Integer fakeRange) {
	this.fakeRange = fakeRange;
    }

    public Integer getNonRange() {
	return nonRange;
    }

    public void setNonRange(Integer nonRange) {
	this.nonRange = nonRange;
    }

    public String getCreatOrg() {
	return creatOrg;
    }

    public void setCreatOrg(String creatOrg) {
	this.creatOrg = creatOrg;
    }

    public Date getCigarOutdate() {
	return cigarOutdate;
    }

    public void setCigarOutdate(Date cigarOutdate) {
	this.cigarOutdate = cigarOutdate;
    }

}
