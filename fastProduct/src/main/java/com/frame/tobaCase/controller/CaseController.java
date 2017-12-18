/**    
* @Title: TestController.java
* @Package com.frame.tobaCase.controller
* @Description: 案件控制层
* @author: liy
* @date 2017年2月10日 上午11:28:10
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.sys.service.IOrganizationService;
import com.frame.sys.service.IUserService;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.CaseInvoPers;
import com.frame.tobaCase.entity.CaseSend;
import com.frame.tobaCase.entity.CaseTempHistory;
import com.frame.tobaCase.entity.GatherModel;
import com.frame.tobaCase.entity.Law;
import com.frame.tobaCase.entity.LicenceInfo;
import com.frame.tobaCase.entity.NonCigar;
import com.frame.tobaCase.entity.NonExcel;
import com.frame.tobaCase.entity.SearchDataVo;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICaseInfoSlaveService;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.frame.tobaCase.service.ICaseSendService;
import com.frame.tobaCase.service.ICaseTempHistoryService;
import com.frame.tobaCase.service.ILawService;
import com.frame.tobaCase.service.ILicenceInfoService;
import com.frame.tobaCase.service.INonCigarService;
import com.frame.tobaCase.service.ITemplateService;
import com.frame.tobaCase.utils.ExcelUtil;
import com.frame.tobaCase.utils.ExportExcelUtil;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/caseInfo")
public class CaseController extends BaseController<CaseInfo, String> {
    public static final String CASEFILEPATH = "caseInfoFile";
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    @Resource
    private ICaseInfoService caseInfoService;

    @Resource
    private ICaseInfoSlaveService caseInfoSlaveService;

    @Resource
    private ICaseInvoPersService caseInvoPersService;

    @Resource
    private ILicenceInfoService licenceInfoService;

    @Resource
    private ILawService lawService;

    @Resource
    private IUserService userService;

    @Resource
    private IOrganizationService organizationService;

    @Resource
    private ITemplateService templateService;

    @Resource
    private ICaseTempHistoryService caseTempHistoryService;
    @Resource
    private INonCigarService nonCigarService;
    @Resource
    private ICaseCigarService caseCigarService;
    @Resource
    private ICaseSendService caseSendService;

    @Override
    protected IBaseService<CaseInfo, String> getBaseService() {
	return caseInfoService;
    }

    /**
     * 
    * @Description: 跳转到涉案卷烟登记
    * @param @return
    * @author: liy
    * @date 2017年2月21日 下午7:18:26
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/CaseCigar")
    public String toCaseCigar(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);
	String type = request.getParameter("type");
	model.addAttribute("caseInfoId", caseInfoId);
	model.addAttribute("type", type);
	return "webmaster/caseInfo/caseinfoEditCigar";
    }

    /**
     * 
    * @Description: 进出存帐：出库日期
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:07:29
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/updateOutdate")
    @ResponseBody
    public String updateOutdate(HttpServletRequest request, Model model) {
	JSONObject json = new JSONObject();
	try {
	    String id = request.getParameter("caseInfoId");
	    String value = request.getParameter("outdate");
	    if (id != null && (!("".equals(id)))) {
		CaseInfo info = caseInfoService.findById(id);
		info.setCigarOutdate(sdf.parse(value));
		caseInfoService.update(info);
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 查看涉案卷烟
    * @param @return
    * @author: yuyf
    * @date 2017年2月21日 下午7:18:26
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toCigarView")
    public String toCigarView(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);
	model.addAttribute("caseInfoId", caseInfoId);
	return "webmaster/caseInfo/caseinfoCigarView";
    }

    /**
     * 
    * @Description: 送达回证：地点、方式
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:08:08
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/updateSend")
    @ResponseBody
    public String updateSend(HttpServletRequest request, Model model) {
	JSONObject json = new JSONObject();
	try {
	    String caseInfoId = request.getParameter("caseInfoId");
	    String flag = request.getParameter("flag");
	    String name = request.getParameter("name");
	    String value = request.getParameter("value");
	    CaseSend caseSend = caseSendService.findByCaseIdAndCode(caseInfoId, flag);
	    switch (name) {
	    case "way":
		caseSend.setWay(value);
		break;
	    case "place":
		caseSend.setPlace(value);
		break;
	    case "wenhao":
		caseSend.setWenhao(value);
		break;

	    }
	    caseSendService.update(caseSend);
	    json.put("status", 1);
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 非烟条码明细
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:08:58
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toEditNonDetail")
    public String toEditNonDetail(HttpServletRequest request, Model model) {
	String caseId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);
	this.doNonCigar(caseId);
	List<NonCigar> nons = nonCigarService.findByCaseId(caseId);
	CaseInfo info = caseInfoService.findById(caseId);
	model.addAttribute("info", info);
	CaseInvoPers pers = caseInvoPersService.findById(caseId);
	model.addAttribute("pers", pers);
	JSONObject nonjson = new JSONObject();
	nonjson.put("nons", nons);
	String type = request.getParameter("type");
	model.addAttribute("type", type);
	model.addAttribute("caseInfoId", caseId);
	model.addAttribute("nonjson", nonjson);
	model.addAttribute("nons", nons);
	return "webmaster/caseInfo/caseinfoEditNonDetail";
    }

    /**
     * 
    * @Description: 历史案件 --非烟条码明细
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:09:31
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toNonDetail")
    public String toNonDetail(HttpServletRequest request, Model model) {
	String caseId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);

	CaseInfo info = caseInfoService.findById(caseId);
	model.addAttribute("info", info);
	CaseInvoPers pers = caseInvoPersService.findById(caseId);
	model.addAttribute("pers", pers);
	JSONObject nonjson = new JSONObject();
	this.doNonCigar(caseId);
	List<NonCigar> nons = nonCigarService.findByCaseId(caseId);
	nonjson.put("nons", nons);
	model.addAttribute("caseInfoId", caseId);
	model.addAttribute("nonjson", nonjson);
	model.addAttribute("nons", nons);
	return "webmaster/caseInfo/caseinfoNonDetailView";
    }

    /**
     * 
    * @Description: 跳转到案件信息登记
    * @param @param request
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 下午6:45:34
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toEditCase")
    public String toEditCase(HttpServletRequest request, Model model) {
	try {
	    String id = request.getParameter("caseInfoId");
	    model = this.tagValue(request, model);
	    String caseNo = "";
	    if (StringUtil.isNotEmpty(id)) {
		CaseInfo info = caseInfoService.findById(id);
		CaseInfoSlave slave = caseInfoSlaveService.findByMasterId(id);
		if (info != null) {
		    caseNo = info.getCaseNo();
		}
		model.addAttribute("info", info);
		model.addAttribute("slave", slave);
		model.addAttribute("caseInfoId", id);
	    } else {
		caseNo = caseInfoService.findMaxCaseNo();// 案件编号自增
	    }
	    String type = request.getParameter("type");
	    model.addAttribute("type", type);
	    List<Law> laws = lawService.findAll();
	    model.addAttribute("caseNo", caseNo);
	    model.addAttribute("laws", laws);

	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/caseInfo/caseinfoEdit";
    }

    /**
     * 
    * @Description: 查看案件信息
    * @param @param request
    * @param @param model
    * @param @return
    * @author: yuyf
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toHisView")
    public String toHisView(HttpServletRequest request, Model model) {
	try {
	    String id = request.getParameter("caseInfoId");
	    String caseNo = "";
	    model = this.tagValue(request, model);
	    CaseInfo info = caseInfoService.findById(id);
	    CaseInfoSlave slave = caseInfoSlaveService.findByMasterId(id);
	    if (info != null) {
		caseNo = info.getCaseNo();
	    }
	    model.addAttribute("info", info);
	    model.addAttribute("slave", slave);
	    model.addAttribute("caseInfoId", id);

	    List<Law> laws = lawService.findAll();

	    model.addAttribute("caseNo", caseNo);
	    model.addAttribute("laws", laws);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/caseInfo/caseinfoView";
    }

    /**
    * @Description: 保存案件基本信息
    * @param @param info
    * @param @param slave
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 上午11:34:31
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("doSaveCase")
    @ResponseBody
    public String saveCase(CaseInfo info, CaseInfoSlave slave, HttpServletRequest request) {
	JSONObject json = new JSONObject();
	String caseInfoId = request.getParameter("caseInfoId");
	try {
	    if (StringUtil.isNotEmpty(info.getId())) {
		caseInfoService.updateCaseInfo(info, slave);// 修改
	    } else {
		if (StringUtil.isNotEmpty(caseInfoId)) { // 是否先上传图片
		    info.setId(caseInfoId);
		} else {
		    caseInfoId = UUIDUtil.get32UUID();
		    info.setId(caseInfoId);
		}
		Subject user = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
		String id = shiroUser.id;
		User user1 = userService.findById(id);
		info.setCreatOrg(user1.getOrgId());

		caseInfoService.saveCaseInfo(info, slave);// 新增
		caseInfoId = info.getId();

		for (int i = 1; i <= 4; i++) {
		    CaseSend caseSend = new CaseSend();
		    caseSend.setCaseId(caseInfoId);
		    caseSend.setPlace("广东省潮州市烟草专卖局");
		    caseSend.setWay("直接送达");
		    caseSend.setCode(i + "");
		    caseSendService.save(caseSend);
		}

	    }
	    json.put("id", caseInfoId);
	    json.put("status", "1");
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
    * @Description: 跳转到涉案人员
    * @param @param request
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 下午6:43:55
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toEditPer")
    public String toEditPerv(HttpServletRequest request, Model model) {
	try {
	    String caseInfoId = request.getParameter("caseInfoId");
	    model = this.tagValue(request, model);
	    if (StringUtil.isNotEmpty(caseInfoId)) {
		CaseInvoPers pers = caseInvoPersService.findById(caseInfoId);
		model.addAttribute("pers", pers);
	    }
	    List<String> lics = licenceInfoService.findPerRespon("");
	    String type = request.getParameter("type");
	    model.addAttribute("type", type);
	    model.addAttribute("lics", lics);
	    model.addAttribute("caseInfoId", caseInfoId);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/caseInfo/caseinfoEditPerv";
    }

    /**
    * @Description: 查看涉案人员
    * @param @param request
    * @param @param model
    * @param @return
    * @author: yuyf
    * @date 2017年3月21日 下午6:43:55
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toPervView")
    public String toPervView(HttpServletRequest request, Model model) {
	try {
	    String caseInfoId = request.getParameter("caseInfoId");
	    model = this.tagValue(request, model);
	    if (StringUtil.isNotEmpty(caseInfoId)) {
		CaseInvoPers pers = caseInvoPersService.findById(caseInfoId);
		model.addAttribute("pers", pers);
	    }
	    List<String> lics = licenceInfoService.findPerRespon("");
	    String type = request.getParameter("type");
	    if (StringUtil.isEmpty(caseInfoId)) {
		type = "t";
	    }
	    model.addAttribute("type", type);
	    model.addAttribute("lics", lics);
	    model.addAttribute("caseInfoId", caseInfoId);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/caseInfo/caseinfoPervView";
    }

    /**
    * @Description: 保存涉案人员
    * @param @param pers
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 下午6:44:09
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("saveEditPer")
    @ResponseBody
    public String saveEditPer(CaseInvoPers pers) {
	JSONObject json = new JSONObject();
	String caseInfoId = pers.getCaseInfoId();
	try {
	    if (StringUtil.isNotEmpty(pers.getId())) {
		caseInvoPersService.updateCaseInvo(pers);// 修改
	    } else {
		caseInfoId = caseInvoPersService.saveCaseInvo(pers, caseInfoId);
	    }
	    json.put("caseInfoId", caseInfoId);
	    json.put("status", "1");
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
    * @Description: 跳转到涉案证据登记
    * @param @return
    * @author: lpy
    * @date 2017年2月23日 下午6:44:28
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toEditDence")
    public String toEditDence(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);
	int isSite = 0;
	if (StringUtil.isNotEmpty(caseInfoId)) {
	    CaseInfo caseInfo = caseInfoService.findById(caseInfoId);
	    if (caseInfo.getIsSite() != null) {
		isSite = caseInfo.getIsSite();
	    }
	}
	String type = request.getParameter("type");
	model.addAttribute("type", type);
	model.addAttribute("isSite", isSite);
	model.addAttribute("caseInfoId", caseInfoId);
	return "webmaster/caseInfo/caseinfoEditEvidence";
    }

    /**
    * @Description: 查看涉案证据
    * @param @return
    * @author: yuyf
    * @date 2017年3月21日 下午6:44:28
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toDenceView")
    public String toDenceView(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	model = this.tagValue(request, model);
	int isSite = 0;
	if (StringUtil.isNotEmpty(caseInfoId)) {
	    CaseInfo caseInfo = caseInfoService.findById(caseInfoId);
	    isSite = caseInfo.getIsSite();
	    model.addAttribute("tyke", "1");
	}
	model.addAttribute("isSite", isSite);
	model.addAttribute("caseInfoId", caseInfoId);
	return "webmaster/caseInfo/caseinfoEvidenceView";
    }

    /**
    * @Description: 根据许可证号码拿到许可证信息
    * @param @param licNo
    * @param @return
    * @author: lpy
    * @date 2017年3月1日 上午11:26:08
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("getLicValue")
    @ResponseBody
    public String getLicValue(String licNo) {
	JSONObject json = new JSONObject();
	try {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("licNo", licNo);
	    List<LicenceInfo> lic = licenceInfoService.findAll(map);
	    if (lic != null) {
		json.put("lic", lic.get(0));
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    e.printStackTrace();
	    json.put("status", 0);
	}
	return json.toString();
    }

    /**
    * @Description: 根据id删除
    * @param @param id
    * @param @return
    * @author: lpy
    * @date 2017年3月1日 上午11:27:00
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_REMOVE)
    @RequestMapping("remove")
    @ResponseBody
    public String remove(String id) {
	JSONObject json = new JSONObject();
	try {
	    if (StringUtil.isNotEmpty(id)) {
		String[] ids = { id };
		caseInfoService.remove(ids);
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    e.printStackTrace();
	    json.put("status", 0);
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 结案方法
    * @param @param caseId
    * @param @return
    * @author shizh
    * @date 2017年3月17日 下午5:33:18
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/endCase")
    @ResponseBody
    public String endCase(String caseId) {
	int res = 0;
	String[] docsIds = { "1", "2", "3", "4", "5", "6", "7", "8", "10" };
	String docsId = "";
	try {
	    for (int i = 0; i < docsIds.length; i++) {
		docsId = docsIds[i];
		CaseTempHistory caseTempHistory = new CaseTempHistory();
		caseTempHistory.setStatus(1);
		caseTempHistory.setCaseId(caseId);
		caseTempHistory.setDocsId(docsId);
		CaseTempHistory checkHistory = caseTempHistoryService.checkHistory(caseId, docsId);
		String content;
		if (checkHistory != null) {
		    content = checkHistory.getContent();
		} else {
		    content = templateService.GenerateOriginCont(docsId, caseId, 1);
		}
		caseTempHistory.setContent(content);
		res = caseTempHistoryService.saveCaseTemp(caseTempHistory);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject();
	if (res == 1) {
	    int endCase = caseInfoService.endCase(caseId);
	    jsonObject.put("result", endCase == 1);
	}
	jsonObject.put("result", res == 1);
	return jsonObject.toString();
    }

    /**
     * 
    * @Description: TODO(跳至"添加涉案卷烟"弹出框)
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年3月6日 下午7:46:45
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("toAddCigar")
    public String toAddCigar(HttpServletRequest request, Model model) {
	String caseInfoId = request.getParameter("caseInfoId");
	model.addAttribute("caseInfoId", caseInfoId);
	return "webmaster/caseInfo/caseinfoAddCigar";
    }

    /**
     * 
    * @Description: 导出案件汇总 
    * @param @param ids
    * @param @param sv
    * @param @param response
    * @param @throws IOException
    * @author: liy
    * @date 2017年4月14日 上午10:00:40
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/caseExcelExport")
    @ResponseBody
    public void userExcelExport(String[] ids, SearchDataVo sv, HttpServletResponse response) throws IOException {
	if (StringUtils.isNoneEmpty(ids)) {
	    // TODO 查询指定ID的数据并将其导出
	} else {
	    Subject user = SecurityUtils.getSubject();
	    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	    String id = shiroUser.orgId;
	    List<String> stringList = organizationService.findOrgIdById(id);
	    stringList.add(id);
	    sv.setOrgs(stringList);
	    List<GatherModel> licenceInfos = caseInfoService.findBySearchVo(sv);
	    if (licenceInfos.size() > 0) {
		for (int i = 0; i < licenceInfos.size(); i++) {
		    Double falseFine = (double) 0;
		    Double nonFine = (double) 0;
		    CaseInfo caseInfo = caseInfoService.findById(licenceInfos.get(i).getId());
		    if (caseInfo.getFakeRange() != null && caseInfo.getFakeRange() != 0
			    && licenceInfos.get(i).getFalsePrice() != null) {
			float t = (float) caseInfo.getFakeRange() / 100; // 转换成小数
			falseFine = new BigDecimal(licenceInfos.get(i).getFalsePrice()).multiply(new BigDecimal(t))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    }
		    if (caseInfo.getNonRange() != null && caseInfo.getNonRange() != 0
			    && licenceInfos.get(i).getNonPrice() != null) {
			float t = (float) caseInfo.getNonRange() / 100; // 转换成小数
			nonFine = new BigDecimal(licenceInfos.get(i).getNonPrice()).multiply(new BigDecimal(t))
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		    }
		    licenceInfos.get(i).setFalseFine(falseFine);
		    licenceInfos.get(i).setNonFine(nonFine);
		    licenceInfos.get(i).setSumFine(new BigDecimal(falseFine).add(new BigDecimal(nonFine))
			    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		    // 案件性质的判断
		    /*
		     * int max = (licenceInfos.get(i).getFalseSmokeNum() >
		     * licenceInfos.get(i).getNonSmokeNum()) ?
		     * licenceInfos.get(i).getFalseSmokeNum() :
		     * licenceInfos.get(i).getNonSmokeNum(); max = (max >
		     * licenceInfos.get(i).getSmuggledSmokeNum()) ? max :
		     * licenceInfos.get(i).getSmuggledSmokeNum();
		     * if(max==licenceInfos.get(i).getFalseSmokeNum()){
		     * licenceInfos.get(i).setCaseQualitative("假烟案件"); }else
		     * if(max==licenceInfos.get(i).getNonSmokeNum()){
		     * licenceInfos.get(i).setCaseQualitative("非烟案件"); }else
		     * if(max==licenceInfos.get(i).getSmuggledSmokeNum()){
		     * licenceInfos.get(i).setCaseQualitative("走私烟案件"); }
		     */
		}
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		new ExcelUtil<GatherModel>(GatherModel.class).exportExcel2("31",
			"数据汇总 " + "(" + dateFormater.format(date) + ")", "数据汇总", licenceInfos, response);
	    }
	}
    }

    /**
     * 
    * @Description: 导出非烟上报表
    * @param @param ids
    * @param @param sv
    * @param @param response
    * @param @throws IOException
    * @author: liy
    * @date 2017年4月14日 上午10:00:40
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/nonExcelExport")
    @ResponseBody
    public void nonExcelExport(String[] ids, SearchDataVo sv, HttpServletResponse response) throws IOException {
	if (StringUtils.isNoneEmpty(ids)) {
	    // TODO 查询指定ID的数据并将其导出
	} else {
	    Subject user = SecurityUtils.getSubject();
	    ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	    String id = shiroUser.orgId;
	    List<String> stringList = organizationService.findOrgIdById(id);
	    stringList.add(id);
	    sv.setOrgs(stringList);
	    List<NonExcel> licenceInfos = caseInfoService.findBySearch(sv);
	    if (licenceInfos.size() > 0) {
		for (int i = 0; i < licenceInfos.size(); i++) {
		    // CaseInfo caseInfo =
		    // caseInfoService.findById(licenceInfos.get(i).getId());
		    String autoGenerate = templateService.AutoGenerate("1", licenceInfos.get(i).getId(), 1);// TODO
		    autoGenerate = autoGenerate.replace("&nbsp;", " ").replace("<br>", "\r\n").replaceAll("<[^>]*>", "")
			    .replaceAll("[(/>)<]", "");
		    licenceInfos.get(i).setSummary(autoGenerate);
		}
	    }
	    SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy/MM/dd");
	    Date date = new Date();
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("licenceInfos", licenceInfos);
	    Map<String, String> map2 = new HashMap<String, String>();
	    new ExportExcelUtil().TempExport("35", map, map2, response);

	}
    }

    /**
    * 
    * @Description: 获取分页数据带业务
    * @param @param request
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:07:34
    * @throws
    */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/getPagingList")
    @ResponseBody
    public DateTablesResult<Map<String, Object>> getPagingList(HttpServletRequest request) {
	DateTablesResult<Map<String, Object>> dataTable = new DateTablesResult<Map<String, Object>>(request);
	Subject user = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	String id = shiroUser.orgId;
	List<String> stringList = organizationService.findOrgIdById(id);
	stringList.add(id);
	dataTable.setOrgs(stringList);
	return getBaseService().queryByPage(dataTable);
    }

    /**
     * 
    * @Description: 跳转到列表页面
    * @param @param req
    * @param @return
    * @author: yuyf
    * @date 
    * @throws
     */

    @RequiresPermissions(SysConstant.CASE_TOLIST)
    @RequestMapping("/toHisList")
    protected String toHisList(HttpServletRequest req) {
	return getPagePath().concat("HisList");
    }

    /**
    * @Description: 判断立案编号是否重复
    * @param @param regiNo
    * @param @return
    * @author: lpy
    * @date 2017年4月14日 下午4:35:52
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/isSame")
    @ResponseBody
    public String isSame(String regiNo) {
	JSONObject json = new JSONObject();
	try {
	    int i = caseInfoService.regiNoIsSame(regiNo);
	    if (i > 0) {
		json.put("isSame", true);
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    e.printStackTrace();
	    json.put("status", 0);
	}
	return json.toString();
    }

    /**
    * @Description: 判断涉案人员是否填写
    * @param @param caseInfoId
    * @param @return
    * @author: lpy
    * @date 2017年4月18日 下午2:51:23
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/isPersWrite")
    @ResponseBody
    public String isPersWrite(String caseInfoId) {
	JSONObject json = new JSONObject();
	try {
	    int i = caseInvoPersService.findCountByCaseId(caseInfoId);
	    if (i == 0) {
		json.put("write", 0);
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    e.printStackTrace();
	    json.put("status", 0);
	}
	return json.toString();
    }
    
    
    
    /**
     * @Description: 判断委托人是否填写
     * @param @param caseInfoId
     * @param @return
     * @author: lpy
     * @date 2017年4月18日 下午2:51:23
     * @throws
      */
     @RequiresPermissions(SysConstant.CASE_EDIT)
     @RequestMapping("/isWPerWrite")
     @ResponseBody
     public String isWPerWrite(String caseInfoId) {
 	JSONObject json = new JSONObject();
 	try {
 	    String wPer = caseInfoService.findCountByCaseId(caseInfoId);
 	    if ("".equals(wPer)||wPer==null) {
 		json.put("status", 0);
 	    }else{
 		json.put("status", 1);
 	    }
 	    
 	} catch (Exception e) {
 	    e.printStackTrace();
 	    json.put("status", 0);
 	}
 	return json.toString();
     }

    /**
     * 
    * @Description: 文书预览判断
    * @param @param request
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:14:11
    * @throws
     */
    public Model tagValue(HttpServletRequest request, Model model) {
	String tagId = request.getParameter("tagId");
	String tag = "";
	if (tagId == null || tagId == "") {
	    tag = "toInspect?notF=1";
	} else {
	    switch (tagId) {
	    case "1":
		tag = "toFiling?notF=1";
		break;
	    case "2":
		tag = "toInspect?notF=1";
		break;
	    case "3":
		tag = "toconclusion?notF=1";
		break;
	    case "4":
		tag = "toFinality?notF=1";
		break;
	    case "5":
		tag = "toExamine?notF=1";
		break;
	    case "6":
		tag = "toPrior?notF=1";
		break;
	    case "7":
		tag = "toPunish?notF=1";
		break;
	    case "8":
		tag = "toClosed?notF=1";
		break;
	    case "10":
		tag = "toEstimate?notF=1";
		break;
	    case "11":
		tag = "toEstimateDetail?notF=1";
		break;
	    case "12":
		tag = "toPricing?notF=1";
		break;
	    case "13":
		tag = "toSeizureList?notF=1";
		break;
	    case "21":
		tag = "toProof?flag=1";
		break;
	    case "22":
		tag = "toProof?flag=2";
		break;
	    case "23":
		tag = "toProof?flag=3";
		break;
	    case "24":
		tag = "toProof?flag=4";
		break;
	    case "32":
		tag = "toGangYin?notF=1";
		break;
	    default:
		tag = "toInspect?notF=1";
		break;
	    }
	}
	model.addAttribute("tag", tag);
	model.addAttribute("tagId", tagId);
	return model;
    }

    /**
     * 
    * @Description: 非烟条码明细数据更新
    * @param @param caseId
    * @author yuyf
    * @date 2017年5月11日 下午5:14:38
    * @throws
     */
    public void doNonCigar(String caseId) {
	List<CaseCigar> nons1 = caseCigarService.findByCaseId(caseId);
	List<String> cigarIds = new ArrayList<String>();
	List<String> nonIds = nonCigarService.findCigarIds(caseId);
	for (CaseCigar caseCigar : nons1) {
	    if ("非".equals(caseCigar.getInspectResult())) {
		String caseCigarId = caseCigar.getId();
		int number = caseCigar.getNumber();
		int nonNum = nonCigarService.findEachCigarTotal(caseId, caseCigarId);
		if (number < nonNum) {// 删记录
		    List<NonCigar> cigarList = nonCigarService.findByCaseIdAndCigarId(caseId, caseCigarId);
		    for (int i = 0; i < nonNum - number; i++) {
			String[] ids = { cigarList.get(i).getId() };
			nonCigarService.remove(ids);
		    }

		} else if (number > nonNum) {// 加记录
		    List<NonCigar> nonCigars = new ArrayList<NonCigar>();
		    for (int j = 0; j < number - nonNum; j++) {
			String name = caseCigar.getName();
			String cigarId = caseCigar.getId();
			NonCigar non = new NonCigar();
			non.setName(name == null ? "" : name);
			non.setCigarId(cigarId == null ? "" : cigarId);
			non.setCaseId(caseId);
			non.setNumber(1);
			nonCigars.add(non);

		    }
		    nonCigarService.saveAll(nonCigars, caseId);
		}
		cigarIds.add(caseCigar.getId());
	    }

	}
	for (String string : nonIds) {
	    boolean flag = cigarIds.contains(string);
	    if (!flag) {
		nonCigarService.removeByCaseIdCigarId(caseId, string);
	    }
	}

    }
}
