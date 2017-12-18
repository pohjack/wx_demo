/**    
* @Title: ICaseInvoPersService.java
* @Package com.frame.tobaCase.service
* @Description: 案件信息service接口
* @author: linpy
* @date 2017年2月20日 下午2:56:59
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.Map;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CaseInvoPers;

public interface ICaseInvoPersService extends IBaseService<CaseInvoPers, String> {

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
    * @Description: 保存涉案人员
    * @param @param pers
    * @param @param caseInfoId
    * @param @return
    * @author: lpy
    * @date 2017年2月27日 下午5:35:19
    * @throws
     */
    public String saveCaseInvo(CaseInvoPers pers, String caseInfoId);

    /**
    * @Description: 根据许可证号码查询涉案人员
    * @param @param licNo
    * @param @return
    * @author: lpy
    * @date 2017年2月28日 上午11:53:16
    * @throws
     */
    public CaseInvoPers findByLicNo(String licNo);

    /**
    * @Description: 修改涉案人员
    * @param @param pers
    * @param @return
    * @author: lpy
    * @date 2017年3月2日 下午3:21:50
    * @throws
     */
    public int updateCaseInvo(CaseInvoPers pers);

    /**
    * @Description: 根据案件信息编号查询涉案人员数量
    * @param @param caseInfoId
    * @param @return
    * @author: lpy
    * @date 2017年4月18日 下午2:47:49
    * @throws
     */
    public int findCountByCaseId(String caseInfoId);
}
