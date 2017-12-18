/**    
* @Title: LogLoginDaoImpl.java
* @Package com.frame.core.dao.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: Chensy
* @date 2016年10月17日 下午3:59:03
* @version V1.0
*/
package com.frame.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.ILogLoginDao;
import com.frame.sys.entity.LogLogin;

@Repository("logLoginDao")
public class LogLoginDaoImpl extends MybaitsBaseDaoImpl<LogLogin, String> implements ILogLoginDao {


}
