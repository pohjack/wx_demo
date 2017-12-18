/**    
* @Package com.frame.tobaCase.service.impl
* @Description: 案件信息service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.tobaCase.dao.ICaseEvidenceDao;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.GatherModel;
import com.frame.tobaCase.entity.NonExcel;
import com.frame.tobaCase.entity.SearchDataVo;
import com.frame.tobaCase.service.ICaseInfoService;
import com.github.pagehelper.StringUtil;

@Service("caseInfoService")
public class CaseInfoServiceImpl extends BaseServiceImpl<CaseInfo, String> implements ICaseInfoService {
    @Resource
    private ICaseInfoDao caseInfoDao;

    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;

    @Resource
    private ICaseEvidenceDao caseEvidenceDao;

    @Override
    protected IMybaitsBaseDao<CaseInfo, String> getBaseDao() {
	return caseInfoDao;
    }

    @Override
    public Map<String, Object> findByKey(String id) {
	return caseInfoDao.findByKey(id);
    }

    @Override
    public int saveCaseInfo(CaseInfo info, CaseInfoSlave slave) {
	String caseCause = info.getCaseCause();
	if (StringUtil.isNotEmpty(caseCause)) {
	    caseCause = caseCause.replace(",", "、");
	}
	info.setCaseCause(caseCause);
	caseInfoDao.save(info);
	slave.setMasterId(info.getId());
	return caseInfoSlaveDao.save(slave);
    }

    @Override
    public int updateCaseInfo(CaseInfo info, CaseInfoSlave slave) {
	String caseCause = info.getCaseCause();
	if (StringUtil.isNotEmpty(caseCause)) {
	    caseCause = caseCause.replace(",", "、");
	}
	int isSite = info.getIsSite();
	if (isSite == 1) {
	    caseEvidenceDao.deleteByArgs(info.getId()); // 删除类型为3、4证据
	}
	info.setCaseCause(caseCause);
	caseInfoDao.update(info);
	return caseInfoSlaveDao.update(slave);
    }

    @Override
    public String findCaseCount(Map<String, Object> endCase) {
	return caseInfoDao.findCaseCount(endCase);
    }

    @Override
    public String findMaxCaseNo() {
	int max = caseInfoDao.findMaxCaseNo();
	DecimalFormat df = new DecimalFormat("000");
	return df.format(max + 1);
    }

    @Override
    public Map<String, Object> findCompareCase(Map<String, Object> map) {
	return caseInfoDao.findCompareCase(map);
    }

    @Override
    public int updateCaseCauseInfo(String caseId, String caseNo, String caseCause, String source) {
	CaseInfo caseInfo = new CaseInfo();
	caseInfo.setId(caseId);
	int result;
	if (StringUtils.isNotEmpty(source)) {
	    caseInfo.setCaseSource(source);
	    result = caseInfoDao.updateSource(caseInfo);
	} else {
	    caseInfo.setCaseCauseCode(caseNo);
	    caseInfo.setCaseCause(caseCause);
	    result = caseInfoDao.updateCause(caseInfo);
	}
	return result;
    }

    @Override
    public int endCase(String caseId) {
	return caseInfoDao.endCase(caseId);
    }

    @Override
    public List<GatherModel> findBySearchVo(SearchDataVo sv) {
	return caseInfoDao.findBySearchVo(sv);
    }

    @Override
    public int regiNoIsSame(String regiNo) {
	return caseInfoDao.regiNoIsSame(regiNo);
    }

    @Override
    public List<NonExcel> findBySearch(SearchDataVo sv) {
	return caseInfoDao.findBySearch(sv);
    }

    @Override
    public String findCountByCaseId(String caseInfoId) {
	// TODO
	return caseInfoDao.findCountByCaseId(caseInfoId);

    }
}
