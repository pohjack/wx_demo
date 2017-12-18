/**
 * @Title: RoleDaoImpl.java
 * @Package com.frame.sys.dao.impl
 * @Description: 角色数据访问层实现类
 * @author: lzl
 * @date 2016年7月13日 上午11:08:48
 * @version V1.0
 */
package com.frame.sys.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IRoleDao;
import com.frame.sys.entity.Role;

@Repository("roleDao")
public class RoleDaoImpl extends MybaitsBaseDaoImpl<Role,String>implements IRoleDao{
	private String sqlNamespace = "com.frame.sys.entity.Role.";
    @Override
    public List<Role> findRoleIdByUserId(String userId){
        return selectList("findRoleIdByUserId",userId);
    }
    
    @Override
    public List<Map<String,Object>> findResourceByRoleId(String id){
        return selectListMap("findResourceByRoleId",id);
    }

	@Override
	public int changeState(Map<String, Object> map){
		int updateNum = this.getSqlSession().update(sqlNamespace.concat("changeState"), map);
		return updateNum;
	}

	@Override
	public int isSameName(String name){
		return this.getSqlSession().selectOne("isSameName",name);
	}
}
