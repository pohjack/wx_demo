package com.frame.sys.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IOrganizationDao;
import com.frame.sys.entity.Organization;
import com.frame.sys.service.IOrganizationService;

@Service("organizationService")
public class OrganizationServiceImpl extends BaseServiceImpl<Organization, String> implements IOrganizationService{
	@Resource
	private IOrganizationDao organizationDao;

	@Override
	protected IMybaitsBaseDao<Organization, String> getBaseDao(){
		return organizationDao;
	}

	@Override
	public void changeState(Map<String, Object> map){
		organizationDao.changeState(map);
	}

	@Override
	public List<String> findOrgIdById(String id){
		return organizationDao.findOrgIdById(id);
	}
}
