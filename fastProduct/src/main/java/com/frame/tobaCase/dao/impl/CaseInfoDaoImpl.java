/**    
* @Title: CaseInfoDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 案件信息dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.GatherModel;
import com.frame.tobaCase.entity.NonExcel;
import com.frame.tobaCase.entity.SearchDataVo;

@Repository("caseInfoDao")
public class CaseInfoDaoImpl extends MybaitsBaseDaoImpl<CaseInfo, String> implements ICaseInfoDao {

    public Map<String, Object> findByKey(String id) {
	return this.getSqlSession().selectOne(getSqlName("findByKey"), id);
    }

    @Override
    public String findCaseCount(Map<String, Object> endCase) {
	return this.getSqlSession().selectOne("findCaseCount", endCase);
    }

    @Override
    public int findMaxCaseNo() {
	return this.getSqlSession().selectOne(getSqlName("findMaxCaseNo"));
    }

    @Override
    public Map<String, Object> findCompareCase(Map<String, Object> map) {
	return this.getSqlSession().selectOne("findCompareCase", map);
    }

    @Override
    public int updateCause(CaseInfo caseInfo) {
	return this.getSqlSession().update(getSqlName("updateCause"), caseInfo);
    }

    @Override
    public int updateSource(CaseInfo caseInfo) {
	return this.getSqlSession().update(getSqlName("updateSource"), caseInfo);
    }

    @Override
    public int endCase(String caseId) {

	return this.getSqlSession().update(getSqlName("endCase"), caseId);
    }

    @Override
    public List<GatherModel> findBySearchVo(SearchDataVo sv) {
	return this.getSqlSession().selectList("findBySearchVo", sv);
    }

    @Override
    public int regiNoIsSame(String regiNo) {
	return this.getSqlSession().selectOne(getSqlName("regiNoIsSame"), regiNo);
    }

    @Override
    public List<NonExcel> findBySearch(SearchDataVo sv) {
	return this.getSqlSession().selectList("findBySearch", sv);

    }

    @Override
    public String findCountByCaseId(String caseInfoId) {
	// TODO
	return this.getSqlSession().selectOne(getSqlName("findCountByCaseId"), caseInfoId);

    }
}
