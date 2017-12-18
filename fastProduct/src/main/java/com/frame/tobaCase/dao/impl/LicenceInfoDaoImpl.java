/**    
* @Title: LicenceInfoDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 许可证信息dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ILicenceInfoDao;
import com.frame.tobaCase.entity.LicenceInfo;

@Repository("licenceInfoDao")
public class LicenceInfoDaoImpl extends MybaitsBaseDaoImpl<LicenceInfo, String> implements ILicenceInfoDao {

    @Override
    public Integer findBylicNoOutCount(String licNo) {
	return this.getSqlSession().selectOne(getSqlName("findBylicNoOutCount"), licNo);
    }

    @Override
    public LicenceInfo findBylicNoOutObject(String licNo) {
	return this.getSqlSession().selectOne(getSqlName("findBylicNoOutObject"), licNo);
    }

    @Override
    public List<LicenceInfo> findByIds(String[] ids) {
	return this.getSqlSession().selectList(getSqlName("findByIds"), ids);
    }

    @Override
    public List<String> findPerRespon(String perRespon) {
	return this.getSqlSession().selectList(getSqlName("findPerRespon"), perRespon);
    }

    @Override
    public int importLic(LicenceInfo licenceInfo) {
	licenceInfo.setId(UUIDUtil.get32UUID());
	return this.getSqlSession().insert(getSqlName("saveImport"), licenceInfo);
    }
}
