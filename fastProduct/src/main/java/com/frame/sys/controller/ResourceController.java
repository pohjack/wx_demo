/**    
* @Title: ResourceController.java
* @Package com.frame.sys.controller
* @Description: 资源管理controller
* @author: Shizh
* @date 2016年10月27日 下午3:27:25
* @version V1.0
*/
package com.frame.sys.controller;

import java.util.Date;
import java.util.HashMap;
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
import com.frame.sys.entity.Resources;
import com.frame.sys.service.IResourcesService;
import com.frame.sys.service.IRoleResourceService;
import com.github.pagehelper.StringUtil;

@Controller
@RequestMapping("/webmaster/sys/resources")
public class ResourceController extends BaseController<Resources, String> {

    @Resource
    private IResourcesService resourcesService;
    @Resource
    IRoleResourceService roleResourceService;

    @Override
    protected IBaseService<Resources, String> getBaseService() {
	return resourcesService;
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
    @RequiresPermissions(SysConstant.RESOURCE_EDIT)
    public String toEditView(HttpServletRequest request, Model model) {
	String id = request.getParameter("id");
	Resources resources = null;
	if (StringUtil.isNotEmpty(id)) {
	    resources = resourcesService.findById(id);
	}
	model.addAttribute("r", resources);
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
    @RequiresPermissions(SysConstant.RESOURCE_EDIT)
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
    @RequiresPermissions(SysConstant.RESOURCE_CHANGE_STATE)
    @RequestMapping("/changeState")
    public String changeState(HttpServletRequest request) {
	String[] ids = request.getParameterValues("arr");
	String status = request.getParameter("status");
	String changeStatus = ("0".equals(status)) ? "1" : "0";
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("ids", ids);
	map.put("status", changeStatus);
	map.put("updator", this.getCurrentUserID());
	map.put("updated", new Date());
	try {
	    resourcesService.changeState(map);
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    @RequestMapping("/toChooseImages")
    public String chooseImages() {
	return getPagePath().concat("images");
    }

    /**
    * @Description: 保存资源，连带把资源添加到admin上
    * @param @param request
    * @param @param res
    * @param @return
    * @author: lpy
    * @date 2016年11月16日 下午3:50:51
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.RESOURCE_INSERT)
    @RequestMapping("/save")
    public String save(Resources res) {
	try {
	    if (res != null) {
		resourcesService.mysave(res);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    /**
    * @Description: 删除资源，连带其角色拥有的资源
    * @param @param resIds
    * @param @return
    * @author: lpy
    * @date 2016年11月17日 下午4:36:51
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.RESOURCE_REMOVE)
    @RequestMapping("/removeRes")
    public String removeRes(String[] resIds) {
	try {
	    if (resIds != null && resIds.length > 0) {
		resourcesService.myRemove(resIds);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }
}
