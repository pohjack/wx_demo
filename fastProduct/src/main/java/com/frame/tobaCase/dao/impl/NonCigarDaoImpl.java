package com.frame.tobaCase.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.INonCigarDao;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.NonCigar;

@Repository("nonCigarDao")
public class NonCigarDaoImpl extends MybaitsBaseDaoImpl<NonCigar, String> implements INonCigarDao {

    @Override
    public List<NonCigar> findByCaseId(String id) {
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
    public int saveAll(List<NonCigar> nonCigar) {
	// TODO
	return getSqlSession().insert(getSqlName("saveAll"), nonCigar);
    }

    @Override
    public List<NonCigar> findByCaseIdAndCigarId(String caseId, String cigarId) {
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
