/**    
* @Title: CaseInfoSlave.java
* @Package com.frame.tobaCase.entity
* @Description: 涉案信息实体从类对象
* @author: linpy
* @date 2017年2月20日 下午2:40:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.frame.core.entity.BizBaseEntity;

public class CaseInfoSlave extends BizBaseEntity {
    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String masterId; // 案件基本信息主表编号，外键
    private String userId; // 承办人编号
    private String userName; // 承办人名字
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspDate; // 送检日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date askDate; // 询问日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inInformDate; // 事先告知日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date evalDate; // 估价日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endCase; // 结案日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date penaltyDate; // 处罚决定书日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endInquDate; // 调查终结时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date archiveDate; // 归档日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inspRepoDate; // 检验报告日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date batchsDate; // 呈批日期

    public String getMasterId() {
	return masterId;
    }

    public void setMasterId(String masterId) {
	this.masterId = masterId;
    }

    public String getUserId() {
	return userId;
    }

    public void setUserId(String userId) {
	this.userId = userId;
    }

    public String getUserName() {
	return userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public Date getInspDate() {
	return inspDate;
    }

    public void setInspDate(Date inspDate) {
	this.inspDate = inspDate;
    }

    public Date getAskDate() {
	return askDate;
    }

    public void setAskDate(Date askDate) {
	this.askDate = askDate;
    }

    public Date getInInformDate() {
	return inInformDate;
    }

    public void setInInformDate(Date inInformDate) {
	this.inInformDate = inInformDate;
    }

    public Date getEvalDate() {
	return evalDate;
    }

    public void setEvalDate(Date evalDate) {
	this.evalDate = evalDate;
    }

    public Date getEndCase() {
	return endCase;
    }

    public void setEndCase(Date endCase) {
	this.endCase = endCase;
    }

    public Date getPenaltyDate() {
	return penaltyDate;
    }

    public void setPenaltyDate(Date penaltyDate) {
	this.penaltyDate = penaltyDate;
    }

    public Date getEndInquDate() {
	return endInquDate;
    }

    public void setEndInquDate(Date endInquDate) {
	this.endInquDate = endInquDate;
    }

    public Date getArchiveDate() {
	return archiveDate;
    }

    public void setArchiveDate(Date archiveDate) {
	this.archiveDate = archiveDate;
    }

    public Date getInspRepoDate() {
	return inspRepoDate;
    }

    public void setInspRepoDate(Date inspRepoDate) {
	this.inspRepoDate = inspRepoDate;
    }

    public Date getBatchsDate() {
	return batchsDate;
    }

    public void setBatchsDate(Date batchsDate) {
	this.batchsDate = batchsDate;
    }

}
