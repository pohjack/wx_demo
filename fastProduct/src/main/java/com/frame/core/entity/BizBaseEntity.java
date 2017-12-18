/**   
*     
* 类名称：BizBaseEntity   
* 类描述：   业务实体基类。所有的业务实体类继承此类
* 创建人：jekoll  
* 创建时间：2016年3月20日 下午11:14:26   
* 修改人：jekoll   
* 修改时间：2016年3月20日 下午11:14:26   
* 修改备注：   
* @version  v1.0 
*    
*/
package com.frame.core.entity;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import org.springframework.format.annotation.DateTimeFormat;


@MappedSuperclass
public abstract class BizBaseEntity extends IdEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * @Fields creator : 创建者
     */
   
    protected String creator;
    
    /**
     * @Fields updator : 修改者
     */
   
    protected String updator;
    
    /**
     * @Fields created : 创建时间
     */

	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    protected Date created = new Date ();
    
    /**
     * @Fields updated : 更新时间
     */

	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm:ss")
    protected Date updated = new Date();
    
    /**
     * @Fields sort : 排序,默认值为0,越大越排前面
     */
    
    private Integer sort = 0;
    
    /**
     * @Fields remark : 备注
     */
 
    protected String remark;
    
    /**
     * @Fields status : 状态1启用，0禁用
     */
   
    protected Integer status = 1;
    
    /**
     * @Fields delStatue : 删除标记1已删除 0未删除 默认0
     */

    protected Integer removeStatue = 0;
    
    public String getCreator(){
        return creator;
    }
    
    public void setCreator(String creator){
        this.creator = creator;
    }
    
    public String getUpdator(){
        return updator;
    }
    
    public void setUpdator(String updator){
        this.updator = updator;
    }

    public Date getCreated(){
        return created;
    }
    
    public void setCreated(Date created){
        this.created = created;
    }

    public Date getUpdated(){
        return updated;
    }
    
    public void setUpdated(Date updated){
        this.updated = updated;
    }
    
    public Integer getSort(){
        return sort;
    }
    
    public void setSort(Integer sort){
        this.sort = sort;
    }
    
    public String getRemark(){
        return remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }
    
    public Integer getStatus(){
        return status;
    }
    
    public void setStatus(Integer status){
        this.status = status;
    }
    
    public Integer getRemoveStatue(){
        return removeStatue;
    }
    
    public void setRemoveStatue(Integer removeStatue){
        this.removeStatue = removeStatue;
    }
}
