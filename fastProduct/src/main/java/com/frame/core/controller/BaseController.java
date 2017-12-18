/**    
* @Title: BaseController.java
* @Package com.frame.core.controller.BaseController<T, ID>
* @Description: BaseController,通用controller类
* @author: shizh
* @date 2016年10月21日 下午3:35:51
* @version V1.0
*/
package com.frame.core.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.ReflectionUtils;
import com.frame.core.commons.SysConstant;
import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.commons.result.ResultMessage;
import com.frame.core.entity.BizBaseEntity;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.entity.TreeNode;
import com.frame.core.exception.BaseAppException;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.ShiroUser;

import net.sf.json.JSONObject;

public abstract class BaseController<T extends BizBaseEntity, ID extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract IBaseService<T, ID> getBaseService();

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    @Resource
    protected ResultMessage resutlMessage;

    private Class<T> clazz;

    private T model;

    public BaseController() {
	try {
	    model = this.getClazz().newInstance();
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	}
    }

    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
	if (clazz == null) {
	    clazz = ReflectionUtils.getClassGenricType(this.getClass(), 0);
	}
	return clazz;
    }

    protected void setServletResponse(HttpServletResponse response) {
	this.response = response;
    }

    protected void setServletRequest(HttpServletRequest request) {
	this.request = request;
    }

    protected T getModel() {
	return model;
    }

    /**  
    * @Description: 获取类名称并转换成全部小写
    * @param @return
    * @author: lzl
    * @date 2016年7月22日 上午10:59:11
    * @throws
    */
    private String getSimpleName() {
	return this.getClazz().getSimpleName().toLowerCase();
    }

    /**
    * @ pathPrefix : jsp文件路径(如:/webmaster/sys/user/userList.jsp)
    */
    private String pathPrefix;

    public String getPathPrefix() {
	return pathPrefix;
    }

    public void setPathPrefix(String pathPrefix) {
	this.pathPrefix = pathPrefix;
    }

    /**  
    * @Description: jsp前
    * @param @return
    * @author: lzl
    * @date 2016年7月22日 上午11:09:46
    * @throws
    */
    private String getViewPrefix() {
	RequestMapping mapping = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
	String prefixStr = mapping.value()[0];
	if (StringUtils.isNotEmpty(getPathPrefix())) {// 子类自定义路径
	    prefixStr = getPathPrefix();
	}
	return prefixStr;
    }

    /**
     * 
    * @Description: 获取页面路径
    * @param @return
    * @author ShiZH
    * @date 2016年11月17日 上午9:22:34
    * @throws
     */
    protected String getPagePath() {
	String pagePath = getViewPrefix().concat(SysConstant.PAGE_PATH_SEPARATOR.concat(getSimpleName()));
	return pagePath;
    }

    /**
     * 
    * @Description: 获取当前登录用户id
    * @param @return
    * @author: Shizh
    * @date 2016年10月27日 上午11:16:44
    * @throws
     */
    protected String getCurrentUserID() {
	Subject user = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	return shiroUser.id;
    }

    /**
     * 
    * @Description: 跳转到列表页面
    * @param @param req
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:07:18
    * @throws
     */
    @RequestMapping("/toList")
    @RequiresPermissions(value = { "user:view", "resource:view", "role:view", "organization:view", "case:to_list",
	    "dict:view", "licence:view", "cigar:view", "law:view", "area:view", "loglogin:view", "logexception:view",
	    "logoperation:view" }, logical = Logical.OR)
    protected String toList(HttpServletRequest req) {
	return getPagePath().concat(SysConstant.PAGE_JUMP_TOLIST);
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
    @RequestMapping("/getPagingList")
    @ResponseBody
    protected DateTablesResult<Map<String, Object>> getPagingList(HttpServletRequest request) {
	DateTablesResult<Map<String, Object>> dataTable = new DateTablesResult<Map<String, Object>>(request);
	return getBaseService().queryByPage(dataTable);
    }

    /**
     * 
    * @Description: 跳转到编辑、新增页面
    * @param @param id
    * @param @param model
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:08:12
    * @throws
     */
    @RequestMapping("/toEdit")
    protected String toEdit(ID id, Model model) {
	if (StringUtils.isNotEmpty((String) id)) {
	    T obj = getBaseService().findById(id);
	    model.addAttribute("obj", obj);
	}
	return getPagePath().concat(SysConstant.PAGE_JUMP_EDIT);
    }

    /**
     * 
    * @Description: 跳转到查看页面
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:09:50
    * @throws
     */
    @RequestMapping("/toView/{id}")
    protected String toVeiw() {// 跳转到查看页面
	return getPagePath().concat(SysConstant.PAGE_JUMP_VIEW);
    }

    /**
     * 
    * @Description: 保存数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:10:17
    * @throws
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("/doSave")
    protected Object doSave(T obj) {
	T object = getBaseService().findById((ID) (obj.getId()));
	Integer result = 0;
	if (object != null) {
	    obj.setUpdator(this.getCurrentUserID());
	    result = getBaseService().update(obj);
	} else {
	    obj.setCreator(this.getCurrentUserID());
	    result = getBaseService().save(obj);
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 删除数据的方法,包含删除单条和批量删除
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:10:48
    * @throws
     */
    @RequestMapping("/doRemove")
    @ResponseBody
    protected Object doRemove(String id, Model model) {
	String[] idStrings;
	Integer result;
	if (StringUtils.isNotEmpty(id)) { // id为空判断
	    if (id.contains(",")) { // 多个id，解析成数组
		idStrings = id.split(",");
	    } else { // 单个id
		idStrings = new String[] { id };
	    }
	    result = getBaseService().remove(idStrings);
	} else { // id为空抛出异常
	    throw new BaseAppException("NULLPointer：id为空", new NullPointerException(), "NULLPointer",
		    new String[] { "传入id为空!" });
	}
	return resutlMessage.renderSuccess(result);
    }

    /**
     * 
    * @Description: 获取树数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:26:37
    * @throws
     */
    @RequestMapping("/getTreeList")
    protected String getTreeList(Model model) {
	List<TreeNode> treeNodes = getBaseService().findTreeNodes();
	model.addAttribute(SysConstant.TREE_OBJECT, treeNodes);
	return getPagePath().concat(SysConstant.PAGE_JUMP_TOLIST);
    }

    /**
     * 
    * @Description: ajax获取树数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:26:37
    * @throws
     */
    @ResponseBody
    @RequestMapping("/getAjaxTreeList")
    protected String getAjaxTreeList() {
	List<TreeNode> treeNodes = getBaseService().findTreeNodes();
	JSONObject json = new JSONObject();
	json.put(SysConstant.TREE_OBJECT, treeNodes);
	return json.toString();
    }

    /**
     * 
    * @Description: 删除所有数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月21日 下午2:26:37
    * @throws
     */
    @RequestMapping("/removeAll")
    @ResponseBody
    protected Object removeAll() {
	Integer result = getBaseService().removeAll();
	return resutlMessage.renderSuccess(result);
    }
}
