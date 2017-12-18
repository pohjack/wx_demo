/**    
* @Title: CaseCigarArr.java
* @Package com.frame.tobaCase.entity
* @Description: 用于接受涉案卷烟数组对象
* @author: liy
* @date 2017年2月23日 下午12:07:25
* @version V1.0
*/
package com.frame.tobaCase.entity;

import java.util.List;

import com.frame.core.entity.BizBaseEntity;

public class CaseCigarModel extends BizBaseEntity {
    private static final long serialVersionUID = 1L;

    private List<CaseCigar> caseCigars;

    public List<CaseCigar> getCaseCigars() {
	return caseCigars;
    }

    public void setCaseCigars(List<CaseCigar> caseCigars) {
	this.caseCigars = caseCigars;
    }

    public CaseCigarModel() {
	super();
    }

}
