/**
 * @Title: LogExceptionServiceImpl.java
 * @Package com.frame.sys.service.impl
 * @Description: 异常service实现类
 * @author: shizh
 * @date 2016年11月1日 下午5:43:30
 * @version V1.0
 */
package com.frame.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.ILogLoginDao;
import com.frame.sys.entity.LogLogin;
import com.frame.sys.service.ILogLoginService;

@Service("logLoginService")
public class LogLoginServiceImpl extends BaseServiceImpl<LogLogin, String>implements ILogLoginService{
    
    @Resource
    private ILogLoginDao logLoginDao;
    
    @Override
    protected IMybaitsBaseDao<LogLogin, String> getBaseDao(){
        return logLoginDao;
    }
    
}
