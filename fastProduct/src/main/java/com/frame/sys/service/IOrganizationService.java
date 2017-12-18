package com.frame.sys.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.sys.entity.Organization;

public interface IOrganizationService extends IBaseService<Organization, String>{
	
	
	/**
	* @Description: 禁用部门
	* @param @param id
	* @param @param status
	* @author: lpy
	* @date 2016年10月26日 下午5:00:06
	* @throws
	 */
	public void changeState(Map<String, Object> map);
	
	
	/**
	 * 
	* @Description: 根据机构id 查询子集机构
	* @param @param id
	* @param @return
	* @author: liy
	* @date 2017年3月17日 下午7:11:33
	* @throws
	 */
	public List<String> findOrgIdById(String id);
	
	
}
