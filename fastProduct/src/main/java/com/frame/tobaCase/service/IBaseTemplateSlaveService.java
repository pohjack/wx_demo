/**    
* @Title: IBaseTemplateSlaveService.java
* @Package com.frame.tobaCase.service
* @Description: 基础模板从表service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.BaseTemplateSlave;

public interface IBaseTemplateSlaveService extends IBaseService<BaseTemplateSlave, String> {

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
     * @Description: 将查询得到的数据组合之后返回
     * @param @param id
     * @param @return
     * @author shizh
     * @date 2017年2月13日 下午6:21:03
     * @throws
     */
    String templateComb(String id);

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
