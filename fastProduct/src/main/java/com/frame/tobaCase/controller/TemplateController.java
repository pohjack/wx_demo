/**    
* @Package com.frame.tobaCase.controller
* @Description: 模板控制层
* @author: shizh
* @date 2017年2月13日 下午4:23:26
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.EHCacheUtil;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.BaseTemplate;
import com.frame.tobaCase.entity.BaseTemplateSlave;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.CaseSend;
import com.frame.tobaCase.entity.CaseTempHistory;
import com.frame.tobaCase.entity.CigarInfo;
import com.frame.tobaCase.entity.DictionaryData;
import com.frame.tobaCase.entity.DocsTemplateSlave;
import com.frame.tobaCase.entity.NonCigar;
import com.frame.tobaCase.service.IBaseTemplateService;
import com.frame.tobaCase.service.IBaseTemplateSlaveService;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseGangYinService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICaseInfoSlaveService;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.frame.tobaCase.service.ICaseSendService;
import com.frame.tobaCase.service.ICaseTempHistoryService;
import com.frame.tobaCase.service.ICigarInfoService;
import com.frame.tobaCase.service.IDictionaryDataService;
import com.frame.tobaCase.service.IDocsTemplateService;
import com.frame.tobaCase.service.IDocsTemplateSlaveService;
import com.frame.tobaCase.service.INonCigarService;
import com.frame.tobaCase.service.ITemplateService;
import com.frame.tobaCase.utils.ExportExcelUtil;
import com.frame.tobaCase.utils.ExportWord;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController<DictionaryData, String> {

    @Resource
    private IDocsTemplateService docsTemplateService;
    @Resource
    private IDocsTemplateSlaveService docsTemplateSlaveService;
    @Resource
    private IBaseTemplateSlaveService baseTemplateSlaveService;
    @Resource
    private IBaseTemplateService baseTemplateService;
    @Resource
    private ICaseCigarService caseCigarService;
    @Resource
    private ICaseInfoService caseInfoService;
    @Resource
    private IDictionaryDataService dictionaryDataService;
    @Resource
    private ITemplateService templateService;
    @Resource
    private ICaseTempHistoryService caseTempHistoryService;
    @Resource
    private ICaseInfoSlaveService caseInfoSlaveService;
    @Resource
    private ICaseInvoPersService caseInvoPersService;
    @Resource
    private ICigarInfoService cigarInfoService;
    @Resource
    private ICaseGangYinService caseGangYinService;
    @Resource
    private INonCigarService nonCigarService;
    @Resource
    private ICaseSendService caseSendService;

    @Override
    protected IBaseService<DictionaryData, String> getBaseService() {
	return dictionaryDataService;
    }

    /**
     * 
     * @Description: 跳转到测试页面 @param @return @author shizh @date 2017年2月15日
     * 下午7:37:25 @throws
     */
    @RequiresPermissions(SysConstant.TEMP_VIEW)
    @RequestMapping("/test")
    public String toTest() {
	return "webmaster/test/templateTest";
    }

    /**
     * 
     * @Description: 跳转到模板管理页面 @param @return @author shizh @date 2017年2月20日
     * 上午11:53:46 @throws
     */
    @RequiresPermissions(SysConstant.TEMP_VIEW)
    @RequestMapping("/toTemplate")
    public String toBaseModelManager(Model model) {
	List<BaseTemplate> baseTemps = baseTemplateService.findAll();
	List<BaseTemplate> baseTemplates = new ArrayList<BaseTemplate>();
	for (BaseTemplate baseTemplate : baseTemps) {
	    StringBuffer preview = templateService.BaseTempGenerate(baseTemplate.getContent(), "1", "7", "");
	    baseTemplate.setContent(preview.toString());
	    baseTemplates.add(baseTemplate);
	}
	model.addAttribute("baseTemps", baseTemplates);
	return "webmaster/docTemp/docTemplate";
    }

    /**
     * 
     * @Description: 获取文书模板内容 @param @param modelNo @param @return @author
     * shizh @date 2017年3月10日 上午11:30:47 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/getTempInfo")
    @ResponseBody
    public String getTempInfo(String modelNo, String tags) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNotEmpty(modelNo)) {
	    int tag = StringUtils.isEmpty(tags) ? 1 : Integer.parseInt(tags);
	    List<Map<String, Object>> baseTemplates = baseTemplateService.findBaseInfo(modelNo, tag);
	    String autoGenerate = templateService.AutoGenerate(modelNo, "1", tag);
	    jsonObject.put("baseTemplates", baseTemplates);
	    jsonObject.put("autoGenerate", autoGenerate);
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 重新生成方法 @param @param caseId @param @param
     * docsId @param @return @author shizh @date 2017年3月13日 下午4:17:15 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/rebuildSysTemp")
    @ResponseBody
    public String rebuildSysTemp(String caseId, String docsId) {
	int res = 0;
	try {
	    CaseTempHistory caseTempHistory = new CaseTempHistory();
	    caseTempHistory.setStatus(1);
	    caseTempHistory.setCaseId(caseId);
	    caseTempHistory.setDocsId(docsId);
	    String content = templateService.GenerateOriginCont(docsId, caseId, 1);// TODO
										   // 根据不同的表格栏位重新生成
	    caseTempHistory.setContent(content);
	    res = caseTempHistoryService.saveCaseTemp(caseTempHistory);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("res", res == 1);
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 重新生成方法 @param @param caseId @param @param
     * docsId @param @return @author shizh @date 2017年3月13日 下午4:17:15 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/savaToHis")
    @ResponseBody
    public String savaToHis(String caseId, String docsId) {
	int res = 0;
	try {
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
	} catch (Exception e) {
	    e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("res", res == 1);
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 基础模板在文书模板中的排序 @param @param baseId @param @param
     * baseIdAfter @param @param modelNo @param @return @author shizh @date
     * 2017年3月10日 下午4:11:43 @throws
     */
    @RequiresPermissions(SysConstant.TEMP_VIEW)
    @RequestMapping("/changeSort")
    @ResponseBody
    public String changeSort(String baseId, String baseIdAfter) {
	JSONObject jsonObject = new JSONObject();
	int changeSort = docsTemplateSlaveService.changeSort(baseId, baseIdAfter);
	jsonObject.put("result", changeSort == 1);
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 跳转到模板管理页面 @param @return @author shizh @date 2017年2月20日
     * 上午11:53:46 @throws
     */
    @RequiresPermissions(SysConstant.BASETEMP_VIEW)
    @RequestMapping("/toBaseTemp")
    public String toBaseTemp(Model model) {
	List<BaseTemplate> baseTemps = baseTemplateService.findAll();
	model.addAttribute("baseTemplates", baseTemps);
	return "webmaster/docTemp/template";
    }

    /**
     * 
     * @Description: 新增基础模板到文书模板 @param @param baseId @param @param
     * modelNo @param @return @author shizh @date 2017年3月10日 下午2:21:23 @throws
     */
    @RequiresPermissions(SysConstant.TEMP_VIEW)
    @RequestMapping("/addBaseToDoc")
    @ResponseBody
    public String addBaseToDoc(String baseId, String modelNo, String tags) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNotEmpty(baseId) && StringUtils.isNoneEmpty(modelNo)) {
	    int tag = StringUtils.isEmpty(tags) ? 1 : Integer.parseInt(tags);
	    BaseTemplate baseTemplate = baseTemplateService.findById(baseId);
	    StringBuffer baseTempGenerate = templateService.BaseTempGenerate(baseTemplate.getContent(), "1", modelNo,
		    baseId);
	    jsonObject.put("autoGenerate", baseTempGenerate.toString());
	    jsonObject.put("baseTemplate", baseTemplate);
	    DocsTemplateSlave dSlave = new DocsTemplateSlave();
	    dSlave.setBaseTempId(baseId);
	    dSlave.setMasterId(modelNo);
	    dSlave.setType(tag);
	    int res = docsTemplateSlaveService.addBaseToDoc(dSlave);
	    jsonObject.put("result", res == 1);
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 新增基础模板到文书模板 @param @param baseId @param @param
     * modelNo @param @return @author shizh @date 2017年3月10日 下午2:21:23 @throws
     */
    @RequiresPermissions(SysConstant.TEMP_VIEW)
    @RequestMapping("/rempveBaseFromDoc")
    @ResponseBody
    public String rempveBaseFromDoc(String baseId) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNotEmpty(baseId)) {
	    String[] ids = { baseId };
	    Integer res = docsTemplateSlaveService.remove(ids);
	    jsonObject.put("result", res == 1);
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 获取文书模板包含的子模板 @param @param modelNo @param @return @author
     * shizh @date 2017年2月24日 下午4:02:38 @throws
     */
    /*
     * @RequestMapping("/getBaseTemplate")
     * 
     * @ResponseBody//TODO 删除本方法 public String getBaseTemplate(String modelNo){
     * JSONObject jsonObject = new JSONObject();
     * if(StringUtils.isNotEmpty(modelNo)){ List<BaseTemplate> baseTemplates =
     * baseTemplateService.findBaseByDocId(modelNo); String autoGenerate =
     * templateService.AutoGenerate(modelNo, "1");
     * jsonObject.put("baseTemplates", baseTemplates);
     * jsonObject.put("autoGenerate", autoGenerate); } return
     * jsonObject.toString(); }
     */

    /**
     * 
     * @Description: 获取子模板预览 @param @param id @param @param
     * modelNo @param @return @author shizh @date 2017年3月6日 下午2:31:41 @throws
     */
    @RequiresPermissions(SysConstant.BASETEMP_VIEW)
    @RequestMapping("/getSubTemplate")
    @ResponseBody
    public String getSubTemplate(String id, String modelNo) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNotEmpty(id) && StringUtils.isNoneEmpty(modelNo)) {
	    BaseTemplate baseTemplate = baseTemplateService.findById(id);
	    StringBuffer baseTempGenerate = templateService.BaseTempGenerate(baseTemplate.getContent(), "1", modelNo,
		    "");
	    jsonObject.put("autoGenerate", baseTempGenerate.toString());
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 跳转到子模板添加页面 @param @return @author shizh @date 2017年2月24日
     * 下午5:21:42 @throws
     */
    @RequiresPermissions(SysConstant.BASETEMP_VIEW)
    @RequestMapping("/toTempAdd")
    public String toTempAdd(String tid, String modelNo, Model model) {
	if (StringUtils.isNotEmpty(tid) && !"undefined".equals(tid)) {
	    BaseTemplate baseTemplate = baseTemplateService.findById(tid);
	    List<BaseTemplateSlave> findByMasterId = baseTemplateSlaveService.findByMasterId(tid);
	    EHCacheUtil.initCacheManager().getCache("baseSlave");// 根据配置文件
								 // ehcahce.xml
								 // 的缓存名字获取缓存对象
	    EHCacheUtil.put("baseSlave", findByMasterId);// 设置缓存
	    StringBuffer baseTempGenerate = templateService.BaseTempGenerate(baseTemplate.getContent(), "1", modelNo,
		    "");
	    // String baseTempEdit = templateService.BaseTempEdit(tid, "1",
	    // modelNo);
	    model.addAttribute("preview", baseTempGenerate.toString());
	    model.addAttribute("name", baseTemplate.getName());
	    /*
	     * String templateComb = baseTemplateSlaveService.templateComb(tid);
	     */
	    model.addAttribute("edit", baseTemplate.getContent());
	} else {
	    tid = UUIDUtil.get32UUID();
	}
	model.addAttribute("id", tid);
	model.addAttribute("modelNo", modelNo);
	return "webmaster/docTemp/template_add";
    }

    /**
     * 
     * @Description: 保存修改到基础模板从表 @param @param
     * baseTemplateSlave @param @return @author shizh @date 2017年2月28日
     * 下午4:37:28 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/addToBase")
    @ResponseBody
    public String addToBase(BaseTemplateSlave baseTemplateSlave) {
	Integer save = baseTemplateSlaveService.save(baseTemplateSlave);
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("result", save.intValue() == 1);
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 重新生成预览 @param @param
     * baseTemplateSlave @param @return @author shizh @date 2017年2月28日
     * 下午4:37:28 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/rebuildPrev")
    @ResponseBody
    public String rebuildPrev(String content, String modelNo) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNoneEmpty(content) && StringUtils.isNotEmpty(modelNo)) {
	    StringBuffer baseTempGenerate = templateService.BaseTempGenerate(content, "1", modelNo, "");
	    jsonObject.put("preview", baseTempGenerate.toString());
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 删除基础模板从表的记录 @param @param id @param @return @author
     * shizh @date 2017年2月28日 下午5:03:13 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/removeBaseTemp")
    @ResponseBody
    public String removeBaseTemp(String id) {
	JSONObject jsonObject = new JSONObject();
	if (StringUtils.isNotEmpty(id)) {
	    String[] ids = { id };
	    Integer remove = baseTemplateSlaveService.remove(ids);
	    jsonObject.put("result", remove.intValue() == 1);
	}
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 数据回滚 @param @param tid @param @return @author shizh @date
     * 2017年3月1日 上午9:40:00 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @SuppressWarnings("unchecked")
    @RequestMapping("/rollBack")
    @ResponseBody
    public String rollBack(String tid) {
	if (StringUtils.isNotEmpty(tid)) {
	    EHCacheUtil.initCacheManager().getCache("baseSlave");// 根据配置文件
								 // ehcahce.xml
								 // 的缓存名字获取缓存对象
	    List<BaseTemplateSlave> baseTemplateSlaves = (List<BaseTemplateSlave>) EHCacheUtil.get("baseSlave");
	    baseTemplateSlaveService.removeTempData(tid);
	    if (baseTemplateSlaves != null && baseTemplateSlaves.size() > 0) {
		for (BaseTemplateSlave baseTemplateSlave : baseTemplateSlaves) {
		    baseTemplateSlaveService.save(baseTemplateSlave);
		}
	    }
	    EHCacheUtil.remove("baseSlave");
	}
	return "";
    }

    /**
     * 
     * @Description: 基础模板主表更新 @param @param tid 主表ID @param @param content
     * 模板内容 @param @return @author shizh @date 2017年3月1日 下午12:10:14 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/saveBTEdit")
    @ResponseBody
    public boolean saveBTEdit(BaseTemplate baseTemplate) {
	String baseTempId = baseTemplate.getId();
	BaseTemplate template = baseTemplateService.findById(baseTempId);
	int updateBaseTemp = 0;
	if (template != null) {
	    updateBaseTemp = baseTemplateService.update(baseTemplate);
	} else {
	    updateBaseTemp = baseTemplateService.save(baseTemplate);
	}
	return updateBaseTemp == 1;
    }

    /**
     * 
     * @Description: 案件内容编辑跳转方法 @param @param caseId 案件ID @param @param modelNo
     * 文书模板ID @param @return @author shizh @date 2017年3月2日 下午5:08:51 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toCaseDocsEdit")
    public String toCaseDocsEdit(String caseId, String modelNo, String tags, Model model) {
	if (StringUtils.isNotEmpty(caseId) && StringUtils.isNotEmpty(modelNo)) {
	    int tag = StringUtils.isEmpty(tags) ? 1 : Integer.parseInt(tags);
	    String autoGenerate = templateService.AutoGenerate(modelNo, caseId, tag);
	    model.addAttribute("preview", autoGenerate);
	    CaseTempHistory checkHistory = caseTempHistoryService.checkHistory(caseId, modelNo);
	    String content = "";
	    if (checkHistory != null) {
		content = checkHistory.getContent();
	    } else {
		content = templateService.GenerateOriginCont(modelNo, caseId, tag);
	    }
	    model.addAttribute("edit", content);
	}
	return "webmaster/docTemp/caseTempEdit";
    }

    /**
     * 
     * @Description: 保存案件对应的文书模板到历史记录表 @param @param caseId @param @param
     * modelNo @param @param content @param @return @author shizh @date
     * 2017年3月3日 下午12:04:13 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/saveCaseTemp")
    @ResponseBody
    public String saveCaseTemp(CaseTempHistory caseTempHistory) {
	int res = 0;
	try {
	    caseTempHistory.setStatus(1);
	    res = caseTempHistoryService.saveCaseTemp(caseTempHistory);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("res", res == 1);
	return jsonObject.toString();
    }

    /**
     * 
     * @Description: 导出Excel @param @param caseId @param @param
     * modelNo @param @param response @param @return @param @throws
     * FileNotFoundException @param @throws IOException @author shizh @date
     * 2017年3月7日 下午5:31:02 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/exportExcel")
    @ResponseBody
    public void exportExcel(String caseId, String modelNo, String tags, HttpServletResponse response)
	    throws FileNotFoundException, IOException {
	Map<String, Object> caseInfo = caseInfoService.findByKey(caseId);
	Map<String, Object> findByCaseId = caseInfoSlaveService.findByCaseId(caseId);
	Map<String, Object> findByCaseId2 = caseInvoPersService.findByCaseId(caseId);
	if (findByCaseId != null) {
	    caseInfo.putAll(findByCaseId);
	}
	if (findByCaseId2 != null) {
	    caseInfo.putAll(findByCaseId2);
	}
	tags = StringUtils.isEmpty(tags) ? "1" : tags;
	String[] split = tags.split(",");
	Map<String, String> map = new HashMap<String, String>();
	for (int i = 0; i < split.length; i++) {
	    String autoGenerate = templateService.AutoGenerate(modelNo, caseId, Integer.parseInt(split[i]));// TODO
	    autoGenerate = autoGenerate.replace("&nbsp;", " ").replace("<br>", "\r\n").replaceAll("<[^>]*>", "")
		    .replaceAll("[(/>)<]", "");
	    map.put("tag" + split[i], autoGenerate);
	}
	new ExportExcelUtil().TempExport(modelNo, caseInfo, map, response);
    }

    /**
     * 
     * @Description: 导出Excel方法2 @param @param caseId @param @param
     * modelNo @param @param response @param @throws
     * FileNotFoundException @param @throws IOException @author yuyf @date
     * 2017年3月24日 下午2:31:02 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/exportExcel2")
    @ResponseBody
    public void exportExcel2(String caseId, String modelNo, HttpServletRequest request, HttpServletResponse response)
	    throws FileNotFoundException, IOException {
	Map<String, Object> caseInfo = caseInfoService.findByKey(caseId);
	CaseInfo caseInfo2 = caseInfoService.findById(caseId);
	Map<String, Object> per = caseInvoPersService.findByCaseId(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	List<CaseCigar> cigarList = caseCigarService.findByCaseId(caseId);
	if ("11".equals(modelNo)) {
	    int total = 0;
	    for (int i = 0; i < cigarList.size(); i++) {
		// CigarInfo
		// cigarInfo=cigarInfoService.findById(cigarList.get(i).getCigarId());
		total += cigarList.get(i).getNumber();
		// cigarList.get(i).setBarCode(cigarInfo.getBarCode());
	    }
	    caseInfo.put("total", total);
	}
	Map<String, Object> map = new HashMap<String, Object>();
	if ("32".equals(modelNo)) {
	    List<CaseGangYin> gangYins = caseGangYinService.findByCaseId(caseId);
	    map.put("gangYins", gangYins);
	}
	if ("34".equals(modelNo)) {
	    List<NonCigar> nonCigars = nonCigarService.findByCaseId(caseId);
	    map.put("nonCigars", nonCigars);
	}
	List<CaseCigar> caseCigar = caseCigarService.findByCaseId(caseId);

	List<CaseCigar> jiaCigar = new ArrayList<CaseCigar>();
	List<CaseCigar> siCigar = new ArrayList<CaseCigar>();
	for (int i = 0; i < caseCigar.size(); i++) {
	    if ("假".equals(caseCigar.get(i).getInspectResult())) {
		jiaCigar.add(caseCigar.get(i));
		// jiajson.put("jia"+i, caseCigar.get(i));

	    }
	    ;
	    if ("私".equals(caseCigar.get(i).getInspectResult())) {
		siCigar.add(caseCigar.get(i));
		// sijson.put("si"+i, caseCigar.get(i));
	    }
	    ;
	}
	int size = jiaCigar.size() > siCigar.size() ? jiaCigar.size() : siCigar.size();
	caseInfo.put("rows", size);
	caseInfo.put("caseCigar", caseCigar);
	caseInfo.put("jia", jiaCigar);
	caseInfo.put("si", siCigar);
	map.put("caseSlave", caseSlave);
	map.put("cigarList", cigarList);
	caseInfo.put("caseInfo", caseInfo2);
	if (per != null) {
	    map.putAll(per);
	}
	caseInfo.putAll(map);
	Map<String, String> map2 = new HashMap<String, String>();
	new ExportExcelUtil().TempExport(modelNo, caseInfo, map2, response);
    }

    /**
     * 
     * @Description: 送达回证 @param @param caseId @param @param
     * modelNo @param @param request @param @param response @param @throws
     * FileNotFoundException @param @throws IOException @author yuyf @date
     * 2017年4月12日 下午2:21:47 @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/exportProof")
    @ResponseBody
    public void exportProof(String caseId, String modelNo, HttpServletRequest request, HttpServletResponse response)
	    throws FileNotFoundException, IOException {
	CaseInfo caseInfo = caseInfoService.findById(caseId);
	Map<String, Object> per = caseInvoPersService.findByCaseId(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	Map<String, Object> map = new HashMap<String, Object>();
	if (caseInfo != null) {
	    map.put("caseInfo", caseInfo);
	}
	if (caseSlave != null) {
	    map.put("caseSlave", caseSlave);
	}

	if (per != null) {
	    map.putAll(per);
	}
	String flag = request.getParameter("flag");
	CaseSend caseSend = caseSendService.findByCaseIdAndCode(caseId, flag);
	map.put("place", caseSend.getPlace());
	map.put("way", caseSend.getWay());
	map.put("wenhao", caseSend.getWenhao());
	Map<String, String> map2 = new HashMap<String, String>();
	new ExportExcelUtil().TempExport(modelNo, map, map2, response);
    }

    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/exportWord")
    @ResponseBody
    public void exportWord(String caseId, String modelNo, HttpServletRequest request, HttpServletResponse response)
	    throws Exception {
	Map<String, Object> caseInfo = caseInfoService.findByKey(caseId);
	List<CaseCigar> cigarList = caseCigarService.findByCaseId(caseId);
	if ("11".equals(modelNo)) {
	    int total = 0;
	    for (int i = 0; i < cigarList.size(); i++) {
		// CigarInfo
		// cigarInfo=cigarInfoService.findById(cigarList.get(i).getCigarId());
		total += cigarList.get(i).getNumber();
		// cigarList.get(i).setBarCode(cigarInfo.getBarCode());
	    }
	    caseInfo.put("total", total);
	}
	caseInfo.put("cigarList", cigarList);
	new ExportWord().createSimpleTable("估价明细表", caseInfo, response);
	;

    }
}
