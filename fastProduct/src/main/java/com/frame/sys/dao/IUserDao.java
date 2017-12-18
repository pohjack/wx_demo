/**
 * @Title: IUserDao.java
 * @Package com.frame.sys.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月11日 下午5:44:08
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.User;

public interface IUserDao extends IMybaitsBaseDao<User,String>{
    
    /**  
    * @Description: 根据用户名查找系统用户
    * @param @param loginName
    * @param @return
    * @author: zk
    * @date 2016年7月11日 下午6:18:55
    * @throws
    */
    User findUserByLoginName(String loginName);
    
    /**
     * 
    * @Description: 更新用户登录失败次数
    * @param @param user
    * @param @return
    * @author: Shizh
    * @date 2016年11月1日 下午4:36:58
    * @throws
     */
    int updateUserLFTimes(User user);
    
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
    
    /**
     * 
    * @Description: 查询多条记录
    * @param @param ids
    * @param @return
    * @author: liy
    * @date 2017年3月3日 下午1:45:38
    * @throws
     */
    public List<User>  findByIds(String[] ids);
    
    public List<Map<String, Object>>  findAllMap(User user);
    
    /**
     * 
    * @Description: 根据机构id查找用户
    * @param @param orgId
    * @param @return
    * @author: liy
    * @date 2017年3月4日 下午2:09:16
    * @throws
     */
    public List<User>  findByOrgId(String orgId);
    
}
