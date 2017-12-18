/**    
* @Title: CaseTempHistoryDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 基础模板dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ICaseTempHistoryDao;
import com.frame.tobaCase.entity.CaseTempHistory;

@Repository("caseTempHistoryDao")
public class CaseTempHistoryDaoImpl extends MybaitsBaseDaoImpl<CaseTempHistory, String> implements ICaseTempHistoryDao {

    @Override
    public CaseTempHistory checkHistory(CaseTempHistory caseTempHistory) {
	return this.getSqlSession().selectOne(getSqlName("findByCidDid"), caseTempHistory);
    }

}
