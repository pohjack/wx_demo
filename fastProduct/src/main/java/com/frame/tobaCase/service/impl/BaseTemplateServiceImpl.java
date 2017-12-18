/**    
* @Title: BaseTemplateServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 基础模板service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.IBaseTemplateDao;
import com.frame.tobaCase.entity.BaseTemplate;
import com.frame.tobaCase.service.IBaseTemplateService;

@Service("baseTemplateService")
public class BaseTemplateServiceImpl extends BaseServiceImpl<BaseTemplate, String> implements IBaseTemplateService {

    @Resource
    private IBaseTemplateDao baseTemplateDao;

    @Override
    protected IMybaitsBaseDao<BaseTemplate, String> getBaseDao() {
	return baseTemplateDao;
    }

    @Override
    public List<BaseTemplate> findBaseByDocId(String modelNo) {
	return baseTemplateDao.findBaseByDocId(modelNo);
    }

    @Override
    public List<Map<String, Object>> findBaseInfo(String modelNo, int tag) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("modelNo", modelNo);
	map.put("tag", tag);
	return baseTemplateDao.findBaseInfo(map);
    }

}
