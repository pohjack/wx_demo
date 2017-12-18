/**    
* @Title: IRoleResourceService.java
* @Package com.frame.sys.service
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2016年11月2日 下午3:23:16
* @version V1.0
*/
package com.frame.sys.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.RoleResource;

public interface IRoleResourceService extends  IBaseService<RoleResource,String>{
	 /**
     * 
    * @Description: 通过角色id 查找角色资源对象
    * @param @param userId
    * @param @return
    * @author: liy
    * @date 2016年10月14日 上午9:24:13
    * @throws
     */
	List<RoleResource> findRoleResourceByRoleId(String roleId);
    
    
	/**
	 * 
	* @Description:根据角色id和资源id查找角色资源菜单
	* @param @param roleResource
	* @param @return
	* @author: liy
	* @date 2016年11月2日 下午3:12:09
	* @throws
	 */
    List<RoleResource>  findByRoleResource(RoleResource roleResource);
    
    /**
     * 
    * @Description: 根据角色id删除
    * @param @param userId
    * @param @return
    * @author: liy
    * @date 2016年11月2日 下午4:53:18
    * @throws
     */
    int deleteByRoleId(String[] roleIds);
    
    /**
     * @Description: 根据角色id和资源id删除记录
     * @param @param userRole
     * @param @return
     * @author: lpy
     * @date 2016年11月10日 下午5:33:29
     * @throws
      */
     int deleteByUserOrRes(Object obj);
}
