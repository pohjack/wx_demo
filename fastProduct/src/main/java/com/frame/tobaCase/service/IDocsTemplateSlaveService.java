/**    
* @Title: IDocsTemplateSlaveService.java
* @Package com.frame.tobaCase.service
* @Description: 文书模板从表service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.DocsTemplateSlave;

public interface IDocsTemplateSlaveService extends IBaseService<DocsTemplateSlave, String> {

    /**
     * 
    * @Description: 根据主表id，查询该id对应的所有基础模板
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月13日 下午5:25:01
    * @throws
     */
    List<DocsTemplateSlave> findByMasterId(String id, int tag);

    /**
     * 
    * @Description: 保存基础模板到文书模板
    * @param @param dSlave
    * @param @return
    * @author shizh
    * @date 2017年3月10日 下午2:28:43
    * @throws
     */
    int addBaseToDoc(DocsTemplateSlave dSlave);

    /**
     * 
    * @Description: 文书模板包含的基础模板排序
    * @param @param baseId
    * @param @param baseIdAfter
    * @param @param modelNo
    * @param @return
    * @author shizh
    * @date 2017年3月10日 下午4:01:12
    * @throws
     */
    int changeSort(String baseId, String baseIdAfter);
}
