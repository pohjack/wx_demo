/**    
* @Title: CigarInfoDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 卷烟信息库dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICigarInfoDao;
import com.frame.tobaCase.entity.CigarInfo;

@Repository("cigarInfoDao")
public class CigarInfoDaoImpl extends MybaitsBaseDaoImpl<CigarInfo, String> implements ICigarInfoDao {

    @Override
    public List<CigarInfo> findByIds(String[] ids) {
	return this.getSqlSession().selectList(getSqlName("findByIds"), ids);
    }

    @Override
    public Integer findBybarCodeOutCount(String barCode) {
	return this.getSqlSession().selectOne(getSqlName("findBybarCodeOutCount"), barCode);
    }

    @Override
    public CigarInfo findBybarCodeOutObject(String barCode) {
	return this.getSqlSession().selectOne(getSqlName("findBybarCodeOutObject"), barCode);
    }

    @Override
    public List<String> findAllName() {
	return this.getSqlSession().selectList("findAllName");
    }

}
