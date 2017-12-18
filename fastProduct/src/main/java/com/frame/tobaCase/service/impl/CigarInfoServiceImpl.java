/**    
* @Title: CigarInfoServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 卷烟信息库service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICigarInfoDao;
import com.frame.tobaCase.entity.CigarInfo;
import com.frame.tobaCase.service.ICigarInfoService;

@Service("cigarInfoService")
public class CigarInfoServiceImpl extends BaseServiceImpl<CigarInfo, String> implements ICigarInfoService {

    @Resource
    private ICigarInfoDao cigarInfoDao;

    @Override
    protected IMybaitsBaseDao<CigarInfo, String> getBaseDao() {
	return cigarInfoDao;
    }

    @Override
    public List<CigarInfo> findByIds(String[] ids) {
	return cigarInfoDao.findByIds(ids);
    }

    @Override
    public Integer findBybarCodeOutCount(String barCode) {
	return cigarInfoDao.findBybarCodeOutCount(barCode);
    }

    @Override
    public CigarInfo findBybarCodeOutObject(String barCode) {
	return cigarInfoDao.findBybarCodeOutObject(barCode);
    }

    @Override
    public List<String> findAllName() {
	return cigarInfoDao.findAllName();
    }
}
