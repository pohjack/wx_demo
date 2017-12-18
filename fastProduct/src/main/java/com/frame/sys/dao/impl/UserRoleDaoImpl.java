/**
 * @Title: UserRoleDaoImpl.java
 * @Package com.frame.sys.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 上午10:25:43
 * @version V1.0
 */
package com.frame.sys.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.commons.sqlUtils.Constant;
import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IUserRoleDao;
import com.frame.sys.entity.UserRole;

@Repository("userRoleDao")
public class UserRoleDaoImpl extends MybaitsBaseDaoImpl<UserRole,String>implements IUserRoleDao{
	/**
	 * 查询方法，使用map传递一个userid
	 */
	@Override
	public List<UserRole> findRoleIdListByUserId(String userId){
		return this.getSqlSession().selectList(getSqlName("findAllByUserId"), userId);
	}

/**
 * 删除方法，使用map中list删除条件
 */
	@Override
	public int deleteByUserId(String[] userids){
		Map<String, String[]> userRoles = new HashMap<String, String[]>();
		userRoles.put("userIds", userids);
		/*userRoles.put("roleIds", roleIds);*///另外一个条件
		return this.getSqlSession().delete(getSqlName(Constant.MAPPING_IDS_DELETE), userRoles);
			
	}

	@Override
	public String findCountByRoleId(String[] roleIds){
		return this.getSqlSession().selectOne(getSqlName("findCountByRoleId"),roleIds);
			
	}

	@Override
	public int saveByUserId(List<UserRole> userRoles){
		return this.getSqlSession().insert("saveByUserId", userRoles);
	}
}
