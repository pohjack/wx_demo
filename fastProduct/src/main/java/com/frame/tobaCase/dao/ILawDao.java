/**    
* @Title: ILawDao.java
* @Package com.frame.tobaCase.dao
* @Description: 法律法规dao接口
* @author: shizh
* @date 2017年2月21日 下午2:55:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.Law;

public interface ILawDao extends IMybaitsBaseDao<Law, String> {
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
