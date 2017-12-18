/**
 * @Title: RoleServiceImpl.java
 * @Package com.frame.sys.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 上午11:14:55
 * @version V1.0
 */
package com.frame.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.EHCacheUtil;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IRoleDao;
import com.frame.sys.dao.IRoleResourceDao;
import com.frame.sys.entity.Resources;
import com.frame.sys.entity.Role;
import com.frame.sys.entity.RoleResource;
import com.frame.sys.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role,String>implements IRoleService{
    
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IRoleResourceDao roleResourceDao;
    
    @Override
    protected IMybaitsBaseDao<Role,String> getBaseDao(){
        return roleDao;
    }
    
    @Resource
    private ResourcesServiceImpl resourcesService;
    
    @Override
    public List<Role> findRoleIdByUserId(String userId){
        return roleDao.findRoleIdByUserId(userId);
    }
    
    @Override
    public List<Map<String,Object>> findResourceByRoleId(String id){
        return roleDao.findResourceByRoleId(id);
    }
    
	/**
	* @Description: 根据用户的角色获取能访问的资源
	* @param @param roleList 角色列表
	* @param @return
	* @author: shizh
	* @date 2016年10月28日 下午3:31:26
	* @throws
	 */
    @Override
    public Map<String,String> getUserRoleResource(List<Role> roleList){
        Map<String,String> menuMap = new HashMap<String,String>();
        for(Role role:roleList){
            List<Map<String,Object>> resourceList = findResourceByRoleId(role.getId());
            for(Map<String,Object> map:resourceList){
                if(map.get("menuType").toString().equals("0")) {
                    if(menuMap.get(map.get(map.get("menuId").toString()))==null) {
                        menuMap.put(map.get("menuId").toString(),map.toString());
                    }
                }
            }
        }
        return menuMap;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Resources> getUserResource(List<Role> roleList){
        Map<String,String> menuMap = getUserRoleResource(roleList);
        EHCacheUtil.initCacheManager().getCache("resourceCache");// 根据配置文件 ehcahce.xml 的缓存名字获取缓存对象
        List<Resources> allResources = (List<Resources>)EHCacheUtil.get("resourcesList");
        List<Resources> cuurUserResources = new ArrayList<Resources>();
        for(Resources resources:allResources){
            if(menuMap.get(resources.getId())!=null) {
                cuurUserResources.add(resources);
            }
        }
        return resourcesService.getResources(cuurUserResources);
    }

	@Override
	public int changeState(Map<String, Object> map){
		return roleDao.changeState(map);
	}

	@Override
	public int isSameName(String name){
		return roleDao.isSameName(name);
	}

	@Override
	public void removeRole(String[] roleIds){
		roleDao.delete(roleIds);
		roleResourceDao.deleteByRoleId(roleIds);
	}

	@Override
	public void mySave(Role role, String[] resIds){
		String id = role.getId();
		if(id==null || "".equals(id)) {
			id=UUIDUtil.get32UUID();
    		role.setId(id);
    		roleDao.save(role);
    		RoleResource r = new RoleResource();
    		r.setRoleId(id);
    		if(resIds!=null && resIds.length>0) {
    			for(int i=0; i<resIds.length; i++) {
        			r.setResourceId(resIds[i]);
        			roleResourceDao.save(r);
        		}
    		}
		} else {
			roleDao.update(role);
    	}
	}
}
