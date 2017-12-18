/**
 * @Title: IMybaitsBaseDao.java
 * @Package com.frame.core.dao
 * @Description: TODO(用一句话描述该文件做什么)
 * @author: lzl
 * @date 2016年7月13日 下午6:55:17
 * @version V1.0
 */
package com.frame.core.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.SecurityUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.frame.core.commons.ReflectionUtils;
import com.frame.core.commons.sqlUtils.Constant;
import com.frame.core.commons.utils.UUIDUtil;
import com.frame.core.entity.BizBaseEntity;
import com.frame.core.entity.JsTreeNode;
import com.frame.core.entity.TreeNode;
import com.frame.sys.entity.ShiroUser;

@MappedSuperclass
public class MybaitsBaseDaoImpl<T extends BizBaseEntity,ID extends Serializable> extends SqlSessionDaoSupport implements IMybaitsBaseDao<T,ID>{
    
    private Class<T> clazz;
    
    /**
     * 获取 SqlMapping 的命名空间
     */
    private String sqlNamespace = getDefaultSqlNamespace();
    
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 
    * @Description: 获取默认SqlMapping命名空间
    * 使用泛型参数中业务实体类型的全限定名作为默认的命名空间
    * 如果实际应用中需要特殊的命名空间，可由子类重写该方法实现自己的命名空间规则
    * @param @return 返回命名空间字符串
    * @author: Shizh
    * @date 2016年10月21日 上午10:47:06
    * @throws
     */
    @SuppressWarnings("unchecked")
    protected String getDefaultSqlNamespace(){
        clazz = ReflectionUtils.getClassGenricType(this.getClass());
        return clazz.getName();
    }
    
    /**
     * 
    * @Description: 将SqlMapping命名空间与给定的SqlMapping名组合在一起
    * @param @param sqlName SqlMapping名
    * @param @return 组合了SqlMapping命名空间后的完整SqlMapping名
    * @author: Shizh
    * @date 2016年10月25日 上午10:49:12
    * @throws
     */
    protected String getSqlName(String sqlName){
        return sqlNamespace+Constant.SQLNAME_SEPARATOR+sqlName;
    }
    
    public String getSqlNamespace(){
        return sqlNamespace;
    }
    
    public void setSqlNamespace(String sqlNamespace){
        this.sqlNamespace = sqlNamespace;
    }
    /**
     * 
    * @Description: 生成主键值
    * 默认情况下什么也不做,如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值
    * @param @param item 要持久化的对象
    * @author: Shizh
    * @date 2016年10月25日 上午10:50:33
    * @throws
     */
    protected void generateId(T item){
        item.setId(UUIDUtil.get32UUID());
    }
  
    /**
     * 
    * @Description: 生成创建者
    * @param @param item
    * @author shizh
    * @date 2016年11月23日 上午10:01:45
    * @throws
     */
    protected void generateCreator(T item) {
		item.setCreator(getCurrentUser());
	}
    
    /**
     * 
    * @Description: 生成更新者
    * @param @param item
    * @author shizh
    * @date 2016年11月23日 上午10:16:43
    * @throws
     */
    protected void generateUpdator(T item) {
    	item.setUpdator(getCurrentUser());
	}
    /**
     * 
    * @Description: 获取当前用户id
    * @param @return
    * @author shizh
    * @date 2016年11月23日 上午10:19:29
    * @throws
     */
    protected String getCurrentUser() {
    	ShiroUser user = (ShiroUser)(SecurityUtils.getSubject().getPrincipal());
    	return user==null?null:user.id;
	}
    
    /**
     * 
    * @Description: 查询所有数据
    * @param @param 
    * @author: Shizh
    * @date 2016年10月25日 上午10:53:52
    * @throws
     */
    @Override
    public List<T> findAll(){
        return getSqlSession().selectList(getSqlName(Constant.MAPPING_ID_FINDALL));
    }
    
    /**  
    * @Description: 根据mybatis配置id获取数据列表 
    * @param @param keyId mybaits配置id
    * @param @param parameter 参数
    * @param @return
    * @author: lizeling
    * @date 2016年7月13日 下午7:30:22
    * @throws
    */
    protected List<T> selectList(String keyId,Object parameter){
        return this.getSqlSession().selectList(getSqlName(keyId),parameter);
    }
    
