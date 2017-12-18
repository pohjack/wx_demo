/**    
* @Title: IBaseTemplateSlaveDao.java
* @Package com.frame.tobaCase.dao
* @Description: 基础模板从表dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.BaseTemplateSlave;

public interface IBaseTemplateSlaveDao extends IMybaitsBaseDao<BaseTemplateSlave, String> {

    /**
     * 
    * @Description: 通过主表ID查询对应的数据字典数据
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月13日 下午6:21:03
    * @throws
     */
    List<BaseTemplateSlave> findByMasterId(String id);

    /**
     * 
    * @Description: 删除修改的临时数据
    * @param @param tid
    * @author shizh
    * @date 2017年2月28日 下午8:33:17
    * @throws
     */
    void removeTempData(String tid);
}
