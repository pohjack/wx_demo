/**    
* @Title: DocsTemplateSlaveServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 文书模板从表service实现类
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
import com.frame.tobaCase.dao.IDocsTemplateSlaveDao;
import com.frame.tobaCase.entity.DocsTemplateSlave;
import com.frame.tobaCase.service.IDocsTemplateSlaveService;

@Service("docsTemplateSlaveService")
public class DocsTemplateSlaveServiceImpl extends BaseServiceImpl<DocsTemplateSlave, String>
	implements IDocsTemplateSlaveService {

    @Resource
    private IDocsTemplateSlaveDao docsTemplateSlaveDao;

    @Override
    protected IMybaitsBaseDao<DocsTemplateSlave, String> getBaseDao() {
	return docsTemplateSlaveDao;
    }

    public List<DocsTemplateSlave> findByMasterId(String id, int tag) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("id", id);
	map.put("tag", tag);
	return docsTemplateSlaveDao.findByMasterId(map);
    }

    @Override
    public int addBaseToDoc(DocsTemplateSlave dSlave) {
	int max = docsTemplateSlaveDao.getTopSort(dSlave.getMasterId());
	dSlave.setSort(max + 1);
	return docsTemplateSlaveDao.save(dSlave);
    }

    @Override
    public int changeSort(String baseId, String baseIdAfter) {
	DocsTemplateSlave before = docsTemplateSlaveDao.findById(baseId);
	DocsTemplateSlave after = docsTemplateSlaveDao.findById(baseIdAfter);
	int beforeSort = before.getSort();
	int afterSort = after.getSort();
	before.setSort(afterSort);
	int re = docsTemplateSlaveDao.updateSort(before);
	if (re == 1) {
	    after.setSort(beforeSort);
	    re = docsTemplateSlaveDao.updateSort(after);
	}
	return re;
    }

}
