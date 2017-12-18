/**
 * @Title: IUserRoleDao.java
 * @Package com.frame.sys.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 上午10:24:12
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.List;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.UserRole;

public interface IUserRoleDao extends IMybaitsBaseDao<UserRole,String>{
	 /**
     * 
    * @Description:通过用户id查找角色id
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年10月13日 下午3:43:25
    * @throws
     */
	List<UserRole> findRoleIdListByUserId(String userId);
    
    /**
     * 
    * @Description: 删除当前用户的记录
    * @param @param userId
    * @param @return
    * @author: liy
    * @date 2016年11月2日 下午4:53:18
    * @throws
     */
    int deleteByUserId(String[] strings);
    
    /**
    * @Description: 传入多个用户id，查询出数量
    * @param @param userId
    * @param @return
    * @author: lpy
    * @date 2016年11月14日 下午4:00:46
    * @throws
     */
    String findCountByRoleId(String[] roleIds);
    /**
     * 
    * @Description: 保存当前用户的记录
    * @param @param userRoles 用户权限数组
    * @param @return
    * @author: Chensy
    * @date 2016年11月21日 下午1:00:37
    * @throws
     */
    int saveByUserId(List<UserRole> userRoles);
}
