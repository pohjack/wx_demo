/**    
* @Title: ILawService.java
* @Package com.frame.tobaCase.service
* @Description: 证据信息service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.Law;

public interface ILawService extends IBaseService<Law, String> {

    /**
     * 
    * @Description: 根据案由编号查询对象
    * @param @param lawNum
    * @param @return
    * @author: liy
    * @date 2017年3月6日 下午7:01:46
    * @throws
     */
    public List<Law> findByLawNum(String lawNum);
}
