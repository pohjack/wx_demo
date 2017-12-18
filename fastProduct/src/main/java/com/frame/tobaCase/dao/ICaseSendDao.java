/**    
* @Title: ICaseCigarDao.java
* @Package com.frame.tobaCase.dao
* @Description: 涉案卷烟dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseSend;

public interface ICaseSendDao extends IMybaitsBaseDao<CaseSend, String> {

    /**
     * 
    * @Description: 查询案件的某一送达
    * @param @param caseId
    * @param @param cigarId
    * @param @return
    * @author yuyf
    * @date 2017年5月9日 上午10:21:43
    * @throws
     */
    CaseSend findByCaseIdAndCode(String caseId, String code);

}
