/**    
* @Title: LicenceInfoController.java
* @Package com.frame.tobaCase.controller
* @Description:  许可证管理控制层
* @author: liy
* @date 2017年2月22日 下午5:53:17
* @version V1.0
*/
package com.frame.tobaCase.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.Law;
import com.frame.tobaCase.service.ILawService;

@Controller
@RequestMapping("webmaster/law")
public class LawController extends BaseController<Law, String> {
    @Resource
    private ILawService lawService;

    @Override
    protected IBaseService<Law, String> getBaseService() {
	return lawService;
    }

    /**
     * 
    * @Description:检查案由编号是否存在
    * @param @param lawNo
    * @param @return
    * @author: liy
    * @date 2017年3月6日 下午7:00:17
    * @throws
     */
    @RequestMapping("/checkLawNum")
    @RequiresPermissions(SysConstant.LAW_VIEW)
    @ResponseBody
    public Object checkLawNum(String lawNum) {
	List<Law> lawList = null;
	String result = "success";
	if (StringUtils.isNotEmpty(lawNum)) {
	    lawList = lawService.findByLawNum(lawNum);
	    if (lawList.size() > 0) {
		result = "false";
	    }
	}
	return resutlMessage.renderSuccess(result);
    }
}
