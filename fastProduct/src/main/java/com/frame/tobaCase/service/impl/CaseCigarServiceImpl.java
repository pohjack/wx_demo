/**    
* @Title: CaseCigarServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: 涉案卷烟service实现类
* @author: shizh
* @date 2017年2月13日 下午3:06:59
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
import com.frame.tobaCase.dao.ICaseCigarDao;
import com.frame.tobaCase.dao.ICaseInfoDao;
import com.frame.tobaCase.dao.ICaseInfoSlaveDao;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.service.ICaseCigarService;
import com.github.pagehelper.StringUtil;

@Service("caseCigarService")
public class CaseCigarServiceImpl extends BaseServiceImpl<CaseCigar, String> implements ICaseCigarService {

    @Resource
    private ICaseCigarDao caseCigarDao;
    @Resource
    private ICaseInfoDao caseInfoDao;
    @Resource
    private ICaseInfoSlaveDao caseInfoSlaveDao;
    @Resource
    private IUserDao userDao;

    @Override
    protected IMybaitsBaseDao<CaseCigar, String> getBaseDao() {
	return caseCigarDao;
    }

    @Override
    public List<CaseCigar> findByCaseId(String id) {
	return caseCigarDao.findByCaseId(id);
    }

    @Override
    public String findTypesById(String id) {
	return caseCigarDao.findTypesById(id);

    }

    @Override
    public Double countTotalVal(String id, String param) {
	return caseCigarDao.countTotalVal(id, param);
    }

    @Override
    public List<CaseCigar> findByAttr(String id, String param) {
	return caseCigarDao.findByAttr(id, param);
    }

    @Override
    public String countType(String id) {
	return caseCigarDao.countType(id);
    }

    @Override
    public String saveAll(List<CaseCigar> caseCigar, String caseInfoId) {
	Map<String, Integer> range = getSum(caseCigar);// 获取罚款幅度
	// 方案1--
	if (StringUtil.isEmpty(caseInfoId)) {// 前面没有添加案件基本数据，则新增一条案件基本数据
	    caseInfoId = UUIDUtil.get32UUID();
	    CaseInfo info = getCaseInfo(caseInfoId);
	    info.setFakeRange(range.get("fakeRange"));
	    info.setNonRange(range.get("nonRange"));
	    CaseInfoSlave slave = new CaseInfoSlave();
	    slave.setMasterId(caseInfoId);

	    // CaseInfo info = new CaseInfo();
	    // CaseInfoSlave slave = new CaseInfoSlave();
	    // info.setId(caseInfoId);
	    // info.setFakeRange(range.get("fakeRange"));
	    // info.setNonRange(range.get("nonRange"));
	    // slave.setMasterId(caseInfoId);
	    caseInfoDao.save(info);
	    caseInfoSlaveDao.save(slave);
	} else {
	    CaseInfo info = caseInfoDao.findById(caseInfoId);
	    info.setFakeRange(range.get("fakeRange"));
	    info.setNonRange(range.get("nonRange"));
	    caseInfoDao.update(info);
	}
	for (CaseCigar cigar : caseCigar) {
	    cigar.setCaseId(caseInfoId);

	}
	caseCigarDao.saveAll(caseCigar);
	return caseInfoId;
	// --
	// 方案2--
	/*
	 * if(StringUtil.isNotEmpty(caseCigar.get(0).getId())) {
	 * caseCigarDao.removeByCaseId(caseInfoId); } else { for(CaseCigar cigar
	 * : caseCigar){ cigar.setCaseId(caseInfoId);
	 * cigar.setId(UUIDUtil.get32UUID()); } }
	 * caseCigarDao.saveAll(caseCigar); return caseInfoId;
	 */
	// --
    }

    @Override
    public int updateAll(CaseCigar caseCigar, int type) {
	if (type == 1) {
	    caseCigarDao.update(caseCigar);
	}
	List<CaseCigar> caseCigars = caseCigarDao.findByCaseId(caseCigar.getCaseId());
	if (caseCigars != null && caseCigars.size() > 0) {
	    Map<String, Integer> range = getSum(caseCigars);// 获取罚款幅度
	    CaseInfo info = caseInfoDao.findById(caseCigar.getCaseId());
	    if (info != null) {
		info.setFakeRange(range.get("fakeRange"));
		info.setNonRange(range.get("nonRange"));
		caseInfoDao.update(info);
	    }
	}

	return 0;
    }

    @Override
    public Map<String, Object> findCigarName(Map<String, Object> map) {
	Map<String, Object> data = new HashMap<String, Object>();
	List<Map<String, Object>> cigars = caseCigarDao.findCigarName(map);
	int sumNumber = 0;
	if (cigars != null && cigars.size() > 0) {
	    for (Map<String, Object> cigar : cigars) {
		sumNumber += Integer.parseInt(cigar.get("cigarCount").toString());
	    }
	}
	data.put("sumNumber", sumNumber);
	data.put("cigars", cigars);
	return data;
    }

    @Override
    public Map<String, Object> findInspectResult(Map<String, Object> map) {
	Map<String, Object> value = new HashMap<String, Object>();
	List<Map<String, Object>> cigars = caseCigarDao.findInspectResult(map);
	int range = 0; // 罚款比例
	String inspectResult = "";// 哪种类型的烟
	if (cigars != null && cigars.size() > 0) {
	    double sunValue = 0;// 罚款总值
	    double totalValue = 0;// 非/假罚款数量
	    double sumNonValue = 0;// 非罚款总值
	    double sumFakeValue = 0;// 假罚款总值
	    for (Map<String, Object> cigar : cigars) {
		totalValue = Double.parseDouble(cigar.get("totalValue").toString());
		inspectResult = cigar.get("inspectResult").toString();
		if ("非".equals(inspectResult)) {
		    range = Integer.parseInt(cigar.get("nonRange").toString());
		    totalValue = totalValue * range * 0.01;
		    sumNonValue += totalValue;
		} else if ("假".equals(inspectResult)) {
		    range = Integer.parseInt(cigar.get("fakeRange").toString());
		    totalValue = totalValue * range * 0.01;
		    sumFakeValue += totalValue;
		} else {
		    totalValue = 0;
		}
		sunValue += totalValue;
	    }
	    value.put("sumValue", sunValue);
	    value.put("sumNonValue", sumNonValue);
	    value.put("sumFakeValue", sumFakeValue);
	}
	return value;
    }

    @Override
    public Map<String, Object> findInspectResult2(Map<String, Object> map) {
	Map<String, Object> maps = new HashMap<String, Object>();
	List<Map<String, Object>> cigars = caseCigarDao.findInspectResult2(map);
	if (cigars != null && cigars.size() > 0) {
	    for (Map<String, Object> m : cigars) {
		maps.put(m.get("inspectResult").toString(), m);
	    }
	}
	return maps;
    }

    @Override
    public Map<String, Object> findCompareByResult(Map<String, Object> map) {
	return caseCigarDao.findCompareByResult(map);
    }

    // 获得罚款幅度
    private Map<String, Integer> getSum(List<CaseCigar> caseCigar) {
	double num1 = 0;
	double num2 = 0;
	for (CaseCigar c : caseCigar) {
	    if ("非".equals(c.getInspectResult())) {
		num1 += c.getTotalValue();
	    } else if ("假".equals(c.getInspectResult())) {
		num2 += c.getTotalValue();
	    }
	}
	int nonRange = countRange(num1, "非");
	int fakeRange = countRange(num2, "假");
	Map<String, Integer> map = new HashMap<String, Integer>();
	map.put("fakeRange", fakeRange);
	map.put("nonRange", nonRange);
	return map;
    }

    // 根据类型及总值计算罚款幅度
    private int countRange(double sum, String type) {
	int ratio = 0;
	if ("非".equals(type)) {
	    if (sum > 0 && sum <= 1000) {
		ratio = 5;
	    } else if (sum >= 1000 && sum < 2000) {
		ratio = 6;
	    } else if (sum >= 2000 && sum < 5000) {
		ratio = 7;
	    } else if (sum >= 5000 && sum < 10000) {
		ratio = 8;
	    } else if (sum >= 10000) {
		ratio = 10;
	    }
	} else {
	    if (sum > 0 && sum < 1500) {
		ratio = 25;
	    } else if (sum >= 1500 && sum < 10000) {
		ratio = 35;
	    } else if (sum >= 10000) {
		ratio = 45;
	    }
	}
	return ratio;
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
