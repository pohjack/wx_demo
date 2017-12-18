/**    
* @Title: IBaseTemplateService.java
* @Package com.frame.tobaCase.service
* @Description: 基础模板service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;
import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.BaseTemplate;

public interface IBaseTemplateService extends IBaseService<BaseTemplate, String> {

    /**
     * 
    * @Description: 根据文书模板ID关联查询包含的所有基础模板信息
    * @param @param modelNo 文书模板id
    * @param @return
    * @author shizh
    * @date 2017年2月24日 下午3:45:16
    * @throws
     */
    List<BaseTemplate> findBaseByDocId(String modelNo);

    /**
     * 
    * @Description: 根据文书模板ID关联查询包含的所有基础模板信息
    * @param @param modelNo
    * @param @return
    * @author shizh
    * @date 2017年3月10日 下午5:16:29
    * @throws
     */
    List<Map<String, Object>> findBaseInfo(String modelNo, int tag);
}
