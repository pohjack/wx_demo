/**    
* @Title: DocsTemplateServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 文书模板service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.IDocsTemplateDao;
import com.frame.tobaCase.entity.DocsTemplate;
import com.frame.tobaCase.service.IDocsTemplateService;

@Service("docsTemplateService")
public class DocsTemplateServiceImpl extends BaseServiceImpl<DocsTemplate, String> implements IDocsTemplateService {

    @Resource
    private IDocsTemplateDao docsTemplateDao;

    @Override
    protected IMybaitsBaseDao<DocsTemplate, String> getBaseDao() {
	return docsTemplateDao;
    }

}
