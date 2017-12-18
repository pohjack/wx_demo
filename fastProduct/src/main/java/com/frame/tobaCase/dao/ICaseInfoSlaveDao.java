/**    
* @Title: ICaseInfoSlaveDao.java
* @Package com.frame.tobaCase.dao
* @Description: 案件信息从类dao接口
* @author: linpy
* @date 2017年2月30日 下午2:55:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseInfoSlave;

public interface ICaseInfoSlaveDao extends IMybaitsBaseDao<CaseInfoSlave, String> {

    /**
     * 
    * @Description: 根据案件id查询案件信息
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
