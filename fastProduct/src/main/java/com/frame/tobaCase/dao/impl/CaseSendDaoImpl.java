package com.frame.tobaCase.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseSendDao;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseSend;

@Repository("caseSendDao")
public class CaseSendDaoImpl extends MybaitsBaseDaoImpl<CaseSend, String> implements ICaseSendDao {

    @Override
    public CaseSend findByCaseIdAndCode(String caseId, String code) {
	Map<String, String> map = new HashMap<String, String>();
	map.put("caseId", caseId);
	map.put("code", code);
	return getSqlSession().selectOne(getSqlName("findByCaseIdAndCode"), map);

    }

}
