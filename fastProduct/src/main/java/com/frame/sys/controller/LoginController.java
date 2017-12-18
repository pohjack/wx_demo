/**
 * @Title: LoginController.java
 * @Package com.frame.sys.controller
 * @Description: 后台用户登录处理
 * @author: lzl
 * @date 2016年7月7日 下午6:57:14
 * @version V1.0
 */
package com.frame.sys.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.frame.core.commons.result.ResultMessage;
import com.frame.core.commons.utils.PropertiesUtil;
import com.frame.core.controller.BaseController;
import com.frame.core.exception.BaseAppException;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.ShiroUser;
import com.frame.sys.entity.User;
import com.frame.sys.service.IUserService;
import com.frame.sys.utils.ValidateCodeUtil;

@Controller
public class LoginController extends BaseController<User, String> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private IUserService userService;

    @Resource
    private ResultMessage resutlMessage;

    @RequestMapping("/login")
    public String toLoginPage() {
	// TODO 简单登录 后期改成session
	return "/webmaster/login";
    }

    /**
    * 
    * @Description: 用户登录验证
    * @param @param username 用户名
    * @param @param password 密码
    * @param @param submitCode 验证码
    * @param @param request
    * @param @return
    * @author: Shizh
    * @date 2016年10月22日 下午2:13:50
    * @throws
    */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object loginHandle(String username, String password, String submitCode, HttpServletRequest request) {
	logger.info("后台用户登录username=" + username + "==password=" + password + "==validateCode=" + submitCode);
	logger.info("DigestUtils.md5Hex(password)==" + DigestUtils.md5Hex(password));
	Subject user = SecurityUtils.getSubject();
	UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
	token.setRememberMe(true);
	try {
	    user.login(token);
	    User user1 = new User();
	    logger.info("==用户登录成功===");
	    request.setAttribute("success", "true");
	    user1.setLoginName(username);
	    ;
	    user1.setLoginTime(new Date());
	    user1.setLoginTimes(0);
	    userService.updateUserLFTimes(user1);
	} catch (UnknownAccountException e) {
	    /*
	     * logger.error("账号不存在：{}", e); return
	     * resutlMessage.renderError(request, "账号不存在");
	     */
	    throw new BaseAppException("UnknownAccountException:账号不存在", e, PropertiesUtil.getLogProperty("UNKNOWCOUNT"),
		    new String[] { "账号不存在！" });
	} catch (LockedAccountException e) {
	    /*
	     * logger.error("账号已被锁定：{}", e); return
	     * resutlMessage.renderError(request, "登录失败次数过多，账号已被锁定");
	     */
	    throw new BaseAppException("LockedAccountException:账号已被锁定", e, "LockedAccount",
		    new String[] { "登录失败次数过多，账号已被锁定！" });
	} catch (IncorrectCredentialsException e) {
	    /*
	     * logger.error("密码错误：{}", e); return
	     * resutlMessage.renderError(request, "用户名/密码错误");
	     */
	    throw new BaseAppException("IncorrectCredentialsException:密码错误", e, "IncorrectCredentials",
		    new String[] { "用户名/密码错误！" });
	} catch (DisabledAccountException e) {
	    /*
	     * logger.error("账号未启用：{}", e); return
	     * resutlMessage.renderError(request, "账号未启用");
	     */
	    throw new BaseAppException("DisabledAccountException:账号未启用", e, "DisabledAccount",
		    new String[] { "账号未启用！" });
	} catch (RuntimeException e) {
	    /*
	     * logger.error("未知错误,请联系管理员：{}", e); return
	     * resutlMessage.renderError(request, "未知错误,请联系管理员");
	     */
	    throw new BaseAppException("RuntimeException:未知错误,请联系管理员", e, "Runtime", new String[] { "未知错误,请联系管理员！" });
	}
	return resutlMessage.renderSuccess();
    }

    /**
     * 
    * @Description: 退出系统登录的方法
    * @param @return
    * @author: Shizh
    * @date 2016年10月27日 下午5:28:58
    * @throws
     */
    @RequestMapping(value = "/logout")
    public Object logout(HttpServletRequest httpServletRequest) {
	logger.info("系统退出");
	Subject subject = SecurityUtils.getSubject();
	ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
	httpServletRequest.setAttribute("id", shiroUser == null ? null : shiroUser.id);
	subject.logout();
	return "webmaster/login";
    }

    @Override
    protected IBaseService<User, String> getBaseService() {
	return userService;
    }

    /**
     * 
    * @Description: 授权无效，用户没有权限跳转的页面
    * @param @return
    * @author: Shizh
    * @date 2016年10月29日 下午2:19:43
    * @throws
     */
    @RequestMapping("/unauth")
    public String unauth() {
	return "unauth";
    }

    /**
     * 
    * @Description: 生成验证码
    * @param @param request
    * @param @param response
    * @author: Shizh
    * @date 2016年11月1日 上午10:26:08
    * @throws
     */
    @RequestMapping(value = "/validateCode")
    public void validateCode(HttpServletRequest request, HttpServletResponse response) {
	response.setHeader("Cache-Control", "no-cache");
	String verifyCode = ValidateCodeUtil.generateTextCode(ValidateCodeUtil.TYPE_ALL_MIXED, 4, null);
	request.getSession().setAttribute("validateCode", verifyCode);
	response.setContentType("image/jpeg");
	BufferedImage bim = ValidateCodeUtil.generateImageCode(verifyCode, 100, 39, 3, true, new Color(223, 223, 223),
		new Color(21, 45, 70), new Color(21, 45, 70));
	try {
	    ImageIO.write(bim, "JPEG", response.getOutputStream());
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

}
