/**    
* @Title: LicenceInfoController.java
* @Package com.frame.tobaCase.controller
* @Description:  许可证管理控制层
* @author: liy
* @date 2017年2月22日 下午5:53:17
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.LicenceInfo;
import com.frame.tobaCase.service.ILicenceInfoService;
import com.frame.tobaCase.utils.ExcelUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/licenceInfo")
public class LicenceInfoController extends BaseController<LicenceInfo, String> {
    @Resource
    private ILicenceInfoService licenceInfoService;

    @Override
    protected IBaseService<LicenceInfo, String> getBaseService() {
	return licenceInfoService;
    }

    /**
     * 导入方法
     * @param request
     * @param response
     * @return
     */

    /**
     * 
    * @Description: 导入Excel文件
    * @param @param file
    * @param @return
    * @param @throws Exception
    * @author shizh
    * @date 2017年3月4日 下午3:42:55
    * @throws
     */
    @RequiresPermissions(SysConstant.EXCEL_IMPOT)
    @RequestMapping("/licenceExcelImport")
    @ResponseBody
    public String licenceExcelImport(@RequestParam(value = "file", required = false) MultipartFile file)
	    throws Exception {
	String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	JSONObject jsonObject = new JSONObject();
	if (suffix.equals(".xlsx") || suffix.equals(".xls")) {
	    List<LicenceInfo> importExcel = (List<LicenceInfo>) new ExcelUtil<LicenceInfo>(LicenceInfo.class)
		    .importExcel(file.getInputStream(), 2, suffix);
	    int importLic = licenceInfoService.importLic(importExcel);
	    System.out.println(importLic);
	    jsonObject.put("res", "上传成功，共上传" + importLic + "条有效数据！");
	} else {
	    jsonObject.put("res", "文件格式错误，请上传.xls或.xlsx文件！");
	}
	return jsonObject.toString();
    }

    /**
     * 
    * @Description: 导出许可证列表Excel
    * @param @param ids
    * @param @param response
    * @param @throws IOException
    * @author shizh
    * @date 2017年3月4日 下午3:50:49
    * @throws
     */
    @RequestMapping("/licExcelExport")
    @RequiresPermissions(SysConstant.EXCEL_EXPORT)
    @ResponseBody
    public void userExcelExport(String[] ids, LicenceInfo licenceInfo, HttpServletResponse response)
	    throws IOException {
	if (StringUtils.isNoneEmpty(ids)) {
	    // TODO 查询指定ID的数据并将其导出
	} else {
	    List<LicenceInfo> licenceInfos = licenceInfoService.findAll(licenceInfo);
	    new ExcelUtil<LicenceInfo>(LicenceInfo.class).exportExcel("许可证列表", "许可证列表" + new Date().getTime(),
		    licenceInfos, response);
	}
    }

}
