/**    
* @Title: IDictionaryDataService.java
* @Package com.frame.tobaCase.service
* @Description: 数据字典service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.DictionaryData;

public interface IDictionaryDataService extends IBaseService<DictionaryData, String> {

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
