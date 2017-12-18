/**
 * @Title: IResourcesDao.java
 * @Package com.frame.sys.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 下午6:54:59
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.Resources;

public interface IResourcesDao extends IMybaitsBaseDao<Resources,String>{
	/**
	* @Description: 禁用菜单
	* @param @param id
	* @author: lpy
	* @date 2016年10月26日 下午4:58:14
	* @throws
	 */
	int changeState(Map<String, Object> map);
	
	/**
	 * 
	* @Description: 根据uri查找资源信息
	* @param @param uri
	* @param @return
	* @author ShiZH
	* @date 2016年11月16日 上午11:35:32
	* @throws
	 */
	Resources findByURI(String uri);
}
