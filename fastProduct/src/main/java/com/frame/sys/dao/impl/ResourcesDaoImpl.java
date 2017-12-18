/**
 * @Title: ResourceDaoImpl.java
 * @Package com.frame.sys.dao.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 下午6:55:17
 * @version V1.0
 */
package com.frame.sys.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IResourcesDao;
import com.frame.sys.entity.Resources;

@Repository("resourcesDao")
public class ResourcesDaoImpl extends MybaitsBaseDaoImpl<Resources,String>implements IResourcesDao{
	private String sqlNamespace = "com.frame.sys.entity.Resources.";
	
	@Override
	public int changeState(Map<String,Object> map){
		int updateNum = this.getSqlSession().update(sqlNamespace.concat("changeState"), map);
		return updateNum;	
	}

	@Override
	public Resources findByURI(String uri) {
		return this.getSqlSession().selectOne(getSqlName("findByURI"), uri);
			
	}
}
