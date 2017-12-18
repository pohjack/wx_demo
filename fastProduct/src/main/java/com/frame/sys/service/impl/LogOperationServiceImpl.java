/**
 * @Title: LogOperationServiceImpl.java
 * @Package com.frame.sys.service.impl
 * @Description: 操作日志service实现类
 * @author: shizh
 * @date 2016年11月1日 下午5:43:30
 * @version V1.0
 */
package com.frame.sys.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.ILogOperationDao;
import com.frame.sys.entity.LogOperation;
import com.frame.sys.service.ILogOperationService;

@Service("logOperationService")
public class LogOperationServiceImpl extends BaseServiceImpl<LogOperation, String>implements ILogOperationService{
    
    @Resource
    private ILogOperationDao logOperationDao;
    
    @Override
    protected IMybaitsBaseDao<LogOperation, String> getBaseDao(){
        return logOperationDao;
    }
    
}
