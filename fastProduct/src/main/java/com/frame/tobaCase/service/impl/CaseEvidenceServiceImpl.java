/**    
* @Title: CaseEvidenceServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 证据信息service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IUserDao;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.tobaCase.dao.ICaseEvidenceDao;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseEvidence;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.service.ICaseEvidenceService;

@Service("caseEvidenceService")
public class CaseEvidenceServiceImpl extends BaseServiceImpl<CaseEvidence, String> implements ICaseEvidenceService {

    @Resource
    private ICaseEvidenceDao caseEvidenceDao;
    @Resource
    private ICaseInfoDao caseInfoDao;
    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;
    @Resource
    private IUserDao userDao;

    @Override
    protected IMybaitsBaseDao<CaseEvidence, String> getBaseDao() {
	return caseEvidenceDao;
    }

    @Override
    public List<Map<String, Integer>> countByCaseId(String id) {
	return caseEvidenceDao.countByCaseId(id);
    }

    @Override
    public int saveCaseFileInfo(int flag, CaseEvidence ce, int j) {
	int i = 0;
	if (flag == 0) { // 添加
	    i = caseEvidenceDao.save(ce);
	    if (j == 0) {
		CaseInfo info = getCaseInfo(ce.getCaseInfoId());
		CaseInfoSlave slave = new CaseInfoSlave();
		slave.setId(UUIDUtil.get32UUID());
		slave.setMasterId(ce.getCaseInfoId());
		// CaseInfo info = new CaseInfo();
		// CaseInfoSlave slave = new CaseInfoSlave();
		// info.setId(ce.getCaseInfoId());
		// slave.setId(UUIDUtil.get32UUID());
		// slave.setMasterId(ce.getCaseInfoId());
		caseInfoDao.save(info);
		caseInfoSlaveDao.save(slave);
	    }
	} else { // 修改
	    List<CaseEvidence> ceList = caseEvidenceDao.findByCaseInfoId(ce.getCaseInfoId(), ce.getEvidType());
	    if (ceList != null && ceList.size() > 0) {
		String id = ceList.get(0).getId();
		ce.setId(id);
		i = caseEvidenceDao.update(ce);
	    } else {
		i = caseEvidenceDao.save(ce); // 表示修改的时候之前没有保存 证据信息
	    }
	}
	return i;
    }

    @Override
    public List<CaseEvidence> findByCaseInfoId(String caseId, Integer index) {
	return caseEvidenceDao.findByCaseInfoId(caseId, index);

    }

    @Override
    public List<Map<String, Object>> countEvidence(String id) {
	return caseEvidenceDao.countEvidence(id);
    }

    @Override
    public int deleteByArgs(String caseInfoId) {
	return caseEvidenceDao.deleteByArgs(caseInfoId);
    }

    // 拿到一个新的caseinfo对象
    private CaseInfo getCaseInfo(String id) {
	CaseInfo info = new CaseInfo();
	String caseNo = getMaxCaseNo();// 拿到立案数字
	String regiNo = getRegiNo(caseNo);// 拿到立案编号
	ShiroUser user = (ShiroUser) (SecurityUtils.getSubject().getPrincipal());
	User user1 = userDao.findById(user.id);// 拿到当前登陆者
	info.setId(id);
	info.setCaseNo(caseNo);
	info.setRegiNo(regiNo);
	info.setCreatOrg(user1.getOrgId());
	info.setFakeRange(0);
	info.setNonRange(0);
	info.setIsSite(1);
	return info;
    }

    private String getMaxCaseNo() {
	int max = caseInfoDao.findMaxCaseNo();
	DecimalFormat df = new DecimalFormat("000");
	return df.format(max + 1);
    }

    private String getRegiNo(String caseNo) {
	Calendar cal = Calendar.getInstance();
	String regiNo = "潮烟立[" + cal.get(Calendar.YEAR) + "]第" + caseNo + "号";
	return regiNo;
    }
}
