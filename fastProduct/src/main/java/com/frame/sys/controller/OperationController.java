/**    
* @Title: OperationController.java
* @Package com.frame.sys.controller
* @Description: 操作日志功能controller
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
import com.frame.sys.entity.LogOperation;
import com.frame.sys.service.ILogOperationService;

@Controller
@RequestMapping("/webmaster/sys/logoperation")
public class OperationController extends BaseController<LogOperation, String> {

    @Resource
    private ILogOperationService logOperationService;

    @Override
    protected IBaseService<LogOperation, String> getBaseService() {
	return logOperationService;
    }

}
