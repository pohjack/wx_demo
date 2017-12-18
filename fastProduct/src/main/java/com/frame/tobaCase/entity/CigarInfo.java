/**    
* @Title: CigarInfo.java
* @Package com.frame.tobaCase.entity
* @Description: 卷烟库实体对象
* @author: shizh
* @date 2017年2月13日 下午2:01:30
* @version V1.0
*/
package com.frame.tobaCase.entity;

import com.frame.core.entity.BizBaseEntity;
import com.frame.tobaCase.utils.ExcelAnnotation;

public class CigarInfo extends BizBaseEntity {

    /**
    * @ serialVersionUID : 序列化时保持版本的兼容性
    */
    private static final long serialVersionUID = 1L;

    @ExcelAnnotation(exportName = "卷烟名称")
    private String name; // 卷烟名称

    @ExcelAnnotation(exportName = "卷烟零售价")
    private Double retailPrice; // 卷烟零售价

    @ExcelAnnotation(exportName = "条形码")
    private String barCode; // 条形码

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Double getRetailPrice() {
	return retailPrice;
    }

    public void setRetailPrice(Double retailPrice) {
	this.retailPrice = retailPrice;
    }

    public String getBarCode() {
	return barCode;
    }

    public void setBarCode(String barCode) {
	this.barCode = barCode;
    }

}
