/**    
* @Title: ICigarInfoService.java
* @Package com.frame.tobaCase.service
* @Description: 卷烟库信息service接口
* @author: shizh
* @date 2017年2月13日 下午2:56:57
* @version V1.0
*/
package com.frame.tobaCase.service;

import java.util.List;

import com.frame.core.service.IBaseService;
import com.frame.tobaCase.entity.CigarInfo;

public interface ICigarInfoService extends IBaseService<CigarInfo, String> {
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
