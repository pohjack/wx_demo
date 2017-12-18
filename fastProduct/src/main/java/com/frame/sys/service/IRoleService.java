/**
 * @Title: IRoleService.java
 * @Package com.frame.sys.service
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 上午11:14:40
 * @version V1.0
 */
package com.frame.sys.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Resources;
import com.frame.sys.entity.Role;

public interface IRoleService extends IBaseService<Role,String>{
    
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
    * @Description: 根据当前用户获取用户资源菜单，去掉重复菜单以及按扭操作
    * @param @param user
    * @param @return
    * @author: lzl
    * @date 2016年7月18日 下午3:51:47
    * @throws
    */
    Map<String,String> getUserRoleResource(List<Role> roleList);
    List<Resources> getUserResource(List<Role> roleList);
    
    /**
    * @Description: 禁用启用角色
    * @param @param map
    * @param @return
    * @author: lpy
    * @date 2016年11月11日 下午2:40:25
    * @throws
     */
    int changeState(Map<String, Object> map);
    
    /**
    * @Description: 查询是否重名
    * @param @param name
    * @param @return
    * @author: lpy
    * @date 2016年11月16日 下午3:06:05
    * @throws
     */
    int isSameName(String name);
    
    /**
    * @Description: 删除，连带删除资源
    * @param @param roleIds
    * @author: lpy
    * @date 2016年11月17日 下午5:17:48
    * @throws
     */
    void removeRole(String[] roleIds);
    
    /**
    * @Description: 保存角色，如果是新增则连带保存资源
    * @param @param role
    * @param @param resIds
    * @author: lpy
    * @date 2016年11月17日 下午5:27:06
    * @throws
     */
    void mySave(Role role, String[] resIds);
}
