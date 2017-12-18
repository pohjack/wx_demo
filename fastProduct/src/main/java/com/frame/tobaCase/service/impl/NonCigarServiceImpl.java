package com.frame.tobaCase.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICaseCigarDao;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.INonCigarDao;
import com.frame.tobaCase.entity.NonCigar;
import com.frame.tobaCase.service.INonCigarService;

@Service("nonCigarService")
public class NonCigarServiceImpl extends BaseServiceImpl<NonCigar, String> implements INonCigarService {

    @Resource
    private ICaseCigarDao caseCigarDao;
    @Resource
    private ICaseInfoDao caseInfoDao;
    @Resource
    private INonCigarDao nonCigarDao;

    @Override
    public List<NonCigar> findByCaseId(String id) {
	// TODO
	return nonCigarDao.findByCaseId(id);

    }

    @Override
    public Double countTotalVal(String id, String param) {
	// TODO
	return nonCigarDao.countTotalVal(id, param);

    }

    @Override
    public String saveAll(List<NonCigar> nonCigar, String caseInfoId) {
	for (NonCigar cigar : nonCigar) {
	    cigar.setCaseId(caseInfoId);
	    cigar.setId(UUIDUtil.get32UUID());
	}
	nonCigarDao.saveAll(nonCigar);
	return caseInfoId;
    }

    @Override
    protected IMybaitsBaseDao<NonCigar, String> getBaseDao() {

	return nonCigarDao;

    }

    @Override
    public List<NonCigar> findByCaseIdAndCigarId(String caseId, String cigarId) {

	return nonCigarDao.findByCaseIdAndCigarId(caseId, cigarId);

    }

    @Override
    public int findEachCigarTotal(String caseId, String cigarId) {
	return nonCigarDao.findEachCigarTotal(caseId, cigarId);
    }

    @Override
    public List<String> findCigarIds(String caseId) {
	return nonCigarDao.findCigarIds(caseId);

    }

    @Override
    public int removeByCaseIdCigarId(String caseId, String cigarId) {
	return nonCigarDao.removeByCaseIdCigarId(caseId, cigarId);
    }

}
