/**    
* @Title: LicenceInfo.java
* @Package com.frame.tobaCase.entity
* @Description: 许可证信息实体对象
* @author: shizh
* @date 2017年2月13日 下午2:50:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.frame.core.entity.BizBaseEntity;
import com.frame.tobaCase.utils.ExcelAnnotation;

public class LicenceInfo extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    @ExcelAnnotation(exportName = "负责人(经营者)姓名")
    private String perRespon; // 负责人姓名，如林镇雄

    @ExcelAnnotation(exportName = "许可证号")
    private String licNo; // 许可证号码

    private String retailCode; // 零售户编号

    @ExcelAnnotation(exportName = "工商营业执照编号")
    private String bizLicNo; // 工商营业执照编号

    @ExcelAnnotation(exportName = "发证机关")
    private String issueUnit; // 发证单位，如潮州市烟草专卖局

    @ExcelAnnotation(exportName = "企业(字号)名称")
    private String corpName; // 企业名称

    private String econNatu; // 经济性质，如集体企业、个人独资、有限公司、国有企业等

    @ExcelAnnotation(exportName = "身份证号")
    private String idCard; // 证件号码

    @ExcelAnnotation(exportName = "经营地址")
    private String premises; // 经营场所，即经营地址

    @ExcelAnnotation(exportName = "联系人电话")
    private String phone; // 联系电话

    @ExcelAnnotation(exportName = "经营范围")
    private String bizScope; // 经营范围，如卷烟、雪茄烟等

    @ExcelAnnotation(exportName = "零售业态")
    private String operaForms; // 经营业态，如食杂店、便利店等

    @ExcelAnnotation(exportName = "地区类别")
    private String siteAttr; // 地段属性，如城镇、乡村

    @ExcelAnnotation(exportName = "特殊群体类型")
    private String specType; // 特群类型，如非特殊群体、其他、下岗失业人员等

    private Integer licStatus; // 许可证状态是否有效，0表示无效，1表示有效，默认1

    private Integer retailStatus; // 零售户状态是否有效，0表示无效，1表示有效，默认1

    @ExcelAnnotation(exportName = "发证日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date certDate; // 发证日期

    @ExcelAnnotation(exportName = "许可证截止期限")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date effeDate; // 有效日期

    @ExcelAnnotation(exportName = "许可证截止期限")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date bizLicDate; // 营业执照有效期

    @ExcelAnnotation(exportName = "所属单位")
    private String audiDept; // 稽查部门

    public String getPerRespon() {
	return perRespon;
    }

    public void setPerRespon(String perRespon) {
	this.perRespon = perRespon;
    }

    public String getLicNo() {
	return licNo;
    }

    public void setLicNo(String licNo) {
	this.licNo = licNo;
    }

    public String getRetailCode() {
	return retailCode;
    }

    public void setRetailCode(String retailCode) {
	this.retailCode = retailCode;
    }

    public String getBizLicNo() {
	return bizLicNo;
    }

    public void setBizLicNo(String bizLicNo) {
	this.bizLicNo = bizLicNo;
    }

    public String getIssueUnit() {
	return issueUnit;
    }

    public void setIssueUnit(String issueUnit) {
	this.issueUnit = issueUnit;
    }

    public String getCorpName() {
	return corpName;
    }

    public void setCorpName(String corpName) {
	this.corpName = corpName;
    }

    public String getEconNatu() {
	return econNatu;
    }

    public void setEconNatu(String econNatu) {
	this.econNatu = econNatu;
    }

    public String getIdCard() {
	return idCard;
    }

    public void setIdCard(String idCard) {
	this.idCard = idCard;
    }

    public String getPremises() {
	return premises;
    }

    public void setPremises(String premises) {
	this.premises = premises;
    }

    public String getPhone() {
	return phone;
    }

    public void setPhone(String phone) {
	this.phone = phone;
    }

    public String getBizScope() {
	return bizScope;
    }

    public void setBizScope(String bizScope) {
	this.bizScope = bizScope;
    }

    public String getOperaForms() {
	return operaForms;
    }

    public void setOperaForms(String operaForms) {
	this.operaForms = operaForms;
    }

    public String getSiteAttr() {
	return siteAttr;
    }

    public void setSiteAttr(String siteAttr) {
	this.siteAttr = siteAttr;
    }

    public String getSpecType() {
	return specType;
    }

    public void setSpecType(String specType) {
	this.specType = specType;
    }

    public Integer getLicStatus() {
	return licStatus;
    }

    public void setLicStatus(Integer licStatus) {
	this.licStatus = licStatus;
    }

    public Integer getRetailStatus() {
	return retailStatus;
    }

    public void setRetailStatus(Integer retailStatus) {
	this.retailStatus = retailStatus;
    }

    public Date getCertDate() {
	return certDate;
    }

    public void setCertDate(Date certDate) {
	this.certDate = certDate;
    }

    public Date getEffeDate() {
	return effeDate;
    }

    public void setEffeDate(Date effeDate) {
	this.effeDate = effeDate;
    }

    public Date getBizLicDate() {
	return bizLicDate;
    }

    public void setBizLicDate(Date bizLicDate) {
	this.bizLicDate = bizLicDate;
    }

    public String getAudiDept() {
	return audiDept;
    }

    public void setAudiDept(String audiDept) {
	this.audiDept = audiDept;
    }

    /**
     * 获取表头字段名称
     * @return
     */
    public Map<String, String> getHeader() {
	Map<String, String> map = new HashMap<String, String>();

	map.put("负责人", "perRespon");
	map.put("许可证号码", "licNo");
	map.put("零售户编号", "retailCode");
	map.put("工商营业执照编号", "bizLicNo");
	map.put("发证单位", "issueUnit");
	map.put("企业名称", "corpName");
	map.put("经济性质", "econNatu");
	map.put("证件号码", "idCard");
	map.put("经营场所", "premises");
	map.put("联系电话", "phone");
	map.put("经营范围", "bizScope");
	map.put("经营业态", "operaForms");
	map.put("地段属性", "siteAttr");
	map.put("特群类型", "specType");
	map.put("许可证状态是否有效", "licStatus");
	map.put("零售户状态是否有效", "retailStatus");
	map.put("发证日期", "certDate");
	map.put("有效日期", "effeDate");
	map.put("营业执照有效期", "bizLicDate");
	map.put("稽查部门", "audiDept");

	return map;
    }

    /**
     * 获取表头字段名称
     * @return
     */
    public Map<String, String> setHeader() {
	Map<String, String> map = new HashMap<String, String>();

	map.put("perRespon", "负责人");
	map.put("licNo", "许可证号码");
	map.put("retailCode", "零售户编号");
	map.put("bizLicNo", "工商营业执照编号");
	map.put("issueUnit", "发证单位");
	map.put("corpName", "企业名称");
	map.put("econNatu", "经济性质");
	map.put("idCard", "证件号码");
	map.put("premises", "经营场所");
	map.put("phone", "联系电话");
	map.put("bizScope", "经营范围");
	map.put("operaForms", "经营业态");
	map.put("siteAttr", "地段属性");
	map.put("specType", "特群类型");
	map.put("licStatus", "许可证状态是否有效");
	map.put("retailStatus", "零售户状态是否有效");
	map.put("certDate", "发证日期");
	map.put("effeDate", "有效日期");
	map.put("bizLicDate", "营业执照有效期");
	map.put("audiDept", "稽查部门");
	return map;
    }
}
