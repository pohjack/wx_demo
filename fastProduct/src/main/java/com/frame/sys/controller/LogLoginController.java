/**    
* @Title: LogLoginController.java
* @Package com.frame.sys.controller
* @Description: 登录日志controller
* @author: Shizh
* @date 2016年11月2日 下午8:13:05
* @version V1.0
*/
package com.frame.sys.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.sys.entity.LogLogin;
import com.frame.sys.service.ILogLoginService;

@Controller
@RequestMapping("/webmaster/sys/loglogin")
public class LogLoginController extends BaseController<LogLogin, String> {

    @Resource
    private ILogLoginService logLoginService;

    @Override
    protected IBaseService<LogLogin, String> getBaseService() {
	return logLoginService;
    }

}
