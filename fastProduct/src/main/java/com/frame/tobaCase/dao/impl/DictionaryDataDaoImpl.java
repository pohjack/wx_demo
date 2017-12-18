/**    
* @Title: DictionaryDataDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 数据字典dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.IDictionaryDataDao;
import com.frame.tobaCase.entity.DictionaryData;

@Repository("dictionaryDataDao")
public class DictionaryDataDaoImpl extends MybaitsBaseDaoImpl<DictionaryData, String> implements IDictionaryDataDao {

    @Override
    public DictionaryData findByCode(String code) {
	return this.getSqlSession().selectOne(getSqlName("findByCode"), code);
    }

}
