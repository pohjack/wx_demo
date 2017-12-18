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

public class Law extends BizBaseEntity {

    /**
    * @ serialVersionUID : 
    */
    private static final long serialVersionUID = 1L;

    private String caseCauseNo;
    private String caseCauseCont;
    private String breakLaw;
    private String punishLaw;
    private String authenRes;

    public String getCaseCauseNo() {
	return caseCauseNo;
    }

    public void setCaseCauseNo(String caseCauseNo) {
	this.caseCauseNo = caseCauseNo;
    }

    public String getCaseCauseCont() {
	return caseCauseCont;
    }

    public void setCaseCauseCont(String caseCauseCont) {
	this.caseCauseCont = caseCauseCont;
    }

    public String getBreakLaw() {
	return breakLaw;
    }

    public void setBreakLaw(String breakLaw) {
	this.breakLaw = breakLaw;
    }

    public String getPunishLaw() {
	return punishLaw;
    }

    public void setPunishLaw(String punishLaw) {
	this.punishLaw = punishLaw;
    }

    public String getAuthenRes() {
	return authenRes;
    }

    public void setAuthenRes(String authenRes) {
	this.authenRes = authenRes;
    }
}
