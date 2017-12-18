/**    
* @Title: ICaseEvidenceDao.java
* @Package com.frame.tobaCase.dao
* @Description: 证据信息dao接口
* @author: shizh
* @date 2017年2月21日 下午2:55:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;
import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseEvidence;

public interface ICaseEvidenceDao extends IMybaitsBaseDao<CaseEvidence, String> {

    /**
     * 
    * @Description: 查询证据数量
    * @param @return
    * @author shizh
    * @date 2017年2月22日 下午6:31:49
    * @throws
     */
    List<Map<String, Integer>> countByCaseId(String id);

    /**
     * 
    * @Description:根据案件Id 查询图片或文件路径
    * @param @param caseId
    * @param @return
    * @author: liy
    * @date 2017年2月24日 上午11:50:34
    * @throws
     */

    public List<CaseEvidence> findByCaseInfoId(String caseId, Integer index);

    List<Map<String, Object>> countEvidence(String id);

    /**
     * 
    * @Description:删除委托人证据
    * @param @param caseInfoId
    * @param @return
    * @author: liy
    * @date 2017年3月21日 下午1:41:40
    * @throws
     */
    int deleteByArgs(String caseInfoId);

}
