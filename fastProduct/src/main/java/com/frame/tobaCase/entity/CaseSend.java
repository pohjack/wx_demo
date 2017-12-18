/**    
* @Title: Law.java
* @Package com.frame.tobaCase.entity
* @Description: 法律法规实体对象
* @author: shizh
* @date 2017年2月23日 下午1:44:35
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;

public class CaseSend extends BizBaseEntity {

    /**
    * @ serialVersionUID : 
    */
    private static final long serialVersionUID = 1L;

    /**
     * @ code :送达类型 1 2 3 4
     */
    private String code;

    /**
    * @ place : 送达地点
    */
    private String place;
    /**
     * @ way : 送达方式
     */
    private String way;

    /**
    * @ caseId : 案件编号
    */
    private String caseId;

    private String wenhao;
    
    public String getWenhao() {
        return wenhao;
    }

    public void setWenhao(String wenhao) {
        this.wenhao = wenhao;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getPlace() {
	return place;
    }

    public void setPlace(String place) {
	this.place = place;
    }

    public String getWay() {
	return way;
    }

    public void setWay(String way) {
	this.way = way;
    }

    public String getCaseId() {
	return caseId;
    }

    public void setCaseId(String caseId) {
	this.caseId = caseId;
    }

}
