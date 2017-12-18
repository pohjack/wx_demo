/**    
* @Title: DocsTemplateDaoImpl.java
* @Package com.frame.tobaCase.dao.impl
* @Description: 文书模板dao实现类
* @author: shizh
* @date 2017年2月13日 下午3:36:37
* @version V1.0
*/
package com.frame.tobaCase.dao.impl;

import org.springframework.stereotype.Repository;

import com.frame.core.dao.MybaitsBaseDaoImpl;
import com.frame.tobaCase.dao.IDocsTemplateDao;
import com.frame.tobaCase.entity.DocsTemplate;

@Repository("docsTemplateDao")
public class DocsTemplateDaoImpl extends MybaitsBaseDaoImpl<DocsTemplate, String> implements IDocsTemplateDao {

}
