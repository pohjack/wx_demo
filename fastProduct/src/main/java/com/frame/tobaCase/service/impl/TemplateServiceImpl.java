/**    
* @Title: TemplateServiceImpl.java
* @Package com.frame.tobaCase.service.impl
* @Description: TODO(用一句话描述该文件做什么)
* @author: shizh
* @date 2017年2月23日 下午8:57:50
* @version V1.0
*/
package com.frame.tobaCase.service.impl;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.EHCacheUtil;
import com.frame.tobaCase.entity.BaseTemplate;
import com.frame.tobaCase.entity.BaseTemplateSlave;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseTempHistory;
import com.frame.tobaCase.entity.DictionaryData;
import com.frame.tobaCase.entity.DocsTemplateSlave;
import com.frame.tobaCase.service.IBaseTemplateService;
import com.frame.tobaCase.service.IBaseTemplateSlaveService;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseEvidenceService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICaseInfoSlaveService;
import com.frame.tobaCase.service.ICaseInvoPersService;
import com.frame.tobaCase.service.ICaseTempHistoryService;
import com.frame.tobaCase.service.IDictionaryDataService;
import com.frame.tobaCase.service.IDocsTemplateService;
import com.frame.tobaCase.service.IDocsTemplateSlaveService;
import com.frame.tobaCase.service.ILawService;
import com.frame.tobaCase.service.ITemplateService;
import com.frame.tobaCase.utils.CNYConvertUtil;

@Service("templateService")
public class TemplateServiceImpl implements ITemplateService {
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
    private ICaseInfoSlaveService caseInfoSlaveService;

    @Resource
    private ICaseInvoPersService caseInvoPersService;

    @Resource
    private ICaseEvidenceService caseEvidenceService;

    @Resource
    private ILawService lawService;

    @Resource
    private ICaseTempHistoryService caseTempHistoryService;

    public static boolean TEMP_FLAG = true;

    /**
     * @throws Exception
     * 
     * @Description: 模板自动生成测试 
     * @param @return 
     * @author shizh 
     * @date 2017年2月13日 下午5:41:28 
     * @throws
     */
    public String AutoGenerate(String modelNo, String caseId, int tag) {
	// Map<String, Object> caseInfo = getInfo(caseId, modelNo);
	CaseTempHistory checkHistory = caseTempHistoryService.checkHistory(caseId, modelNo);
	StringBuffer stringBuffer = new StringBuffer();
	if (tag == 1) {
	    if (checkHistory != null) {
		stringBuffer = BaseTempGenerate(checkHistory.getContent(), caseId, modelNo, "");
	    } else {
		List<DocsTemplateSlave> baseTemps = docsTemplateSlaveService.findByMasterId(modelNo, tag); // 文书模板查询
		if (baseTemps.size() > 0) {
		    for (DocsTemplateSlave docsTemplateSlave : baseTemps) { // 循环文书模板
			String baseTempId = docsTemplateSlave.getBaseTempId();
			// List<BaseTemplateSlave> datas =
			// baseTemplateSlaveService.findByMasterId(baseTempId);
			BaseTemplate data = baseTemplateService.findById(baseTempId); // 查询文书模板包含的基础模板
			if (data != null) { // 处理基础模板数据
			    StringBuffer generateStr = BaseTempGenerate(data.getContent(), caseId, modelNo, baseTempId);
			    // StringBuffer generateStr =
			    // BaseTemplateGenerate(datas,caseInfo);
			    stringBuffer.append(generateStr);
			}
		    }
		}
	    }
	} else {
	    List<DocsTemplateSlave> baseTemps = docsTemplateSlaveService.findByMasterId(modelNo, tag); // 文书模板查询
	    if (baseTemps.size() > 0) {
		for (DocsTemplateSlave docsTemplateSlave : baseTemps) { // 循环文书模板
		    String baseTempId = docsTemplateSlave.getBaseTempId();
		    // List<BaseTemplateSlave> datas =
		    // baseTemplateSlaveService.findByMasterId(baseTempId);
		    BaseTemplate data = baseTemplateService.findById(baseTempId); // 查询文书模板包含的基础模板
		    if (data != null) { // 处理基础模板数据
			StringBuffer generateStr = BaseTempGenerate(data.getContent(), caseId, modelNo, baseTempId);
			// StringBuffer generateStr =
			// BaseTemplateGenerate(datas,caseInfo);
			stringBuffer.append(generateStr);
		    }
		}
	    }
	}
	return stringBuffer.toString();
    }

