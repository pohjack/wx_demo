/**
 * @Title: IMybaitsBaseDao.java
 * @Package com.frame.core.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 下午6:55:17
 * @version V1.0
 */
package com.frame.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.frame.core.entity.IdEntity;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.entity.TreeNode;

public interface IMybaitsBaseDao<T extends IdEntity,ID extends Serializable> {
    
    /**  
    * @Description: 查找所有的数据
    * @param @return
    * @author: lzl
    * @date 2016年7月13日 下午7:08:51
    * @throws
    */
    List<T> findAll();
    
    /**  
    * @Description: 获取业务实体记录总数
    * @param @return
    * @author: lzl
    * @date 2016年7月20日 下午4:33:04
    * @throws
    */
    Integer findAllTotal();
    
    /**  
     * @Description: 获取符合条件的业务实体记录总数
     * @param @return
     * @author: shizh
     * @date 2016年10月25日 上午11:33:04
     * @throws
     */
     Integer findAllTotal(Object parameter);
    
    /**  
    * @Description: 根据id获取业务实体对象
    * @param @param id
    * @param @return
    * @author: lzl
    * @date 2016年7月20日 下午4:21:09
    * @throws
    */
    T findById(ID id);
    
    /**
     * 
    * @Description: 根据条件查询所有数据
    * @param @param parameter
    * @param @return
    * @author: Shizh
    * @date 2016年10月25日 上午11:34:42
    * @throws
     */
    List<T> findAll(Object parameter);
    
    /**
     * 
    * @Description: 根据条件分页查询
    * @param @param parameter
    * @param @return
    * @author: Shizh
    * @date 2016年10月25日 上午11:51:15
    * @throws
     */
    List<Map<String,Object>> queryByPage(Object parameter);
    
    /**
     * 
    * @Description: 根据id删除数据
    * @param @param ids id数组集合
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午3:43:54
    * @throws
     */
    Integer delete(String[] ids);
    
    /**
     * 
     * @Description: 删除所有数据
     * @param @return
     * @author: Shizh
     * @date 2016年10月21日 下午3:43:54
     * @throws
     */
    Integer deleteAll();
    
    /**
     * 
    * @Description: 保存数据
    * @param @param obj 实体对象
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午3:45:43
    * @throws
     */
    Integer save(T obj);
    /**
     * 
    * @Description: 更新数据
    * @param @param obj 实体对象
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午3:45:43
    * @throws
     */
    Integer update(T obj);
    
    /**
     * 
    * @Description: 获取树结构数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月25日 上午9:55:39
    * @throws
     */
    List<TreeNode> getTreeNodes();

	List<JsTreeNode> getJsTreeNodes();
    
    
}
