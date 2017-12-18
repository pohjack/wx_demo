package com.frame.sys.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.ILogOperationDao;
import com.frame.sys.entity.LogOperation;

@Repository("logOperationDao")
public class LogOperationDaoImpl extends MybaitsBaseDaoImpl<LogOperation, String>implements ILogOperationDao {

	@Override
	public Map<String, Object> findObjectById(Map<String, Object> obj){
		return this.getSqlSession().selectOne(getSqlName("findObjectById"),obj);
	}

	@Override
	public Integer updateNewVal(LogOperation logOperation) {
		return this.getSqlSession().update(getSqlName("updateNewVal"),logOperation);
			
	}

}
