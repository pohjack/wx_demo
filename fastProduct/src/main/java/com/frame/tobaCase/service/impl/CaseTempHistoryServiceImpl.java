/**    
* @Title: BaseTemplateServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 基础模板service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICaseTempHistoryDao;
import com.frame.tobaCase.entity.CaseTempHistory;
import com.frame.tobaCase.service.ICaseTempHistoryService;

@Service("caseTempHistoryService")
public class CaseTempHistoryServiceImpl extends BaseServiceImpl<CaseTempHistory, String>
	implements ICaseTempHistoryService {

    @Resource
    private ICaseTempHistoryDao caseTempHistoryDao;

    @Override
    protected IMybaitsBaseDao<CaseTempHistory, String> getBaseDao() {
	return caseTempHistoryDao;
    }

    @Override
    public CaseTempHistory checkHistory(String caseId, String modelNo) {
	CaseTempHistory caseTempHistory = new CaseTempHistory();
	caseTempHistory.setCaseId(caseId);
	caseTempHistory.setDocsId(modelNo);
	caseTempHistory.setStatus(1);
	return caseTempHistoryDao.checkHistory(caseTempHistory);
    }

    @Override
    @Transactional(rollbackFor = { Exception.class })
    public int saveCaseTemp(CaseTempHistory caseTempHistory) throws Exception {
	caseTempHistoryDao.update(caseTempHistory);
	Integer save = caseTempHistoryDao.save(caseTempHistory);
	return save;
    }

}
