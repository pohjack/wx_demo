/**    
* @Title: AreaDaoImpl.java
* @Package com.frame.sys.dao.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: yuyf
* @date 2017年3月8日 上午10:30:43
* @version V1.0
*/
package com.frame.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.sys.dao.IAreaDao;

import com.frame.sys.entity.Area;


@Repository("areaDao")
public class AreaDaoImpl extends MybaitsBaseDaoImpl<Area, String> implements IAreaDao {

}
