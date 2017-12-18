/**    
* @Title: ICaseEvidenceService.java
* @Package com.frame.tobaCase.service
* @Description: 证据信息service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseEvidence;

public interface ICaseEvidenceService extends IBaseService<CaseEvidence, String> {

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
    * @Description: 保存案件证据
    * @param @param i  ,j
    * @param @param files
    * @author: liy
    * @date 2017年2月24日 上午10:23:40
    * @throws
     */
    int saveCaseFileInfo(int flag, CaseEvidence ce, int j);

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
