/**    
* @Title: ICaseInvoPersDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 案件信息dao实现类
* @author: linpy
* @date 2017年2月20日 下午2:54:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseInvoPersDao;
import com.frame.tobaCase.entity.CaseInvoPers;

@Repository("caseInvoPersDao")
public class CaseInvoPersDaoImpl extends MybaitsBaseDaoImpl<CaseInvoPers, String> implements ICaseInvoPersDao {

    @Override
    public Map<String, Object> findByCaseId(String id) {
	return this.getSqlSession().selectOne(getSqlName("findByCaseId"), id);
    }

    @Override
    public CaseInvoPers findByLicNo(String licNo) {
	return this.getSqlSession().selectOne(getSqlName("findByLicNo"), licNo);
    }

    @Override
    public int findCountByCaseId(String caseInfoId) {
	return this.getSqlSession().selectOne(getSqlName("findCountByCaseId"), caseInfoId);
    }

}
