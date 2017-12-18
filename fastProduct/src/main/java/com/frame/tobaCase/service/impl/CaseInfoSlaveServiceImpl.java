/**    
* @Title: CaseInfoServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 案件信息从类service实现类
* @author: linpy
* @date 2017年2月20日 下午2:56:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.service.ICaseInfoSlaveService;

@Service("caseInfoSlaveService")
public class CaseInfoSlaveServiceImpl extends BaseServiceImpl<CaseInfoSlave, String> implements ICaseInfoSlaveService {

    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;

    @Override
    protected IMybaitsBaseDao<CaseInfoSlave, String> getBaseDao() {
	return caseInfoSlaveDao;
    }

    @Override
    public Map<String, Object> findByCaseId(String id) {
	return caseInfoSlaveDao.findByCaseId(id);
    }

    @Override
    public CaseInfoSlave findByMasterId(String caseId) {
	return caseInfoSlaveDao.findByMasterId(caseId);
    }

}
