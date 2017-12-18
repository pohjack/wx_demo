package com.frame.tobaCase.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IUserDao;
import com.frame.tobaCase.dao.ICaseCigarDao;
import com.frame.tobaCase.dao.ICaseGangYinDao;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.service.ICaseGangYinService;

@Service("caseGangYinService")
public class CaseGangYinServiceImpl extends BaseServiceImpl<CaseGangYin, String> implements ICaseGangYinService {

    @Resource
    private ICaseCigarDao caseCigarDao;
    @Resource
    private ICaseInfoDao caseInfoDao;
    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private ICaseGangYinDao caseGangYinDao;

    @Override
    public List<CaseGangYin> findByCaseId(String id) {
	// TODO
	return caseGangYinDao.findByCaseId(id);

    }

    @Override
    public Double countTotalVal(String id, String param) {
	// TODO
	return caseGangYinDao.countTotalVal(id, param);

    }

    @Override
    public String saveAll(List<CaseGangYin> caseGangYin, String caseInfoId) {
	for (CaseGangYin gangYin : caseGangYin) {
	    gangYin.setCaseId(caseInfoId);
	    gangYin.setId(UUIDUtil.get32UUID());
	}
	caseGangYinDao.saveAll(caseGangYin);
	return caseInfoId;
    }

    @Override
    protected IMybaitsBaseDao<CaseGangYin, String> getBaseDao() {

	return caseGangYinDao;

    }

    @Override
    public List<CaseGangYin> findByCaseIdAndCigarId(String caseId, String cigarId) {

	return caseGangYinDao.findByCaseIdAndCigarId(caseId, cigarId);
    }

    @Override
    public int findEachCigarTotal(String caseId, String cigarId) {
	return caseGangYinDao.findEachCigarTotal(caseId, cigarId);
    }

    @Override
    public List<String> findCigarIds(String caseId) {
	return caseGangYinDao.findCigarIds(caseId);

    }

    @Override
    public int removeByCaseIdCigarId(String caseId, String cigarId) {
	return caseGangYinDao.removeByCaseIdCigarId(caseId, cigarId);
    }

}
