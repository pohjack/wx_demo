/**    
* @Title: DocsTemplateSlaveDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 文书模板从表dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.IDocsTemplateSlaveDao;
import com.frame.tobaCase.entity.DocsTemplateSlave;

@Repository("docsTemplateSlaveDao")
public class DocsTemplateSlaveDaoImpl extends MybaitsBaseDaoImpl<DocsTemplateSlave, String>
	implements IDocsTemplateSlaveDao {

    @Override
    public List<DocsTemplateSlave> findByMasterId(Map<String, Object> map) {
	return this.getSqlSession().selectList(getSqlName("findByMasterId"), map);
    }

    @Override
    public int getTopSort(String masterId) {
	return this.getSqlSession().selectOne(getSqlName("getTopSort"), masterId);
    }

    @Override
    public int updateSort(DocsTemplateSlave docsTemplateSlave) {
	return this.getSqlSession().update(getSqlName("updateSort"), docsTemplateSlave);
    }

}
