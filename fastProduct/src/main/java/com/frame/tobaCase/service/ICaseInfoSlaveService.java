/**    
* @Title: ICaseInfoService.java
* @Package com.frame.tobaCase.service
* @Description: 案件信息从类service接口
* @author: linpy
* @date 2017年2月20日 下午2:56:59
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseInfoSlave;

public interface ICaseInfoSlaveService extends IBaseService<CaseInfoSlave, String> {

    /**
     * 
    * @Description: 根据案件id查询涉案信息
    * @param @param id
    * @param @return
    * @author shizh
    * @date 2017年2月14日 上午10:32:56
    * @throws
     */
    Map<String, Object> findByCaseId(String id);

    /**
     * 
    * @Description: 根据案件信息主表ID查询从表信息
    * @param @param caseId
    * @param @return
    * @author shizh
    * @date 2017年2月24日 下午2:09:39
    * @throws
     */
    CaseInfoSlave findByMasterId(String caseId);
}
