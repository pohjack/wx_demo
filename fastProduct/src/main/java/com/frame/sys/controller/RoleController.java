/**
 * @Title: RoleController.java
 * @Package com.frame.sys.controller
 * @Description: 角色管理
 * @author: lzl
 * @date 2016年7月20日 下午5:17:54
 * @version V1.0
 */
package com.frame.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.RoleResource;
import com.frame.sys.entity.User;
import com.frame.sys.entity.UserRole;
import com.frame.sys.service.IRoleResourceService;
import com.frame.sys.service.IRoleService;
import com.frame.sys.service.IUserRoleService;
import com.frame.sys.service.IUserService;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/webmaster/sys/role")
public class RoleController extends BaseController<Role, String> {

    @Resource
    private IRoleService roleService;
    @Resource
    private IUserRoleService userRoleService;
    @Resource
    private IUserService userService;
    @Resource
    IRoleResourceService roleResourceService;

    @Override
    protected IBaseService<Role, String> getBaseService() {
	return roleService;
    }

    /**
     * 
    * @Description: 获得当前用户信息
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年10月13日 上午9:48:53
    * @throws
     */
    @RequestMapping(value = "UpdateInfo")
    @RequiresPermissions(SysConstant.ROLE_VIEW)
    @ResponseBody
    public String editeInfo(String id) {
	Role role = null;
	if (id != null && !"".equals(id)) {
	    role = getBaseService().findById(id);
	}
	JSONObject json = new JSONObject();
	json.put("Role", role);
	return json.toString();
    }

    /**
    * @Description: 根据角色id拿到资源
    * @param @param id
    * @param @return
    * @author: lpy
    * @date 2016年11月10日 下午3:08:59
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_VIEW)
    @RequestMapping(value = "toResouseByRoleId")
    public String toResouseByRoleId(String id) {
	List<Map<String, Object>> resList = null;
	if (StringUtil.isNotEmpty(id)) {
	    resList = roleService.findResourceByRoleId(id);
	}
	JSONObject json = new JSONObject();
	json.put("resList", resList);
	return json.toString();
    }

    /**
    * @Description: 根据角色id拿到用户
    * @param @param id
    * @param @return
    * @author: lpy
    * @date 2016年11月10日 下午3:09:15
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_VIEW)
    @RequestMapping(value = "toUserByRoleId")
    public String toUserByRoleId(String id) {
	List<UserRole> list = null;
	if (StringUtil.isNotEmpty(id)) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("roleId", id);
	    list = userRoleService.findAll(map);
	}
	List<User> users = null;
	if (list != null && list.size() > 0) {
	    users = new ArrayList<User>();
	    ;
	    for (int i = 0; i < list.size(); i++) {
		users.add(userService.findById(list.get(i).getUserId()));
	    }
	}
	JSONObject json = new JSONObject();
	json.put("users", users);
	return json.toString();
    }

    /**
    * @Description: 修改角色所拥有的资源
    * @param @param flag(判断是添加还是删除)
    * @param @return
    * @author: lpy
    * @date 2016年11月10日 下午4:31:19
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_EIDT)
    @RequestMapping(value = "amendRoleRes")
    public String amendRoleRes(String[] resIds, String roleId, String flag) {
	try {
	    if (resIds != null && resIds.length > 0 && StringUtil.isNotEmpty(roleId)) {
		RoleResource r = new RoleResource();
		r.setRoleId(roleId);
		for (int i = 0; i < resIds.length; i++) {
		    r.setResourceId(resIds[i]);
		    if ("true".equals(flag)) {
			roleResourceService.save(r);
		    } else if ("false".equals(flag)) {
			roleResourceService.deleteByUserOrRes(r);
		    }
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}

	return "true";
    }

    /**
     * @Description: 删除,连带删除资源
     * @param @param roleId
     * @param @return
     * @author: lpy
     * @date 2016年11月11日 上午9:40:46
     * @throws
      */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_REMOVE)
    @RequestMapping(value = "removeRole")
    public String removeRole(String id) {
	try {
	    if (StringUtil.isNotEmpty(id)) {
		String roleIds[] = { id };
		roleService.removeRole(roleIds);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    /**
    * @Description: 保存角色，如果是新增则连带保存资源
    * @param @param role
    * @param @return
    * @author: lpy
    * @date 2016年11月11日 上午10:31:10
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_EIDT)
    @RequestMapping(value = "saveRole")
    public String saveRole(Role role, String[] resIds) {
	try {
	    if (role != null) {
		role.setUpdator(getCurrentUserID());
		roleService.mySave(role, resIds);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    /**
    * @Description: 禁用启用方法
    * @param @param request
    * @param @return
    * @author: lpy
    * @date 2016年11月11日 下午6:28:13
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_CHANGE_STATE)
    @RequestMapping(value = "changeState")
    public String changeState(HttpServletRequest request) {
	String id = request.getParameter("roleId");
	String status = request.getParameter("status");
	String changeStatus = ("0".equals(status)) ? "1" : "0";
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("id", id);
	map.put("status", changeStatus);
	map.put("updator", this.getCurrentUserID());
	map.put("updated", new Date());
	try {
	    roleService.changeState(map);
	} catch (Exception e) {
	    e.printStackTrace();
	    return e.getMessage();
	}
	return "true";
    }

    /**
     * @Description: 传入角色数组查询拥有多少个用户
     * @param @param request
     * @param @return
     * @author: lpy
     * @date 2016年11月11日 下午6:28:13
     * @throws
      */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_VIEW)
    @RequestMapping(value = "toFindCountByRoleId")
    public String toFindCountByRoleId(String[] roleIds) {
	String userCount = "0";
	try {
	    if (roleIds != null && roleIds.length > 0) {
		userCount = userRoleService.findCountByRoleId(roleIds);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return userCount;
    }

    /**
     * @Description: 判断是否有重名存在
     * @param @param request
     * @param @return
     * @author: lpy
     * @date 2016年11月11日 下午6:28:13
     * @throws
      */
    @ResponseBody
    @RequiresPermissions(SysConstant.ROLE_EIDT)
    @RequestMapping(value = "toSameName")
    public String toSameName(String name) {
	try {
	    if (StringUtil.isNotEmpty(name)) {
		int count = roleService.isSameName(name);
		if (count > 0) {
		    return "false";
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "true";
    }
}
