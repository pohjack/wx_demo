/**    
* @Title: CaseEvidenceController.java
* @Package com.frame.tobaCase.controller
* @Description: 涉案登记控制层
* @author: liy
* @date 2017年2月23日 下午6:51:05
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseEvidence;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.service.ICaseEvidenceService;
import com.frame.tobaCase.service.ICaseInfoService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/caseEvidence")
public class CaseEvidenceController extends BaseController<CaseEvidence, String> {
    @Resource
    private ICaseEvidenceService caseEvidenceService;
    @Resource
    private ICaseInfoService caseInfoService;

    @Override
    protected IBaseService<CaseEvidence, String> getBaseService() {
	return caseEvidenceService;
    }

    public static final String CASEFILEPATH = "caseEvidence";

    /**
     * 
    * @Description:tex文件导入
    * @param @param multipartFile
    * @param @param request
    * @param @param response
    * @param @return
    * @param @throws Exception
    * @author: liy
    * @date 2017年1月9日 下午2:51:39
    * @throws
     */
    /*
     * @RequestMapping(value = "/upload")
     * 
     * @ResponseBody public String fileUpload(@RequestParam("files")
     * MultipartFile file, HttpServletRequest request, HttpServletResponse
     * response){ String type = request.getParameter("type"); String caseId =
     * request.getParameter("caseId"); if("".equals(caseId)){ caseId =
     * UUIDUtil.get32UUID(); } System.out.println(type); String type="1"; String
     * caseId="1"; JSONObject json = new JSONObject(); String message =
     * "success"; String id = UUIDUtil.get32UUID(); String evidUrl="";
     * //保存到数据库的路径 String origName="";//文件名 // 循环获取file数组中得文件 String attPath =
     * GetServerRealPath.getRootPath()+File.separator+ CASEFILEPATH +
     * File.separator + caseId + File.separator;// 当前案件相关文件所在目录
     * if(!file.isEmpty()){ origName = file.getOriginalFilename();
     * System.out.println(origName); String txtPath = attPath + type; // i
     * 对应不同文件所在目录 1->店铺照片,2->涉案卷烟照片,3->委托人身份证复印件,4->现场照片,5->涉嫌烟草条码明细表 if(!new
     * File(txtPath).exists()){ new File(txtPath).mkdirs(); } File targetFile =
     * new File(txtPath, origName); try{ file.transferTo(targetFile); // 上传文件
     * }catch(IllegalStateException e){ e.printStackTrace(); }catch(IOException
     * e){ e.printStackTrace(); } CaseEvidence ce = new CaseEvidence(); evidUrl
     * =caseId+ File.separator+type+ File.separator+file.getOriginalFilename();
     * //保存在本地数据库路径 ce.setId(id); ce.setCaseInfoId(caseId);
     * ce.setEvidName(checkType(type)); ce.setEvidType(Integer.valueOf(type));
     * ce.setEvidUrl(evidUrl); int i = caseEvidenceService.save(ce); // 保存案件文件信息
     * if(i < 0){ message = "error"; } } json.put("path",evidUrl); // 文件实际名称
     * json.put("name",origName); json.put("fileId", id); // 保存到数据库的文件名
     * json.put("Message", message); json.put("caseId", caseId); return
     * json.toString(); }
     */
    /**
     * 
    * @Description: 页面加载获取文件
    * @param @param caseInfoId
    * @param @return
    * @author: liy
    * @date 2017年2月25日 下午6:58:18
    * @throws
     */
    @RequestMapping(value = "/getFile")
    @ResponseBody
    public String getFile(String caseInfoId) {
	JSONObject json = new JSONObject();
	String[] array = { "1", "2", "3", "4", "5", "6", "7" };
	for (String s : array) {
	    json.put(s, caseEvidenceService.findByCaseInfoId(caseInfoId, Integer.valueOf(s)));
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 类型判断
    * @param @param type
    * @param @return
    * @author: liy
    * @date 2017年3月9日 下午4:18:22
    * @throws
     */
    public static String checkType(String type) {
	String picName = "";
	if ("1".equals(type)) {
	    picName = "烟草专卖零售许可证复印件";
	} else if ("2".equals(type)) {
	    picName = "《涉嫌未在当地烟草批发企业进货卷烟的条盒打码明细表》";
	} else if ("3".equals(type)) {
	    picName = "被委托人${prinName}身份证复印件";
	} else if ("4".equals(type)) {
	    picName = "委托书";
	} else if ("5".equals(type)) {
	    picName = "店铺照片";
	} else if ("6".equals(type)) {
	    picName = "涉案卷烟照片";
	} else {
	    picName = "现场照片";
	}
	return picName;
    }

    /**
     * 
    * @Description: 保存证据
    * @param @param caseEvidences
    * @param @return
    * @author: liy
    * @date 2017年3月20日 下午2:17:36
    * @throws
     */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping(value = "/saveEvidence")
    @ResponseBody
    public String saveEvidence(@RequestBody List<CaseEvidence> caseEvidences, String caseId) {
	JSONObject json = new JSONObject();
	int flag = 0; // 判断修改还是保存
	int i = 0;
	int j = 0; // 判断是否为第一次保存
	try {
	    if (StringUtils.isNotEmpty(caseId)) { // 修改
		flag = 1;
		CaseInfo caseInfo = caseInfoService.findById(caseId);
		if (caseInfo != null) {
		    if (caseInfo.getIsSite() != null) {
			if (caseInfo.getIsSite() == 1) {
			    //caseEvidenceService.deleteByArgs(caseId); // 删除类型为3、4证据(当事人在现场)
			}
		    }
		    json.put("result", true);
		    if (caseEvidences.size() > 0) {
			for (CaseEvidence ce : caseEvidences) {
			    String name = checkType(ce.getEvidType().toString());
			    ce.setEvidName(name);
			    ce.setCaseInfoId(caseId);
			    i = caseEvidenceService.saveCaseFileInfo(flag, ce, j);
			    if (i > 0) {
				json.put("result", true);
			    } else {
				json.put("result", false);
			    }
			    j++;
			}
		    }
		}
	    } else {
		if (caseEvidences.size() > 0) {
		    caseId = UUIDUtil.get32UUID();
		    for (CaseEvidence ce : caseEvidences) {
			String name = checkType(ce.getEvidType().toString());
			ce.setEvidName(name);
			ce.setCaseInfoId(caseId);
			i = caseEvidenceService.saveCaseFileInfo(flag, ce, j);
			if (i > 0) {
			    json.put("result", true);
			} else {
			    json.put("result", false);
			}
			j++;
		    }
		}
		/* caseId =UUIDUtil.get32UUID(); */
	    }
	    /*
	     * for(CaseEvidence ce :caseEvidences){ String name =
	     * checkType(ce.getEvidType().toString()); ce.setEvidName(name);
	     * ce.setCaseInfoId(caseId); i =
	     * caseEvidenceService.saveCaseFileInfo(flag, ce,j); if(i > 0){
	     * json.put("result", true); }else{ json.put("result", false); }
	     * j++; }
	     */

	    json.put("caseInfoId", caseId);
	} catch (Exception e) {
	    json.put("result", false);
	    e.printStackTrace();
	}
	return json.toString();
    }
}
