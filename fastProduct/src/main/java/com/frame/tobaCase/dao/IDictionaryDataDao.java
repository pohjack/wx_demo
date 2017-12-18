/**    
* @Title: IDictionaryDataDao.java
* @Package com.frame.tobaCase.dao
* @Description: 数据字典dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.DictionaryData;

public interface IDictionaryDataDao extends IMybaitsBaseDao<DictionaryData, String> {

    /**
     * 
    * @Description: 根据code查询数据字典数据
    * @param @param code
    * @param @return
    * @author shizh
    * @date 2017年2月15日 下午4:57:18
    * @throws
     */
    DictionaryData findByCode(String code);
}
