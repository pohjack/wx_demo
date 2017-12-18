/**
 * @Title: IResourcesService.java
 * @Package com.frame.sys.service
 * @Description: 系统资源服务接口
 * @author: lzl
 * @date 2016年7月13日 下午6:52:20
 * @version V1.0
 */
package com.frame.sys.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Resources;

public interface IResourcesService extends IBaseService<Resources,String>{
    
    /**  
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @return
    * @author: lzl
    * @date 2016年7月18日 下午5:46:29
    * @throws
    */
    List<Resources> getResources(List<Resources> resourcesList) ;
    
    /**
     * 
    * @Description: 通过访问路径查找资源名
    * @param @param uri
    * @param @return
    * @author ShiZH
    * @date 2016年11月16日 上午11:26:09
    * @throws
     */
    Resources findByURI(String uri);
    
    /**
	* @Description: 禁用菜单
	* @param @param id
	* @param @param status
	* @author: lpy
	* @date 2016年10月26日 下午5:00:06
	* @throws
	 */
	public void changeState(Map<String, Object> map);
	
	/**
	* @Description: 保存资源，连带保存到admin上
	* @param @param res
	* @author: lpy
	* @date 2016年11月17日 下午4:27:44
	* @throws
	 */
	public void mysave(Resources res);
	
	/**
	* @Description: 删除资源，连带其角色拥有的资源
	* @param @param resIds
	* @author: lpy
	* @date 2016年11月17日 下午4:33:45
	* @throws
	 */
	public void myRemove(String[] resIds);
}
