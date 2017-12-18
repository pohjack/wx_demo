/**    
* @Title: BaseTemplateSlaveServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 基础模板从表service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.IBaseTemplateSlaveDao;
import com.frame.tobaCase.entity.BaseTemplateSlave;
import com.frame.tobaCase.service.IBaseTemplateSlaveService;

@Service("baseTemplateSlaveService")
public class BaseTemplateSlaveServiceImpl extends BaseServiceImpl<BaseTemplateSlave, String>
	implements IBaseTemplateSlaveService {

    @Resource
    private IBaseTemplateSlaveDao baseTemplateSlaveDao;

    @Override
    protected IMybaitsBaseDao<BaseTemplateSlave, String> getBaseDao() {
	return baseTemplateSlaveDao;
    }

    @Override
    public List<BaseTemplateSlave> findByMasterId(String id) {
	return baseTemplateSlaveDao.findByMasterId(id);
    }

    @Override
    public String templateComb(String id) {
	List<BaseTemplateSlave> temps = baseTemplateSlaveDao.findByMasterId(id);
	StringBuffer stringBuffer = new StringBuffer();
	for (BaseTemplateSlave baseTemplateSlave : temps) {
	    String color = "'>";
	    Integer category = baseTemplateSlave.getCategory();
	    if (1 == category) {
		color = "red' contenteditable='false'>";
	    } else if (2 == category) {
		color = "green' contenteditable='false'>";
	    }
	    stringBuffer.append("<span ").append("tempId='").append(baseTemplateSlave.getId()).append("' style='color:")
		    .append(color).append(baseTemplateSlave.getValue()).append("</span>");
	}
	return stringBuffer.toString();
    }

    @Override
    public void removeTempData(String tid) {
	baseTemplateSlaveDao.removeTempData(tid);
    }

}
