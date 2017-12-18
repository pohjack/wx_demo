/**    
* @Title: ICaseTempHistoryService.java
* @Package com.frame.tobaCase.service
* @Description: 基础模板service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseTempHistory;

public interface ICaseTempHistoryService extends IBaseService<CaseTempHistory, String> {

    /**
     * 
    * @Description: 验证该案件该模板是否有历史记录
    * @param @param caseId 案件ID
    * @param @param modelNo 模板ID
    * @param @return
    * @author shizh
    * @date 2017年3月2日 下午8:36:43
    * @throws
     */
    CaseTempHistory checkHistory(String caseId, String modelNo);

    /**
     * 
    * @Description: 保存案件对应的文书模板到历史记录表
    * @param @param caseTempHistory
    * @param @return
    * @author shizh
    * @date 2017年3月3日 下午12:06:51
    * @throws
     */
    int saveCaseTemp(CaseTempHistory caseTempHistory) throws Exception;
}