    /**
     * 
     * @Description: 生成原始未替换的内容 
     * @param @param modelNo 
     * @param @param caseId 
     * @param @return 
     * @author shizh 
     * @date 2017年3月2日 下午8:49:13 
     * @throws
     */
    public String GenerateOriginCont(String modelNo, String caseId, int tag) {
	StringBuffer stringBuffer = new StringBuffer();
	List<DocsTemplateSlave> baseTemps = docsTemplateSlaveService.findByMasterId(modelNo, tag); // 文书模板查询
	if (baseTemps.size() > 0) {
	    for (DocsTemplateSlave docsTemplateSlave : baseTemps) { // 循环文书模板
		String baseTempId = docsTemplateSlave.getBaseTempId();
		// List<BaseTemplateSlave> datas =
		// baseTemplateSlaveService.findByMasterId(baseTempId);
		BaseTemplate data = baseTemplateService.findById(baseTempId); // 查询文书模板包含的基础模板
		if (data != null) { // 处理基础模板数据
		    // StringBuffer generateStr =
		    // BaseTemplateGenerate(datas,caseInfo);
		    stringBuffer.append(data.getContent());
		}
	    }
	}
	return stringBuffer.toString();
    }

    /**
     * 
     * @Description: 子模板编辑预览方法 
     * @param @param tid 子模板ID 
     * @param @param caseId  用于展示的案件ID 
     * @param @param modelNo 文书模板ID 
     * @param @return 
     * @author shizh 
     * @date 2017年2月25日 下午2:48:41 
     * @throws
     */
    public String BaseTempEdit(String tid, String caseId, String modelNo) {
	Map<String, Object> caseInfo = getInfo(caseId, modelNo);
	StringBuffer stringBuffer = new StringBuffer();
	List<BaseTemplateSlave> datas = baseTemplateSlaveService.findByMasterId(tid); // 查询文书模板包含的基础模板
	if (datas.size() > 0) { // 处理基础模板数据
	    StringBuffer generateStr = BaseTemplateGenerate(datas, caseInfo);
	    stringBuffer.append(generateStr);
	}
	return stringBuffer.toString();
    }

    /**
     * 
     * @Description: 查询案件包含的所有信息存放在map中用于替换变量 
     * @param @param caseId 
     * @param @param modelNo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月25日 下午2:47:13 
     * @throws
     */
    public Map<String, Object> getInfo(String caseId, String modelNo) {
	Map<String, Object> caseInfo = caseInfoService.findByKey(caseId);
	if (caseInfo != null && caseInfo.size() > 0) {
	    Map<String, Object> caseInfoSlave = caseInfoSlaveService.findByCaseId(caseId);
	    Map<String, Object> caseInvoPers = caseInvoPersService.findByCaseId(caseId);
	    if (caseInfoSlave != null) {
		caseInfo.putAll(caseInfoSlave);
	    }
	    if (caseInvoPers != null) {
		caseInfo.putAll(caseInvoPers);
	    }
	    caseInfo.put("modelNO", modelNo);
	}
	return caseInfo;
    }

    /**
     * @throws Exception
     * 
     * @Description: 基础模板引用数据字典拼装测试 
     * @param @param data 
     * @param @param caseInfo 案件信息 
     * @param @return 
     * @author shizh 
     * @date 2017年2月13日 下午6:55:02 
     * @throws
     */
    public StringBuffer BaseTemplateGenerate(List<BaseTemplateSlave> data, Map<String, Object> caseInfo) {
	StringBuffer stringBuffer = new StringBuffer();
	for (BaseTemplateSlave baseTemplateSlave : data) {
	    String value = baseTemplateSlave.getValue();
	    int type = baseTemplateSlave.getCategory();
	    if (StringUtils.isNotEmpty(value)) {// 基础模板调用数据字典的值处理（常量变量以及判别量的区分与处理）
		value = ValueJudge(value, type, caseInfo);
	    }
	    stringBuffer.append(value);
	}
	return stringBuffer;
    }

    /**
     * 
     * @Description: 基础模板段落变量替换方法 
     * @param @param template 模板数据，基础模板表中的content，模板内容 
     * @param @param caseId 案件ID 
     * @param @param modelNo 文书模板ID 
     * @param @return 
     * @author shizh 
     * @date 2017年3月1日 下午3:19:00 
     * @throws
     */
    public StringBuffer BaseTempGenerate(String template, String caseId, String modelNo, String bId) {
	StringBuffer sb = new StringBuffer("<span btId='" + bId + "'>");
	if (StringUtils.isNoneEmpty(template)) {
	    Map<String, Object> caseInfo = getInfo(caseId, modelNo);
	    Matcher m = Pattern.compile("[$#]\\{\\w+\\}").matcher(template);// 用参数替换模板中的${}和#{}变量
	    while (m.find()) {
		String param = m.group(); // ${xx}
		String colum = param.substring(2, param.length() - 1);
		String res = "";
		if (param.startsWith("#")) {
		    res = DoJudge(colum, caseInfo);
		} else {
		    res = DoInfoReplace(colum, caseInfo);
		}
		m.appendReplacement(sb, StringUtils.isEmpty(res) ? "" : res);
	    }
	    m.appendTail(sb);
	}
	return sb.append("</span>");
    }

