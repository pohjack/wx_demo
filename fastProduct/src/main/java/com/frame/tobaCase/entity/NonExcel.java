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

public class NonExcel extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;
    private String id;
    private String regiNo; // 编号,如：潮烟立[2017]第001号
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date crimeDate; // 案发时间，立案时间
    private String perRespon; // 当事人
    private String caseAddr; // 案发地点
    private String summary; // 案情摘要
    private int nonSmokeNum; // 非烟数量
    private int kindNo;
    private Double nonPrice = 0.0;// 品种数量

    public Double getNonPrice() {
	return nonPrice;
    }

    public void setNonPrice(Double nonPrice) {
	this.nonPrice = nonPrice;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public int getNonSmokeNum() {
	return nonSmokeNum;
    }

    public void setNonSmokeNum(int nonSmokeNum) {
	this.nonSmokeNum = nonSmokeNum;
    }

    public String getRegiNo() {
	return regiNo;
    }

    public void setRegiNo(String regiNo) {
	this.regiNo = regiNo;
    }

    public Date getCrimeDate() {
	return crimeDate;
    }

    public void setCrimeDate(Date crimeDate) {
	this.crimeDate = crimeDate;
    }

    public String getPerRespon() {
	return perRespon;
    }

    public void setPerRespon(String perRespon) {
	this.perRespon = perRespon;
    }

    public String getCaseAddr() {
	return caseAddr;
    }

    public void setCaseAddr(String caseAddr) {
	this.caseAddr = caseAddr;
    }

    public String getSummary() {
	return summary;
    }

    public void setSummary(String summary) {
	this.summary = summary;
    }

    public int getKindNo() {
	return kindNo;
    }

    public void setKindNo(int kindNo) {
	this.kindNo = kindNo;
    }

}
