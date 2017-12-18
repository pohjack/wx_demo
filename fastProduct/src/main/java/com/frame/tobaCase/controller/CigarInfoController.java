/**    
* @Title: CigarInfoController.java
* @Package com.frame.tobaCase.controller
* @Description: 卷烟信息管理控制层
* @author: liy
* @date 2017年2月22日 下午6:02:19
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CigarInfo;
import com.frame.tobaCase.service.ICigarInfoService;
import com.frame.tobaCase.utils.ExcelUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/cigarInfo")
public class CigarInfoController extends BaseController<CigarInfo, String> {

    public static final String type = "cigar2"; // 用于案件登记中添加卷烟 datatable 中条件搜索标识

    @Resource
    private ICigarInfoService cigarInfoService;

    @Override
    protected IBaseService<CigarInfo, String> getBaseService() {
	return cigarInfoService;
    }

    /**
     * 
    * @Description: 导入方法
    * @param @param file
    * @param @return
    * @param @throws Exception
    * @author: liy
    * @date 2017年3月4日 下午5:26:50
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.EXCEL_IMPOT)
    @RequestMapping("/cigarInfoImport")
    public String userExcelImport(@RequestParam(value = "file", required = false) MultipartFile file) {
	List<CigarInfo> importExcel;
	JSONObject json = new JSONObject();
	String msg = "导入成功";
	try {
	    String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
	    if (suffix.equals(".xlsx") || suffix.equals(".xls")) {
		importExcel = (List<CigarInfo>) new ExcelUtil<CigarInfo>(CigarInfo.class)
			.importExcel(file.getInputStream(), 2, suffix);
		for (CigarInfo info : importExcel) {
		    int i = cigarInfoService.findBybarCodeOutCount(info.getBarCode());
		    System.out.println(i);
		    if (cigarInfoService.findBybarCodeOutCount(info.getBarCode()) > 0) { // 判断条码是否已经存在数据库
			info.setId(cigarInfoService.findBybarCodeOutObject(info.getBarCode()).getId());
			cigarInfoService.update(info);
		    } else {
			cigarInfoService.save(info);
		    }
		}
	    } else {
		msg = "文件格式错误";
	    }
	} catch (Exception e) {
	    msg = "导入失败";
	    e.printStackTrace();
	}
	json.put("Msg", msg);
	return json.toString();
    }

    /**
     * 
    * @Description: 导出到卷烟信息Excel 表
    * @param @param ids
    * @param @param response
    * @param @throws IOException
    * @author: liy
    * @date 2017年3月4日 下午5:27:11
    * @throws
     */

    @RequiresPermissions(SysConstant.EXCEL_EXPORT)
    @RequestMapping("/cigarInfoExport")
    @ResponseBody
    public void userExcelExport(String[] ids, CigarInfo cInfo, HttpServletResponse response) throws IOException {
	if (StringUtils.isNoneEmpty(ids)) {
	    // TODO 查询指定ID的数据并将请其导出
	} else {
	    List<CigarInfo> users = cigarInfoService.findAll(cInfo); // 获取页面参数
	    new ExcelUtil<CigarInfo>(CigarInfo.class).exportExcel("卷烟列表", "卷烟列表" + new Date().getTime(), users,
		    response);
	}
    }

    /**
     * 
    * @Description: 新增涉案卷烟
    * @param @param model
    * @param @param request
    * @param @return
    * @author yuyf
    * @date 2017年5月11日 下午5:20:07
    * @throws
     */
    @RequiresPermissions(SysConstant.CIGAR_EDIT)
    @RequestMapping("/toAddCigar")
    protected String toEdit(Model model, HttpServletRequest request) {
	CigarInfo cigar = new CigarInfo();
	if (request.getParameter("barCode") != null) {
	    cigar.setBarCode(request.getParameter("barCode"));
	}
	if (request.getParameter("name") != null) {
	    cigar.setName(request.getParameter("name"));
	}
	model.addAttribute("obj", cigar);
	return getPagePath().concat("Add");
    }

    /**
    * 
    * @Description: 获取分页数据
    * @param @param request
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:07:34
    * @throws
    */
    @RequiresPermissions(SysConstant.CASE_EDIT)
    @RequestMapping("/getPagingList2")
    @ResponseBody
    public DateTablesResult<Map<String, Object>> getPagingList2(HttpServletRequest request) {
	DateTablesResult<Map<String, Object>> dataTable = new DateTablesResult<Map<String, Object>>(request);
	String s = dataTable.getCondition().get("ids").toString();
	String[] id = s.split(",");
	List<String> ids = Arrays.asList(id);
	dataTable.getCondition().put("ids", ids);
	dataTable.getCondition().put("type", type); // 用于案件登记中添加卷烟 datatable
	return getBaseService().queryByPage(dataTable);
    }
}
