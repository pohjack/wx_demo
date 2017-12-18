/**
 * @Title: UserServiceImpl.java
 * @Package com.frame.sys.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月11日 下午5:43:30
 * @version V1.0
 */
package com.frame.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.MD5;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IUserDao;
import com.frame.sys.dao.IUserRoleDao;
import com.frame.sys.entity.User;
import com.frame.sys.entity.UserRole;
import com.frame.sys.service.IUserService;
import com.frame.tobaCase.utils.RandomUtil;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, String> implements IUserService{
	@Resource
	private IUserDao userDao;

	@Resource
	private IUserRoleDao userRoleDao;

	@Override
	protected IMybaitsBaseDao<User, String> getBaseDao(){
		return userDao;
	}

	@Override
	public User findUserByLoginName(String loginName){
		return userDao.findUserByLoginName(loginName);
	}

	@Override
	public int updateUserLFTimes(User user){
		return userDao.updateUserLFTimes(user);
	}

	@Override
	public List<User> findByOrgId(String orgId){
		return userDao.findByOrgId(orgId);
	}

	@Override
	public int unlock(String id){
		return userDao.unlock(id);
	}

	@Override
	public int deleteUserAndUserRole(String[] id){
		int i = userDao.delete(id);
		i = userRoleDao.deleteByUserId(id);
		return i;
	}

	@Override
	public int saveOrUpdateUserAndUserRole(User user, String roleids){
		Integer result = 0;
		String userid = "";
		if(StringUtils.isEmpty(user.getId())){ // 保存用户基本信息
			userid = UUIDUtil.get32UUID();
			user.setId(userid);
			String initPassword = SysConstant.INITPASSWORD; // 初始化密碼
			user.setPassword(MD5.md5(initPassword));
			result = userDao.save(user);
		}else{
			userid = user.getId();
			result = userDao.update(user); // 修改
			result = userRoleDao.deleteByUserId(userid.split(",")); // 删除用户角色表中包含的所有用户id数组
		}
		if(roleids != null && StringUtils.isNotEmpty(roleids)){
			String[] roleid = roleids.split(",");
			List<UserRole> userRoles = new ArrayList<UserRole>();
			for(int i = 0; i < roleid.length; i++){
				userRoles.add(new UserRole(UUIDUtil.get32UUID(), userid, roleid[i]));//调用自定义构造器直接赋值
			}
			userRoleDao.saveByUserId(userRoles);
		}
		return result;
	}

	@Override
	public int saveByUserId(List<UserRole> userRoles){
		return userRoleDao.saveByUserId(userRoles);
	}
	
	@Override
	public String findUserCountByLoginName(String loginName) {
		return userDao.findUserCountByLoginName(loginName);
	}

	@Override
	public List<User> findNotAdminAndRoot(){
		return userDao.findNotAdminAndRoot();
	}

	@Override
	public List<User> findByIds(String[] ids){
				return userDao.findByIds(ids);
			
	}

	@Override
	public List<Map<String, Object>> findAllMap(User user){
				return userDao.findAllMap(user);
			
	}

	@Override
	public int importUser(List<User> users){
		for (User user:users){
			if(StringUtils.isNotEmpty(user.getLoginName())){
				User  us = userDao.findUserByLoginName(user.getLoginName());
				if(us!=null){
					userDao.update(user);
				}else{
					userDao.save(user);
				}
			}else{  //导入用户名不存在时 
				user.setLoginName(RandomUtil.getRandomUserName());
				userDao.save(user);
			}
		}
		return 0;
	}
}
