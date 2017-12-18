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
import com.frame.sys.dao.ILogExceptionDao;
import com.frame.sys.entity.LogException;
import com.frame.sys.service.ILogExceptionService;

@Service("logExceptionService")
public class LogExceptionServiceImpl extends BaseServiceImpl<LogException, String>implements ILogExceptionService{
    
    @Resource
    private ILogExceptionDao logExceptionDao;
    
    @Override
    protected IMybaitsBaseDao<LogException, String> getBaseDao(){
        return logExceptionDao;
    }
    
}
