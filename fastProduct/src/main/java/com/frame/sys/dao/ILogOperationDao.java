/**
 * @Title: ILogOperationDao.java
 * @Package com.frame.core.dao
 * @Description: 操作日志dao
 * @author: shizh
 * @date 2016年11月2日 下午19:02:00
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.LogOperation;

public interface ILogOperationDao extends IMybaitsBaseDao<LogOperation,String>{


	Map<String, Object> findObjectById(Map<String, Object> obj);
	/**
	 * 
	* @Description: 更新操作日志的旧值新值
	* @param @param logOperation
	* @param @return
	* @author: Shizh
	* @date 2016年11月3日 下午1:06:42
	* @throws
	 */
	Integer updateNewVal(LogOperation logOperation);
}
