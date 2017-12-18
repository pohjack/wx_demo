/**    
* @Title: RoleResourceImpl.java
* @Package com.frame.sys.dao.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2016年11月2日 下午3:15:31
* @version V1.0
*/
package com.frame.sys.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IRoleResourceDao;
import com.frame.sys.entity.RoleResource;
@Repository("roleResourceDao")
public class RoleResourceDaoImpl extends MybaitsBaseDaoImpl<RoleResource,String> implements IRoleResourceDao {


	@Override
	public List<RoleResource> findRoleResourceByRoleId(String roleId){
		return this.getSqlSession().selectList(getSqlName("findRoleResourceByRoleId"),roleId);
			
	}


	@Override
	public List<RoleResource> findByRoleResource(RoleResource roleResource){
		return this.getSqlSession().selectList(getSqlName("findByRoleResource"),roleResource);			
	}


	@Override
	public int deleteByRoleId(String[] roleIds){
		return this.getSqlSession().delete(getSqlName("removeByIds"),roleIds);
			
	}


	@Override
	public int deleteByUserOrRes(Object obj){
		return this.getSqlSession().delete(getSqlName("deleteByUserAndRes"),obj);
	}


	@Override
	public int deleteByResId(String[] resIds){
		return this.getSqlSession().delete(getSqlName("removeByResIds"),resIds);
	}

}
