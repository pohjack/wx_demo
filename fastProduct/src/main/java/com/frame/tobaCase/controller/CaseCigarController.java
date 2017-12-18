/**    
* @Title: CaseCigarController.java
* @Package com.frame.tobaCase.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2017年2月23日 下午12:03:07
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.commons.utils.DateUtil;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.service.IOrganizationService;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseCigarModel;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInvoPers;
import com.frame.tobaCase.entity.NonCigar;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseGangYinService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.frame.tobaCase.service.ICigarInfoService;
import com.frame.tobaCase.service.INonCigarService;
import com.frame.tobaCase.utils.ReflectSetValueUtil;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/caseCigar")
public class CaseCigarController extends BaseController<CaseCigar, String> {
    @Resource
    private ICaseCigarService caseCigarService;
    @Resource
    private ICaseInfoService caseInfoService;
    @Resource
    private ICigarInfoService cigarInfoService;
    @Resource
    private IOrganizationService organizationService;
    @Resource
    private ICaseInvoPersService caseInvoPersService;
    @Resource
    private INonCigarService nonCigarService;
    @Resource
    private ICaseGangYinService caseGangYinService;

    @Override
    protected IBaseService<CaseCigar, String> getBaseService() {
	return caseCigarService;
    }

    /**
     * 
    * @Description: 跳转到进出存帐页面
    * @param @return
    * @author: yuyf
    * @date 2017年3月22日 下午7:18:26
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_ACCOUNT)
    @RequestMapping("/toAccount")
    public String toAccount(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	CaseInfo info = caseInfoService.findById(caseInfoId);
	model.addAttribute("info", info);
	/*
	 * JSONObject aoData= new JSONObject();
	 * 
	 * String json = "{\"caseInfoId\":"+caseInfoId+"}"; JSONObject
	 * searchDate = JSONObject.fromObject(json);
	 * aoData.put("name","searchDate"); aoData.put("value",searchDate);
	 * request.setAttribute("aoData", aoData);
	 * DateTablesResult<Map<String,Object>>
	 * caseCigar=this.getPagingList(request);
	 */
	List<CaseCigar> caseCigar = caseCigarService.findByCaseId(caseInfoId);
	model.addAttribute("caseCigar", caseCigar);
	model.addAttribute("caseInfoId", caseInfoId);
	return "webmaster/docTemp/account";
    }

    /**
    * @Description: 保存涉案卷烟
    * @param @param caseCigars
    * @param @param caseInfoId
    * @param @return
    * @author: lpy
    * @date 2017年3月9日 上午11:18:16
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/saveCaseCigar")
    @ResponseBody
    public Object saveCaseCigar(CaseCigarModel caseCigars, String caseInfoId) {
	JSONObject json = new JSONObject();
	try {
	    List<CaseCigar> caseCigarList = caseCigars.getCaseCigars();
	    if (caseCigarList != null && caseCigarList.size() > 0) {
		List<CaseCigar> list = new ArrayList<CaseCigar>();
		for (CaseCigar c : caseCigarList) {
		    if (c.getCigarId() != null) {
			c.setId(UUIDUtil.get32UUID());
			c.setFormat("1x10包");
			list.add(c);
		    }
		}
		caseInfoId = caseCigarService.saveAll(list, caseInfoId);
	    }
	    json.put("caseInfoId", caseInfoId);
	    json.put("status", "1");
	} catch (Exception e) {
	    json.put("status", "0");
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
    * @Description: 删除涉案卷烟
    * @param @param caseCigars
    * @param @param caseInfoId
    * @param @return
    * @author: lpy
    * @date 2017年3月9日 上午11:18:16
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_REMOVE)
    @RequestMapping("/removeCaseCigar")
    @ResponseBody
    public String removeCaseCigar(String id, String caseId) {
	JSONObject json = new JSONObject();
	CaseCigar cc = new CaseCigar();
	try {
	    if (StringUtil.isNotEmpty(id)) {
		cc = caseCigarService.findById(id);
		String[] ids = { id };
		caseCigarService.remove(ids);
	    }
	    json.put("status", "1");
	    caseCigarService.updateAll(cc, 0); // 0 删除 1 修改
	} catch (Exception e) {
	    json.put("status", "0");
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
    * @Description: 修改涉案卷烟
    * @param @param request
    * @param @return
    * @author: lpy
    * @date 2017年3月9日 上午11:20:10
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/updateCaseCigar")
    @ResponseBody
    public String updateCaseCigar(HttpServletRequest request) {
	JSONObject json = new JSONObject();
	try {
	    String id = request.getParameter("id");
	    String caseId = request.getParameter("caseId");
	    String[] name = request.getParameterValues("name");
	    String[] value = request.getParameterValues("value");
	    if (StringUtil.isNotEmpty(id)) {
		CaseCigar caseCigar = caseCigarService.findById(id);
		if (caseCigar != null) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    if (name != null && name.length > 0 && value != null && value.length > 0) {
			for (int i = 0; i < name.length; i++) {
			    map.put(name[i], value[i]);
			}
		    }
		    ReflectSetValueUtil refValue = new ReflectSetValueUtil();
		    refValue.reflectSetValue(caseCigar, map);
		    caseCigarService.updateAll(caseCigar, 1);// 0 删除 1 修改
		}
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();

    }

    /**
    * @Description: 统计涉案卷烟
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2017年3月9日 上午11:20:22
    * @throws
     */
    @RequiresPermissions(SysConstant.STATS_VIEW)
    @RequestMapping("/stats")
    public String stats(Model model) {
	try {
	    List<String> cigars = cigarInfoService.findAllName();
	    model.addAttribute("cigars", cigars);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/stats/Stats";
    }

    /**
    * @Description: 统计涉案卷烟
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2017年3月9日 上午11:20:22
    * @throws
     */
    @RequiresPermissions(SysConstant.STATS_VIEW)
    @ResponseBody
    @RequestMapping("/searchStats")
    public String searchStats(String cigarName, String startDate, String endDate) {
	JSONObject json = new JSONObject();
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("cigarName", cigarName);
	    map.put("startDate", startDate);
	    map.put("endDate", endDate);
	    List<String> orgId = getOrgId();
	    map.put("orgId", orgId);
	    Map<String, Object> data = new HashMap<String, Object>();
	    data.putAll(caseCigarService.findInspectResult2(map));// 统计假非私数量
	    data.putAll(caseCigarService.findInspectResult(map));// 统计假非私罚款
	    map.put("status", "1");
	    String startCase = caseInfoService.findCaseCount(map);// 统计立案数量
	    map.put("status", "0");
	    String endCase = caseInfoService.findCaseCount(map);// 统计结案数量
	    data.put("startCase", startCase);
	    data.put("endCase", endCase);
	    json.put("data", data);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 统计卷烟列表
    * @param @param request
    * @param @param model
    * @param @return
    * @author linpy
    * @date 2017年3月6日 下午7:46:45
    * @throws
     */
    @RequiresPermissions(SysConstant.STATS_VIEW)
    @RequestMapping("toCigarStatsList")
    public String toCigarStatsList(String cigarType, String startDate, String endDate, String name, Model model) {
	model.addAttribute("cigarType", cigarType);
	model.addAttribute("startDate", startDate);
	model.addAttribute("endDate", endDate);
	model.addAttribute("name", name);
	return "webmaster/stats/cigarStatsList";
    }

    @RequestMapping("/compareStats")
    public String campareStats(Model model) {
	try {
	    List<String> cigars = cigarInfoService.findAllName();
	    model.addAttribute("cigars", cigars);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/stats/CompareStats";
    }

    /**
     * 获取分页数据
     */
    @RequestMapping("/getPagingList2")
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @ResponseBody
    public DateTablesResult<Map<String, Object>> getPagingList2(HttpServletRequest request) {
	DateTablesResult<Map<String, Object>> dataTable = new DateTablesResult<Map<String, Object>>(request);
	List<String> stringList = getOrgId();
	dataTable.setOrgs(stringList);
	return getBaseService().queryByPage(dataTable);
    }

    @ResponseBody
    @RequiresPermissions(SysConstant.STATS_VIEW)
    @RequestMapping("/searchCampareStats")
    public String searchCampareStats(HttpServletRequest request) {
	JSONObject json = new JSONObject();
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    List<Map<String, Object>> listDate = new ArrayList<Map<String, Object>>();
	    String strDate = request.getParameter("date");
	    for (int i = 0; i < 12; i++) {
		Map<String, Object> listStart = new HashMap<String, Object>();
		listStart.put("endDate", strDate);
		strDate = DateUtil.checkOptionDay(-1, strDate, "month");
		listStart.put("startDate", strDate);
		listDate.add(listStart);
	    }
	    map.put("dateList", listDate);

	    Map<String, Object> list0 = caseCigarService.findCompareByResult(map);// 涉案全部卷烟
	    map.put("result", "非");
	    Map<String, Object> list1 = caseCigarService.findCompareByResult(map);// 涉案非烟
	    map.put("result", "假");
	    Map<String, Object> list2 = caseCigarService.findCompareByResult(map);// 涉案假烟
	    map.put("result", "私");
	    Map<String, Object> list3 = caseCigarService.findCompareByResult(map);// 涉案私烟
	    Map<String, Object> list4 = caseInfoService.findCompareCase(map); // 案件数量
	    list0 = sortMap(list0);
	    list1 = sortMap(list1);
	    list2 = sortMap(list2);
	    list3 = sortMap(list3);
	    list3 = sortMap(list4);
	    List<Object> sumList = valueToList(list0);
	    List<Object> nonList = valueToList(list1);
	    List<Object> fakeList = valueToList(list2);
	    List<Object> privList = valueToList(list3);
	    List<Object> caseList = valueToList(list4);
	    json.put("sumList", sumList);
	    json.put("nonList", nonList);
	    json.put("fakeList", fakeList);
	    json.put("privList", privList);
	    json.put("caseList", caseList);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return json.toString();
    }

    @RequestMapping("/stats2")
    @RequiresPermissions(SysConstant.STATS_VIEW)
    public String stats2() {
	return "webmaster/stats/Stats2";
    }

    /**
     * 
    * @Description: TODO更新涉案卷烟规格
    * @param @param request
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:06:18
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/updateCigarFormat")
    @ResponseBody
    public String updateCigarFormat(HttpServletRequest request) {
	JSONObject json = new JSONObject();
	try {
	    String id = request.getParameter("id");
	    String value = request.getParameter("value");
	    if (id != null && (!("".equals(id)))) {
		CaseCigar caseCigar = caseCigarService.findById(id);
		caseCigar.setFormat(value);
		caseCigarService.update(caseCigar);
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();

    }

    @ResponseBody
    @RequestMapping("/searchStats2")
    @RequiresPermissions(SysConstant.STATS_VIEW)
    public String searchStats2(String cigarName, String startDate, String endDate) {
	JSONObject json = new JSONObject();
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("cigarName", cigarName);
	    map.put("startDate", startDate);
	    map.put("endDate", endDate);
	    Map<String, Object> data = new HashMap<String, Object>();
	    data.putAll(caseCigarService.findInspectResult2(map));// 统计假非私数量
	    data.putAll(caseCigarService.findInspectResult(map));// 统计假非私罚款

	    String startCase = caseInfoService.findCaseCount(map);// 统计结案数量
	    map.put("endCase", "endCase");
	    String endCase = caseInfoService.findCaseCount(map);// 统计立案数量
	    if (!"0".equals(startCase)) {
		data.put("startCase", startCase);
	    }
	    if (!"0".equals(endCase)) {
		data.put("endCase", endCase);
	    }
	    json.put("data", data);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return json.toString();
    }

    // 拿到部门的id
    private List<String> getOrgId() {
	Subject user = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	String id = shiroUser.orgId;
	List<String> stringList = organizationService.findOrgIdById(id);
	stringList.add(id);
	return stringList;
    }

    // 把map中的value转换为list
    private List<Object> valueToList(Map<String, Object> map) {
	Collection<Object> valueCollection = map.values();
	List<Object> valueList = new ArrayList<Object>(valueCollection);
	return valueList;
    }

    // 对map排序
    private Map<String, Object> sortMap(Map<String, Object> map) {
	TreeMap<String, Object> treemap = new TreeMap<String, Object>(map);
	return treemap;
    }
}
