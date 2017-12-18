/**
 * @Title: IUserService.java
 * @Package com.frame.sys.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月11日 下午5:43:07
 * @version V1.0
 */
package com.frame.sys.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.UserRole;

public interface IUserRoleService extends IBaseService<UserRole, String>{
	/**
	 * 
	* @Description:通过用户id查找角色id
	* @param @param id
	* @param @return
	* @author: liy
	* @date 2016年10月13日 下午3:43:25
	* @throws
	 */
	List<UserRole> findRoleIdListByUserId(String id);

	/**
	 * 
	* @Description: 删除当前用户的记录
	* @param @param userId
	* @param @return
	* @author: liy
	* @date 2016年11月2日 下午4:53:18
	* @throws
	 */
	int deleteByUserId(String userId);

	/**
	* @Description: 传入多个用户id，查询出数量
	* @param @param userIds
	* @param @return
	* @author: lpy
	* @date 2016年11月14日 下午4:04:16
	* @throws
	 */
	String findCountByRoleId(String[] roleIds);
}
