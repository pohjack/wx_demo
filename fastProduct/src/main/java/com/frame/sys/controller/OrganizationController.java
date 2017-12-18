/**    
* @Title: OrganizationController.java
* @Package com.frame.sys.controller.OrganizationController
* @Description: 部门控制
* @author: liy
* @date 2016年10月19日 下午5:18:28
* @version V1.0
*/
package com.frame.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Organization;
import com.frame.sys.entity.User;
import com.frame.sys.service.IOrganizationService;
import com.frame.sys.service.IUserService;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/webmaster/sys/organization")
public class OrganizationController extends BaseController<Organization, String> {
    @Resource
    private IOrganizationService organizationService;
    @Resource
    private IUserService userService;

    @Override
    protected IBaseService<Organization, String> getBaseService() {
	return organizationService;
    }

    /**
    * @Description: 跳转到编辑页面
    * @param @param request
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2016年10月24日 下午5:31:26
    * @throws
     */
    @RequestMapping("/toEditView")
    @RequiresPermissions(SysConstant.ORGANIZATION_EDIT)
    public String toEditView(HttpServletRequest request, Model model) {
	String id = request.getParameter("id");
	if (StringUtil.isNotEmpty(id)) {
	    Organization organization = organizationService.findById(id);
	    List<User> users = userService.findByOrgId(id);
	    model.addAttribute("o", organization);
	    model.addAttribute("u", users);
	}
	String pageView = getPagePath().concat(SysConstant.PAGE_JUMP_EDIT);
	return pageView;
    }

    /**
    * @Description: 跳转到新增页面
    * @param @param request
    * @param @param model
    * @param @return
    * @author: lpy
    * @date 2016年10月24日 下午5:31:26
    * @throws
     */
    @RequestMapping("/toAddView")
    @RequiresPermissions(SysConstant.ORGANIZATION_INSERT)
    public String toAddView(HttpServletRequest request, Model model) {
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	model.addAttribute("id", id);
	model.addAttribute("name", name);
	String pageView = getPagePath().concat(SysConstant.PAGE_JUMP_ADD);
	return pageView;
    }

    /**
    * @Description: 禁启用部门
    * @param @param request
    * @param @return
    * @author: lpy
    * @date 2016年10月19日 下午5:47:41
    * @throws
     */
    @ResponseBody
    @RequestMapping("/changeState")
    @RequiresPermissions(SysConstant.ORGANIZATION_CHANGE_STATE)
    public String changeState(HttpServletRequest request) {
	String[] ids = request.getParameterValues("orgIds");
	String status = request.getParameter("status");
	String changeStatus = ("0".equals(status)) ? "1" : "0";
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("ids", ids);
	map.put("status", changeStatus);
	map.put("updator", this.getCurrentUserID());
	map.put("updated", new Date());
	try {
	    organizationService.changeState(map);
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    /**
    * @Description: 获取所有的机构
    * @param @return
    * @author: liy
    * @date 2016年10月28日 上午9:31:55
    * @throws
     */
    @ResponseBody
    @RequestMapping("/getAllOrg")
    @RequiresPermissions(SysConstant.ORGANIZATION_VIEW)
    public String getAllOrg() {
	List<Organization> orgList = organizationService.findAll();
	JSONObject json2 = new JSONObject();
	if (orgList.size() > 0) {
	    JSONArray jsonArray = new JSONArray();
	    JSONObject json = new JSONObject();
	    for (Organization org : orgList) {
		json.put("id", org.getId());
		json.put("orgName", org.getOrgName());
		jsonArray.add(json);
	    }
	    json2.put("org", jsonArray.toString());
	    return json2.toString();
	}
	return json2.toString();
    }

    /**
    * @Description: 是否有下级员工
    * @param @param request
    * @param @return
    * @author: lpy
    * @date 2016年11月8日 上午10:28:36
    * @throws
     */
    @ResponseBody
    @RequestMapping("/toUnderLing")
    @RequiresPermissions(SysConstant.ORGANIZATION_VIEW)
    public String toUnderLing(HttpServletRequest request) {
	String[] arr = request.getParameterValues("arr");
	List<User> users = null;
	if (arr != null && arr.length > 0) {
	    for (int i = 0; i < arr.length; i++) {
		users = userService.findByOrgId(arr[i]);
		if (users != null && users.size() > 0) {
		    return "false";
		}
	    }
	}
	return "true";
    }
}
