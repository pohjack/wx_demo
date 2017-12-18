/**
 * @Title: IBaseService.java
 * @Package com.frame.sys.dao.impl
 * @Description:
 * @author: lzl
 * @date 2016年7月13日 上午11:08:48
 * @version V1.0
 */
package com.frame.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.entity.IdEntity;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.entity.TreeNode;

public interface IBaseService<T extends IdEntity, ID extends Serializable> {

	/**
	 * @Description: 查找所有的数据
	 * @param @return
	 * @author: zk 
	 * @date 2016年7月13日 下午7:25:20 
	 * @throws
	 */
	List<T> findAll();

	/**
	 * @Description: 获取符合条件的业务实体记录总数
	 * @param 
	 * @return 
	 * @author: shizh 
	 * @date 2016年10月25日 上午11:33:04 
	 * @throws
	 */
	Integer findAllTotal(Object parameter);

	/**
	 * @Description: 获取业务实体记录总数 
	 * @param 
	 * @return 
	 * @author: lzl 
	 * @date 2016年7月20日  下午4:33:04 
	 * @throws
	 */
	Integer findAllTotal();

	/**
	 * 
	 * @Description: 根据条件查询所有数据 
	 * @param @param parameter 
	 * @return 
	 * @author: Shizh 
	 * @date 2016年10月25日 上午11:34:42 
	 * @throws
	 */
	List<T> findAll(Object parameter);

	
	DateTablesResult<Map<String, Object>> queryByPage(DateTablesResult<Map<String, Object>> datatable);

	/**
	 * 
	 * @Description: 通过id查询详情 
	 * @param @param id 
	 * @return 
	 * @author: Shizh 
	 * @date 2016年10月21日 上午10:30:31 
	 * @throws
	 */
	T findById(ID id);

	/**
	 * 
	 * @Description: 删除数据 
	 * @param @param ids id数组集合 
	 * @param @return 
	 * @author: Shizh 
	 * @date 2016年10月21日 下午3:41:53 
	 * @throws
	 */
	Integer remove(String[] ids);
	
	/**
	 * 
	 * @Description: 删除所有数据 
	 * @param @return 
	 * @author: Shizh 
	 * @date 2016年10月21日 下午3:41:53 
	 * @throws
	 */
	Integer removeAll();

	/**
	 * 
	 * @Description: 保存数据 
	 * @param @param obj 
	 * @param @return 
	 * @author: Shizh 
	 * @date 2016年10月21日 下午3:42:54 
	 * @throws
	 */
	Integer save(T obj);

	/**
	 * 
	 * @Description: 更新数据 
	 * @param @param obj 
	 * @param @return 
	 * @author: Shizh 
	 * @date 2016年10月21日 下午5:15:27 
	 * @throws
	 */
	Integer update(T obj);

	/**
	 * 
	 * @Description: 获取树结构数据 
	 * @param @return 
	 * @author: Shizh 
	 * @date 2016年10月25日 上午9:51:37 
	 * @throws
	 */
	List<TreeNode> findTreeNodes();

	List<JsTreeNode> findJsTreeNodes();
}
