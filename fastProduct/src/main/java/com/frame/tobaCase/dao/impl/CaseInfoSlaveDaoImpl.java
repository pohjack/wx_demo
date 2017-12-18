/**    
* @Title: ICaseInfoSlaveDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 案件信息从类dao实现类
* @author: linpy
* @date 2017年2月20日 下午2:54:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseInfoSlave;

@Repository("caseInfoSlaveDao")
public class CaseInfoSlaveDaoImpl extends MybaitsBaseDaoImpl<CaseInfoSlave, String> implements ICaseInfoSlaveDao {

    @Override
    public Map<String, Object> findByCaseId(String id) {
	return this.getSqlSession().selectOne(getSqlName("findByCaseId"), id);
    }

    @Override
    public CaseInfoSlave findByMasterId(String caseId) {
	return this.getSqlSession().selectOne(getSqlName("findByMasterId"), caseId);
    }

}
