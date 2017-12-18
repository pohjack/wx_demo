/**    
* @Title: AreaServiceImpl.java
* @Package com.frame.sys.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: yuyf
* @date 2017年3月8日 上午10:41:47
* @version V1.0
*/
package com.frame.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IAreaDao;
import com.frame.sys.entity.Area;
import com.frame.sys.service.IAreaService;

@Service("areaService")
public class AreaServiceImpl extends BaseServiceImpl<Area, String>implements IAreaService {
	  
    @Resource
    private IAreaDao areaDao;
    
	
	@Override
	protected IMybaitsBaseDao<Area, String> getBaseDao() {
		// TODO
		 return areaDao;
			
	}

}
