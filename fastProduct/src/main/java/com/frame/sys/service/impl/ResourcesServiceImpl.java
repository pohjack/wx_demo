/**
 * @Title: ResourcesServiceImpl.java
 * @Package com.frame.sys.service.impl
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 下午6:53:40
 * @version V1.0
 */
package com.frame.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.service.BaseServiceImpl;
import com.frame.sys.dao.IResourcesDao;
import com.frame.sys.dao.IRoleResourceDao;
import com.frame.sys.entity.Resources;
import com.frame.sys.entity.RoleResource;
import com.frame.sys.service.IResourcesService;

@Service("resourcesService")
public class ResourcesServiceImpl extends BaseServiceImpl<Resources,String>implements IResourcesService{
    
    @Resource
    private IResourcesDao resourcesDao;
	@Resource
	IRoleResourceDao roleResourceDao;
    
    @Override
    protected IMybaitsBaseDao<Resources,String> getBaseDao(){
        return resourcesDao;
    }
    
    /**
     * 
    * @Description: 封装菜单数据，得到类似树结构的分级菜单list列表
    * @param @param resourcesList 当前用户所有菜单列表
    * @param @return
    * @author: Shizh
    * @date 2016年10月27日 下午3:57:34
    * @throws
     */
    @Override
    public List<Resources> getResources(List<Resources> resourcesList){
        List<Resources> menuList = new ArrayList<Resources>();
        int cuurUserResourcessize = resourcesList.size();
        if(cuurUserResourcessize>0) {
            for(Resources resources:resourcesList){
                if(resources.getResPid().equals("0")) {
                	List<Resources> subResources = getSubResources(resources.getId(),resourcesList);
                	if(subResources.size() > 0){
                		resources.setHasSub(true);
                	}else{
                		resources.setHasSub(false);
                	}
                    resources.setSubMenu(subResources);
                    menuList.add(resources);
                }
            }
        }
        return menuList;
    }
    
    /**
     * 
    * @Description: 获取子菜单列表
    * @param @param id 父菜单id
    * @param @param allResourcesList 所有菜单数据
    * @param @return
    * @author: Shizh
    * @date 2016年10月27日 下午4:01:46
    * @throws
     */
    private List<Resources> getSubResources(String id,List<Resources> allResourcesList){
        List<Resources> subLists = new ArrayList<Resources>();
        for(Resources resources:allResourcesList){
            if(resources.getResPid().equals(id)) {
            	List<Resources> subResources = getSubResources(resources.getId(),allResourcesList);
                if(subResources.size()>0) {
                    resources.setHasSub(true);
                }else{
                    resources.setHasSub(false);
                }
                resources.setSubMenu(subResources);
                subLists.add(resources);
            }
        }
        return subLists;
    }

    public Resources findByURI(String uri){
    	return resourcesDao.findByURI(uri);
    }
    
    @Override
	public void changeState(Map<String, Object> map){
    	resourcesDao.changeState(map);
	}

	@Override
	public void mysave(Resources res){
		String id = UUIDUtil.get32UUID();
		res.setId(id);
		resourcesDao.save(res);						//保存资源
		RoleResource r = new RoleResource();
		r.setRoleId("1");
		r.setResourceId(id);
		roleResourceDao.save(r);					//关联admin
	}

	@Override
	public void myRemove(String[] resIds){
		resourcesDao.delete(resIds);
		roleResourceDao.deleteByResId(resIds);
	}
}
