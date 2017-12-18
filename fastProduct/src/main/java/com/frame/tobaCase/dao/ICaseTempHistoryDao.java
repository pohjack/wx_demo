/**    
* @Title: ICaseTempHistoryDao.java
* @Package com.frame.tobaCase.dao
* @Description: 基础模板dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CaseTempHistory;

public interface ICaseTempHistoryDao extends IMybaitsBaseDao<CaseTempHistory, String> {

    /**
     * 
    * @Description: 验证该案件该模板是否有历史记录
    * @param @param caseTempHistory 封装了案件ID和模板ID
    * @param @return
    * @author shizh
    * @date 2017年3月2日 下午8:36:43
    * @throws
     */
    CaseTempHistory checkHistory(CaseTempHistory caseTempHistory);
}
