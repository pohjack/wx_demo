/**    
* @Title: IDocsTemplateSlaveDao.java
* @Package com.frame.tobaCase.dao
* @Description: 文书模板从表dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.DocsTemplateSlave;

public interface IDocsTemplateSlaveDao extends IMybaitsBaseDao<DocsTemplateSlave, String> {

    /**
     * 
    * @Description: 根据主表id，查询该id对应的所有基础模板
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月13日 下午5:25:01
    * @throws
     */
    List<DocsTemplateSlave> findByMasterId(Map<String, Object> map);

    /**
     * 
    * @Description: 获取文书模板中基础模板的最大排序
    * @param @param masterId
    * @param @return
    * @author shizh
    * @date 2017年3月10日 下午2:30:16
    * @throws
     */
    int getTopSort(String masterId);

    /**
     * 
    * @Description: 更新排序
    * @param @param docsTemplateSlave
    * @param @return
    * @author shizh
    * @date 2017年3月10日 下午4:06:44
    * @throws
     */
    int updateSort(DocsTemplateSlave docsTemplateSlave);
}
