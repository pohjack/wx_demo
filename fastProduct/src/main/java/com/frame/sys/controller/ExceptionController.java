/**    
* @Title: ExceptionController.java
* @Package com.frame.sys.controller
* @Description: TODO(用一句话描述该文件做什么)
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
import com.frame.sys.entity.LogException;
import com.frame.sys.service.ILogExceptionService;

@Controller
@RequestMapping("/webmaster/sys/logexception")
public class ExceptionController extends BaseController<LogException, String> {

    @Resource
    private ILogExceptionService logExceptionService;

    @Override
    protected IBaseService<LogException, String> getBaseService() {
	return logExceptionService;
    }

}
