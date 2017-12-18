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

public class CaseCigar extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String caseId; // 案件信息id
    private String cigarId; // 卷烟数据库ID，外键
    private String name; // 卷烟名称
    private int number; // 卷烟数量
    private String unit; // 单位
    private double retailPrice; // 卷烟零售价
    private double totalValue; // 卷烟总价值
    private String preQualitative; // 初步定性
    private String inspectResult; // 检查结果
    private String cigarAttr; // 卷烟属性
    private int inspectNum; // 送检数量
    private int returnNum; // 返还数量
    private String barCode; // 条形码
    private String format;// 规格

    public String getBarCode() {
	return barCode;
    }

    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

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

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getNumber() {
	return number;
    }

    public void setNumber(int number) {
	this.number = number;
    }

    public String getUnit() {
	return unit;
    }

    public void setUnit(String unit) {
	this.unit = unit;
    }

    public double getRetailPrice() {
	return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
	this.retailPrice = retailPrice;
    }

    public double getTotalValue() {
	return totalValue;
    }

    public void setTotalValue(double totalValue) {
	this.totalValue = totalValue;
    }

    public String getPreQualitative() {
	return preQualitative;
    }

    public void setPreQualitative(String preQualitative) {
	this.preQualitative = preQualitative;
    }

    public String getInspectResult() {
	return inspectResult;
    }

    public void setInspectResult(String inspectResult) {
	this.inspectResult = inspectResult;
    }

    public String getCigarAttr() {
	return cigarAttr;
    }

    public void setCigarAttr(String cigarAttr) {
	this.cigarAttr = cigarAttr;
    }

    public int getInspectNum() {
	return inspectNum;
    }

    public void setInspectNum(int inspectNum) {
	this.inspectNum = inspectNum;
    }

    public int getReturnNum() {
	return returnNum;
    }

    public void setReturnNum(int returnNum) {
	this.returnNum = returnNum;
    }

    public String getFormat() {
	return format;
    }

    public void setFormat(String format) {
	this.format = format;
    }

}
