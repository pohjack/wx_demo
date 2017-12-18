/**
 * @Title: IndexController.java
 * @Package com.frame.sys.controller
 * @Description: 主页跳转控制器
 * @author: lzl
 * @date 2016年7月7日 下午6:46:22
 * @version V1.0
 */
package com.frame.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.sys.entity.Resources;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.service.IRoleService;

@Controller
@RequestMapping("/public")
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private IRoleService roleService;

    @RequestMapping("/index")
    public String toIndexPage(Model model) {
	Subject user = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) user.getPrincipal();
	String name = shiroUser.name;
	String id = shiroUser.id;
	List<Role> roleList = shiroUser.roleList;
	List<Resources> menuList = roleService.getUserResource(roleList);
	logger.info("=====" + menuList.size());
	model.addAttribute("username", name);
	model.addAttribute("id", id);
	model.addAttribute("menuList", menuList);

	return "webmaster/index";
    }

    @RequestMapping("/main")
    public String toMainPage(Model model) {
	return "webmaster/main";
    }

}
