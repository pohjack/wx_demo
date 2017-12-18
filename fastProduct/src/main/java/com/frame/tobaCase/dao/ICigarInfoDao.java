/**    
* @Title: ICigarInfoDao.java
* @Package com.frame.tobaCase.dao
* @Description: 卷烟信息库dao接口
* @author: shizh
* @date 2017年2月13日 下午3:10:28
* @version V1.0
*/
package com.frame.tobaCase.dao;

import java.util.List;

import com.frame.core.dao.IMybaitsBaseDao;
import com.frame.tobaCase.entity.CigarInfo;

public interface ICigarInfoDao extends IMybaitsBaseDao<CigarInfo, String> {

    /**
     * 根据id数组获取记录集合
     * @param licNo 许可证号
     * @return
     */
    public List<CigarInfo> findByIds(String[] ids);

    /**
     * 根据条形码查询数量
     * @param licNo 许可证号
     * @return
     */
    public Integer findBybarCodeOutCount(String barCode);

    /**
     * 根据条形码查询id
     * @param licNo 许可证号
     * @return
     */
    public CigarInfo findBybarCodeOutObject(String barCode);

    /**
    * @Description: 查询所有卷烟名称
    * @param @return
    * @author: lpy
    * @date 2017年3月7日 上午10:20:32
    * @throws
     */
    public List<String> findAllName();
}
