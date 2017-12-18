/**    
* @Title: CaseCigarDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 涉案卷烟dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseCigarDao;
import com.frame.tobaCase.entity.CaseCigar;

@Repository("caseCigarDao")
public class CaseCigarDaoImpl extends MybaitsBaseDaoImpl<CaseCigar, String> implements ICaseCigarDao {

    @Override
    public List<CaseCigar> findByCaseId(String id) {
	return this.getSqlSession().selectList(getSqlName("findByCaseId"), id);
    }

    @Override
    public String findTypesById(String id) {
	return getSqlSession().selectOne(getSqlName("findTypesByid"), id);
    }

    @Override
    public Double countTotalVal(String id, String param) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("id", id);
	map.put("param", param);
	return getSqlSession().selectOne(getSqlName("countTotalVal"), map);
    }

    @Override
    public List<CaseCigar> findByAttr(String id, String param) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("id", id);
	map.put("param", param);
	return getSqlSession().selectList(getSqlName("findByAttr"), map);
    }

    @Override
    public String countType(String id) {
	return getSqlSession().selectOne(getSqlName("countType"), id);
    }

    @Override
    public int saveAll(List<CaseCigar> caseCigar) {
	return getSqlSession().insert(getSqlName("saveAll"), caseCigar);
    }

    @Override
    public int removeByCaseId(String caseId) {
	return getSqlSession().delete(getSqlName("removeByCaseId"), caseId);
    }

    @Override
    public List<Map<String, Object>> findCigarName(Map<String, Object> map) {
	return getSqlSession().selectList(getSqlName("findCigarName"), map);
    }

    @Override
    public List<Map<String, Object>> findInspectResult(Map<String, Object> map) {
	return getSqlSession().selectList(getSqlName("findInspectResult"), map);
    }

    @Override
    public List<Map<String, Object>> findInspectResult2(Map<String, Object> map) {
	return getSqlSession().selectList(getSqlName("findInspectResult2"), map);
    }

    @Override
    public Map<String, Object> findCompareByResult(Map<String, Object> map) {
	return getSqlSession().selectOne("findCompareByResult", map);
    }

}
