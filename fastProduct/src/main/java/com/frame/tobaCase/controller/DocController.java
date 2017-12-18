/**    
* @Title: DocController.java
* @Package com.frame.tobaCase.controller
* @Description: 文书controller
* @author: shizh
* @date 2017年2月23日 下午7:05:35
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.entity.CaseInfoSlave;
import com.frame.tobaCase.entity.CaseSend;
import com.frame.tobaCase.entity.DictionaryData;
import com.frame.tobaCase.entity.DocsTemplate;
import com.frame.tobaCase.entity.Law;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseGangYinService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICaseInfoSlaveService;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.frame.tobaCase.service.ICaseSendService;
import com.frame.tobaCase.service.ICigarInfoService;
import com.frame.tobaCase.service.IDictionaryDataService;
import com.frame.tobaCase.service.ILawService;
import com.frame.tobaCase.service.ITemplateService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/docTemplate")
public class DocController extends BaseController<DocsTemplate, String> {

    @Resource
    private ICaseInfoService caseInfoService;
    @Resource
    private ICaseInfoSlaveService caseInfoSlaveService;
    @Resource
    private ICaseInvoPersService caseInvoPersService;
    @Resource
    private ITemplateService templateService;
    @Resource
    private ILawService lawService;
    @Resource
    private IDictionaryDataService dictionaryDataService;
    @Resource
    private ICaseCigarService caseCigarService;
    @Resource
    private ICigarInfoService cigarInfoService;
    @Resource
    private ICaseGangYinService caseGangYinService;
    @Resource
    private ICaseSendService caseSendService;

    @Override
    protected IBaseService<DocsTemplate, String> getBaseService() {
	return null;
    }

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");

    /**
     * 
    * @Description: 跳转到检查勘验笔录模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toInspect")
    public String toInspect(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/inspect";
    }

    /**
     * 
    * @Description: 没收财物清单
    * @param @param caseId
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年4月12日 下午3:36:35
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toSeizureList")
    public String toSeizureList(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	CaseInfo info = caseInfoService.findById(caseId);
	Map<String, Object> per = caseInvoPersService.findByCaseId(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	List<CaseCigar> caseCigar = caseCigarService.findByCaseId(caseId);
	JSONObject jiajson = new JSONObject();
	JSONObject sijson = new JSONObject();
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
	jiajson.put("jia", jiaCigar);
	sijson.put("si", siCigar);
	int size = jiaCigar.size() > siCigar.size() ? jiaCigar.size() : siCigar.size();
	model.addAttribute("rows", size);
	model.addAttribute("caseCigar", caseCigar);
	model.addAttribute("jia", jiajson);
	model.addAttribute("si", sijson);
	model.addAttribute("info", info);
	model.addAttribute("caseSlave", caseSlave);
	model.addAttribute("per", per);
	return "webmaster/docTemp/seizureList";
    }

    /**
        * 
       * @Description: 跳转到钢印页面
       * @param @return
       * @author: yuyf
       * @date 2017年3月22日 下午7:18:26
       * @throws
        */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toGangYin")
    public String toAccount(HttpServletRequest request, Model model) {
	String caseId = request.getParameter("caseId");
	this.doSiCigar(caseId);
	List<CaseGangYin> caseGangYin = caseGangYinService.findByCaseId(caseId);
	model.addAttribute("caseId", caseId);
	model.addAttribute("caseGangYin", caseGangYin);
	return "webmaster/docTemp/gangYin";
    }

    /**
     * 
    * @Description: 涉案烟草专卖品核价表
    * @param @param caseId
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年4月12日 下午3:36:35
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toPricing")
    public String toPricing(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	CaseInfo info = caseInfoService.findById(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	model.addAttribute("info", info);
	model.addAttribute("caseSlave", caseSlave);
	List<CaseCigar> caseCigar = caseCigarService.findByCaseId(caseId);
	model.addAttribute("caseCigar", caseCigar);

	return "webmaster/docTemp/pricing";
    }

    /**
     * 
    * @Description: 跳到送达回证页面
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年4月12日 上午9:37:30
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toProof")
    public String toProof(String caseId, Model model, String flag) {

	CaseInfo caseInfo = caseInfoService.findById(caseId);
	Map<String, Object> per = caseInvoPersService.findByCaseId(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	String proofName = "";
	String regiNo = caseInfo.getRegiNo();
	String songNo = regiNo.replace("立", "送");
	String chuNo = regiNo.replace("立", "处");

	switch (flag) {
	case "1":
	    proofName = "广东省潮州市烟草专卖局先行登记保存证据处理通知书";
	    songNo = songNo.replace("号", "-1号");
	    break;
	case "2":
	    proofName = "广东省潮州市烟草专卖局涉案烟草专卖品鉴定结论告知书";
	    songNo = songNo.replace("号", "-2号");
	    break;
	case "3":
	    proofName = "广东省潮州市烟草专卖局行政处罚事先告知书";
	    songNo = songNo.replace("号", "-3号");
	    if (caseSlave != null) {
		model.addAttribute("time",
			caseSlave.getInInformDate() == null ? "" : sdf.format(caseSlave.getInInformDate()));
	    }
	    break;
	case "4":
	    proofName = "广东省潮州市烟草专卖局行政处罚决定书";
	    songNo = songNo.replace("号", "-4号");
	    if (caseSlave != null) {
		model.addAttribute("time",
			caseSlave.getPenaltyDate() == null ? "" : sdf.format(caseSlave.getPenaltyDate()));
	    }

	    break;
	default:
	    break;
	}
	CaseSend caseSend = caseSendService.findByCaseIdAndCode(caseId, flag);
	model.addAttribute("caseSend", caseSend);
	model.addAttribute("caseId", caseId);
	model.addAttribute("per", per);
	model.addAttribute("proofName", proofName);
	model.addAttribute("songNo", songNo);
	model.addAttribute("chuNo", chuNo);
	model.addAttribute("flag", flag);
	return "webmaster/docTemp/proof";
    }

    /**
     * 
    * @Description: AJAX获取检查勘验笔录信息
    * @param id	案件id
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:53:37
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/getInfo")
    @ResponseBody
    public String getInfo(String caseId, String modelNo, String tags) {
	Map<String, Object> manInfo = caseInvoPersService.findByCaseId(caseId);
	CaseInfo caseInfo = caseInfoService.findById(caseId);
	CaseInfoSlave caseSlave = caseInfoSlaveService.findByMasterId(caseId);
	String chengPers=caseSlave.getUserName().replace(",", "、");
	caseSlave.setUserName(chengPers);
	if (StringUtils.isEmpty(tags)) {
	    tags = "1";
	}
	String[] split = tags.split(",");
	Map<String, String> map = new HashMap<String, String>();
	for (int i = 0; i < split.length; i++) {
	    String autoGenreate = templateService.AutoGenerate(modelNo, caseId, Integer.parseInt(split[i]));
	    map.put("tag" + split[i], autoGenreate);
	}
	JSONObject jsonObject = new JSONObject();
	jsonObject.put("result", manInfo);
	jsonObject.put("caseInfo", caseInfo);
	jsonObject.put("caseSlave", caseSlave);
	jsonObject.put("map", map);
	return jsonObject.toString();
    }

    /**
     * 
    * @Description: 跳转到案件调查终结报告模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toFinality")
    public String toFinality(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/finality";
    }

    /**
     * 
    * @Description: 跳转到鉴定结论
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toconclusion")
    public String toconclusion(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/conclusion";
    }

    /**
     * 
     * @Description: 跳转到行政处罚事先告知
     * @param @param model
     * @param @return
     * @author shizh
     * @date 2017年2月24日 上午9:49:47
     * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toPrior")
    public String toPrior(String caseId, Model model) {
	DictionaryData postAgency = dictionaryDataService.findByCode("postAgency");
	model.addAttribute("postAgency", postAgency.getValue());
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/prior";
    }

    /**
     * 
     * @Description: 跳转到立案报告模板
     * @param @param model
     * @param @return
     * @author shizh
     * @date 2017年2月24日 上午9:49:47
     * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toFiling")
    public String toFiling(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	List<Law> laws = lawService.findAll();
	model.addAttribute("laws", laws);
	return "webmaster/docTemp/filing";
    }

    /**
     * 
    * @Description: 更改案由
    * @param @param caseId
    * @param @param caseNo
    * @param @param caseCause
    * @param @param source
    * @param @return
    * @author shizh
    * @date 2017年3月13日 上午10:30:40
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/updateCaseCause")
    @ResponseBody
    public String updateCaseCause(String caseId, String caseNo, String caseCause, String source) {
	JSONObject jsonObject = new JSONObject();
	int result = caseInfoService.updateCaseCauseInfo(caseId, caseNo, caseCause, source);
	jsonObject.put("result", result == 1);
	return jsonObject.toString();
    }

    /**
     * 
    * @Description: 跳转到案件处理审批模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toExamine")
    public String toExamine(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/examine";
    }

    /**
     * 
    * @Description: 跳转到卷烟价值估算模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toEstimate")
    public String toEstimate(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/estimate";
    }

    /**
     * 
    * @Description: 跳转到估价明细表
    * @param @param caseId
    * @param @param model
    * @param @return
    * @author yuyf
    * @date 2017年4月26日 下午12:10:12
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toEstimateDetail")
    public String toEstimateDetail(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	CaseInfo info = caseInfoService.findById(caseId);
	model.addAttribute("info", info);
	List<CaseCigar> caseCigar = caseCigarService.findByCaseId(caseId);
	int total = 0;
	for (int i = 0; i < caseCigar.size(); i++) {
	    // CigarInfo
	    // cigarInfo=cigarInfoService.findById(caseCigar.get(i).getCigarId());
	    total += caseCigar.get(i).getNumber();
	    // caseCigar.get(i).setBarCode(cigarInfo.getBarCode());
	}
	model.addAttribute("caseCigar", caseCigar);
	model.addAttribute("total", total);
	return "webmaster/docTemp/estimateDetail";
    }

    /**
     * 
    * @Description: 跳转到行政处罚决定书模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toPunish")
    public String toPunish(String caseId, Model model) {
	DictionaryData postAgency = dictionaryDataService.findByCode("postAddr");
	model.addAttribute("postAgency", postAgency.getValue());
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/punish";
    }

    /**
     * 
    * @Description: 跳转到结案报告模板
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toClosed")
    public String toClosed(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/closed";
    }
    
    /**
     * 
    * @Description: 跳转到案卷封页
    * @param @param model
    * @param @return
    * @author shizh
    * @date 2017年2月24日 上午9:49:47
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/toClosure")
    public String toClosure(String caseId, Model model) {
	model.addAttribute("caseId", caseId);
	return "webmaster/docTemp/closure";
    }
    
    

    /**
     * 
    * @Description:走私钢印--数据实时更新
    * @param @param caseId
    * @author yuyf
    * @date 2017年5月11日 下午5:21:32
    * @throws
     */
    public void doSiCigar(String caseId) {
	List<CaseCigar> nons1 = caseCigarService.findByCaseId(caseId);
	List<String> cigarIds = new ArrayList<String>();
	List<String> nonIds = caseGangYinService.findCigarIds(caseId);
	for (CaseCigar caseCigar : nons1) {
	    if ("私".equals(caseCigar.getInspectResult())) {
		String caseCigarId = caseCigar.getId();
		int number = caseCigar.getNumber();
		int nonNum = caseGangYinService.findEachCigarTotal(caseId, caseCigarId);
		if (number < nonNum) {// 删记录
		    List<CaseGangYin> cigarList = caseGangYinService.findByCaseIdAndCigarId(caseId, caseCigarId);
		    for (int i = 0; i < nonNum - number; i++) {
			String[] ids = { cigarList.get(i).getId() };
			caseGangYinService.remove(ids);
		    }

		} else if (number > nonNum) {// 加记录
		    List<CaseGangYin> nonCigars = new ArrayList<CaseGangYin>();
		    for (int j = 0; j < number - nonNum; j++) {
			String name = caseCigar.getName();
			String cigarId = caseCigar.getId();
			CaseGangYin non = new CaseGangYin();
			non.setName(name == null ? "" : name);
			non.setCigarId(cigarId == null ? "" : cigarId);
			non.setCaseId(caseId);
			non.setNumber(1);
			nonCigars.add(non);

		    }
		    caseGangYinService.saveAll(nonCigars, caseId);
		}
		cigarIds.add(caseCigar.getId());
	    }

	}
	for (String string : nonIds) {
	    boolean flag = cigarIds.contains(string);
	    if (!flag) {
		caseGangYinService.removeByCaseIdCigarId(caseId, string);
	    }
	}

    }
}