    /**  
    * @Description: 根据mybatis配置id获取数据列表 返回map
    * @param @param keyId
    * @param @param parameter
    * @param @return
    * @author: zk
    * @date 2016年7月13日 下午7:31:39
    * @throws
    */
    protected List<Map<String,Object>> selectListMap(String keyId,Object parameter){
        return this.getSqlSession().selectList(getSqlName(keyId),parameter);
    }
    
    /**
     * 
    * @Description: 通过id查询业务数据
    * @param @param id 
    * @author: Shizh
    * @date 2016年10月25日 上午10:55:12
    * @throws
     */
    @Override
    public T findById(ID id){
        return this.getSqlSession().selectOne(getSqlName(Constant.MAPPING_ID_FINDBYID),id);
    }
    /**
     * 
    * @Description: 查询数据总条数
    * @param @param 
    * @author: Shizh
    * @date 2016年10月25日 上午10:56:18
    * @throws
     */
    @Override
    public Integer findAllTotal(){
        return this.getSqlSession().selectOne(getSqlName(Constant.MAPPING_ID_FINDALLTOTAL));
    }
    
    @Override
    public List<Map<String,Object>> queryByPage(Object parameter){
        return getSqlSession().selectList(getSqlName(Constant.MAPPING_ID_FINDBYPAGE),parameter);
    }

    /**
     * 
    * @Description: 删除数据，包含单个删除和批量删除
    * @param @param ids 要删除的id数组
    * @author: Shizh
    * @date 2016年10月25日 上午11:02:07
    * @throws
     */
	@Override
	public Integer delete(String[] ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("updator", getCurrentUser());
		map.put("updated", new Date());
		return getSqlSession().update(getSqlName(Constant.MAPPING_IDS_DELETE), map);
	}

	 /**
     * 
    * @Description: 保存数据
    * @param @param obj 要保存的数据实体对象
    * @author: Shizh
    * @date 2016年10月25日 上午11:05:58
    * @throws
     */
	@Override
	public Integer save(T obj) {
		if(StringUtils.isEmpty(obj.getId()))
			this.generateId(obj);
			this.generateCreator(obj);
			this.generateUpdator(obj);
		return getSqlSession().insert(getSqlName(Constant.MAPPING_ID_SAVE), obj);
	}
	
	 /**
     * 
    * @Description: 修改数据
    * @param @param obj 要修改的实体数据对象
    * @author: Shizh
    * @date 2016年10月25日 上午11:06:35
    * @throws
     */
	@Override
	public Integer update(T obj) {
		this.generateUpdator(obj);
		return getSqlSession().update(getSqlName(Constant.MAPPING_ID_UPDATE), obj);
	}

	 /**
     * 
    * @Description: 获取树结构数据
    * @param @param 
    * @author: Shizh
    * @date 2016年10月25日 上午11:07:48
    * @throws
     */
	@Override
	public List<TreeNode> getTreeNodes() {
		return getSqlSession().selectList(getSqlName(Constant.MAPPING_ID_FINDTREE));
	}
	 /**
     * 
    * @Description: 根据条件查询数据总数
    * @param @param 
    * @author: Shizh
    * @date 2016年10月25日 上午11:57:38
    * @throws
     */
	@Override
	public Integer findAllTotal(Object parameter) {
		return this.getSqlSession().selectOne(getSqlName(Constant.MAPPING_ID_FINDALLTOTAL), parameter);
	}
	 /**
     * 
    * @Description: 根据条件查找所有数据
    * @param @param 
    * @author: Shizh
    * @date 2016年10月25日 上午11:57:13
    * @throws
     */
	@Override
	public List<T> findAll(Object parameter) {
		return getSqlSession().selectList(getSqlName(Constant.MAPPING_ID_FINDALL), parameter);
	}
	
	 /**
     * 
    * @Description: 删除数据
    * @author: Shizh
    * @date 2016年10月25日 上午11:02:07
    * @throws
     */
	@Override
	public Integer deleteAll() {
		return getSqlSession().delete(getSqlName(Constant.MAPPING_DELETE_ALL));
			
	}
	@Override
	public List<JsTreeNode> getJsTreeNodes() {
		// TODO
		return getSqlSession().selectList(getSqlName(Constant.MAPPING_ID_FINDTREE));
			
	}
}
