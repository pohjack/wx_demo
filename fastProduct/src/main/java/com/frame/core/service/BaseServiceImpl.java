/**
 * @Title: BaseServiceImpl.java
 * @Package com.frame.core.service
 * @Description: baseService封装
 * @author: lzl
 * @date 2016年7月13日 下午6:55:17
 * @version V1.0
 */
package com.frame.core.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import com.frame.core.commons.result.DateTablesResult;
import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.core.entity.IdEntity;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.entity.TreeNode;

@MappedSuperclass
public abstract class BaseServiceImpl<T extends IdEntity,ID extends Serializable> implements IBaseService<T,ID>{
    
    protected abstract IMybaitsBaseDao<T,ID> getBaseDao();
    
    @Override
    public List<T> findAll(){
        return getBaseDao().findAll();
    }
    
    @Override
    public  DateTablesResult<Map<String,Object>>  queryByPage(DateTablesResult<Map<String,Object>> datatable){
        datatable.setAaData(getBaseDao().queryByPage(datatable));
        datatable.setRecordsTotal(getBaseDao().findAllTotal(datatable));
        datatable.setRecordsFiltered(datatable.getRecordsTotal());
        return datatable;
    }
    
    @Override
    public T findById(ID id){
    	return getBaseDao().findById(id);
    }
    
    @Override
    public Integer remove(String[] ids){
    	return getBaseDao().delete(ids);
    }
    
    @Override
    public Integer removeAll(){
    	return getBaseDao().deleteAll();
    }
    
    @Override
    public Integer save(T obj){
    	return getBaseDao().save(obj);
    }
    
    @Override
    public Integer update(T obj){
    	return getBaseDao().update(obj);
    }
    @Override
    public List<TreeNode> findTreeNodes(){
    	return getBaseDao().getTreeNodes();
    }

    @Override
    public List<JsTreeNode> findJsTreeNodes(){
    	return getBaseDao().getJsTreeNodes();
    }
	@Override
	public Integer findAllTotal(Object parameter) {
		return getBaseDao().findAllTotal(parameter);
	}

	@Override
	public Integer findAllTotal() {
		return getBaseDao().findAllTotal();
	}

	@Override
	public List<T> findAll(Object parameter) {
		return getBaseDao().findAll(parameter);
	}
}
