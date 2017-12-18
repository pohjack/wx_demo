/**    
* @Title: CaseGangYinController.java
* @Package com.frame.tobaCase.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author: liy
* @date 2017年2月23日 下午12:03:07
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.util.StringUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.utils.DateUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseCigar;
import com.frame.tobaCase.entity.CaseGangYin;
import com.frame.tobaCase.entity.CaseInfo;
import com.frame.tobaCase.service.ICaseCigarService;
import com.frame.tobaCase.service.ICaseGangYinService;
import com.frame.tobaCase.service.ICaseInfoService;
import com.frame.tobaCase.service.ICigarInfoService;
import com.frame.tobaCase.utils.ReflectSetValueUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("webmaster/caseGangYin")
public class CaseGangYinController extends BaseController<CaseGangYin, String> {
    @Resource
    private ICaseGangYinService caseGangYinService;
    @Resource
    private ICaseInfoService caseInfoService;
    @Resource
    private ICigarInfoService cigarInfoService;
    @Resource
    private ICaseCigarService caseCigarService;

    @Override
    protected IBaseService<CaseGangYin, String> getBaseService() {
	return caseGangYinService;
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
    @RequestMapping("/updateGangYin")
    @ResponseBody
    public String updateCaseCigar(HttpServletRequest request) {
	JSONObject json = new JSONObject();
	try {
	    String id = request.getParameter("id");
	    String[] name = request.getParameterValues("name");
	    String[] value = request.getParameterValues("value");
	    if (id != null && (!("".equals(id)))) {
		CaseGangYin caseCigar = caseGangYinService.findById(id);
		if (caseCigar != null) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    if (name != null && name.length > 0 && value != null && value.length > 0) {
			for (int i = 0; i < name.length; i++) {
			    map.put(name[i], value[i]);
			}
		    }
		    ReflectSetValueUtil refValue = new ReflectSetValueUtil();
		    refValue.reflectSetValue(caseCigar, map);
		    caseGangYinService.update(caseCigar);
		}
	    }
	    json.put("status", 1);
	} catch (Exception e) {
	    json.put("status", 0);
	    e.printStackTrace();
	}
	return json.toString();

    }

}