    /**
     * @throws Exception
     * 
     * @Description: 调用数据字典值常量变量以及判别量的区分 
     * @param @param value 
     * @param @param caseInfo 案件信息 
     * @param @return 
     * @author shizh 
     * @date 2017年2月14日 上午10:10:35 
     * @throws
     */
    public String ValueJudge(String value, int type, Map<String, Object> caseInfo) {
	switch (type) {
	case 1:
	    String colum = value.substring(2, value.length() - 1);
	    value = DoInfoReplace(colum, caseInfo).toString();
	    break;
	case 2:
	    value = DoJudge(value, caseInfo);
	    break;
	default:
	    break;
	}
	return value;
    }

    /**
     * 
     * @Description: 涉案卷烟拼装方法 
     * @param @param id 案件id 
     * @param @return 
     * @author shizh 
     * @date 2017年2月14日 上午10:36:30 
     * @throws
     */
    public String DoCaseCigar(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String modelNo = (String) caseInfo.get("modelNO");
	String id = (String) caseInfo.get("id");
	List<CaseCigar> cigars = temp.caseCigarService.findByCaseId(id);
	int size = cigars.size();
	StringBuffer sBuffer = new StringBuffer();
	if (size > 0) {
	    String name, unit, result;
	    Integer number;
	    if ("3".equals(modelNo)) {
		StringBuffer fake = new StringBuffer();
		StringBuffer real = new StringBuffer();
		for (CaseCigar caseCigar : cigars) {
		    name = caseCigar.getName();
		    number = caseCigar.getNumber();
		    unit = caseCigar.getUnit();
		    result = caseCigar.getInspectResult();
		    String string = name + number + unit;
		    if ("假".equals(result)) {
			DoCombination(fake, string);
		    } else {
			DoCombination(real, string);
		    }
		}
		if (fake.length() > 0) {
		    DictionaryData fakeData = temp.dictionaryDataService.findByCode("fakeCigar");
		    String bi = ",";
		    if (!(real.length() > 0)) {
			bi = "。";
		    }
		    fake.append(fakeData.getValue()).append(bi);
		}
		if (real.length() > 0) {
		    DictionaryData realData = temp.dictionaryDataService.findByCode("realCigar");
		    real.append(realData.getValue()).append("。");
		}
		sBuffer.append(fake).append(real);
	    } else {
		StringBuffer forEachCigars = ForEachCigars(cigars);
		sBuffer.append(forEachCigars);
	    }
	}
	return sBuffer.toString();
    }

    /**
     * 
     * @Description: 执法人员信息拼装方法 
     * @param @param agentsIds 
     * @param @return 
     * @author shizh 
     * @date 2017年2月14日 下午4:22:08 
     * @throws
     */
    public String DoCaseAgents(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String agentsIds = (String) caseInfo.get("catchpole_no");
	String agentsNames = (String) caseInfo.get("catchpole_name");
	StringBuffer stringBuffer = new StringBuffer();
	if (StringUtils.isNoneEmpty(agentsIds) && StringUtils.isNotEmpty(agentsNames)) {
	    String[] agentsId = agentsIds.split(",");
	    String[] agentsName = agentsNames.split("、");
	    int length = agentsId.length;
	    for (int i = 0; i < length; i++) {
		String cardId = agentsId[i];
		String name = agentsName[i];
		stringBuffer.append(name).append("（执法证：").append(cardId).append(")");
		if (i < length - 1) {
		    stringBuffer.append("、");
		}
	    }
	}
	return stringBuffer.toString();
    }

    /**
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws ClassNotFoundException
     * 
     * @Description: 变量值的替换方法 
     * @param @param colum 
     * @param @return 
     * @author shizh 
     * @date 2017年2月14日 下午6:01:39 
     * @throws
     */
    public String DoInfoReplace(String colum, Map<String, Object> caseInfo) {
	String result = "";
	if (caseInfo != null && caseInfo.size() > 0) {
	    Object object = caseInfo.get(colum);
	    if (object != null) {
		String simpleName = caseInfo.get(colum).getClass().getSimpleName();
		if (simpleName.equals("Date")) {// 时间类型的数据处理
		    Date date = (Date) caseInfo.get(colum);
		    result = new SimpleDateFormat("yyyy年MM月dd日").format(date);
		} else if (simpleName.equals("Time")) {
		    Date date2 = (Date) caseInfo.get(colum);
		    result = new SimpleDateFormat("HH时mm分").format(date2);
		} else {
		    String modelNo = (String) caseInfo.get("modelNO");
		    result = (String) caseInfo.get(colum);
		    if ("7".equals(modelNo) && "case_cause".equals(colum)) {
			result = result.replace("涉嫌", "");
		    }
		    if ("4".equals(modelNo) && "case_cause".equals(colum)) {
			result = result.replace("涉嫌", "");
		    }
		    if ("5".equals(modelNo) && "case_cause".equals(colum)) {
			result = result.replace("涉嫌", "");
		    }
		    if ("8".equals(modelNo) && "case_cause".equals(colum)) {
			result = result.replace("涉嫌", "");
		    }
		    if ("9".equals(modelNo) && "case_cause".equals(colum)) {
			result = result.replace("涉嫌", "");
		    }
		}
	    }
	}
	return result;
    }

