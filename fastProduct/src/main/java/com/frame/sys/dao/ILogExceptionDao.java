/**
 * @Title: LogExceptionDao.java
 * @Package com.frame.core.dao
 * @Description: 异常日志dao
 * @author: shizh
 * @date 2016年11月2日 下午19:02:00
 * @version V1.0
 */
package com.frame.sys.dao;

import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.sys.entity.LogException;

public interface ILogExceptionDao extends IMybaitsBaseDao<LogException,String>{

	/**  
	* @Description: 查询日志-模糊条件-日期条件
	* @param @param obj
	* @param @return
	* @author: chensy
	* @date 2016年10月13日 下午19:02:00
	* @throws
	*/
	LogException findAll(Map<String, Object> obj);
}
