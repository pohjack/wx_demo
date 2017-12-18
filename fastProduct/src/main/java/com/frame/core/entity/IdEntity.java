/**   
*     
* 类名称：IdEntity   
* 类描述：   统一定义id的entity基类， 基类统一定义id的属性名称、数据类型、列名映射及生成策略，
* 子类可重载getId()函数重定义id的列名映射和生成策略，本系统采用UUID主键策略
* 创建人：jekoll  
* 创建时间：2016年3月20日 下午8:51:07   
* 修改人：jekoll   
* 修改时间：2016年3月20日 下午8:51:07   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class IdEntity implements Serializable{
    
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    protected String id;
    
    /**
     * @Fields searchValue : 全局搜索值，用户封装，不持久化到数据库
     */
    @Transient
    private String searchValue;
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public String getSearchValue(){
        return searchValue;
    }
    
    public void setSearchValue(String searchValue){
        this.searchValue = searchValue;
    }
}
