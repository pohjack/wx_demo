/**    
* @Title: BaseTemplateSlaveDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 基础模板从表dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.IBaseTemplateSlaveDao;
import com.frame.tobaCase.entity.BaseTemplateSlave;

@Repository("baseTemplateSlaveDao")
public class BaseTemplateSlaveDaoImpl extends MybaitsBaseDaoImpl<BaseTemplateSlave, String>
	implements IBaseTemplateSlaveDao {

    @Override
    public List<BaseTemplateSlave> findByMasterId(String id) {
	return this.getSqlSession().selectList(getSqlName("findByMasterId"), id);
    }

    @Override
    public void removeTempData(String tid) {
	this.getSqlSession().delete(getSqlName("removeTempData"), tid);
    }

}
