/**    
* @Title: DictionaryDataController.java
* @Package com.frame.tobaCase.controller
* @Description: 数据字典控制层
* @author: liy
* @date 2017年2月22日 下午5:44:31
* @version V1.0
*/
package com.frame.tobaCase.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.frame.core.controller.BaseController;
import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.DictionaryData;
import com.frame.tobaCase.service.IDictionaryDataService;

@Controller
@RequestMapping("webmaster/dictData")
public class DictionaryDataController extends BaseController<DictionaryData, String> {
    @Resource
    private IDictionaryDataService dictionaryDataService;

    @Override
    protected IBaseService<DictionaryData, String> getBaseService() {
	return dictionaryDataService;
    }
}
