/**    
* @Title: ICaseInvoPersDao.java
* @Package com.frame.tobaCase.dao
* @Description: 案件信息dao接口
* @author: linpy
* @date 2017年2月30日 下午2:55:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.Map;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseInvoPers;

public interface ICaseInvoPersDao extends IMybaitsBaseDao<CaseInvoPers, String> {

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
    * @Description: 根据许可证号码查询涉案人员
    * @param @param licNo
    * @param @return
    * @author: lpy
    * @date 2017年2月28日 上午11:53:16
    * @throws
     */
    public CaseInvoPers findByLicNo(String licNo);

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
