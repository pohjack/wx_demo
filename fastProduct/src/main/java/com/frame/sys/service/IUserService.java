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
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.User;
import com.frame.sys.entity.UserRole;

public interface IUserService extends IBaseService<User,String>{
    
    User findUserByLoginName(String loginName);
    
    /**
     * 
    * @Description: 更新用户登录失败次数
    * @param @param user
    * @param @return
    * @author: Shizh
    * @date 2016年11月1日 下午4:35:29
    * @throws
     */
    int updateUserLFTimes(User user);
    
    List<User> findByOrgId(String orgId);
    
    /**
     * 
    * @Description: 解锁
    * @param @param id
    * @author: liy
    * @date 2016年11月10日 下午6:50:36
    * @throws
     */
    int  unlock(String id);
    
    /**
     * 
    * @Description: 删除用户以及用户角色信息
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月17日 下午5:24:35
    * @throws
     */
    int deleteUserAndUserRole(String[] id);
    
    /**
     * 
    * @Description:保存或修改用户后执行删除用户角色表
    * @param @param user roleids
    * @param @return
    * @author: liy
    * @date 2016年11月17日 下午6:15:01
    * @throws
     */
    int saveOrUpdateUserAndUserRole(User user,String roleids);/**
     * 
    * @Description: 保存当前用户的记录
    * @param @param userRoles 用户权限数组
    * @param @return
    * @author: Chensy
    * @date 2016年11月21日 下午1:00:37
    * @throws
     */
    int saveByUserId(List<UserRole> userRoles);

    /**
     * 
    * @Description: 查询名字是否重复，查询数量
    * @param @param loginName
    * @param @return
    * @author: Chensy
    * @date 2016年11月22日 下午12:07:05
    * @throws
     */
    String findUserCountByLoginName(String loginName);
    
    /**
     * @Description: 查询非root和admin的用户
     * @param @return
     * @author: lpy
     * @date 2017年2月22日 下午8:02:55
     * @throws
      */
     public List<User> findNotAdminAndRoot();
     
     public List<User>  findByIds(String[] ids);
     
     public List<Map<String, Object>>  findAllMap(User user);
     
     /**
      * 
     * @Description: 保存导入的数据
     * @param @param users
     * @param @return
     * @author shizh
     * @date 2017年3月4日 下午3:45:25
     * @throws
      */
     int importUser(List<User> users);
     
     
}
