package com.frame.sys.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.ILogExceptionDao;
import com.frame.sys.entity.LogException;

@Repository("logExceptionDao")
public class LogExceptionDaoImpl extends MybaitsBaseDaoImpl<LogException, String>implements ILogExceptionDao {

	@Override
	public LogException findAll(Map<String, Object> obj){
		// TODO Auto-generated method stub
				return null;
			
	}

}
