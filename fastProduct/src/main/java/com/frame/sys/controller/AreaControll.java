/**    
* @Title: AreaControll.java
* @Package com.frame.sys.controller
* @Description: TODO(用一句话描述该文件做什么)
* @author: yuyf
* @date 2017年3月8日 上午10:45:52
* @version V1.0
*/
package com.frame.sys.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Area;
import com.frame.sys.entity.Organization;
import com.frame.sys.entity.User;
import com.frame.sys.service.IAreaService;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/webmaster/sys/area")
public class AreaControll extends BaseController<Area, String> {

    @Resource
    private IAreaService areaService;

    @Override
    protected IBaseService<Area, String> getBaseService() {
	return areaService;

    }

    @RequiresPermissions(SysConstant.AREA_VIEW)
    @RequestMapping("/toList2")
    protected String toList2(HttpServletRequest req, Model model) {
	List<JsTreeNode> treeNodes = getBaseService().findJsTreeNodes();
	JSONObject json = new JSONObject();
	json.put(SysConstant.TREE_OBJECT, treeNodes);
	model.addAttribute("jsonArray", json);
	return getPagePath().concat(SysConstant.PAGE_JUMP_TOLIST);
    }

    @RequiresPermissions(SysConstant.AREA_VIEW)
    @ResponseBody
    @RequestMapping("/getAjaxJsTreeList")
    protected String getAjaxJsTreeList(HttpServletRequest request) {
	String id = request.getParameter("id");
	if (StringUtil.isNotEmpty(id)) {
	    // List<JsTreeNode> treeNodes =
	    // getBaseService().findJsTreeNodes2(id);
	}
	List<JsTreeNode> treeNodes = getBaseService().findJsTreeNodes();
	JSONObject json = new JSONObject();
	json.put(SysConstant.TREE_OBJECT, treeNodes);
	return json.toString();
    }

    @RequiresPermissions(SysConstant.AREA_VIEW)
    @RequestMapping("/toAddView")
    public String toAddView(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
	String id = request.getParameter("id");
	String name2 = request.getParameter("name");
	if (StringUtil.isNotEmpty(name2)) {
	    String name = URLDecoder.decode(name2, "UTF-8");
	    model.addAttribute("name", name);
	}
	model.addAttribute("id", id);

	String pageView = getPagePath().concat(SysConstant.PAGE_JUMP_ADD);
	return pageView;
    }

    @RequiresPermissions(SysConstant.AREA_VIEW)
    @RequestMapping("/toEditView")
    public String toEditView(HttpServletRequest request, Model model) {
	String id = request.getParameter("id");
	if (StringUtil.isNotEmpty(id)) {
	    Area area = areaService.findById(id);
	    model.addAttribute("o", area);
	}
	String pageView = getPagePath().concat(SysConstant.PAGE_JUMP_EDIT);
	return pageView;
    }

}
