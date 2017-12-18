/**    
* @Title: RoleResourceImpl.java
* @Package com.frame.sys.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2016年11月2日 下午3:26:50
* @version V1.0
*/
package com.frame.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IRoleResourceDao;
import com.frame.sys.entity.RoleResource;
import com.frame.sys.service.IRoleResourceService;
@Service("roleResourceService")
public class RoleResourceServiceImpl extends BaseServiceImpl<RoleResource ,String> implements IRoleResourceService {
	
	@Resource
	private  IRoleResourceDao roleResourceDao;
	
	 @Override
	  protected IMybaitsBaseDao<RoleResource,String> getBaseDao(){
	        return roleResourceDao;
	    }
	
	@Override
	public List<RoleResource> findRoleResourceByRoleId(String roleId){		
		return roleResourceDao.findRoleResourceByRoleId(roleId);
			
	}


	public List<RoleResource> findByRoleResource(RoleResource roleResource){		
		return roleResourceDao.findByRoleResource(roleResource);
			
	}

	@Override
	public int deleteByRoleId(String[] roleIds){
		return roleResourceDao.deleteByRoleId(roleIds);
			
	}

	@Override
	public int deleteByUserOrRes(Object obj){
		return roleResourceDao.deleteByUserOrRes(obj);
			
	}



}
