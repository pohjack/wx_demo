package com.frame.tobaCase.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseGangYinDao;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;

@Repository("caseGangYinDao")
public class CaseGangYinDaoImpl extends MybaitsBaseDaoImpl<CaseGangYin, String> implements ICaseGangYinDao {

    @Override
    public List<CaseGangYin> findByCaseId(String id) {
	// TODO
	return this.getSqlSession().selectList(getSqlName("findByCaseId"), id);

    }

    @Override
    public Double countTotalVal(String id, String param) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("id", id);
	map.put("param", param);
	return getSqlSession().selectOne(getSqlName("countTotalVal"), map);

    }

    @Override
    public int saveAll(List<CaseGangYin> caseGangYin) {
	// TODO
	return getSqlSession().insert(getSqlName("saveAll"), caseGangYin);
    }

    @Override
    public List<CaseGangYin> findByCaseIdAndCigarId(String caseId, String cigarId) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("caseId", caseId);
	map.put("cigarId", cigarId);
	return getSqlSession().selectList(getSqlName("findByCaseIdAndCigarId"), map);

    }

    @Override
    public int findEachCigarTotal(String caseId, String cigarId) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("caseId", caseId);
	map.put("cigarId", cigarId);
	return getSqlSession().selectOne(getSqlName("findEachCigarTotal"), map);
    }

    @Override
    public List<String> findCigarIds(String caseId) {
	return this.getSqlSession().selectList(getSqlName("findCigarIds"), caseId);
    }

    @Override
    public int removeByCaseIdCigarId(String caseId, String cigarId) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("caseId", caseId);
	map.put("cigarId", cigarId);
	return getSqlSession().delete(getSqlName("removeByCaseIdCigarId"), map);

    }

}
