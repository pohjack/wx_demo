/**
 * @Title: InitDataListener.java
 * @Package com.frame.core.interceptor
 * @Description: 启动web容器加载数据
 * @author: lzl
 * @date 2016年7月13日 下午6:40:39
 * @version V1.0
 */
package com.frame.core.interceptor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.frame.core.commons.utils.EHCacheUtil;
import com.frame.sys.entity.Resources;
import com.frame.sys.service.IResourcesService;
import com.frame.tobaCase.entity.Law;
import com.frame.tobaCase.service.ILawService;

@Component
public class InitDataListener implements ApplicationListener<ContextRefreshedEvent>{
    
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private IResourcesService resourcesService;
    @Resource
    private ILawService lawService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){
        logger.info("启动加载数据");
        List<Resources> resourcesList = resourcesService.findAll();
        Map<String, Map<String, String>> map = new HashMap<String, Map<String,String>>();
       
        logger.info("启动加载资源数据==="+resourcesList.size());
        EHCacheUtil.initCacheManager().getCache("resourceCache");// 根据配置文件 ehcahce.xml 的缓存名字获取缓存对象
        EHCacheUtil.put("resourcesList",resourcesList);// 设置缓存
      }
}
