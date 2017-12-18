/**    
* @Title: GatherModel.java
* @Package com.frame.tobaCase.entity
* @Description: 案件估价汇总 实体
* @author: liy
* @date 2017年4月13日 下午5:41:59
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.frame.core.entity.BizBaseEntity;
import com.frame.tobaCase.utils.ExcelAnnotation;

public class GatherModel extends BizBaseEntity {
    private static final long serialVersionUID = 1L;

    private String id;

    @ExcelAnnotation(exportName = "立案号")
    private String caseNo;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ExcelAnnotation(exportName = "立案时间")
    private Date crimeDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ExcelAnnotation(exportName = "结案时间")
    private Date endCase;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ExcelAnnotation(exportName = "估价时间")
    private Date evalDate;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @ExcelAnnotation(exportName = "检验报告日期")
    private Date inspRepoDate;

    @ExcelAnnotation(exportName = "当事人")
    private String perRespon;

    @ExcelAnnotation(exportName = "电话")
    private String partyPhone;

    @ExcelAnnotation(exportName = "案发地点")
    private String premises;

    @ExcelAnnotation(exportName = "先行登记数量（条）")
    private int preSumNum;

    @ExcelAnnotation(exportName = "假烟")
    private int falseSmokeNum;

    @ExcelAnnotation(exportName = "非烟")
    private int nonSmokeNum;

    @ExcelAnnotation(exportName = "走私烟")
    private int smuggledSmokeNum;

    @ExcelAnnotation(exportName = "总案值（元）")
    private Double SumPrice = 0.0;

    @ExcelAnnotation(exportName = "假烟货值（元）")
    private Double falsePrice = 0.0;

    @ExcelAnnotation(exportName = "非烟货值（元）")
    private Double nonPrice = 0.0;

    @ExcelAnnotation(exportName = "走私烟货值（元）")
    private Double smuggledPrice = 0.0;

    @ExcelAnnotation(exportName = "总罚款")
    private Double SumFine;

    @ExcelAnnotation(exportName = "假烟罚款（元）")
    private Double falseFine;

    @ExcelAnnotation(exportName = "非烟罚款（元）")
    private Double nonFine;

    @ExcelAnnotation(exportName = "属性")
    private String siteAttr;

    @ExcelAnnotation(exportName = "业态")
    private String operaForms;

    @ExcelAnnotation(exportName = "部门")
    private String apartentMent;

    @ExcelAnnotation(exportName = "案件定性")
    private String caseQualitative;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getCaseNo() {
	return caseNo;
    }

    public void setCaseNo(String caseNo) {
	this.caseNo = caseNo;
    }

    public Date getCrimeDate() {
	return crimeDate;
    }

    public void setCrimeDate(Date crimeDate) {
	this.crimeDate = crimeDate;
    }

    public Date getEndCase() {
	return endCase;
    }

    public void setEndCase(Date endCase) {
	this.endCase = endCase;
    }

    public Date getEvalDate() {
	return evalDate;
    }

    public void setEvalDate(Date evalDate) {
	this.evalDate = evalDate;
    }

    public Date getInspRepoDate() {
	return inspRepoDate;
    }

    public void setInspRepoDate(Date inspRepoDate) {
	this.inspRepoDate = inspRepoDate;
    }

    public String getCaseQualitative() {
	return caseQualitative;
    }

    public void setCaseQualitative(String caseQualitative) {
	this.caseQualitative = caseQualitative;
    }

    public String getPerRespon() {
	return perRespon;
    }

    public void setPerRespon(String perRespon) {
	this.perRespon = perRespon;
    }

    public String getPartyPhone() {
	return partyPhone;
    }

    public void setPartyPhone(String partyPhone) {
	this.partyPhone = partyPhone;
    }

    public String getPremises() {
	return premises;
    }

    public void setPremises(String premises) {
	this.premises = premises;
    }

    public int getPreSumNum() {
	return preSumNum;
    }

    public void setPreSumNum(int preSumNum) {
	this.preSumNum = preSumNum;
    }

    public int getFalseSmokeNum() {
	return falseSmokeNum;
    }

    public void setFalseSmokeNum(int falseSmokeNum) {
	this.falseSmokeNum = falseSmokeNum;
    }

    public int getNonSmokeNum() {
	return nonSmokeNum;
    }

    public void setNonSmokeNum(int nonSmokeNum) {
	this.nonSmokeNum = nonSmokeNum;
    }

    public int getSmuggledSmokeNum() {
	return smuggledSmokeNum;
    }

    public void setSmuggledSmokeNum(int smuggledSmokeNum) {
	this.smuggledSmokeNum = smuggledSmokeNum;
    }

    public Double getSumPrice() {
	return SumPrice;
    }

    public void setSumPrice(Double sumPrice) {
	SumPrice = sumPrice;
    }

    public Double getFalsePrice() {
	return falsePrice;
    }

    public void setFalsePrice(Double falsePrice) {
	this.falsePrice = falsePrice;
    }

    public Double getNonPrice() {
	return nonPrice;
    }

    public void setNonPrice(Double nonPrice) {
	this.nonPrice = nonPrice;
    }

    public Double getSmuggledPrice() {
	return smuggledPrice;
    }

    public void setSmuggledPrice(Double smuggledPrice) {
	this.smuggledPrice = smuggledPrice;
    }

    public Double getSumFine() {
	return SumFine;
    }

    public void setSumFine(Double sumFine) {
	SumFine = sumFine;
    }

    public Double getFalseFine() {
	return falseFine;
    }

    public void setFalseFine(Double falseFine) {
	this.falseFine = falseFine;
    }

    public Double getNonFine() {
	return nonFine;
    }

    public void setNonFine(Double nonFine) {
	this.nonFine = nonFine;
    }

    public String getSiteAttr() {
	return siteAttr;
    }

    public void setSiteAttr(String siteAttr) {
	this.siteAttr = siteAttr;
    }

    public String getOperaForms() {
	return operaForms;
    }

    public void setOperaForms(String operaForms) {
	this.operaForms = operaForms;
    }

    public String getApartentMent() {
	return apartentMent;
    }

    public void setApartentMent(String apartentMent) {
	this.apartentMent = apartentMent;
    }
}
