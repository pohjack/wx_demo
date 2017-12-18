/**    
* @Title: BaseTemplateDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 基础模板dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.IBaseTemplateDao;
import com.frame.tobaCase.entity.BaseTemplate;

@Repository("baseTemplateDao")
public class BaseTemplateDaoImpl extends MybaitsBaseDaoImpl<BaseTemplate, String> implements IBaseTemplateDao {

    @Override
    public List<BaseTemplate> findBaseByDocId(String modelNo) {
	return this.getSqlSession().selectList(getSqlName("findBaseByDocId"), modelNo);
    }

    @Override
    public List<Map<String, Object>> findBaseInfo(Map<String, Object> map) {
	return this.getSqlSession().selectList(getSqlName("findBaseInfo"), map);
    }

}
