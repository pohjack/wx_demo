/**    
* @Title: CaseEvidenceDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 证据信息dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseEvidenceDao;
import com.frame.tobaCase.entity.CaseEvidence;

@Repository("caseEvidenceDao")
public class CaseEvidenceDaoImpl extends MybaitsBaseDaoImpl<CaseEvidence, String> implements ICaseEvidenceDao {
    @Override
    @MapKey("evid_type")
    public List<Map<String, Integer>> countByCaseId(String id) {
	return this.getSqlSession().selectList(getSqlName("countByCaseId"), id);
    }

    @Override
    @MapKey("evid_type")
    public List<CaseEvidence> findByCaseInfoId(String caseId, Integer index) {
	Map<String, Object> condition = new HashMap<String, Object>();
	condition.put("caseId", caseId);
	condition.put("index", index);
	return this.getSqlSession().selectList(getSqlName("findByCaseInfoId"), condition);
    }

    @Override
    public List<Map<String, Object>> countEvidence(String id) {
	return this.getSqlSession().selectList(getSqlName("countEvidence"), id);
    }

    @Override
    public int deleteByArgs(String caseInfoId) {
	return this.getSqlSession().delete(getSqlName("deleteByArgs"), caseInfoId);
    }
}
