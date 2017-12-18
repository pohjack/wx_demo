/**
 * @Title: UserController.java
 * @Package com.frame.sys.controller
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月20日 下午5:17:54
 * @version V1.0
 */
package com.frame.sys.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.SysConstant;
import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.commons.utils.MD5;
import com.frame.core.controller.BaseController;
import com.frame.core.exception.BaseAppException;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Organization;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.sys.entity.UserRole;
import com.frame.sys.service.IOrganizationService;
import com.frame.sys.service.IRoleService;
import com.frame.sys.service.IUserRoleService;
import com.frame.sys.service.IUserService;
import com.frame.tobaCase.utils.ExcelUtil;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/webmaster/sys/user")
public class UserController extends BaseController<User, String> {
    @Resource
    private IUserService userService;

    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleService roleService;

    @Resource
    private IOrganizationService organizationService;

    @Override
    protected IBaseService<User, String> getBaseService() {
	return userService;
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
    @RequiresPermissions(SysConstant.USER_EDIT)
    @RequestMapping("/getPagingList")
    @ResponseBody
    protected DateTablesResult<Map<String, Object>> getPagingList(HttpServletRequest request) {
	String currentId = "";
	DateTablesResult<Map<String, Object>> dataTable = new DateTablesResult<Map<String, Object>>(request);
	Subject subject = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
	if (shiroUser != null) {
	    currentId = shiroUser.id;
	}
	dataTable.getCondition().put("currentId", currentId); // 排除当前登录用户
	return getBaseService().queryByPage(dataTable);
    }

    /**
     * 
    * @Description: 去添加页面/编辑页面
    * @param @param model
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:56:47
    * @throws
     */
    @RequiresPermissions(SysConstant.USER_EDIT)
    @RequestMapping("/toEditUser")
    public String toEditUser(Model model, String id, String type) {
	List<UserRole> currentRoleList = null;
	List<Role> roleList = roleService.findAll();
	User user = null;
	String currentRoleIds = "";
	if (StringUtils.isNotEmpty(id)) {
	    user = userService.findById(id);
	    currentRoleList = userRoleService.findRoleIdListByUserId(id);
	    if (currentRoleList != null && !currentRoleList.isEmpty()) {
		for (int j = 0; j < currentRoleList.size(); j++) {
		    currentRoleIds += currentRoleList.get(j).getRoleId() + ",";
		}
		if (currentRoleIds.length() > 0) {
		    currentRoleIds = currentRoleIds.substring(0, currentRoleIds.length() - 1);// 判断一次，去掉最后一个逗号
		}
	    }
	}
	List<Organization> allOrg = organizationService.findAll();
	model.addAttribute("roleList", roleList);
	model.addAttribute("currentRoleIds", currentRoleIds);
	model.addAttribute("user", user);
	model.addAttribute("allOrg", allOrg);
	model.addAttribute("type", type);
	String path = getPagePath().concat(SysConstant.PAGE_JUMP_EDIT);
	return path;
    }

    /**
     * 
    * @Description: 更新状态
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月8日 下午5:28:39
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.USER_CHANGE_STATE)
    @RequestMapping("/changeState")
    public int changeState(String id) {
	int result = 0;
	if (StringUtils.isNotEmpty(id)) {
	    User user = userService.findById(id);
	    if (user != null && user.getStatus() != null) {
		user.setStatus(1 - user.getStatus());
		result = userService.update(user);
	    }
	}
	return result;
    }

    /**
     * 
    * @Description: 保存用户信息
    * @param @param user
    * @param @param roleids
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:57:03
    * @throws
     */
    @ResponseBody
    @RequiresPermissions(SysConstant.USER_EDIT)
    @RequestMapping("/saveOrUpdateUser")
    public Object saveOrUpdateUser(User user, String roleids) {
	Integer result = 0;
	result = userService.saveOrUpdateUserAndUserRole(user, roleids);
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description:去修改密码页面
    * @param @param model
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:57:35
    * @throws
     */
    @RequestMapping("/toPassword")
    public String toPassword(Model model, String id) {
	model.addAttribute("id", id);
	return getPagePath().concat("Password");
    }

    /**
     * 
    * @Description: 修改密码
    * @param @param password
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:57:56
    * @throws
     */
    @ResponseBody
    @RequestMapping("/changePassword")
    public Object changePassword(String password, String id) {
	Integer result = 0;
	if (StringUtils.isNotEmpty(id)) {
	    /*
	     * User user = userService.findById(id); if(user != null){
	     */
	    User user = new User();
	    user.setPassword(MD5.md5(password));
	    result = userService.update(user);
	    /* } */
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 删除
    * @param @param password
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:57:56
    * @throws
     */
    @RequestMapping("/removeUser")
    @RequiresPermissions(SysConstant.USER_REMOVE)
    @ResponseBody
    public Object doRemove(String[] ids) {// 从页面直接传回数组
	Integer result = 0;
	if (ids != null && ids.length > 0) {
	    result = userService.deleteUserAndUserRole(ids); // 删除用户基本信息
	} else {
	    throw new BaseAppException("NULLPointer：id为空", new NullPointerException(), "NULLPointer",
		    new String[] { "传入id为空!" });
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description:解锁
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午6:18:46
    * @throws
     */
    @RequestMapping("/unlock")
    @RequiresPermissions(SysConstant.USER_UNLOCK)
    @ResponseBody
    public Object unlock(String id) {
	int result = 0;
	if (StringUtils.isNotEmpty(id)) {
	    result = userService.unlock(id);
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 判断用户名是否存在
    * @param @param request
    * @param @return
    * @author: liy
    * @date 2016年11月14日 下午7:53:42
    * @throws
     */
    @RequestMapping("/toCheckName")
    @ResponseBody
    public Object toCheckName(HttpServletRequest request) {
	String result = "false";
	String loginName = request.getParameter("loginName");
	if (loginName != null && StringUtils.isNotEmpty(loginName)) {
	    String count = userService.findUserCountByLoginName(loginName);
	    if (1 > Integer.parseInt(count)) {
		result = "true";
	    }
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 獲得用戶管理中所需所有角色信息（json），用樹展示出來--暂不调用
    * @param @return
    * @author: liy
    * @date 2016年11月17日 上午10:01:53
    * @throws
     */
    public JSONArray getTreeRole() {
	JSONArray outJsonArray = new JSONArray();
	JSONObject json = new JSONObject();
	List<Role> roleList = roleService.findAll();
	if (roleList != null && !roleList.isEmpty()) {
	    for (int i = 0; i < roleList.size(); i++) {
		json.put("id", roleList.get(i).getId());
		json.put("name", roleList.get(i).getName());
		json.put("pId", "0");
		outJsonArray.add(json);
	    }
	    json.put("name", "角色选择");
	    json.put("id", "0");
	    outJsonArray.add(json);
	}
	return outJsonArray;
    }

    /**
     * 
    * @Description: 判断用户名是否存在
    * @param @param request
    * @param @return
    * @author: liy
    * @date 2016年11月14日 下午7:53:42
    * @throws
     */
    @RequestMapping("/findNotAdminAndRoot")
    @ResponseBody
    public String findNotAdminAndRoot(HttpServletRequest request) {
	JSONObject json = new JSONObject();
	List<User> user = null;
	try {
	    user = userService.findNotAdminAndRoot();
	    json.put("userList", user);
	    json.put("status", "1");
	} catch (Exception e) {
	    json.put("status", "0");
	    e.printStackTrace();
	}
	return json.toString();
    }

    /**
     * 
    * @Description:重置密码
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2017年3月10日 下午5:42:11
    * @throws
     */
    @RequestMapping("/resetPassword")
    @RequiresPermissions(SysConstant.USER_CHANGE_PASSWORD)
    @ResponseBody
    public Object resetPassword(String id) {
	String initPassword = "";
	Integer result = 0;
	if (StringUtils.isNotEmpty(id)) {
	    User user = new User();
	    user.setId(id);
	    initPassword = SysConstant.INITPASSWORD; // 初始化
	    user.setPassword(MD5.md5(initPassword));
	    user.setStatus(null);// 防止修改状态
	    result = userService.update(user);
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 导出用户列表Excel
    * @param @param ids
    * @param @param response
    * @param @throws IOException
    * @author shizh
    * @date 2017年3月4日 下午3:50:49
    * @throws
     */
    @RequiresPermissions(SysConstant.EXCEL_EXPORT)
    @RequestMapping("/userExcelExport")
    @ResponseBody
    public void userExcelExport(String[] ids, User user, HttpServletResponse response) throws IOException {
	if (StringUtils.isNoneEmpty(ids)) {
	    // TODO 查询指定ID的数据并将请其导出
	} else {
	    List<User> users = userService.findAll(user);
	    new ExcelUtil<User>(User.class).exportExcel("用户列表", "用户列表" + new Date().getTime(), users, response);
	}
    }

    @RequestMapping("/toOneselfUserEdit")
    public String toOneselfUserEdit(String id, Model model) {
	try {
	    User user = userService.findById(id);
	    model.addAttribute("user", user);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return "webmaster/sys/user/oneselfUserEdit";
    }

    @ResponseBody
    @RequestMapping("/saveOneselfUserEdit")
    public String SaveOneselfUserEdit(String id, String realName, String email, String mobile) {
	JSONObject json = new JSONObject();
	try {
	    if (StringUtil.isNotEmpty(id)) {
		User user = userService.findById(id);
		if (user != null) {
		    user.setRealName(realName);
		    user.setEmail(email);
		    user.setMobile(mobile);
		    userService.update(user);
		}
	    }
	    json.put("status", "1");
	} catch (Exception e) {
	    e.printStackTrace();
	    json.put("status", "0");
	}
	return json.toString();
    }

    /**
     * 
    * @Description: 修改密码(不限制权限)
    * @param @param password
    * @param @param id
    * @param @return
    * @author: liy
    * @date 2016年11月10日 下午2:57:56
    * @throws
     */
    @ResponseBody
    @RequestMapping("/oneselfChPwd")
    public Object oneselfChPwd(String password, String id) {
	Integer result = 0;
	try {
	    if (StringUtils.isNotEmpty(id)) {
		User user = new User();
		user.setId(id);
		user.setPassword(MD5.md5(password));
		result = userService.update(user);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return resutlMessage.renderSuccess(result);
    }
}
