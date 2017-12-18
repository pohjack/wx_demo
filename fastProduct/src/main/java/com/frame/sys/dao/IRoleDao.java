/**
 * @Title: IRoleDao.java
 * @Package com.frame.sys.dao
 * @Description: 角色dao层
 * @author: lzl
 * @date 2016年7月13日 上午11:08:17
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.Role;

public interface IRoleDao extends IMybaitsBaseDao<Role,String>{
    
    /**  
     * @Description: 根据用户id查找用户所属权限
     * @param @param userId
     * @param @return
     * @author: zk
     * @date 2016年7月13日 下午2:08:16
     * @throws
     */
    List<Role> findRoleIdByUserId(String userId);
    
    /**  
     * @Description: 根据权限id查找权限所属的资源
     * @param @param id
     * @param @return
     * @author: zk
     * @date 2016年7月13日 下午2:10:28
     * @throws
     */
    List<Map<String,Object>> findResourceByRoleId(String id);
    
    /**
    * @Description: 禁用启用部门
    * @param @param roleId
    * @param @return
    * @author: lpy
    * @date 2016年11月11日 下午2:35:52
    * @throws
     */
    int changeState(Map<String, Object> map);
    
    /**
    * @Description: 查询是否重名的存在
    * @param @param name
    * @param @return
    * @author: lpy
    * @date 2016年11月16日 下午3:04:21
    * @throws
     */
    int isSameName(String name);
}
