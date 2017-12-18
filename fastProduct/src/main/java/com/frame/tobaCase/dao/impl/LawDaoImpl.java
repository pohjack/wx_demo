/**    
* @Title: LawDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 法律法规dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.ILawDao;
import com.frame.tobaCase.entity.Law;

@Repository("lawDao")
public class LawDaoImpl extends MybaitsBaseDaoImpl<Law, String> implements ILawDao {
    @Override
    public List<Law> findByLawNum(String lawNum) {
	return this.getSqlSession().selectList(getSqlName("findByLawNum"), lawNum);
    }
}
