/**    
* @Title: CaseInvoPersServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 案件信息service实现类
* @author: linpy
* @date 2017年2月20日 下午2:56:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IUserDao;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.dao.ICaseInvoPersDao;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.CaseInvoPers;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.github.pagehelper.StringUtil;

@Service("caseInvoPersService")
public class CaseInvoPersServiceImpl extends BaseServiceImpl<CaseInvoPers, String> implements ICaseInvoPersService {

    @Resource
    private ICaseInvoPersDao caseInvoPersDao;
    @Resource
    private ICaseInfoDao caseInfoDao;
    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;
    @Resource
    private IUserDao userDao;

    @Override
    protected IMybaitsBaseDao<CaseInvoPers, String> getBaseDao() {
	return caseInvoPersDao;
    }

    @Override
    public Map<String, Object> findByCaseId(String id) {
	return caseInvoPersDao.findByCaseId(id);
    }

    @Override
    public String saveCaseInvo(CaseInvoPers pers, String caseInfoId) {
	if (StringUtil.isEmpty(caseInfoId)) {// 没有保存案件基本信息时，先生成一个案件基本信息的对象
	    caseInfoId = UUIDUtil.get32UUID();// 生成一个caseid
	    pers.setCaseInfoId(caseInfoId);
	    CaseInfo info = getCaseInfo(caseInfoId);
	    info.setCaseAddr(pers.getPremises());
	    CaseInfoSlave slave = new CaseInfoSlave();
	    slave.setMasterId(caseInfoId);
	    // ShiroUser user =
	    // (ShiroUser)(SecurityUtils.getSubject().getPrincipal());
	    // User user1 = userDao.findById(user.id);//拿到当前登陆者
	    // caseInfoId = UUIDUtil.get32UUID();//生成一个caseid
	    // String caseNo = getMaxCaseNo();//拿到立案数字
	    // String regiNo = getRegiNo(caseNo);//拿到立案编号
	    // pers.setCaseInfoId(caseInfoId);
	    // CaseInfo info = new CaseInfo();
	    // CaseInfoSlave slave = new CaseInfoSlave();
	    // info.setId(caseInfoId);
	    // info.setCaseAddr(pers.getPremises());
	    // info.setCreatOrg(user1.getOrgId());
	    // info.setCaseNo(caseNo);
	    // info.setRegiNo(regiNo);
	    // slave.setMasterId(caseInfoId);
	    caseInfoDao.save(info);// 保存case基本信息
	    caseInfoSlaveDao.save(slave);// 保存涉案人员
	} else {
	    CaseInfo info = caseInfoDao.findById(caseInfoId);
	    info.setCaseAddr(pers.getPremises());
	    caseInfoDao.update(info);
	}
	caseInvoPersDao.save(pers);
	return caseInfoId;
    }

    @Override
    public CaseInvoPers findByLicNo(String licNo) {
	return caseInvoPersDao.findByLicNo(licNo);
    }

    @Override
    public int updateCaseInvo(CaseInvoPers pers) {
	CaseInfo info = caseInfoDao.findById(pers.getCaseInfoId());
	if (info != null) {
	    info.setCaseAddr(pers.getPremises());
	    caseInfoDao.update(info);
	}
	return caseInvoPersDao.update(pers);
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

    @Override
    public int findCountByCaseId(String caseInfoId) {
	int num = caseInvoPersDao.findCountByCaseId(caseInfoId);
	return num;
    }
}
