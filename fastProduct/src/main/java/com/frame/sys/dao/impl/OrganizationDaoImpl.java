package com.frame.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IOrganizationDao;
import com.frame.sys.entity.Organization;

@Repository("organizationDao")
public class OrganizationDaoImpl extends MybaitsBaseDaoImpl<Organization, String> implements IOrganizationDao{
	private String sqlNamespace = "com.frame.sys.entity.Organization.";

	@Override
	public int changeState(Map<String, Object> map){
		int updateNum = this.getSqlSession().update(sqlNamespace.concat("changeState"), map);
		return updateNum;
	}

	@Override
	public List<String> findOrgIdById(String id){
		return this.getSqlSession().selectList(sqlNamespace.concat("findOrgIdById"), id);
	}
}
