/**    
* @Title: UserRoleServiceImpl.java
* @Package com.frame.sys.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2016年10月13日 下午3:49:27
* @version V1.0
*/
package com.frame.sys.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IUserRoleDao;
import com.frame.sys.entity.UserRole;
import com.frame.sys.service.IUserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole, String> implements IUserRoleService{
	@Resource
	private IUserRoleDao userRoleDao;

	@Override
	protected IMybaitsBaseDao<UserRole, String> getBaseDao(){
		return userRoleDao;
	}

	@Override
	public List<UserRole> findRoleIdListByUserId(String id){
		return userRoleDao.findRoleIdListByUserId(id);
	}

	@Override
	public int deleteByUserId(String userId){
		if(userId != null || StringUtils.isNotEmpty(userId)){
			return userRoleDao.deleteByUserId(userId.split(","));
		}
		return 0;
	}

	@Override
	public String findCountByRoleId(String[] roleIds){
		return userRoleDao.findCountByRoleId(roleIds);
	}
}
