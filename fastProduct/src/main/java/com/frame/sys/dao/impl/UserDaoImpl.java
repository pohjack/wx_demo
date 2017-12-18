/**
 * @Title: UserDaoImpl.java
 * @Package com.frame.sys.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月11日 下午5:44:23
 * @version V1.0
 */
package com.frame.sys.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IUserDao;
import com.frame.sys.entity.User;

@Repository("userDao")
public class UserDaoImpl extends MybaitsBaseDaoImpl<User,String>implements IUserDao{
    
    @Override
    public User findUserByLoginName(String loginName){
        return this.getSqlSession().selectOne(getSqlName("findUserByLoginName"),loginName);
    }

	@Override
	public int updateUserLFTimes(User user) {
		return this.getSqlSession().update(getSqlName("updateLoginTimes"), user);
			
	}

	@Override
	public int unlock(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("updator", getCurrentUser());
		map.put("updated", new Date());
		return  this.getSqlSession().update(getSqlName("unlock"), map);
	}

	@Override
	public String findUserCountByLoginName(String loginName) {
		return this.getSqlSession().selectOne(getSqlName("findUserCountByLoginName"), loginName);
	}

	@Override
	public List<User> findNotAdminAndRoot(){
		return this.getSqlSession().selectList(getSqlName("findNotAdminAndRoot"));
	}

	@Override
	public List<User> findByIds(String[] ids){
		 return this.getSqlSession().selectList(getSqlName("findByIds"),ids);
	}

	@Override
	public List<Map<String, Object>> findAllMap(User user){
		 return this.getSqlSession().selectList(getSqlName("findAllMap"),user);
			
	}

	@Override
	public List<User> findByOrgId(String orgId){
		 return this.getSqlSession().selectList(getSqlName("findByOrgId"),orgId);
	}
}
