/**    
* @Title: LicenceInfoServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 许可证信息service实现类
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
import com.frame.tobaCase.dao.ILicenceInfoDao;
import com.frame.tobaCase.entity.LicenceInfo;
import com.frame.tobaCase.service.ILicenceInfoService;

@Service("licenceInfoService")
public class LicenceInfoServiceImpl extends BaseServiceImpl<LicenceInfo, String> implements ILicenceInfoService {

    @Resource
    private ILicenceInfoDao licenceInfoDao;

    @Override
    protected IMybaitsBaseDao<LicenceInfo, String> getBaseDao() {
	return licenceInfoDao;
    }

    @Override
    public Integer findBylicNoOutCount(String licNo) {
	return licenceInfoDao.findBylicNoOutCount(licNo);
    }

    @Override
    public LicenceInfo findBylicNoOutObject(String licNo) {
	return licenceInfoDao.findBylicNoOutObject(licNo);
    }

    @Override
    public List<LicenceInfo> findByIds(String[] ids) {
	return licenceInfoDao.findByIds(ids);
    }

    @Override
    public List<String> findPerRespon(String perRespon) {
	return licenceInfoDao.findPerRespon(perRespon);
    }

    @Override
    public int importLic(List<LicenceInfo> licenceInfos) {
	int i = 0;
	for (LicenceInfo licenceInfo : licenceInfos) {
	    licenceInfoDao.importLic(licenceInfo);
	    i++;
	}
	return i;
    }
}
