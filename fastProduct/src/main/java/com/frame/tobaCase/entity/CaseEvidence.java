/**    
* @Title: CaseEvidence.java
* @Package com.frame.tobaCase.entity
* @Description: 涉案证据实体对象
* @author: shizh
* @date 2017年2月22日 下午5:52:26
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class CaseEvidence extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    private String caseInfoId; // 案件基本信息编号，外键
    private Integer evidType; // 证据类型.1->店铺照片,2->涉案卷烟照片,3->许可证副本,4->现场照片,5->涉嫌烟草条码明细表
    private int evidNo; // 证据数量
    private String evidName; // 证据名称

    public String getCaseInfoId() {
	return caseInfoId;
    }

    public void setCaseInfoId(String caseInfoId) {
	this.caseInfoId = caseInfoId;
    }

    public Integer getEvidType() {
	return evidType;
    }

    public void setEvidType(Integer evidType) {
	this.evidType = evidType;
    }

    public int getEvidNo() {
	return evidNo;
    }

    public void setEvidNo(int evidNo) {
	this.evidNo = evidNo;
    }

    public String getEvidName() {
	return evidName;
    }

    public void setEvidName(String evidName) {
	this.evidName = evidName;
    }
}
