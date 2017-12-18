package com.frame.tobaCase.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICaseSendDao;
import com.frame.tobaCase.entity.CaseSend;
import com.frame.tobaCase.service.ICaseSendService;

@Service("caseSendService")
public class CaseSendServiceImpl extends BaseServiceImpl<CaseSend, String> implements ICaseSendService {

    @Resource
    private ICaseSendDao caseSendDao;

    @Override
    public CaseSend findByCaseIdAndCode(String caseId, String code) {
	return caseSendDao.findByCaseIdAndCode(caseId, code);

    }

    @Override
    protected IMybaitsBaseDao<CaseSend, String> getBaseDao() {
	return caseSendDao;

    }

}