    /**
     * @throws Exception
     * 
     * @Description: 判别量和方法反射调用业务方法 
     * @param @param colum 
     * @param @return 
     * @author shizh 
     * @date 2017年2月15日 下午2:15:13 
     * @throws
     */
    public String DoJudge(String colum, Map<String, Object> caseInfo) {
	Class<?> c;
	String invoke = "";
	try {
	    c = Class.forName("com.frame.tobaCase.service.impl.TemplateServiceImpl");
	    Object obj = c.newInstance();
	    Method method = c.getMethod(colum, TemplateServiceImpl.class, Map.class);
	    invoke = (String) method.invoke(obj, new Object[] { this, caseInfo });
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return invoke;
    }

    /**
     * 
     * @Description: 是否在现场判别 
     * @param @return 
     * @author shizh 
     * @date 2017年2月15日 下午3:08:28 
     * @throws
     */
    public String IsNonCigar(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String isNonCigar = (String) caseInfo.get("case_cause_code");
	String reString = "";
	String code1 = "NonCigar";
	String code2 = "noNonCigar";
	int i = isNonCigar.indexOf("002");
	if (i != -1) {// 当事人不在现场
	    DictionaryData data = temp.dictionaryDataService.findByCode(code1);
	    reString = data.getValue();
	} else {
	    DictionaryData data = temp.dictionaryDataService.findByCode(code2);
	    reString = data.getValue();
	}
	return reString;
    }

    public String IsSiCigar(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String id = (String) caseInfo.get("id");// TODO 待优化
	String types = temp.caseCigarService.findTypesById(id);
	String modelNo = (String) caseInfo.get("modelNO");
	StringBuffer stringBuffer = new StringBuffer();
	if (StringUtils.isNotEmpty(types)) {
	    String[] type = types.split(",");
	    boolean flag = true;
	    for (int i = 0; i < type.length; i++) {
		String attr = type[i];
		if (!attr.equals("私")) {
		    flag = false;
		}
	    }
	    if (flag) {
		stringBuffer.append(
			"　　当事人如不服本行政处罚决定，可以自收到本决定书之日起60日内向广东省烟草专卖局或潮州市人民政府申请复议；也可以自收到本决定书之日起15日内向潮州市湘桥区人民法院提起诉讼。<br>　　逾期不申请行政复议，也不向人民法院起诉，又不履行处罚决定的，本机关将申请人民法院强制执行。");

	    } else {
		stringBuffer.append(
			"　　当事人应自收到本决定书之日起15日内将罚款缴到中国工商银行潮州金山支行，地址：潮州市春荣路中段嘉苑名庭1-4号。<br>　　到期不缴纳罚款的，我局将依法每日按罚款数额的百分之三加处罚款。<br>　　当事人如不服本行政处罚决定，可以自收到本决定书之日起60日内向广东省烟草专卖局或潮州市人民政府申请复议；也可以自收到本决定书之日起15日内向潮州市湘桥区人民法院提起诉讼。<br>　　逾期不申请行政复议，也不向人民法院起诉，又不履行处罚决定的，本机关将申请人民法院强制执行。");

	    }
	}
	return stringBuffer.toString();
    }

    /**
     * 
     * @Description: 是否在现场判别 
     * @param @return 
     * @author shizh 
     * @date 2017年2月15日 下午3:08:28 
     * @throws
     */
    public String IsSite(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	int isSite = (int) caseInfo.get("is_site");
	String reString = "";
	// String modelNo = (String) caseInfo.get("modelNO");
	String code = "notSite";
	boolean is = (isSite == 0);
	/*
	 * boolean equals2 = "2".equals(modelNo); boolean equals = equals2 &&
	 * !TEMP_FLAG;
	 */
	/*
	 * if(equals2){ if(TEMP_FLAG){ TEMP_FLAG = false; }else{ if(is){ code =
	 * "toStaff"; }else{ code = "toParty"; } TEMP_FLAG = true; } }
	 */
	if (is) {// 当事人不在现场
	    DictionaryData data = temp.dictionaryDataService.findByCode(code);
	    reString = data.getValue();
	}
	return reString;
    }

    /**
     * 
     * @Description: 是否在现场判别交给谁 
     * @param @return 
     * @author shizh 
     * @date 2017年2月15日 下午3:08:28 
     * @throws
     */
    public String ToWho(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	int isSite = (int) caseInfo.get("is_site");
	String reString = "";
	String code = "toParty";
	boolean is = (isSite == 0);
	if (is) {// 当事人不在现场
	    code = "toStaff";
	}
	DictionaryData data = temp.dictionaryDataService.findByCode(code);
	reString = data.getValue();
	return reString;
    }

    /**
     * 
     * @Description: 卷烟品种判别 
     * @param @return 
     * @author shizh 
     * @date 2017年2月15日下午3:08:28 
     * @throws
     */
    public String CigarKind(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	List<CaseCigar> cigars = temp.caseCigarService.findByCaseId((String) caseInfo.get("id"));
	String reString = "";
	String code = "OneAuth";
	boolean is = (cigars.size() > 1);
	if (is) {// 卷烟品种大于1
	    code = "AllAuth";
	}
	DictionaryData data = temp.dictionaryDataService.findByCode(code);
	reString = data.getValue();
	return reString;
    }

    /**
     * 
     * @Description: 发文字号拼装 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月16日 下午3:08:14 
     * @throws
     */
    public String DoDocNum(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	// String modelNo = (String) caseInfo.get("modelNO");
	String code = "docNum3";
	DictionaryData data = temp.dictionaryDataService.findByCode(code);
	String fir = data.getValue();
	String year = new SimpleDateFormat("yyyy").format((Date) caseInfo.get("crime_date"));
	String caseNo = (String) caseInfo.get("code");
	return fir + "[" + year + "]第" + caseNo + "号";
    }

    /**
     * 
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param stringBuffer
    * @param @param string
    * @param @return
    * @author shizh
    * @date 2017年3月13日 下午5:15:49
    * @throws
     */
    public StringBuffer DoCombination(StringBuffer stringBuffer, String string) {
	int length = stringBuffer.length();
	if (length > 0) {
	    string = "、" + string;
	}
	stringBuffer.append(string);
	return stringBuffer;
    }

    /**
     * 
     * @Description: 违反法律法规拼装方法（测试） 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @param @throws UnsupportedEncodingException 
     * @author shizh 
     * @date 2017年2月17日 下午6:51:45 
     * @throws
     */
    public String DoLaw(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String doLawExtra = DoLawExtra(temp, caseInfo, "breakLaw");
	return doLawExtra;
    }

    /**
     * 
     * @Description: 惩罚依据法律法规拼装方法（测试） 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @param @throws UnsupportedEncodingException 
     * @author shizh 
     * @date 2017年2月17日下午6:51:45 
     * @throws
     */
    public String DoPunishLaw(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String doLawExtra = DoLawExtra(temp, caseInfo, "punishLaw");
	return doLawExtra;
    }

    /**
     * 
     * @Description: 法律法规拼装方法（测试） 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @param @throws UnsupportedEncodingException 
     * @author shizh 
     * @date 2017年2月17日下午6:51:45 
     * @throws
     */
    @SuppressWarnings("unchecked")
    public String DoLawExtra(TemplateServiceImpl temp, Map<String, Object> caseInfo, String code) {
	String cause = (String) caseInfo.get("case_cause_code");
	String join = "";
	if (StringUtils.isNotEmpty(cause)) {
	    String[] split = cause.split(",");
	    String aString = "";
	    List<String> list = new ArrayList<String>();
	    EHCacheUtil.initCacheManager().getCache("lawCache");// 根据配置文件
	    // ehcahce.xml
	    // 的缓存名字获取缓存对象
	    Map<String, Map<String, String>> map = (Map<String, Map<String, String>>) EHCacheUtil.get("lawMap");
	    for (String string : split) {
		Map<String, String> laws = map.get(string);
		aString = laws.get(code);
		if (!list.contains(aString)) {
		    list.add(aString);
		}
	    }
	    join = StringUtils.join(list, "、");
	    join = join.replaceAll("(《中华人民共和国烟草专卖法实施条例》)(.*?)(\\1)(.*?)", "$1$2$4").replaceAll("、国务院", "及国务院");
	}
	return join;
    }

    /**
     * 
     * @Description: 卷烟检查结果及估价信息拼装方法
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月21日下午2:24:31 
     * @throws
     */
    public String DoCigarAttr(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String id = (String) caseInfo.get("id");
	Map<String, Object> classify = Classify(temp, id);
	StringBuffer fakeBuffer = (StringBuffer) classify.get("fakeBuffer");
	StringBuffer notBuffer = (StringBuffer) classify.get("notBuffer");
	StringBuffer privateBuffer = (StringBuffer) classify.get("privateBuffer");
	String plus=(fakeBuffer == null ? "" : fakeBuffer.toString()) + (notBuffer == null ? "" : notBuffer.toString())
		+ (privateBuffer == null ? "" : privateBuffer.toString());
	return plus.substring(0, plus.length() - 1).concat("。");
    }

    /**
     * 
     * @Description: 抽取卷烟检验结果及估价信息拼装的公共方法 
     * @param @param temp 
     * @param @param id 
     * @param @return 
     * @author shizh 
     * @date 2017年2月22日上午9:42:37 
     * @throws
     */
    private Map<String, Object> Classify(TemplateServiceImpl temp, String id) {
	Map<String, Object> map = new HashMap<String, Object>();
	List<CaseCigar> cigars = temp.caseCigarService.findByCaseId(id);
	StringBuffer fakeBuffer = new StringBuffer();
	StringBuffer notBuffer = new StringBuffer();
	StringBuffer privateBuffer = new StringBuffer();
	int fakeNo = 0, notNo = 0, priNo = 0, fakeTotal = 0, notTotal = 0, priTotal = 0;
	double totalValue = 0, fakeVal = 0, notVal = 0, priVal = 0;
	for (CaseCigar caseCigar : cigars) {
	    String inspectResult = caseCigar.getInspectResult();
	    String name = caseCigar.getName();
	    Integer number = caseCigar.getNumber();
	    String unit = caseCigar.getUnit();
	    StringBuffer sBuffer = new StringBuffer();
	    Double totalValue2 = caseCigar.getTotalValue();
	    totalValue += totalValue2;
	    sBuffer.append(name).append(number).append(unit).append("、");
	    switch (inspectResult) {// 待优化
	    case "假":
		fakeNo += 1;
		fakeTotal += number;
		fakeVal += totalValue2;
		fakeBuffer.append(sBuffer);
		break;
	    case "非":
		notNo += 1;
		notTotal += number;
		notVal += totalValue2;
		notBuffer.append(sBuffer);
		break;
	    default:
		priNo += 1;
		priTotal += number;
		priVal += totalValue2;
		privateBuffer.append(sBuffer);
		break;
	    }
	}
	fakeBuffer = Extra(fakeBuffer, fakeNo, fakeTotal, "fakeGood", temp, fakeVal);
	notBuffer = Extra(notBuffer, notNo, notTotal, "nonSmoke", temp, notVal);
	String privateStr="";
	String siStr=temp.caseInfoService.findById(id).getCaseCause();
	if(siStr.contains("无标志外国卷烟、专供出口卷烟")){
	    privateStr= "privateSmoke";
	}else if(siStr.contains("无标志外国卷烟")){
	    privateStr= "privateSmokeWu";
	}else if(siStr.contains("专供出口卷烟")){
	    privateStr= "privateSmokeZh";
	}else{
	    privateStr= "privateSmoke";
	}
	privateBuffer = Extra(privateBuffer, priNo, priTotal, privateStr, temp, priVal);
	map.put("fakeBuffer", fakeBuffer);
	map.put("notBuffer", notBuffer);
	map.put("privateBuffer", privateBuffer);
	map.put("totalValue", totalValue);
	return map;
    }

    /**
     * 
     * @Description: 抽取方法对每一分类单独拼装 
     * @param @param str 
     * @param @param kind 
     * @param @param total 
     * @param @return 
     * @author shizh 
     * @date 2017年2月21日 下午4:07:31 
     * @throws
     */
    private StringBuffer Extra(StringBuffer str, Integer kind, Integer total, String code, TemplateServiceImpl temp,
	    double val) {
	if (str.length() <= 0) {
	    return null;
	}
	StackTraceElement[] ste = Thread.currentThread().getStackTrace();
	StackTraceElement a = (StackTraceElement) ste[3];
	if (StringUtils.isNotEmpty(str))
	    str.deleteCharAt(str.length() - 1);
	if (kind > 1)
	    str.append("等共");
	if (str.length() > 0) {
	    if (kind > 1) {
		str.append("计").append(kind).append("个品种").append(total).append("条");
	    } else if (kind == 1) {

	    }

	}
	DictionaryData d2 = temp.dictionaryDataService.findByCode(code + "_belong");
	if ("DoCigarAttr".equals(a.getMethodName())) {
	    DictionaryData d = temp.dictionaryDataService.findByCode(code);
	    str.append(d.getValue()).append(d2.getValue()).append("；");
	} else {
	    str.insert(0, d2.getValue());
	    String value = CNYConvertUtil.change(val);
	    str.append("，货值人民币").append(value).append("；");
	}
	return str;
    }

    /**
     * 
     * @Description: 卷烟估价信息拼装 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月21日下午4:45:09 
     * @throws
     */
    public String DoCigarEval(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String id = (String) caseInfo.get("id");
	Map<String, Object> classify = Classify(temp, id);
	StringBuffer fakeBuffer = (StringBuffer) classify.get("fakeBuffer");
	StringBuffer notBuffer = (StringBuffer) classify.get("notBuffer");
	StringBuffer privateBuffer = (StringBuffer) classify.get("privateBuffer");
	double totalVal = (double) classify.get("totalValue");
	String change = CNYConvertUtil.change(totalVal);
	StringBuffer stringBuffer = new StringBuffer();
	if ((fakeBuffer == null && notBuffer == null) || (fakeBuffer == null && privateBuffer == null)
		|| (privateBuffer == null && notBuffer == null)) {
	    stringBuffer.append(fakeBuffer == null ? "" : fakeBuffer).append(notBuffer == null ? "" : notBuffer)
		    .append(privateBuffer == null ? "" : privateBuffer);
	} else {
	    stringBuffer.append("该批卷烟货值人民币").append(change).append("，").append(fakeBuffer == null ? "" : fakeBuffer)
		    .append(notBuffer == null ? "" : notBuffer).append(privateBuffer == null ? "" : privateBuffer);
	}
	
	return stringBuffer.toString().substring(0, stringBuffer.toString().length() - 1).concat("。");
    }

    /**
     * 
     * @Description: 处罚结果拼装方法 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月22日上午11:43:50 
     * @throws
     */
    public String DoPunish(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	String id = (String) caseInfo.get("id");// TODO 待优化
	String types = temp.caseCigarService.findTypesById(id);
	int index = 1;
	String tab = "", xiaohui = "", code = "", typeRange = "", fak = "%的罚款，计人民币";
	String modelNo = (String) caseInfo.get("modelNO");
	if (!"6".equals(modelNo)) {
	    tab = "<br>　　";
	}
	StringBuffer stringBuffer = new StringBuffer();
	if (StringUtils.isNotEmpty(types)) {
	    String[] type = types.split(",");
	    boolean flag = true;
	    for (int i = 0; i < type.length; i++) {
		String attr = type[i];
		if (!"私".equals(attr)) {
		    Integer fakeRange = 0;
		    if ("假".equals(attr)) {
			DictionaryData stopSell = temp.dictionaryDataService.findByCode("stopSelling");
			if (type.length == 1) {
			    stringBuffer.append(tab).append(stopSell.getValue());
			} else {
			    stringBuffer.append(tab).append(index++).append("、").append(stopSell.getValue());
			}
			code = temp.dictionaryDataService.findByCode("illegalFines").getValue();
			xiaohui = temp.dictionaryDataService.findByCode("destroy").getValue();
			typeRange = "fake_range";
			fakeRange = (Integer) caseInfo.get(typeRange);
		    } else if ("非".equals(attr) && flag) {
			flag = false;
			code = temp.dictionaryDataService.findByCode("nonFines").getValue();
			typeRange = "non_range";
			fakeRange = (Integer) caseInfo.get(typeRange);
		    }

		    double countTotalVal = temp.caseCigarService.countTotalVal(id, type[i]);
		    String change = CNYConvertUtil.change(countTotalVal);
		    double range = ((double) fakeRange) / 100;
		    String fine = CNYConvertUtil.change(countTotalVal * range);
		    if (type.length == 1) {
			stringBuffer.append(tab).append(code).append(change).append(fakeRange).append(fak).append(fine)
				.append("。");
		    } else {
			stringBuffer.append(tab).append(index++).append("、").append(code).append(change)
				.append(fakeRange).append(fak).append(fine).append("。");
		    }
		} else {
		    code = temp.dictionaryDataService.findByCode("chufa4").getValue();// TODO
		    List<CaseCigar> cigars = temp.caseCigarService.findByAttr(id, "私");
		    StringBuffer forEachCigars = ForEachCigars(cigars);
		    if (type.length == 1) {
			stringBuffer.append(tab).append(code).append(forEachCigars).append("予以没收。");
		    } else {
			stringBuffer.append(tab).append(index).append("、").append(code).append(forEachCigars)
				.append("予以没收。");
		    }
		}
	    }
	    stringBuffer.append("<br>");
	    if (xiaohui != "") {
		stringBuffer.append("&nbsp;&nbsp;").append(xiaohui).append("<br>");
	    }
	}
	return stringBuffer.toString();
    }

    /**
     * 
     * @Description: 涉案卷烟拼装方法
     * @param @param  cigars 
     * @param @return 
     * @author shizh 
     * @date 2017年2月22日下午3:53:59 
     * @throws
     */
    public StringBuffer ForEachCigars(List<CaseCigar> cigars) {
	StringBuffer sBuffer = new StringBuffer();
	int size = 0, no = 0;
	for (CaseCigar caseCigar : cigars) {
	    size += 1;
	    String name = caseCigar.getName();
	    Integer number = caseCigar.getNumber();
	    no += number;
	    String unit = caseCigar.getUnit();
	    sBuffer.append(name).append(number).append(unit).append("、");
	}
	sBuffer.deleteCharAt(sBuffer.length() - 1);
	if (size > 1)
	    sBuffer.append("等共").append("计").append(size).append("个品种").append(no).append("条");

	return sBuffer;
    }

    /**
     * 
     * @Description: 证据信息拼装方法 
     * @param @param temp 
     * @param @param caseInfo 
     * @param @return 
     * @author shizh 
     * @date 2017年2月22日下午4:55:46 
     * @throws
     */
    public String DoEvidence(TemplateServiceImpl temp, Map<String, Object> caseInfo) {
	StringBuffer stringBuffer = new StringBuffer();
	String id = (String) caseInfo.get("id");
	String countType = temp.caseCigarService.countType(id); // 统计烟数量以及规格
	String[] mokeTotalArry = new String[2];
	DictionaryData evi = temp.dictionaryDataService.findByCode("involve");
	DictionaryData evidNotice = temp.dictionaryDataService.findByCode("evidNotice");
	DictionaryData evidDoc = temp.dictionaryDataService.findByCode("evidDoc");
	LinkedList<String> evList = new LinkedList<String>();
	if (StringUtils.isNotEmpty(countType) && !"0".equals(countType)) {
	    mokeTotalArry = countType.split(",");
	    String involve = evi.getValue().replace("${mokeTotal}", mokeTotalArry[0]).replace("${spec}",
		    mokeTotalArry[1]);
	    evList.add(involve);
	}
	evList.add(evidNotice.getValue());
	List<Map<String, Object>> evidsMap = temp.caseEvidenceService.countEvidence(id);
	StringBuffer siteEvid = new StringBuffer();// 现场证据
	int num1 = -1;
	String client = "";// 身份证复印件字符串
	boolean flag = false;// 表示 身份证复印件与委托书是否相等
	if ((evidsMap != null && evidsMap.size() > 0) || evidsMap.size() == 0) {
	    siteEvid.append("当事人" + caseInfo.get("per_respon") + "身份证1份，");
	}
	for (Map<String, Object> map : evidsMap) {
	    int num = (int) map.get("evidTypeTotal");
	    if(map.get("evid_name").toString().indexOf("委托书") != -1 &&(int) map.get("evidTypeTotal")==0){
		 if(num1!=-1){
		     siteEvid.append(client + num1 + "份，" );
		 }
	    }
	    if (num > 0) {
		String evidName = map.get("evid_name").toString();
		int indexOf = evidName.indexOf("被委托人");
		int isSite = (int) caseInfo.get("is_site");
		if ( indexOf != -1) {
		    String prinName = (String) caseInfo.get("prin_name");
		    client = evidName.replace("${prinName}", prinName == null ? "" : prinName);
		} else {
		    if (evidName.indexOf("委托书") != -1 ) {
			if (num1 == (int) map.get("evidTypeTotal")) {
			    siteEvid.append(client + "、" + evidName);
			    flag = true;
			} else {
			    if(num1!=-1){
				siteEvid.append(client + num1 + "份、" + evidName);
			    }else{
				siteEvid.append(evidName);
			    }
			    
			}
		    } else {
			if (evidName.indexOf("许可证") != -1) {
			    siteEvid.replace(9, 12, "、" + evidName);
			} else {
			    siteEvid.append(evidName);
			}
		    }
		}
		if (evidName.indexOf("许可证") != -1 || (evidName.equals("委托书") && flag)) {
		    siteEvid.append("各");
		}
		if (evidName.indexOf("身份证复印件") != -1 ) {
		    num1 = (int) map.get("evidTypeTotal");
		} else {
		    siteEvid.append(map.get("evidTypeTotal"));
		}
		if (evidName.indexOf("照片") != -1) {
		    siteEvid.append("张、");
		} else if (evidName.indexOf("许可证") != -1 || evidName.indexOf("明细表") != -1
			|| evidName.indexOf("委托书") != -1) {
		    siteEvid.append("份，");
		} else {
		    if (evidName.indexOf("复印件") != -1) {
		    } else {
			if (isSite == 0) {
			    siteEvid.append("、");
			} else {
			    siteEvid.append("份，");
			}
		    }
		}
	    }

	}
	if (siteEvid.length() > 0) {
	    String siteEvidStr = siteEvid.toString().substring(0, siteEvid.toString().length() - 1).concat("。");
	    evList.add(siteEvidStr);
	}
	if (StringUtils.isNotEmpty(evidDoc.getValue())) {
	    String[] evidDocArr = evidDoc.getValue().split(",");
	    if (evidDocArr.length > 0) {
		for (int i = 0; i < evidDocArr.length; i++) {
		    evList.add(evidDocArr[i] + "。");
		}
	    }
	}
	int i = 1;
	for (String string : evList) {
	    stringBuffer.append("　　" + i + "." + string + "<br>");
	    i++;
	}
	return stringBuffer.toString();
    }
}
